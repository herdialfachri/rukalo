package com.herdialfachri.rukaloumkm.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.herdialfachri.rukaloumkm.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class DashboardFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: FoodAdapter
    private lateinit var searchView: SearchView
    private val viewModel: DashboardViewModel by viewModels()
    private var searchList = ArrayList<FoodItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        searchView = view.findViewById(R.id.search)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        myAdapter = FoodAdapter(searchList)
        recyclerView.adapter = myAdapter

        viewModel.foodItems.observe(viewLifecycleOwner) { data ->
            lifecycleScope.launch {
                updateData(data)
            }
        }

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                lifecycleScope.launch {
                    filterList(newText)
                }
                return false
            }
        })

        myAdapter.onItemClick = {
            val intent = Intent(activity, DetailActivity2::class.java)
            intent.putExtra("android", it)
            startActivity(intent)
        }
    }

    private suspend fun updateData(data: List<FoodItem>) {
        withContext(Dispatchers.Default) {
            searchList.clear()
            searchList.addAll(data)
        }
        withContext(Dispatchers.Main) {
            myAdapter.notifyDataSetChanged()
        }
    }

    private suspend fun filterList(query: String?) {
        val searchText = query?.lowercase(Locale.getDefault()) ?: ""
        val filteredList = withContext(Dispatchers.Default) {
            viewModel.foodItems.value?.filter {
                it.dataTitle.lowercase(Locale.getDefault()).contains(searchText)
            } ?: emptyList()
        }
        withContext(Dispatchers.Main) {
            searchList.clear()
            searchList.addAll(filteredList)
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    override fun onPause() {
        super.onPause()
        searchView.setQuery("", false)
        searchView.clearFocus()
    }
}
