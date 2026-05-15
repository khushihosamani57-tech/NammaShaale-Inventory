package com.example.nammashaaleinventory.ui.inventory

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nammashaaleinventory.data.model.InventoryItem
import com.example.nammashaaleinventory.databinding.ActivityAddItemBinding
import com.example.nammashaaleinventory.viewmodel.InventoryViewModel

class AddItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddItemBinding
    private val viewModel: InventoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            saveItem()
        }

        viewModel.operationStatus.observe(this) { success ->
            binding.progressBar.visibility = View.GONE
            binding.btnSave.isEnabled = true
            if (success) {
                Toast.makeText(this, "Item saved successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Failed to save item", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveItem() {
        val name = binding.etItemName.text.toString().trim()
        val category = binding.spinnerCategory.selectedItem.toString()
        val quantityStr = binding.etQuantity.text.toString().trim()
        val description = binding.etDescription.text.toString().trim()

        if (name.isEmpty() || quantityStr.isEmpty()) {
            Toast.makeText(this, "Please fill required fields", Toast.LENGTH_SHORT).show()
            return
        }

        val quantity = quantityStr.toIntOrNull() ?: 0
        val item = InventoryItem(
            name = name,
            category = category,
            quantity = quantity,
            description = description
        )

        binding.progressBar.visibility = View.VISIBLE
        binding.btnSave.isEnabled = false
        viewModel.addItem(item)
    }
}