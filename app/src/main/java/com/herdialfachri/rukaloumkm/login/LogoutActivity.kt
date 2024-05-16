//package com.herdialfachri.rukaloumkm.login
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.auth.FirebaseAuth
//import com.herdialfachri.rukaloumkm.MainActivity
//import com.herdialfachri.rukaloumkm.login.LoginActivity
//
//class LogoutActivity : AppCompatActivity() {
//    private lateinit var firebaseAuth: FirebaseAuth
//    private lateinit var logout: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        firebaseAuth = FirebaseAuth.getInstance()
//
//        // Panggil fungsi logout
//        logout()
//    }
//
//    private fun logout() {
//        firebaseAuth.signOut()
//        // Redirect ke halaman login atau halaman lain yang sesuai
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//        finish() // Tutup Activity saat ini agar pengguna tidak dapat kembali menggunakan tombol back
//    }
//}