package com.caps.rempasi.data.repository

import com.caps.rempasi.data.local.entity.RecipeEntity
import com.caps.rempasi.data.local.room.RecipeDatabase
import com.caps.rempasi.data.remote.RemoteDataSource
import com.caps.rempasi.data.remote.response.PredictionsItem
import com.caps.rempasi.data.remote.retrofit.ApiService
import com.caps.rempasi.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val recipeDatabase: RecipeDatabase,
) : RecipeRepository {
    override fun imageDetection(image: MultipartBody.Part): Flow<List<PredictionsItem>> = flow {
        val response = apiService.imageDetection(image)
        if (response.status) {
            val predictions = response.data.predictions
            emit(predictions)
        }
    }.flowOn(Dispatchers.IO)

    override fun getRecipes(keyword: List<String>): Flow<List<RecipeEntity>> = flow {
        val response = apiService.getRecommendationRecipe(keyword)
        if (response.status) {
            val recipes = response.data
            val recipeList = recipes.map { recipeItem ->
                val isSaved = recipeDatabase.recipeDao().isSavedRecipe(recipeItem.name)
                RecipeEntity(
                    recipeItem.id,
                    recipeItem.image,
                    recipeItem.name,
                    recipeItem.steps,
                    recipeItem.ingredients,
                    isSaved
                )
            }
            recipeDatabase.recipeDao().deleteNoSaved()
            recipeDatabase.recipeDao().insertRecipes(recipeList)
            emit(recipeList)
        }
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