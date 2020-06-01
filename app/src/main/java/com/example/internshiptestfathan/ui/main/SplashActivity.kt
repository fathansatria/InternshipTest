package com.example.internshiptestfathan.ui.main

import android.R.id.home
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.internshiptestfathan.R


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            val main = Intent(this, MainActivity::class.java)
            startActivity(main)
            finish()
        }, 2000)
    }
}