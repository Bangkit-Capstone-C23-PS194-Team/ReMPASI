package com.caps.rempasi.data.remote.retrofit

import com.caps.rempasi.data.remote.response.RecipeItem
import com.caps.rempasi.data.remote.response.RecommendationResponse

object DummyRecipeData {
    fun getRecipes() =
        RecommendationResponse(
            data = listOf(
                RecipeItem(
                    id = 0,
                    name = "Nasi Goreng",
                    image = "https://res.cloudinary.com/dk0z4ums3/image/upload/v1638252657/attached_image/cara-menghangatkan-mpasi-agar-kualitasnya-tetap-terjaga-0-alodokter.jpg",
                    ingredients = listOf(
                        "Nasi",
                        "Bawang merah",
                        "Bawang putih",
                        "Kecap manis",
                        "Kecap asin",
                        "Garam",
                        "Merica",
                        "Telur",
                        "Daging ayam atau udang",
                        "Minyak goreng"
                    ),
                    steps = listOf(
                        "Panaskan minyak goreng",
                        "Tumis bawang merah dan bawang putih hingga harum",
                        "Tambahkan daging ayam atau udang, aduk rata",
                        "Masukkan nasi, kecap manis, kecap asin, garam, dan merica, aduk hingga tercampur rata",
                        "Pindahkan nasi goreng ke piring saji",
                        "Goreng telur setengah matang dan letakkan di atas nasi goreng"
                    )
                ),
                RecipeItem(
                    id = 1,
                    name = "Ayam Bakar",
                    image = "https://res.cloudinary.com/dk0z4ums3/image/upload/v1638252657/attached_image/cara-menghangatkan-mpasi-agar-kualitasnya-tetap-terjaga-0-alodokter.jpg",
                    ingredients = listOf(
                        "Ayam",
                        "Bawang merah",
                        "Bawang putih",
                        "Kecap manis",
                        "Kecap asin",
                        "Garam",
                        "Merica",
                        "Air jeruk nipis",
                        "Minyak goreng"
                    ),
                    steps = listOf(
                        "Haluskan bawang merah dan bawang putih",
                        "Campurkan bawang merah, bawang putih, kecap manis, kecap asin, garam, merica, dan air jeruk nipis, aduk rata",
                        "Marinasi ayam dengan bumbu yang sudah dicampur selama beberapa jam",
                        "Panaskan minyak goreng",
                        "Bakar ayam di atas panggangan atau teflon hingga matang dan berwarna kecokelatan",
                        "Angkat dan sajikan"
                    )
                ),
                RecipeItem(
                    id = 2,
                    name = "Sate Ayam",
                    image = "",
                    ingredients = listOf(
                        "Daging ayam",
                        "Bawang merah",
                        "Bawang putih",
                        "Kecap manis",
                        "Kecap asin",
                        "Serai",
                        "Garam",
                        "Gula",
                        "Minyak goreng"
                    ),
                    steps = listOf(
                        "Potong daging ayam menjadi dadu kecil",
                        "Haluskan bawang merah dan bawang putih",
                        "Campurkan bawang merah, bawang putih, kecap manis, kecap asin, serai, garam, dan gula, aduk rata",
                        "Marinasi daging ayam dengan bumbu selama beberapa jam",
                        "Tusukkan daging ayam yang sudah dimarinasi ke tusuk sate",
                        "Panaskan minyak goreng, kemudian panggang sate ayam hingga matang dan berwarna kecokelatan",
                        "Angkat dan sajikan dengan bumbu kacang"
                    )
                ),
                RecipeItem(
                    id = 3,
                    name = "Mie Goreng",
                    image = "https://img.my-best.id/product_images/bdb83fa3fa2f114623dca445fa9918ff.jpg?ixlib=rails-4.3.1&q=70&lossless=0&w=800&h=800&fit=clip&s=74ff4022aecfd88dfb31284714d133de",
                    ingredients = listOf(
                        "Mie telur",
                        "Bawang merah",
                        "Bawang putih",
                        "Cabe rawit",
                        "Wortel",
                        "Kol",
                        "Kecap manis",
                        "Kecap asin",
                        "Garam",
                        "Merica",
                        "Minyak goreng"
                    ),
                    steps = listOf(
                        "Rebus mie telur hingga matang, tiriskan, dan sisihkan",
                        "Iris tipis bawang merah, bawang putih, cabe rawit, wortel, dan kol",
                        "Panaskan minyak goreng, tumis bawang merah dan bawang putih hingga harum",
                        "Tambahkan cabe rawit, wortel, dan kol, aduk rata hingga layu",
                        "Masukkan mie telur, kecap manis, kecap asin, garam, dan merica, aduk hingga tercampur rata",
                        "Angkat dan sajikan selagi hangat"
                    )
                ),
                RecipeItem(
                    id = 4,
                    name = "Soto Ayam",
                    image = "https://i.pinimg.com/originals/5b/c5/7b/5bc57bf59d86b78868219fdcabd53411.jpg",
                    ingredients = listOf(
                        "Daging ayam",
                        "Bawang merah",
                        "Bawang putih",
                        "Serai",
                        "Jahe",
                        "Daun jeruk",
                        "Daun salam",
                        "Garam",
                        "Merica",
                        "Minyak goreng",
                        "Lontong",
                        "Telur rebus",
                        "Kecap manis",
                        "Sambal",
                        "Bawang goreng"
                    ),
                    steps = listOf(
                        "Rebus daging ayam dalam air hingga empuk, angkat, dan suwir-suwir",
                        "Haluskan bawang merah, bawang putih, serai, jahe, daun jeruk, daun salam, garam, dan merica",
                        "Panaskan minyak goreng, tumis bumbu halus hingga harum",
                        "Masukkan suwiran daging ayam, aduk rata",
                        "Tambahkan air kaldu ayam, masak hingga mendidih",
                        "Siapkan mangkuk, isi dengan lontong, telur rebus, suwiran ayam, kecap manis, sambal, dan bawang goreng",
                        "Tuangkan kuah soto di atasnya, sajikan selagi hangat"
                    )
                ),
                RecipeItem(
                    id = 5,
                    name = "Rendang Daging",
                    image = "",
                    ingredients = listOf(
                        "Daging sapi",
                        "Bawang merah",
                        "Bawang putih",
                        "Cabe merah",
                        "Serai",
                        "Jahe",
                        "Kelapa parut",
                        "Santan",
                        "Gula merah",
                        "Garam",
                        "Minyak goreng"
                    ),
                    steps = listOf(
                        "Potong daging sapi menjadi potongan kecil",
                        "Haluskan bawang merah, bawang putih, cabe merah, serai, dan jahe",
                        "Panaskan minyak goreng, tumis bumbu halus hingga harum",
                        "Tambahkan potongan daging sapi, aduk rata hingga daging berubah warna",
                        "Masukkan kelapa parut dan santan, aduk perlahan hingga meresap",
                        "Tambahkan gula merah dan garam, aduk rata",
                        "Masak rendang dengan api kecil hingga daging empuk dan bumbu meresap",
                        "Sajikan rendang daging dengan nasi hangat"
                    )
                ),
                RecipeItem(
                    id = 6,
                    name = "Nasi Uduk",
                    image = "",
                    ingredients = listOf(
                        "Beras",
                        "Santan",
                        "Serai",
                        "Daun pandan",
                        "Daun salam",
                        "Jahe",
                        "Bawang merah",
                        "Bawang putih",
                        "Garam"
                    ),
                    steps = listOf(
                        "Cuci beras hingga bersih, tiriskan",
                        "Panaskan santan, serai, daun pandan, daun salam, jahe, bawang merah, dan bawang putih dalam panci",
                        "Masukkan beras ke dalam panci, aduk rata",
                        "Tambahkan garam secukupnya, aduk kembali",
                        "Masak nasi dengan api kecil hingga matang dan beras menyerap santan",
                        "Angkat dan sajikan nasi uduk hangat"
                    )
                ),
                RecipeItem(
                    id = 7,
                    name = "Gado-gado",
                    image = "",
                    ingredients = listOf(
                        "Sayuran (kubis, tauge, kacang panjang, wortel)",
                        "Tempe",
                        "Tahu",
                        "Telur rebus",
                        "Ketupat",
                        "Bawang putih",
                        "Kacang tanah",
                        "Kecap manis",
                        "Air asam jawa",
                        "Garam",
                        "Gula merah",
                        "Minyak goreng"
                    ),
                    steps = listOf(
                        "Rebus sayuran, tempe, tahu, dan telur hingga matang, tiriskan",
                        "Potong-potong sayuran, tempe, dan tahu",
                        "Haluskan bawang putih dan kacang tanah",
                        "Panaskan minyak goreng, tumis bawang putih hingga harum",
                        "Tambahkan kacang tanah halus, kecap manis, air asam jawa, garam, dan gula merah, aduk rata",
                        "Masukkan sayuran, tempe, dan tahu, aduk hingga terbalut rata dengan bumbu",
                        "Sajikan gado-gado dengan ketupat dan telur rebus, siram dengan bumbu kacang"
                    )
                ),
                RecipeItem(
                    id = 8,
                    name = "Soto Betawi",
                    image = "",
                    ingredients = listOf(
                        "Daging sapi",
                        "Bawang merah",
                        "Bawang putih",
                        "Serai",
                        "Jahe",
                        "Kunyit",
                        "Lengkuas",
                        "Daun salam",
                        "Daun jeruk",
                        "Santan",
                        "Garam",
                        "Merica",
                        "Minyak goreng",
                        "Air"
                    ),
                    steps = listOf(
                        "Rebus daging sapi dalam air hingga empuk, angkat, dan suwir-suwir",
                        "Haluskan bawang merah, bawang putih, serai, jahe, kunyit, dan lengkuas",
                        "Panaskan minyak goreng, tumis bumbu halus hingga harum",
                        "Masukkan daging suwir, aduk rata",
                        "Tambahkan air, daun salam, dan daun jeruk, masak hingga mendidih",
                        "Tambahkan santan, garam, dan merica, masak hingga kuah terasa kental",
                        "Sajikan soto Betawi dengan nasi, telur rebus, kentang rebus, dan daun bawang"
                    )
                ),
                RecipeItem(
                    id = 9,
                    name = "Pecel Lele",
                    image = "",
                    ingredients = listOf(
                        "Lele",
                        "Terasi",
                        "Bawang merah",
                        "Bawang putih",
                        "Cabe rawit",
                        "Gula merah",
                        "Garam",
                        "Air asam jawa",
                        "Air jeruk nipis",
                        "Kecap manis",
                        "Minyak goreng"
                    ),
                    steps = listOf(
                        "Cuci bersih lele",
                        "Haluskan terasi, bawang merah, bawang putih, dan cabe rawit",
                        "Panaskan minyak goreng, goreng lele hingga matang dan berwarna kecokelatan",
                        "Haluskan gula merah, garam, air asam jawa, air jeruk nipis, dan kecap manis, aduk rata",
                        "Tumis bumbu halus hingga harum, tambahkan air secukupnya, masak hingga matang",
                        "Sajikan lele dengan nasi hangat dan pecel (bumbu)",
                        "Tambahkan lalapan seperti timun, tomat, dan daun kemangi jika diinginkan"
                    )
                ),
                RecipeItem(
                    id = 10,
                    name = "Pisang Goreng",
                    image = "https://assets.ayobandung.com/crop/0x0:0x0/750x500/webp/photo/2023/02/17/525195314.jpg",
                    ingredients = listOf(
                        "Pisang",
                        "Tepung terigu",
                        "Gula",
                        "Air",
                        "Minyak goreng"
                    ),
                    steps = listOf(
                        "Kupas pisang dan potong-potong sesuai selera",
                        "Campurkan tepung terigu, gula, dan air, aduk rata hingga membentuk adonan kental",
                        "Panaskan minyak goreng dalam wajan",
                        "Celupkan potongan pisang ke dalam adonan tepung",
                        "Goreng pisang dalam minyak panas hingga kecokelatan dan matang",
                        "Angkat dan tiriskan pisang goreng",
                        "Goreng pisang dalam minyak panas hingga kecokelatan dan matang",
                        "Angkat dan tiriskan pisang goreng",
                        "Sajikan pisang goreng hangat sebagai camilan atau hidangan penutup. Anda juga bisa menambahkan topping seperti gula bubuk atau madu sesuai selera."
                    )
                ),
                RecipeItem(
                    id = 11,
                    name = "Ayam Goreng",
                    image = "",
                    ingredients = listOf(
                        "10 Potong ayam",
                        "Bawang putih",
                        "Kunyit",
                        "Kemiri",
                        "Ketumbar",
                        "Garam",
                        "Merica",
                        "Minyak goreng"
                    ),
                    steps = listOf(
                        "Haluskan bawang putih, kunyit, kemiri, ketumbar, garam, dan merica",
                        "Marinasi potong ayam dengan bumbu halus selama beberapa waktu",
                        "Panaskan minyak goreng dalam wajan",
                        "Goreng ayam dalam minyak panas hingga kecokelatan dan matang",
                        "Angkat dan tiriskan ayam goreng",
                        "Sajikan ayam goreng hangat sebagai hidangan utama bersama nasi dan sambal."
                    )
                ),
                RecipeItem(
                    id = 12,
                    name = "Mie Ayam",
                    image = "",
                    ingredients = listOf(
                        "Mie telur",
                        "Daging ayam",
                        "Bawang putih",
                        "Kecap manis",
                        "Kecap asin",
                        "Garam",
                        "Merica",
                        "Minyak goreng",
                        "Sawi",
                        "Daun bawang",
                        "Kucai",
                        "Bawang goreng"
                    ),
                    steps = listOf(
                        "Rebus mie telur hingga matang, tiriskan",
                        "Potong daging ayam menjadi dadu kecil",
                        "Haluskan bawang putih, kecap manis, kecap asin, garam, dan merica",
                        "Marinasi potongan ayam dengan bumbu halus selama beberapa waktu",
                        "Panaskan minyak goreng dalam wajan",
                        "Goreng daging ayam hingga matang dan berwarna kecokelatan",
                        "Rebus sawi hingga matang, tiriskan",
                        "Siapkan mangkuk, masukkan mie telur, daging ayam, sawi, daun bawang, dan kucai",
                        "Tambahkan kecap manis, kecap asin, garam, merica, dan bawang goreng sesuai selera",
                        "Aduk rata, sajikan mie ayam hangat"
                    )
                ),
                RecipeItem(
                    id = 13,
                    name = "Sayur Asem",
                    image = "",
                    ingredients = listOf(
                        "Labu siam",
                        "Kacang panjang",
                        "Jagung manis",
                        "Kemangi",
                        "Daun melinjo",
                        "Asam jawa",
                        "Gula merah",
                        "Garam",
                        "Bawang merah",
                        "Bawang putih",
                        "Cabe rawit",
                        "Air"
                    ),
                    steps = listOf(
                        "Potong labu siam, kacang panjang, dan jagung manis menjadi potongan",
                        "Rebus air dalam panci",
                        "Masukkan labu siam, kacang panjang, dan jagung manis ke dalam panci berisi air",
                        "Tambahkan asam jawa, gula merah, garam, bawang merah, bawang putih, dan cabe rawit",
                        "Rebus sayuran hingga matang dan bumbu tercampur dengan baik",
                        "Tambahkan kemangi dan daun melinjo, masak sebentar hingga layu",
                        "Angkat dan sajikan sayur asem hangat sebagai hidangan utama bersama nasi putih",
                        "Nikmati sayur asem dengan citarasa asam segar dan aroma rempah yang lezat!"
                    )
                ),
                RecipeItem(
                    id = 14,
                    name = "Pepes Ikan",
                    image = "",
                    ingredients = listOf(
                        "Ikan (gurame, nila, atau lele)",
                        "Daun pisang",
                        "Bumbu pepes (bawang merah, bawang putih, cabe merah, terasi, garam, gula, daun jeruk)",
                        "Daun kemangi",
                        "Air jeruk nipis"
                    ),
                    steps = listOf(
                        "Bersihkan ikan dan lumuri dengan air jeruk nipis",
                        "Haluskan bumbu pepes (bawang merah, bawang putih, cabe merah, terasi, garam, gula, daun jeruk)",
                        "Oleskan bumbu pepes ke ikan yang telah dibersihkan",
                        "Bungkus ikan dengan daun pisang dan ikat dengan tali atau lidi",
                        "Kukus pepes ikan dalam panci atau kukusan selama kurang lebih 30-40 menit",
                        "Angkat pepes ikan dan sajikan dengan daun kemangi"
                    )
                ),
                RecipeItem(
                    id = 15,
                    name = "Sop Buntut",
                    image = "",
                    ingredients = listOf(
                        "Buntut sapi",
                        "Bawang merah",
                        "Bawang putih",
                        "Wortel",
                        "Kentang",
                        "Daun bawang",
                        "Daun seledri",
                        "Kaldu sapi",
                        "Merica",
                        "Garam",
                        "Minyak goreng"
                    ),
                    steps = listOf(
                        "Panaskan minyak goreng dalam panci",
                        "Tumis bawang merah dan bawang putih hingga harum",
                        "Tambahkan buntut sapi, aduk rata dan masak hingga buntut berubah warna",
                        "Masukkan air dan kaldu sapi, masak hingga mendidih",
                        "Tambahkan wortel dan kentang ke dalam panci, masak hingga sayuran lunak dan buntut sapi empuk",
                        "Beri garam dan merica secukupnya sesuai selera",
                        "Tambahkan daun bawang dan daun seledri, masak sebentar hingga layu",
                        "Angkat sop buntut dan sajikan hangat",
                        "Nikmati sop buntut dengan nasi putih atau roti sebagai hidangan utama yang lezat dan menghangatkan."
                    )
                ),
                RecipeItem(
                    id = 16,
                    name = "Pempek Palembang",
                    image = "",
                    ingredients = listOf(
                        "Tepung sagu",
                        "Ikan tengiri",
                        "Bawang putih",
                        "Garam",
                        "Air es",
                        "Air",
                        "Cuka",
                        "Gula",
                        "Kecap manis",
                        "Daun seledri",
                        "Telur"
                    ),
                    steps = listOf(
                        "Haluskan ikan tengiri dan bawang putih",
                        "Campurkan tepung sagu, ikan halus, bawang putih, garam, dan air es, uleni hingga tercampur rata",
                        "Diamkan adonan selama beberapa waktu",
                        "Bentuk adonan menjadi bulatan atau lonjong",
                        "Rebus air dalam panci hingga mendidih",
                        "Masukkan pempek ke dalam air mendidih dan masak hingga pempek mengapung",
                        "Angkat pempek dan tiriskan",
                        "Sajikan pempek dengan cuka, gula, kecap manis, dan irisan daun seledri",
                        "Bisa juga ditambahkan irisan telur rebus jika diinginkan"
                    )
                ),
                RecipeItem(
                    id = 17,
                    name = "Es Teler",
                    image = "",
                    ingredients = listOf(
                        "Kelapa muda",
                        "Nangka",
                        "Buah alpukat",
                        "Kelapa parut",
                        "Sirup cocopandan",
                        "Es batu",
                        "Santan kental",
                        "Gula",
                        "Susu kental manis"
                    ),
                    steps = listOf(
                        "Potong kelapa muda, nangka, dan buah alpukat menjadi potongan kecil",
                        "Siapkan mangkuk atau gelas saji",
                        "Letakkan kelapa parut di dasar mangkuk",
                        "Tambahkan potongan kelapa muda, nangka, dan alpukat",
                        "Tuangkan sirup cocopandan secukupnya",
                        "Tambahkan es batu hingga mangkuk terisi",
                        "Siram dengan santan kental, gula, dan susu kental manis sesuai selera",
                        "Es teler siap disajikan, aduk rata sebelum dinikmati"
                    )
                ),

                ),
            message = "Data fetch successfuly",
            status = true
        )
}