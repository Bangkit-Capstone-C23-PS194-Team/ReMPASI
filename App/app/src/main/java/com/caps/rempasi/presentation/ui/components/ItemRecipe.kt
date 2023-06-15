package com.caps.rempasi.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.caps.rempasi.presentation.ui.theme.ReMPASITheme
import com.caps.rempasi.R
import com.caps.rempasi.presentation.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemRecipe(
    id: Int,
    thumbnail: String,
    title: String,
    description: List<String>,
    isSaved: Boolean,
    onItemClicked: (Int) -> Unit,
    saveToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentSaved by remember { mutableStateOf(isSaved) }

    Surface(
        onClick = { onItemClicked(id) },
        color = White,
        modifier = modifier
            .fillMaxWidth()
            .height(91.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(4.dp)
        ) {
            Box {
                AsyncImage(
                    model = thumbnail,
                    contentDescription = stringResource(R.string.thumbnail, title),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(75.dp)
                        .height(75.dp)
                        .clip(RoundedCornerShape(5.dp))
                )
                IconButton(
                    onClick = {
                        saveToggle()
                        currentSaved = !currentSaved
                    },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = if (currentSaved) painterResource(id = R.drawable.bookmark) else painterResource(
                            id = R.drawable.bookmark_outlined
                        ),
                        contentDescription = null,
                        tint = Red,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.fillMaxHeight(),
            ) {
                Text(
                    text = title,
                    style = Typography.bodyMedium,
                    color = Red,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(
                        R.string.ingredients_description,
                        description.joinToString(", ")
                    ),
                    style = Typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
    Divider()
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    ReMPASITheme {
        ItemRecipe(
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
            isSaved = false,
            onItemClicked = {},
            saveToggle = {}
        )
    }
}