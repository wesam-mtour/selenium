package com.exalt.infra.dataprovider;

import org.apache.poi.ss.usermodel.*;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class ExcelDataProvider {

    public static Map<Integer, ArrayList<String>> excelData = new HashMap<Integer, ArrayList<String>>();

    @NotNull
    @DataProvider(name = "Excel")
    public static Object[][] getData(@NotNull Method method) throws IOException {
        excelData.clear();
        /*
         Create a DataFormatter to format and get each cell's exact value as String
         */
        DataFormatter dataFormatter = new DataFormatter();
        Row row;
        String packageName = method.getDeclaringClass().getPackage().getName();
        String parentPackages = packageName.substring(6 + packageName.indexOf("tests"));
        parentPackages = parentPackages.replace(".", "\\");
        /*
         Creating a Workbook from an Excel file (.xls or .xlsx)
         */
        Workbook workbook = WorkbookFactory.create(new File("files\\" + parentPackages + "\\" + method.getDeclaringClass().getSimpleName() + ".xlsx"));
        /*
         Getting the Sheet
         */
        Sheet sheet = workbook.getSheet(method.getName());
        /*
          for-each loop to iterate over the rows that have "run" equal to yes
         */
        int numberOfRowsInSheet = 0;
        for (int i = 0; i < sheet.getLastRowNum() + 1; ++i) {
            if (dataFormatter.formatCellValue(sheet.getRow(i).getCell(0)).equals("yes")) {
                numberOfRowsInSheet++;
            }
        }
        int numberOfColumnsInSheet = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[numberOfRowsInSheet][numberOfColumnsInSheet - 3];
        /*
        i started from 1 to ignore the first row in the sheet
         */
        int index = 1;
        for (int i = 1; i <= sheet.getLastRowNum(); ++i) {
            row = sheet.getRow(i);
            if ((dataFormatter.formatCellValue(row.getCell(0))).equals("yes")) {
                excelData.put(index - 1, new ArrayList<String>(Arrays.asList(
                        dataFormatter.formatCellValue(row.getCell(0)),
                        dataFormatter.formatCellValue(row.getCell(1)),
                        dataFormatter.formatCellValue(row.getCell(2)),
                        "pass")));
                for (int j = 0; j < numberOfColumnsInSheet - 3; ++j) {
                    data[index - 1][j] = dataFormatter.formatCellValue(row.getCell(j + 3));
                }
                index++;
            }
        }
        /*
         Closing the workbook
         */
        workbook.close();
        return data;
    }
}