package com.jay.navermovie.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.jay.navermovie.R
import com.jay.navermovie.api.NetworkService
import com.jay.navermovie.data.NaverResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName

    private lateinit var searchBtn: Button
    private lateinit var searchMoive: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: MovieAdapter
    private lateinit var call: Call<NaverResult>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        initView()
        initAdapter()
    }

    private fun initView() {
        searchBtn = findViewById(R.id.btn_search)
        searchMoive = findViewById(R.id.et_search)
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerView)

        onClickListener()
    }

    private fun initAdapter() {
        adapter = MovieAdapter()
        recyclerView.adapter = adapter

        // 액션을 받는 액티비티가 없으면 예외가 발생할 경우에 대한 처리
        adapter.setItemClickListener { movie ->
            Intent(Intent.ACTION_VIEW, Uri.parse(movie.link)).takeIf {
                it.resolveActivity(packageManager) != null
            }?.run(this::startActivity)
        }
    }

    private fun onClickListener() {
        searchBtn.setOnClickListener { searchMovie() }
    }

    private fun isLoading(loading: Boolean) {
        progressBar.visibility = if (loading) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    private fun searchMovie() {
        val movie = searchMoive.text.toString().trim()

        if (movie.isEmpty()) {
            onMessage("영화제목을 입력해 주세요")
        } else {
            onMessage("잠시만 기다려 주세요")
            requestMovie(movie)
        }
    }

    private fun onMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun requestMovie(query: String) {
        isLoading(true)
        adapter.clear()

        val select = NetworkService.naverApi
        call = select.getSearchMovie(query)

        call.enqueue(object : Callback<NaverResult> {
            override fun onFailure(call: Call<NaverResult>, t: Throwable) {
                isLoading(false)
                onMessage("통신에 실패 했습니다.")
            }

            override fun onResponse(call: Call<NaverResult>, response: Response<NaverResult>) {
                isLoading(false)
                with(response) {
                    val body = body()

                    if (isSuccessful && body != null) {
                        body.items.let { adapter.setItems(it) }
                    } else {
                        onMessage("불러오는데 실패 했습니다.")
                    }
                }
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()

        // call객체가 초기화 되지 않았을 경우에 대한 예외
        if (this::call.isInitialized) call.cancel()
    }
}

