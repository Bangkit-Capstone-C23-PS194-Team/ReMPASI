package com.caps.rempasi.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecommendationResponse(
    @field:SerializedName("data")
    val data: List<RecipeItem>,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Boolean
)

data class RecipeItem(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("ingredients")
    val ingredients: List<String>,

    @field:SerializedName("steps")
    val steps: List<String>
)