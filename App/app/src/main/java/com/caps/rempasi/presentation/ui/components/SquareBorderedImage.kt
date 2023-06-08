package com.caps.rempasi.presentation.ui.components

import android.graphics.Paint
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import coil.compose.rememberAsyncImagePainter
import com.caps.rempasi.data.remote.response.PredictionsItem
import kotlin.random.Random

@Composable
fun SquareBorderedImage(
    imageUri: Uri,
    predictions: List<PredictionsItem>,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUri),
            contentDescription = null,
        )

        for (item in predictions) {
            val topLeftY = item.annotatedCoordinate[0] // Koordinat y titik top left
            val topLeftX = item.annotatedCoordinate[1] // Koordinat x titik top left
            val bottomRightY = item.annotatedCoordinate[2] // Koordinat y titik bottom right
            val bottomRightX = item.annotatedCoordinate[3] // Koordinat x titik bottom right

            Canvas(modifier = Modifier.matchParentSize()) {
                // Get the size of the image
                val imageSize = size
                val imageWidth = imageSize.width
                val imageHeight = imageSize.height

                // Calculate the coordinates based on the percentage of the image size
                val x1 = (topLeftX * imageWidth).toFloat()
                val y1 = (topLeftY * imageHeight).toFloat()
                val x2 = (bottomRightX * imageWidth).toFloat()
                val y2 = (bottomRightY * imageHeight).toFloat()

                val colorRandom = Color(Random.Default.nextInt(256), Random.Default.nextInt(256), Random.Default.nextInt(256))
                // Draw the border rectangle
                drawRect(
                    color = colorRandom,
                    style = Stroke(width = 4f),
                    topLeft = Offset(x1, y1),
                    size = Size(x2 - x1, y2 - y1)
                )

                val text = item.label
                drawIntoCanvas {
                    val paint = Paint().apply {
                        color = colorRandom.toArgb()
                        textSize = 32f
                    }
                    it.nativeCanvas.drawText(text, x1, y1-4f, paint)
                }
            }
        }
    }
}