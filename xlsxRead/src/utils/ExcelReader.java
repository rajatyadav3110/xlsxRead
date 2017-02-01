package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public static void main(String[] args) {
		
		try {
			
			File myFile = new File("C:/Users/Infotech/Desktop/bank.xlsx");
			
			FileInputStream fis = new FileInputStream(myFile); 
			
			// Finds the workbook instance for XLSX file
			XSSFWorkbook myWorkBook = new XSSFWorkbook(fis); 
			
			// Return first sheet from the XLSX workbook
//			XSSFSheet mySheet = myWorkBook.getSheetAt(0); 
			
			// Return first sheet from the XLSX workbook
//			XSSFSheet mySheet = myWorkBook.getSheetAt(0); 
						
						
			Iterator<Sheet> sheetIterator = myWorkBook.iterator(); 
			
			// Traversing over each row of XLSX file
			while (sheetIterator.hasNext()) {
				XSSFSheet mySheet = (XSSFSheet) sheetIterator.next();
				// Get iterator to all the rows in current sheet
				Iterator<Row> rowIterator = mySheet.iterator(); 
				
				// Traversing over each row of XLSX file
				while (rowIterator.hasNext()) {
					
					Row row = rowIterator.next(); 
					
					// For each row, iterate through each columns
					Iterator<Cell> cellIterator = row.cellIterator();
					int columnNo = 0;
					
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						
						columnNo++;
						
							
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_BLANK:
								System.out.print("\t");
								break;
								
							case Cell.CELL_TYPE_ERROR:
								System.out.print("ERROR\t");
								break;
								
							case Cell.CELL_TYPE_FORMULA:
								System.out.print(cell.getCellFormula() + "\t");
								break;
								
							case Cell.CELL_TYPE_STRING:
								System.out.print(cell.getStringCellValue() + "\t");
								break;
								
							case Cell.CELL_TYPE_NUMERIC:
								System.out.print(cell.getNumericCellValue() + "\t");
								break;
								
							case Cell.CELL_TYPE_BOOLEAN:
								System.out.print(cell.getBooleanCellValue() + "\t");
								break;
							default:
							}
							
						}
						
					System.out.println("");
						
					}
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		


// Read more:
// http://java67.blogspot.com/2014/09/how-to-read-write-xlsx-file-in-java-apache-poi-example.html#ixzz43yxfKjxW

}
