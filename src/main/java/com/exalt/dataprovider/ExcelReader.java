package com.exalt.dataprovider;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    public static Object[][] getCredentials(String filePath, int sheetNumber) throws IOException {
        DataFormatter dataFormatter = new DataFormatter();
        String email = null;
        String password = null;
        Row row;
        /*
         Creating a Workbook from an Excel file (.xls or .xlsx)
         */
        Workbook workbook = WorkbookFactory.create(new File(filePath));
        /*
         Retrieving the number of sheets in the Workbook
         obtain a sheetIterator and iterate over it
         */
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            System.out.println("=> " + sheet.getSheetName());
        }
        /*
         Getting the Sheet
         */
        Sheet sheet = workbook.getSheetAt(sheetNumber);
        /*
         Or you can use a for-each loop to iterate over the rows and columns
         */
        Object[][] credential = new Object[sheet.getLastRowNum()][sheet.getRow(0).getPhysicalNumberOfCells()];
        for (int i = 1, j = 0; i <= sheet.getLastRowNum(); ++i, ++j) {
            row = sheet.getRow(i);
            for (Cell cell : row) {
                if (cell.getColumnIndex() == 0) {
                    email = dataFormatter.formatCellValue(cell);
                } else if (cell.getColumnIndex() == 1) {
                    password = dataFormatter.formatCellValue(cell);
                } else {
                    System.out.println("wrong column index");
                }
            }
            credential[j][0] = email;
            credential[j][1] = password;
        }
        /*
         Closing the workbook
         */
        workbook.close();
        return credential;
    }
}
