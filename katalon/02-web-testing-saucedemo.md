# Bagian 2 — Web Application Testing dengan Katalon

> 📌 Sebelum membaca ini, sebaiknya pahami dulu konsep dasar di [step-by-step.md](step-by-step.md).

Di bagian ini kita belajar menguji **website** secara otomatis. Katalon akan membuka browser, mengetik, mengklik, dan memeriksa halaman — semua otomatis, seperti ada "robot" yang menggantikan tangan kita.

Website latihan yang kita pakai: **https://www.saucedemo.com/**
Ini adalah toko online palsu (dummy) yang memang disediakan khusus untuk latihan testing.

### Akun untuk Login (tersedia di halaman situsnya)
- **Username**: `standard_user`
- **Password**: `secret_sauce`

---

## Konsep Penting: Object Repository

Saat menguji web, Katalon harus tahu **di mana** letak tombol/kolom yang akan diklik atau diisi. Informasi lokasi ini disimpan di **Object Repository** (ibarat "buku alamat" elemen halaman).

Setiap elemen (tombol login, kolom username, dll.) disebut **Test Object**, dan dikenali lewat **Locator** (alamat elemen). Locator yang umum:
- **id** → penanda unik elemen (paling disukai karena pasti).
- **xpath** → alamat berdasarkan struktur halaman.
- **css** → alamat berdasarkan gaya/penanda CSS.

> Tenang, kita tidak harus menghafal locator. Katalon punya fitur **Record** dan **Spy** yang mengambil locator secara otomatis.

---

## Langkah 1 — Membuat Project Web Baru

1. Buka **Katalon Studio**.
2. **File → New → Project**.
3. Isi:
   - **Name**: `Training-Web-Testing`
   - **Type**: **Web** (atau **Generic**).
4. Klik **OK**.

---

## Langkah 2 — Merekam Skenario dengan "Record" (Cara Termudah)

Fitur **Record** membuat test case otomatis sambil Anda mengklik. Cocok untuk pemula. Pada modul ini kita memakai **Web Recorder (Katalon Recorder Plus)** yang berjalan di browser **Chrome**.

1. Buka **Web Recorder**, lalu pada **Select Available Browser** pilih **Chrome**.
2. Mulai merekam, lalu pada browser buka alamat `https://www.saucedemo.com/`.
3. Lakukan langkah login secara normal:
   - Klik kolom **Username**, ketik `standard_user`.
   - Klik kolom **Password**, ketik `secret_sauce`.
   - Klik tombol **Login**.
   - (opsional) klik judul **Products** di halaman berikutnya.
4. Lihat tab **Recorded Steps** — setiap aksi Anda otomatis tercatat sebagai langkah, contohnya:
   ```
   1  Open Browser            ""
   2  Navigate To Url         'https://www.saucedemo.com/'
   3  Set Text                input_Username     'standard_user'
   4  Set Encrypted Text      input_Password     'qcu24s4901Fy...=='   ← password otomatis disamarkan
   5  Click                   input_login-button
   6  Click                   span_Products
   ```
   > 🔒 Perhatikan password direkam sebagai **Set Encrypted Text** (nilainya diacak), jadi aman — tidak tampil sebagai teks asli.
5. Tab **Captured Objects** berisi daftar elemen yang ditangkap (mis. `input_Username`, `input_Password`, `input_login-button`, `span_Products`).
6. Setelah selesai (dan setelah menambah verifikasi di **Langkah 3**), klik tombol biru **Save** untuk mengirim hasil rekaman ke **Katalon Studio**. Semua elemen otomatis tersimpan ke **Object Repository**.

> 🎉 Tanpa menulis kode, Anda sudah punya test case login!

---

## Langkah 3 — Menambahkan Verifikasi (Assertion)

Setelah login, kita perlu memastikan login **benar-benar berhasil**. Tandanya: muncul halaman produk dengan judul **"Products"**.

Cara menambah verifikasi **berbeda** tergantung Anda sedang di **Web Recorder** atau di dalam **Katalon Studio**. Pilih sesuai yang Anda pakai.

### Cara A — Di dalam Web Recorder (yang sedang Anda pakai)

> Ini sesuai panel **Web Recorder / Katalon Recorder Plus** di browser, yang punya tab **Recorded Steps · Captured Objects · Variables** dan daftar **Test Steps**.

Saat masih di jendela Web Recorder (sebelum klik **Save**):

1. Di area **Test Steps**, klik tombol **➕ Add** (pojok kanan atas daftar langkah).
2. Sebuah baris langkah baru muncul. Pilih perintah (command) **`Verify Text Present`**.

   > ℹ️ **Penting:** di Web Recorder, perintah verifikasi langsung tersedia sebagai daftar command (seperti `Verify Text Present`, `Verify Element Present`, dll.). **Tidak ada** menu bernama "Add → Built-in Keyword" — itu istilah di Katalon Studio (lihat Cara B).

