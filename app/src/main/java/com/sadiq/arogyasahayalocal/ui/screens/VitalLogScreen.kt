package com.sadiq.arogyasahayalocal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sadiq.arogyasahayalocal.viewmodel.VitalLogViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VitalLogScreen(
    viewModel: VitalLogViewModel,
    onBackClick: () -> Unit = {}
) {

    var systolicBP by remember { mutableStateOf("") }
    var diastolicBP by remember { mutableStateOf("") }
    var heartRate by remember { mutableStateOf("") }
    var glucoseLevel by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    var message by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Vital Health Log",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                },

                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackClick()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F8FC))
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(20.dp)
        ) {

            /*
            -------------------------
            PREMIUM HEADER BANNER
            -------------------------
            */

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {

                Box(
                    modifier = Modifier
                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    Color(0xFF1D4ED8),
                                    Color(0xFF2563EB)
                                )
                            )
                        )
                        .padding(24.dp)
                ) {

                    Column {

                        Text(
                            text = "Health Monitoring",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Track BP, Sugar, Heart Rate and patient wellness records",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            /*
            -------------------------
            MAIN FORM CARD
            -------------------------
            */

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {

                    // Systolic BP
                    OutlinedTextField(
                        value = systolicBP,
                        onValueChange = {
                            systolicBP = it
                        },
                        label = {
                            Text("Systolic BP")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.MonitorHeart,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Diastolic BP
                    OutlinedTextField(
                        value = diastolicBP,
                        onValueChange = {
                            diastolicBP = it
                        },
                        label = {
                            Text("Diastolic BP")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.MonitorHeart,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Heart Rate
                    OutlinedTextField(
                        value = heartRate,
                        onValueChange = {
                            heartRate = it
                        },
                        label = {
                            Text("Heart Rate")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Sugar Level
                    OutlinedTextField(
                        value = glucoseLevel,
                        onValueChange = {
                            glucoseLevel = it
                        },
                        label = {
                            Text("Glucose Level")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.WaterDrop,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Weight
                    OutlinedTextField(
                        value = weight,
                        onValueChange = {
                            weight = it
                        },
                        label = {
                            Text("Weight (kg)")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Notes
                    OutlinedTextField(
                        value = notes,
                        onValueChange = {
                            notes = it
                        },
                        label = {
                            Text("Daily Health Notes")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Notes,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        minLines = 3
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    /*
                    SAVE BUTTON
                    */

                    Button(
                        onClick = {
                            if (
                                systolicBP.isNotEmpty() &&
                                diastolicBP.isNotEmpty() &&
                                heartRate.isNotEmpty() &&
                                glucoseLevel.isNotEmpty()
                            ) {

                                viewModel.addVitalLog(
                                    systolicBP = systolicBP.toInt(),
                                    diastolicBP = diastolicBP.toInt(),
                                    heartRate = heartRate.toInt(),
                                    glucoseLevel = glucoseLevel.toInt(),
                                    date = System.currentTimeMillis().toString()
                                )

                                message =
                                    "Vital Health Log Saved Successfully"

                                systolicBP = ""
                                diastolicBP = ""
                                heartRate = ""
                                glucoseLevel = ""
                                weight = ""
                                notes = ""

                            } else {
                                message =
                                    "Please complete all required fields"
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Text(
                            text = "Save Health Record",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            /*
            SUCCESS MESSAGE
            */

            if (message.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE8F5E9)
                    )
                ) {
                    Text(
                        text = message,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}