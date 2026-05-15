package com.example.nammashaaleinventory

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nammashaaleinventory.ui.splash.SplashActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Always start from Splash to ensure correct flow (Onboarding -> Login -> Dashboard)
        startActivity(Intent(this, SplashActivity::class.java))
        finish()
    }
}
