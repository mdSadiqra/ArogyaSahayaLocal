package com.sadiq.arogyasahayalocal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class DashboardItem(
    val title: String,
    val subtitle: String,
    val emoji: String
)

@Composable
fun DashboardScreen(
    onAddMedicineClick: () -> Unit = {},
    onHistoryClick: () -> Unit = {},
    onVitalLogClick: () -> Unit = {},
    onGraphClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onSOSClick: () -> Unit = {},
    onAshaClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onUpdatePasswordClick: () -> Unit = {}
) {

    var showMenu by remember { mutableStateOf(false) }

    val items = listOf(
        DashboardItem(
            "Medical Profile",
            "Patient details and health conditions",
            "👤"
        ),

        DashboardItem(
            "Add Medicine",
            "Daily medicine reminder setup",
            "💊"
        ),

        DashboardItem(
            "Medicine History",
            "Saved medicine records",
            "📋"
        ),

        DashboardItem(
            "Vital Log",
            "BP / Sugar / Heart Rate",
            "❤️"
        ),

        DashboardItem(
            "Health Graph",
            "7-Day trend analysis",
            "📈"
        ),

        DashboardItem(
            "ASHA Connect",
            "Health camp schedules",
            "🏥"
        ),

        DashboardItem(
            "Emergency SOS",
            "Emergency quick support",
            "🚨"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F8FC))
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        /*
        --------------------------------
        TOP BAR
        --------------------------------
        */

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {

                Text(
                    text = "Welcome Back",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Arogya Sahaya Local",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Box {

                Card(
                    shape = RoundedCornerShape(50.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    )
                ) {
                    IconButton(
                        onClick = {
                            showMenu = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Profile Menu",
                            modifier = Modifier.size(38.dp)
                        )
                    }
                }

                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = {
                        showMenu = false
                    }
                ) {

                    DropdownMenuItem(
                        text = {
                            Text("My Profile")
                        },
                        onClick = {
                            showMenu = false
                            onProfileClick()
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null
                            )
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text("Update Password")
                        },
                        onClick = {
                            showMenu = false
                            onUpdatePasswordClick()
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null
                            )
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text("Logout")
                        },
                        onClick = {
                            showMenu = false
                            onLogoutClick()
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.ExitToApp,
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        /*
        --------------------------------
        PREMIUM TOP BANNER
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
                                Color(0xFF0F766E),
                                Color(0xFF14B8A6)
                            )
                        )
                    )
                    .padding(26.dp)
            ) {

                Column {

                    Text(
                        text = "Digital Health Companion",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Healthcare support for rural families, elderly users and medicine tracking",
                        color = Color.White,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = Color.White.copy(alpha = 0.20f)
                    ) {
                        Text(
                            text = "Zero-Error Health Monitoring",
                            color = Color.White,
                            modifier = Modifier.padding(
                                horizontal = 14.dp,
                                vertical = 8.dp
                            )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        /*
        --------------------------------
        QUICK ACCESS TITLE
        --------------------------------
        */

        Text(
            text = "Quick Access",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(18.dp))

        /*
        --------------------------------
        PROFESSIONAL GRID
        --------------------------------
        */

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.height(1120.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(items) { item ->

                PremiumDashboardCard(
                    title = item.title,
                    subtitle = item.subtitle,
                    emoji = item.emoji,
                    onClick = {

                        when (item.title) {

                            "Medical Profile" ->
                                onProfileClick()

                            "Add Medicine" ->
                                onAddMedicineClick()

                            "Medicine History" ->
                                onHistoryClick()

                            "Vital Log" ->
                                onVitalLogClick()

                            "Health Graph" ->
                                onGraphClick()

                            "ASHA Connect" ->
                                onAshaClick()

                            "Emergency SOS" ->
                                onSOSClick()
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun PremiumDashboardCard(
    title: String,
    subtitle: String,
    emoji: String,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(235.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column {

                Text(
                    text = emoji,
                    fontSize = 28.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    minLines = 2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = subtitle,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    minLines = 2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp
                )
            ) {
                Text(
                    text = "Open",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}