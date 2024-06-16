package com.herdialfachri.rukaloumkm.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.herdialfachri.rukaloumkm.R

class DashboardViewModel : ViewModel() {

    private val _foodItems = MutableLiveData<List<FoodItem>>().apply {
        value = listOf(
            FoodItem(
                R.drawable.seblak, "Seblak Bandung", "Seblak adalah makanan khas Bandung, Indonesia, yang terkenal dengan cita rasa pedas dan gurih.",
                "30 Menit", "9.5 dari 10",
                "1 centong krupuk oren atau bahan lain sesuai selera.\n2 buah sosis ukuran sesuai selera.\n1 butir telur ayam atau sesuai selera.\n2 buah ceker ayam atau sesuai selera.\n500 mili air matang atau mentah.\n11 biji cabe merah kriting.\n4 biji cabe rawit setan atau sesuai selera.\n1 siung bawang putih segar.\n1 ruas kencur segar.\n1 sdt garam dan 1/2 sdt gula pasir.\n1/2 sdt kaldu jamur.\n2 sendok atau secukupnya minyak goreng.",
                "1. Siapkan semua bahan bahan yang diperlukan.\n2. Ulek atau blender halus bahan bumbu.\n3. Tuang minyak dan tumis bumbu sampai harum.\n4. Tambahkan air lalu masukkan ceker ayam.\n5. Setelah kuah mendidih masukkan telur.\n6. Tunggu telur agak matang lalu masukan sosis.\n7. Setelah sosis dan telur matang masukkan krupuk.\n8. Tambahkan garam, kaldu, gula pasir, koreksi rasa.\n9. Seblak pedas gurih siap dinikmati."
            ),
            FoodItem(
                R.drawable.nyemek, "Mie Nyemek", "Mie nyemek adalah hidangan khas Indonesia yang merupakan perpaduan antara mie goreng dan mie kuah, menghasilkan mie yang sedikit berkuah atau 'nyemek'.",
                "30 Menit", "9 dari 10",
                "2 bungkus indomie soto lainnya.\n1 butir telor ayam.\n2 buah bakso atau sesuai selera.\n5 batang sawi atau sesuai selera.\n7 siung bawang merah segar.\n5 siung bawang putih segar.\n2 buah cabai rawit atau sesuai selera.\n3 buah cabai besar merah (optional).\n2 buah tomat segar.\n5 sachet saos ABC atau merk lainnya.\n2 sendok makan kecap atau sesuai selera.\n1 sdt garam dan penyedap (untuk koreksi rasa).",
                "1. Siapkan bahan yang sudah disebutkan.\n2. Iris semua bumbu, sawi putih dan bakso.\n3. Tumis bawang merah iris terlebih dahulu.\n4. Setelah menguning masukan cabai, tumis layu.\n5. Setelah itu masukan tomat yang sudah diiris.\n6. Setelah tomat layu masukan air (perkiraan saja).\n7. Tunggu mendidih, tambahkan sawi, bakso dan mie.\n8. Masak mie dan tunggu sampai setengah matang.\n9. Tambahkan bumbu mie dan aduk rata.\n10. Koreksi rasa, bisa disesuaikan dengan selera.\n11. Tambahkan telur, tunggu sampai telur matang.\n12. Angkat dan hidangkan mie nyemek."
            ),
            FoodItem(
                R.drawable.tahuwalik, "Tahu Walik", "Tahu walik adalah makanan khas Banyuwangi, Indonesia, yang terbuat dari tahu yang dibalik dan diisi dengan adonan daging atau tepung.",
                "60 Menit", "8 dari 10",
                "10 buah tahu coklat.\n150 gr Tepung tapioka.\n75 gr Tepung terigu (tapioka 2:1 terigu).\n6 siung bawang putih.\n1 sdt Garam.\n1 sdm Royco (boleh kurang atau lebih).\n1/2 sdm Ladaku merica bubuk.\n2 batang Daun bawang (opsional).\nAir bersih secukupnya.",
                "1. Potong tahu coklat menjadi 2 bagian, menjadi bentuk segitiga.\n2. Balik tahu coklat, keluarkan isi tahu kemudian sisihkan.\n3. Uleg bawang putih hingga halus.\n4. Masukkan bahan kering (tepung tapioka dan terigu) ke dalam wadah lalu campur.\n5. Setelah itu masukkan ulegan bawang putih, garam, lada, royco, isian tahu dan daun bawang ke dalam wadah tepung tadi.\n6. Campur semua bahan sebelum dimasukkan air.\n7. Setelah itu masukkan air secara perlahan sedikit demi sedikit agar tidak terlalu encer.\n8. Pastikan tekstur adonan tidak terlalu encer agar bisa dimasukkan ke dalam tahu (saran: tekstur adonan seperti cilok).\n9. Pastikan rasa adonan sudah pas tidak terlalu asin.\n10. Masukkan adonan ke dalam tahu satu per satu, jangan terlalu banyak agar bisa matang sempurna saat digoreng.\n11. Setelah selesai, kemudian panaskan minyak di minyak panas api kecil ke sedang agar tidak gosong. Minyak harus banyak sampai tahu terendam minyak.\n12. Goreng tahu hingga kecoklatan dan krispi."
            ),
            FoodItem(
                R.drawable.roti, "Roti Sandwich", "Roti sandwich adalah makanan yang terdiri dari dua potong roti dengan berbagai isian di tengahnya.",
                "15 Menit", "8.5 dari 10",
                "2 lembar roti tawar.\n1 lembar smoked beef.\n1 butir telur ayam.\n1 slice keju.\n1 lembar daun selada.\n1 iris tomat atau sesuai selera.\n1 iris timun atau sesuai selera.\n1 sdm Saus ABC atau mayonaise.\n1 sdm Margarin.",
                "1. Siapkan bahan, cuci dan potong selada, tomat dan timun.\n2. Panggang smoked beef sebentar saja dengan margarin, bolak balik, angkat. Di wajan yg sama masukkan telur, pecahkan kuningnya atau dibiarkan utuh tidak apa2. Panggang roti hingga hangat.\n3. Tata 1 lembar roti di piring saji, lapisi dengan smoked beef, kemudian telur, lalu keju. Tambahkan toping sayuran.\n4. Terakhir, tuang saus ABC atau mayonaise. Tutup dengan lembar roti lainnya. Sandwich siap disajikan.\n5. Sandwich bisa dipanaskan dan dipadatkan lagi, sesuai selera."
            ),
            FoodItem(
                R.drawable.cookies, "Cookies", "Cookies adalah kue kering kecil yang umumnya memiliki tekstur renyah di luar dan lembut di dalam.",
                "80 Menit", "9 dari 10",
                "340 gr tepung terigu protein sedang.\n170 gr mentega.\n100 gr gula pasir.\n90 gr gula palem.\n1 butir telur (60gr).\n1/2 sdt baking soda.\n1/2 sdt baking powder.\n1/2 sdt garam.\n1/2 sdt pasta vanilla.\n100 gr chocochips.\n50 gr dark chocolate (cincang).",
                "1. Kocok mentega, vanilla, gula pasir, dan gula palem hingga tercampur dan gula larut.\n2. Masukkan telur, dan garam, kocok rata.\n3. Masukkan tepung, chocochips, baking soda, baking powder, chocolate.\n4. Aduk hingga rata. Kemudian timbang adonan sesuai selera (sy di 80 gr). Tata dlm loyang.\n5. Panggang dlm oven dgn suhu 180°C kurang lebih selama 15 - 20 menit. Angkat, siap dihidangkan."
            ),
            FoodItem(
                R.drawable.ciskek, "Cheese Cake", "Cheesecake adalah kue penutup yang terkenal dengan teksturnya yang lembut dan rasa keju yang kaya rasa.",
                "90 Menit", "10 dari 10",
                "3 Putih telur (suhu ruang).\n70 gr Gula pasir.\n1/4 gr Cream of tar tar.\n3 Kuning telur.\n135 gr Cream cheese.\n30 gr Butter.\n50 ml Susu cair (UHT).\n30 gr Tepung terigu (kunci biru).\n10 gr Tepung maizena.\n1 sdt atau sejumput garam.",
                "1. Cairkan cream cheese, butter dan susu cair. Boleh pakai metode double boiler. boleh di microwave dan ketika texture masih bergerigil langsung kocok pakai whisk.\n2. Masukkan kuning telur dan kocok kembali dengan whisk hingga rata.\n3. Kemudian masukkan tepung terigu, maizena dan garam. Jangan lupa di saring, alu di kocok kembali pakai whisk tetapi jangan berlebihan yang penting sudah tercampur rata.\n4, Siapkan mixer, ixer putih telur telur kecepatan sedang, tunggu sampai berbusa, masukkan gula pasir secara bertahap dan cream of tar tar. Jika berhasil bentuknya akan mengembang seperti. Lalu mixer hingga kaku.\n5. Kalau sudah secara bertahap (sekitar 3x tahap) tuang adonan putih telur ke adonan yg tadi di whisk. Aduk dengan spatula. Dengan menggunakan metode putar balik. Dan jgn di tekan-tekan adonan nya.\n6. Panaskan oven suhu atas bawah 150 derajat. Lalu siapkan loyang di alasi baking paper. Dan tuang adonan jgn sampai penuh.\n7. Panggang dengan metode au bine (loyang cheesecake taruh di loyang besar yg berisi air). Panggang 30 menit dgn suhu 150 derajat. Setelah 30 menit turunkan suhu 140 derajat. Panggang selama 30 menit.\n8. Setelah 30 menit lagi turunkan suhu 130 derajat. Panggang 30 menit. Setelah total 90 menit. Matikan oven. Angin- anginkan cheesecake dalam oven selama 15 menit.\n9. Setelah diangin-anginkan keluarkan dan biarkan dingin. Keluarkan dari loyang. Tunggu suhu ruang. Simpan dalam kulkas 1-2 jam. Baru di potong-potong."
            ),
            FoodItem(
                R.drawable.saladsayur, "Risoles", "Salad sayur adalah hidangan segar yang terdiri dari campuran berbagai jenis sayuran segar yang biasanya disajikan dalam bentuk potongan atau irisan.",
                "45 Menit", "8 dari 10",
                "10 lbr kulit risoles.\n250 gr ayam giling.\n200 gr tepung roti.\n3 buah wortel.\n4 siung bawang putih.\n1 sdt merica bubuk.\n1/2 sdt kaldu ayam bubuk.\n5 sdm minyak untuk menumis.\n1 batang daun bawang.\n2 btr telur (utk pencelup).\nGaram secukupnya.",
                "1. Tumis bawang putih hingga harum, masukkan ayam giling, merica, kaldu bubuk, dan garam. Aduk sampai rata.\n2. Masukkan wortel, masak sampai matang, masukkan daun bawang. Koreksi rasa, angkat.\n3. Ambil selembar kulit risoles, isi dgn ayam wortel yg sdh ditumis.\n4. Gulung risoles dgn cara melipat bagian kiri kanan kulit kemudian gulung, celupkan dlm kocokan telur lalu gulingkan ke tepung roti.\n5. Panaskan minyak dalam wajan dgn api sedang, goreng risoles sampai kuning kecoklatan."
            )
        )
    }
    val foodItems: LiveData<List<FoodItem>> = _foodItems
}
