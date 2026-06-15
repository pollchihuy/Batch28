package com.juaracoding.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BuatSampleExcel {

    public String generate() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sc");

        // 1. Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"nama_lengkap", "bpjs_kes", "bpjs_tek", "no_ktp", "email"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // 2. Generate 1 row of random data using GenerateData
        GenerateData generateData = new GenerateData();
        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue(generateData.dataNamaLengkap());
        dataRow.createCell(1).setCellValue(generateData.dataBPJSKesehatan());
        dataRow.createCell(2).setCellValue(generateData.dataBPJSTek());
        dataRow.createCell(3).setCellValue(generateData.dataKTP());
        dataRow.createCell(4).setCellValue(generateData.dataEmail());

        // 3. Prepare target directory based on pattern [yyMMddhhmm]
        String timestamp = new SimpleDateFormat("yyMMddhhmm").format(new Date());
        String dirPath = "excel/" + timestamp;
        File directory = new File(dirPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 4. Save workbook to file
        String filePath = dirPath + "/sample.xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Excel file successfully created at: " + filePath);
        } catch (IOException e) {
            System.out.println("Error while writing Excel file: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return filePath;
    }
}
