package com.caps.rempasi.data.repository

import com.caps.rempasi.data.remote.RemoteDataSource
import com.caps.rempasi.domain.model.Recipe
import com.caps.rempasi.domain.repository.RecipeRepository
import com.caps.rempasi.utils.toRecipeDomain
import com.caps.rempasi.utils.toRecipesDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepositoryImpl @Inject constructor(
    private val remote: RemoteDataSource
) : RecipeRepository {
    override fun searchSavedRecipes(query: String): Flow<List<Recipe>> = flow {
        val response = remote.getRecipes().recipes.filter {
            it.recipeName.contains(query, ignoreCase = true)
        }
        val recipes = response.toRecipesDomain()
        emit(recipes)
    }

    override fun getDetailRecipeById(id: Int): Flow<Recipe> = flow {
        val response = remote.getRecipes().recipes.first { it.id == id }
        val recipe = response.toRecipeDomain()
        emit(recipe)
    }
}