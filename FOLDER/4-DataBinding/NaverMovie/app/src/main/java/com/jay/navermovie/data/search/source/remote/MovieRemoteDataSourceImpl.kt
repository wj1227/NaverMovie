package com.jay.navermovie.data.search.source.remote

import android.util.Log
import com.jay.navermovie.api.NetworkService
import com.jay.navermovie.data.search.Movie
import com.jay.navermovie.data.search.NaverResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class MovieRemoteDataSourceImpl(private val api: NetworkService) :
    MovieRemoteDataSource {
    companion object {
        const val TAG = "MovieRemoteDataSourceImpl"
    }

    override fun searchMovie(
        query: String,
        success: (List<Movie>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        val call = api.naverApi.getSearchMovie(query)
        call.enqueue(object : Callback<NaverResult> {
            override fun onFailure(call: Call<NaverResult>, t: Throwable) {
                Log.d(TAG, "Remote fail")
                fail(t)
            }

            override fun onResponse(call: Call<NaverResult>, response: Response<NaverResult>) {
                with(response) {
                    val body = body()
                    if (isSuccessful && body != null) {
                        Log.d(TAG, "Remote success")
                        success(body.items)
                    } else {
                        Log.d(TAG, "Remote response fail")
                        fail(HttpException(this))
                    }
                }
            }
        })
    }
}