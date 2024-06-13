package com.herdialfachri.rukaloumkm.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.github.clans.fab.FloatingActionButton
import com.github.clans.fab.FloatingActionMenu
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.herdialfachri.rukaloumkm.MainActivity
import com.herdialfachri.rukaloumkm.R

class DetailActivity : AppCompatActivity() {

    private lateinit var detailDesc: TextView
    private lateinit var detailTitle: TextView
    private lateinit var detailLang: TextView
    private lateinit var detailImage: ImageView
    private lateinit var deleteButton: FloatingActionButton
    private lateinit var editButton: FloatingActionButton
    private lateinit var mainFab: FloatingActionMenu
    private var key: String = ""
    private var imageUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detailDesc = findViewById(R.id.detailDesc)
        detailImage = findViewById(R.id.detailImage)
        detailTitle = findViewById(R.id.detailTitle)
        deleteButton = findViewById(R.id.deleteButton)
        editButton = findViewById(R.id.editButton)
        detailLang = findViewById(R.id.detailLang)
        mainFab = findViewById(R.id.mainFab)

        intent.extras?.let { bundle ->
            detailDesc.text = bundle.getString("Description")
            detailTitle.text = bundle.getString("Title")
            detailLang.text = bundle.getString("Language")
            key = bundle.getString("Key") ?: ""
            imageUrl = bundle.getString("Image") ?: ""
            Glide.with(this).load(imageUrl).into(detailImage)
        }

        // Amati status autentikasi pengguna
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            // User belum login, sembunyikan FloatingActionMenu
            mainFab.visibility = View.GONE
        } else {
            // User sudah login, tampilkan FloatingActionMenu
            mainFab.visibility = View.VISIBLE
        }

        deleteButton.setOnClickListener {
            val reference = FirebaseDatabase.getInstance().getReference("Product")
            val storage = FirebaseStorage.getInstance()

            val storageReference = storage.getReferenceFromUrl(imageUrl)
            storageReference.delete().addOnSuccessListener {
                reference.child(key).removeValue()
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
        }

        editButton.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java).apply {
                putExtra("Title", detailTitle.text.toString())
                putExtra("Description", detailDesc.text.toString())
                putExtra("Language", detailLang.text.toString())
                putExtra("Image", imageUrl)
                putExtra("Key", key)
            }
            startActivity(intent)
        }
    }
}
