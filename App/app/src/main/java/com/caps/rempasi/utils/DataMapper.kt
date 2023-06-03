package com.caps.rempasi.utils

import com.caps.rempasi.data.remote.response.RecipeItem
import com.caps.rempasi.domain.model.Recipe

fun List<RecipeItem>.toRecipesDomain() = map {
    Recipe(it.id, it.imageUrl, it.recipeName, it.steps, it.ingredients)
}

fun RecipeItem.toRecipeDomain() = Recipe(id, imageUrl, recipeName, steps, ingredients)

fun test() {
    var A = listOf<Recipe>()

    var queryGetRecipeName = "Select * from recipe where recipe_name like %keyword%"

// FOR SEBANYAK  queryGetRecipeName Length
//    di body fornya bikin 1 query baru untuk ngeget data steps misal disimpen di var C,
//    dan 1 query lagi untuk ngeget data ingredients misal disimpan di var D
//    jadiin kedua variabel penampung hasil query tersebut menjadi list

//    setelah itu, masih di dalam body for, di bawah inisiasi 2 variable tadi
//    bikin object Recipe dengan data
//    id = queryGetRecipeName[i][id]
//    name = queryGetRecipeName[i]["recipe_name]
//    image = queryGetRecipeName[i]["image"]
//    steps = B
//    ingredients = C

//    setelah itu push objectnya ke LIST A


}