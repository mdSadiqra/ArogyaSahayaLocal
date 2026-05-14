package com.sadiq.arogyasahayalocal.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sadiq.arogyasahayalocal.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencySOSScreen(
    viewModel: ProfileViewModel,
    onBackClick: () -> Unit = {}
) {

    val context = LocalContext.current
    val savedProfile by viewModel.profileState.collectAsState()

    var statusMessage by remember {
        mutableStateOf("")
    }

    val ambulanceNumber = "108"

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    val familyEmergencyContact =
        savedProfile?.emergencyContact ?: ""

    val emergencySmsMessage =
        """
Emergency Alert!

I need immediate medical help.

Please contact me urgently.

Shared from Arogya Sahaya App
        """.trimIndent()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Emergency SOS",
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
                .background(Color(0xFFF7F8FC))
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            /*
            --------------------------------
            HEADER CARD
            --------------------------------
            */

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {

                Box(
                    modifier = Modifier
                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    Color(0xFFB91C1C),
                                    Color(0xFFEF4444)
                                )
                            )
                        )
                        .padding(24.dp)
                ) {

                    Column {

                        Text(
                            text = "Emergency Medical Support",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = "Instant ambulance access, guardian alert and fast emergency response support",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            /*
            --------------------------------
            BIG SOS BUTTON
            --------------------------------
            */

            Button(
                onClick = {
                    val callIntent = Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("tel:$ambulanceNumber")
                    )

                    context.startActivity(callIntent)

                    statusMessage =
                        "Emergency mode activated. Ambulance support opening now."
                },
                modifier = Modifier.size(240.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFDC2626)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 14.dp
                )
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = "SOS",
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            Text(
                text = "Tap SOS for immediate emergency response",
                fontSize = 15.sp,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            /*
            --------------------------------
            QUICK ACTION ROW 1
            --------------------------------
            */

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                QuickActionCard(
                    title = "Ambulance Call",
                    subtitle = "108 Emergency",
                    icon = Icons.Default.Call,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        val callIntent = Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse("tel:$ambulanceNumber")
                        )

                        context.startActivity(callIntent)
                    }
                )

                QuickActionCard(
                    title = "Family Alert",
                    subtitle = if (
                        familyEmergencyContact.isEmpty()
                    ) {
                        "No Contact Saved"
                    } else {
                        familyEmergencyContact
                    },
                    icon = Icons.Default.Message,
                    modifier = Modifier.weight(1f),
                    onClick = {

                        if (familyEmergencyContact.isNotEmpty()) {

                            val smsIntent = Intent(
                                Intent.ACTION_SENDTO
                            ).apply {
                                data = Uri.parse(
                                    "smsto:$familyEmergencyContact"
                                )

                                putExtra(
                                    "sms_body",
                                    emergencySmsMessage
                                )
                            }

                            context.startActivity(smsIntent)

                            statusMessage =
                                "Emergency alert prepared for family contact."

                        } else {

                            statusMessage =
                                "Please save emergency contact in Medical Profile first."
                        }
                    }
                )
            }

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            /*
            --------------------------------
            QUICK ACTION ROW 2
            --------------------------------
            */

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                QuickActionCard(
                    title = "Nearest Hospital",
                    subtitle = "Open Maps",
                    icon = Icons.Default.LocalHospital,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        val mapIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("geo:0,0?q=hospital near me")
                        )

                        context.startActivity(mapIntent)
                    }
                )

                QuickActionCard(
                    title = "Share Location",
                    subtitle = "Emergency Help",
                    icon = Icons.Default.LocationOn,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        statusMessage =
                            "Live location sharing can be added next for advanced emergency support."
                    }
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            /*
            --------------------------------
            STATUS CARD
            --------------------------------
            */

            if (statusMessage.isNotEmpty()) {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFEBEE)
                    )
                ) {

                    Text(
                        text = statusMessage,
                        modifier = Modifier.padding(18.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )
        }
    }
}

@Composable
fun QuickActionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(22.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        onClick = {
            onClick()
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Surface(
                shape = CircleShape,
                color = Color(0xFFFFF1F2)
            ) {
                Box(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}