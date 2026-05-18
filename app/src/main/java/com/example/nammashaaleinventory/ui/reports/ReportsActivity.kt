package com.example.nammashaaleinventory.ui.reports

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.data.model.Asset
import com.example.nammashaaleinventory.ui.assets.AssetListActivity
import com.example.nammashaaleinventory.ui.assets.ScanAssetActivity
import com.example.nammashaaleinventory.ui.dashboard.DashboardActivity
import com.example.nammashaaleinventory.ui.profile.ProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ReportsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reports)

        findViewById<ImageView>(R.id.btnMenu).setOnClickListener {
            finish()
        }
        
        setupAssetGallery()
        setupNavigation()
    }

    private fun setupAssetGallery() {
        val rvGallery = findViewById<RecyclerView>(R.id.rvAssetGallery)
        rvGallery.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        
        val dummyAssets = listOf(
            Asset(1, "Projector", "SN001", "Electronics", "Needs Repair", "ic_assets"),
            Asset(2, "Dell Laptop", "SN002", "Electronics", "Working", "ic_assets"),
            Asset(3, "Office Chair", "SN003", "Furniture", "Working", "ic_assets"),
            Asset(4, "Printer", "SN004", "Electronics", "Out of Order", "ic_assets"),
            Asset(5, "Whiteboard", "SN005", "Furniture", "Working", "ic_assets")
        )
        
        rvGallery.adapter = AssetImageAdapter(dummyAssets)
    }
    
    private fun setupNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_reports
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
                R.id.nav_scan -> {
                    startActivity(Intent(this, ScanAssetActivity::class.java))
                    finish()
                    false
                }
                R.id.nav_reports -> true
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