3. Isi kolom yang muncul:
   - **Text**: `Products`  (teks yang harus ada di halaman setelah login)
   - **Is Regex**: `false`  (artinya: cocokkan teks apa adanya, bukan pola regex)
   - **Description (optional)**: boleh dikosongkan
4. Klik **Done**. Langkah verifikasi kini tampil di daftar, contohnya:
   ```
   7  Verify Text Present  'Products'  false
   ```
5. (Opsional) Klik **▷ Run** untuk mencoba langsung di browser.
6. Klik tombol biru **Save** (pojok kanan atas Web Recorder) untuk mengirim hasil rekaman + verifikasi ke **Katalon Studio**. Semua elemen (mis. `input_Username`, `input_Password`, `input_login-button`, `span_Products`) otomatis tersimpan ke **Object Repository**.

### Cara B — Di dalam Katalon Studio (mode Manual)

Kalau test case sudah ada di Katalon Studio dan Anda membukanya di tab **Manual**:

1. Klik tombol **Add** → **Built-in Keyword** (di Katalon Studio, menu inilah yang tersedia).
2. Pilih keyword seperti **Verify Element Present**, **Verify Element Text**, atau **Verify Text Present**.
3. Pada kolom **Object**, arahkan ke elemen judul "Products" (mis. `span_Products` dari Object Repository).

> **Ringkasnya:** "+ Add → Verify Text Present" = di **Web Recorder**. "Add → Built-in Keyword" = di **Katalon Studio**. Keduanya bertujuan sama: mengecek teks "Products" muncul.

---

## Langkah 4 — Menulis Test Case dengan Scripting (Groovy)

Sekarang kita tulis skenario login secara manual lewat kode, supaya paham apa yang terjadi di balik layar.

1. Klik kanan **Test Cases → New → Test Case**, beri nama `TC_Web_Login`.
2. Buka tab **Script**, tulis:

```groovy
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType

// Fungsi bantu: membuat object berdasarkan locator id, tanpa Object Repository
def buatObjek = { String idValue ->
    TestObject obj = new TestObject(idValue)
    obj.addProperty('id', ConditionType.EQUALS, idValue)
    return obj
}

// 1. Buka browser dan halaman saucedemo
WebUI.openBrowser('')
WebUI.navigateToUrl('https://www.saucedemo.com/')

// 2. Ketik username dan password
WebUI.setText(buatObjek('user-name'), 'standard_user')
WebUI.setText(buatObjek('password'), 'secret_sauce')

// 3. Klik tombol Login
WebUI.click(buatObjek('login-button'))

// 4. Verifikasi: pastikan teks "Products" muncul (tanda login berhasil)
WebUI.verifyElementText(
    findTestObject('Object Repository/judul_products'),  // bisa juga pakai object hasil Record
    'Products'
)

// 5. Tutup browser
WebUI.closeBrowser()
```

Penjelasan sederhana:
- `WebUI` = kumpulan perintah untuk web (buka browser, klik, ketik, dll.).
- `openBrowser('')` = buka browser baru (kosongkan tanda kutip).
- `navigateToUrl(...)` = pergi ke alamat website.
- `setText(elemen, teks)` = ketik teks ke dalam kolom.
- `click(elemen)` = klik tombol.
- `verifyElementText(...)` = pastikan teks elemen sesuai harapan.
- `closeBrowser()` = tutup browser setelah selesai.

> **Catatan locator saucedemo:** kolom username ber-id `user-name`, password ber-id `password`, dan tombol login ber-id `login-button`. Itulah kenapa kita pakai id tersebut di atas.

---

## Langkah 5 — Skenario Lanjutan: Tambah Barang ke Keranjang & Checkout

Ini contoh skenario lengkap dari login → pilih barang → checkout. Tulis di test case baru `TC_Web_Checkout`:

```groovy
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType

// Fungsi bantu membuat object dari id
def byId = { String idValue ->
    TestObject obj = new TestObject(idValue)
    obj.addProperty('id', ConditionType.EQUALS, idValue)
    return obj
}

// Fungsi bantu membuat object dari xpath (untuk elemen tanpa id)
def byXpath = { String xpathValue ->
    TestObject obj = new TestObject(xpathValue)
    obj.addProperty('xpath', ConditionType.EQUALS, xpathValue)
    return obj
}

// === 1. LOGIN ===
WebUI.openBrowser('')
WebUI.navigateToUrl('https://www.saucedemo.com/')
WebUI.setText(byId('user-name'), 'standard_user')
WebUI.setText(byId('password'), 'secret_sauce')
WebUI.click(byId('login-button'))

// Pastikan sudah masuk halaman produk
WebUI.verifyElementPresent(byXpath("//span[text()='Products']"), 10)

// === 2. TAMBAH BARANG KE KERANJANG ===
// Tombol "Add to cart" untuk produk backpack
WebUI.click(byId('add-to-cart-sauce-labs-backpack'))

// Pastikan angka di ikon keranjang menjadi 1
WebUI.verifyElementText(byXpath("//span[@class='shopping_cart_badge']"), '1')

// === 3. BUKA KERANJANG & CHECKOUT ===
WebUI.click(byXpath("//a[@class='shopping_cart_link']"))   // ikon keranjang
WebUI.click(byId('checkout'))                              // tombol Checkout

// === 4. ISI DATA PEMBELI ===
WebUI.setText(byId('first-name'), 'Budi')
WebUI.setText(byId('last-name'), 'Santoso')
WebUI.setText(byId('postal-code'), '12345')
WebUI.click(byId('continue'))

// === 5. SELESAIKAN PEMBELIAN ===
WebUI.click(byId('finish'))

// Pastikan muncul pesan sukses "Thank you for your order!"
WebUI.verifyElementPresent(byXpath("//h2[text()='Thank you for your order!']"), 10)
println('Checkout berhasil!')

// === 6. TUTUP BROWSER ===
WebUI.closeBrowser()
```

