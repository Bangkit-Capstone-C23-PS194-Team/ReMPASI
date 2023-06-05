package com.caps.rempasi.domain.repository

import com.caps.rempasi.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun imageDetection(): Flow<String>
    fun getRecipes(): Flow<List<RecipeEntity>>
    fun getDetailRecipeById(id: Int): Flow<RecipeEntity>
    fun searchSavedRecipes(query: String): Flow<List<RecipeEntity>>
}
