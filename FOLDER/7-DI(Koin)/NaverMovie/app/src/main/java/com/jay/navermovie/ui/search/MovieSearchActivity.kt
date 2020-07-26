package com.jay.navermovie.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import com.jay.navermovie.R
import com.jay.navermovie.base.BaseActivity
import com.jay.navermovie.databinding.ActivityMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieSearchActivity : BaseActivity<ActivityMovieBinding>(R.layout.activity_movie) {
    private val TAG = javaClass.simpleName

    private lateinit var adapter: MovieSearchAdapter
    private val viewModel: MovieSearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initAdapter()
        initViewModelCallback()
    }

    private fun initBinding() {
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

