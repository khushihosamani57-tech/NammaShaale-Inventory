package com.example.nammashaaleinventory.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.ui.auth.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Delay for 2 seconds to show the splash screen
        Handler(Looper.getMainLooper()).postDelayed({
            // Simple navigation to Login for this demo/rebuild
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 2000)
    }
}