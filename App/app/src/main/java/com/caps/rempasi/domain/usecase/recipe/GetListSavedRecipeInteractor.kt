package com.caps.rempasi.domain.usecase.recipe

import com.caps.rempasi.data.local.entity.RecipeEntity
import com.caps.rempasi.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListSavedRecipeInteractor @Inject constructor(
    private val recipeRepository: RecipeRepository
) : GetListSavedRecipe {
    override fun searchSavedRecipes(query: String): Flow<List<RecipeEntity>> = recipeRepository.searchSavedRecipes(query)
}