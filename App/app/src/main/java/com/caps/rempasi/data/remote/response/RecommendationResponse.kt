package com.caps.rempasi.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecommendationResponse(
    @field:SerializedName("list_recipe")
    val recipes: List<RecipeItem>
)

data class RecipeItem(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("recipe_name")
    val recipeName: String,

    @field:SerializedName("ingredients")
    val ingredients: List<String>,

    @field:SerializedName("steps")
    val steps: List<String>,
)
