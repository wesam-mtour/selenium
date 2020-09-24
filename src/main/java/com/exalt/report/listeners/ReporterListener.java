package com.exalt.report.listeners;

import com.exalt.dataproviderinfra.datareader.ConduitLoginPageDataReader;
import com.exalt.report.generatedreports.*;
import org.jetbrains.annotations.NotNull;
import org.testng.*;
import org.testng.internal.TestResult;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ReporterListener implements IReporter {
    final static String FAILED_TESTS_REPORT_URL = "http://localhost:63342/automation-project/test-output/Failed%20Tests%20Report.html?_ijt=sr3628kumtgp99nloghuk5bpei";
    final String SUITE_REPORT_PATH = "test-output\\Suite Report.html";
    final String SUMMARY_REPORT_PATH = "test-output\\Summary Report.html";
    final String PASSED_TESTS_REPORT_PATH = "test-output\\Passed Tests Report.html";
    final String FAILED_TESTS_REPORT_PATH = "test-output\\Failed Tests Report.html";
    final String METHODS_REPORT_URL = "test-output\\Methods Report.html";

    int totalNumberOfPassedMethods = 0;
    int totalNumberOfFailedMethods = 0;
    int totalNumberOfSkippedMethods = 0;
    int totalNumberOfPassedTests = 0;
    int totalNumberOfFailedTests = 0;
    String suiteName;
    boolean parallel = false;
    List<Double> testsTime = new ArrayList<>();

    /*
    To read the infrastructure report
     */
    ITestNGMethod[] a = new ITestNGMethod[0];

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        for (ISuite suite : suites) {
            suiteName = suite.getName();
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult sr : suiteResults.values()) {
                ITestContext tc = sr.getTestContext();
                /*
                Check the success of all methods for this test
                 */
                //   checkAllMethodsAcceptance(tc);
                /*
                To add new row in the test report table
                 */
                addRowToSummaryReport(sr, tc);
                addRowToFailedTestReport(sr, tc);

                /*
                If there are no failed methods for this test then it should be added to passed report and Methods Report
                 */
                if (tc.getFailedTests().size() == 0) {
                    //addRowToPassedTestReport(sr, tc);
                    //addRowToMethodsReport(sr, tc);
                }

            }
        }
        /*
        To check the type of tests "parallel or not"
         */
        if (!(xmlSuites.get(0).getParallel().name().equals("NONE"))) {
            parallel = true;
            //writeAsParallelTest();
        } else {
            // writeAsSequentialTest();
        }
        /*
         To add new row in the suite report table
         */
        // addRowToSuiteReport();
        /*
        Print all reports permanently
         */
        fileOverwriting();
    }

    /*
    To collect all the times that the tests in the suite are not parallel
     */
    private double totalTimeSpentByTests(@NotNull List<Double> list) {
        double sum = 0;
        for (Double i : list)
            sum += i;
        return sum;
    }

    private void addRowToMethodsReport(@NotNull ISuiteResult sr, @NotNull ITestContext tc) {
        String testName = sr.toString().substring(21, sr.toString().length() - 1);
        //  testsTime.add(((tc.getEndDate().getTime() - tc.getStartDate().getTime()) / 1000.0));
        for (ITestNGMethod iResultMap : tc.getAllTestMethods())
            MethodsReport.concat(
                    "<tr>" +
                            "<td>" + testName + "</td>" +
                            "<td>" + iResultMap.getMethodName() + "</td>" +
                            "<td style=\"color:black;font-weight: bold;\">" + "" + "</td>" +
                            "</tr>");
    }

    /*
    Check the success of all methods for this test
     */
    private void checkAllMethodsAcceptance(@NotNull ITestContext tc) {
        if (tc.getAllTestMethods().length == tc.getPassedTests().size()) {
            totalNumberOfPassedTests += 1;
        } else {
            totalNumberOfFailedTests += 1;
        }
    }

    private void addRowToSuiteReport() {
        if (parallel == true) {
            SuiteReport.concat(
                    "<tr>" +
                            "<td>" + suiteName + "</td>" +
                            "<td>" + totalNumberOfPassedTests + "</td>" +
                            "<td>" + totalNumberOfFailedTests + "</td>" +
                            "<td>" + Collections.max(testsTime) + "</td>" +
                            "</tr>"
            );
        } else {
            SuiteReport.concat(
                    "<tr>" +
                            "<td>" + suiteName + "</td>" +
                            "<td>" + totalNumberOfPassedTests + "</td>" +
                            "<td>" + totalNumberOfFailedTests + "</td>" +
                            "<td>" + totalTimeSpentByTests(testsTime) + "</td>" +
                            "</tr>"
            );
        }
    }

    private void addRowToPassedTestReport(@NotNull ISuiteResult sr, @NotNull ITestContext tc) {
        String testName = sr.toString().substring(21, sr.toString().length() - 1);
        // testsTime.add(((tc.getEndDate().getTime() - tc.getStartDate().getTime()) / 1000.0));
        PassedTestsReport.concat(
                "<tr>" +
                        "<td>" + testName + "</td>" +
                        "<td style=\"background-color:yellowgreen;color:black;font-weight: bold;\">" + tc.getPassedTests().getAllResults().size() + "</td>" +
                        "<td style=\"color:black;font-weight: bold;\">" + testsTime.get(testsTime.size() - 1) + "</td>" +
                        "</tr>");
    }

    private void addRowToFailedTestReport(@NotNull ISuiteResult sr, @NotNull ITestContext tc) {
        String testName = sr.toString().substring(21, sr.toString().length() - 1);
//
//        XmlTest classes = tc.getCurrentXmlTest();
//        for (XmlClass xmlClass : classes.getClasses()) {


        for (XmlClass xmlTest: tc.getCurrentXmlTest().getClasses()){
        }
        for (ITestResult testResult : tc.getFailedTests().getAllResults()) {
            testResult.getTestClass();
            testResult.getName();
            IClass ii = testResult.getTestClass();
            Object[] aa = testResult.getParameters();
            FailedTestsReport.concat(
                    "<h2>Suite:" + testResult.getTestClass().getName() + "</h2>" +
                            "<table style=\"width:100%\">" +
                            "<tr>" +
                            "<th>Test Name</th>" +
                            "<th>Test Case</th>" +
                            "<th>Test Case Number </th>" +
                            "<th>Reason </th>" +
                            "</tr>" +
                            "<tr>" +
                            "<td>"+testResult.getName()+"</th>" +
                            "<td>"+ConduitLoginPageDataReader.testCases.get(0)+"</th>" +
                            "<td>"+0+" </th>" +
                            "<td>"+testResult.getThrowable()+" </th>" +
                            "</tr>" +
                            "</table>");

        }
    }


    private void addRowToSummaryReport(@NotNull ISuiteResult sr, @NotNull ITestContext tc) {
        totalNumberOfPassedMethods += tc.getPassedTests().getAllResults().size();
        totalNumberOfFailedMethods += tc.getFailedTests().getAllResults().size();
        totalNumberOfSkippedMethods += tc.getSkippedTests().getAllResults().size();
        String testName = sr.toString().substring(21, sr.toString().length() - 1);
        testsTime.add(((tc.getEndDate().getTime() - tc.getStartDate().getTime()) / 1000.0));
        SummaryReport.concat(
                "<tr>" +
                        "<td>Number of suites</td>" +
                        "<td>11 wrong</td> " +
                        "</tr>" +
                        "<tr>" +
                        "<td>All Tests</td>" +
                        "<td>" + (totalNumberOfFailedMethods + totalNumberOfPassedMethods) + "</td>" +
                        "</tr>" +
                        "<tr> " +
                        "<td><a href=\"" + FAILED_TESTS_REPORT_URL + "\" target=\"_blank\">Passed Tests</a></td>" +
                        "<td>" + totalNumberOfPassedMethods + "</td>" +
                        "</tr>" +
                        "<tr> " +
                        "<td><a href=\"" + FAILED_TESTS_REPORT_URL + "\" target=\"_blank\">Failed Tests</a></td>" +
                        "<td>" + totalNumberOfFailedMethods + "</td>" +
                        "</tr>" +
                        "<tr> " +
                        "<td>Skipped Tests</td>" +
                        "<td>" + totalNumberOfSkippedMethods + "</td>" +
                        "</tr>");
    }

    private void writeAsParallelTest() {
        SummaryReport.concat(
                "<tr>" +
                        "<td style=\"color:black;font-weight:bold;\">" + "Total" + "</td>" +
                        "<td style=\"color:black;font-weight:bold;\">" + totalNumberOfPassedMethods + "</td>" +
                        "<td style=\"color:black;font-weight:bold;\">" + totalNumberOfFailedMethods + "</td>" +
                        "<td style=\"color:black;font-weight:bold;\">" + totalNumberOfSkippedMethods + "</td>" +
                        "<td style=\"color:black;font-weight:bold;\">" + Collections.max(testsTime) + "</td>" +
                        "</tr>");
    }

    private void writeAsSequentialTest() {
        SummaryReport.concat(
                "<tr>" +
                        "<td style=\"color:black;font-weight:bold;\">" + "Total" + "</td>" +
                        "<td style=\"color:black;font-weight:bold;\">" + totalNumberOfPassedMethods + "</td>" +
                        "<td style=\"color:black;font-weight:bold;\">" + totalNumberOfFailedMethods + "</td>" +
                        "<td style=\"color:black;font-weight:bold;\">" + totalNumberOfSkippedMethods + "</td>" +
                        "<td style=\"color:black;font-weight:bold;\">" + totalTimeSpentByTests(testsTime) + "</td>" +
                        "</tr>");
    }

    private void fileOverwriting() {
        try {
            Files.write(Paths.get(FAILED_TESTS_REPORT_PATH), FailedTestsReport.concatEndTags().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(Paths.get(METHODS_REPORT_URL), MethodsReport.concatEndTags().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(Paths.get(PASSED_TESTS_REPORT_PATH), PassedTestsReport.concatEndTags().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(Paths.get(SUITE_REPORT_PATH), SuiteReport.concatEndTags().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(Paths.get(SUMMARY_REPORT_PATH), SummaryReport.concatEndTags().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
