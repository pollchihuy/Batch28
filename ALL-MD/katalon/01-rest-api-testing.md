# Bagian 1 — REST API Testing dengan Katalon

> 📌 Sebelum membaca ini, sebaiknya pahami dulu konsep dasar di [step-by-step.md](step-by-step.md).

Di bagian ini kita akan belajar menguji **REST API**. Kita akan memakai server latihan gratis bernama **JSONPlaceholder** (`https://jsonplaceholder.typicode.com`) yang memang disediakan untuk belajar.

---

## Informasi Environment (Lingkungan yang Dipakai)

Modul ini disusun dan diuji pada lingkungan berikut. Jika versi Anda berbeda, sebagian tampilan menu mungkin sedikit berbeda.

| Komponen | Keterangan |
|---|---|
| **Tools** | Katalon Studio |
| **Versi** | **11.2.1** (build `f9be99eb1b`) — *You are up to date* |
| **Edisi (Edition)** | **Free** (gratis) — *bukan* Enterprise. Beberapa fitur berbayar tidak tersedia. |
| **Sistem Operasi** | Windows 10 Pro (64-bit) |
| **Browser** | (tidak dipakai di bagian API; diperlukan untuk bagian Web testing) |
| **Server uji (target)** | JSONPlaceholder — `https://jsonplaceholder.typicode.com` |
| **Koneksi** | Wajib terhubung internet (server target online) |

> Cara cek versi: di Katalon klik **Help → About Katalon Studio**. Akan muncul jendela berisi nomor **Version**.

---

## Apa itu "curl"?

`curl` adalah perintah di terminal untuk memanggil API. Pada modul ini, perintah curl kita pakai sebagai **acuan** (referensi) tentang request apa yang harus dibuat di Katalon. Jadi setiap langkah di bawah akan merujuk ke salah satu cURL pada daftar berikut.

> ℹ️ Katalon **Free tidak bisa meng-import cURL secara langsung**. Karena itu cURL di bawah hanya menjadi **panduan**, dan kita akan membuat request-nya **secara manual** di Katalon.

---

## Daftar cURL yang Digunakan (Acuan)

Berikut 3 perintah cURL yang menjadi dasar seluruh latihan di dokumen ini. Setiap langkah selanjutnya akan menyebut nomor cURL ini (misalnya "sesuai **cURL #1**").

### cURL #1 — GET Semua Postingan
Mengambil **semua** data postingan.
```bash
curl --request GET \
  --url https://jsonplaceholder.typicode.com/posts
```

### cURL #2 — GET Satu Postingan (berdasarkan ID)
Mengambil **satu** postingan saja. Perhatikan ada angka `1` di akhir URL yang merupakan **ID postingan**.
```bash
curl --request GET \
  --url https://jsonplaceholder.typicode.com/posts/1
```

### cURL #3 — POST Membuat Postingan Baru
**Mengirim/membuat** data postingan baru.
```bash
curl --request POST \
  --url https://jsonplaceholder.typicode.com/posts \
  --header 'Content-Type: application/json' \
  --data '{
    "userId": 1,
    "id": 1,
    "title": "coba jsonplace holder",
    "body": "body language"
  }'
```

