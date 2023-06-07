package com.caps.rempasi.domain.usecase.recipe

import com.caps.rempasi.data.local.entity.RecipeEntity
import com.caps.rempasi.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailRecipeInteractor @Inject constructor(
    private val repository: RecipeRepository
): DetailRecipe {
    override fun getRecipe(id: Int): Flow<RecipeEntity> = repository.getDetailRecipeById(id)
}