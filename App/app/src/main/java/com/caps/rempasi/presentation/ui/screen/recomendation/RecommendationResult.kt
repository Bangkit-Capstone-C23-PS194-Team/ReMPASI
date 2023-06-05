package com.caps.rempasi.presentation.ui.screen.recomendation

import android.os.Parcelable
import com.caps.rempasi.data.local.entity.RecipeEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecommendationResult(
    val annotatedImage: String,
    val listRecipe: List<RecipeEntity>
) : Parcelable