**Penjelasan bagian-bagian cURL (terutama cURL #3):**
- `--request GET` / `--request POST` → jenis permintaan. **GET** = mengambil data, **POST** = mengirim/membuat data.
- `--url ...` → alamat tujuan (server yang dipanggil).
- `--header 'Content-Type: application/json'` → memberi tahu server bahwa data yang kita kirim berbentuk **JSON** (format teks untuk data).
- `--data '{...}'` → isi data yang dikirim ke server.

---

## Langkah 1 — Membuat Project API Baru

1. Buka **Katalon Studio**.
2. Klik menu **File → New → Project** (atau di layar awal pilih **New Project**).
3. Isi:
   - **Name**: `Training-API-Testing`
   - **Type**: pilih **API/Web Service** (atau **Generic** jika ingin gabungan).
   - **Location**: pilih folder penyimpanan, contoh yang dipakai pada modul ini: `D:\SQA\Batch28\katalon`.
4. Klik **OK**. Katalon akan membuat struktur folder otomatis.

> **Penjelasan:** Project ini ibarat lemari kosong. Selanjutnya kita isi dengan "request" (permintaan API) dan "test case" (skenario uji).
>
> ✅ **Konfigurasi yang sudah berjalan pada modul ini:** project bernama **`Training-API-Testing`** tersimpan di **`D:\SQA\Batch28\katalon\Training-API-Testing`**, memakai **Katalon Studio 11.2.1 (Free)**. Seluruh langkah & script di bawah sudah diuji jalan pada konfigurasi ini.

---

## Langkah 2 — Membuat Request Pertama (GET Semua Data)

> 🎯 Langkah ini mengacu pada **cURL #1** (GET semua postingan).

### Membuat Request (Manual / GUI)

1. Klik kanan folder **Object Repository** → **New → Web Service Request**.
2. Akan muncul jendela **New Web Service Request** dengan kolom berikut:
   - **Name**: ketik `GET_AllPosts`
   - **Request Type**: pilih **RESTful** (defaultnya memang sudah RESTful)
   - **URL**: ketik `https://jsonplaceholder.typicode.com/posts`
   - **Description**: boleh dikosongkan
3. Klik **OK**. Request akan terbuka di editor.

   > **Catatan penting:** di jendela pembuatan **belum ada** pilihan method (GET/POST). Method dipilih **setelah** request terbuka.

4. Di editor request (bagian kiri atas), ada **dropdown method**. Pastikan terpilih **GET**. Di sebelahnya ada kolom URL yang sudah berisi alamat tadi.
5. Klik tombol **▶ hijau** (Send Request / Test Request) di kanan kolom URL untuk menjalankan.
6. Lihat panel **Response** di sebelah kanan:
   - **Status: 200 OK** (warna hijau) → berhasil 🎉
   - Tab **Body** menampilkan daftar postingan dalam bentuk JSON
   - Ada juga info **Elapsed** (lama waktu) dan **Size** (ukuran data)

> **Arti 200 OK:** server menjawab "permintaan berhasil, ini datanya".

### Tentang Import (Katalon Free)

> ⚠️ **Penting:** Katalon **Free tidak punya fitur import dari cURL**. Saat Anda klik kanan **Object Repository → Import**, pilihan yang tersedia hanya **4**:
> - **From OpenAPI** (file spesifikasi API format OpenAPI/Swagger)
> - **From WSDL** (untuk API jenis SOAP)
> - **From Postman** (file koleksi Postman, `.json`)
> - **From SoapUI**
>
> Karena tidak ada import cURL, untuk contoh-contoh di modul ini kita **buat request secara manual** seperti langkah di atas (caranya cepat dan mudah, cukup salin URL dari daftar cURL di atas).

**Alternatif jika ingin tetap "mengimpor":** Anda bisa memasukkan perintah curl ke aplikasi **Postman** terlebih dahulu (Postman bisa baca cURL), simpan sebagai **Collection** (`.json`), lalu di Katalon pilih **Import → From Postman**. Namun untuk latihan ini cara manual sudah lebih dari cukup.

---

## Langkah 3 — Membuat Request GET Satu Postingan (berdasarkan ID)

> 🎯 Langkah ini mengacu pada **cURL #2** (GET satu postingan).

Perhatikan pada cURL #2 ada angka `1` di akhir URL (`.../posts/1`). Angka itu adalah **ID postingan** yang ingin diambil. Kita cukup menuliskan ID-nya langsung di URL.

### Membuat Request

1. Klik kanan **Object Repository → New → Web Service Request**.
2. Di jendela yang muncul, isi:
   - **Name**: `GET_PostById`
   - **Request Type**: **RESTful**
   - **URL**: ketik alamat lengkap dengan ID-nya:
     ```
     https://jsonplaceholder.typicode.com/posts/1
     ```
3. Klik **OK**.
4. Setelah editor terbuka, pastikan **dropdown method** (kiri atas) terpilih **GET**.
5. Klik tombol **▶ hijau** untuk menjalankan. Lihat panel **Response**: status **200 OK** dan isinya hanya **satu** postingan (id = 1).

> **Mau menguji ID lain?** Cukup ubah angka di akhir URL, misalnya jadi `.../posts/5`, lalu jalankan lagi.
>
> ℹ️ **Catatan:** pada modul sebelumnya ada langkah membuat "variabel" lewat tab khusus. Untuk latihan sederhana di Katalon Free ini, **tidak perlu** — kita cukup tulis ID langsung di URL seperti di atas. (Pengaturan nilai dinamis tetap bisa dilakukan lewat scripting Groovy, lihat Langkah 6.)

---

## Langkah 4 — Membuat Request POST (Mengirim Data Baru)

> 🎯 Langkah ini mengacu pada **cURL #3** (POST membuat postingan baru).

POST artinya kita **mengirim data baru** ke server.

### Cara Manual

1. Klik kanan **Object Repository → New → Web Service Request**.
2. Di jendela yang muncul, isi:
   - **Name**: `POST_CreatePost`
   - **Request Type**: **RESTful**
   - **URL**: `https://jsonplaceholder.typicode.com/posts`
3. Klik **OK**.
4. Setelah editor terbuka, ubah **dropdown method** (kiri atas) dari GET menjadi **POST**.
5. Buka tab **HTTP Header**, klik **Add**, tambahkan:
   - **Name**: `Content-Type`
   - **Value**: `application/json`

   > Ini memberi tahu server bahwa data kita berformat JSON.
6. Buka tab **HTTP Body** → pilih opsi **raw**. Isi dengan teks JSON berikut:
   ```json
   {
     "userId": 1,
     "id": 1,
     "title": "coba jsonplace holder",
     "body": "body language"
   }
   ```
7. Klik tombol **▶ hijau** untuk menjalankan. Jika berhasil, **Status: 201 Created** akan muncul.

> **Arti 201 Created:** server menjawab "data baru berhasil dibuat".

---

## Langkah 5 — Menambahkan Verifikasi (Assertion)

Menjalankan request saja belum cukup. Kita perlu **memastikan hasilnya benar** secara otomatis. Inilah inti dari testing.

Setelah menjalankan request, buka tab **Verification** (deretan tab di bawah kolom URL). Di sana ada area kosong untuk menulis script Groovy pengecekan.

> 💡 **Cara cepat (sesuai tampilan Katalon):** Pada panel **Response** di kanan, blok/seleksi bagian JSON yang ingin dicek, lalu tekan **Ctrl + K**. Katalon otomatis menuliskan script verifikasi untuk bagian itu ke tab Verification. (Di bawah panel Response ada tulisan: *"Select JSON or XML response data and press Ctrl/Command + K to add verification scripts directly"*.)

### ⚠️ PENTING — Cara Menjalankan Verifikasi (sering bikin bingung)

Script di tab **Verification TIDAK akan jalan** kalau Anda hanya klik tombol **▶ hijau** biasa. Tombol itu hanya **mengirim request** (menampilkan Body, Header, Status), **tanpa** menjalankan verifikasi. Akibatnya tab **Verification Log akan kosong** walaupun script sudah diisi.

**Cara yang benar:** klik **panah dropdown (▼) di sebelah tombol ▶ hijau**, lalu pilih:

- **Test Request and Verify** → mengirim request **DAN** menjalankan script verifikasi. (Gunakan ini!)
- *Test Request and Save to File* → hanya mengirim lalu menyimpan response ke file.

Setelah memilih **Test Request and Verify**, barulah hasilnya muncul di tab **Verification Log**:

- **Result: PASSED** (hijau) → semua pengecekan lulus.
- **Result: FAILED** (merah) → ada pengecekan gagal, lengkap dengan pesan alasannya. Contoh pesan saat status diharapkan 201 padahal aslinya 200:
  ```
  START Verification
  - 3: verifyResponseStatusCode(response, 201)
  ERROR  Expected status code is '201' but actual status code is '200'
  ```

> **Catat baik-baik:** Verification Log yang **kosong** hampir selalu berarti verifikasi **belum dijalankan** (Anda klik "Send" biasa, bukan "Test Request and Verify"). Jadi kalau log kosong, jangan bingung — ganti cara menjalankannya dulu.

### Tentang Log yang Tetap "Kosong" Saat LULUS

Perintah `WS.verifyResponseStatusCode(...)` dan `assert` bersifat **"diam saat lulus"** — mereka hanya memunculkan pesan saat **GAGAL**. Jadi kalau ingin ada pesan **hijau** sebagai bukti lulus, tambahkan baris `KeywordUtil.markPassed(...)` (jangan lupa import-nya). Contoh lengkap ada di akhir Langkah 5.

### Struktur Script di Tab Verification (PENTING dipahami dulu)

Saat Anda membuka tab **Verification**, Katalon **sudah mengisi template otomatis** seperti ini (bagian atas):

```groovy
import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()
ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()

// >>> di bawah baris inilah Anda menulis pengecekan <<<
```

Maksudnya:
- Baris-baris `import` = "mengambil alat" yang dibutuhkan.
- `request` dan `response` = otomatis berisi request & jawaban (response) **terakhir** yang Anda kirim. Karena sudah disediakan template, di contoh-contoh singkat berikut kita **cukup menambahkan baris pengecekan** di bawahnya — variabel `response` sudah tersedia.

> ⚠️ **Ingat dari kasus kita tadi:** kalau Anda memakai kelas tambahan seperti `KeywordUtil`, template di atas **belum** meng-import-nya. Anda harus menambahkan sendiri `import com.kms.katalon.core.util.KeywordUtil`, kalau tidak akan muncul error `No such property: KeywordUtil`.

### Contoh Verifikasi untuk GET Semua Data (cURL #1)

Tambahkan baris berikut di bawah template (setelah baris `response = ...`):

```groovy
// Memastikan server menjawab dengan kode 200 (berhasil)
WS.verifyResponseStatusCode(response, 200)

// Memastikan isi jawaban (response) tidak kosong
assert response.getResponseBodyContent() != null
```

Penjelasan:
- `WS` adalah singkatan dari **Web Service keyword** (kumpulan perintah API bawaan Katalon).
- `verifyResponseStatusCode(response, 200)` → cek apakah status codenya 200. Kalau bukan, test GAGAL.
- `assert` → kata kunci untuk "pastikan kondisi ini benar".

### Contoh Verifikasi untuk GET Satu Postingan (cURL #2)

```groovy
// Pastikan status 200
WS.verifyResponseStatusCode(response, 200)

// Ambil nilai "id" dari jawaban server, lalu pastikan nilainya = 1
def idDariServer = WS.getElementPropertyValue(response, 'id')
assert idDariServer == '1'
```

### Contoh Verifikasi untuk POST (cURL #3)

```groovy
// Pastikan data berhasil dibuat (status 201)
WS.verifyResponseStatusCode(response, 201)

// Pastikan judul yang kita kirim tersimpan dengan benar
def judul = WS.getElementPropertyValue(response, 'title')
assert judul == 'coba jsonplace holder'
```

### Contoh Lengkap di Tab Verification (dengan import & pesan lulus)

Script di tab **Verification** sebenarnya sudah punya beberapa baris `import` otomatis dari Katalon. Jika Anda ingin menambahkan pesan **PASSED** berwarna hijau (supaya Verification Log tidak terlihat kosong saat lulus), tambahkan `import` untuk `KeywordUtil` lalu panggil `KeywordUtil.markPassed(...)`. Berikut contoh utuh yang bisa langsung dipakai:

```groovy
import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager
import com.kms.katalon.core.util.KeywordUtil          // <-- WAJIB ditambahkan agar markPassed bisa dipakai

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

// Ambil request & response terakhir
RequestObject request = WSResponseManager.getInstance().getCurrentRequest()
ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()

// Untuk GET, status yang benar adalah 200 (bukan 201)
WS.verifyResponseStatusCode(response, 200)

// Pastikan isi jawaban (response) tidak kosong
assert response.getResponseBodyContent() != null

// Pesan hijau ini akan tampil di Verification Log saat semua pengecekan LULUS
KeywordUtil.markPassed("Verifikasi berhasil: status 200 OK dan body tidak kosong")
```

> ❗ **Kesalahan umum (sudah pernah kita alami):** memakai `KeywordUtil.markPassed(...)` **tanpa** menambahkan baris `import com.kms.katalon.core.util.KeywordUtil`. Saat dijalankan akan muncul error:
> ```
> ❌ Verification FAILED.
> Reason:
> groovy.lang.MissingPropertyException: No such property: KeywordUtil ...
> ```
> Solusinya cukup tambahkan baris import tersebut.

**Bukti hasil saat sudah benar (PASSED):** setelah import lengkap dan dijalankan via **▼ → Test Request and Verify**, log menampilkan:

```
START Verification
- 1: request = getInstance().getCurrentRequest()
- 2: response = getInstance().getCurrentResponse()
- 3: verifyResponseStatusCode(response, 200)
- 4: assert response.getResponseBodyContent() != null
- 5: markPassed("Verifikasi berhasil: status 200 OK dan body tidak kosong")
  com.kms.katalon.core.util.KeywordUtil  - Verifikasi berhasil: status 200 OK dan body tidak kosong
END Verification
```

Tidak ada baris `ERROR` → berarti **semua pengecekan lulus**. 🎉

> 🔎 **Untuk membuktikan verifikasi benar-benar berjalan:** coba ubah `200` menjadi `201`, jalankan lagi. Akan muncul **Result: FAILED** + pesan *"Expected status code is '201' but actual status code is '200'"*. Kembalikan ke `200` agar lulus lagi.

---

## Langkah 6 — Menulis Test Case dengan Scripting (Groovy)

Selain memanggil request lewat GUI, kita bisa membuat **Test Case** berisi script Groovy lengkap. Ini lebih fleksibel karena bisa mengatur banyak langkah sekaligus.

> 💡 **Cara cepat membuat Test Case dari request (sesuai tampilan Katalon):** Di editor request, klik **panah kecil di sebelah tombol ➕ hijau**, lalu pilih **"Add to new Test Case"** (atau **"Add to existing Test Case"** untuk menambah ke test case yang sudah ada). Katalon otomatis membuatkan test case yang sudah berisi pemanggilan request tersebut — Anda tinggal menambahkan verifikasi.

Atau, buat Test Case manual dari awal:

1. Klik kanan folder **Test Cases** → **New → Test Case**.
2. Beri nama, misalnya `TC_API_GetAllPosts`.
3. Buka tab **Script** (di bagian bawah, sebelah tab Manual).
4. Tulis kode berikut:

### Script GET Semua Data (cURL #1)

```groovy
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject

// 1. Buat objek request kosong
RequestObject request = new RequestObject('GET_AllPosts')
request.setRestUrl('https://jsonplaceholder.typicode.com/posts')
request.setRestRequestMethod('GET')

// 2. Kirim request, simpan jawabannya ke variabel 'response'
ResponseObject response = WS.sendRequest(request)

// 3. Cek hasilnya
WS.verifyResponseStatusCode(response, 200)
println('Status Code: ' + response.getStatusCode())
println('Isi jawaban: ' + response.getResponseBodyContent())
```

> Penjelasan baris `import`: itu seperti "mengambil alat" yang dibutuhkan sebelum bekerja. `WS` adalah alat untuk urusan API.

### Script GET Satu Postingan (cURL #2)

> Di sinilah "nilai dinamis" tadi kita pakai: lewat scripting Groovy, ID bisa disimpan dalam variabel dan diganti-ganti dengan mudah.

```groovy
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject

// Variabel ID yang bisa diganti-ganti
def postId = 1

RequestObject request = new RequestObject('GET_PostById')
request.setRestUrl('https://jsonplaceholder.typicode.com/posts/' + postId)
request.setRestRequestMethod('GET')

ResponseObject response = WS.sendRequest(request)

WS.verifyResponseStatusCode(response, 200)

// Pastikan id pada jawaban sama dengan yang kita minta
def idDariServer = WS.getElementPropertyValue(response, 'id')
assert idDariServer.toString() == postId.toString()
println('Berhasil mengambil postingan id: ' + idDariServer)
```

### Script POST (Mengirim Data Baru) (cURL #3)

```groovy
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType

// 1. Siapkan request
RequestObject request = new RequestObject('POST_CreatePost')
request.setRestUrl('https://jsonplaceholder.typicode.com/posts')
request.setRestRequestMethod('POST')

// 2. Tambahkan header Content-Type = application/json
ArrayList<TestObjectProperty> headers = new ArrayList<TestObjectProperty>()
headers.add(new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json'))
request.setHttpHeaderProperties(headers)

// 3. Siapkan isi data (body) dalam bentuk JSON
String body = '''{
    "userId": 1,
    "id": 1,
    "title": "coba jsonplace holder",
    "body": "body language"
}'''
request.setBodyContent(new com.kms.katalon.core.testobject.impl.HttpTextBodyContent(body))

// 4. Kirim dan periksa hasil
ResponseObject response = WS.sendRequest(request)

WS.verifyResponseStatusCode(response, 201)   // 201 = data berhasil dibuat
def judul = WS.getElementPropertyValue(response, 'title')
assert judul == 'coba jsonplace holder'
println('Data baru berhasil dibuat dengan judul: ' + judul)
```

> **Tanda `'''`** (tiga petik) dipakai untuk menulis teks yang panjang/berbaris banyak, seperti JSON, tanpa ribet.

---

## Langkah 7 — Menjalankan Test Case

1. Buka Test Case yang ingin dijalankan.
2. Klik tombol **Run** (ikon ▶ hijau di toolbar atas).
3. Lihat hasilnya di panel **Log Viewer** (bawah):
   - ✅ Hijau = **PASSED** (lulus)
   - ❌ Merah = **FAILED** (gagal) — klik untuk melihat alasannya.

---

## Langkah 8 — Menjalankan Banyak Test Sekaligus (Test Suite)

1. Klik kanan folder **Test Suites** → **New → Test Suite**.
2. Beri nama `TS_API_Regression`.
3. Klik tombol **Add** → pilih Test Case yang sudah dibuat (boleh beberapa sekaligus).
4. Klik **Run**.
5. Setelah selesai, Katalon membuat **Report** (laporan) otomatis yang berisi: jumlah test lulus, gagal, dan durasinya.

> **Manfaat Test Suite:** sekali klik, semua skenario API diuji bersamaan. Cocok untuk pengecekan rutin.

---

## Ringkasan Bagian 1

Yang sudah Anda pelajari:
- ✔ Membuat project API
- ✔ Membuat request GET semua data, GET satu postingan (by ID), dan POST (secara manual, sesuai GUI Katalon Free)
- ✔ Memahami pilihan Import yang tersedia di Katalon Free (OpenAPI, WSDL, Postman, SoapUI — tanpa cURL)
- ✔ Menambahkan verifikasi/assertion (termasuk cara cepat Ctrl+K)
- ✔ **Menjalankan verifikasi dengan benar** lewat **▼ → Test Request and Verify** (kunci agar Verification Log tidak kosong)
- ✔ Menulis Test Case dengan Groovy
- ✔ Menjalankan lewat Test Suite & melihat report

➡️ Lanjut ke [Bagian 2 — Web Testing](02-web-testing-saucedemo.md).
