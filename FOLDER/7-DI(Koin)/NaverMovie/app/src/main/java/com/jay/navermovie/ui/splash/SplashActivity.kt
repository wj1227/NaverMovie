package com.jay.navermovie.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.jay.navermovie.R
import com.jay.navermovie.base.BaseActivity
import com.jay.navermovie.databinding.ActivitySplashBinding
import com.jay.navermovie.ui.login.LoginActivity
import com.jay.navermovie.ui.search.MovieSearchActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initViewModelCallback()
        viewModel.doSplash()
    }

    private fun initBinding() {
        binding.vm = viewModel
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            goMovieSearch.observe(this@SplashActivity, Observer {
                goMovieSearch()
            })
            goLogin.observe(this@SplashActivity, Observer {
                goLogin()
            })
        }
    }

    fun goLogin() {
        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1500L)
    }

    fun goMovieSearch() {
        Handler().postDelayed({
            showToast(getString(R.string.auto_login_msg))
            startActivity(Intent(this, MovieSearchActivity::class.java))
            finish()
        }, 1500L)
    }

}
