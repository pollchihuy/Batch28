# Modul Training Katalon Studio

Selamat datang di modul training **Katalon Studio**. Modul ini dibuat sederhana dan bertahap (step-by-step) supaya mudah diikuti, bahkan jika Anda baru pertama kali memakai Katalon.

---

## Apa itu Katalon Studio?

**Katalon Studio** adalah aplikasi (tools) untuk melakukan **testing otomatis** (automation testing). Artinya, kita membuat "robot" yang menjalankan pengujian aplikasi secara otomatis, sehingga kita tidak perlu mengklik atau mengetik manual berulang-ulang.

Dengan Katalon kita bisa menguji:
- **REST API** → menguji "dapur" aplikasi (server) yang mengirim dan menerima data, biasanya berupa teks JSON.
- **Web Application** → menguji "tampilan" aplikasi yang dibuka di browser (klik tombol, isi form, login, dll).

---

## Istilah Penting (Kamus Sederhana)

Sebelum mulai, kenali dulu istilah-istilah ini. Semua dijelaskan dengan bahasa sehari-hari:

| Istilah | Arti Sederhana |
|---|---|
| **Project** | Folder besar tempat menyimpan semua pekerjaan testing kita. |
| **Test Case** | Satu skenario pengujian. Contoh: "menguji login berhasil". |
| **Test Suite** | Kumpulan beberapa Test Case yang dijalankan sekaligus. |
| **Object Repository** | "Buku alamat" yang menyimpan lokasi tombol/kolom di halaman web supaya Katalon tahu harus mengklik apa. |
| **Keyword** | Perintah siap pakai dari Katalon. Contoh: "klik", "ketik teks", "buka browser". |
| **Groovy** | Bahasa pemrograman yang dipakai Katalon untuk menulis script. Mirip Java, tapi lebih sederhana. |
| **Request** | Permintaan yang kita kirim ke server (misalnya: "tolong kirimkan data postingan nomor 1"). |
| **Response** | Jawaban yang dikirim server atas permintaan kita. |
| **Assertion / Verification** | Pengecekan: "apakah hasilnya sesuai harapan?". Jika tidak sesuai, test dianggap GAGAL. |
| **Status Code** | Kode angka dari server yang menandakan hasil. Contoh: `200` = berhasil, `201` = data berhasil dibuat, `404` = tidak ditemukan. |

> **Catatan:** Di Katalon kita bisa bekerja dengan **dua cara**:
> 1. **Manual / GUI** → klik-klik tombol (cocok untuk pemula).
> 2. **Scripting (Groovy)** → menulis kode (lebih fleksibel dan kuat).
>
> Modul ini menjelaskan **keduanya**, supaya Anda paham dari dasar.

---

## Prasyarat (Yang Harus Disiapkan)

1. **Katalon Studio sudah terinstall** di komputer Anda. ✔ (sudah selesai)
2. **Koneksi internet** (karena kita menguji server dan website online).
3. **Browser** (Chrome direkomendasikan) untuk bagian Web testing.
4. (Opsional) Akun Katalon gratis — kadang diminta saat pertama membuka aplikasi.

---

## Daftar Materi

Materi dipecah menjadi beberapa file sesuai jenis pengujiannya:

### 1. [REST API Testing → 01-rest-api-testing.md](01-rest-api-testing.md)
Belajar menguji API menggunakan contoh nyata (`jsonplaceholder.typicode.com`):
- GET semua data
- GET satu data berdasarkan ID
- POST mengirim data baru
- Lengkap dengan cara manual **dan** scripting Groovy.

### 2. [Web Application Testing → 02-web-testing-saucedemo.md](02-web-testing-saucedemo.md)
Belajar menguji website `https://www.saucedemo.com/`:
- Login
- Menambah barang ke keranjang
- Checkout
- Lengkap dengan cara manual **dan** scripting Groovy.

---

## Alur Belajar yang Disarankan

```
Baca file ini (konsep dasar)
        │
        ▼
Praktik REST API Testing  ──►  Praktik Web Testing
        │                            │
        └──────────┬─────────────────┘
                   ▼
        Jalankan lewat Test Suite & lihat laporan (report)
```

Selamat belajar! 🚀 Mulai dari file [01-rest-api-testing.md](01-rest-api-testing.md).
