package com.caps.rempasi.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.caps.rempasi.data.local.entity.RecipeEntity

@Database(
    entities = [RecipeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}
