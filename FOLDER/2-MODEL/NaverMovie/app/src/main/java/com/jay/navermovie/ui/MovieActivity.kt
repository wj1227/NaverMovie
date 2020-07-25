package com.jay.navermovie.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.jay.navermovie.R
import com.jay.navermovie.utils.MyApplication
import retrofit2.HttpException

class MovieActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName

    private lateinit var searchBtn: Button
    private lateinit var searchMoive: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: MovieAdapter
    private lateinit var myApplication: MyApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        initView()
        initAdapter()
    }

    private fun initView() {
        myApplication = application as MyApplication
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

        myApplication.movieRepository.searchMovie(query,
            success = {
                if (it.isEmpty()) {
                    onMessage("존재하지 않은 영화입니다.")
                } else {
                    adapter.clear()
                    adapter.setItems(it)
                    onMessage("영화를 불러왔습니다.")
                }
                isLoading(false)
            },
            fail = {
                Log.d(TAG, "$it")
                when (it) {
                    is HttpException -> onMessage("네트워크 상태가 좋지 않습니다.")
                    else -> onMessage(it.message.toString())
                }
                isLoading(false)
            }
        )
    }
}

