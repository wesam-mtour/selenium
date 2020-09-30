package com.exalt.infra.dataprovider.datareader;

import org.apache.poi.ss.usermodel.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ConduitLoginTest {
    final static String XLSX_FILE_PATH = "files\\ConduitLoginTest.xlsx";
    final static int sheetNumber = 0;
    public static Map<Integer, ArrayList<String>> testCases = new HashMap<Integer, ArrayList<String>>();

    public Map<Integer, ArrayList<String>> getExcelData() {
        return testCases;
    }

    @NotNull
    public static Object[][] getCredentials() throws IOException {
        /*
         Create a DataFormatter to format and get each cell's exact value as String
         */
        DataFormatter dataFormatter = new DataFormatter();
        Row row;
        /*
         Creating a Workbook from an Excel file (.xls or .xlsx)
         */
        Workbook workbook = WorkbookFactory.create(new File(XLSX_FILE_PATH));
        /*
         Getting the Sheet
         */
        Sheet sheet = workbook.getSheetAt(sheetNumber);
        /*
         Or you can use a for-each loop to iterate over the rows and columns
         */
        Object[][] credential = new Object[sheet.getLastRowNum()][3];
        for (int i = 1, j = 0; i <= sheet.getLastRowNum(); ++i, ++j) {
            row = sheet.getRow(i);
            testCases.put(i - 1, new ArrayList<String>(Arrays.asList(
                    dataFormatter.formatCellValue(row.getCell(0)),
                    dataFormatter.formatCellValue(row.getCell(1)),
                    dataFormatter.formatCellValue(row.getCell(2)),
                    "pass")));
            credential[j][0] = dataFormatter.formatCellValue(row.getCell(0));
            credential[j][1] = dataFormatter.formatCellValue(row.getCell(3));
            credential[j][2] = dataFormatter.formatCellValue(row.getCell(4));
        }
        /*
         Closing the workbook
         */
        workbook.close();
        return credential;
    }
}