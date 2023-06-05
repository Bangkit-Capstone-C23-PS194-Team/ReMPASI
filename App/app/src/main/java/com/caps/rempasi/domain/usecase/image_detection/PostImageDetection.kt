package com.caps.rempasi.domain.usecase.image_detection

import kotlinx.coroutines.flow.Flow

interface PostImageDetection {
    fun imageDetection(): Flow<String>
}