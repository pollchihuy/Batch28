# RestAssured Test Automation Framework (SQA Batch 28)

Project ini adalah kerangka kerja (framework) otomatisasi pengujian API menggunakan **Maven**, **RestAssured**, dan **TestNG**.

## Prasyarat (Prerequisites)
Sebelum menjalankan project ini, pastikan Anda sudah menginstal:
1. **Java Development Kit (JDK) 17** atau versi terbaru.
2. **Apache Maven** terpasang di sistem dan terdaftar dalam PATH environment variable.
3. IDE pilihan Anda (seperti **IntelliJ IDEA** atau **Eclipse**).

---

## Struktur Folder Project
```text
RestAssured/
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── sqa/
│   │   │           └── restassured/
│   │   │               └── SampleTest.java  <-- Kelas Test menggunakan RestAssured
│   │   └── resources/
│   │       └── testng.xml                  <-- Konfigurasi TestNG Suite
├── .gitignore
├── pom.xml                                  <-- Konfigurasi Dependency & Plugin Maven
└── README.md
```

---

## Cara Menjalankan Test

### Melalui Command Line (Terminal)
Gunakan perintah berikut di folder root project untuk menjalankan seluruh suite pengujian:

```bash
mvn clean test
```

### Melalui IDE (IntelliJ IDEA)
1. Buka folder `RestAssured` sebagai project Maven di IntelliJ IDEA.
2. Tunggu hingga Maven selesai mengunduh semua dependency.
3. Buka file `src/test/java/com/sqa/restassured/SampleTest.java`.
4. Klik tombol **Run** (ikon segitiga hijau) di sebelah deklarasi class atau di setiap metode test untuk menjalankan test secara individual.
5. Atau klik kanan pada file `src/test/resources/testng.xml` lalu pilih **Run '...testng.xml'**.

---

## Library Utama yang Digunakan
- **RestAssured (v5.4.0)**: Digunakan untuk mempermudah pengujian REST API di Java.
- **TestNG (v7.10.2)**: Framework testing untuk manajemen test case, assertions, dan test suites.
- **Jackson Databind (v2.17.1)**: Untuk serialisasi dan deserialisasi data JSON ke Java Object (POJO) dan sebaliknya.
