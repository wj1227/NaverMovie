package com.jay.navermovie.data.search.source

import com.jay.navermovie.data.search.Movie
import com.jay.navermovie.data.search.source.local.MovieLocalDataSource
import com.jay.navermovie.data.search.source.remote.MovieRemoteDataSource
import com.jay.navermovie.utils.NetWorkManager

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val networkManager: NetWorkManager
) : MovieRepository {

    override fun searchMovie(
        query: String,
        success: (List<Movie>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        if (networkManager.networkState()) {
            // remote 검색 전 local에서 선검색 후 데이터 전달한다.
            with(movieLocalDataSource.searchMovie(query)) {
                if (this.isNotEmpty())
                    success(this)
            }

            // remote에서 검색한다.
            movieRemoteDataSource.searchMovie(
                query,
                success = {
                    movieLocalDataSource.insertMovies(it)
                    success(it)
                },
                fail = {
                    with(movieLocalDataSource.searchMovie(query)) {
                        if (this.isEmpty()) {
                            fail(it)
                        } else {
                            success(this)
                        }
                    }
                }
            )
        } else {
            // local에서 검색
            with(movieLocalDataSource.searchMovie(query)) {
                if (this.isEmpty()) {
                    fail(Throwable("네트워크 상태를 확인해 주세요"))
                } else {
                    success(this)
                }
            }
        }
    }
}