package com.example.nammashaaleinventory.ui.assets

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.nammashaaleinventory.R

class AssetDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asset_details)

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }

        setupDetails()
    }

    private fun setupDetails() {
        // Set labels for the rows
        setupRow(R.id.rowCategory, "Category", "Computer")
        setupRow(R.id.rowLocation, "Location", "Computer Lab - Room 101")
        setupRow(R.id.rowPurchaseDate, "Purchase Date", "15/06/2022")
        setupRow(R.id.rowPurchasePrice, "Purchase Price", "₹45,000")
        setupRow(R.id.rowWarranty, "Warranty", "Valid till 15/06/2025")
    }

    private fun setupRow(id: Int, label: String, value: String) {
        val row = findViewById<android.view.View>(id)
        row.findViewById<TextView>(R.id.tvLabel).text = label
        row.findViewById<TextView>(R.id.tvValue).text = value
    }
}