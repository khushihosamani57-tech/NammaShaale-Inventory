package com.example.nammashaaleinventory.ui.assets

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.data.model.Asset
import com.example.nammashaaleinventory.viewmodel.AssetViewModel

class AddAssetActivity : AppCompatActivity() {

    private val viewModel: AssetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_asset)

        val name = findViewById<EditText>(R.id.etName)
        val serial = findViewById<EditText>(R.id.etSerial)
        val category = findViewById<EditText>(R.id.etCategory)
        val save = findViewById<Button>(R.id.btnSave)
        val back = findViewById<ImageButton>(R.id.btnBack)

        back.setOnClickListener {
            finish()
        }

        save.setOnClickListener {
            val assetName = name.text.toString()
            val serialNumber = serial.text.toString()
            val categoryName = category.text.toString()

            if (assetName.isNotBlank() && categoryName.isNotBlank()) {
                val asset = Asset(
                    name = assetName,
                    serialNumber = serialNumber,
                    category = categoryName,
                    condition = "Working",
                    imageUri = ""
                )
                viewModel.insert(asset)
                finish()
            }
        }
    }
}