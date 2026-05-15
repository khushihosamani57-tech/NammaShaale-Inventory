package com.example.nammashaaleinventory.ui.assets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.data.model.Asset
import com.google.android.material.chip.Chip

class AssetAdapter : ListAdapter<Asset, AssetAdapter.AssetViewHolder>(AssetDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_asset, parent, false)
        return AssetViewHolder(view)
    }

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AssetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvAssetName)
        private val tvCategory: TextView = itemView.findViewById(R.id.tvAssetCategory)
        private val chipCondition: Chip = itemView.findViewById(R.id.chipCondition)

        fun bind(asset: Asset) {
            tvName.text = asset.name
            tvCategory.text = asset.category
            chipCondition.text = asset.condition
            
            // Set chip color or icon based on condition if desired
            if (asset.condition.contains("Working", ignoreCase = true)) {
                chipCondition.setChipIconResource(android.R.drawable.presence_online)
            } else {
                chipCondition.setChipIconResource(android.R.drawable.presence_busy)
            }
        }
    }

    class AssetDiffCallback : DiffUtil.ItemCallback<Asset>() {
        override fun areItemsTheSame(oldItem: Asset, newItem: Asset): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Asset, newItem: Asset): Boolean {
            return oldItem == newItem
        }
    }
}
