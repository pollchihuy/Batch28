# Modul 2 — Web Testing dengan UiPath & SauceDemo

**Level:** Pemula  
**Durasi:** ~3 jam  
**Prasyarat:** Modul 1 (pengenalan.md) sudah selesai  
**Aplikasi Target:** https://www.saucedemo.com/

---

## Daftar Isi

1. [Mengenal SauceDemo](#1-mengenal-saucedemo)
2. [Setup Project Test Automation](#2-setup-project-test-automation)
3. [Mengenal Activity Web Automation](#3-mengenal-activity-web-automation)
4. [Praktikum 1 — Login Berhasil](#4-praktikum-1--login-berhasil)
5. [Praktikum 2 — Login Gagal (Negative Test)](#5-praktikum-2--login-gagal-negative-test)
6. [Praktikum 3 — Add to Cart](#6-praktikum-3--add-to-cart)
7. [Praktikum 4 — Checkout Flow](#7-praktikum-4--checkout-flow)
8. [Praktikum 5 — Data Driven Testing](#8-praktikum-5--data-driven-testing)
9. [Rangkuman & Best Practice](#9-rangkuman--best-practice)

---

## 1. Mengenal SauceDemo

SauceDemo (`https://www.saucedemo.com/`) adalah aplikasi web dummy khusus untuk latihan automation testing.

### Akun yang Tersedia

| Username | Password | Kondisi |
|---|---|---|
| `standard_user` | `secret_sauce` | User normal — semua fitur berjalan |
| `locked_out_user` | `secret_sauce` | Akun terkunci — tidak bisa login |
| `problem_user` | `secret_sauce` | Ada bug di beberapa fitur |
| `performance_glitch_user` | `secret_sauce` | Login lambat disengaja |
| `error_user` | `secret_sauce` | Beberapa aksi throw error |

> Kita akan pakai `standard_user` untuk happy path dan `locked_out_user` untuk negative test.

### Flow Aplikasi

```
[Login Page]
     ↓
[Products Page]  →  [Add to Cart]
     ↓
[Cart Page]      →  [Checkout]
     ↓
[Checkout: Info] →  [Checkout: Overview] →  [Order Complete]
```

---

## 2. Setup Project Test Automation

### Step 1: Buat Project Baru

1. Buka UiPath Studio
2. Klik **Test Automation**
3. Isi detail:
   - **Name:** `SauceDemoTesting`
   - **Location:** pilih folder yang kamu inginkan
   - **Language:** VB
4. Klik **Create**

### Step 2: Buka Project Panel

Panel Project **tidak otomatis tampil** di UiPath Studio 2026. Cara membukanya:

```
Cara 1 (Keyboard): Ctrl + Alt + P
Cara 2 (Menu)    : Klik View (di toolbar atas) → Project
Cara 3 (Icon)    : Klik ikon folder kecil di sisi kiri Studio
```

Setelah Project panel terbuka, akan tampil seperti ini di sisi kiri:

```
📁 SauceDemoTesting        ← nama project
   📄 Main.xaml
   📄 project.json
```

### Step 3: Buat Struktur Folder

Target struktur yang ingin dibuat:

```
SauceDemoTesting/
├── Main.xaml                  ← sudah ada, jangan dihapus
├── TestCases/
│   ├── TC01_LoginBerhasil.xaml
│   ├── TC02_LoginGagal.xaml
│   ├── TC03_AddToCart.xaml
│   └── TC04_Checkout.xaml
├── Workflows/
│   └── WF_Login.xaml
└── Data/
    └── TestData.xlsx
```

**Cara membuat folder `TestCases`:**
1. Di Project panel, **kanan klik** nama project `SauceDemoTesting`
2. Pilih **Add** → **New Folder**
3. Ketik nama: `TestCases` → Enter

Ulangi untuk membuat folder `Workflows` dan `Data`.

**Cara membuat file .xaml baru di dalam folder:**
1. **Kanan klik** folder `TestCases`
2. Pilih **Add** → **Sequence**
3. Ketik nama: `TC01_LoginBerhasil` → Create

> Jika menu **Add** tidak muncul saat kanan klik, pastikan kamu klik tepat pada nama folder, bukan area kosong di bawahnya.

### Step 4: Install Package UiPath.UIAutomation

Pastikan package web automation sudah ada:
1. Klik menu **Manage Packages** (ikon kotak di toolbar)
2. Di panel kiri, klik **All Packages** (bukan Project Dependencies — package tidak akan ditemukan jika kategori salah)
3. Ketik `UiPath.UIAutomation.Activities` di kotak pencarian
4. Pilih package **UiPath.UIAutomation.Activities** by UiPath yang berstatus **Pending install**
5. Klik **Save** untuk menginstall

> **Catatan:** Package ini berlabel "Prerelease" di Studio 2026 — itu normal, tetap bisa digunakan.

---

## 3. Mengenal Activity Web Automation

### Activity Utama yang Akan Dipakai

| Activity | Fungsi | Contoh Penggunaan |
|---|---|---|
| `Use Application/Browser` | Container untuk semua aksi browser | Wrap semua aksi web di dalamnya |
| `Click` | Klik elemen | Klik tombol Login |
| `Type Into` | Ketik teks ke input field | Isi username/password |
| `Get Text` | Ambil teks dari elemen | Ambil pesan error |
| `Verify Expression` | Assert / validasi | Cek teks sama dengan yang diharapkan |
| `Check App State` | Cek kondisi aplikasi | Cek elemen ada/tidak ada |
| `Select Item` | Pilih dari dropdown | Pilih item di select box |

### Cara Indicate Element (Menunjuk Elemen)

Saat kamu drag activity `Click` atau `Type Into`, akan muncul tombol **Indicate Element** di Properties.

```
Langkah-langkah:
1. Klik tombol "Indicate Element" / "Indicate on Screen"
2. Browser akan otomatis muncul di depan
3. Arahkan mouse ke elemen yang ingin ditunjuk
4. Elemen akan di-highlight dengan kotak biru
5. Klik elemen tersebut
6. Selector otomatis terisi
```

> Ini mirip seperti Inspect Element di browser, tapi UiPath yang otomatis generate selector-nya.

---

## 4. Praktikum 1 — Login Berhasil

### Tujuan
Otomasi skenario login dengan kredensial valid dan verifikasi berhasil masuk.

### Test Case

```
Test Case ID : TC01
Judul        : Login dengan kredensial valid
Precondition : Browser Chrome sudah terinstall
Steps        :
  1. Buka https://www.saucedemo.com/
  2. Isi username: standard_user
  3. Isi password: secret_sauce
  4. Klik tombol Login
Expected     : Halaman Products tampil, URL berubah ke /inventory.html
```

---

### Langkah A — Konfigurasi "Use Application/Browser"

Kamu sudah drag activity **Use Application/Browser** ke canvas. Sekarang perlu mengisi URL-nya.

**Cara membuka Properties panel:**
- Tekan **Escape** dulu untuk tutup menu kanan klik yang muncul
- Klik **satu kali** pada kotak activity `Use Application/Browser` di canvas untuk memilihnya
- Tekan **F4** — panel Properties akan muncul di sisi **kanan** Studio

Tampilan Properties panel di kanan:
```
┌─────────────────────────────┐
│ PROPERTIES                  │
├─────────────────────────────┤
│ Display Name                │
│ Use Application/Browser     │
├─────────────────────────────┤
│ Input                       │
│ Browser URL  [____________] │  ← isi di sini
│ BrowserType  [Chrome     ▼] │  ← pilih Chrome
├─────────────────────────────┤
│ Options                     │
│ ...                         │
└─────────────────────────────┘
```

**Isi field berikut di Properties:**
- **Browser URL** → klik kotak kosong di sampingnya → ketik:
  ```
  "https://www.saucedemo.com/"
  ```
  > Perhatikan: harus pakai tanda kutip `" "` di awal dan akhir
- **BrowserType** → klik dropdown → pilih **Chrome**

---

### Langkah B — Tambah Activity "Type Into" untuk Username

Activity `Type Into` harus diletakkan **di dalam** kotak `Use Application/Browser`, bukan di luar.

**Cara menambahkan:**
1. Di panel **Activities** (kiri), ketik `Type Into` di kotak pencarian
2. Drag activity `Type Into` → lepas **di dalam** area putih kotak `Use Application/Browser`

Setelah di-drag, tampilan canvas akan seperti ini:
```
┌─── Use Application/Browser ──────────────┐
│                                           │
│  ┌─── Type Into ──────────────────────┐  │
│  │  Indicate Element  [target: ...]   │  │
│  │  Text              [____________]  │  │
│  └────────────────────────────────────┘  │
│                                           │
└───────────────────────────────────────────┘
```

**Cara indicate elemen username:**
1. Klik activity `Type Into` di canvas untuk memilihnya
2. Di dalam kotak activity, klik tombol **"Indicate Element"** (ikon target/crosshair)
   > Atau tekan **F4** → di Properties cari field **Target** → klik ikon target di sampingnya
3. Studio akan meminimize, browser Chrome akan terbuka otomatis
4. Arahkan mouse ke **field username** di halaman SauceDemo
5. Field akan di-highlight dengan kotak **oranye/biru**
6. **Klik** field username tersebut
7. Studio akan kembali tampil — selector terisi otomatis

**Isi teks username:**
- Klik activity `Type Into` → tekan F4 → di Properties cari **Text**
- Klik kotak di samping **Text** → ketik:
  ```
  "standard_user"
  ```

---

### Langkah C — Tambah Activity "Type Into" untuk Password

Ulangi proses yang sama seperti Langkah B:
1. Drag `Type Into` kedua ke dalam `Use Application/Browser`, letakkan **di bawah** Type Into pertama
2. Indicate element → klik field **password** di SauceDemo
3. Di Properties → **Text** → isi:
   ```
   "secret_sauce"
   ```

---

### Langkah D — Tambah Activity "Click" untuk Tombol Login

1. Drag activity `Click` ke dalam `Use Application/Browser`, letakkan di bawah Type Into password
2. Klik activity `Click` → klik tombol **"Indicate Element"**
3. Browser terbuka → klik tombol **LOGIN** di halaman SauceDemo
4. Studio kembali — selector tombol login terisi otomatis

---

### Langkah E — Tambah Verifikasi dengan Check App State

> Jangan pakai `Verify Expression` dengan `browser.Url` — properti itu tidak tersedia di UiPath.
> Gunakan `Check App State` untuk memverifikasi elemen pada halaman target.

1. Cari `Check App State` di Activities panel → drag ke dalam Do, letakkan setelah Click
2. Klik tombol **"Indicate Element"** di dalam activity
3. **Login dulu secara manual** di Chrome sampai masuk halaman Products
4. Klik elemen heading **"Products"** di halaman inventory
5. Kembali ke Studio → di dropdown kondisi pilih **"Appear"**
6. Di field **"Error message"** ketik:
   ```
   "TC01 GAGAL: Halaman Products tidak tampil setelah login"
   ```

Hasil akhir canvas akan seperti ini:
```
┌─── Use Application/Browser (saucedemo.com) ───┐
│                                                │
│  ┌── Type Into ──────────────────────────┐    │
│  │  Target: field username               │    │
│  │  Text: "standard_user"                │    │
│  └───────────────────────────────────────┘    │
│                    ↓                           │
│  ┌── Type Into ──────────────────────────┐    │
│  │  Target: field password               │    │
│  │  Text: "secret_sauce"                 │    │
│  └───────────────────────────────────────┘    │
│                    ↓                           │
│  ┌── Click ──────────────────────────────┐    │
│  │  Target: tombol LOGIN                 │    │
│  └───────────────────────────────────────┘    │
│                    ↓                           │
│  ┌── Verify Expression ──────────────────┐    │
│  │  Expression: URL contains "inventory" │    │
│  └───────────────────────────────────────┘    │
│                                                │
└────────────────────────────────────────────────┘
```

---

### Langkah F — Jalankan

1. Pastikan file `TC01_LoginBerhasil.xaml` sedang terbuka dan aktif di canvas
2. Klik tombol **▶ Run** di toolbar atas (atau tekan **F5**)
3. Browser Chrome akan terbuka otomatis
4. Form username & password terisi otomatis
5. Tombol Login diklik otomatis
6. Cek panel **Output** di bawah canvas

**Hasil yang Diharapkan di Panel Output:**
```
[Info] TC01 PASS: Halaman Products berhasil tampil
```

> Jika ada error merah di Output, screenshot dan tanyakan — kita debug bersama.

---

## 5. Praktikum 2 — Login Gagal (Negative Test)

### Tujuan
Verifikasi bahwa akun yang terkunci menampilkan pesan error yang benar.

### Test Case

```
Test Case ID : TC02
Judul        : Login dengan akun terkunci
Steps        :
  1. Buka https://www.saucedemo.com/
  2. Isi username: locked_out_user
  3. Isi password: secret_sauce
  4. Klik tombol Login
Expected     : Muncul pesan error "Epic sadface: Sorry, this user has been locked out."
```

### Langkah Membuat di UiPath Studio

**Step 1: Buka file TC02_LoginGagal.xaml**

**Step 2: Susun workflow berikut:**

```
[Use Application/Browser] → URL: "https://www.saucedemo.com/"
  │
  ├── [Type Into]
  │     Indicate: field username
  │     Text: "locked_out_user"
  │
  ├── [Type Into]
  │     Indicate: field password
  │     Text: "secret_sauce"
  │
  ├── [Click]
  │     Indicate: tombol Login
  │
  ├── [Get Text]
  │     Indicate: elemen pesan error (.error-message-container h3)
  │     Output: pesanError (Variable String)
  │
  └── [Verify Expression]
        Expression: pesanError.Contains("locked out")
        Verification Message: "TC02 PASS: Pesan error locked out tampil dengan benar"
```

**Step 3: Buat Variable untuk Menyimpan Pesan Error**
1. Klik tab **Variables** di bawah canvas
2. Buat variable:
   - Name: `pesanError`
   - Type: `String`

**Step 4: Cara Indicate Elemen Error Message**
1. Jalankan dulu secara manual di browser: login dengan `locked_out_user`
2. Lihat pesan error yang muncul (kotak merah di bawah form)
3. Kembali ke Studio, indicate elemen tersebut

**Step 5: Hubungkan Get Text ke Variable**
- Di activity `Get Text`, di Properties → **Result** → pilih `pesanError`

**Step 6: Jalankan**

**Hasil yang Diharapkan:**
```
[Info] TC02 PASS: Pesan error locked out tampil dengan benar
```

---

## 6. Praktikum 3 — Add to Cart

### Tujuan
Otomasi menambahkan produk ke cart dan verifikasi badge cart bertambah.

### Test Case

```
Test Case ID : TC03
Judul        : Add product ke cart
Precondition : User sudah login sebagai standard_user
Steps        :
  1. Login ke SauceDemo
  2. Di halaman Products, klik "Add to cart" pada produk pertama
  3. Cek badge angka di icon cart berubah menjadi "1"
Expected     : Badge cart menampilkan angka 1
```

### Langkah Membuat di UiPath Studio

**Konsep: Reusable Workflow untuk Login**

Daripada menulis ulang langkah login di setiap test case, kita buat satu file khusus login.

**Step 1: Buat WF_Login.xaml di folder Workflows**

Isi `WF_Login.xaml`:
```
[Sequence] - Arguments: in_Username (String), in_Password (String)
  │
  ├── [Use Application/Browser] → URL: "https://www.saucedemo.com/"
  │     │
  │     ├── [Type Into] → field username → in_Username
  │     ├── [Type Into] → field password → in_Password
  │     └── [Click]     → tombol Login
  │
  └── [Log Message] → "Login sebagai " + in_Username + " berhasil"
```

**Cara buat Argument di WF_Login.xaml:**
1. Klik tab **Arguments** di bawah canvas
2. Buat:
   - `in_Username` → Direction: In → Type: String
   - `in_Password` → Direction: In → Type: String

**Step 2: Buat TC03_AddToCart.xaml**

```
[Sequence]
  │
  ├── [Invoke Workflow File]
  │     WorkflowFileName: "Workflows/WF_Login.xaml"
  │     Arguments:
  │       in_Username = "standard_user"
  │       in_Password = "secret_sauce"
  │
  ├── [Click]
  │     Indicate: tombol "Add to cart" produk pertama
  │     (Sauce Labs Backpack → btn_add-to-cart-sauce-labs-backpack)
  │
  ├── [Get Text]
  │     Indicate: badge angka di icon cart (.shopping_cart_badge)
  │     Output: jumlahCart (Variable String)
  │
  └── [Verify Expression]
        Expression: jumlahCart = "1"
        Verification Message: "TC03 PASS: Badge cart menunjukkan 1 item"
```

**Cara Invoke Workflow:**
1. Cari `Invoke Workflow File` di Activities
2. Drag ke canvas
3. Di Properties → **WorkflowFileName** → browse pilih `WF_Login.xaml`
4. Klik **Import Arguments** → isi nilai untuk `in_Username` dan `in_Password`

**Hasil yang Diharapkan:**
```
[Info] Login sebagai standard_user berhasil
[Info] TC03 PASS: Badge cart menunjukkan 1 item
```

---

## 7. Praktikum 4 — Checkout Flow

### Tujuan
Otomasi complete checkout flow dari cart sampai order complete.

### Test Case

```
Test Case ID : TC04
Judul        : Complete checkout flow
Steps        :
  1. Login sebagai standard_user
  2. Add produk pertama ke cart
  3. Klik icon cart
  4. Klik tombol Checkout
  5. Isi First Name: "John"
  6. Isi Last Name: "Doe"
  7. Isi Zip Code: "12345"
  8. Klik Continue
  9. Klik Finish
Expected     : Halaman "Thank you for your order!" tampil
```

### Langkah Membuat di UiPath Studio

**Buat TC04_Checkout.xaml:**

```
[Sequence]
  │
  ├── [Invoke Workflow File] → WF_Login.xaml
  │     in_Username: "standard_user"
  │     in_Password: "secret_sauce"
  │
  ├── [Click] → Add to cart (produk pertama)
  │
  ├── [Click] → Icon cart (.shopping_cart_link)
  │
  ├── [Click] → Tombol Checkout (#checkout)
  │
  ├── [Type Into] → First Name (#first-name) → "John"
  │
  ├── [Type Into] → Last Name (#last-name) → "Doe"
  │
  ├── [Type Into] → Zip Code (#postal-code) → "12345"
  │
  ├── [Click] → Tombol Continue (#continue)
  │
  ├── [Click] → Tombol Finish (#finish)
  │
  ├── [Get Text] → heading complete (.complete-header)
  │     Output: pesanSelesai (Variable String)
  │
  └── [Verify Expression]
        Expression: pesanSelesai.Contains("Thank you")
        Verification Message: "TC04 PASS: Order berhasil diselesaikan"
```

**Tips Saat Indicate Element:**
- Gunakan **Selector Editor** untuk melihat dan edit selector
- Prioritaskan attribute `id` atau `data-test` karena lebih stabil
- Contoh selector stabil untuk tombol Checkout:
  ```xml
  <webctrl id='checkout' tag='BUTTON' />
  ```

**Hasil yang Diharapkan:**
```
[Info] Login sebagai standard_user berhasil
[Info] TC04 PASS: Order berhasil diselesaikan
```

---

## 8. Praktikum 5 — Data Driven Testing

### Tujuan
Menjalankan test login dengan banyak kombinasi data dari file Excel.

### Konsep Data Driven Testing

```
Excel File (TestData.xlsx)
┌──────────────────────┬──────────────┬──────────────────┐
│ Username             │ Password     │ Expected_Result  │
├──────────────────────┼──────────────┼──────────────────┤
│ standard_user        │ secret_sauce │ success          │
│ locked_out_user      │ secret_sauce │ error            │
│ standard_user        │ wrong_pass   │ error            │
│ wrong_user           │ secret_sauce │ error            │
└──────────────────────┴──────────────┴──────────────────┘
```

Robot akan baca tiap baris dan jalankan test otomatis untuk setiap kombinasi.

### Step 1: Buat File Excel

1. Buat file `TestData.xlsx` di folder `Data/`
2. Isi dengan data di atas (buat header di baris pertama)

### Step 2: Buat TC05_DataDriven.xaml

```
[Sequence]
  │
  ├── [Excel Process Scope]
  │     Workbook Path: "Data/TestData.xlsx"
  │     │
  │     └── [Read Range]
  │               Sheet: "Sheet1"
  │               Output: dtTestData (Variable DataTable)
  │
  └── [For Each Row in Data Table]
        DataTable: dtTestData
        │
        └── [Sequence] - proses tiap baris
              │
              ├── [Assign]
              │     username = row("Username").ToString
              │     password = row("Password").ToString
              │     expected = row("Expected_Result").ToString
              │
              ├── [Use Application/Browser] → SauceDemo
              │     │
              │     ├── [Type Into] → username field → username
              │     ├── [Type Into] → password field → password
              │     └── [Click] → Login button
              │
              └── [If] expected = "success"
                    Then:
                      [Verify Expression] URL contains "inventory"
                    Else:
                      [Get Text] error message
                      [Verify Expression] error message not empty
```

### Step 3: Buat Variable yang Dibutuhkan

| Nama | Tipe | Keterangan |
|---|---|---|
| `dtTestData` | `DataTable` | Menyimpan seluruh isi Excel |
| `username` | `String` | Username tiap iterasi |
| `password` | `String` | Password tiap iterasi |
| `expected` | `String` | Expected result tiap iterasi |

### Step 4: Cara Install Excel Package

1. Klik **Manage Packages**
2. Cari `UiPath.Excel.Activities`
3. Install

### Step 5: Jalankan

Robot akan login sebanyak 4 kali (sesuai baris di Excel) dan report setiap test case.

**Hasil yang Diharapkan di Output:**
```
[Info] Row 1 - standard_user: PASS (login berhasil)
[Info] Row 2 - locked_out_user: PASS (error message tampil)
[Info] Row 3 - standard_user/wrong_pass: PASS (error message tampil)
[Info] Row 4 - wrong_user: PASS (error message tampil)
```

---

## 9. Rangkuman & Best Practice

### Apa yang Sudah Dipelajari

| Praktikum | Konsep yang Dipelajari |
|---|---|
| TC01 Login Berhasil | Use Application/Browser, Type Into, Click, Verify Expression |
| TC02 Login Gagal | Get Text, verifikasi pesan error |
| TC03 Add to Cart | Invoke Workflow (reusable), Get Text badge |
| TC04 Checkout | End-to-end flow, multi-step automation |
| TC05 Data Driven | Read Excel, For Each Row, conditional verification |

### Best Practice UiPath Web Testing

**1. Pakai Reusable Workflow**
```
Jangan: Tulis ulang langkah login di setiap test case
Lakukan: Buat WF_Login.xaml, invoke dari test case lain
```

**2. Gunakan ID Selector yang Stabil**
```xml
<!-- Bagus: pakai id atau data-test -->
<webctrl id='login-button' tag='INPUT' />

<!-- Hindari: pakai posisi / index -->
<webctrl idx='3' tag='INPUT' />
```

**3. Tambahkan Log Message di Setiap Tahap**
```
[Log Message] "Membuka browser SauceDemo..."
[Log Message] "Mengisi username: " + username
[Log Message] "Klik tombol login..."
```
Ini memudahkan debugging saat ada yang gagal.

**4. Pisahkan Test Data dari Logic**
- Jangan hardcode data di dalam workflow
- Pakai Excel atau Constants file

**5. Gunakan Try/Catch untuk Error Handling**
```
[Try]
  └── [Aksi yang mungkin gagal]
[Catch NullReferenceException]
  └── [Log Message] "Elemen tidak ditemukan: " + exception.Message
[Finally]
  └── [Close Application] → pastikan browser selalu ditutup
```

### Cheat Sheet Selector SauceDemo

| Elemen | Selector / ID |
|---|---|
| Field Username | `id='user-name'` |
| Field Password | `id='password'` |
| Tombol Login | `id='login-button'` |
| Error Message | `class='error-message-container'` |
| Add to Cart (item pertama) | `id='add-to-cart-sauce-labs-backpack'` |
| Icon Cart | `class='shopping_cart_link'` |
| Badge Cart | `class='shopping_cart_badge'` |
| Tombol Checkout | `id='checkout'` |
| First Name | `id='first-name'` |
| Last Name | `id='last-name'` |
| Zip Code | `id='postal-code'` |
| Tombol Continue | `id='continue'` |
| Tombol Finish | `id='finish'` |
| Complete Header | `class='complete-header'` |

---

**Selamat!** Kamu sudah menyelesaikan Modul 2. Kamu kini bisa membuat automation test untuk aplikasi web menggunakan UiPath Studio.

**Modul Sebelumnya:** [Modul 1 — Pengenalan UiPath](pengenalan.md)
