package com.sadiq.arogyasahayalocal.ui.screens

import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AlarmActivity : ComponentActivity() {

    private var ringtone: Ringtone? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val medicineName =
            intent.getStringExtra("medicine_name")
                ?: "Medicine Reminder"

        /*
        --------------------------------
        PLAY ALARM SOUND
        --------------------------------
        */

        val alarmUri =
            RingtoneManager.getDefaultUri(
                RingtoneManager.TYPE_ALARM
            )

        ringtone =
            RingtoneManager.getRingtone(
                this,
                alarmUri
            )

        ringtone?.play()

        setContent {

            MaterialTheme {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color(0xFF0F172A),
                                    Color(0xFF1E293B)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        shape = MaterialTheme.shapes.extraLarge
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(28.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Surface(
                                shape = CircleShape,
                                color = Color(0xFFE0F2FE)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Alarm,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(24.dp)
                                        .size(48.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Text(
                                text = "Medicine Alarm",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Time to take:",
                                fontSize = 16.sp,
                                color = Color.Gray
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = medicineName,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold
                            )

                            Spacer(modifier = Modifier.height(32.dp))

                            Button(
                                onClick = {
                                    ringtone?.stop()
                                    finish()
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp)
                            ) {
                                Text(
                                    text = "Dismiss Alarm",
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        ringtone?.stop()
        super.onDestroy()
    }
}