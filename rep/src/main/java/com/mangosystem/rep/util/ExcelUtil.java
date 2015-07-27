package com.mangosystem.rep.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	public ExcelUtil() {}
	
	public static List<Map<String,Object>> readXLS(String inputFileName, int rows) {

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		StringBuffer sbResult = new StringBuffer();

		//xls
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(inputFileName));
			HSSFWorkbook wb = new HSSFWorkbook(fs);

			if (wb != null) {
				//int sheetLength = wb.getNumberOfSheets();  //sheet 총 개수
				for (int s=0; s<1; s++) {	//전체 sheets
					
					HSSFSheet xsheet = wb.getSheetAt(0); //선택 sheet
					//int rowsLength = xsheet.getPhysicalNumberOfRows();  //rows 총 개수
					
					int rowsLength = rows == -1 ? xsheet.getPhysicalNumberOfRows() : rows;
					
					int firstCellNum = 0;
					int lastCellNum = 0;
					
					//시작셀과 마지막셀 확인
					for (int r=0; r<rowsLength; r++) {
						HSSFRow row = xsheet.getRow(r);
						firstCellNum = row.getFirstCellNum() < firstCellNum ? row.getFirstCellNum() : firstCellNum;
						lastCellNum = row.getLastCellNum() > lastCellNum ? row.getLastCellNum() : lastCellNum;
					}
					
					Map<String,Object> rowMap = null;
					for (int r=0; r<rowsLength; r++) {  //전체 rows
						rowMap = new HashMap<String, Object>();
						
						HSSFRow row = xsheet.getRow(r);
						if (row != null) {
							//int cellsLength = row.getPhysicalNumberOfCells();  //cells 총 개수
							//int firstCellNum = row.getFirstCellNum();  //cells 
							//int lastCellNum = row.getLastCellNum();  //
	
							for (int c=firstCellNum; c<lastCellNum; c++) {
	
								HSSFCell cell = row.getCell(c);
								String cellValue = null;
								HashMap<String, Object> hm = null;
	
								if (cell != null) {
									cellValue = null;
									hm = new HashMap<String, Object>();
	
									switch (cell.getCellType()) {
									case Cell.CELL_TYPE_NUMERIC:
										cellValue = String.valueOf(cell.getNumericCellValue());
										break;
									case Cell.CELL_TYPE_STRING:
										cellValue = cell.getStringCellValue();
										break;
									case Cell.CELL_TYPE_FORMULA:
										cellValue = cell.getCellFormula();
										break;
	
									case Cell.CELL_TYPE_BLANK:
										break;
									case Cell.CELL_TYPE_BOOLEAN:
										cellValue = String.valueOf(cell.getBooleanCellValue());
										break;
									case Cell.CELL_TYPE_ERROR:
										//cellValue = cell.getErrorCellString();
										cellValue = "" + cell.getErrorCellValue();
										break;
									}
									
									sbResult.append(cellValue + "\t\t");
									rowMap.put(String.valueOf(c), cellValue);
									
								} else {
									sbResult.append(null + "\t\t");
									rowMap.put(String.valueOf(c), "");
								}
							}
							sbResult.append("\n");
							result.add(rowMap);
						}
					}
				}
				//System.out.println( sbResult );
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	public static List<Map<String,Object>> readXLSX(String inputFileName, int rows) {

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		StringBuffer sbResult = new StringBuffer();

		try {
			OPCPackage pkg = OPCPackage.open(new File( inputFileName ));
			XSSFWorkbook wb = new XSSFWorkbook(pkg);

			if (wb != null) {
				//int sheetLength = wb.getNumberOfSheets();  //sheet 총 개수
				for (int s=0; s<1; s++) {	//전체 sheets
					
					XSSFSheet xsheet = wb.getSheetAt(0); //선택 sheet
					//int rowsLength = xsheet.getPhysicalNumberOfRows();  //rows 총 개수
					
					int rowsLength = rows == -1 ? xsheet.getPhysicalNumberOfRows() : rows;
					Map<String,Object> rowMap = null;
					
					int firstCellNum = 0;
					int lastCellNum = 0;
					
					//시작셀과 마지막셀 확인
					for (int r=0; r<rowsLength; r++) {
						XSSFRow row = xsheet.getRow(r);
						firstCellNum = row.getFirstCellNum() < firstCellNum ? row.getFirstCellNum() : firstCellNum;
						lastCellNum = row.getLastCellNum() > lastCellNum ? row.getLastCellNum() : lastCellNum;
					}
					
					for (int r=0; r<rowsLength; r++) {  //전체 rows
						rowMap = new HashMap<String, Object>();
						
						XSSFRow row = xsheet.getRow(r);
						if (row != null) {
							//int cellsLength = row.getPhysicalNumberOfCells();  //cells 총 개수
							//int firstCellNum = row.getFirstCellNum();  //cells 
							//int lastCellNum = row.getLastCellNum();  //
	
							for (int c=firstCellNum; c<lastCellNum; c++) {
	
								XSSFCell cell = row.getCell(c);
								String cellValue = null;
								HashMap<String, Object> hm = null;
	
								if (cell != null) {
									cellValue = null;
									hm = new HashMap<String, Object>();
	
									switch (cell.getCellType()) {
									case Cell.CELL_TYPE_NUMERIC:
										cellValue = String.valueOf(cell.getNumericCellValue());
										break;
									case Cell.CELL_TYPE_STRING:
										cellValue = cell.getStringCellValue();
										break;
									case Cell.CELL_TYPE_FORMULA:
										cellValue = cell.getCellFormula();
										break;
	
									case Cell.CELL_TYPE_BLANK:
										cellValue = null;
										break;
									case Cell.CELL_TYPE_BOOLEAN:
										cellValue = String.valueOf(cell.getBooleanCellValue());
										break;
									case Cell.CELL_TYPE_ERROR:
										//cellValue = cell.getErrorCellString();
										cellValue = "" + cell.getErrorCellValue();
										break;
									}
									
									sbResult.append(cellValue + "\t\t");
									rowMap.put(String.valueOf(c), cellValue);
									
								} else {
									sbResult.append(null + "\t\t");
									rowMap.put(String.valueOf(c), "");
								}
							}
							sbResult.append("\n");
							result.add(rowMap);
						}
					}
				}
				//System.out.println( sbResult );
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}