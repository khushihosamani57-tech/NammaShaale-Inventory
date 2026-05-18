package com.example.nammashaaleinventory.ui.assets

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.ui.dashboard.DashboardActivity
import com.example.nammashaaleinventory.ui.profile.ProfileActivity
import com.example.nammashaaleinventory.ui.reports.ReportsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ScanAssetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_asset)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        setupNavigation()
    }

    private fun setupNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_scan
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, DashboardActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_assets -> {
                    startActivity(Intent(this, AssetListActivity::class.java))
                    finish()
                    false
                }
                R.id.nav_scan -> true
                R.id.nav_reports -> {
                    startActivity(Intent(this, ReportsActivity::class.java))
                    finish()
                    false
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                    false
                }
                else -> false
            }
        }
    }
}