package com.caps.rempasi.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey
    var id: Int,
    var imageUrl: String,
    var recipe_name: String,
    var steps: List<String>,
    var ingredients: List<String>,
    var isSaved: Boolean = false,
): Parcelable
