package com.herdialfachri.rukaloumkm.data

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.herdialfachri.rukaloumkm.R
import com.herdialfachri.rukaloumkm.ui.home.DetailActivity

class MyAdapter(private val context: Context, private var dataList: List<DataClass>) :
    RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]

        Glide.with(context).load(currentItem.dataImage).into(holder.recImage)
        holder.recTitle.text = currentItem.dataTitle
//        holder.recDesc.text = currentItem.dataDesc
        holder.recLang.text = currentItem.dataLang

        holder.recCard.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("Image", currentItem.dataImage)
                putExtra("Description", currentItem.dataDesc)
                putExtra("Title", currentItem.dataTitle)
                putExtra("Key", currentItem.key)
                putExtra("Language", currentItem.dataLang)
                putExtra("Seller", currentItem.dataSeller)
                putExtra("Produk", currentItem.dataProduct)
                putExtra("Alamat", currentItem.dataAddress)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun searchDataList(searchList: List<DataClass>) {
        dataList = searchList
        notifyDataSetChanged()
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val recImage: ImageView = itemView.findViewById(R.id.recImage)
    val recTitle: TextView = itemView.findViewById(R.id.recTitle)
//    val recDesc: TextView = itemView.findViewById(R.id.recDesc)
    val recLang: TextView = itemView.findViewById(R.id.recPriority)
    val recCard: CardView = itemView.findViewById(R.id.recCard)
}
