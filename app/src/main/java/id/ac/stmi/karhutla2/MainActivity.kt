package id.ac.stmi.karhutla2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.stmi.karhutla2.data.DatabaseHelper
import id.ac.stmi.karhutla2.ui.screens.*
import id.ac.stmi.karhutla2.ui.theme.Karhutla2Theme

sealed class Screen {
    object Beranda : Screen()
    object DetailHotspot : Screen()
    object HotspotList : Screen()
    object Lapor : Screen()
    object Analisis : Screen()
}

class MainActivity : ComponentActivity() {
    private var databaseHelper: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var startupError by mutableStateOf<String?>(null)
        try {
            databaseHelper = DatabaseHelper(this)
        } catch (e: Exception) {
            startupError = "Init Error: ${e.message}\n${e.stackTraceToString()}"
        }

        setContent {
            Karhutla2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFD0DDD5)
                ) {
                    if (startupError != null) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = startupError!!,
                                color = Color.Red,
                                fontSize = 13.sp
                            )
                        }
                    } else if (databaseHelper != null) {
                        var currentScreen by remember { mutableStateOf<Screen>(Screen.Beranda) }

                        when (currentScreen) {
                            is Screen.Beranda -> BerandaScreen(
                                databaseHelper = databaseHelper!!,
                                onNavigateToDetail = { currentScreen = Screen.DetailHotspot },
                                onNavigateToHotspot = { currentScreen = Screen.HotspotList },
                                onNavigateToLapor = { currentScreen = Screen.Lapor },
                                onNavigateToAnalisis = { currentScreen = Screen.Analisis }
                            )
                            is Screen.DetailHotspot -> DetailHotspotScreen(
                                databaseHelper = databaseHelper!!,
                                onNavigateBack = { currentScreen = Screen.Beranda },
                                onNavigateToRadar = { currentScreen = Screen.Beranda },
                                onNavigateToHotspot = { currentScreen = Screen.HotspotList },
                                onNavigateToLapor = { currentScreen = Screen.Lapor },
                                onNavigateToAnalisis = { currentScreen = Screen.Analisis }
                            )
                            is Screen.HotspotList -> HotspotListScreen(
                                databaseHelper = databaseHelper!!,
                                onSelectHotspot = { _ -> currentScreen = Screen.DetailHotspot },
                                onNavigateToRadar = { currentScreen = Screen.Beranda },
                                onNavigateToLapor = { currentScreen = Screen.Lapor },
                                onNavigateToAnalisis = { currentScreen = Screen.Analisis }
                            )
                            is Screen.Lapor -> LaporScreen(
                                databaseHelper = databaseHelper!!,
                                onNavigateToRadar = { currentScreen = Screen.Beranda },
                                onNavigateToHotspot = { currentScreen = Screen.HotspotList },
                                onNavigateToAnalisis = { currentScreen = Screen.Analisis }
                            )
                            is Screen.Analisis -> AnalisisScreen(
                                databaseHelper = databaseHelper!!,
                                onNavigateToRadar = { currentScreen = Screen.Beranda },
                                onNavigateToHotspot = { currentScreen = Screen.HotspotList },
                                onNavigateToLapor = { currentScreen = Screen.Lapor }
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        databaseHelper?.close()
        super.onDestroy()
    }
}
