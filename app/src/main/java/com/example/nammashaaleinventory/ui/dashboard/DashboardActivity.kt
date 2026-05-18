package com.example.nammashaaleinventory.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.data.model.Asset
import com.example.nammashaaleinventory.databinding.ActivityDashboardBinding
import com.example.nammashaaleinventory.ui.assets.AddAssetActivity
import com.example.nammashaaleinventory.ui.assets.AssetAdapter
import com.example.nammashaaleinventory.ui.assets.AssetDetailsActivity
import com.example.nammashaaleinventory.ui.assets.AssetListActivity
import com.example.nammashaaleinventory.ui.assets.ScanAssetActivity
import com.example.nammashaaleinventory.ui.notifications.NotificationsActivity
import com.example.nammashaaleinventory.ui.profile.ProfileActivity
import com.example.nammashaaleinventory.ui.reports.ReportsActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSummary()
        setupQuickActions()
        setupNavigation()
        setupRecentAssets()
        
        binding.btnNotifications.setOnClickListener {
            startActivity(Intent(this, NotificationsActivity::class.java))
        }

        binding.tvViewAll.setOnClickListener {
            startActivity(Intent(this, AssetListActivity::class.java))
        }
    }

    private fun setupSummary() {
        binding.tvTotalAssets.text = "245"
        binding.tvCountWorking.text = "198"
        binding.tvCountRepair.text = "32"
        binding.tvCountBroken.text = "15"
    }

    private fun setupQuickActions() {
        binding.cardAddAsset.setOnClickListener {
            startActivity(Intent(this, AddAssetActivity::class.java))
        }
        binding.cardScanAsset.setOnClickListener {
            startActivity(Intent(this, ScanAssetActivity::class.java))
        }
        binding.cardAssets.setOnClickListener {
            startActivity(Intent(this, AssetListActivity::class.java))
        }
        binding.cardReports.setOnClickListener {
            startActivity(Intent(this, ReportsActivity::class.java))
        }
    }

    private fun setupNavigation() {
        binding.bottomNav.selectedItemId = R.id.nav_home
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_assets -> {
                    startActivity(Intent(this, AssetListActivity::class.java))
                    false
                }
                R.id.nav_scan -> {
                    startActivity(Intent(this, ScanAssetActivity::class.java))
                    false
                }
                R.id.nav_reports -> {
                    startActivity(Intent(this, ReportsActivity::class.java))
                    false
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    false
                }
                else -> false
            }
        }
    }

    private fun setupRecentAssets() {
        val adapter = AssetAdapter()
        adapter.setOnItemClickListener {
            startActivity(Intent(this, AssetDetailsActivity::class.java))
        }
        binding.rvRecentAssets.adapter = adapter
        binding.rvRecentAssets.layoutManager = LinearLayoutManager(this)
        
        adapter.submitList(getDummyRecentAssets())
    }

    private fun getDummyRecentAssets(): List<Asset> {
        return listOf(
            Asset(1, "Microscope - Bio Lab", "INV-00124", "Lab Equipment", "Working", "img_microscope"),
            Asset(2, "Football - Sports Room", "INV-00123", "Sports", "Working", "img_football"),
            Asset(3, "Wooden Table - Library", "INV-00122", "Furniture", "Needs Repair", "img_table")
        )
    }
}
