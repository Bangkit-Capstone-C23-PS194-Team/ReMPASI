package com.caps.rempasi.presentation.ui.screen.auth

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caps.rempasi.R
import com.caps.rempasi.presentation.ui.components.ButtonEndIcon
import com.caps.rempasi.presentation.ui.theme.ReMPASITheme
import com.caps.rempasi.presentation.ui.theme.Typography
import com.caps.rempasi.presentation.ui.theme.White
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    name: String,
    openHomeScreenCallback: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .background(White)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 48.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.thinking_face),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(258.dp),
            contentScale = ContentScale.Fit,
        )
        Text(
            text = "Selamat datang $name! Ada bahan apa aja nih di rumah?",
            style = Typography.titleLarge.copy(
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(64.dp))
        ButtonEndIcon(textTitle = "Ambil foto sekarang") {
            openHomeScreenCallback()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    ReMPASITheme {
        WelcomeScreen(name = "Hafid") {}
    }
}