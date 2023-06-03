package com.caps.rempasi.domain.repository

import com.caps.rempasi.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun searchSavedRecipes(query: String): Flow<List<Recipe>>

    fun getDetailRecipeById(id: Int): Flow<Recipe>
}