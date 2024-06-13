package com.herdialfachri.rukaloumkm.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.herdialfachri.rukaloumkm.R
import com.herdialfachri.rukaloumkm.data.DataClass
import com.herdialfachri.rukaloumkm.data.MyAdapter

class HomeFragment : Fragment() {

    private lateinit var fab: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
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
        searchView = view.findViewById(R.id.search)
        searchView.clearFocus()

        val gridLayoutManager = GridLayoutManager(requireContext(), 1)
        recyclerView.layoutManager = gridLayoutManager

        setupRecyclerView()
        setupViewModel()

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

    private fun searchList(text: String?) {
        val searchList = mutableListOf<DataClass>()
        text?.let {
            for (dataClass in dataList) {
                if (dataClass.dataTitle?.toLowerCase()?.contains(it.toLowerCase()) == true) {
                    searchList.add(dataClass)
                }
            }
        }
        adapter.searchDataList(searchList)
    }
}
