package com.example.nammashaaleinventory.ui.reports

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nammashaaleinventory.databinding.ActivityReportsBinding
import com.example.nammashaaleinventory.viewmodel.InventoryViewModel

class ReportsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportsBinding
    private val viewModel: InventoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        viewModel.fetchItems()
        viewModel.items.observe(this) { items ->
            val totalStock = items.sumOf { it.quantity }
            val issuedCount = items.count { it.isIssued }
            
            binding.tvTotalStock.text = "Total Items in Stock: $totalStock"
            binding.tvIssuedCount.text = "Total Items Issued: $issuedCount"
        }

        binding.btnGenerateAiSummary.setOnClickListener {
            generateAiSummary()
        }
    }

    private fun generateAiSummary() {
        binding.progressBar.visibility = View.VISIBLE
        binding.tvAiSummary.text = "Analyzing inventory data..."
        
        // Mocking AI response as per requirements
        binding.root.postDelayed({
            binding.progressBar.visibility = View.GONE
            val items = viewModel.items.value ?: emptyList()
            val summary = if (items.isEmpty()) {
                "No inventory data available to analyze."
            } else {
                "Inventory Summary Report:\n\n" +
                "1. You have ${items.size} unique product categories.\n" +
                "2. Total quantity across all items is ${items.sumOf { it.quantity }}.\n" +
                "3. Stock levels are healthy, but consider restocking items in the 'Lab' category.\n" +
                "4. AI Recommendation: Standardize the 'Furniture' audit process."
            }
            binding.tvAiSummary.text = summary
        }, 2000)
    }
}