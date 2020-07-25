package com.jay.navermovie.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jay.navermovie.R
import com.jay.navermovie.data.login.source.LoginRepository
import com.jay.navermovie.data.login.source.LoginRepositoryImpl
import com.jay.navermovie.data.login.source.local.LoginLocalDataSource
import com.jay.navermovie.data.login.source.local.LoginLocalDataSourceImpl
import com.jay.navermovie.databinding.ActivityLoginBinding
import com.jay.navermovie.ui.search.MovieSearchActivity
import com.jay.navermovie.utils.PreferenceManager

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initDataBinding()
        inject()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.bind = this
    }

    private fun inject() {
        val preferenceManager = PreferenceManager(this)
        val loginLocalDataSource: LoginLocalDataSource = LoginLocalDataSourceImpl(preferenceManager)
        val loginRepository: LoginRepository = LoginRepositoryImpl(loginLocalDataSource)
        presenter = LoginPresenter(this, loginRepository)
    }

    fun btnLoginClick() {
        val id = binding.tvId.text.toString().trim()
        val pw = binding.tvPw.text.toString().trim()
        presenter.ingLogin(id, pw)
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun showIdEmptyError() {
        binding.tvId.error = getString(R.string.login_id_empty)
    }

    override fun showPwEmptyError() {
        binding.tvPw.error = getString(R.string.login_pw_empty)
    }

    override fun showInfoFail() {
        Toast.makeText(this, R.string.login_user_fail, Toast.LENGTH_SHORT).show()
    }

    override fun successLogin() {
        startActivity(Intent(this, MovieSearchActivity::class.java))
        finish()
    }
}
