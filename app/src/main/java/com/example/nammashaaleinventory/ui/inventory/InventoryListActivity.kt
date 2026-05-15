package com.example.nammashaaleinventory.ui.inventory

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nammashaaleinventory.data.model.InventoryItem
import com.example.nammashaaleinventory.databinding.ActivityInventoryListBinding
import com.example.nammashaaleinventory.viewmodel.InventoryViewModel

class InventoryListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInventoryListBinding
    private val viewModel: InventoryViewModel by viewModels()
    private lateinit var adapter: InventoryAdapter
    private var allItems: List<InventoryItem> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInventoryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        setupRecyclerView()
        setupSearch()

        viewModel.fetchItems()
        viewModel.items.observe(this) { items ->
            binding.progressBar.visibility = View.GONE
            allItems = items
            adapter.submitList(items)
        }
    }

    private fun setupRecyclerView() {
        adapter = InventoryAdapter { item ->
            // Handle item click if needed (e.g., show details)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filter(query: String) {
        val filteredList = if (query.isEmpty()) {
            allItems
        } else {
            allItems.filter { 
                it.name.contains(query, ignoreCase = true) || 
                it.category.contains(query, ignoreCase = true) 
            }
        }
        adapter.submitList(filteredList)
    }
}