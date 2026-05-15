package com.example.nammashaaleinventory.ui.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nammashaaleinventory.R

class OnboardingAdapter(
    private val list: List<OnboardingItem>
) : RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val emoji: TextView = view.findViewById(R.id.txtEmoji)
        val title: TextView = view.findViewById(R.id.txtTitle)
        val desc: TextView = view.findViewById(R.id.txtDesc)
        val bg: LinearLayout = view.findViewById(R.id.bgLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_onboarding, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.emoji.text = item.emoji
        holder.title.text = item.title
        holder.desc.text = item.desc
        holder.bg.setBackgroundColor(item.bgColor)
    }
}