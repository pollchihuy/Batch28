package com.juaracoding.utils;

import java.util.Random;

public class GenerateData {

    private Random random;
    private String alfabetLowerCase = "abcdefghijklmnopqrstuvwxyz";
    private String alfabetUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String alfabetAllCase = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String allCharacter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private String[] strProvider = {"gmail", "yahoo", "outllok", "hotmail"};
    private String[] strDomain = {"com", "co.id", "net", "edu"};

    public GenerateData() {
        random = new Random();
    }

    public String dataNamaLengkap() {
        /**
         * Format data Nama Lengkap ?
         * Spasi Huruf Besar Huruf Kecil
         * min 10 maks 55 character
         */
        String namaLengkap = "";
        while (true) {
            int numWords = random.nextInt(2, 4); // 2 s.d 3 kata, agar selalu ada spasi
            StringBuilder sb = new StringBuilder();
            for (int w = 0; w < numWords; w++) {
                if (w > 0) {
                    sb.append(" ");
                }
                int wordLength = random.nextInt(4, 11); // 4 s.d 10 karakter per kata
                for (int j = 0; j < wordLength; j++) {
                    if (j == 0) {
                        sb.append(alfabetUpperCase.charAt(random.nextInt(alfabetUpperCase.length())));
                    } else {
                        sb.append(alfabetLowerCase.charAt(random.nextInt(alfabetLowerCase.length())));
                    }
                }
            }
            String result = sb.toString();
            if (result.length() >= 10 && result.length() <= 55) {
                namaLengkap = result;
                break;
            }
        }
        return namaLengkap;
    }

    // public String dataNamaDepan() {
    //     /**
    //      * Format data Nama Depan ?
    //      * Spasi Huruf Besar Huruf Kecil
    //      * min 10 maks 55 character
    //      */
    //     String namaDepan = "";
    //     while (true) {
    //         int wordLength = random.nextInt(10, 16); // 10 s.d 15 karakter
    //         StringBuilder sb = new StringBuilder();
    //         for (int j = 0; j < wordLength; j++) {
    //             if (j == 0) {
    //                 sb.append(alfabetUpperCase.charAt(random.nextInt(alfabetUpperCase.length())));
    //             } else {
    //                 sb.append(alfabetLowerCase.charAt(random.nextInt(alfabetLowerCase.length())));
    //             }
    //         }
    //         String result = sb.toString();
    //         if (result.length() >= 10 && result.length() <= 55) {
    //             namaDepan = result;
    //             break;
    //         }
    //     }
    //     return namaDepan;
    // }

    public String dataBPJSKesehatan() {
        /**
         * Format data BPJS ?
         * 13 digit angka         
         */
        return String.valueOf(random.nextLong(1000000000000L, 10000000000000L));
    }

    public String dataBPJSTek() {
        /**
         * Format data BPJS Ketenagakerjaan ?
         * 11 digit angka
         */
        return String.valueOf(random.nextLong(10000000000L, 100000000000L));
    }

    public String dataKTP() {
        /**
         * Format data NO KTP ?
         * 16 digit angka tanpa tanda baca dan spasi
         */
        return String.valueOf(random.nextLong(1000000000000000L, 10000000000000000L));
    }

    public String dataEmail() {
        /**
         * Format data Email ?
         * 1.numerik min 1 maks 3 digit
         * 2. (.)
         * 3.Alfabet Lower Case 5 maks 10 character
         * 4. @
         * 5.provider
         * 6. (.)
         * 7.domain
         * contoh : 22.panji@gmail.com
         */
        String email = "";
        email = String.valueOf(random.nextInt(1000)); // dari 0 s.d 999
        email = email + "."; // 77.
        int randAlfabet = random.nextInt(5, 11); // 5 s.d 10
        for (int i = 0; i < randAlfabet; i++) {
            email = email + alfabetLowerCase.charAt(random.nextInt(alfabetLowerCase.length()));
        }
        email = email + "@"; // 77.panji@
        email = email + strProvider[random.nextInt(strProvider.length)]; // 77.panji@gmail
        email = email + "."; // 77.panji@gmail.
        email = email + strDomain[random.nextInt(strDomain.length)]; // 77.panji@gmail.com        

        return email;
    }
}