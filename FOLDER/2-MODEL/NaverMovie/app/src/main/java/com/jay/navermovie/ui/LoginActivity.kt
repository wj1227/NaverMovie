package com.jay.navermovie.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jay.navermovie.R
import com.jay.navermovie.utils.PreferenceManager

class LoginActivity : AppCompatActivity() {

    private lateinit var tvId: TextView
    private lateinit var tvPw: TextView
    private lateinit var btnLogin: Button

    companion object {
        private const val USER_ID = "1234"
        private const val USER_PW = "1234"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        initListener()
    }

    private fun initView() {
        tvId = findViewById(R.id.tv_id)
        tvPw = findViewById(R.id.tv_pw)
        btnLogin = findViewById(R.id.btn_login)
    }

    private fun initListener() {
        btnLogin.setOnClickListener {
            val id = tvId.text.toString().trim()
            val pw = tvPw.text.toString().trim()

            if (id != USER_ID || pw != USER_PW) {
                tvPw.error = "아이디 또는 비밀번호가 틀렸습니다"
            } else if (id.isEmpty()) {
                tvId.error = "아이디를 입력해 주세요"
            } else if (pw.isEmpty()) {
                tvPw.error = "비밀번호를 입력해 주세요"
            } else {
                startActivity(Intent(this, MovieActivity::class.java))
                PreferenceManager.setLogin(this, PreferenceManager.AUTO_LOGIN_KEY, true)
                finish()
            }
        }
    }


}
