package com.example.nammashaaleinventory.ui.assets

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.data.model.Asset
import com.example.nammashaaleinventory.viewmodel.AssetViewModel
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class AddAssetActivity : AppCompatActivity() {

    private val viewModel: AssetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_asset)

        val etName = findViewById<TextInputEditText>(R.id.etName)
        val spinnerCategory = findViewById<AutoCompleteTextView>(R.id.spinnerCategory)
        val spinnerLocation = findViewById<AutoCompleteTextView>(R.id.spinnerLocation)
        val etPurchaseDate = findViewById<TextInputEditText>(R.id.etPurchaseDate)
        val spinnerCondition = findViewById<AutoCompleteTextView>(R.id.spinnerCondition)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        // Setup Spinners
        val categories = arrayOf("Computer", "Furniture", "Electronics", "Lab Equipment", "Sports", "Others")
        spinnerCategory.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categories))

        val locations = arrayOf("Computer Lab 1", "Computer Lab 2", "Science Lab", "Library", "Staff Room", "Class 8A", "Class 8B")
        spinnerLocation.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, locations))

        val conditions = arrayOf("Working", "Needs Repair", "Out of Order")
        spinnerCondition.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, conditions))

        btnBack.setOnClickListener {
            finish()
        }

        etPurchaseDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(this, { _, year, month, dayOfMonth ->
                etPurchaseDate.setText("$dayOfMonth/${month + 1}/$year")
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val category = spinnerCategory.text.toString()
            val location = spinnerLocation.text.toString()
            val date = etPurchaseDate.text.toString()
            val condition = spinnerCondition.text.toString()

            if (name.isEmpty() || category.isEmpty() || location.isEmpty() || date.isEmpty() || condition.isEmpty()) {
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val asset = Asset(
                name = name,
                serialNumber = "SN-" + System.currentTimeMillis().toString().takeLast(6), // Dummy serial
                category = category,
                condition = condition,
                imageUri = "" // Placeholder
            )
            
            viewModel.insert(asset)
            Toast.makeText(this, "Asset saved successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}