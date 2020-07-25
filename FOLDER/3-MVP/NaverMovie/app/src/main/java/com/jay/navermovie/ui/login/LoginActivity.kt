package com.jay.navermovie.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jay.navermovie.R
import com.jay.navermovie.data.login.source.LoginRepository
import com.jay.navermovie.data.login.source.LoginRepositoryImpl
import com.jay.navermovie.data.login.source.local.LoginLocalDataSource
import com.jay.navermovie.data.login.source.local.LoginLocalDataSourceImpl
import com.jay.navermovie.ui.search.MovieSearchActivity
import com.jay.navermovie.utils.PreferenceManager

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var tvId: TextView
    private lateinit var tvPw: TextView
    private lateinit var btnLogin: Button
    private lateinit var progressBar: ProgressBar

    private lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        initListener()
        inject()
    }

    private fun initView() {
        tvId = findViewById(R.id.tv_id)
        tvPw = findViewById(R.id.tv_pw)
        btnLogin = findViewById(R.id.btn_login)
        progressBar = findViewById(R.id.progressBar)
    }

    private fun inject() {
        val preferenceManager = PreferenceManager(this)
        val loginLocalDataSource: LoginLocalDataSource = LoginLocalDataSourceImpl(preferenceManager)
        val loginRepository: LoginRepository = LoginRepositoryImpl(loginLocalDataSource)
        presenter = LoginPresenter(this, loginRepository)
    }

    private fun initListener() {
        btnLogin.setOnClickListener {
            val id = tvId.text.toString().trim()
            val pw = tvPw.text.toString().trim()
            presenter.ingLogin(id, pw)
        }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showIdEmptyError() {
        tvId.error = getString(R.string.login_id_empty)
    }

    override fun showPwEmptyError() {
        tvPw.error = getString(R.string.login_pw_empty)
    }

    override fun showInfoFail() {
        Toast.makeText(this, R.string.login_user_fail, Toast.LENGTH_SHORT).show()
    }

    override fun successLogin() {
        startActivity(Intent(this, MovieSearchActivity::class.java))
        finish()
    }
}
