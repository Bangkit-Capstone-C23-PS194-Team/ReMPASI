package com.caps.rempasi.data.repository

import android.util.Log
import com.caps.rempasi.data.local.entity.RecipeEntity
import com.caps.rempasi.data.local.room.RecipeDatabase
import com.caps.rempasi.data.remote.RemoteDataSource
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
    override fun imageDetection(): Flow<String> = flow {
        emit("https://www.google.com/url?sa=i&url=https%3A%2F%2Firraoctavia.com%2Fkenali-bahan-pangan-mpasi-menu-4-bintang%2F&psig=AOvVaw1Q33pes7ramHPLH6s5eLaJ&ust=1686029339215000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCODrw_Cyq_8CFQAAAAAdAAAAABBP")
    }.flowOn(Dispatchers.IO)

    override fun getRecipes(): Flow<List<RecipeEntity>> = flow {
        val response = remote.getRecipes().recipes
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
        Log.d("jknasd", "getDetailRecipeById: $id")
        val detailRecipe = recipeDatabase.recipeDao().getRecipeById(id)
        Log.d("jknasd", "getDetailRecipeById: $detailRecipe")
        emit(detailRecipe)
    }.flowOn(Dispatchers.IO)

    override fun searchSavedRecipes(query: String): Flow<List<RecipeEntity>> = flow {
        val response = recipeDatabase.recipeDao().searchSavedRecipes(query)
        emit(response)
    }.flowOn(Dispatchers.IO)
}