Penjelasan tambahan:
- `verifyElementPresent(elemen, 10)` → tunggu sampai 10 detik untuk memastikan elemen muncul. Berguna karena halaman web kadang butuh waktu memuat.
- Angka di keranjang (`shopping_cart_badge`) dipakai untuk memastikan barang benar-benar masuk.

---

## Langkah 6 — Contoh Pengujian Login GAGAL (Negative Test)

Testing yang baik juga menguji **kondisi gagal**. Misalnya login dengan akun yang dikunci (`locked_out_user`).

```groovy
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType

def byId = { String idValue ->
    TestObject obj = new TestObject(idValue)
    obj.addProperty('id', ConditionType.EQUALS, idValue)
    return obj
}
def byXpath = { String xpathValue ->
    TestObject obj = new TestObject(xpathValue)
    obj.addProperty('xpath', ConditionType.EQUALS, xpathValue)
    return obj
}

WebUI.openBrowser('')
WebUI.navigateToUrl('https://www.saucedemo.com/')
WebUI.setText(byId('user-name'), 'locked_out_user')
WebUI.setText(byId('password'), 'secret_sauce')
WebUI.click(byId('login-button'))

// Pastikan muncul pesan error (login memang harus gagal)
def pesanError = WebUI.getText(byXpath("//h3[@data-test='error']"))
assert pesanError.contains('locked out')
println('Pesan error tampil sesuai harapan: ' + pesanError)

WebUI.closeBrowser()
```

> **Negative test** = sengaja menguji kondisi salah, untuk memastikan aplikasi menampilkan pesan error yang benar.

---

## Langkah 7 — Menjalankan Test Case

1. Buka test case.
2. Pastikan browser (Chrome) sudah punya **driver** (Katalon biasanya mengurus ini otomatis).
3. Klik tombol **Run** → pilih **Chrome**.
4. Browser terbuka otomatis dan menjalankan langkah-langkah. Lihat hasil di **Log Viewer**:
   - ✅ Hijau = lulus
   - ❌ Merah = gagal (klik untuk detail)

---

## Langkah 8 — Menjalankan via Test Suite & Laporan

1. Klik kanan **Test Suites → New → Test Suite**, beri nama `TS_Web_Regression`.
2. Klik **Add**, pilih test case web yang sudah dibuat.
3. Pada kolom **Browser**, pilih Chrome.
4. Klik **Run**.
5. Setelah selesai, Katalon membuat **Report** otomatis (jumlah lulus/gagal, screenshot saat gagal, durasi).

> 💡 **Tips:** Saat test gagal, Katalon otomatis menyimpan **screenshot** halaman pada saat gagal. Ini sangat membantu mencari penyebab masalah.

---

## Tips Tambahan untuk Web Testing

- **Gunakan Object Repository hasil Record** bila memungkinkan; lebih rapi daripada menulis locator manual seperti contoh di atas (contoh manual hanya untuk pembelajaran).
- **Tambahkan "wait"** (`WebUI.waitForElementPresent`) jika halaman lambat memuat, supaya test tidak gagal karena elemen belum muncul.
- **Pisahkan data** (username/password) ke dalam **Data Files** agar mudah diuji dengan banyak akun (Data-Driven Testing).

---

## Ringkasan Bagian 2

Yang sudah Anda pelajari:
- ✔ Membuat project Web
- ✔ Merekam skenario dengan fitur Record
- ✔ Konsep Object Repository & Locator (id, xpath, css)
- ✔ Menulis test login dengan Groovy
- ✔ Skenario lengkap: login → tambah keranjang → checkout
- ✔ Negative test (login gagal)
- ✔ Menjalankan lewat Test Suite & melihat report + screenshot

⬅️ Kembali ke [Halaman Utama](step-by-step.md) atau [Bagian 1 — REST API](01-rest-api-testing.md).
