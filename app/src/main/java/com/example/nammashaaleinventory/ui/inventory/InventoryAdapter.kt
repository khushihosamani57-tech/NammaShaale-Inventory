package com.example.nammashaaleinventory.ui.inventory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nammashaaleinventory.data.model.InventoryItem
import com.example.nammashaaleinventory.databinding.ItemInventoryBinding

class InventoryAdapter(private val onItemClick: (InventoryItem) -> Unit) :
    ListAdapter<InventoryItem, InventoryAdapter.InventoryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        val binding = ItemInventoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return InventoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class InventoryViewHolder(private val binding: ItemInventoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: InventoryItem) {
            binding.tvItemName.text = item.name
            binding.tvCategory.text = "Category: ${item.category}"
            binding.tvQuantity.text = "Qty: ${item.quantity}"
            
            if (item.quantity > 0) {
                binding.tvStatus.text = "Available"
                binding.tvStatus.setTextColor(android.graphics.Color.GREEN)
            } else {
                binding.tvStatus.text = "Out of Stock"
                binding.tvStatus.setTextColor(android.graphics.Color.RED)
            }

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<InventoryItem>() {
        override fun areItemsTheSame(oldItem: InventoryItem, newItem: InventoryItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: InventoryItem, newItem: InventoryItem): Boolean {
            return oldItem == newItem
        }
    }
}