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
        toolbar.setNavigationOnClickListener {
            finish() // Tutup aktivitas dan kembali ke fragment sebelumnya
        }

        // Ambil data dari Intent dan set ke Views
        val getData = intent.getParcelableExtra<FoodItem>("android")
        if (getData != null) {
            val detailTitle: TextView = findViewById(R.id.detailTitle)
            val detailImage: ImageView = findViewById(R.id.detailImage)
            val waktuMasak: TextView = findViewById(R.id.waktu_masak)
            val ratingRasa: TextView = findViewById(R.id.cita_rasa)
            val detailDesc: TextView = findViewById(R.id.detailmakanan)
            val bahanBaku: TextView = findViewById(R.id.detailbahanbaku)
            val resepMasak : TextView = findViewById(R.id.detailresep)

            detailTitle.text = getData.dataTitle
            detailImage.setImageResource(getData.dataImage)
            waktuMasak.text = getData.dataWaktu
            ratingRasa.text = getData.dataRating
            detailDesc.text = getData.dataDesc
            bahanBaku.text = getData.dataBahan
            resepMasak.text = getData.dataResep
        }
    }
}
