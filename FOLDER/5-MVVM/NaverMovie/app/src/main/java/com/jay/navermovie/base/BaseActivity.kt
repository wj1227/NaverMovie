package com.jay.navermovie.base

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jay.navermovie.ui.login.LoginActivity

abstract class BaseActivity : AppCompatActivity() {

    protected fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    }

    fun String.showLongToast() = Toast.makeText(applicationContext,this,Toast.LENGTH_LONG).show()
    fun String.showShortToast() = Toast.makeText(applicationContext,this,Toast.LENGTH_SHORT).show()

    fun <T> T.startActivity(activity: Activity){
        startActivity(Intent(activity,this as Class<*>))
        finish()
    }
}