package com.jay.navermovie.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jay.navermovie.R
import com.jay.navermovie.data.search.Movie
import com.jay.navermovie.databinding.ActivityMovieBinding
import com.jay.navermovie.utils.MyApplication

class MovieSearchActivity : AppCompatActivity(), MovieSearchContract.View {
    private val TAG = javaClass.simpleName

    private lateinit var binding: ActivityMovieBinding

    private lateinit var adapter: MovieSearchAdapter
    private lateinit var myApplication: MyApplication
    private lateinit var presenter: MovieSearchContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        initDataBinding()
        inject()
        initAdapter()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        binding.bind = this
    }

    private fun inject() {
        myApplication = application as MyApplication
        presenter = MovieSearchPresenter(this, myApplication.movieRepository)
    }

    private fun initAdapter() {
        adapter = MovieSearchAdapter { movie ->
            Intent(Intent.ACTION_VIEW, Uri.parse(movie.link)).takeIf {
                it.resolveActivity(packageManager) != null
            }?.run(this::startActivity)
        }

        binding.recyclerView.adapter = adapter
    }

    fun btnSearchClick() {
        val movie = binding.etSearch.text.toString().trim()
        presenter.searchMovie(movie)
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.INVISIBLE
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

