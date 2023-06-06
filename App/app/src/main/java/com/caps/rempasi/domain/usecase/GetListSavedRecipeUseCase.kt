package com.caps.rempasi.domain.usecase

import com.caps.rempasi.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface GetListSavedRecipeUseCase {
    fun searchSavedRecipes(query: String): Flow<List<RecipeEntity>>
}