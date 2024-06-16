package com.herdialfachri.rukaloumkm.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.herdialfachri.rukaloumkm.R

class FoodAdapter(private val dataList: ArrayList<FoodItem>) :
    RecyclerView.Adapter<FoodAdapter.ViewHolderClass>() {
    var onItemClick: ((FoodItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvTitle.text = currentItem.dataTitle
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvTitle: TextView = itemView.findViewById(R.id.title)
    }
}
