package com.jay.navermovie.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jay.navermovie.R
import com.jay.navermovie.utils.PreferenceManager

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startActivity()
    }

    private fun startActivity() {
        Handler().postDelayed({
            if (PreferenceManager.getLogin(this, PreferenceManager.AUTO_LOGIN_KEY)) {
                Toast.makeText(this, "자동 로그인 성공", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MovieActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 1500L)
    }
}
