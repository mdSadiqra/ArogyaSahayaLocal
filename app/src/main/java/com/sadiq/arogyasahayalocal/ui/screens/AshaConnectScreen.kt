package com.sadiq.arogyasahayalocal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Verified
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sadiq.arogyasahayalocal.data.HealthCampEntity
import com.sadiq.arogyasahayalocal.viewmodel.HealthCampViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AshaConnectScreen(
    viewModel: HealthCampViewModel,
    onBackClick: () -> Unit = {}
) {

    val camps by viewModel.camps.collectAsState()

    val defaultCampList = listOf(
        HealthCampEntity(
            title = "Village Health Camp",
            date = "12 May 2026",
            location = "Primary Health Center",
            workerName = "ASHA Worker: Sunitha",
            campType = "General Checkup",
            status = "Confirmed"
        ),
        HealthCampEntity(
            title = "BP & Sugar Screening",
            date = "18 May 2026",
            location = "Community Hall",
            workerName = "ASHA Worker: Lakshmi",
            campType = "Diabetes + BP Check",
            status = "Confirmed"
        ),
        HealthCampEntity(
            title = "Women Wellness Camp",
            date = "25 May 2026",
            location = "Government Hospital",
            workerName = "ASHA Worker: Shabana",
            campType = "Women Healthcare",
            status = "Upcoming"
        ),
        HealthCampEntity(
            title = "Child Vaccination Drive",
            date = "02 June 2026",
            location = "Village Anganwadi Center",
            workerName = "ASHA Worker: Rekha",
            campType = "Vaccination Support",
            status = "Upcoming"
        )
    )

    val displayList =
        if (camps.isEmpty()) defaultCampList else camps

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "ASHA Connect",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onBackClick() }
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
                .background(Color(0xFFF7FAFC))
                .padding(paddingValues)
                .padding(18.dp)
        ) {

            /*
            --------------------------------
            PREMIUM HEADER
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
                                    Color(0xFF0369A1),
                                    Color(0xFF38BDF8)
                                )
                            )
                        )
                        .padding(24.dp)
                ) {
                    Column {

                        Text(
                            text = "Health Camp Calendar",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = "Register for village camps, vaccination drives and local healthcare events",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            /*
            --------------------------------
            SUMMARY CARD
            --------------------------------
            */

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {
                        Text(
                            text = "Upcoming Camps",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )

                        Text(
                            text = "${displayList.size} Scheduled",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Surface(
                        shape = CircleShape,
                        color = Color(0xFFE0F2FE)
                    ) {
                        Box(
                            modifier = Modifier.padding(14.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            /*
            --------------------------------
            CAMP LIST
            --------------------------------
            */

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(displayList) { camp ->

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

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = camp.title,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(
                                        modifier = Modifier.height(4.dp)
                                    )

                                    Text(
                                        text = if (camp.isRegistered)
                                            "Registered Successfully"
                                        else
                                            camp.status,
                                        fontSize = 13.sp,
                                        color = if (camp.isRegistered)
                                            Color(0xFF059669)
                                        else
                                            Color(0xFF2563EB),
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }

                                Icon(
                                    imageVector = if (camp.isRegistered)
                                        Icons.Default.CheckCircle
                                    else
                                        Icons.Default.Verified,
                                    contentDescription = null
                                )
                            }

                            Spacer(
                                modifier = Modifier.height(18.dp)
                            )

                            AshaInfoRow(
                                icon = Icons.Default.CalendarMonth,
                                title = "Camp Date",
                                value = camp.date
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            AshaInfoRow(
                                icon = Icons.Default.LocationOn,
                                title = "Location",
                                value = camp.location
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            AshaInfoRow(
                                icon = Icons.Default.Person,
                                title = "Assigned Worker",
                                value = camp.workerName
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            AshaInfoRow(
                                icon = Icons.Default.LocalHospital,
                                title = "Camp Type",
                                value = camp.campType
                            )

                            Spacer(
                                modifier = Modifier.height(20.dp)
                            )

                            Button(
                                onClick = {
                                    if (!camp.isRegistered) {
                                        viewModel.updateCamp(
                                            camp.copy(
                                                isRegistered = true
                                            )
                                        )
                                    }
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(18.dp),
                                enabled = !camp.isRegistered,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (camp.isRegistered)
                                        Color.Gray
                                    else
                                        Color(0xFF0284C7)
                                )
                            ) {
                                Text(
                                    text = if (camp.isRegistered)
                                        "Already Registered"
                                    else
                                        "Register for Camp",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                item {
                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AshaInfoRow(
    icon: ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Surface(
            shape = CircleShape,
            color = Color(0xFFF1F5F9)
        ) {
            Box(
                modifier = Modifier.padding(10.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null
                )
            }
        }

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        Column {
            Text(
                text = title,
                fontSize = 13.sp,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = value,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}