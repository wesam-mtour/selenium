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

import static com.exalt.infra.utils.Constants.*;

public final class DataProviderFinder<T> {

    public static Map<Integer, ArrayList<String>> excelData = new HashMap<Integer, ArrayList<String>>();

    @NotNull
    @DataProvider(name = "Excel")
    public static Object[][] getData(@NotNull Method method) throws IOException {

        excelData.clear();
        if (method.getName().equals("LoginWithInvalidCredentialsTest")) {
        /*
         Create a DataFormatter to format and get each cell's exact value as String
         */
            DataFormatter dataFormatter = new DataFormatter();
            Row row;
        /*
         Creating a Workbook from an Excel file (.xls or .xlsx)
         */
            Workbook workbook = WorkbookFactory.create(new File("files\\" + method.getDeclaringClass().getSimpleName() + ".xlsx"));
        /*
         Getting the Sheet
         */
            Sheet sheet = workbook.getSheet(method.getName());
        /*
         Or you can use a for-each loop to iterate over the rows and columns
         */
            Object[][] invalidCredential = new Object[sheet.getLastRowNum()][3];
            for (int i = 1, j = 0; i <= sheet.getLastRowNum(); ++i, ++j) {
                row = sheet.getRow(i);
                excelData.put(i - 1, new ArrayList<String>(Arrays.asList(
                        dataFormatter.formatCellValue(row.getCell(0)),
                        dataFormatter.formatCellValue(row.getCell(1)),
                        dataFormatter.formatCellValue(row.getCell(2)),
                        "pass")));
                if (excelData.get(i - 1).get(0).equals("yes")) {
                    invalidCredential[j][0] = dataFormatter.formatCellValue(row.getCell(3));
                    invalidCredential[j][1] = dataFormatter.formatCellValue(row.getCell(4));
                    invalidCredential[j][2] = dataFormatter.formatCellValue(row.getCell(5));
                }
            }
        /*
         Closing the workbook
         */
            workbook.close();
            return invalidCredential;
        } else if (method.getName().equals("ConduitPostNewArticleTest")) {
        /*
         Create a DataFormatter to format and get each cell's exact value as String
         */
            DataFormatter dataFormatter = new DataFormatter();
            Row row;
        /*
         Creating a Workbook from an Excel file (.xls or .xlsx)
         */
            Workbook workbook = WorkbookFactory.create(new File("files\\" + method.getDeclaringClass().getSimpleName() + ".xlsx"));
        /*
         Getting the Sheet
         */
            Sheet sheet = workbook.getSheetAt(SHEET_NUMBER);
        /*
         Or you can use a for-each loop to iterate over the rows and columns
         */
            Object[][] posters = new Object[sheet.getLastRowNum()][4];
            for (int i = 1, j = 0; i <= sheet.getLastRowNum(); ++i, ++j) {
                row = sheet.getRow(i);
                excelData.put(i - 1, new ArrayList<String>(Arrays.asList(
                        dataFormatter.formatCellValue(row.getCell(0)),
                        dataFormatter.formatCellValue(row.getCell(1)),
                        dataFormatter.formatCellValue(row.getCell(2)),
                        "pass")));
                if (excelData.get(i - 1).get(0).equals("yes")) {
                    posters[j][0] = dataFormatter.formatCellValue(row.getCell(3));
                    posters[j][1] = dataFormatter.formatCellValue(row.getCell(4));
                    posters[j][2] = dataFormatter.formatCellValue(row.getCell(5));
                    posters[j][3] = dataFormatter.formatCellValue(row.getCell(6));
                }
            }
        /*
         Closing the workbook
         */
            workbook.close();
            return posters;

        } else if (method.getName().equals("ch_OldPassToInvalidPasswordTest")) {
         /*
         Create a DataFormatter to format and get each cell's exact value as String
         */
            DataFormatter dataFormatter = new DataFormatter();
            Row row;
        /*
         Creating a Workbook from an Excel file (.xls or .xlsx)
         */
            Workbook workbook = WorkbookFactory.create(new File("files\\" + method.getDeclaringClass().getSimpleName() + ".xlsx"));
        /*
         Getting the Sheet
         */
            Sheet sheet = workbook.getSheet(method.getName());
        /*
         Or you can use a for-each loop to iterate over the rows and columns
         */
            Object[][] invalidPasswords = new Object[sheet.getLastRowNum()][3];
            for (int i = 1, j = 0; i <= sheet.getLastRowNum(); ++i, ++j) {
                row = sheet.getRow(i);
                excelData.put(i - 1, new ArrayList<String>(Arrays.asList(
                        dataFormatter.formatCellValue(row.getCell(0)),
                        dataFormatter.formatCellValue(row.getCell(1)),
                        dataFormatter.formatCellValue(row.getCell(2)),
                        "pass")));
                if (excelData.get(i - 1).get(0).equals("yes")) {
                    invalidPasswords[j][0] = dataFormatter.formatCellValue(row.getCell(3));
                    invalidPasswords[j][1] = dataFormatter.formatCellValue(row.getCell(4));
                    invalidPasswords[j][2] = dataFormatter.formatCellValue(row.getCell(5));

                }
            }
        /*
         Closing the workbook
         */
            workbook.close();
            return invalidPasswords;
        } else if (method.getName().equals("LoginWithValidCredentialsTest")) {
         /*
         Create a DataFormatter to format and get each cell's exact value as String
         */
            DataFormatter dataFormatter = new DataFormatter();
            Row row;
        /*
         Creating a Workbook from an Excel file (.xls or .xlsx)
         */
            Workbook workbook = WorkbookFactory.create(new File("files\\" + method.getDeclaringClass().getSimpleName() + ".xlsx"));
        /*
         Getting the Sheet
         */
            Sheet sheet = workbook.getSheet(method.getName());
        /*
         Or you can use a for-each loop to iterate over the rows and columns
         */
            Object[][] validCredentials = new Object[sheet.getLastRowNum()][2];
            for (int i = 1, j = 0; i <= sheet.getLastRowNum(); ++i, ++j) {
                row = sheet.getRow(i);
                excelData.put(i - 1, new ArrayList<String>(Arrays.asList(
                        dataFormatter.formatCellValue(row.getCell(0)),
                        dataFormatter.formatCellValue(row.getCell(1)),
                        dataFormatter.formatCellValue(row.getCell(2)),
                        "pass")));
                if (excelData.get(i - 1).get(0).equals("yes")) {
                    validCredentials[j][0] = dataFormatter.formatCellValue(row.getCell(3));
                    validCredentials[j][1] = dataFormatter.formatCellValue(row.getCell(4));
                }
            }
        /*
         Closing the workbook
         */
            workbook.close();
            return validCredentials;
        } else if (method.getName().equals("ch_OldPassToValidPasswordTest")) {
        /*
         Create a DataFormatter to format and get each cell's exact value as String
         */
            DataFormatter dataFormatter = new DataFormatter();
            Row row;
        /*
         Creating a Workbook from an Excel file (.xls or .xlsx)
         */
            Workbook workbook = WorkbookFactory.create(new File("files\\" + method.getDeclaringClass().getSimpleName() + ".xlsx"));
        /*
         Getting the Sheet
         */
            Sheet sheet = workbook.getSheet(method.getName());
        /*
         Or you can use a for-each loop to iterate over the rows and columns
         */
            Object[][] validPasswords = new Object[sheet.getLastRowNum()][3];
            for (int i = 1, j = 0; i <= sheet.getLastRowNum(); ++i, ++j) {
                row = sheet.getRow(i);
                excelData.put(i - 1, new ArrayList<String>(Arrays.asList(
                        dataFormatter.formatCellValue(row.getCell(0)),
                        dataFormatter.formatCellValue(row.getCell(1)),
                        dataFormatter.formatCellValue(row.getCell(2)),
                        "pass")));
                if (excelData.get(i - 1).get(0).equals("yes")) {
                    validPasswords[j][0] = dataFormatter.formatCellValue(row.getCell(3));
                    validPasswords[j][1] = dataFormatter.formatCellValue(row.getCell(4));
                    validPasswords[j][2] = dataFormatter.formatCellValue(row.getCell(5));
                }
            }
        /*
         Closing the workbook
         */
            workbook.close();
            return validPasswords;
        } else {
            return null;
        }
    }
}
