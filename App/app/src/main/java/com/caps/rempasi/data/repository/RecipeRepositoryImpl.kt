package com.caps.rempasi.data.repository

import com.caps.rempasi.data.local.entity.RecipeEntity
import com.caps.rempasi.data.local.room.RecipeDatabase
import com.caps.rempasi.data.remote.RemoteDataSource
import com.caps.rempasi.domain.repository.RecipeRepository
import com.caps.rempasi.presentation.ui.screen.recomendation.RecommendationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepositoryImpl @Inject constructor(
    private val remote: RemoteDataSource,
    private val recipeDatabase: RecipeDatabase,
) : RecipeRepository {
    override fun imageDetection(): Flow<RecommendationResult> = flow {
        val imageUrl = "https://cdn.popmama.com/content-images/post/20210813/gabrielle-henderson-djy0xdwceum-unsplashjpg-3bbf6004a0fb5dd501b52970b2aafc7c_800x420.jpg"
        val objectDetect = listOf(
            "Ayam",
            "Goreng"
        )
        emit(RecommendationResult(imageUrl, objectDetect ))
    }.flowOn(Dispatchers.IO)

    override fun getRecipes(keyword: List<String>): Flow<List<RecipeEntity>> = flow {
        val response = remote.getRecipes().recipes.filter {
            it.recipeName.contains(keyword.first(), ignoreCase = true).or(it.recipeName.contains(
                keyword[1], ignoreCase = true))
        }
        val recipeList = response.map { recipeItem ->
            val isSaved = recipeDatabase.recipeDao().isSavedRecipe(recipeItem.recipeName)
            RecipeEntity(
                recipeItem.id,
                recipeItem.imageUrl,
                recipeItem.recipeName,
                recipeItem.steps,
                recipeItem.ingredients,
                isSaved
            )
        }
        recipeDatabase.recipeDao().deleteNoSaved()
        recipeDatabase.recipeDao().insertRecipes(recipeList)
        emit(recipeList)
    }.flowOn(Dispatchers.IO)

    override fun getDetailRecipeById(id: Int): Flow<RecipeEntity> = flow {
        val detailRecipe = recipeDatabase.recipeDao().getRecipeById(id)
        emit(detailRecipe)
    }.flowOn(Dispatchers.IO)

    override fun searchSavedRecipes(query: String): Flow<List<RecipeEntity>> = flow {
        val response = recipeDatabase.recipeDao().searchSavedRecipes(query)
        emit(response)
    }.flowOn(Dispatchers.IO)

    override suspend fun setSaved(recipe: RecipeEntity, isSaved: Boolean) {
        recipe.isSaved = isSaved
        recipeDatabase.recipeDao().updateRecipe(recipe)
    }
}