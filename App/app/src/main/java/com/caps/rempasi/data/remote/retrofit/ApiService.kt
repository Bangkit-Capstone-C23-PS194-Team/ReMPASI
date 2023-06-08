package com.caps.rempasi.data.remote.retrofit

import com.caps.rempasi.data.remote.response.DetectionResponse
import com.caps.rempasi.data.remote.response.RecommendationResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {

    @Multipart
    @POST("upload")
    suspend fun imageDetection(
        @Part file: MultipartBody.Part,
    ): DetectionResponse

    @GET("recommendation")
    suspend fun getRecommendationRecipe(
        @Query("ingredients") ingredients: List<String>
    ): RecommendationResponse
}