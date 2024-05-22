package com.herdialfachri.rukaloumkm.ui.profile.undang

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.herdialfachri.rukaloumkm.R

class ShareFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_share, container, false)

        // Mendapatkan referensi ke tombol "Share"
        val btnShare: Button = view.findViewById(R.id.btn_share)

        // Menambahkan OnClickListener ke tombol "Share"
        btnShare.setOnClickListener(this)

        return view
    }

    override fun onStart() {
        super.onStart()
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNav?.visibility = View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mendapatkan referensi ke toolbar
        val toolbar: Toolbar = view.findViewById(R.id.toolbar_share)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_share -> {
                // Membuat Intent untuk berbagi teks
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.type = "text/plain"
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Helo Foodies! ayo bantu aku mempromosikan UMKM desa Palasari Girang dengan mengunduh dan membagikan aplikasi ini! https://www.youtube.com/"
                )

                // Memulai aktivitas untuk berbagi
                startActivity(Intent.createChooser(intent, "Bagikan melalui"))
            }
        }
    }
}
