package com.caps.rempasi.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.caps.rempasi.presentation.ui.theme.Typography

@Composable
fun DetailContent(
    name: String,
    thumbnail: Int,
    ingredients: List<String>,
    steps: List<String>,
    modifier: Modifier = Modifier,
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        Image(
            painter = painterResource(id = thumbnail),
            contentDescription = "thumbnail $name",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(243.dp)
                .shadow(
                    elevation = 6.dp,
                    shape = RectangleShape
                )
                .clip(
                    shape = RoundedCornerShape(5.dp)
                )
        )
        Text(text = name, style = Typography.headlineLarge)
        DetailSection(title = "Bahan-bahan") {
            ingredients.forEach { item ->
                Row(
                    modifier = Modifier.padding(bottom = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .background(Color.Black, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item,
                        style = Typography.bodyMedium
                    )
                }
            }
        }
        DetailSection(title = "Cara Membuat") {
            for ((index, item) in steps.withIndex()) {
                val itemNumber = index + 1
                ItemStep(number = itemNumber, step = item)
            }
        }
    }

}