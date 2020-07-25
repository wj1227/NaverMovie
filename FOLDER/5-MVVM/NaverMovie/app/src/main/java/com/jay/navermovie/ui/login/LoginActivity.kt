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
import androidx.databinding.Observable
import com.jay.navermovie.R
import com.jay.navermovie.base.BaseActivity
import com.jay.navermovie.data.login.source.LoginRepository
import com.jay.navermovie.data.login.source.LoginRepositoryImpl
import com.jay.navermovie.data.login.source.local.LoginLocalDataSource
import com.jay.navermovie.data.login.source.local.LoginLocalDataSourceImpl
import com.jay.navermovie.databinding.ActivityLoginBinding
import com.jay.navermovie.ui.search.MovieSearchActivity
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

        val preferenceManager = PreferenceManager(this)
        val loginLocalDataSource: LoginLocalDataSource = LoginLocalDataSourceImpl(preferenceManager)
        val loginRepository: LoginRepository = LoginRepositoryImpl(loginLocalDataSource)

        viewModel = LoginViewModel(loginRepository)
        binding.vm = viewModel
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            isIdEmpty.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showIdEmptyError()
                }
            })
            isPwEmpty.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showPwEmptyError()
                }
            })
            loginErrorMsg.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showToast(getString(R.string.login_user_fail))
                }
            })
            loginSuccessMsg.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    successLogin()
                }
            })
        }
    }

    fun showIdEmptyError() {
        binding.tvId.error = getString(R.string.login_id_empty)
    }

    fun showPwEmptyError() {
        binding.tvPw.error = getString(R.string.login_pw_empty)
    }

    fun successLogin() {
        startActivity(Intent(this, MovieSearchActivity::class.java))
        finish()
    }
}
