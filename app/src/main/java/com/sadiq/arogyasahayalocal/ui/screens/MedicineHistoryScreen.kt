package com.sadiq.arogyasahayalocal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Notifications
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
import com.sadiq.arogyasahayalocal.data.MedicineEntity
import com.sadiq.arogyasahayalocal.viewmodel.MedicineViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineHistoryScreen(
    viewModel: MedicineViewModel,
    onBackClick: () -> Unit = {}
) {

    val medicineList = viewModel.medicines.collectAsState()

    /*
    --------------------------------
    EDIT DIALOG STATES
    --------------------------------
    */

    var showEditDialog by remember {
        mutableStateOf(false)
    }

    var selectedMedicine by remember {
        mutableStateOf<MedicineEntity?>(null)
    }

    var editMedicineName by remember {
        mutableStateOf("")
    }

    var editDosage by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Medicine History",
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
                .padding(paddingValues)
                .padding(18.dp)
        ) {

            /*
            --------------------------------
            TOP HEADER CARD
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
                                    Color(0xFF6D28D9),
                                    Color(0xFF8B5CF6)
                                )
                            )
                        )
                        .padding(24.dp)
                ) {
                    Column {
                        Text(
                            text = "Saved Medicines",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )

                        Text(
                            text = "Manage medicine reminders beautifully",
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
            EMPTY STATE
            --------------------------------
            */

            if (medicineList.value.isEmpty()) {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(28.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No Medicines Added Yet",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = "Add your first medicine reminder",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }

            } else {

                /*
                --------------------------------
                MEDICINE LIST
                --------------------------------
                */

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    items(medicineList.value) { medicine ->

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

                                /*
                                TOP ROW
                                */

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Surface(
                                            shape = CircleShape,
                                            color = Color(0xFFEDE9FE)
                                        ) {
                                            Box(
                                                modifier = Modifier.padding(10.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Medication,
                                                    contentDescription = null
                                                )
                                            }
                                        }

                                        Spacer(
                                            modifier = Modifier.width(12.dp)
                                        )

                                        Column {
                                            Text(
                                                text = medicine.medicineName,
                                                fontSize = 19.sp,
                                                fontWeight = FontWeight.Bold
                                            )

                                            Text(
                                                text = medicine.dosage,
                                                fontSize = 13.sp,
                                                color = Color.Gray
                                            )
                                        }
                                    }

                                    /*
                                    ACTION BUTTONS
                                    */

                                    Row {

                                        /*
                                        EDIT BUTTON
                                        */

                                        IconButton(
                                            onClick = {
                                                selectedMedicine = medicine
                                                editMedicineName =
                                                    medicine.medicineName
                                                editDosage =
                                                    medicine.dosage
                                                showEditDialog = true
                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Edit,
                                                contentDescription = "Edit"
                                            )
                                        }

                                        /*
                                        DELETE BUTTON
                                        */

                                        IconButton(
                                            onClick = {
                                                viewModel.deleteMedicine(
                                                    medicine
                                                )
                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = "Delete"
                                            )
                                        }
                                    }
                                }

                                Spacer(
                                    modifier = Modifier.height(20.dp)
                                )

                                /*
                                REMINDER SCHEDULE
                                */

                                PremiumInfoRow(
                                    icon = Icons.Default.Notifications,
                                    title = "Reminder Schedule",
                                    value = buildString {

                                        if (medicine.morning)
                                            append("Morning ")

                                        if (medicine.afternoon)
                                            append("Afternoon ")

                                        if (medicine.night)
                                            append("Night")

                                    }.ifEmpty {
                                        "No Reminder Selected"
                                    }
                                )

                                Spacer(
                                    modifier = Modifier.height(14.dp)
                                )

                                /*
                                REMINDER TIME
                                */

                                PremiumInfoRow(
                                    icon = Icons.Default.AccessTime,
                                    title = "Reminder Time",
                                    value =
                                        "${medicine.reminderHour}:${medicine.reminderMinute} ${medicine.reminderPeriod}"
                                )
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

            /*
            --------------------------------
            FULL POPUP EDIT DIALOG
            --------------------------------
            */

            if (
                showEditDialog &&
                selectedMedicine != null
            ) {

                AlertDialog(
                    onDismissRequest = {
                        showEditDialog = false
                    },

                    title = {
                        Text(
                            text = "Edit Medicine",
                            fontWeight = FontWeight.Bold
                        )
                    },

                    text = {
                        Column {

                            OutlinedTextField(
                                value = editMedicineName,
                                onValueChange = {
                                    editMedicineName = it
                                },
                                label = {
                                    Text("Medicine Name")
                                },
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            OutlinedTextField(
                                value = editDosage,
                                onValueChange = {
                                    editDosage = it
                                },
                                label = {
                                    Text("Dosage")
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    },

                    confirmButton = {
                        Button(
                            onClick = {
                                selectedMedicine?.let {

                                    viewModel.updateMedicine(
                                        it.copy(
                                            medicineName = editMedicineName,
                                            dosage = editDosage
                                        )
                                    )
                                }

                                showEditDialog = false
                            }
                        ) {
                            Text("Save")
                        }
                    },

                    dismissButton = {
                        TextButton(
                            onClick = {
                                showEditDialog = false
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun PremiumInfoRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Surface(
            shape = CircleShape,
            color = Color(0xFFF3F4F6)
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