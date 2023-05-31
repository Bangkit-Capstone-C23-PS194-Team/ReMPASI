package com.caps.rempasi.presentation.ui.screen.onboarding

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caps.rempasi.R
import com.caps.rempasi.presentation.ui.components.ButtonEndIcon
import com.caps.rempasi.presentation.ui.components.OnboardingContent
import com.caps.rempasi.presentation.ui.theme.Red
import com.caps.rempasi.presentation.ui.theme.RedSecondary
import com.caps.rempasi.utils.OnBoardingPageData
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun OnBoardingScreen(
    onboardingViewModel: OnboardingViewModel = hiltViewModel(),
    learnMoreCallback: () -> Unit,
) {
    val pages = listOf(
        OnBoardingPageData.First,
        OnBoardingPageData.Second,
        OnBoardingPageData.Third,
        OnBoardingPageData.Fourth,
    )

    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp)
    ) {
        HorizontalPager(
            modifier = Modifier.weight(7f),
            count = 4,
            state = pagerState,
            verticalAlignment = Alignment.CenterVertically
        ) { position ->
            OnboardingContent(onBoardingPage = pages[position])
        }
        ButtonEndIcon(
            textTitle = stringResource(R.string.learn_more),
            modifier = Modifier.weight(2f),
            pagerState = pagerState
        ) {
            onboardingViewModel.saveOnBoardingState(completed = true)
            learnMoreCallback()
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            activeColor = Red,
            inactiveColor = RedSecondary,
            spacing = 34.dp,
            indicatorHeight = 20.dp,
            indicatorWidth = 20.dp,
            pagerState = pagerState
        )
    }
}