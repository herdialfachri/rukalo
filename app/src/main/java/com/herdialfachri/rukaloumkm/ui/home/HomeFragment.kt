package com.herdialfachri.rukaloumkm.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.herdialfachri.rukaloumkm.R
import com.herdialfachri.rukaloumkm.data.DataClass
import com.herdialfachri.rukaloumkm.data.MyAdapter

class HomeFragment : Fragment() {

    private lateinit var fab: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private val viewModel: HomeViewModel by viewModels()
    private var dataList = mutableListOf<DataClass>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        fab = view.findViewById(R.id.fab)

        val gridLayoutManager = GridLayoutManager(requireContext(), 1)
        recyclerView.layoutManager = gridLayoutManager

        setupRecyclerView()
        setupViewModel()

        // Amati status autentikasi pengguna
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // User sudah login, tampilkan FAB
            fab.visibility = View.VISIBLE
        } else {
            // User belum login, sembunyikan FAB
            fab.visibility = View.GONE
        }

        // Set listener untuk perubahan status autentikasi
        auth.addAuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                // User sudah login, tampilkan FAB
                fab.visibility = View.VISIBLE
            } else {
                // User belum login, sembunyikan FAB
                fab.visibility = View.GONE
            }
        }

        fab.setOnClickListener {
            // Tindakan ketika FAB diklik
            val intent = Intent(requireContext(), UploadActivity::class.java)
            startActivity(intent)
        }

        // Setup SearchView dan listener lainnya
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshData() // Memastikan data diperbarui saat fragment kembali aktif
    }

    private fun setupRecyclerView() {
        adapter = MyAdapter(requireContext(), dataList)
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel.products.observe(viewLifecycleOwner) { products ->
            dataList.clear()
            dataList.addAll(products)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Pastikan untuk menghapus listener saat Fragment dihancurkan
        FirebaseAuth.getInstance().removeAuthStateListener { /* listener */ }
    }
}
