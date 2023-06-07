package com.caps.rempasi.domain.usecase.recipe

import com.caps.rempasi.data.local.entity.RecipeEntity

interface UpdateSavedRecipe {
    suspend fun updateRecipe(recipe: RecipeEntity, isSaved: Boolean)
}