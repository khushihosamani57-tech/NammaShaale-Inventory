package com.example.nammashaaleinventory.ui.inventory

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nammashaaleinventory.databinding.ActivityReturnItemBinding
import com.example.nammashaaleinventory.viewmodel.InventoryViewModel

class ReturnItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReturnItemBinding
    private val viewModel: InventoryViewModel by viewModels()
    private var selectedItemId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReturnItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        val itemId = intent.getStringExtra("ITEM_ID")
        val itemName = intent.getStringExtra("ITEM_NAME")
        
        if (itemId != null && itemName != null) {
            selectedItemId = itemId
            binding.tvSelectedItemReturn.text = "Returning: $itemName"
        }

        binding.btnReturn.setOnClickListener {
            returnItem()
        }

        viewModel.operationStatus.observe(this) { success ->
            binding.progressBar.visibility = View.GONE
            binding.btnReturn.isEnabled = true
            if (success) {
                Toast.makeText(this, "Item returned successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Failed to return item", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun returnItem() {
        val qtyStr = binding.etReturnQuantity.text.toString().trim()

        if (selectedItemId == null) {
            Toast.makeText(this, "Please select an item", Toast.LENGTH_SHORT).show()
            return
        }

        if (qtyStr.isEmpty()) {
            Toast.makeText(this, "Please enter quantity", Toast.LENGTH_SHORT).show()
            return
        }

        val qty = qtyStr.toIntOrNull() ?: 0
        binding.progressBar.visibility = View.VISIBLE
        binding.btnReturn.isEnabled = false
        viewModel.returnItem(selectedItemId!!, qty)
    }
}