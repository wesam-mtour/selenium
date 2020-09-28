package com.exalt.report.listeners;

import com.exalt.report.generatedreports.*;
import org.jetbrains.annotations.NotNull;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ReporterListener implements IReporter {
    final static String SUMMARY_REPORT_PATH = "test-output\\Summary Report.html";
    final static String PASSED_TESTS_REPORT_PATH = "test-output\\Passed Tests Report.html";
    final static String FAILED_TESTS_REPORT_PATH = "test-output\\Failed Tests Report.html";
    final static String SKIPPED_TESTS_REPORT_PATH = "test-output\\Skipped Tests Report.html";
    final static String PASSED_TESTS_REPORT_LINK = "../test-output/Passed Tests Report.html";
    final static String FAILED_TESTS_REPORT_LINK = "../test-output/Failed Tests Report.html";
    final static String SKIPPED_TESTS_REPORT_LINK = "../test-output/Skipped Tests Report.html";


    int totalNumberOfPassedMethods = 0;
    int totalNumberOfFailedMethods = 0;
    int totalNumberOfSkippedMethods = 0;
    int numberOfSuites = 0;
    List<Double> testsTime = new ArrayList<>();
    Map<Integer, ArrayList<String>> excelData;

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            numberOfSuites = suiteResults.size();
            for (ISuiteResult sr : suiteResults.values()) {
                ITestContext tc = sr.getTestContext();
                totalNumberOfPassedMethods += tc.getPassedTests().getAllResults().size();
                totalNumberOfFailedMethods += tc.getFailedTests().getAllResults().size();
                totalNumberOfSkippedMethods += tc.getSkippedTests().getAllResults().size();
                /*
                Writing a report of Failed  tests
                 */
                try {
                    writeFailedTestReport(sr, tc);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                /*
                Writing a report of successful tests
                 */
                try {
                    writePassedTestReport(sr, tc);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                try {
                    writeSkippedTestReport(sr, tc);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
        /*
        Writing a summary report of all tests
        */
        writeSummaryReport();
        /*
        Print all reports permanently
         */

        fileOverwriting();
    }

    /*
    To collect all the times that the tests in the suite are not parallel
     */
    private void writeSkippedTestReport(@NotNull ISuiteResult sr, @NotNull ITestContext tc) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Map<String, String> map = new HashMap<String, String>();
        for (ITestResult testResult : tc.getSkippedTests().getAllResults()) {
            if (!(map.containsKey(testResult.getName()))) {
                SkippedTestsReport.concat(
                        "<h2>Suite:" + tc.getName() + "</h2>" +
                                "<table style=\"width:100%\">" +
                                "<tr>" +
                                "<th>Test Name</th>" +
                                "<th>Run</th>" +
                                "<th>Description</th>" +
                                "<th>Test Case Number </th>");
                if (testResult.getParameters().length != 0) {
                    String ClassName = "com.exalt.dataproviderinfra.datareader." + testResult.getName();
                    Class<?> dataClass = Class.forName(ClassName); // convert string classname to class
                    Object instance = dataClass.newInstance();
                    String methodName = "getExcelData";
                    Method getNameMethod = instance.getClass().getMethod(methodName);
                    excelData = (Map<Integer, ArrayList<String>>) getNameMethod.invoke(instance);
                    for (int i = 0; i < excelData.size(); ++i) {
                        if (excelData.get(i).get(0).equals("no")) {
                            SkippedTestsReport.concat(
                                    "</tr>" +
                                            "<tr>" +
                                            "<td>" + testResult.getName() + "</td>" +
                                            "<td>" + excelData.get(i).get(0) + "</td>" +
                                            "<td>" + excelData.get(i).get(2) + "</td>" +
                                            "<td>" + excelData.get(i).get(1) + " </td>" +
                                            "</tr>");
                        }
                    }
                } else {
                    SkippedTestsReport.concat(
                            "</tr>" +
                                    "<tr>" +
                                    "<td>" + testResult.getName() + "</td>" +
                                    "<td>" + "No test case currently" + " </td>" +
                                    "<td>" + "No test case currently" + "</td>" +
                                    "<td>" + "No test case currently" + " </td>" +

                                    "</tr>");

                }
                map.put(testResult.getName(), "demoValue");
            }
            SkippedTestsReport.concat("</table>");
        }
    }

    private void writePassedTestReport(@NotNull ISuiteResult sr, @NotNull ITestContext tc) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Map<String, String> map = new HashMap<String, String>();
        for (ITestResult testResult : tc.getPassedTests().getAllResults()) {
            if (!(map.containsKey(testResult.getName()))) {
                PassedTestsReport.concat(
                        "<h2>Suite:" + tc.getName() + "</h2>" +
                                "<table style=\"width:100%\">" +
                                "<tr>" +
                                "<th>Test Name</th>" +
                                "<th>Description</th>" +
                                "<th>Test Case Number </th>");
                if (testResult.getParameters().length != 0) {
                    String ClassName = "com.exalt.dataproviderinfra.datareader." + testResult.getName();
                    Class<?> dataClass = Class.forName(ClassName); // convert string classname to class
                    Object instance = dataClass.newInstance();
                    String methodName = "getExcelData";
                    Method getNameMethod = instance.getClass().getMethod(methodName);
                    excelData = (Map<Integer, ArrayList<String>>) getNameMethod.invoke(instance);
                    for (int i = 0; i < excelData.size(); ++i) {
                        if (excelData.get(i).get(3).equals("pass") && excelData.get(i).get(0).equals("yes")) {
                            PassedTestsReport.concat(
                                    "</tr>" +
                                            "<tr>" +
                                            "<td>" + testResult.getName() + "</td>" +
                                            "<td>" + excelData.get(i).get(2) + "</td>" +
                                            "<td>" + excelData.get(i).get(1) + " </td>" +
                                            "</tr>");
                        }
                    }
                } else {
                    PassedTestsReport.concat(
                            "</tr>" +
                                    "<tr>" +
                                    "<td>" + testResult.getName() + "</td>" +
                                    "<td>" + "No test case currently" + "</td>" +
                                    "<td>" + "No test case currently" + " </td>" +
                                    "</tr>");

                }
                map.put(testResult.getName(), "demoValue");
            }
            PassedTestsReport.concat("</table>");
        }
    }

    private void writeFailedTestReport(@NotNull ISuiteResult sr, @NotNull ITestContext tc) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Map<String, String> map = new HashMap<String, String>();
        for (ITestResult testResult : tc.getFailedTests().getAllResults()) {
            List<Integer> FailedInvocationList = testResult.getMethod().getFailedInvocationNumbers();
            if (!(map.containsKey(testResult.getName()))) {
                FailedTestsReport.concat(
                        "<h2>Suite:" + tc.getName() + "</h2>" +
                                "<table style=\"width:100%\">" +
                                "<tr>" +
                                "<th>Test Name</th>" +
                                "<th>Description</th>" +
                                "<th>Test Case Number </th>" +
                                "<th>Reason </th>");
                if (testResult.getParameters().length != 0) {
                    String ClassName = "com.exalt.dataproviderinfra.datareader." + testResult.getName();
                    Class<?> dataClass = Class.forName(ClassName); // convert string classname to class
                    Object instance = dataClass.newInstance();
                    String methodName = "getExcelData";
                    Method getNameMethod = instance.getClass().getMethod(methodName);
                    excelData = (Map<Integer, ArrayList<String>>) getNameMethod.invoke(instance);

                    for (int index : FailedInvocationList) {
                        if (excelData.get(index).get(0).equals("yes")) {
                            FailedTestsReport.concat(
                                    "</tr>" +
                                            "<tr>" +
                                            "<td>" + testResult.getName() + "</td>" +
                                            "<td>" + excelData.get(index).get(2) + "</td>" +
                                            "<td>" + excelData.get(index).get(1) + " </td>" +
                                            "<td>" + testResult.getThrowable().getMessage().substring(0, 158) + " </td>" +
                                            "</tr>");
                            excelData.get(index).set(3, "failed");
                        }
                    }

                } else {
                    FailedTestsReport.concat(
                            "</tr>" +
                                    "<tr>" +
                                    "<td>" + testResult.getName() + "</td>" +
                                    "<td>" + "No test case currently" + "</td>" +
                                    "<td>" + "No test case currently" + " </td>" +
                                    "<td>" + testResult.getThrowable() + " </td>" +
                                    "</tr>");
                }
                map.put(testResult.getName(), "demoValue");
            }
            FailedTestsReport.concat("</table>");
        }
    }


    private void writeSummaryReport() {
        SummaryReport.concat(
                "<table>" +
                        "<tr>" +
                        "<td>Number of suites</td>" +
                        "<td>" + numberOfSuites + "</td> " +
                        "</tr>" +
                        "<tr>" +
                        "<td>All Tests</td>" +
                        "<td>" + (totalNumberOfFailedMethods + totalNumberOfPassedMethods + totalNumberOfSkippedMethods) + "</td>" +
                        "</tr>" +
                        "<tr> " +
                        "<td><a href=\"" + PASSED_TESTS_REPORT_LINK + "\" target=\"_blank\">Passed Tests</a></td>" +
                        "<td>" + totalNumberOfPassedMethods + "</td>" +
                        "</tr>" +
                        "<tr> " +
                        "<td><a href=\"" + FAILED_TESTS_REPORT_LINK + "\" target=\"_blank\">Failed Tests</a></td>" +
                        "<td>" + totalNumberOfFailedMethods + "</td>" +
                        "</tr>" +
                        "<tr> " +
                        "<td><a href=\"" + SKIPPED_TESTS_REPORT_LINK + "\" target=\"_blank\">Skipped Tests</a></td>" +
                        "<td>" + totalNumberOfSkippedMethods + "</td>" +
                        "</tr>" +
                        "</table>" +
                        "<br>");
    }

    private void fileOverwriting() {
        try {
            Files.write(Paths.get(SUMMARY_REPORT_PATH), SummaryReport.concatEndTags().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(Paths.get(PASSED_TESTS_REPORT_PATH), PassedTestsReport.concatEndTags().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(Paths.get(FAILED_TESTS_REPORT_PATH), FailedTestsReport.concatEndTags().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(Paths.get(SKIPPED_TESTS_REPORT_PATH), SkippedTestsReport.concatEndTags().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
