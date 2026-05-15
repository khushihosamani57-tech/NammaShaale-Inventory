package com.example.nammashaaleinventory.ui.onboarding

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.ui.auth.LoginActivity

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: OnboardingAdapter
    private lateinit var dotsLayout: LinearLayout
    private lateinit var btnNext: Button

    private val onboardingItems = listOf(
        OnboardingItem(
            "📦",
            "Track Every Asset",
            "Register tablets, lab equipment, sports kits and every school resource in one place.",
            Color.parseColor("#E3F2FD")
        ),
        OnboardingItem(
            "📸",
            "Photo Documentation",
            "Capture photos of each asset with your camera so records are always visual & accurate.",
            Color.parseColor("#E8F5E9")
        ),
        OnboardingItem(
            "🔧",
            "Request Repairs Fast",
            "Log issues, report damage and submit repair requests instantly.",
            Color.parseColor("#FFF3E0")
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)
        dotsLayout = findViewById(R.id.dotsLayout)
        btnNext = findViewById(R.id.btnNext)

        adapter = OnboardingAdapter(onboardingItems)
        viewPager.adapter = adapter

        setupDots(0)

        viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setupDots(position)

                    if (position == onboardingItems.size - 1) {
                        btnNext.text = "Get Started →"
                    } else {
                        btnNext.text = "Next →"
                    }
                }
            }
        )

        btnNext.setOnClickListener {
            if (viewPager.currentItem + 1 < onboardingItems.size) {
                viewPager.currentItem += 1
            } else {
                navigateToLogin()
            }
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun setupDots(position: Int) {
        dotsLayout.removeAllViews()
        for (i in onboardingItems.indices) {
            val dot = ImageView(this)
            if (i == position) {
                dot.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.dot_selected
                    )
                )
            } else {
                dot.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.dot_unselected
                    )
                )
            }
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(6, 0, 6, 0)
            dot.layoutParams = params
            dotsLayout.addView(dot)
        }
    }
}