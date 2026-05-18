package com.example.nammashaaleinventory.ui.assets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.data.model.Asset
import com.example.nammashaaleinventory.databinding.ItemAssetBinding

class AssetAdapter : ListAdapter<Asset, AssetAdapter.AssetViewHolder>(AssetDiffCallback()) {

    private var onItemClickListener: ((Asset) -> Unit)? = null

    fun setOnItemClickListener(listener: (Asset) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val binding = ItemAssetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AssetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        val asset = getItem(position)
        holder.bind(asset)
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(asset)
        }
    }

    class AssetViewHolder(private val binding: ItemAssetBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(asset: Asset) {
            binding.tvAssetName.text = asset.name
            binding.tvAssetDetails.text = "INV-${asset.serialNumber.ifEmpty { asset.id.toString() }}"
            binding.tvStatus.text = asset.condition
            
            // Set color based on condition
            val colorRes = when {
                asset.condition.contains("Working", ignoreCase = true) -> R.color.working_green
                asset.condition.contains("Repair", ignoreCase = true) -> R.color.repair_orange
                else -> R.color.broken_red
            }
            binding.tvStatus.setTextColor(binding.root.context.getColor(colorRes))
            
            // Display asset image if provided, otherwise show default icon
            if (asset.imageUri.isNotEmpty()) {
                val context = binding.root.context
                val resourceId = context.resources.getIdentifier(asset.imageUri, "drawable", context.packageName)
                if (resourceId != 0) {
                    binding.ivAssetIcon.setImageResource(resourceId)
                    binding.ivAssetIcon.setPadding(0, 0, 0, 0) // Remove padding for full images
                } else {
                    binding.ivAssetIcon.setImageResource(R.drawable.ic_assets)
                    binding.ivAssetIcon.setPadding(10, 10, 10, 10)
                }
            } else {
                binding.ivAssetIcon.setImageResource(R.drawable.ic_assets)
                binding.ivAssetIcon.setPadding(10, 10, 10, 10)
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
