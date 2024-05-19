package com.herdialfachri.rukaloumkm.login

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.herdialfachri.rukaloumkm.MainActivity
import com.herdialfachri.rukaloumkm.R
import com.herdialfachri.rukaloumkm.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var loadingProgressBar: ProgressBar
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadingProgressBar = findViewById(R.id.loadingProgressBar)
        val passwordEditText = binding.loginPassword
        passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()

        firebaseAuth = FirebaseAuth.getInstance()
        checkUserLoggedIn()
        setupLoginButton()

        binding.forgotPassword.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.dialog_forgot, null)
            val userEmail = view.findViewById<EditText>(R.id.editBox)
            builder.setView(view)
            val dialog = builder.create()
            view.findViewById<Button>(R.id.btnReset).setOnClickListener {
                if (userEmail.text.toString().isEmpty()) {
                    Toast.makeText(this, "Silakan masukan email anda", Toast.LENGTH_SHORT).show()
                } else {
                    compareEmail(userEmail, dialog)
                }
            }
            view.findViewById<Button>(R.id.btnCancel).setOnClickListener {
                dialog.dismiss()
            }
            if (dialog.window != null) {
                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            }
            dialog.show()
        }

        binding.signupRedirectText.setOnClickListener {
            val phoneWhatsapp = "6282122506110"
            val dialWhatsappIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://api.whatsapp.com/send?phone=$phoneWhatsapp&text=Assalamualaikum,saya mau mendaftar sebagai penjual UMKM%0ANIK:%0ANAMA:%0ANama%20Usaha:")
            )
            startActivity(dialWhatsappIntent)
        }
    }

    private fun checkUserLoggedIn() {
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            finish()
        }
    }

    private fun compareEmail(email: EditText, dialog: AlertDialog) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            Toast.makeText(this, "Masukan email yang valid", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.sendPasswordResetEmail(email.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Reset kata sandi berhasil, cek email anda",
                        Toast.LENGTH_SHORT
                    ).show()
                    dialog.dismiss()
                } else {
                    Toast.makeText(
                        this,
                        "Gagal mengirim email reset, coba lagi",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun setupLoginButton() {
        binding.loginButton.setOnClickListener {
            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (isInternetAvailable()) {
                    loadingProgressBar.visibility = View.VISIBLE
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            loadingProgressBar.visibility = View.GONE
                            if (task.isSuccessful) {
                                val currentUserEmail = firebaseAuth.currentUser?.email
                                Toast.makeText(
                                    this,
                                    "Anda masuk sebagai $currentUserEmail",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent = Intent(this, MainActivity::class.java).apply {
                                    flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                }
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this,
                                    "Maaf email atau password yang anda masukan salah",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Kolom tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
