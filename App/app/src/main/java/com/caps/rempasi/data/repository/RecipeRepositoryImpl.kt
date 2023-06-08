package com.caps.rempasi.data.repository

import com.caps.rempasi.data.local.entity.RecipeEntity
import com.caps.rempasi.data.local.room.RecipeDatabase
import com.caps.rempasi.data.remote.RemoteDataSource
import com.caps.rempasi.data.remote.response.PredictionsItem
import com.caps.rempasi.domain.repository.RecipeRepository
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
    override fun imageDetection(): Flow<List<PredictionsItem>> = flow {
        val predictions = listOf(
            PredictionsItem(
                annotatedCoordinate = listOf(
                    0.355621755,
                    0.679655254,
                    0.526849329,
                    0.804018199,
                ),
                confidence = 0.993053317,
                label = "Jeruk"
            ),
            PredictionsItem(
                annotatedCoordinate = listOf(
                    0.00356794661,
                    0.115658775,
                    0.875023,
                    0.373989522
                ),
                confidence = 0.661687613,
                label = "Nanas"
            ),
            PredictionsItem(
                annotatedCoordinate = listOf(
                    0.190684676,
                    0.0220946632,
                    0.623081207,
                    0.17608887
                ),
                confidence = 0.61356312,
                label = "Wortel"
            ),
        )
        emit(predictions)
    }.flowOn(Dispatchers.IO)

    override fun getRecipes(keyword: List<String>): Flow<List<RecipeEntity>> = flow {
        val response = remote.getRecipes().data.filter {
            it.name.contains(keyword.first(), ignoreCase = true).or(it.name.contains(
                keyword[1], ignoreCase = true))
        }
        val recipeList = response.map { recipeItem ->
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