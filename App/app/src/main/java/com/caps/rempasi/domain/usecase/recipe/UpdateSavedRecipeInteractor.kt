package com.caps.rempasi.domain.usecase.recipe

import com.caps.rempasi.data.local.entity.RecipeEntity
import com.caps.rempasi.domain.repository.RecipeRepository
import javax.inject.Inject

class UpdateSavedRecipeInteractor @Inject constructor(
    private val recipeRepository: RecipeRepository
) : UpdateSavedRecipe {
    override suspend fun updateRecipe(recipe: RecipeEntity, isSaved: Boolean) = recipeRepository.setSaved(recipe, isSaved)
}