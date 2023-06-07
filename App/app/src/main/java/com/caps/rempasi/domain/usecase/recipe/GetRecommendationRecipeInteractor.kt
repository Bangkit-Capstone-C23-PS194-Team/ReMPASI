package com.caps.rempasi.domain.usecase.recipe

import com.caps.rempasi.data.local.entity.RecipeEntity
import com.caps.rempasi.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecommendationRecipeInteractor @Inject constructor(
    private val recipeRepository: RecipeRepository
) : GetRecommendationRecipe {
    override fun getRecipes(keyword: List<String>): Flow<List<RecipeEntity>> = recipeRepository.getRecipes(keyword)
}