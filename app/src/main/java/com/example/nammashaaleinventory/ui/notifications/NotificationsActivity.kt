package com.example.nammashaaleinventory.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.ui.assets.ScanAssetActivity

class NotificationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        val dummyNotifications = listOf(
            NotificationItem("Scan Needed", "Periodic audit for Lab 2 is due. Please scan assets.", "Just now", R.drawable.ic_qr_code, R.color.primary, "SCAN"),
            NotificationItem("Projector - Class 8B", "Marked as 'Needs Repair'", "2 mins ago", R.drawable.ic_repair, R.color.repair_orange, "ASSET"),
            NotificationItem("Chair - Staff Room", "Marked as 'Out of Order'", "1 hour ago", R.drawable.dot_red, R.color.error, "ASSET"),
            NotificationItem("Computer - Lab 2", "Warranty will expire in 15 days", "2 hours ago", R.drawable.ic_assets, R.color.primary, "INFO"),
            NotificationItem("New Asset Added", "Printer - Office", "2 days ago", R.drawable.ic_assets, R.color.primary, "INFO")
        )
        
        recyclerView.adapter = NotificationAdapter(dummyNotifications) { item ->
            when (item.type) {
                "SCAN" -> {
                    startActivity(Intent(this, ScanAssetActivity::class.java))
                }
                "ASSET" -> {
                    // Navigate to asset details if we had an ID
                    // startActivity(Intent(this, AssetDetailsActivity::class.java))
                }
            }
        }
        
        setupNavigation()
    }

    private fun setupNavigation() {
        val bottomNav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    finish()
                    true
                }
                R.id.nav_assets -> {
                    startActivity(Intent(this, com.example.nammashaaleinventory.ui.assets.AssetListActivity::class.java))
                    finish()
                    false
                }
                R.id.nav_scan -> {
                    startActivity(Intent(this, com.example.nammashaaleinventory.ui.assets.ScanAssetActivity::class.java))
                    finish()
                    false
                }
                R.id.nav_reports -> {
                    startActivity(Intent(this, com.example.nammashaaleinventory.ui.reports.ReportsActivity::class.java))
                    finish()
                    false
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, com.example.nammashaaleinventory.ui.profile.ProfileActivity::class.java))
                    finish()
                    false
                }
                else -> false
            }
        }
    }
}