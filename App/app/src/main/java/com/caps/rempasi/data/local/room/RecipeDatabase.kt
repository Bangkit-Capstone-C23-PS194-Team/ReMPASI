package com.caps.rempasi.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.caps.rempasi.data.local.entity.RecipeEntity
import com.caps.rempasi.utils.StringListTypeConverter

@Database(
    entities = [RecipeEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(StringListTypeConverter::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}
