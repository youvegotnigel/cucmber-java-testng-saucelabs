package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtility {

    public static final Logger log = LogManager.getLogger(ExcelUtility.class.getName());
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    public static XSSFCell xssfCell;
    public static XSSFRow xssfRow;
    public static String path = System.getProperty("user.dir");
    public static String excelFilePath = path + "\\testdata\\";


    //Constructor
    public ExcelUtility(String excelFileName, String sheetName) {
        File file = new File(path + "\\testdata\\" + excelFileName);
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            log.debug("Excel Workbook Opened.");
            log.debug("File Path of excel - " + excelFilePath + "\\" + excelFileName);
            log.debug("Excel Sheet name - " + sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int GetTotalRowCount() {
        int rowCount = sheet.getPhysicalNumberOfRows();
        log.debug("Number of Rows in the excel - " + rowCount);
        return rowCount;
    }

    public int GetTotalColumnCount() {
        int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
        log.debug("Number of Columns in the excel - " + columnCount);
        return columnCount;
    }

    public String[][] GetDataFromExcel2DArray() {

        int rowCount = GetTotalRowCount();
        int columnCount = GetTotalColumnCount();
        DataFormatter formatter = new DataFormatter();
        int tempRow = rowCount - 1;
        String[][] data = new String[tempRow][columnCount]; //excluding header in row
        for (int i = 0; i < tempRow; i++) {
            for (int j = 0; j < columnCount; j++) {
                int temp = i + 1;
                Cell cell = sheet.getRow(temp).getCell(j);
                data[i][j] = formatter.formatCellValue(cell);
            }
        }
        log.debug("Getting data from Excel in a 2D Array.");
        return data;
    }

    public void CloseExcelFile() {
        try {
            workbook.close();
            log.debug("Excel workbook closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCellDataStringValue(int rowNum, int colNum) {
        xssfCell = sheet.getRow(rowNum).getCell(colNum);
        String cellData = xssfCell.getStringCellValue();
        log.debug("Cell Data of String value is returned from the excel - " + cellData);
        return cellData;
    }

    public double getCellDataNumericValue(int rowNum, int colNum) {
        xssfCell = sheet.getRow(rowNum).getCell(colNum);
        double cellData = xssfCell.getNumericCellValue();
        log.debug("Cell Data of Numeric value is returned from the excel - " + cellData);
        return cellData;
    }

    public String getCellDataDateValue(int rowNum, int colNum) {
        xssfCell = sheet.getRow(rowNum).getCell(colNum);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date cellDate = xssfCell.getDateCellValue();
        String dateValue = dateFormat.format(cellDate);
        log.debug("Cell Data of Date value is returned from the excel - " + dateValue);
        return dateValue;
    }

    public void setCellDataStringValue(String value, int rowNum, int colNum) {
        xssfRow = sheet.getRow(rowNum);
        if (xssfRow == null) {
            xssfRow = sheet.createRow(rowNum);
        }
        xssfCell = xssfRow.getCell(colNum);
        if (xssfCell == null) {
            xssfCell = xssfRow.createCell(colNum);
            xssfCell.setCellValue(value);
        } else {
            xssfCell.setCellValue(value);
        }
        try {
            FileOutputStream fis = new FileOutputStream(excelFilePath + "ExcelTestData.xlsx");
            workbook.write(fis);
            log.debug("Cell Data of String type is added into the excel - " + value);
            fis.flush();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCellDataNumericValue(int value, int rowNum, int colNum) {
        xssfRow = sheet.getRow(rowNum);
        if (xssfRow == null) {
            xssfRow = sheet.createRow(rowNum);
        }
        xssfCell = xssfRow.getCell(colNum);
        if (xssfCell == null) {
            xssfCell = xssfRow.createCell(colNum);
            xssfCell.setCellValue(value);
        } else {
            xssfCell.setCellValue(value);
        }
        try {
            FileOutputStream fis = new FileOutputStream(excelFilePath + "ExcelTestData.xlsx");
            workbook.write(fis);
            log.debug("Cell Data of Numeric type is added into the excel - " + value);
            fis.flush();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
