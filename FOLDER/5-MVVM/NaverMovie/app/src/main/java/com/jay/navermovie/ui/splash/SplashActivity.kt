package com.jay.navermovie.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import com.jay.navermovie.R
import com.jay.navermovie.base.BaseActivity
import com.jay.navermovie.data.login.source.LoginRepository
import com.jay.navermovie.data.login.source.LoginRepositoryImpl
import com.jay.navermovie.data.login.source.local.LoginLocalDataSource
import com.jay.navermovie.data.login.source.local.LoginLocalDataSourceImpl
import com.jay.navermovie.ui.login.LoginActivity
import com.jay.navermovie.ui.search.MovieSearchActivity
import com.jay.navermovie.utils.PreferenceManager

class SplashActivity : BaseActivity() {
    private lateinit var viewModel: SplashViewModel
    private val DELAY_TIME = 1500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        inject()
        initViewModelCallback()
        viewModel.doSplash()
    }

    private fun inject() {
        val preferenceManager = PreferenceManager(this)
        val loginLocalDataSource: LoginLocalDataSource = LoginLocalDataSourceImpl(preferenceManager)
        val loginRepository: LoginRepository = LoginRepositoryImpl(loginLocalDataSource)
        viewModel = SplashViewModel(loginRepository)
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            splashStatus.observe(this@SplashActivity, Observer { status ->
                when(status){
                    is SplashStatus.LOGIN -> goLogin()
                    is SplashStatus.MOVIE -> goMovieSearch()
                }
            })
        }
    }

    private fun goLogin() {
        postDelay {
            LoginActivity::class.java.startActivity(this)
        }
    }

    private fun goMovieSearch() {
        postDelay {
            MovieSearchActivity::class.java.startActivity(this)
        }
    }

    private fun postDelay(action : () -> Unit){
        Handler(Looper.getMainLooper()).postDelayed({
            action()
        },DELAY_TIME)
    }

}
