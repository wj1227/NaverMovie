package com.jay.navermovie.ui.search

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
import com.jay.navermovie.data.search.Movie
import com.jay.navermovie.utils.MyApplication

class MovieSearchActivity : AppCompatActivity(), MovieSearchContract.View {
    private val TAG = javaClass.simpleName

    private lateinit var searchBtn: Button
    private lateinit var searchMoive: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: MovieSearchAdapter
    private lateinit var myApplication: MyApplication
    private lateinit var presenter: MovieSearchContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        initView()
        initAdapter()
        onClickListener()
        presenter = MovieSearchPresenter(this, myApplication.movieRepository)
    }

    private fun initView() {
        myApplication = application as MyApplication
        searchBtn = findViewById(R.id.btn_search)
        searchMoive = findViewById(R.id.et_search)
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun initAdapter() {
        adapter = MovieSearchAdapter()
        recyclerView.adapter = adapter
    }

    private fun onClickListener() {
        searchBtn.setOnClickListener {
            val movie = searchMoive.text.toString().trim()
            presenter.searchMovie(movie)
        }

        // 액션을 받는 액티비티가 없으면 예외가 발생할 경우에 대한 처리
        adapter.setItemClickListener { movie ->
            Intent(Intent.ACTION_VIEW, Uri.parse(movie.link)).takeIf {
                it.resolveActivity(packageManager) != null
            }?.run(this::startActivity)
        }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun networkError() {
        Toast.makeText(this, R.string.search_network_error_msg, Toast.LENGTH_SHORT).show()
    }

    override fun emptyMovies() {
        Toast.makeText(this, R.string.search_emptyMovies, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage() {
        Toast.makeText(this, R.string.search_query_empty, Toast.LENGTH_SHORT).show()
    }

    override fun errorMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun successSearchMovies(moives: List<Movie>) {
        adapter.clear()
        adapter.setItems(moives)
        Toast.makeText(this, R.string.search_success, Toast.LENGTH_SHORT).show()
    }
}

