package com.herdialfachri.rukaloumkm.ui.home

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.herdialfachri.rukaloumkm.MainActivity
import com.herdialfachri.rukaloumkm.R
import com.herdialfachri.rukaloumkm.data.DataClass

class UpdateActivity : AppCompatActivity() {

    private lateinit var updateImage: ImageView
    private lateinit var updateButton: Button
    private lateinit var updateDesc: EditText
    private lateinit var updateTitle: EditText
    private lateinit var updateLang: EditText
    private lateinit var title: String
    private lateinit var desc: String
    private lateinit var lang: String
    private lateinit var imageUrl: String
    private lateinit var key: String
    private lateinit var oldImageURL: String
    private lateinit var uri: Uri
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference

    private val activityResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback<ActivityResult> { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    result.data?.data?.let {
                        uri = it
                        updateImage.setImageURI(uri)
                    }
                } else {
                    Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show()
                }
            }
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        updateButton = findViewById(R.id.updateButton)
        updateDesc = findViewById(R.id.updateDesc)
        updateImage = findViewById(R.id.updateImage)
        updateLang = findViewById(R.id.updateLang)
        updateTitle = findViewById(R.id.updateTitle)

        intent.extras?.let { bundle ->
            Glide.with(this).load(bundle.getString("Image")).into(updateImage)
            updateTitle.setText(bundle.getString("Title"))
            updateDesc.setText(bundle.getString("Description"))
            updateLang.setText(bundle.getString("Language"))
            key = bundle.getString("Key").orEmpty()
            oldImageURL = bundle.getString("Image").orEmpty()
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Product").child(key)

        updateImage.setOnClickListener {
            val photoPicker = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }
            activityResultLauncher.launch(photoPicker)
        }

        updateButton.setOnClickListener {
            saveData()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun saveData() {
        storageReference = FirebaseStorage.getInstance().getReference("Android Images")
            .child(uri.lastPathSegment!!)

        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(R.layout.progress_layout)
        val dialog = builder.create()
        dialog.show()

        storageReference.putFile(uri).addOnSuccessListener { taskSnapshot ->
            taskSnapshot.storage.downloadUrl.addOnCompleteListener { uriTask ->
                uriTask.result?.let {
                    imageUrl = it.toString()
                    updateData()
                    dialog.dismiss()
                }
            }
        }.addOnFailureListener {
            dialog.dismiss()
        }
    }

    private fun updateData() {
        title = updateTitle.text.toString().trim()
        desc = updateDesc.text.toString().trim()
        lang = updateLang.text.toString()

        val dataClass = DataClass(title, desc, lang, imageUrl)

        databaseReference.setValue(dataClass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL)
                reference.delete()
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
                finish()
            }
        }.addOnFailureListener { e ->
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
