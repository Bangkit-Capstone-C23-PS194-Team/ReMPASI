package com.caps.rempasi.utils

import com.caps.rempasi.data.remote.response.RecipeItem
import com.caps.rempasi.domain.model.Recipe

fun List<RecipeItem>.toRecipesDomain() = map {
    Recipe(it.id, it.imageUrl, it.recipeName, it.steps, it.ingredients)
}

fun RecipeItem.toRecipeDomain() = Recipe(id, imageUrl, recipeName, steps, ingredients)
