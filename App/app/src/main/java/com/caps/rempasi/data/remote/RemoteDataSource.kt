package com.caps.rempasi.data.remote

import com.caps.rempasi.data.remote.retrofit.DummyRecipeData
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {
    fun getRecipes() = DummyRecipeData.getRecipes()
}