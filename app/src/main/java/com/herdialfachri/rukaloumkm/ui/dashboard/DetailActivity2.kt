package com.herdialfachri.rukaloumkm.ui.dashboard

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.herdialfachri.rukaloumkm.R

@Suppress("DEPRECATION")
class DetailActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail2)

        // Temukan Toolbar dan atur Navigation Click Listener
        val toolbar: Toolbar = findViewById(R.id.toolbar_detailfood)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish() // Tutup aktivitas dan kembali ke fragment sebelumnya
        }

        // Ambil data dari Intent dan set ke Views
        val getData = intent.getParcelableExtra<FoodItem>("android")
        if (getData != null) {
            val detailTitle: TextView = findViewById(R.id.detailTitle)
            val detailDesc: TextView = findViewById(R.id.detailDesc)
            val detailImage: ImageView = findViewById(R.id.detailImage)

            detailTitle.text = getData.dataTitle
            detailDesc.text = getData.dataDesc
            detailImage.setImageResource(getData.dataDetailImage)
        }
    }
}
