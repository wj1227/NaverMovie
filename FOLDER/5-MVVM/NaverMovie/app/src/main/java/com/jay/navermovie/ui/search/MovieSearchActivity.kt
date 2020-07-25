package com.jay.navermovie.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.jay.navermovie.R
import com.jay.navermovie.base.BaseActivity
import com.jay.navermovie.data.search.Movie
import com.jay.navermovie.databinding.ActivityMovieBinding
import com.jay.navermovie.utils.MyApplication

class MovieSearchActivity : BaseActivity() {
    private val TAG = javaClass.simpleName

    private lateinit var binding: ActivityMovieBinding

    private lateinit var adapter: MovieSearchAdapter
    private lateinit var myApplication: MyApplication
    private lateinit var viewModel: MovieSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        inject()
        initAdapter()
        initViewModelCallback()
    }

    private fun inject() {
        myApplication = application as MyApplication
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        viewModel = MovieSearchViewModel(myApplication.movieRepository)
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
            msg.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    when (msg.get()) {
                        MovieSearchViewModel.MessageSet.NETWORK_ERROR -> showToast(getString(R.string.search_network_error_msg))
                        MovieSearchViewModel.MessageSet.SUCCESS -> showToast(getString(R.string.search_success))
                        MovieSearchViewModel.MessageSet.EMPTY_RESULT -> showToast(getString(R.string.search_emptyMovies))
                        MovieSearchViewModel.MessageSet.EMPTY_QUERY -> showToast(getString(R.string.search_query_empty))
                    }
                    msg.set(MovieSearchViewModel.MessageSet.BASIC)
                }
            })
        }
    }

}

