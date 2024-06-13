package com.herdialfachri.rukaloumkm.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.herdialfachri.rukaloumkm.R
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
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        searchView = view.findViewById(R.id.search)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        myAdapter = FoodAdapter(searchList)
        recyclerView.adapter = myAdapter

        viewModel.foodItems.observe(viewLifecycleOwner) { data ->
            searchList.clear()
            searchList.addAll(data)
            myAdapter.notifyDataSetChanged()
        }

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return false
            }
        })

        myAdapter.onItemClick = {
            val intent = Intent(activity, DetailActivity2::class.java)
            intent.putExtra("android", it)
            startActivity(intent)
        }

        return view
    }

    private fun filterList(query: String?) {
        val searchText = query?.lowercase(Locale.getDefault()) ?: ""
        val filteredList = viewModel.foodItems.value?.filter {
            it.dataTitle.lowercase(Locale.getDefault()).contains(searchText)
        } ?: emptyList()
        searchList.clear()
        searchList.addAll(filteredList)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        searchView.setQuery("", false)
        searchView.clearFocus()
    }
}
