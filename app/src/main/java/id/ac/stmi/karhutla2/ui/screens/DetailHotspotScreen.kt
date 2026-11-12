package id.ac.stmi.karhutla2.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.stmi.karhutla2.R
import id.ac.stmi.karhutla2.data.DatabaseHelper

@Composable
fun DetailHotspotScreen(
    databaseHelper: DatabaseHelper,
    onNavigateBack: () -> Unit,
    onNavigateToRadar: () -> Unit,
    onNavigateToHotspot: () -> Unit,
    onNavigateToLapor: () -> Unit,
    onNavigateToAnalisis: () -> Unit
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .padding(bottom = 110.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Top Navigation Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF1B4B3B)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🔥", fontSize = 16.sp)
                    }
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(text = "Karhutla Hub", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
                            Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(alertRed))
                        }
                        Text(text = "Kalimantan Tengah", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B4B3B))
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFEAE8DE)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🔔", fontSize = 12.sp)
                    }
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF1B4B3B)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "👤", fontSize = 12.sp)
                    }
                }
            }

            // Sector Header Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onNavigateBack() },
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFEAE8DE))
                ) {
                    Text(text = "←", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "ZONA KARHUTLA", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF235D49))
                    Text(text = "Sektor A-4 Kahayan", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                }

                IconButton(
                    onClick = { /* Share */ },
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFEAE8DE))
                ) {
                    Text(text = "↗", fontSize = 14.sp)
                }
            }

            // Hero Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFEAE8DE))
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(190.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.gambar_karhutla),
                            contentDescription = "Sektor Kahayan Hilir",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        // Top Badges
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(50.dp),
                                color = Color.White.copy(alpha = 0.95f)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(text = "🔥", fontSize = 12.sp)
                                    Text(text = "FRP 182 MW", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                                    Text(text = "• Tingkat Kritis 96%", fontSize = 11.sp, color = Color.Gray)
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.95f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "🔖", fontSize = 14.sp)
                            }
                        }

                        // Bottom Badges
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomStart)
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(50.dp),
                                color = alertRed
                            ) {
                                Text(
                                    text = "🔴 SIAGA 1 EKSTREM",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                            Surface(
                                shape = RoundedCornerShape(50.dp),
                                color = Color.White.copy(alpha = 0.95f)
                            ) {
                                Text(
                                    text = "⏱️ Pembaruan 6 mnt lalu",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.DarkGray,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Sektor Kahayan Hilir, Blok C", fontWeight = FontWeight.Bold, fontSize = 17.sp, color = Color.Black)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            Text(text = "📍", fontSize = 12.sp)
                            Text(text = "Pulang Pisau, Kalimantan Tengah", fontSize = 11.5.sp, color = Color.Gray)
                        }
                    }
                }
            }

            // Coordination Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFEAE8DE))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFE4F2EC)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "🏢", fontSize = 12.sp)
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = "POSKO INDUK KOORDINASI", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                                Text(text = "Posko Manggala Agni Daops II Pulang Pisau", fontSize = 13.5.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                            }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Surface(
                            shape = RoundedCornerShape(50.dp),
                            color = Color(0xFFE4F2EC)
                        ) {
                            Text(
                                text = "SIAGA\nAKTIF",
                                fontSize = 9.5.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF235D49),
                                maxLines = 2,
                                softWrap = false,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F7F2)),
                            border = BorderStroke(1.dp, Color(0xFFEFECE1))
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = "🎯 Koordinat Hotspot", fontSize = 10.sp, color = Color.Gray)
                                Text(text = "-2.2531, 114.1205", fontSize = 12.5.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(top = 4.dp))
                                Text(text = "Radius dampak 3.8 km", fontSize = 9.5.sp, color = Color.Gray, modifier = Modifier.padding(top = 2.dp))
                            }
                        }

                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F7F2)),
                            border = BorderStroke(1.dp, Color(0xFFEFECE1))
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = "💨 Arah & Kecepatan Angin", fontSize = 10.sp, color = Color.Gray)
                                Text(text = "Tenggara 18 km/j", fontSize = 12.5.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(top = 4.dp))
                                Text(text = "Potensi sebar ke utara", fontSize = 9.5.sp, color = Color.Gray, modifier = Modifier.padding(top = 2.dp))
                            }
                        }
                    }
                }
            }

            // Sensor History Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFEAE8DE))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(text = "📈", fontSize = 14.sp)
                            Text(text = "Riwayat Sensor Berkala (AWS)", fontSize = 14.5.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        }
                        Surface(
                            shape = RoundedCornerShape(50.dp),
                            color = Color(0xFFE4F2EC)
                        ) {
                            Text(
                                text = "Interval 15 Menit",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF235D49),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    sensorLogs.forEach { log ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                border = BorderStroke(1.dp, Color(0xFFEAE8DE))
                            ) {
                                Column(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(text = log.time, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = alertRed)
                                    Text(text = "WIB", fontSize = 8.5.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                                }
                            }

                            Card(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F7F2)),
                                border = BorderStroke(1.dp, Color(0xFFEFECE1))
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                        Text(text = "🌡️", fontSize = 12.sp)
                                        Text(text = "TMA ${log.tma} | ${log.rh}", fontSize = 11.5.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                                    }
                                    Text(
                                        text = log.description,
                                        fontSize = 10.sp,
                                        color = Color.DarkGray,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Sticky Dispatch CTA
            Column(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        databaseHelper.insertDispatch("Sektor A-4", "Tim Reaksi Cepat Kahayan", java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault()).format(java.util.Date()))
                        Toast.makeText(context, "Regu Manggala Agni berhasil dikerahkan dan disimpan ke SQLite!", Toast.LENGTH_LONG).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryGreen)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(text = "🛡️", fontSize = 16.sp)
                        Text(
                            text = "Kerahkan Regu Pemadam Manggala Agni",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "🛡️ Terkoordinasi dengan Pusdalops BPBD Pulang Pisau", fontSize = 10.sp, color = Color.Gray)
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
                    onClick = { onNavigateToHotspot() },
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
