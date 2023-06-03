package com.caps.rempasi.data.remote

import com.caps.rempasi.data.remote.retrofit.DummyRecipeData

class RemoteDataSource {
    fun getRecipes() = DummyRecipeData.getRecipes()
}