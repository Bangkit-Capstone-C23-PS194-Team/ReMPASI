package com.caps.rempasi.presentation.ui.screen.recomendation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecommendationResult(
    val annotatedImage: String,
    val objectName: List<String>
) : Parcelable