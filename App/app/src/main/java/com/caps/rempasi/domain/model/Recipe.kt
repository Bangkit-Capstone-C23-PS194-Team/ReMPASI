package com.caps.rempasi.domain.model

data class Recipe(
    val id: Int,
    val imageUrl: String,
    val recipe_name: String,
    val steps: List<String>,
    val ingredients: List<String>,
)