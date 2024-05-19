package com.herdialfachri.rukaloumkm.ui.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.herdialfachri.rukaloumkm.R
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<FoodItem>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    lateinit var descList: Array<String>
    lateinit var detailImageList: Array<Int>
    private lateinit var myAdapter: FoodAdapter
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<FoodItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        imageList = arrayOf(
            R.drawable.seblak,
            R.drawable.nyemek,
            R.drawable.tahuwalik,
            R.drawable.roti,
            R.drawable.cookies,
            R.drawable.ciskek,
            R.drawable.saladsayur,
            R.drawable.chickenwing,
            R.drawable.dimsum,
            R.drawable.macaroni,
            R.drawable.martabakmini,
            R.drawable.milkcheese,
            R.drawable.pudingbuah,
            R.drawable.donatkentang)

        titleList = arrayOf(
            "Seblak Bandung",
            "Mie Nyemek",
            "Tahu Walik",
            "Roti Sandwich",
            "Cookies",
            "Cheese Cake",
            "Salad Buah Sayur",
            "Chicken Wings",
            "Dimsum",
            "Macaroni Schotel",
            "Martabak Mini",
            "Milk Cheese Tea",
            "Puding Buah",
            "Donat Kentang")

        descList = arrayOf(
            getString(R.string.seblakbandung),
            getString(R.string.mienyemek),
            getString(R.string.tahuwalik),
            getString(R.string.rotisandwich),
            getString(R.string.cookies),
            getString(R.string.ciskek),
            getString(R.string.saladsayur),
            getString(R.string.chickenwings),
            getString(R.string.dimsum),
            getString(R.string.makaroni),
            getString(R.string.martabakmini),
            getString(R.string.milkcheese),
            getString(R.string.pudingbuah),
            getString(R.string.donatkentang))

        detailImageList = arrayOf(
            R.drawable.seblak,
            R.drawable.nyemek,
            R.drawable.tahuwalik,
            R.drawable.roti,
            R.drawable.cookies,
            R.drawable.ciskek,
            R.drawable.saladsayur,
            R.drawable.chickenwing,
            R.drawable.dimsum,
            R.drawable.macaroni,
            R.drawable.martabakmini,
            R.drawable.milkcheese,
            R.drawable.pudingbuah,
            R.drawable.donatkentang)

        recyclerView = view.findViewById(R.id.recyclerView)
        searchView = view.findViewById(R.id.search)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf()
        searchList = arrayListOf()
        getData()

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()) {
                    dataList.forEach {
                        if (it.dataTitle.lowercase(Locale.getDefault()).contains(searchText)) {
                            searchList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    searchList.clear()
                    searchList.addAll(dataList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })

        myAdapter = FoodAdapter(searchList)
        recyclerView.adapter = myAdapter

        myAdapter.onItemClick = {
            val intent = Intent(activity, DetailActivity2::class.java)
            intent.putExtra("android", it)
            startActivity(intent)
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNav?.visibility = View.VISIBLE
    }

    private fun getData() {
        for (i in imageList.indices) {
            val dataClass = FoodItem(imageList[i], titleList[i], descList[i], detailImageList[i])
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
        recyclerView.adapter = FoodAdapter(searchList)
    }
}
