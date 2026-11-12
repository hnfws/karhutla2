package id.ac.stmi.karhutla2.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.stmi.karhutla2.R
import id.ac.stmi.karhutla2.data.DatabaseHelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun LaporScreen(
    databaseHelper: DatabaseHelper,
    onNavigateToRadar: () -> Unit,
    onNavigateToHotspot: () -> Unit,
    onNavigateToAnalisis: () -> Unit
) {
    val context = LocalContext.current
    var reporterName by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var statusSelection by remember { mutableStateOf("KRITIS") }
    var selectedImageRes by remember { mutableStateOf(R.drawable.gambar_karhutla) }
    var reportsList by remember { mutableStateOf(databaseHelper.getAllReports()) }

    val htmlBg = Color(0xFFFBFBF6)
    val htmlPrimary = Color(0xFF16382C)
    val htmlDanger = Color(0xFFE53E3E)
    val htmlBorder = Color(0xFFEAE8DE)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(htmlBg)
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
                                .background(htmlPrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "📢", fontSize = 16.sp)
                        }
                        Column {
                            Text(text = "Lapor Titik Karhutla", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                            Text(text = "Laporan Menambah Hotspot ke Peta Secara Dinamis", fontSize = 11.sp, color = Color.Gray)
                        }
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, htmlBorder)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(text = "Formulir Pelaporan Lapangan", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)

                        OutlinedTextField(
                            value = reporterName,
                            onValueChange = { reporterName = it },
                            label = { Text("Nama Pelapor / Petugas") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        OutlinedTextField(
                            value = location,
                            onValueChange = { location = it },
                            label = { Text("Lokasi Kejadian / Sektor (Contoh: Sebangau Hilir)") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(text = "Tingkat Kritis Hotspot", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                listOf("KRITIS", "WASPADA").forEach { status ->
                                    val isSelected = statusSelection == status
                                    Button(
                                        onClick = { statusSelection = status },
                                        shape = RoundedCornerShape(50.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = if (isSelected) htmlDanger else Color(0xFFF1F1F1)
                                        ),
                                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
                                    ) {
                                        Text(
                                            text = status,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (isSelected) Color.White else Color.DarkGray
                                        )
                                    }
                                }
                            }
                        }

                        OutlinedTextField(
                            value = description,
                            onValueChange = { description = it },
                            label = { Text("Keterangan & Kondisi Api di Lapangan") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp),
                            shape = RoundedCornerShape(12.dp),
                            maxLines = 4
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "Bukti Visual Lapangan (Pilih Foto)", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
                                TextButton(onClick = {
                                    selectedImageRes = if (selectedImageRes == R.drawable.gambar_karhutla) R.drawable.ic_launcher_background else R.drawable.gambar_karhutla
                                    Toast.makeText(context, "Foto bukti visual dipilih", Toast.LENGTH_SHORT).show()
                                }) {
                                    Text(text = "Ganti Foto 🔄", fontSize = 11.sp, color = htmlPrimary)
                                }
                            }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(110.dp)
                                    .clickable {
                                        selectedImageRes = if (selectedImageRes == R.drawable.gambar_karhutla) R.drawable.ic_launcher_background else R.drawable.gambar_karhutla
                                    },
                                shape = RoundedCornerShape(14.dp),
                                border = BorderStroke(2.dp, htmlPrimary.copy(alpha = 0.5f))
                            ) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Image(
                                        painter = painterResource(id = selectedImageRes),
                                        contentDescription = "Bukti Visual Lapangan",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                    Surface(
                                        modifier = Modifier
                                            .align(Alignment.BottomEnd)
                                            .padding(8.dp),
                                        shape = RoundedCornerShape(50.dp),
                                        color = Color.Black.copy(alpha = 0.75f)
                                    ) {
                                        Text(
                                            text = "📷 Ketuk untuk Ganti Foto Bukti",
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                        )
                                    }
                                }
                            }
                        }

                        Button(
                            onClick = {
                                if (reporterName.isNotBlank() && location.isNotBlank() && description.isNotBlank()) {
                                    val dateStr = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
                                    databaseHelper.insertReportAndHotspot(reporterName, location, description, dateStr, selectedImageRes, statusSelection)
                                    reportsList = databaseHelper.getAllReports()
                                    reporterName = ""
                                    location = ""
                                    description = ""
                                    Toast.makeText(context, "Laporan berhasil dikirim & Hotspot baru ditambahkan ke Peta!", Toast.LENGTH_LONG).show()
                                } else {
                                    Toast.makeText(context, "Mohon lengkapi semua kolom formulir", Toast.LENGTH_SHORT).show()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = htmlPrimary)
                        ) {
                            Text(text = "Kirim Laporan & Tambah Titik Hotspot", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Riwayat Laporan & Bukti Lapangan (${reportsList.size})",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            items(reportsList) { report ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, htmlBorder)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = report.reporterName, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = htmlPrimary)
                            Text(text = report.date, fontSize = 11.sp, color = Color.Gray)
                        }
                        Text(text = "📍 ${report.location}", fontSize = 11.5.sp, fontWeight = FontWeight.SemiBold, color = htmlDanger, modifier = Modifier.padding(top = 4.dp))
                        Text(text = report.description, fontSize = 11.sp, color = Color.DarkGray, modifier = Modifier.padding(top = 6.dp))

                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Image(
                                painter = painterResource(id = if (report.imageRes != 0) report.imageRes else R.drawable.gambar_karhutla),
                                contentDescription = "Bukti Visual Lapangan",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
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

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = htmlPrimary),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(text = "📢 Lapor", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
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
