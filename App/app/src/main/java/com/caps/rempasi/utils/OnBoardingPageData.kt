package com.caps.rempasi.utils

import androidx.annotation.DrawableRes
import com.caps.rempasi.R

sealed class OnBoardingPageData(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String,
) {
    object First : OnBoardingPageData(
        image = R.drawable.happy_baby,
        title = "Selamat datang di Aplikasi ReMPASI",
        description = "Temukan dunia rasa dan nutrisi yang menakjubkan untuk Si Kecil."
    )

    object Second : OnBoardingPageData(
        image = R.drawable.focus,
        title = "Lengkapi perjalanan makan bayi Anda dengan mudah!",
        description = "Gunakan kamera untuk mengambil foto makanan, dan dapatkan resep MPASI yang menggugah selera dan membantu dalam proses perkembangan mereka."
    )

    object Third : OnBoardingPageData(
        image = R.drawable.creative_thinking,
        title = "Tingkatkan keberagaman makanan bayi Anda!",
        description = "Dengan teknologi deteksi objek, aplikasi kami akan memahami jenis makanan yang Anda berikan kepada Si Kecil."
    )

    object Fourth : OnBoardingPageData(
        image = R.drawable.cooking,
        title = "Jelajahi dunia rasa dan nutrisi bersama bayi Anda!",
        description = "Bawa kesenangan dalam memberi makan Si Kecil dan bantu mereka tumbuh dengan baik."
    )
}
