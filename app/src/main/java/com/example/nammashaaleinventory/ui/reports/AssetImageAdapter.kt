package com.example.nammashaaleinventory.ui.reports

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.data.model.Asset

class AssetImageAdapter(private var items: List<Asset>) : RecyclerView.Adapter<AssetImageAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivAssetImage: ImageView = view.findViewById(R.id.ivAssetImage)
        val tvAssetName: TextView = view.findViewById(R.id.tvAssetName)
        val tvAssetStatus: TextView = view.findViewById(R.id.tvAssetStatus)
    }

    fun updateData(newItems: List<Asset>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_asset_image_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvAssetName.text = item.name
        holder.tvAssetStatus.text = item.condition
        
        // Display asset image if provided, otherwise show default icon
        if (item.imageUri.isNotEmpty()) {
            val context = holder.itemView.context
            val resourceId = context.resources.getIdentifier(item.imageUri, "drawable", context.packageName)
            if (resourceId != 0) {
                holder.ivAssetImage.setImageResource(resourceId)
            } else {
                holder.ivAssetImage.setImageResource(R.drawable.ic_assets)
            }
        } else {
            holder.ivAssetImage.setImageResource(R.drawable.ic_assets)
        }
    }

    override fun getItemCount() = items.size
}