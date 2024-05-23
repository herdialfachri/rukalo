package com.herdialfachri.rukaloumkm.ui.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.herdialfachri.rukaloumkm.R
import java.util.Locale

class DashboardFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<FoodItem>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    lateinit var timeList: Array<String>
    lateinit var ratingList: Array<String>
    lateinit var descList: Array<String>
    lateinit var bahanList: Array<String>
    lateinit var resepList: Array<String>
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
            R.drawable.donatkentang
        )

        timeList = arrayOf(
            "10 Menit",
            "20 Menit",
            "60 Menit",
            "15 Menit",
            "80 Menit",
            "90 Menit",
            "20 Menit",
            "60 Menit",
            "40 Menit",
            "60 Menit",
            "30 Menit",
            "10 Menit",
            "40 Menit",
            "45 Menit"
        )

        ratingList = arrayOf(
            "9.5 dari 10",
            "9 dari 10",
            "8 dari 10",
            "8.5 dari 10",
            "9 dari 10",
            "10 dari 10",
            "8 dari 10",
            "9.5 dari 10",
            "8 dari 10",
            "8.5 dari 10",
            "7.5 dari 10",
            "8.5 dari 10",
            "7 dari 10",
            "8.5 dari 10"
        )

        bahanList = arrayOf(
            "1 centong krupuk oren atau bahan lain sesuai selera. \n" +
                    "2 buah sosis ukuran sesuai selera. \n" +
                    "1 butir telur ayam atau sesuai selera. \n" +
                    "2 buah ceker ayam atau sesuai selera. \n" +
                    "500 mili air matang atau mentah. \n" +
                    "11 biji cabe merah kriting. \n" +
                    "4 biji cabe rawit setan atau sesuai selera. \n" +
                    "1 siung bawang putih segar. \n" +
                    "1 ruas kencur segar. \n" +
                    "1 sdt garam dan 1/2 sdt gula pasir. \n" +
                    "1/2 sdt kaldu jamur. \n" +
                    "2 sendok atau secukupnya minyak goreng.",

            "2 bungkus indomie soto lainnya. \n" +
                    "1 butir telor ayam. \n" +
                    "2 buah bakso atau sesuai selera. \n" +
                    "5 batang sawi atau sesuai selera. \n" +
                    "7 siung bawang merah segar. \n" +
                    "5 siung bawang putih segar. \n" +
                    "2 buah cabai rawit atau sesuai selera. \n" +
                    "3 buah cabai besar merah (optional). \n" +
                    "2 buah tomat segar. \n" +
                    "5 sachet saos ABC atau merk lainnya. \n" +
                    "2 sendok makan kecap atau sesuai selera. \n" +
                    "1 sdt garam dan penyedap (untuk koreksi rasa).",

            "10 buah tahu coklat. \n" +
                    "150 gr Tepung tapioka. \n" +
                    "75 gr Tepung terigu (tapioka 2:1 terigu). \n" +
                    "6 siung bawang putih. \n" +
                    "1 sdt Garam. \n" +
                    "1 sdm Royco (boleh kurang atau lebih). \n" +
                    "1/2 sdm Ladaku merica bubuk. \n" +
                    "2 batang Daun bawang (opsional). \n" +
                    "Air bersih secukupnya.",

            "2 lembar roti tawar. \n" +
                    "1 lembar smoked beef. \n" +
                    "1 butir telur ayam. \n" +
                    "1 slice keju. \n" +
                    "1 lembar daun selada. \n" +
                    "1 iris tomat atau sesuai selera. \n" +
                    "1 iris timun atau sesuai selera. \n" +
                    "1 sdm Saus ABC atau mayonaise. \n" +
                    "1 sdm Margarin.",

            "1. 340 gr tepung terigu protein sedang. \n" +
                    "170 gr mentega. \n" +
                    "100 gr gula pasir. \n" +
                    "90 gr gula palem. \n" +
                    "1 butir telur (60gr). \n" +
                    "1/2 sdt baking soda. \n" +
                    "1/2 sdt baking powder. \n" +
                    "1/2 sdt garam. \n" +
                    "1/2 sdt pasta vanilla. \n" +
                    "100 gr chocochips. \n" +
                    "50 gr dark chocolate (cincang).",

            "3 Putih telur (suhu ruang). \n" +
                    "70 gr Gula pasir. \n" +
                    "1/4 gr Cream of tar tar. \n" +
                    "3 Kuning telur. \n" +
                    "135 gr Cream cheese. \n" +
                    "30 gr Butter. \n" +
                    "50 ml Susu cair (UHT). \n" +
                    "30 gr Tepung terigu (kunci biru). \n" +
                    "10 gr Tepung maizena. \n" +
                    "1 sdt atau sejumput garam.",

            "20 Menit",
            "60 Menit",
            "40 Menit",
            "60 Menit",
            "30 Menit",
            "10 Menit",
            "40 Menit",
            "45 Menit"
        )

        resepList = arrayOf(
            "1. Siapkan semua bahan bahan yang diperlukan. \n" +
                    "2. Ulek atau blender halus bahan bumbu. \n" +
                    "3. Tuang minyak dan tumis bumbu sampai harum. \n" +
                    "4. Tambahkan air lalu masukkan ceker ayam. \n" +
                    "5. Setelah kuah mendidih masukkan telur. \n" +
                    "6. Tunggu telur agak matang lalu masukan sosis. \n" +
                    "7. Setelah sosis dan telur matang masukkan krupuk. \n" +
                    "8. Tambahkan garam, kaldu, gula pasir, koreksi rasa. \n" +
                    "9. Seblak pedas gurih siap dinikmati.",

            "1. Siapkan bahan yang sudah disebutkan. \n" +
                    "2. Iris semua bumbu, sawi putih dan bakso. \n" +
                    "3. Tumis bawang merah iris terlebih dahulu. \n" +
                    "4. Setelah menguning masukan cabai, tumis layu. \n" +
                    "5. Setelah itu masukan tomat yang sudah diiris. \n" +
                    "6. Setelah tomat layu masukan air (perkiraan saja). \n" +
                    "7. Tunggu mendidih, tambahkan sawi, bakso dan mie. \n" +
                    "8. Masak mie dan tunggu sampai setengah matang. \n" +
                    "9. Tambahkan bumbu mie dan aduk rata. \n" +
                    "10. Koreksi rasa, bisa disesuaikan dengan selera. \n" +
                    "11. Tambahkan telur, tunggu sampai telur matang. \n" +
                    "12. Angkat dan hidangkan mie nyemek.",

            "1. Potong tahu coklat menjadi 2 bagian, menjadi bentuk segitiga. \n" +
                    "2. Balik tahu coklat, keluarkan isi tahu kemudian sisihkan. \n" +
                    "3. Uleg bawang putih hingga halus. \n" +
                    "4. Masukkan bahan kering (tepung tapioka dan terigu) ke dalam wadah lalu campur. \n" +
                    "5. Setelah itu masukkan ulegan bawang putih, garam, lada, royco, isian tahu dan" +
                    " daun bawang ke dalam wadah tepung tadi. \n" +
                    "6. Campur semua bahan sebelum dimasukkan air. \n" +
                    "7. Setelah itu masukkan air secara perlahan sedikit demi sedikit agar tidak terlalu encer. \n" +
                    "8. Pastikan tekstur adonan tidak terlalu encer agar bisa dimasukkan ke dalam tahu " +
                    "(saran: tekstur adonan seperti cilok). \n" +
                    "9. Pastikan rasa adonan sudah pas tidak terlalu asin. \n" +
                    "10. Masukkan adonan ke dalam tahu satu per satu, jangan terlalu banyak agar bisa matang sempurna saat digoreng. \n" +
                    "11. Setelah selesai, kemudian panaskan minyak di minyak panas api kecil ke sedang agar tidak gosong. " +
                    "Minyak harus banyak sampai tahu terendam minyak. \n" +
                    "12. Goreng tahu hingga kecoklatan dan krispi.",

            "1. Siapkan bahan, cuci dan potong selada, tomat dan timun. \n" +
                    "2. Panggang smoked beef sebentar saja dengan margarin, bolak balik, angkat. Di wajan yg sama " +
                    "masukkan telur, pecahkan kuningnya atau dibiarkan utuh tidak apa2. Panggang roti hingga hangat. \n" +
                    "3. Tata 1 lembar roti di piring saji, lapisi dengan smoked beef, kemudian telur, lalu keju. Tambahkan toping sayuran. \n" +
                    "4. Terakhir, tuang saus ABC atau mayonaise. Tutup dengan lembar roti lainnya. Sandwich siap disajikan. \n" +
                    "5. Sandwich bisa dipanaskan dan dipadatkan lagi, sesuai selera.",

            "1. Kocok mentega, vanilla, gula pasir, dan gula palem hingga tercampur dan gula larut. \n" +
                    "2. Masukkan telur, dan garam, kocok rata. \n" +
                    "3. Masukkan tepung, chocochips, baking soda, baking powder, chocolate. \n" +
                    "4. Aduk hingga rata. Kemudian timbang adonan sesuai selera (sy di 80 gr). Tata dlm loyang. \n" +
                    "5. Panggang dlm oven dgn suhu 180°C kurang lebih selama 15 - 20 menit. Angkat, siap dihidangkan.",

            "1. Cairkan cream cheese, butter dan susu cair. Boleh pakai metode double boiler. boleh di microwave" +
                    " dan ketika texture masih bergerigil langsung kocok pakai whisk. \n" +
                    "2. Masukkan kuning telur dan kocok kembali dengan whisk hingga rata. \n" +
                    "3. Kemudian masukkan tepung terigu, maizena dan garam. Jangan lupa di saring, alu di kocok" +
                    " kembali pakai whisk tetapi jangan berlebihan yang penting sudah tercampur rata. \n" +
                    "4, Siapkan mixer, ixer putih telur telur kecepatan sedang, tunggu sampai " +
                    "berbusa, masukkan gula pasir secara bertahap dan cream of tar tar. Jika berhasil bentuknya " +
                    "akan mengembang seperti. Lalu mixer hingga kaku. \n" +
                    "5. Kalau sudah secara bertahap (sekitar 3x tahap) tuang adonan putih telur ke adonan " +
                    "yg tadi di whisk. Aduk dengan spatula. Dengan menggunakan metode putar balik. Dan jgn di tekan-tekan adonan nya. \n" +
                    "6. Panaskan oven suhu atas bawah 150 derajat. Lalu siapkan loyang di alasi baking paper. Dan tuang adonan jgn sampai penuh. \n" +
                    "7. Panggang dengan metode au bain marie, yaitu tatakan loyang dgn loyang lagi yg di beri air agar adonan tidak gosong. Panggang sekitar 1 jam. \n" +
                    "8. Setelah 1 jam jangan langsung di buka tetapi diamkan di dalam oven selama 5 menit. \n" +
                    "9. Bisa langsung sajikan selagi hangat atau di masukkan ke kulkas, dimakan pas dingin juga enak.",

            "8 dari 10",
            "9.5 dari 10",
            "8 dari 10",
            "8.5 dari 10",
            "7.5 dari 10",
            "8.5 dari 10",
            "7 dari 10",
            "8.5 dari 10"
        )

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
            "Donat Kentang"
        )

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
            getString(R.string.donatkentang)
        )

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
            R.drawable.donatkentang
        )

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

    override fun onPause() {
        super.onPause()
        searchView.setQuery("", false)
        searchView.clearFocus()
    }

    private fun getData() {
        for (i in imageList.indices) {
            val dataClass = FoodItem(imageList[i], titleList[i], descList[i], timeList[i], ratingList[i],
                bahanList[i], resepList[i])
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
        recyclerView.adapter = FoodAdapter(searchList)
    }
}
