package id.ac.stmi.karhutla2.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import id.ac.stmi.karhutla2.R

data class HotspotItem(
    val id: Int,
    val title: String,
    val region: String,
    val sectorCode: String,
    val temp: String,
    val humidity: String,
    val windSpeed: String,
    val co2: String,
    val waterLevel: String,
    val status: String,
    val description: String,
    val frp: String,
    val criticalPercentage: String,
    val latLng: String,
    val imageRes: Int
)

data class SensorLogItem(
    val id: Int,
    val time: String,
    val tma: String,
    val rh: String,
    val description: String
)

data class ReportItem(
    val id: Int,
    val reporterName: String,
    val location: String,
    val description: String,
    val date: String,
    val imageRes: Int
)

data class DispatchItem(
    val id: Int,
    val sectorCode: String,
    val teamName: String,
    val timestamp: String
)

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val TAG = "DatabaseHelper"
        private const val DATABASE_NAME = "karhutla.db"
        private const val DATABASE_VERSION = 6

        const val TABLE_HOTSPOTS = "hotspots"
        const val TABLE_SENSOR_LOGS = "sensor_logs"
        const val TABLE_REPORTS = "reports"
        const val TABLE_DISPATCHES = "dispatches"
    }

    override fun onCreate(db: SQLiteDatabase) {
        try {
            db.execSQL("""
                CREATE TABLE $TABLE_HOTSPOTS (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT,
                    region TEXT,
                    sector_code TEXT,
                    temperature TEXT,
                    humidity TEXT,
                    wind_speed TEXT,
                    co2 TEXT,
                    water_level TEXT,
                    status TEXT,
                    description TEXT,
                    frp TEXT,
                    critical_percentage TEXT,
                    lat_lng TEXT,
                    image_res INTEGER
                )
            """)

            db.execSQL("""
                CREATE TABLE $TABLE_SENSOR_LOGS (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    time TEXT,
                    tma TEXT,
                    rh TEXT,
                    description TEXT
                )
            """)

            db.execSQL("""
                CREATE TABLE $TABLE_REPORTS (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    reporter_name TEXT,
                    location TEXT,
                    description TEXT,
                    date TEXT,
                    image_res INTEGER
                )
            """)

            db.execSQL("""
                CREATE TABLE $TABLE_DISPATCHES (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    sector_code TEXT,
                    team_name TEXT,
                    timestamp TEXT
                )
            """)

            seedInitialData(db)
        } catch (e: Exception) {
            Log.e(TAG, "Error in onCreate", e)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        try {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_HOTSPOTS")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_SENSOR_LOGS")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_REPORTS")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_DISPATCHES")
            onCreate(db)
        } catch (e: Exception) {
            Log.e(TAG, "Error in onUpgrade", e)
        }
    }

    private fun seedInitialData(db: SQLiteDatabase) {
        try {
            val h1 = ContentValues().apply {
                put("title", "Sektor Kahayan Hilir, Blok C")
                put("region", "Kalimantan Tengah • Pulang Pisau")
                put("sector_code", "Sektor A-4")
                put("temperature", "64,8°C")
                put("humidity", "24%")
                put("wind_speed", "18 km/jam")
                put("co2", "182")
                put("water_level", "-48 cm")
                put("status", "KRITIS")
                put("description", "Kabupaten Pulang Pisau • Kedalaman Gambut: 4,2 m")
                put("frp", "182 MW")
                put("critical_percentage", "96%")
                put("lat_lng", "-2.2531, 114.1205")
                put("image_res", R.drawable.gambar_karhutla)
            }
            db.insert(TABLE_HOTSPOTS, null, h1)

            val h2 = ContentValues().apply {
                put("title", "Sektor Sebangau Hulu")
                put("region", "Kalimantan Tengah • Palangka Raya")
                put("sector_code", "Sektor B-2")
                put("temperature", "58,2°C")
                put("humidity", "31%")
                put("wind_speed", "14 km/jam")
                put("co2", "145")
                put("water_level", "-35 cm")
                put("status", "WASPADA")
                put("description", "Kota Palangka Raya • Kedalaman Gambut: 3,5 m")
                put("frp", "135 MW")
                put("critical_percentage", "78%")
                put("lat_lng", "-2.1245, 113.9123")
                put("image_res", R.drawable.gambar_karhutla)
            }
            db.insert(TABLE_HOTSPOTS, null, h2)

            val h3 = ContentValues().apply {
                put("title", "Sektor Kapuas Murung")
                put("region", "Kalimantan Tengah • Kapuas")
                put("sector_code", "Sektor C-1")
                put("temperature", "61,5°C")
                put("humidity", "27%")
                put("wind_speed", "16 km/jam")
                put("co2", "160")
                put("water_level", "-42 cm")
                put("status", "KRITIS")
                put("description", "Kabupaten Kapuas • Kedalaman Gambut: 3,8 m")
                put("frp", "150 MW")
                put("critical_percentage", "88%")
                put("lat_lng", "-2.9812, 114.3910")
                put("image_res", R.drawable.gambar_karhutla)
            }
            db.insert(TABLE_HOTSPOTS, null, h3)

            val log1 = ContentValues().apply {
                put("time", "09:30")
                put("tma", "-48 cm")
                put("rh", "24% RH Gambut")
                put("description", "Suhu kanopi 36.4°C, kecepatan angin 18 km/j ke arah barat laut. Titik api menyebar di kanal drainase sekunder.")
            }
            db.insert(TABLE_SENSOR_LOGS, null, log1)

            val log2 = ContentValues().apply {
                put("time", "09:15")
                put("tma", "-47 cm")
                put("rh", "25% RH Gambut")
                put("description", "Peningkatan suhu permukaan gambut terdeteksi oleh sensor AWS sektor.")
            }
            db.insert(TABLE_SENSOR_LOGS, null, log2)

            val r1 = ContentValues().apply {
                put("reporter_name", "Budi Santoso (Manggala Agni)")
                put("location", "Sektor A-4 Sebangau Hilir")
                put("description", "Terpantau kepulan asap pekat di area kanal sekunder, tim langsung bergerak.")
                put("date", "12/11/2026 09:00")
                put("image_res", R.drawable.gambar_karhutla)
            }
            db.insert(TABLE_REPORTS, null, r1)

            val d1 = ContentValues().apply {
                put("sector_code", "Sektor A-4")
                put("team_name", "Tim Reaksi Cepat Kahayan")
                put("timestamp", "12/11/2026 09:10")
            }
            db.insert(TABLE_DISPATCHES, null, d1)
        } catch (e: Exception) {
            Log.e(TAG, "Error in seedInitialData", e)
        }
    }

    fun getAllHotspots(): List<HotspotItem> {
        val list = mutableListOf<HotspotItem>()
        try {
            val db = readableDatabase
            val cursor = db.rawQuery("SELECT * FROM $TABLE_HOTSPOTS ORDER BY id DESC", null)
            if (cursor.moveToFirst()) {
                do {
                    list.add(
                        HotspotItem(
                            id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                            title = cursor.getString(cursor.getColumnIndexOrThrow("title")),
                            region = cursor.getString(cursor.getColumnIndexOrThrow("region")),
                            sectorCode = cursor.getString(cursor.getColumnIndexOrThrow("sector_code")),
                            temp = cursor.getString(cursor.getColumnIndexOrThrow("temperature")),
                            humidity = cursor.getString(cursor.getColumnIndexOrThrow("humidity")),
                            windSpeed = cursor.getString(cursor.getColumnIndexOrThrow("wind_speed")),
                            co2 = cursor.getString(cursor.getColumnIndexOrThrow("co2")),
                            waterLevel = cursor.getString(cursor.getColumnIndexOrThrow("water_level")),
                            status = cursor.getString(cursor.getColumnIndexOrThrow("status")),
                            description = cursor.getString(cursor.getColumnIndexOrThrow("description")),
                            frp = cursor.getString(cursor.getColumnIndexOrThrow("frp")),
                            criticalPercentage = cursor.getString(cursor.getColumnIndexOrThrow("critical_percentage")),
                            latLng = cursor.getString(cursor.getColumnIndexOrThrow("lat_lng")),
                            imageRes = cursor.getInt(cursor.getColumnIndexOrThrow("image_res"))
                        )
                    )
                } while (cursor.moveToNext())
            }
            cursor.close()
        } catch (e: Exception) {
            Log.e(TAG, "Error in getAllHotspots", e)
        }
        return list
    }

    fun getAllSensorLogs(): List<SensorLogItem> {
        val list = mutableListOf<SensorLogItem>()
        try {
            val db = readableDatabase
            val cursor = db.rawQuery("SELECT * FROM $TABLE_SENSOR_LOGS ORDER BY id DESC", null)
            if (cursor.moveToFirst()) {
                do {
                    list.add(
                        SensorLogItem(
                            id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                            time = cursor.getString(cursor.getColumnIndexOrThrow("time")),
                            tma = cursor.getString(cursor.getColumnIndexOrThrow("tma")),
                            rh = cursor.getString(cursor.getColumnIndexOrThrow("rh")),
                            description = cursor.getString(cursor.getColumnIndexOrThrow("description"))
                        )
                    )
                } while (cursor.moveToNext())
            }
            cursor.close()
        } catch (e: Exception) {
            Log.e(TAG, "Error in getAllSensorLogs", e)
        }
        return list
    }

    fun insertReportAndHotspot(reporterName: String, location: String, description: String, date: String, imageRes: Int, status: String) {
        try {
            val db = writableDatabase
            val rValues = ContentValues().apply {
                put("reporter_name", reporterName)
                put("location", location)
                put("description", description)
                put("date", date)
                put("image_res", imageRes)
            }
            db.insert(TABLE_REPORTS, null, rValues)

            val hValues = ContentValues().apply {
                put("title", "Sektor $location")
                put("region", "Kalimantan Tengah • $location")
                put("sector_code", "Sektor Baru")
                put("temperature", "63,5°C")
                put("humidity", "25%")
                put("wind_speed", "17 km/jam")
                put("co2", "178")
                put("water_level", "-46 cm")
                put("status", status)
                put("description", description)
                put("frp", "165 MW")
                put("critical_percentage", "92%")
                put("lat_lng", "-2.3500, 114.1500")
                put("image_res", imageRes)
            }
            db.insert(TABLE_HOTSPOTS, null, hValues)
        } catch (e: Exception) {
            Log.e(TAG, "Error in insertReportAndHotspot", e)
        }
    }

    fun getAllReports(): List<ReportItem> {
        val list = mutableListOf<ReportItem>()
        try {
            val db = readableDatabase
            val cursor = db.rawQuery("SELECT * FROM $TABLE_REPORTS ORDER BY id DESC", null)
            if (cursor.moveToFirst()) {
                do {
                    list.add(
                        ReportItem(
                            id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                            reporterName = cursor.getString(cursor.getColumnIndexOrThrow("reporter_name")),
                            location = cursor.getString(cursor.getColumnIndexOrThrow("location")),
                            description = cursor.getString(cursor.getColumnIndexOrThrow("description")),
                            date = cursor.getString(cursor.getColumnIndexOrThrow("date")),
                            imageRes = cursor.getInt(cursor.getColumnIndexOrThrow("image_res"))
                        )
                    )
                } while (cursor.moveToNext())
            }
            cursor.close()
        } catch (e: Exception) {
            Log.e(TAG, "Error in getAllReports", e)
        }
        return list
    }

    fun insertDispatch(sectorCode: String, teamName: String, timestamp: String): Long {
        var id = -1L
        try {
            val db = writableDatabase
            val values = ContentValues().apply {
                put("sector_code", sectorCode)
                put("team_name", teamName)
                put("timestamp", timestamp)
            }
            id = db.insert(TABLE_DISPATCHES, null, values)
        } catch (e: Exception) {
            Log.e(TAG, "Error in insertDispatch", e)
        }
        return id
    }

    fun getAllDispatches(): List<DispatchItem> {
        val list = mutableListOf<DispatchItem>()
        try {
            val db = readableDatabase
            val cursor = db.rawQuery("SELECT * FROM $TABLE_DISPATCHES ORDER BY id DESC", null)
            if (cursor.moveToFirst()) {
                do {
                    list.add(
                        DispatchItem(
                            id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                            sectorCode = cursor.getString(cursor.getColumnIndexOrThrow("sector_code")),
                            teamName = cursor.getString(cursor.getColumnIndexOrThrow("team_name")),
                            timestamp = cursor.getString(cursor.getColumnIndexOrThrow("timestamp"))
                        )
                    )
                } while (cursor.moveToNext())
            }
            cursor.close()
        } catch (e: Exception) {
            Log.e(TAG, "Error in getAllDispatches", e)
        }
        return list
    }
}
