package com.juaracoding.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelReader {

	private Workbook wBook;
	private Sheet sheet;
	private String values;

	private int intRowCount;
	private int intColCount;
	private String[][] strAllData;
	private String[][] arrWithoutHeader;
	private int loopRows;
	private FileInputStream excelFile;
	private BufferedInputStream inputBuff;

	public ExcelReader(String excelPath, String sheetName) {
		try {
			excelFile = new FileInputStream(new File(excelPath));
			inputBuff = new BufferedInputStream(excelFile);
			if (excelPath.endsWith("xlsx")) {
				wBook = new XSSFWorkbook(inputBuff);
				sheet = wBook.getSheet(sheetName);
			} else {
				wBook = new HSSFWorkbook(inputBuff);
				sheet = wBook.getSheet(sheetName);
			}
			getRowCount();
			getColCount();
			setData();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (excelFile != null) excelFile.close();
				if (inputBuff != null) inputBuff.close();
				if (wBook != null) wBook.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public Iterator<Row> getIter() {
		return sheet.iterator();
	}

	public void setData() {
		try {
			strAllData = new String[intRowCount][intColCount];
			arrWithoutHeader = new String[intRowCount - 1][intColCount];
			loopRows = 0;
			Iterator<Row> rX = sheet.iterator();

			while (rX.hasNext()) {
				Row rows = rX.next();
				for (int j = 0; j < intColCount; j++) {
					if (loopRows != 0) {
						arrWithoutHeader[loopRows - 1][j] = getCellData(loopRows, j).toString();
					}
					strAllData[loopRows][j] = getCellData(loopRows, j).toString();
				}
				loopRows++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String[][] getAllData() {
		return strAllData;
	}

	public String[][] getDataWithoutHeader() {
		return arrWithoutHeader;
	}

	public Object getCellData(int rowNum, int colNum) {
		if (sheet.getRow(rowNum) == null) {
			System.out.println("Rownum ke " + rowNum + " Null !!");
			return "";
		}
		values = new DataFormatter().formatCellValue(sheet.getRow(rowNum).getCell(colNum));
		return values;
	}

	public int getRowCount() {
		intRowCount = sheet.getPhysicalNumberOfRows();
		return intRowCount;
	}

	public int getRowCountWH() {
		return arrWithoutHeader.length;
	}

	public int getColCount() {
		if (sheet.getRow(0) == null) {
			return 0;
		}
		intColCount = sheet.getRow(0).getPhysicalNumberOfCells();
		return intColCount;
	}
}
