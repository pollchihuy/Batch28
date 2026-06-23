# Modul 1 — Pengenalan UiPath Studio

**Level:** Pemula  
**Durasi:** ~2 jam  
**Prasyarat:** UiPath Studio Community sudah terinstall

---

## Daftar Isi

1. [Apa itu UiPath?](#1-apa-itu-uipath)
2. [Mengenal Tampilan UiPath Studio](#2-mengenal-tampilan-uipath-studio)
3. [Jenis-Jenis Project](#3-jenis-jenis-project)
4. [Konsep Dasar](#4-konsep-dasar)
5. [Praktikum 1 — Hello World](#5-praktikum-1--hello-world)
6. [Praktikum 2 — Input & Variable](#6-praktikum-2--input--variable)
7. [Praktikum 3 — Kondisi If/Else](#7-praktikum-3--kondisi-ifelse)
8. [Rangkuman](#8-rangkuman)

---

## 1. Apa itu UiPath?

UiPath adalah platform **RPA (Robotic Process Automation)** — software yang memungkinkan kita membuat "robot" virtual yang dapat meniru aksi manusia di komputer secara otomatis.

### Perbandingan dengan Tools yang Sudah Kamu Kenal

| Aspek | Selenium | UiPath |
|---|---|---|
| Target | Web browser | Web, Desktop, SAP, Excel, Email |
| Cara membuat | Menulis code (Java/Python) | Visual drag-and-drop |
| File yang dihasilkan | `.java` / `.py` | `.xaml` |
| Dijalankan via | Terminal / Maven | Studio / UiPath Robot |
| Bisa disimpan di Git | Ya | Ya |

### Tiga Komponen Utama

```
┌─────────────────────────────────────────────────┐
│                                                 │
│   UiPath Studio  →  Tempat membuat automation   │
│   UiPath Robot   →  Yang menjalankan automation │
│   Orchestrator   →  Pusat kontrol & monitoring  │
│                                                 │
└─────────────────────────────────────────────────┘
```

> **Catatan:** Untuk belajar, kamu hanya butuh UiPath Studio. Robot sudah include di dalamnya.

---

## 2. Mengenal Tampilan UiPath Studio

Setelah membuka UiPath Studio dan membuat project baru, kamu akan melihat tampilan seperti ini:

```
┌──────────────┬─────────────────────────────┬──────────────┐
│              │                             │              │
│  ACTIVITIES  │      DESIGN CANVAS          │  PROPERTIES  │
│  (Panel kiri)│      (Area tengah)          │  (Panel kanan│
│              │                             │              │
│  Cari dan    │  Tempat drag-drop activity  │  Setting     │
│  drag        │  dan melihat flow           │  detail      │
│  activity    │  automation                 │  activity    │
│              │                             │              │
├──────────────┴─────────────────────────────┴──────────────┤
│                    OUTPUT / LOG PANEL                      │
│              Hasil run dan pesan log                       │
└────────────────────────────────────────────────────────────┘
```

### Panel-Panel Penting

| Panel | Lokasi | Fungsi |
|---|---|---|
| **Activities** | Kiri | Library semua activity yang bisa dipakai |
| **Design Canvas** | Tengah | Tempat kamu drag-drop dan susun workflow |
| **Properties** | Kanan | Setting detail untuk activity yang dipilih |
| **Variables** | Bawah canvas | Kelola variabel dalam workflow |
| **Output** | Bawah | Lihat hasil log saat menjalankan automation |
| **Project** | Kiri atas | Struktur file project |

### Toolbar Utama

```
▶ Run       → Jalankan automation dari awal
⏸ Pause     → Pause saat running
⏹ Stop      → Hentikan automation
🐛 Debug     → Jalankan mode debug (step-by-step)
```

---

## 3. Jenis-Jenis Project

Saat membuat project baru, ada beberapa pilihan:

### Process
> Project standar untuk automation bisnis umum

Digunakan untuk: otomasi input data, scraping, integrasi sistem

### Test Automation *(Paling Relevan untuk QA)*
> Project khusus untuk membuat automated test

Digunakan untuk: regression testing, functional testing, UI testing

### Library
> Kumpulan workflow yang bisa dipakai ulang di project lain

Digunakan untuk: membuat komponen reusable (mirip utility class di Java)

---

## 4. Konsep Dasar

### 4.1 Activity

Unit terkecil pekerjaan di UiPath. Setiap kotak yang kamu drag adalah satu activity.

**Contoh Activity Umum:**

| Activity | Fungsi | Analog di Selenium |
|---|---|---|
| `Open Browser` | Buka browser | `driver.get()` |
| `Click` | Klik elemen | `element.click()` |
| `Type Into` | Ketik teks | `element.sendKeys()` |
| `Get Text` | Ambil teks elemen | `element.getText()` |
| `Close Browser` | Tutup browser | `driver.quit()` |
| `Message Box` | Tampilkan popup | - |
| `Log Message` | Tulis ke log | `System.out.println()` |
| `Assign` | Assign nilai ke variabel | `String x = "value"` |

### 4.2 Sequence vs Flowchart

**Sequence** — langkah linear dari atas ke bawah (paling sering dipakai)
```
[Step 1]
   ↓
[Step 2]
   ↓
[Step 3]
```

**Flowchart** — ada percabangan visual
```
        [Start]
           ↓
       [Kondisi?]
      ↙          ↘
   [Ya]          [Tidak]
    ↓               ↓
 [Aksi A]       [Aksi B]
```

### 4.3 Variable

Menyimpan data sementara selama workflow berjalan.

| Tipe | Contoh Nilai | Analog Java |
|---|---|---|
| `String` | `"Hello"` | `String` |
| `Int32` | `42` | `int` |
| `Boolean` | `True / False` | `boolean` |
| `Double` | `3.14` | `double` |
| `DataTable` | Tabel data | `List<Map>` |

**Cara membuat variable:**
1. Klik panel **Variables** di bawah canvas
2. Klik **Create Variable**
3. Isi nama, tipe, dan scope

### 4.4 Selector

Cara UiPath mengenali elemen UI — mirip XPath/CSS Selector di Selenium.

```xml
<!-- Contoh selector untuk tombol Login -->
<wnd app='chrome.exe' title='Swag Labs' />
<ctrl name='Login' role='push button' />
```

**UiPath punya UI Explorer** untuk membantu membuat selector secara visual — kamu tinggal hover ke elemen dan klik.

---

## 5. Praktikum 1 — Hello World

### Tujuan
Membuat automation pertama: menampilkan pesan "Hello World"

### Langkah-Langkah

**Step 1: Buat Project Baru**
1. Buka UiPath Studio
2. Klik **Process** di bagian New Project
3. Isi nama: `HelloWorld`
4. Klik **Create**

**Step 2: Buka Main.xaml**
- Di panel Project (kiri), double-click `Main.xaml`
- Canvas akan terbuka, sudah ada container **Sequence** kosong

**Step 3: Tambah Activity Message Box**
1. Di panel **Activities** (kiri), ketik `Message Box` di kotak pencarian
2. Drag activity **Message Box** ke dalam Sequence di canvas
3. Di panel **Properties** (kanan), isi field **Text**:
   ```
   "Hello World! Ini automation pertama saya."
   ```
   > Perhatikan: teks string harus diapit tanda kutip `" "`

**Step 4: Jalankan**
1. Tekan tombol **▶ Run** di toolbar (atau F5)
2. Akan muncul popup dengan teks "Hello World! Ini automation pertama saya."
3. Klik OK

**Hasil yang Diharapkan:**
```
┌────────────────────────────────────────┐
│  Hello World! Ini automation pertama   │
│  saya.                                 │
│                                        │
│              [ OK ]                    │
└────────────────────────────────────────┘
```

---

## 6. Praktikum 2 — Input & Variable

### Tujuan
Belajar menggunakan Input Dialog dan menyimpan hasilnya ke Variable

### Langkah-Langkah

**Step 1: Buat Project Baru**
- Nama: `InputVariable`

**Step 2: Buat Variable**
1. Klik tab **Variables** di bawah canvas
2. Klik **Create Variable**
3. Isi:
   - Name: `namaUser`
   - Variable Type: `String`
   - Scope: `Sequence`

**Step 3: Tambah Activity Input Dialog**
1. Cari `Input Dialog` di panel Activities
2. Drag ke canvas
3. Di Properties, isi:
   - **Title:** `"Input Nama"`
   - **Label:** `"Masukkan nama kamu:"`
   - **Result:** `namaUser` *(pilih dari dropdown variable)*

**Step 4: Tambah Activity Message Box**
1. Drag **Message Box** di bawah Input Dialog
2. Di Properties, isi **Text**:
   ```
   "Halo, " + namaUser + "! Selamat datang di UiPath."
   ```

**Step 5: Jalankan**
1. Tekan **▶ Run**
2. Muncul dialog → ketik nama kamu → klik OK
3. Muncul popup sapaan

**Hasil yang Diharapkan:**
```
Dialog 1:               Dialog 2:
┌──────────────┐        ┌──────────────────────────┐
│ Input Nama   │        │ Halo, Budi! Selamat       │
│              │   →    │ datang di UiPath.         │
│ Nama: [Budi] │        │                           │
│    [ OK ]    │        │         [ OK ]            │
└──────────────┘        └──────────────────────────┘
```

---

## 7. Praktikum 3 — Kondisi If/Else

### Tujuan
Belajar menggunakan activity If untuk membuat percabangan logika

### Skenario
User input angka → robot cek apakah angka tersebut genap atau ganjil

### Langkah-Langkah

**Step 1: Buat Project Baru**
- Nama: `GenapGanjil`

**Step 2: Buat Variable**

| Nama | Tipe | Scope |
|---|---|---|
| `inputAngka` | `String` | Sequence |
| `angka` | `Int32` | Sequence |

**Step 3: Susun Activity**

```
[Sequence]
   ├── Input Dialog
   │     Title: "Cek Angka"
   │     Label: "Masukkan angka:"
   │     Result: inputAngka
   │
   ├── Assign
   │     To: angka
   │     Value: Convert.ToInt32(inputAngka)
   │
   └── If
         Condition: angka Mod 2 = 0
         ├── Then: Message Box → "Angka " + angka.ToString() + " adalah GENAP"
         └── Else: Message Box → "Angka " + angka.ToString() + " adalah GANJIL"
```

**Cara tambah activity If:**
1. Cari `If` di Activities panel
2. Drag ke canvas
3. Di field **Condition** isi: `angka Mod 2 = 0`
4. Di area **Then** drag Message Box, isi pesannya
5. Di area **Else** drag Message Box, isi pesannya

**Step 4: Jalankan dan Test**

| Input | Output yang Diharapkan |
|---|---|
| `4` | "Angka 4 adalah GENAP" |
| `7` | "Angka 7 adalah GANJIL" |
| `0` | "Angka 0 adalah GENAP" |

---

## 8. Rangkuman

| Konsep | Yang Dipelajari |
|---|---|
| **UiPath Studio** | IDE visual untuk membuat automation |
| **Activity** | Unit terkecil aksi (drag-drop ke canvas) |
| **Sequence** | Container untuk langkah-langkah linear |
| **Variable** | Menyimpan data sementara |
| **Message Box** | Menampilkan popup pesan |
| **Input Dialog** | Menerima input dari user |
| **Assign** | Memberikan nilai ke variabel |
| **If** | Percabangan kondisi |

### Checklist Sebelum Lanjut ke Modul Berikutnya

- [ ] Berhasil menjalankan Praktikum 1 (Hello World)
- [ ] Berhasil menjalankan Praktikum 2 (Input & Variable)
- [ ] Berhasil menjalankan Praktikum 3 (If/Else)
- [ ] Memahami perbedaan Activity, Variable, dan Sequence
- [ ] Bisa mencari Activity di panel Activities

---

**Modul Selanjutnya:** [Modul 2 — Web Testing dengan SauceDemo](testing-web.md)
