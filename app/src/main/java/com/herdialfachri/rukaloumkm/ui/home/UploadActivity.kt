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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.herdialfachri.rukaloumkm.R
import com.herdialfachri.rukaloumkm.data.DataClass
import java.text.DateFormat
import java.util.*

class UploadActivity : AppCompatActivity() {

    private lateinit var uploadImage: ImageView
    private lateinit var uploadTopic: EditText
    private lateinit var uploadDesc: EditText
    private lateinit var uploadLang: EditText
    private lateinit var uploadSeller: EditText
    private lateinit var uploadProduct: EditText
    private lateinit var uploadAlamat: EditText
    private lateinit var saveButton: Button
    private var imageURL: String? = null
    private var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        uploadImage = findViewById(R.id.uploadImage)
        uploadTopic = findViewById(R.id.uploadTopic)
        uploadDesc = findViewById(R.id.uploadDesc)
        uploadLang = findViewById(R.id.uploadLang)
        uploadSeller = findViewById(R.id.uploadSeller)
        uploadProduct = findViewById(R.id.uploadProductDesc)
        uploadAlamat = findViewById(R.id.uploadAlamat)
        saveButton = findViewById(R.id.saveButton)

        val activityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                uri = data?.data
                uploadImage.setImageURI(uri)
            } else {
                Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show()
            }
        }

        uploadImage.setOnClickListener {
            val photoPicker = Intent(Intent.ACTION_PICK)
            photoPicker.type = "image/*"
            activityResultLauncher.launch(photoPicker)
        }

        saveButton.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val storageReference: StorageReference = FirebaseStorage.getInstance().reference
            .child("Android Images")
            .child(uri?.lastPathSegment ?: return)

        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(R.layout.progress_layout)
        val dialog = builder.create()
        dialog.show()

        uri?.let {
            storageReference.putFile(it).addOnSuccessListener { taskSnapshot ->
                taskSnapshot.storage.downloadUrl.addOnCompleteListener { uriTask ->
                    if (uriTask.isSuccessful) {
                        uriTask.result?.let { url ->
                            imageURL = url.toString()
                            uploadData()
                        }
                        dialog.dismiss()
                    }
                }
            }.addOnFailureListener {
                dialog.dismiss()
            }
        }
    }

    private fun sanitizePath(path: String): String {
        return path.replace(".", "")
            .replace("#", "")
            .replace("$", "")
            .replace("[", "")
            .replace("]", "")
            .replace("<", "")
            .replace(">", "")
            .replace("?", "")
    }

    private fun uploadData() {
        val title = uploadTopic.text.toString()
        val desc = uploadDesc.text.toString()
        val lang = uploadLang.text.toString()
        val seller = uploadSeller.text.toString()
        val product = uploadProduct.text.toString()
        val address = uploadAlamat.text.toString()

        val dataClass = DataClass(title, desc, lang, imageURL, seller, product, address)

        val currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().time)
        val sanitizedDate = sanitizePath(currentDate)

        FirebaseDatabase.getInstance().getReference("Product").child(sanitizedDate)
            .setValue(dataClass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.addOnFailureListener { e ->
                Toast.makeText(this, e.message.toString(), Toast.LENGTH_SHORT).show()
            }
    }
}
