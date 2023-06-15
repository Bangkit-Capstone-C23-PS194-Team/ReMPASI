package com.caps.rempasi.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.caps.rempasi.presentation.ui.theme.Red
import com.caps.rempasi.presentation.ui.theme.Typography
import com.caps.rempasi.utils.OnBoardingPageData

@Composable
fun OnboardingContent(
    onBoardingPage: OnBoardingPageData,
    modifier: Modifier = Modifier
) {
    val description = onBoardingPage.description
    val annotatedString = buildAnnotatedString {
        description.split(" ").forEachIndexed { index, word ->
            val backgroundSpan = if (word == "Si" || word == "Kecil" || word == "Kecil.") {
                SpanStyle(
                    color = Red,
                )
            } else {
                SpanStyle()
            }
            withStyle(style = backgroundSpan) {
                append(word)
                if (index != description.split(" ").size - 1) {
                    append(" ")
                }
            }
        }
    }

    Box(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = onBoardingPage.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(258.dp),
                contentScale = ContentScale.Fit,
            )
            Text(
                text = onBoardingPage.title,
                style = Typography.titleLarge.copy(
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                text = annotatedString,
                style = Typography.bodyMedium.copy(
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        OnboardingContent(onBoardingPage = OnBoardingPageData.Fourth)
    }
}