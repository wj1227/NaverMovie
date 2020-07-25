package com.jay.navermovie.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jay.navermovie.data.search.Movie
import com.jay.navermovie.data.search.source.MovieRepository
import retrofit2.HttpException

class MovieSearchViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val TAG = "MovieSearchViewModel"

    private val _isLoading = MutableLiveData(false)
    private val _movieList = MutableLiveData<ArrayList<Movie>>()
    private val _msg = MutableLiveData<MessageSet>()

    private var currentQuery: String = ""
    val query = MutableLiveData<String>()

    val movieList: LiveData<ArrayList<Movie>> get() = _movieList
    val isLoading: LiveData<Boolean> get() = _isLoading
    val msg: LiveData<MessageSet> get() = _msg


    fun requestMovie() {
        currentQuery = query.value.toString().trim()

        if (currentQuery.isEmpty()) {
            _msg.value = MessageSet.EMPTY_QUERY
        } else {
            _isLoading.value = true
            movieRepository.searchMovie(currentQuery,
                success = {
                    if (it.isEmpty()) {
                        _msg.value = MessageSet.EMPTY_RESULT
                    } else {
                        _msg.value = MessageSet.SUCCESS
                        _movieList.value = it as ArrayList<Movie>?
                    }
                    _isLoading.value = false
                },
                fail = {
                    Log.d(TAG, it.toString())
                    when (it) {
                        is HttpException -> _msg.value = MessageSet.NETWORK_ERROR
                        else -> _msg.value = MessageSet.BASIC
                    }
                    _isLoading.value = false
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