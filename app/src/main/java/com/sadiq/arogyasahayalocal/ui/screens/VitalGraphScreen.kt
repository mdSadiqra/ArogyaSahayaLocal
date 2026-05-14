package com.sadiq.arogyasahayalocal.ui.screens

import android.graphics.Color
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.sadiq.arogyasahayalocal.viewmodel.VitalLogViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VitalGraphScreen(
    viewModel: VitalLogViewModel,
    onBackClick: () -> Unit = {}
) {

    val vitalLogs = viewModel.vitalLogs.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Health Analytics",
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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(ComposeColor(0xFFF6F8FC))
                .padding(paddingValues)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            /*
            -------------------------
            PREMIUM HEADER
            -------------------------
            */

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = ComposeColor.Transparent
                    )
                ) {

                    Box(
                        modifier = Modifier
                            .background(
                                Brush.horizontalGradient(
                                    listOf(
                                        ComposeColor(0xFF0F766E),
                                        ComposeColor(0xFF14B8A6)
                                    )
                                )
                            )
                            .padding(26.dp)
                    ) {

                        Column {

                            Text(
                                text = "Health Trend Monitor",
                                color = ComposeColor.White,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "7-Day BP, Heart Rate and Glucose Analysis Dashboard",
                                color = ComposeColor.White,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }

            if (vitalLogs.value.isEmpty()) {

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(22.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp)
                        ) {

                            Text(
                                text = "No Health Records Found",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Please save BP, Heart Rate and Glucose data first to generate analytics.",
                                fontSize = 14.sp
                            )
                        }
                    }
                }

            } else {

                val latestLogs = vitalLogs.value.take(7)

                val bpEntries = latestLogs.mapIndexed { index, item ->
                    Entry(
                        (index + 1).toFloat(),
                        item.systolicBP.toFloat()
                    )
                }

                val heartEntries = latestLogs.mapIndexed { index, item ->
                    Entry(
                        (index + 1).toFloat(),
                        item.heartRate.toFloat()
                    )
                }

                val glucoseEntries = latestLogs.mapIndexed { index, item ->
                    Entry(
                        (index + 1).toFloat(),
                        item.glucoseLevel.toFloat()
                    )
                }

                /*
                -------------------------
                SUMMARY CARDS
                -------------------------
                */

                item {
                    HealthSummaryCard(
                        title = "Latest BP",
                        value = "${latestLogs.first().systolicBP} mmHg",
                        icon = "🩺"
                    )
                }

                item {
                    HealthSummaryCard(
                        title = "Latest Heart Rate",
                        value = "${latestLogs.first().heartRate} BPM",
                        icon = "❤️"
                    )
                }

                item {
                    HealthSummaryCard(
                        title = "Latest Glucose",
                        value = "${latestLogs.first().glucoseLevel} mg/dL",
                        icon = "🩸"
                    )
                }

                /*
                -------------------------
                GRAPHS
                -------------------------
                */

                item {
                    PremiumGraphCard(
                        title = "Blood Pressure Graph",
                        entries = bpEntries,
                        label = "BP Trend"
                    )
                }

                item {
                    PremiumGraphCard(
                        title = "Heart Rate Graph",
                        entries = heartEntries,
                        label = "Heart Rate Trend"
                    )
                }

                item {
                    PremiumGraphCard(
                        title = "Glucose Level Graph",
                        entries = glucoseEntries,
                        label = "Glucose Trend"
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun HealthSummaryCard(
    title: String,
    value: String,
    icon: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {

                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = ComposeColor.Gray
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = value,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = icon,
                fontSize = 30.sp
            )
        }
    }
}

@Composable
fun PremiumGraphCard(
    title: String,
    entries: List<Entry>,
    label: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(16.dp))

            AndroidView(
                factory = { context ->

                    val lineChart = LineChart(context)
                    val dataSet = LineDataSet(entries, label)

                    dataSet.color =
                        Color.rgb(15, 118, 110)

                    dataSet.valueTextColor =
                        Color.BLACK

                    dataSet.lineWidth = 3f
                    dataSet.circleRadius = 5f

                    dataSet.setCircleColor(
                        Color.rgb(20, 184, 166)
                    )

                    dataSet.valueTextSize = 10f

                    val lineData = LineData(dataSet)
                    lineChart.data = lineData

                    lineChart.description.text =
                        "7-Day Health Analytics"

                    lineChart.setTouchEnabled(false)
                    lineChart.setPinchZoom(false)
                    lineChart.isDragEnabled = false
                    lineChart.setScaleEnabled(false)

                    lineChart.animateX(1000)

                    val xAxis = lineChart.xAxis
                    xAxis.position =
                        XAxis.XAxisPosition.BOTTOM

                    xAxis.granularity = 1f

                    lineChart.axisRight.isEnabled = false
                    lineChart.legend.isEnabled = true

                    lineChart.layoutParams =
                        ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            550
                        )

                    lineChart.invalidate()
                    lineChart
                }
            )
        }
    }
}