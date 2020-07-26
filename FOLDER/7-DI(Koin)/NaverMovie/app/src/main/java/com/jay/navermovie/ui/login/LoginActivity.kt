package com.jay.navermovie.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.jay.navermovie.R
import com.jay.navermovie.base.BaseActivity
import com.jay.navermovie.databinding.ActivityLoginBinding
import com.jay.navermovie.ui.search.MovieSearchActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initViewModelCallback()
    }

    private fun initBinding() {
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
