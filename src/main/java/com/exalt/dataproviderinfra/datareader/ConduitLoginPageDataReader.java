package com.exalt.dataproviderinfra.datareader;

import org.apache.poi.ss.usermodel.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class ConduitLoginPageDataReader {
    final static String XLSX_FILE_PATH = "files\\ConduitLoginTest.xlsx";
    final static int sheetNumber = 0;

    @NotNull
    public static Object[][] getCredentials() throws IOException {
        /*
         Creating a Workbook from an Excel file (.xls or .xlsx)
         */
        Workbook workbook = WorkbookFactory.create(new File(XLSX_FILE_PATH));
        /*
         Getting the Sheet
         */
        Sheet sheet = workbook.getSheetAt(sheetNumber);
        DataFormatter dataFormatter = new DataFormatter();
        String email = null;
        String password = null;
        Row row;
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
