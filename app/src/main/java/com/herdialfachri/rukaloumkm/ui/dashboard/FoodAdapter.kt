package com.herdialfachri.rukaloumkm.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.herdialfachri.rukaloumkm.R
import com.herdialfachri.rukaloumkm.models.FoodItem

class FoodAdapter(
    private val foodList: List<FoodItem>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(foodItem: FoodItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodItem = foodList[position]
        holder.bind(foodItem)
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(foodItem)
        }
    }

    override fun getItemCount(): Int = foodList.size

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodName: TextView = itemView.findViewById(R.id.food_name)
        private val foodDescription: TextView = itemView.findViewById(R.id.food_description)
        private val foodImage: ImageView = itemView.findViewById(R.id.food_image)

        fun bind(foodItem: FoodItem) {
            foodName.text = foodItem.name
            foodDescription.text = foodItem.description
            Glide.with(itemView.context)
                .load(foodItem.imageUrl)
                .into(foodImage)
        }
    }
}
