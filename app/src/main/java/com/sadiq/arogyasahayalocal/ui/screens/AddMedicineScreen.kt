package com.sadiq.arogyasahayalocal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sadiq.arogyasahayalocal.notification.AlarmScheduler
import com.sadiq.arogyasahayalocal.viewmodel.MedicineViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMedicineScreen(
    viewModel: MedicineViewModel,
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current

    var medicineName by remember { mutableStateOf("") }
    var dosage by remember { mutableStateOf("") }

    var morning by remember { mutableStateOf(false) }
    var afternoon by remember { mutableStateOf(false) }
    var night by remember { mutableStateOf(false) }

    var selectedHour by remember { mutableStateOf("08") }
    var selectedMinute by remember { mutableStateOf("00") }
    var selectedPeriod by remember { mutableStateOf("AM") }

    val hourOptions = (1..12).map {
        it.toString().padStart(2, '0')
    }

    val minuteOptions = (0..59).map {
        it.toString().padStart(2, '0')
    }

    val periodOptions = listOf("AM", "PM")

    var hourExpanded by remember { mutableStateOf(false) }
    var minuteExpanded by remember { mutableStateOf(false) }
    var periodExpanded by remember { mutableStateOf(false) }

    var successMessage by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add Medicine",
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
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(18.dp)
        ) {

            /*
            PREMIUM HEADER CARD
            */

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
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
                            text = "Smart Medicine Tracker",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Set powerful reminders with premium healthcare experience",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            /*
            MAIN FORM CARD
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

                    OutlinedTextField(
                        value = medicineName,
                        onValueChange = {
                            medicineName = it
                        },
                        label = {
                            Text("Medicine Name")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Medication,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(18.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = dosage,
                        onValueChange = {
                            dosage = it
                        },
                        label = {
                            Text("Dosage (Example: 1 Tablet)")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(18.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Reminder Schedule",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    PremiumCheckBox(
                        title = "Morning",
                        checked = morning,
                        onCheckedChange = {
                            morning = it
                        }
                    )

                    PremiumCheckBox(
                        title = "Afternoon",
                        checked = afternoon,
                        onCheckedChange = {
                            afternoon = it
                        }
                    )

                    PremiumCheckBox(
                        title = "Night",
                        checked = night,
                        onCheckedChange = {
                            night = it
                        }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Reminder Time",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        PremiumTimeDropdown(
                            label = "Hour",
                            value = selectedHour,
                            expanded = hourExpanded,
                            options = hourOptions,
                            onExpand = {
                                hourExpanded = !hourExpanded
                            },
                            onDismiss = {
                                hourExpanded = false
                            },
                            onSelect = {
                                selectedHour = it
                                hourExpanded = false
                            },
                            modifier = Modifier.weight(1f)
                        )

                        PremiumTimeDropdown(
                            label = "Minute",
                            value = selectedMinute,
                            expanded = minuteExpanded,
                            options = minuteOptions,
                            onExpand = {
                                minuteExpanded = !minuteExpanded
                            },
                            onDismiss = {
                                minuteExpanded = false
                            },
                            onSelect = {
                                selectedMinute = it
                                minuteExpanded = false
                            },
                            modifier = Modifier.weight(1f)
                        )

                        PremiumTimeDropdown(
                            label = "AM/PM",
                            value = selectedPeriod,
                            expanded = periodExpanded,
                            options = periodOptions,
                            onExpand = {
                                periodExpanded = !periodExpanded
                            },
                            onDismiss = {
                                periodExpanded = false
                            },
                            onSelect = {
                                selectedPeriod = it
                                periodExpanded = false
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    Button(
                        onClick = {

                            if (
                                medicineName.isNotEmpty() &&
                                dosage.isNotEmpty() &&
                                (morning || afternoon || night)
                            ) {

                                var finalHour = selectedHour.toInt()
                                val finalMinute = selectedMinute.toInt()

                                if (
                                    selectedPeriod == "PM" &&
                                    finalHour != 12
                                ) {
                                    finalHour += 12
                                }

                                if (
                                    selectedPeriod == "AM" &&
                                    finalHour == 12
                                ) {
                                    finalHour = 0
                                }

                                viewModel.addMedicine(
                                    medicineName = medicineName,
                                    dosage = dosage,
                                    morning = morning,
                                    afternoon = afternoon,
                                    night = night,
                                    reminderHour = selectedHour,
                                    reminderMinute = selectedMinute,
                                    reminderPeriod = selectedPeriod
                                )

                                AlarmScheduler.scheduleMedicineAlarm(
                                    context = context,
                                    medicineName = medicineName,
                                    hour = finalHour,
                                    minute = finalMinute,
                                    requestCode = medicineName.hashCode()
                                )

                                successMessage =
                                    "Medicine reminder saved successfully"

                                medicineName = ""
                                dosage = ""
                                morning = false
                                afternoon = false
                                night = false
                                selectedHour = "08"
                                selectedMinute = "00"
                                selectedPeriod = "AM"

                            } else {
                                successMessage =
                                    "Please complete all required fields"
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Save Medicine Reminder",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            if (successMessage.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE8F5E9)
                    )
                ) {
                    Text(
                        text = successMessage,
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
fun PremiumCheckBox(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor =
                if (checked)
                    Color(0xFFE0F7FA)
                else
                    Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 14.dp,
                    vertical = 10.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PremiumTimeDropdown(
    label: String,
    value: String,
    expanded: Boolean,
    options: List<String>,
    onExpand: () -> Unit,
    onDismiss: () -> Unit,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                onExpand()
            }
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(label)
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    onDismiss()
                }
            ) {
                options.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(item)
                        },
                        onClick = {
                            onSelect(item)
                        }
                    )
                }
            }
        }
    }
}