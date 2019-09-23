	package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriven {
public static void main(String[] args)throws IOException {
	

	 
		//specify the excel file containing test data
		File src=new File("E:\\DataDrivenTest\\testdata.xlsx");
		//load the excel file
				FileInputStream fis=new FileInputStream(src);
				//load the workbook from the above excel file 
				XSSFWorkbook wb=new XSSFWorkbook(fis);
				//load the sheet from above excel workbook
				XSSFSheet sheet=wb.getSheetAt(2);
				int rowCount=sheet.getLastRowNum()+1;
				int colCount=sheet.getRow(0).getLastCellNum();
				String[][] data = new String[rowCount][colCount];
				int j;
				for(int i=0; i<=rowCount-1; i++) {
					//Reading first column(getCell refer to Column)
					for(j=0; j<colCount; j++)
					data[i][j] =sheet.getRow(i).getCell(j).getStringCellValue();
				}
			
				for(int i=0; i<=rowCount-1; i++) {
					//Reading first column(getCell refer to Column)
					for(j=0; j<colCount; j++)
				System.out.print(data[i][j]);
				System.out.println();
		}
				
				
				ExcelDriven E1 = new ExcelDriven();
				E1.writeIntoExcel();
				
				
				}

public void writeIntoExcel() throws IOException {

	//specify the excel file in which you want to add data
	File src=new File("E:\\DataDrivenTest\\testdata.xlsx");
	String dataToBeAdded[]= {"Test","paswd"};
	//load the excel file
	FileInputStream fis=new FileInputStream(src);
	//load the workbook from the above excel file 
	XSSFWorkbook wb=new XSSFWorkbook(fis);
	//load the sheet from above excel workbook
	XSSFSheet sheet=wb.getSheetAt(0);
	int rowCount=sheet.getLastRowNum()+1;
	int colCount=sheet.getRow(0).getLastCellNum();
	
	 Row newRow = sheet.createRow(rowCount+1);
	 for(int j=0;j<colCount;j++) {
		 Cell cell = newRow.createCell(j);
		 cell.setCellValue(dataToBeAdded[j]);
		 
	 }
	 
	//Create an object of FileOutputStream class to create write data in excel file

	    FileOutputStream outputStream = new FileOutputStream(src);

	    //write data in the excel file

	    wb.write(outputStream);

	    //close output stream

	    outputStream.close();

	 
	
	//close the workbook		
	wb.close();
}
	}

