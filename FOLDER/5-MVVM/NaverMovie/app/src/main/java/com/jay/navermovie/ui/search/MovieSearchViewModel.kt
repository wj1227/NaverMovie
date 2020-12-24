package com.jay.navermovie.ui.search

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.jay.navermovie.data.search.Movie
import com.jay.navermovie.data.search.source.MovieRepository
import com.jay.navermovie.utils.getString
import retrofit2.HttpException

class MovieSearchViewModel(private val movieRepository: MovieRepository) {
    private val TAG = "MovieSearchViewModel"

    var query: ObservableField<String> = ObservableField("")
    var isLoading: ObservableBoolean = ObservableBoolean(false)
    var movieList: ObservableField<ArrayList<Movie>> = ObservableField()
    var msg: ObservableField<MessageSet> = ObservableField()


    fun requestMovie() {
        val query = query.getString()

        if (query.isEmpty()) {
            msg.set(MessageSet.EMPTY_QUERY)
        } else {
            isLoading.set(true)
            movieRepository.searchMovie(query,
                success = {
                    if (it.isEmpty()) {
                        msg.set(MessageSet.EMPTY_RESULT)
                    } else {
                        msg.set(MessageSet.SUCCESS)
                        movieList.set(it as ArrayList<Movie>?)
                    }
                    isLoading.set(false)
                },
                fail = {
                    Log.d(TAG, it.toString())
                    when (it) {
                        is HttpException -> msg.set(MessageSet.NETWORK_ERROR)
                        else -> msg.set(MessageSet.BASIC) // else 임의로 삽입
                    }
                    isLoading.set(false)
                }
            )
        }
    }

    enum class MessageSet {
        BASIC,
        EMPTY_QUERY,
        NETWORK_ERROR,
        SUCCESS,
        EMPTY_RESULT
    }

}