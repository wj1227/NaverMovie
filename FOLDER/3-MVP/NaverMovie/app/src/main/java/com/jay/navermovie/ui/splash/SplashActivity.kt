package com.jay.navermovie.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jay.navermovie.R
import com.jay.navermovie.data.login.source.LoginRepository
import com.jay.navermovie.data.login.source.LoginRepositoryImpl
import com.jay.navermovie.data.login.source.local.LoginLocalDataSource
import com.jay.navermovie.data.login.source.local.LoginLocalDataSourceImpl
import com.jay.navermovie.ui.login.LoginActivity
import com.jay.navermovie.ui.search.MovieSearchActivity
import com.jay.navermovie.utils.PreferenceManager

class SplashActivity : AppCompatActivity(), SplashContract.View {
    private lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        inject()
    }

    private fun inject() {
        val preferenceManager = PreferenceManager(this)
        val loginLocalDataSource: LoginLocalDataSource = LoginLocalDataSourceImpl(preferenceManager)
        val loginRepository: LoginRepository = LoginRepositoryImpl(loginLocalDataSource)
        presenter = SplashPresenter(this, loginRepository)
    }

    override fun goLogin() {
        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1500L)
    }

    override fun goMovieSearch() {
        Handler().postDelayed({
            startActivity(Intent(this, MovieSearchActivity::class.java))
            finish()
        }, 1500L)
    }

    override fun showAutoLogin() {
        Toast.makeText(this, R.string.auto_login_msg, Toast.LENGTH_SHORT).show()
    }
}
