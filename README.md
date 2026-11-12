# 🔥 Karhutla Hub — Aplikasi Pemantauan & Pelaporan Kebakaran Hutan dan Lahan

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-green.svg" alt="Platform Android">
  <img src="https://img.shields.io/badge/Framework-Jetpack%20Compose-blue.svg" alt="Jetpack Compose">
  <img src="https://img.shields.io/badge/Database-SQLite-orange.svg" alt="SQLite">
  <img src="https://img.shields.io/badge/Language-Kotlin-purple.svg" alt="Kotlin">
</p>

**Karhutla Hub** adalah aplikasi mobile Android modern yang dibangun menggunakan **Jetpack Compose** dan **SQLite** untuk pemantauan, analisis, serta pelaporan titik panas (*hotspot*) dan kebakaran hutan dan lahan (Karhutla), khususnya di wilayah Kalimantan Tengah.

---

## 📑 Daftar Isi
1. [Fitur Utama](#-fitur-utama)
2. [Arsitektur & Skema Database SQLite](#-arsitektur--skema-database-sqlite)
3. [Struktur Proyek](#-struktur-proyek)
4. [Panduan Instalasi & Menjalankan Aplikasi](#-panduan-instalasi--menjalankan-aplikasi)
5. [Pemeriksaan Database (App Inspection)](#-pemeriksaan-database-app-inspection)

---

## 🚀 Fitur Utama

### 1. 📡 Beranda & Peta Situasi (Radar)
- **Status Siaga & Metrik Utama**: Menampilkan tingkat siaga (*Siaga II — Waspada Karhutla*), tinggi muka air gambut (`-48 cm`), dan jumlah titik panas aktif (`14 Titik`).
- **Peta Situasi Interaktif**: Representasi visual topografi gambut dengan kanal air, jalur patroli regu (*dashed route*), ikon hotspot menyala (*pulse*), estimasi waktu tiba (*28 menit*), serta titik Pos Komando Daops II.
- **Kartu Telemetri 2x2**: Pemantauan suhu permukaan (`64,8°C`), kelembaban gambut (`24%`), kecepatan angin (`18 km/jam`), dan kadar CO2/asap (`182 Kritis`).
- **Regu Manggala Agni Terdekat**: Informasi regu pemadam terdekat (*Tim Reaksi Cepat Kahayan*) lengkap dengan jumlah personel, pompa apung, jarak, dan tombol panggil cepat.

### 2. 🔥 Detail Hotspot & Sektor Kritis
- **Zona Karhutla Sektor A-4**: Menampilkan FRP (`182 MW`), tingkat kritis (`96%`), status *Siaga 1 Ekstrem*, koordinat GPS (`-2.2531, 114.1205`), serta arah & kecepatan angin (*Tenggara 18 km/j*).
- **Riwayat Sensor AWS (Interval 15 Menit)**: Log sensor berkala yang mencatat suhu kanopi dan penyebaran api di kanal drainase sekunder.
- **Disposisi Tim Pemadam**: Tombol interaktif *"Kerahkan Regu Pemadam Manggala Agni"* yang mencatat riwayat pengerahan tim secara instan ke database SQLite.

### 3. 📢 Lapor Titik Karhutla (Dinamis & Bukti Visual)
- **Formulir Pelaporan Lapangan**: Memungkinkan petugas atau warga melaporkan kejadian karhutla dengan memasukkan nama pelapor, lokasi sektor, tingkat kritis (*Kritis / Waspada*), dan keterangan kejadian.
- **Bukti Visual Lapangan**: Pengguna dapat memilih/mengganti foto bukti visual lapangan dari aset aplikasi yang akan terlampir pada laporan.
- **Hotspot Dinamis**: Setiap laporan baru yang dikirimkan tidak hanya disimpan ke tabel laporan (`reports`), tetapi juga **secara otomatis menambahkan titik hotspot baru ke peta & daftar hotspot** secara *real-time*.

### 4. 📊 Analisis & Riwayat Operasional
- Ringkasan rekapitulasi data log sensor AWS dan daftar riwayat pengerahan disposisi tim pemadam.

---

## 🗄️ Arsitektur & Skema Database SQLite

Aplikasi menggunakan database lokal SQLite (`karhutla.db`) melalui `SQLiteOpenHelper` dengan 4 tabel utama:

| Nama Tabel | Deskripsi | Kolom Utama |
| :--- | :--- | :--- |
| **`hotspots`** | Menyimpan daftar sektor & titik panas aktif | `id`, `title`, `region`, `sector_code`, `temperature`, `humidity`, `wind_speed`, `co2`, `water_level`, `status`, `description`, `frp`, `critical_percentage`, `lat_lng`, `image_res` |
| **`sensor_logs`** | Menyimpan riwayat sensor AWS berkala | `id`, `time`, `tma`, `rh`, `description` |
| **`reports`** | Menyimpan laporan pelaporan warga & bukti visual | `id`, `reporter_name`, `location`, `description`, `date`, `image_res` |
| **`dispatches`** | Menyimpan riwayat pengerahan regu pemadam | `id`, `sector_code`, `team_name`, `timestamp` |

---

## 📂 Struktur Proyek

```text
id.ac.stmi.karhutla2/
│
├── data/
│   └── DatabaseHelper.kt       # Pengelola SQLite (OpenHelper, Seed Data, Queries)
│
├── ui/
│   ├── screens/
│   │   ├── BerandaScreen.kt    # Halaman Beranda / Radar & Peta Situasi
│   │   ├── DetailHotspotScreen.kt # Halaman Detail Sektor A-4 & Disposisi
│   │   ├── HotspotListScreen.kt   # Halaman Daftar Hotspot Aktif
│   │   ├── LaporScreen.kt      # Halaman Lapor & Bukti Visual Lapangan
│   │   └── AnalisisScreen.kt   # Halaman Analisis & Riwayat Disposisi
│   │
│   └── theme/
│       ├── Color.kt
│       ├── Theme.kt            # Palet warna terang (Light Theme konsisten)
│       └── Type.kt
│
└── MainActivity.kt             # Navigasi utama & inisialisasi database
```

---

## 📱 Panduan Instalasi & Menjalankan Aplikasi

1. **Clone Repositori**:
   ```bash
   git clone https://github.com/hnfws/karhutla2.git
   ```
2. **Buka di Android Studio**: Buka folder proyek menggunakan **Android Studio**.
3. **Sinkronisasi Gradle**: Tunggu hingga proses *Gradle Sync* selesai.
4. **Jalankan Aplikasi**: Sambungkan emulator atau perangkat fisik Android, lalu klik tombol **Run** (segitiga hijau).

---

## 🔍 Pemeriksaan Database (App Inspection)

Untuk melihat dan menguji data SQLite secara langsung saat aplikasi berjalan:
1. Di Android Studio, buka **View -> Tool Windows -> App Inspection**.
2. Pilih tab **Database Inspector**.
3. Pilih proses aplikasi `id.ac.stmi.karhutla2`.
4. Anda dapat melihat, memantau, dan menguji tabel (`hotspots`, `reports`, `sensor_logs`, `dispatches`) secara *real-time*.
