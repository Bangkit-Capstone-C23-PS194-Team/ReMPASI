package com.caps.rempasi.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.caps.rempasi.presentation.ui.theme.ReMPASITheme
import com.caps.rempasi.presentation.ui.theme.Typography
import com.caps.rempasi.presentation.ui.theme.White
import com.caps.rempasi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemSavedRecipe(
    id: Int,
    thumbnail: String,
    title: String,
    description: List<String>,
    onItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = {onItemClicked(id)},
        modifier = modifier
            .width(120.dp)
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .clip(RoundedCornerShape(5.dp))
            .background(White)
            .padding(6.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AsyncImage(
                model = thumbnail,
                contentDescription = stringResource(R.string.thumbnail, title),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp))
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                style = Typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = Color.Red,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(
                    R.string.ingredients_description,
                    description.joinToString(", ")
                ),
                style = Typography.bodySmall,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemSavedPreview() {
    ReMPASITheme {
        ItemSavedRecipe(
            id = 1,
            thumbnail = "https://res.cloudinary.com/dk0z4ums3/image/upload/v1638252657/attached_image/cara-menghangatkan-mpasi-agar-kualitasnya-tetap-terjaga-0-alodokter.jpg",
            title = "Bubur Hati Ayam, Wortel, dan Brokoli",
            description = listOf(
                "1 hati ayam kampung",
                "½ buah wortel",
                "¼ bungkul brokoli",
                "2 sdm beras putih",
                "100 ml kaldu ayam kampung",
                "100 ml air",
                "1 siung bawang putih, cingcang halus",
                "Sejumput bawang merah goreng",
            ),
            onItemClicked = {},
        )
    }
}