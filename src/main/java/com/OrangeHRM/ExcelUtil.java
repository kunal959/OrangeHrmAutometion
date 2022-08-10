package com.OrangeHRM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	Workbook wb;
	Sheet sheet;
	Row row;
	Cell cell;

	public String getFileExtension(String filePath) {
		return filePath.substring(filePath.indexOf("."));
	}

	public void getWorkBookInstance(String filePath) throws IOException {
		FileInputStream input;
		try {
			input = new FileInputStream(filePath);
			String extension = getFileExtension(filePath);
			if (extension.equals(".xlsx")) {
				wb = new XSSFWorkbook(input);
			} else {
				wb = new HSSFWorkbook(input);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getSheetInstance(String sheetName) {

		if (Objects.isNull(wb.getSheet(sheetName))) {
			sheet = wb.createSheet(sheetName);
		} else {
			sheet = wb.getSheet(sheetName);
		}
	}

	public Object[][] getExcelData(String filePath, String sheetName) throws IOException {
		getWorkBookInstance(filePath);
		getSheetInstance(sheetName);
		int totalRow = sheet.getPhysicalNumberOfRows();
		System.out.println("Total Row " + totalRow);
		int totalColumn = sheet.getRow(0).getPhysicalNumberOfCells();
		Object[][] data = new Object[totalRow][totalColumn];
		for (int i = 1; i < totalRow; i++) {
			row = sheet.getRow(i);
			for (int j = 0; j < totalColumn; j++) {
				cell = row.getCell(j);
				data[i][j] = getCellData();
			}
		}
		wb.close();
		return data;
	}

	public Object getCellData() {
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			return cell.getNumericCellValue();
		case BOOLEAN:
			return cell.getBooleanCellValue();
		case BLANK:
			return null;
		case _NONE:
			return null;
		case ERROR:
			return cell.getErrorCellValue();
		case FORMULA:
			return cell.getCellFormula();
		default:
			System.out.println("none of the case match with cell type");
			return null;
		}
	}

	public void setExcelData(String filePath, String sheetName, int rowIndex, int cellIndex, Object value)
			throws IOException {
		getSheetInstance(sheetName);
		int totalRow = sheet.getPhysicalNumberOfRows();
		if (sheet.getRow(rowIndex) == null) {
			row = sheet.createRow(rowIndex);
			cell = row.createCell(cellIndex);
			cell.setCellValue(Objects.nonNull(value) ? value.toString() : null);
		} else {
			row = sheet.getRow(rowIndex);
			if (row.getCell(cellIndex) == null) {
				cell = row.createCell(cellIndex);
			} else {
				cell = row.getCell(cellIndex);
			}
			cell.setCellValue(Objects.nonNull(value) ? value.toString() : null);

		}
		FileOutputStream output = new FileOutputStream(filePath);
		wb.write(output);
	
	}
	
	public void closeWBInstance() {
		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
