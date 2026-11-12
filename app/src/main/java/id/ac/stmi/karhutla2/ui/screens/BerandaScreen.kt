package id.ac.stmi.karhutla2.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.stmi.karhutla2.R
import id.ac.stmi.karhutla2.data.DatabaseHelper

@Composable
fun BerandaScreen(
    databaseHelper: DatabaseHelper,
    onNavigateToDetail: () -> Unit,
    onNavigateToHotspot: () -> Unit,
    onNavigateToLapor: () -> Unit,
    onNavigateToAnalisis: () -> Unit
) {
    val scrollState = rememberScrollState()
    val hotspots = remember { databaseHelper.getAllHotspots() }
    val primaryGreen = Color(0xFF132820)
    val bgGreen = Color(0xFFD0DDD5)
    val cardBg = Color(0xFFFBF9F4)
    val alertRed = Color(0xFFE14B32)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgGreen)
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
            // Main Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_sort_by_size),
                        contentDescription = "Menu",
                        tint = Color.DarkGray,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "Karhutla Hub",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(alertRed)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.padding(top = 2.dp)
                    ) {
                        Text(
                            text = "Kalimantan Tengah",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF2A5243)
                        )
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(alertRed)
                                .align(Alignment.TopEnd)
                                .offset(x = (-6).dp, y = 6.dp)
                        )
                        Text(text = "🔔", fontSize = 16.sp)
                    }
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White.copy(alpha = 0.7f))
                            .border(1.dp, Color(0xFFB8CAC0), RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "👤", fontSize = 16.sp)
                    }
                }
            }

            // Hero Banner Image
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(144.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.gambar_karhutla),
                    contentDescription = "Karhutla Banner",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // Alert Status Box
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = cardBg)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFFFEDED)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "⚠️", fontSize = 18.sp)
                        }
                        Text(
                            text = "Siaga II — Waspada Karhutla",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider(color = Color(0xFFEDE7DC))
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(text = "📉", fontSize = 14.sp)
                            Text(text = "Tinggi Muka Air:", fontSize = 12.sp, color = Color.Gray)
                            Text(text = "-48 cm", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(text = "🔥", fontSize = 14.sp)
                            Text(text = "Panas Aktif:", fontSize = 12.sp, color = Color.Gray)
                            Text(text = "14 Titik", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = alertRed)
                        }
                    }
                }
            }

            // Filter Chips Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(50.dp),
                    color = Color.White,
                    shadowElevation = 1.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(text = "⚡", fontSize = 12.sp)
                        Text(text = "Semua Sektor (${hotspots.size})", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                    }
                }

                Surface(
                    shape = RoundedCornerShape(50.dp),
                    color = Color(0xFFFFEDED),
                    border = BorderStroke(1.dp, Color(0xFFF5C4B8))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(alertRed)
                        )
                        Text(text = "Hotspot Kritis (3)", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = alertRed)
                    }
                }
            }

            // Situation Map Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigateToDetail() },
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = cardBg)
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
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .border(1.5.dp, Color.DarkGray, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "⊕", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                            Text(text = "Peta Situasi", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                        }

                        Surface(
                            shape = RoundedCornerShape(50.dp),
                            color = Color.White,
                            border = BorderStroke(1.dp, Color(0xFFE5E5E5))
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFF235D49))
                                )
                                Text(text = "Siaran Langsung", fontSize = 11.sp, color = Color.DarkGray)
                            }
                        }
                    }

                    // Status Pill Bar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF162720))
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(alertRed))
                            Text(text = "Sektor A-4", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                        }
                        Text(text = "68°C KRITIS", color = alertRed, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    // Interactive Topographic Map Container matching HTML exactly
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFE3ECE5))
                            .border(1.dp, Color(0xFFD5E1D8), RoundedCornerShape(16.dp))
                    ) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val w = size.width
                            val h = size.height

                            drawOval(
                                color = Color(0xFFCADBCF),
                                topLeft = androidx.compose.ui.geometry.Offset(w * 0.12f, h * 0.15f),
                                size = androidx.compose.ui.geometry.Size(w * 0.76f, h * 0.7f)
                            )
                            drawOval(
                                color = Color(0xFFBDD2C3),
                                topLeft = androidx.compose.ui.geometry.Offset(w * 0.22f, h * 0.25f),
                                size = androidx.compose.ui.geometry.Size(w * 0.56f, h * 0.5f)
                            )
                            drawOval(
                                color = Color(0xFFAFC7B6),
                                topLeft = androidx.compose.ui.geometry.Offset(w * 0.32f, h * 0.35f),
                                size = androidx.compose.ui.geometry.Size(w * 0.36f, h * 0.3f)
                            )

                            val waterPath = Path().apply {
                                moveTo(w * 0.15f, h * 0.45f)
                                cubicTo(w * 0.35f, h * 0.45f, w * 0.5f, h * 0.65f, w * 0.85f, h * 0.5f)
                            }
                            drawPath(
                                path = waterPath,
                                color = Color(0xFF9BB8CD),
                                style = Stroke(width = 10f, cap = StrokeCap.Round)
                            )

                            val routePath = Path().apply {
                                moveTo(w * 0.25f, h * 0.75f)
                                cubicTo(w * 0.4f, h * 0.55f, w * 0.55f, h * 0.55f, w * 0.75f, h * 0.35f)
                            }
                            drawPath(
                                path = routePath,
                                color = Color(0xFFE14B32),
                                style = Stroke(
                                    width = 6f,
                                    cap = StrokeCap.Round,
                                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 10f), 0f)
                                )
                            )
                        }

                        Text(text = "▲", fontSize = 11.sp, color = Color(0xFF6D8A74), modifier = Modifier.align(Alignment.TopStart).padding(top = 35.dp, start = 45.dp))
                        Text(text = "▲", fontSize = 11.sp, color = Color(0xFF6D8A74), modifier = Modifier.align(Alignment.BottomStart).padding(bottom = 45.dp, start = 90.dp))
                        Text(text = "▲", fontSize = 11.sp, color = Color(0xFF6D8A74), modifier = Modifier.align(Alignment.TopEnd).padding(top = 45.dp, end = 55.dp))

                        Surface(
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.TopStart),
                            shape = RoundedCornerShape(50.dp),
                            color = Color.White.copy(alpha = 0.9f)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(text = "🛣️ Jarak Tempuh: 12,4 km • Akses Kanal", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                            }
                        }

                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(top = 35.dp, end = 75.dp)
                                .size(26.dp)
                                .clip(CircleShape)
                                .background(alertRed.copy(alpha = 0.3f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .clip(CircleShape)
                                    .background(alertRed),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "🔥", fontSize = 9.sp)
                            }
                        }

                        Surface(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(y = 10.dp),
                            shape = RoundedCornerShape(50.dp),
                            color = Color.White.copy(alpha = 0.95f),
                            shadowElevation = 4.dp
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(alertRed))
                                Text(text = "⏱️ Estimasi Tiba: 28 menit", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomStart)
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(50.dp),
                                color = Color.White.copy(alpha = 0.9f)
                            ) {
                                Text(
                                    text = "📍 Pos Komando Daops II",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                    color = Color.Black
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "U", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
                            }
                        }
                    }

                    // Sector Details Text
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Sektor A-4 • Sebangau Hilir",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color.Black
                            )
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = Color(0xFFFFEDED),
                                border = BorderStroke(1.dp, Color(0xFFFBD4CB))
                            ) {
                                Text(
                                    text = "✱ KRITIS",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Black,
                                    color = alertRed,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Text(
                            text = "Kabupaten Pulang Pisau • Kedalaman Gambut: 4,2 m",
                            fontSize = 12.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }

                    // 2x2 Telemetry Cards
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Card(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF4F7F4))
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(Color(0xFFFFEDED)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = "🌡️", fontSize = 14.sp)
                                    }
                                    Text(text = "64,8°C", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = alertRed)
                                }
                            }
                            Card(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF4F7F4))
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(Color(0xFFE6F4EA)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = "💧", fontSize = 14.sp)
                                    }
                                    Text(text = "24%", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFFB56727))
                                }
                            }
                        }

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Card(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF4F7F4))
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(Color(0xFFE0F2F1)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = "💨", fontSize = 14.sp)
                                    }
                                    Column {
                                        Text(text = "Kecepatan", fontSize = 10.sp, color = Color.Gray)
                                        Text(text = "18 km/jam", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                                    }
                                }
                            }
                            Card(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF4F7F4))
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(Color(0xFFFFEDED)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = "🌫️", fontSize = 14.sp)
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                        Text(text = "182", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = alertRed)
                                        Text(text = "KRITIS", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = alertRed)
                                    }
                                }
                            }
                        }
                    }

                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Ambang Batas Muka Air Gambut", fontSize = 10.sp, color = Color.Gray)
                            Text(text = "-48 cm (Kritis)", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = alertRed)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        LinearProgressIndicator(
                            progress = { 0.72f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(50.dp)),
                            color = alertRed,
                            trackColor = Color(0xFFE5ECE7)
                        )
                    }
                }
            }

            // Action Button: Pantau Sektor & Disposisi Tim Patroli
            Button(
                onClick = { onNavigateToDetail() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryGreen)
            ) {
                Text(text = "Pantau Sektor & Disposisi Tim Patroli ➔", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            // Team Section Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = cardBg)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .border(1.dp, Color.DarkGray, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "⚙️", fontSize = 12.sp)
                        }
                        Text(text = "Regu Manggala Agni Terdekat", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Color(0xFFE5E5E5))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color(0xFFE3ECE5)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "04", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF235D49))
                                }
                                Column {
                                    Text(text = "Tim Reaksi Cepat Kahayan", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.Black)
                                    Text(text = "6 Personel • 2 Pompa Apung • 4.8 km", fontSize = 10.sp, color = Color.Gray)
                                }
                            }

                            IconButton(
                                onClick = { /* Call action */ },
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFF1F1F1))
                            ) {
                                Text(text = "📞", fontSize = 14.sp)
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
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryGreen),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(text = "📡 Radar", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
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
