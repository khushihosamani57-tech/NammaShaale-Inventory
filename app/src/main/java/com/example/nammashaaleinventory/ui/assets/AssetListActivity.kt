package com.example.nammashaaleinventory.ui.assets

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.viewmodel.AssetViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AssetListActivity : AppCompatActivity() {

    private val viewModel: AssetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asset_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val fab = findViewById<FloatingActionButton>(R.id.fabAdd)
        val btnBack = findViewById<ImageButton>(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }

        val adapter = AssetAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.allAssets.observe(this) { assets ->
            adapter.submitList(assets)
        }

        fab.setOnClickListener {
            startActivity(Intent(this, AddAssetActivity::class.java))
        }
    }
}
