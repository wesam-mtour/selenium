package com.exalt.infra.dataprovider.datareader;

import org.apache.poi.ss.usermodel.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConduitPostNewArticleTest {
    final static String XLSX_FILE_PATH = "files\\ConduitPostNewArticleTest.xlsx";
    final static int sheetNumber = 0;
    public static Map<Integer, ArrayList<String>> excelData = new HashMap<Integer, ArrayList<String>>();

    public Map<Integer, ArrayList<String>> getExcelData() {
        return excelData;
    }

    @NotNull
    public static Object[][] getPosters() throws IOException {
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
        Object[][] posters = new Object[sheet.getLastRowNum()][5];
        for (int i = 1, j = 0; i <= sheet.getLastRowNum(); ++i, ++j) {
            row = sheet.getRow(i);
            excelData.put(i - 1, new ArrayList<String>(Arrays.asList(
                    dataFormatter.formatCellValue(row.getCell(0)),
                    dataFormatter.formatCellValue(row.getCell(1)),
                    dataFormatter.formatCellValue(row.getCell(2)),
                    "pass")));
            posters[j][0] = dataFormatter.formatCellValue(row.getCell(0));
            posters[j][1] = dataFormatter.formatCellValue(row.getCell(3));
            posters[j][2] = dataFormatter.formatCellValue(row.getCell(4));
            posters[j][3] = dataFormatter.formatCellValue(row.getCell(5));
            posters[j][4] = dataFormatter.formatCellValue(row.getCell(6));
        }
        /*
         Closing the workbook
         */
        workbook.close();
        return posters;
    }
}
