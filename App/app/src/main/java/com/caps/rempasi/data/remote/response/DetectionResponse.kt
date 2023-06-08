package com.caps.rempasi.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class DetectionResponse(
    @field:SerializedName("data")
    val data: Data,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Boolean
)

@Parcelize
data class Data(
    @field:SerializedName("predictions")
    val predictions: List<PredictionsItem>
): Parcelable

@Parcelize
data class PredictionsItem(
    @field:SerializedName("annotated_coordinate")
    val annotatedCoordinate: List<Double>,

    @field:SerializedName("confidence")
    val confidence: Double,

    @field:SerializedName("label")
    val label: String
): Parcelable
