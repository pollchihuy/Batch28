package com.juaracoding.utils;

public class PanggilBuatSampleExcel {
    public static void main(String[] args) {
        // 1. Call BuatSampleExcel to generate the file
        BuatSampleExcel generator = new BuatSampleExcel();
        String generatedFilePath = generator.generate();

        System.out.println("Reading back the generated file...");

        // 2. Use ExcelReader to read the generated file and print it
        ExcelReader excelReader = new ExcelReader(generatedFilePath, "sc");
        String[][] data = excelReader.getAllData();

        System.out.println("--- DATA READ FROM FILE ---");
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println("----------------------------");
    }
}
