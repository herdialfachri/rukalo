package com.herdialfachri.rukaloumkm.ui.profile

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.herdialfachri.rukaloumkm.MainActivity
import com.herdialfachri.rukaloumkm.R
import com.herdialfachri.rukaloumkm.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        val logoutButton = view.findViewById<Button>(R.id.btn_logout)
        val loadingKeluar = view.findViewById<ProgressBar>(R.id.loadingKeluar)

        viewModel.isUserLoggedIn.observe(viewLifecycleOwner, Observer { isLoggedIn ->
            if (isLoggedIn) {
                binding.btnMasuk.visibility = View.GONE
                logoutButton.visibility = View.VISIBLE
            } else {
                binding.btnMasuk.visibility = View.VISIBLE
                logoutButton.visibility = View.GONE
            }
        })

        logoutButton.setOnClickListener {
            loadingKeluar.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                viewModel.logout()
            }, 1300) // 1.3 detik
        }

        viewModel.logoutEvent.observe(viewLifecycleOwner, Observer {
            val intent = Intent(activity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        })

        return view
    }

    //        fungsi agar ketika pindah ke fragment lain bottom nav di hide
    override fun onResume() {
        super.onResume()
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNav?.visibility = View.VISIBLE
    }

    //    fungsi berpindah dari setiap button ke fragment lain
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTentangAplikasi.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_profil_to_aboutFragment)
        )
        binding.btnHubungiDesa.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_profil_to_kontakFragment)
        )
        binding.btnMasuk.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_profil_to_loginActivity)
        )
        binding.buttonUndang.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_profil_to_shareFragment)
        )
        binding.buttonTentangAplikasi.setOnClickListener {
            // Menampilkan pesan "Fitur sedang dikembangkan"
            showToast("Fitur cara pakai aplikasi sedang dikembangkan")
        }
        binding.buttonHakCipta.setOnClickListener {
            // Menampilkan pesan "Fitur sedang dikembangkan"
            showToast("Fitur hak cipta sedang dikembangkan")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
