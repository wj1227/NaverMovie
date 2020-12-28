package com.jay.navermovie.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import com.jay.navermovie.R
import com.jay.navermovie.base.BaseActivity
import com.jay.navermovie.databinding.ActivityMovieBinding
import com.jay.navermovie.utils.Message
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
            movieStatus.observe(this@MovieSearchActivity, Observer {status ->
                when(status){
                    is MovieStatus.NetworkError -> Message.NETWORK_ERROR.message().showShortToast()
                    is MovieStatus.Success -> Message.SUCESS.message().showShortToast()
                    is MovieStatus.DataEmpty -> Message.EMPTY_RESULT.message().showShortToast()
                    is MovieStatus.QueryEmpty -> Message.EMPTY_QUERY.message().showShortToast()
                }
            })
        }
    }

}

