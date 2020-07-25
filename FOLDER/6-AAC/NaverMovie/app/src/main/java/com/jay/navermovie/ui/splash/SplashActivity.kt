package com.jay.navermovie.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jay.navermovie.R
import com.jay.navermovie.base.BaseActivity
import com.jay.navermovie.data.login.source.LoginRepository
import com.jay.navermovie.data.login.source.LoginRepositoryImpl
import com.jay.navermovie.data.login.source.local.LoginLocalDataSource
import com.jay.navermovie.data.login.source.local.LoginLocalDataSourceImpl
import com.jay.navermovie.databinding.ActivitySplashBinding
import com.jay.navermovie.ui.login.LoginActivity
import com.jay.navermovie.ui.search.MovieSearchActivity
import com.jay.navermovie.utils.PreferenceManager

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private lateinit var preferenceManager: PreferenceManager
    private lateinit var loginLocalDataSource: LoginLocalDataSource
    lateinit var loginRepository: LoginRepository
    private val viewModel: SplashViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SplashViewModel(loginRepository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()
        initViewModelCallback()
        viewModel.doSplash()
    }

    private fun inject() {
        preferenceManager = PreferenceManager(this)
        loginLocalDataSource = LoginLocalDataSourceImpl(preferenceManager)
        loginRepository = LoginRepositoryImpl(loginLocalDataSource)
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
