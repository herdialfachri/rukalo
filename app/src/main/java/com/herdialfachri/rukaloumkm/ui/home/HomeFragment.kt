package com.herdialfachri.rukaloumkm.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.herdialfachri.rukaloumkm.R
import com.herdialfachri.rukaloumkm.data.DataClass
import com.herdialfachri.rukaloumkm.data.MyAdapter

class HomeFragment : Fragment() {

    private lateinit var fab: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private lateinit var searchView: SearchView
    private val viewModel: HomeViewModel by viewModels()
    private var dataList = mutableListOf<DataClass>()
    private var originalList = mutableListOf<DataClass>()

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
        searchView = view.findViewById(R.id.search)

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = gridLayoutManager

        setupRecyclerView()
        setupViewModel()

        // Monitor user authentication status
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        updateFabVisibility(currentUser)

        auth.addAuthStateListener { firebaseAuth ->
            updateFabVisibility(firebaseAuth.currentUser)
        }

        fab.setOnClickListener {
            val intent = Intent(requireContext(), UploadActivity::class.java)
            startActivity(intent)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList(newText)
                return true
            }
        })
    }

    private fun updateFabVisibility(user: FirebaseUser?) {
        fab.visibility = if (user != null) View.VISIBLE else View.GONE
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshData() // Ensure data is updated when fragment becomes active again
    }

    private fun setupRecyclerView() {
        adapter = MyAdapter(requireContext(), dataList)
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel.products.observe(viewLifecycleOwner) { products ->
            dataList.clear()
            dataList.addAll(products)
            originalList.clear()
            originalList.addAll(products)
            adapter.notifyDataSetChanged()
        }
    }

    private fun searchList(text: String?) {
        val searchList = mutableListOf<DataClass>()
        text?.let {
            for (dataClass in originalList) {
                if (dataClass.dataTitle?.toLowerCase()?.contains(it.toLowerCase()) == true) {
                    searchList.add(dataClass)
                }
            }
        }
        dataList.clear()
        dataList.addAll(searchList)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Ensure listener is removed when Fragment is destroyed
        FirebaseAuth.getInstance().removeAuthStateListener { /* listener */ }
    }
}
