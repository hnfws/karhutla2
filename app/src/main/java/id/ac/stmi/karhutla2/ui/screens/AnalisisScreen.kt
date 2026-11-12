package id.ac.stmi.karhutla2.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.stmi.karhutla2.data.DatabaseHelper

@Composable
fun AnalisisScreen(
    databaseHelper: DatabaseHelper,
    onNavigateToRadar: () -> Unit,
    onNavigateToHotspot: () -> Unit,
    onNavigateToLapor: () -> Unit
) {
    val dispatches = remember { databaseHelper.getAllDispatches() }
    val sensorLogs = remember { databaseHelper.getAllSensorLogs() }
    val primaryGreen = Color(0xFF16382C)
    val bgCream = Color(0xFFFBFBF6)
    val alertRed = Color(0xFFE53E3E)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgCream)
            .statusBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .padding(bottom = 110.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(primaryGreen),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "📊", fontSize = 16.sp)
                        }
                        Column {
                            Text(text = "Analisis & Riwayat Disposisi", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                            Text(text = "Data operasional tersimpan di SQLite", fontSize = 11.sp, color = Color.Gray)
                        }
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color(0xFFEAE8DE))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(text = "Ringkasan Status Karhutla", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Column {
                                Text(text = "Total Log Sensor", fontSize = 11.sp, color = Color.Gray)
                                Text(text = "${sensorLogs.size} Rekaman", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = primaryGreen)
                            }
                            Column {
                                Text(text = "Total Disposisi Tim", fontSize = 11.sp, color = Color.Gray)
                                Text(text = "${dispatches.size} Pengerahan", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = alertRed)
                            }
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Riwayat Pengerahan Tim Manggala Agni (${dispatches.size})",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            if (dispatches.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Color(0xFFEAE8DE))
                    ) {
                        Text(
                            text = "Belum ada tim yang dikerahkan. Tekan tombol pengerahan pada Detail Hotspot untuk mencatat ke SQLite.",
                            fontSize = 12.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            } else {
                items(dispatches) { dispatch ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Color(0xFFEAE8DE))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = dispatch.teamName, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = primaryGreen)
                                Text(text = dispatch.timestamp, fontSize = 11.sp, color = Color.Gray)
                            }
                            Text(text = "🎯 Target: ${dispatch.sectorCode}", fontSize = 11.5.sp, fontWeight = FontWeight.SemiBold, color = alertRed, modifier = Modifier.padding(top = 4.dp))
                        }
                    }
                }
            }
        }

        // Bottom Navigation Bar with navigation bars padding
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50.dp),
            color = Color.White.copy(alpha = 0.95f),
            shadowElevation = 8.dp,
            border = BorderStroke(1.dp, Color(0xFFE5E5E5))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { onNavigateToRadar() }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "📡", fontSize = 14.sp)
                        Text(text = "Radar", fontSize = 9.sp, color = Color.Gray)
                    }
                }

                TextButton(onClick = { onNavigateToHotspot() }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "🔥", fontSize = 14.sp)
                        Text(text = "Hotspot", fontSize = 9.sp, color = Color.Gray)
                    }
                }

                TextButton(onClick = { onNavigateToLapor() }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "📢", fontSize = 14.sp)
                        Text(text = "Lapor", fontSize = 9.sp, color = Color.Gray)
                    }
                }

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryGreen),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(text = "📊 Analisis", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }
}
