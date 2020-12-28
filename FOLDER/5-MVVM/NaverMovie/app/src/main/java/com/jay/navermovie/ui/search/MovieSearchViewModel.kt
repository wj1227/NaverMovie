package com.jay.navermovie.ui.search

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.navermovie.base.BaseVieModel
import com.jay.navermovie.data.search.Movie
import com.jay.navermovie.data.search.source.MovieRepository
import com.jay.navermovie.utils.getString
import retrofit2.HttpException

class MovieSearchViewModel(private val movieRepository: MovieRepository) : BaseVieModel<MovieStatus> {
    private val TAG = "MovieSearchViewModel"

    var query: ObservableField<String> = ObservableField("")
    var isLoading: ObservableBoolean = ObservableBoolean(false)
    var movieList: ObservableField<ArrayList<Movie>> = ObservableField()
    private val status = MutableLiveData<MovieStatus>()
    val movieStatus: LiveData<MovieStatus> get() = status


    fun requestMovie() {
        val query = query.getString()
        if (query.isEmpty())
            MovieStatus.QueryEmpty.updateStatus()
        else {
            isLoading.set(true)
            movieRepository.searchMovie(query,
                success = {
                    when(it.isEmpty()){
                        true -> MovieStatus.DataEmpty.updateStatus()
                        false -> {
                            movieList.set(it as ArrayList<Movie>)
                            MovieStatus.Success.updateStatus()
                        }
                    }
                    isLoading.set(false)
                },
                fail = {
                    when (it) {
                        is HttpException -> MovieStatus.NetworkError.updateStatus()
                    }
                    it.printStackTrace()
                    isLoading.set(false)
                }
            )
        }
    }

    override fun MovieStatus.updateStatus() = status.postValue(this)

}