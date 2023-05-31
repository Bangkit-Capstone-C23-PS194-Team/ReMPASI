package com.caps.rempasi.presentation.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caps.rempasi.R
import com.caps.rempasi.presentation.ui.theme.ReMPASITheme
import com.caps.rempasi.presentation.ui.theme.Typography

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    openWelcomeCallback: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 48.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.motherhood),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(258.dp),
            contentScale = ContentScale.Fit,
        )
        Text(
            text = "Untuk melanjutkan, silakan login atau registrasi menggunakan akun Google Anda.",
            style = Typography.titleLarge.copy(
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Spacer(modifier = Modifier.height(64.dp))
        Button(
            onClick = {
               openWelcomeCallback()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_google),
                    contentDescription = "Logo Google",
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = "Masuk dengan Google",
                    modifier = Modifier.padding(start = 24.dp),
                    style = Typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthPreview() {
    ReMPASITheme {
        AuthScreen(openWelcomeCallback = {})
    }
}