package com.caps.rempasi.domain.usecase

import com.caps.rempasi.data.local.entity.RecipeEntity
import com.caps.rempasi.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListSavedRecipeUseCaseInteractor @Inject constructor(
    private val recipeRepository: RecipeRepository
) : GetListSavedRecipeUseCase {
    override fun searchSavedRecipes(query: String): Flow<List<RecipeEntity>> = recipeRepository.searchSavedRecipes(query)
}