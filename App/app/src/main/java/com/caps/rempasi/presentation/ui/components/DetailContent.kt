package com.caps.rempasi.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.caps.rempasi.presentation.ui.theme.Red
import com.caps.rempasi.presentation.ui.theme.Typography
import com.caps.rempasi.R
import com.caps.rempasi.presentation.ui.theme.White

@Composable
fun DetailContent(
    name: String,
    thumbnail: String,
    ingredients: List<String>,
    steps: List<String>,
    isSaved: Boolean,
    saveToggle: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var currentSaved by remember { mutableStateOf(isSaved) }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        AsyncImage(
            model = thumbnail,
            contentDescription = "thumbnail $name",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(243.dp)
                .shadow(
                    elevation = 6.dp,
                    shape = RoundedCornerShape(5.dp)
                )
                .clip(RoundedCornerShape(5.dp))
                .background(White),
        )
        Text(text = name, style = Typography.headlineLarge)
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Red,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                saveToggle()
                currentSaved = !currentSaved
            },
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 8.dp),
                painter = if (currentSaved) painterResource(id = R.drawable.bookmark) else painterResource(
                    id = R.drawable.bookmark_outlined
                ),
                contentDescription = "Selanjutnya",
                tint = Color.White
            )
            Text(
                text = if (currentSaved) "Tersimpan" else "Simpan Resep",
                style = Typography.headlineMedium.copy(
                    fontSize = 14.sp,
                )
            )
        }
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