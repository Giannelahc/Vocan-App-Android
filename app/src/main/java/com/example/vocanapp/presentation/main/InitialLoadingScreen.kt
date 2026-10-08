package com.example.vocanapp.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vocanapp.R

@Composable
fun InitialLoadingScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                contentAlignment = Alignment.Center
            ) {

                // Michita
                Image(
                    painter = painterResource(R.drawable.michita_loading),
                    contentDescription = "Michita",
                    modifier = Modifier
                        .size(210.dp)
                        .align(Alignment.BottomCenter),
                    contentScale = ContentScale.Fit
                )


                LettersLoading(Modifier
                    .size(130.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = (-60).dp))
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "Michilingo",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF222222)
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Getting your vocabulary ready...",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}