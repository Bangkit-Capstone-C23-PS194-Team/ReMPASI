package com.caps.rempasi.domain.repository

import com.caps.rempasi.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipes(): Flow<List<RecipeEntity>>
    fun searchSavedRecipes(query: String): Flow<List<RecipeEntity>>
}
