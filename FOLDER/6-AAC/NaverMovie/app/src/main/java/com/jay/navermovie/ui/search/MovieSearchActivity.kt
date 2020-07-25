package com.jay.navermovie.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jay.navermovie.R
import com.jay.navermovie.base.BaseActivity
import com.jay.navermovie.databinding.ActivityMovieBinding
import com.jay.navermovie.utils.MyApplication

class MovieSearchActivity : BaseActivity<ActivityMovieBinding>(R.layout.activity_movie) {
    private val TAG = javaClass.simpleName

    private lateinit var adapter: MovieSearchAdapter
    private lateinit var myApplication: MyApplication
    private val viewModel: MovieSearchViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MovieSearchViewModel(myApplication.movieRepository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()
        initAdapter()
        initViewModelCallback()
    }

    private fun inject() {
        myApplication = application as MyApplication
        binding.vm = viewModel
    }

    private fun initAdapter() {
        adapter = MovieSearchAdapter { movie ->
            Intent(Intent.ACTION_VIEW, Uri.parse(movie.link)).takeIf {
                it.resolveActivity(packageManager) != null
            }?.run(this::startActivity)
        }

        binding.recyclerView.adapter = adapter
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            msg.observe(this@MovieSearchActivity, Observer {
                when (msg.value) {
                    MovieSearchViewModel.MessageSet.NETWORK_ERROR -> showToast(getString(R.string.search_network_error_msg))
                    MovieSearchViewModel.MessageSet.SUCCESS -> showToast(getString(R.string.search_success))
                    MovieSearchViewModel.MessageSet.EMPTY_RESULT -> showToast(getString(R.string.search_emptyMovies))
                    MovieSearchViewModel.MessageSet.EMPTY_QUERY -> showToast(getString(R.string.search_query_empty))
                }
            })
        }
    }

}

