package com.jay.navermovie.ui.login

import android.os.Bundle
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.jay.navermovie.R
import com.jay.navermovie.base.BaseActivity
import com.jay.navermovie.data.login.source.LoginRepository
import com.jay.navermovie.data.login.source.LoginRepositoryImpl
import com.jay.navermovie.data.login.source.local.LoginLocalDataSource
import com.jay.navermovie.data.login.source.local.LoginLocalDataSourceImpl
import com.jay.navermovie.databinding.ActivityLoginBinding
import com.jay.navermovie.ui.search.MovieSearchActivity
import com.jay.navermovie.utils.Message
import com.jay.navermovie.utils.MyApplication
import com.jay.navermovie.utils.PreferenceManager

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        inject()
        initViewModelCallback()
    }

    private fun inject() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = LoginViewModel()
        binding.vm = viewModel
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            loginStatus.observe(this@LoginActivity, Observer {
                when(it){
                    is LoginStatus.IdEmpty -> binding.tvId.emptyError()
                    is LoginStatus.PwEmpty -> binding.tvPw.emptyError()
                    is LoginStatus.LoginError -> Message.LOGIN_ERROR.message().showShortToast()
                    is LoginStatus.LoginSuccess -> successLogin()
                }
            })
        }
    }

    private fun successLogin() {
        with(PreferenceManager(this)){
            autoLogin = true
            MovieSearchActivity::class.java.startActivity(this@LoginActivity)
        }
    }



    private fun EditText.emptyError(){
        when(this){
            binding.tvId -> error = Message.IDEMPTY_ERROR.message()
            binding.tvPw -> error = Message.PWEMPTY_ERROR.message()
        }
    }



}
