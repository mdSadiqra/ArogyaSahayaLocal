package com.sadiq.arogyasahayalocal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bloodtype
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sadiq.arogyasahayalocal.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicalProfileScreen(
    viewModel: ProfileViewModel,
    onBackClick: () -> Unit = {}
) {

    val savedProfile by viewModel.profileState.collectAsState()

    var fullName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var bloodGroup by remember { mutableStateOf("") }
    var chronicCondition by remember { mutableStateOf("") }
    var emergencyContact by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    var isEditMode by remember { mutableStateOf(true) }
    var message by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    LaunchedEffect(savedProfile) {
        savedProfile?.let {
            fullName = it.fullName
            age = it.age
            bloodGroup = it.bloodGroup
            chronicCondition = it.chronicCondition
            emergencyContact = it.emergencyContact
            address = it.address
            isEditMode = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Medical Profile",
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
                },

                actions = {
                    IconButton(
                        onClick = {
                            isEditMode = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit"
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
                .verticalScroll(rememberScrollState())
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
                                    Color(0xFF0F766E),
                                    Color(0xFF14B8A6)
                                )
                            )
                        )
                        .padding(24.dp)
                ) {

                    Column {

                        Text(
                            text = "Patient Healthcare Record",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = "Critical medical details for emergencies, treatment and fast response support",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            /*
            --------------------------------
            MAIN PROFILE CARD
            --------------------------------
            */

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {

                    if (isEditMode) {

                        PremiumInputField(
                            value = fullName,
                            onValueChange = {
                                fullName = it
                            },
                            label = "Full Name",
                            icon = Icons.Default.Person
                        )

                        PremiumInputField(
                            value = age,
                            onValueChange = {
                                age = it
                            },
                            label = "Age",
                            icon = Icons.Default.Person
                        )

                        PremiumInputField(
                            value = bloodGroup,
                            onValueChange = {
                                bloodGroup = it
                            },
                            label = "Blood Group",
                            icon = Icons.Default.Bloodtype
                        )

                        PremiumInputField(
                            value = chronicCondition,
                            onValueChange = {
                                chronicCondition = it
                            },
                            label = "Chronic Condition",
                            icon = Icons.Default.LocalHospital
                        )

                        PremiumInputField(
                            value = emergencyContact,
                            onValueChange = {
                                emergencyContact = it
                            },
                            label = "Emergency Contact",
                            icon = Icons.Default.Call
                        )

                        PremiumInputField(
                            value = address,
                            onValueChange = {
                                address = it
                            },
                            label = "Address",
                            icon = Icons.Default.Home
                        )

                        Spacer(
                            modifier = Modifier.height(24.dp)
                        )

                        Button(
                            onClick = {
                                if (
                                    fullName.isNotEmpty() &&
                                    age.isNotEmpty() &&
                                    bloodGroup.isNotEmpty()
                                ) {

                                    viewModel.saveProfile(
                                        fullName = fullName,
                                        age = age,
                                        bloodGroup = bloodGroup,
                                        chronicCondition = chronicCondition,
                                        emergencyContact = emergencyContact,
                                        address = address
                                    )

                                    message =
                                        "Medical profile saved successfully"

                                    isEditMode = false

                                } else {
                                    message =
                                        "Please fill required fields"
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(58.dp),
                            shape = RoundedCornerShape(18.dp)
                        ) {
                            Text(
                                text = "Save Medical Profile",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    else {

                        PremiumProfileItem(
                            title = "Full Name",
                            value = fullName,
                            icon = Icons.Default.Person
                        )

                        PremiumProfileItem(
                            title = "Age",
                            value = age,
                            icon = Icons.Default.Person
                        )

                        PremiumProfileItem(
                            title = "Blood Group",
                            value = bloodGroup,
                            icon = Icons.Default.Bloodtype
                        )

                        PremiumProfileItem(
                            title = "Chronic Condition",
                            value = chronicCondition,
                            icon = Icons.Default.LocalHospital
                        )

                        PremiumProfileItem(
                            title = "Emergency Contact",
                            value = emergencyContact,
                            icon = Icons.Default.Warning
                        )

                        PremiumProfileItem(
                            title = "Address",
                            value = address,
                            icon = Icons.Default.Home
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            if (message.isNotEmpty()) {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE8F5E9)
                    )
                ) {
                    Text(
                        text = message,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun PremiumInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(label)
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp),
        shape = RoundedCornerShape(18.dp),
        singleLine = true
    )
}

@Composable
fun PremiumProfileItem(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Surface(
            shape = CircleShape,
            color = Color(0xFFE6FFFA)
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
            modifier = Modifier.width(14.dp)
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
                text = if (value.isEmpty())
                    "Not Available"
                else
                    value,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }

    HorizontalDivider()
}