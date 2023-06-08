package com.caps.rempasi.domain.usecase.image_detection

import com.caps.rempasi.data.remote.response.PredictionsItem
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface PostImageDetection {
    fun imageDetection(image: MultipartBody.Part): Flow<List<PredictionsItem>>
}