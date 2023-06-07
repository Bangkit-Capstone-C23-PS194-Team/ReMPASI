package com.caps.rempasi.data.local.room

import androidx.room.*
import com.caps.rempasi.data.local.entity.RecipeEntity

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipes(recipes: List<RecipeEntity>)

    @Query("SELECT EXISTS(SELECT * FROM recipe WHERE recipe_name = :name AND isSaved = 1)")
    suspend fun isSavedRecipe(name: String): Boolean

    @Query("SELECT * FROM recipe where recipe_name LIKE '%'|| :keyword || '%' AND isSaved = 1")
    fun searchSavedRecipes(keyword: String): List<RecipeEntity>

    @Query("SELECT * FROM recipe where id = :id")
    fun getRecipeById(id: Int): RecipeEntity

    @Update
    suspend fun updateRecipe(recipe: RecipeEntity)

    @Query("DELETE FROM recipe WHERE isSaved = 0")
    suspend fun deleteNoSaved()
}