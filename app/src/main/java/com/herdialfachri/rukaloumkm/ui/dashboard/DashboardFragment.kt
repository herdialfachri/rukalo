package com.herdialfachri.rukaloumkm.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.herdialfachri.rukaloumkm.R
import com.herdialfachri.rukaloumkm.databinding.FragmentDashboardBinding
import com.herdialfachri.rukaloumkm.models.FoodItem

class DashboardFragment : Fragment(), FoodAdapter.OnItemClickListener {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val foodList = listOf(
            FoodItem("Pizza", "Delicious cheese pizza", "https://bolulembang.co.id/wp-content/uploads/2021/09/8_20200407112559_cuanki.jpg"),
            FoodItem("Burger", "Juicy beef burger", "https://fastly.4sqi.net/img/general/600x600/1546_gIh4ShSX6S1ofxe3E_6uQqYzG0OOHYkIJwgWRygi5Qc.jpg")
            // Add more items here
        )

        val adapter = FoodAdapter(foodList, this)
        binding.rvDashboard.layoutManager = LinearLayoutManager(context)
        binding.rvDashboard.adapter = adapter

        return root
    }
    override fun onStart() {
        super.onStart()
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNav?.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(foodItem: FoodItem) {
        val bundle = Bundle().apply {
            putParcelable("food_item", foodItem)
        }
        findNavController().navigate(R.id.action_navigation_resep_to_detailFoodFragment, bundle)
    }
}
