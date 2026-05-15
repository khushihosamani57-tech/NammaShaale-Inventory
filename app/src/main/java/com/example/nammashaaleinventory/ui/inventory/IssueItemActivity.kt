package com.example.nammashaaleinventory.ui.inventory

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nammashaaleinventory.data.model.InventoryItem
import com.example.nammashaaleinventory.databinding.ActivityIssueItemBinding
import com.example.nammashaaleinventory.viewmodel.InventoryViewModel

class IssueItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIssueItemBinding
    private val viewModel: InventoryViewModel by viewModels()
    private var selectedItemId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIssueItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        // Simple item selection - for a real app, use a Searchable Spinner or Dialog
        // Here we'll assume the item was passed via intent if we came from Inventory List
        val itemId = intent.getStringExtra("ITEM_ID")
        val itemName = intent.getStringExtra("ITEM_NAME")
        
        if (itemId != null && itemName != null) {
            selectedItemId = itemId
            binding.tvSelectedItem.text = "Selected: $itemName"
        }

        binding.btnIssue.setOnClickListener {
            issueItem()
        }

        viewModel.operationStatus.observe(this) { success ->
            binding.progressBar.visibility = View.GONE
            binding.btnIssue.isEnabled = true
            if (success) {
                Toast.makeText(this, "Item issued successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Failed to issue item", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun issueItem() {
        val name = binding.etRecipientName.text.toString().trim()
        val qtyStr = binding.etIssueQuantity.text.toString().trim()

        if (selectedItemId == null) {
            Toast.makeText(this, "Please select an item", Toast.LENGTH_SHORT).show()
            return
        }

        if (name.isEmpty() || qtyStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val qty = qtyStr.toIntOrNull() ?: 0
        binding.progressBar.visibility = View.VISIBLE
        binding.btnIssue.isEnabled = false
        viewModel.issueItem(selectedItemId!!, name, qty)
    }
}