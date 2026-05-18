package com.example.nammashaaleinventory.ui.assets

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.data.model.Asset
import com.example.nammashaaleinventory.databinding.ActivityAssetListBinding
import com.example.nammashaaleinventory.ui.dashboard.DashboardActivity
import com.example.nammashaaleinventory.ui.profile.ProfileActivity
import com.example.nammashaaleinventory.ui.reports.ReportsActivity
import com.example.nammashaaleinventory.viewmodel.AssetViewModel

class AssetListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAssetListBinding
    private val viewModel: AssetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssetListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        val adapter = AssetAdapter()
        adapter.setOnItemClickListener { asset ->
            val intent = Intent(this, AssetDetailsActivity::class.java)
            // Typically pass asset ID here
            startActivity(intent)
        }
        
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.allAssets.observe(this) { assets ->
            if (assets.isEmpty()) {
                adapter.submitList(getDummyAssets())
            } else {
                adapter.submitList(assets)
            }
        }

        setupNavigation()
    }

    private fun getDummyAssets(): List<Asset> {
        return listOf(
            Asset(1, "Microscope - Bio Lab", "INV-00124", "Lab Equipment", "Working", "img_microscope"),
            Asset(2, "Football - Sports Room", "INV-00123", "Sports", "Working", "img_football"),
            Asset(3, "Wooden Table - Library", "INV-00122", "Furniture", "Needs Repair", "img_table"),
            Asset(4, "Chair - Staff Room", "INV-00121", "Furniture", "Out of Order", ""),
            Asset(5, "Whiteboard - Class 7C", "INV-00120", "Furniture", "Working", ""),
            Asset(6, "Printer - Office", "INV-00119", "Electronics", "Needs Repair", ""),
            Asset(7, "Bookshelf - Library", "INV-00118", "Furniture", "Working", ""),
            Asset(8, "Microscope - Science Lab", "INV-00117", "Lab Equipment", "Working", "img_microscope"),
            Asset(9, "Tablet - Room 102", "INV-00116", "Electronics", "Working", ""),
            Asset(10, "Fan - Class 6A", "INV-00115", "Electrical", "Needs Repair", "")
        )
    }

    private fun setupNavigation() {
        binding.bottomNav.selectedItemId = R.id.nav_assets
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, DashboardActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_assets -> true
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
}
