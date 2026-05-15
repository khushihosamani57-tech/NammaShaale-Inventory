package com.example.nammashaaleinventory.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.ui.assets.AssetListActivity
import com.example.nammashaaleinventory.ui.inventory.IssueLogActivity
import com.example.nammashaaleinventory.ui.inventory.RepairsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setupQuickActions()
        setupStatusCards()
        setupRecentActivity()
        setupNavigation()
    }

    private fun setupQuickActions() {
        val cardIssues = findViewById<View>(R.id.cardIssues)
        val cardRepairs = findViewById<View>(R.id.cardRepairs)
        val cardHealthCheck = findViewById<View>(R.id.cardHealthCheck)
        val cardAssets = findViewById<View>(R.id.cardAssets)

        cardIssues.setOnClickListener { startActivity(Intent(this, IssueLogActivity::class.java)) }
        cardRepairs.setOnClickListener { startActivity(Intent(this, RepairsActivity::class.java)) }
        cardHealthCheck.setOnClickListener { /* Navigate to Health Check */ }
        cardAssets.setOnClickListener { startActivity(Intent(this, AssetListActivity::class.java)) }
    }

    private fun setupStatusCards() {
        val tvCountWorking = findViewById<TextView>(R.id.tvCountWorking)
        val tvCountRepair = findViewById<TextView>(R.id.tvCountRepair)
        val tvCountBroken = findViewById<TextView>(R.id.tvCountBroken)

        tvCountWorking.text = "42"
        tvCountRepair.text = "5"
        tvCountBroken.text = "3"
    }

    private fun setupRecentActivity() {
        val container = findViewById<LinearLayout>(R.id.recentActivityContainer)
        // Static items are already in XML, or you can add more here dynamically
    }

    private fun setupNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_home
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_assets -> {
                    startActivity(Intent(this, AssetListActivity::class.java))
                    false
                }
                R.id.nav_issues -> {
                    startActivity(Intent(this, IssueLogActivity::class.java))
                    false
                }
                R.id.nav_repairs -> {
                    startActivity(Intent(this, RepairsActivity::class.java))
                    false
                }
                else -> false
            }
        }
    }
}