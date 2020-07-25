package com.jay.navermovie.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jay.navermovie.R
import com.jay.navermovie.base.BaseActivity
import com.jay.navermovie.data.login.source.LoginRepository
import com.jay.navermovie.data.login.source.LoginRepositoryImpl
import com.jay.navermovie.data.login.source.local.LoginLocalDataSource
import com.jay.navermovie.data.login.source.local.LoginLocalDataSourceImpl
import com.jay.navermovie.databinding.ActivityLoginBinding
import com.jay.navermovie.ui.search.MovieSearchActivity
import com.jay.navermovie.utils.PreferenceManager

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private lateinit var preferenceManager: PreferenceManager
    private lateinit var loginLocalDataSource: LoginLocalDataSource
    private lateinit var loginRepository: LoginRepository
    private val viewModel: LoginViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return LoginViewModel(loginRepository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()
        initViewModelCallback()
    }

    private fun inject() {
        preferenceManager = PreferenceManager(this)
        loginLocalDataSource = LoginLocalDataSourceImpl(preferenceManager)
        loginRepository = LoginRepositoryImpl(loginLocalDataSource)
        binding.vm = viewModel
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            isIdEmpty.observe(this@LoginActivity, Observer {
                showIdEmptyError()
            })
            isPwEmpty.observe(this@LoginActivity, Observer {
                showPwEmptyError()
            })
            loginErrorMsg.observe(this@LoginActivity, Observer {
                showToast(getString(R.string.login_user_fail))
            })
            loginSuccessMsg.observe(this@LoginActivity, Observer {
                successLogin()
            })
        }
    }

    private fun showIdEmptyError() {
        binding.tvId.error = getString(R.string.login_id_empty)
    }

    private fun showPwEmptyError() {
        binding.tvPw.error = getString(R.string.login_pw_empty)
    }

    private fun successLogin() {
        startActivity(Intent(this, MovieSearchActivity::class.java))
        finish()
    }
}
