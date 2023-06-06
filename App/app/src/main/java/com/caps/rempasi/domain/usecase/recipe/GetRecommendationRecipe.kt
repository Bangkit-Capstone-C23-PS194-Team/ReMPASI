package com.caps.rempasi.domain.usecase.recipe

import com.caps.rempasi.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface GetRecommendationRecipe {
    fun getRecipes(keyword: List<String>): Flow<List<RecipeEntity>>
}