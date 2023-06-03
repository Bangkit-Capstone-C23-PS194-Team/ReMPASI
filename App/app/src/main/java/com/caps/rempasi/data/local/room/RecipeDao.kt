package com.caps.rempasi.data.local.room

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.caps.rempasi.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipes(recipes: List<RecipeEntity>)

    @Query("SELECT EXISTS(SELECT * FROM recipe WHERE recipe_name = :name AND isSaved = 1)")
    suspend fun isSavedRecipe(name: String): Boolean

    @Query("SELECT * FROM recipe where recipe_name LIKE '%'|| :keyword || '%' AND isSaved = 1")
    fun searchSavedRecipes(keyword: String): Flow<List<RecipeEntity>>

    @Update
    suspend fun updateRecipe(recipe: RecipeEntity)

    @Query("DELETE FROM recipe WHERE isSaved = 0")
    suspend fun deleteNoSaved()
}