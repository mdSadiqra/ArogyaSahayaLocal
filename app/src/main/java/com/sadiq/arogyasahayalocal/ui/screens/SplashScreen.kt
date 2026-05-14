package com.sadiq.arogyasahayalocal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit = {}
) {

    LaunchedEffect(Unit) {
        delay(2500)
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFF7FAFC),
                        Color(0xFFFFFFFF),
                        Color(0xFFF1F5F9)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            /*
            -------------------------
            PREMIUM MEDICAL LOGO
            -------------------------
            */

            Card(
                modifier = Modifier.size(140.dp),
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "🏥",
                        fontSize = 58.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            /*
            -------------------------
            APP NAME
            -------------------------
            */

            Text(
                text = "Arogya Sahaya",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Your Digital Healthcare Companion",
                fontSize = 16.sp,
                color = Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(28.dp))

            /*
            -------------------------
            PREMIUM TAG CARD
            -------------------------
            */

            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE0F2FE)
                )
            ) {

                Text(
                    text = "Medicine • Health • Emergency • Care",
                    modifier = Modifier.padding(
                        horizontal = 18.dp,
                        vertical = 10.dp
                    ),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF0369A1)
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            /*
            -------------------------
            LOADING INDICATOR
            -------------------------
            */

            CircularProgressIndicator(
                strokeWidth = 4.dp
            )
        }

        /*
        -------------------------
        FOOTER
        -------------------------
        */

        Text(
            text = "Healthcare Support for Everyone",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            fontSize = 14.sp,
            color = Color(0xFF94A3B8)
        )
    }
}