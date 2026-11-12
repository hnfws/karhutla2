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
fun HotspotListScreen(
    databaseHelper: DatabaseHelper,
    onSelectHotspot: (Int) -> Unit,
    onNavigateToRadar: () -> Unit,
    onNavigateToLapor: () -> Unit,
    onNavigateToAnalisis: () -> Unit
) {
    val hotspots = remember { databaseHelper.getAllHotspots() }
    val primaryGreen = Color(0xFF16382C)
    val bgCream = Color(0xFFFBFBF6)
    val alertRed = Color(0xFFE53E3E)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgCream)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .padding(bottom = 90.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header
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
                        Text(text = "🔥", fontSize = 16.sp)
                    }
                    Column {
                        Text(text = "Daftar Hotspot Aktif", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                        Text(text = "Data tersimpan di SQLite", fontSize = 11.sp, color = Color.Gray)
                    }
                }
            }

            // List of Hotspots
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(hotspots) { hotspot ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Color(0xFFEAE8DE)),
                        onClick = { onSelectHotspot(hotspot.id) }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .clip(CircleShape)
                                            .background(Color(0xFFFFEDED)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = "🔥", fontSize = 12.sp)
                                    }
                                    Text(text = hotspot.sectorCode, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = primaryGreen)
                                }
                                Surface(
                                    shape = RoundedCornerShape(50.dp),
                                    color = if (hotspot.status == "KRITIS") alertRed else Color(0xFFB56727)
                                ) {
                                    Text(
                                        text = hotspot.status,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = hotspot.title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                            Text(text = hotspot.description, fontSize = 11.sp, color = Color.Gray, modifier = Modifier.padding(top = 2.dp))

                            Spacer(modifier = Modifier.height(10.dp))
                            HorizontalDivider(color = Color(0xFFF0EFE6))
                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Suhu: ${hotspot.temp}", fontSize = 11.sp, color = alertRed, fontWeight = FontWeight.Bold)
                                Text(text = "Kelembaban: ${hotspot.humidity}", fontSize = 11.sp, color = Color(0xFFB56727), fontWeight = FontWeight.Bold)
                                Text(text = "TMA: ${hotspot.waterLevel}", fontSize = 11.sp, color = Color.DarkGray, fontWeight = FontWeight.Bold)
                            }
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

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryGreen),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(text = "🔥 Hotspot", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }

                TextButton(onClick = { onNavigateToLapor() }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "📢", fontSize = 14.sp)
                        Text(text = "Lapor", fontSize = 9.sp, color = Color.Gray)
                    }
                }

                TextButton(onClick = { onNavigateToAnalisis() }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "📊", fontSize = 14.sp)
                        Text(text = "Analisis", fontSize = 9.sp, color = Color.Gray)
                    }
                }
            }
        }
    }
}
