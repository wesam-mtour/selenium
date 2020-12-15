package com.exalt.report.listeners;

import com.exalt.report.generatedreports.*;
import org.jetbrains.annotations.NotNull;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.IOException;
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
                writeFailedTestReport(tc);

                /*
                Writing a report of successful tests
                 */

                writePassedTestReport(tc);
                /*
                skipped test report not completed, has many arguments to be improve
                 */
                 writeSkippedTestReport( tc);
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

    private void writeSkippedTestReport(@NotNull ITestContext tc) {
        /*
        not completed, has many lines to be improved
         */
        Map<String, String> map = new HashMap<String, String>();
        SkippedTestsReport.concat(
                "<h2>Suite:" + tc.getName() + "</h2>" +
                        "<table style=\"width:100%\">" +
                        "<tr>" +
                        "<th>Class Name</th>" +
                        "<th>Test Name</th>" +
                        "<th>Test Case Number</th>" +
                        "<th>Test Description</th>" +
                        "</tr>");
        for (ITestResult testResult : tc.getSkippedTests().getAllResults()) {
            if (testResult.getParameters().length != 0) {
                SkippedTestsReport.concat(
                        "</tr>" +
                                "<tr>" +
                                "<td>" + testResult.getTestClass().getName().substring(16) + "</td>" +
                                "<td>" + testResult.getName() + "</td>" +
                                "<td>" + testResult.getParameters()[0].toString() + "</td>" +
                                "<td>" + testResult.getParameters()[1].toString() + "</td>" +
                                "</tr>");
            } else {
                SkippedTestsReport.concat(
                        "</tr>" +
                                "<tr>" +
                                "<td>" + testResult.getTestClass().getName().substring(16) + "</td>" +
                                "<td>" + testResult.getName() + "</td>" +
                                "<td>" + "No test case currently" + " </td>" +
                                "<td>" + "No test case currently" + "</td>" +
                                "</tr>");
            }

        }
        SkippedTestsReport.concat("</table>");
    }

    private void writePassedTestReport(@NotNull ITestContext tc) {
        Map<String, String> map = new HashMap<String, String>();
        PassedTestsReport.concat(
                "<h2>Suite:" + tc.getName() + "</h2>" +
                        "<table style=\"width:100%\">" +
                        "<tr>" +
                        "<th>Class Name</th>" +
                        "<th>Test Name</th>" +
                        "<th>Test Case Number</th>" +
                        "<th>Test Description</th>" +
                        "</tr>");
        for (ITestResult testResult : tc.getPassedTests().getAllResults()) {
            if (testResult.getParameters().length != 0) {
                PassedTestsReport.concat(
                        "</tr>" +
                                "<tr>" +
                                "<td>" + testResult.getTestClass().getName().substring(16) + "</td>" +
                                "<td>" + testResult.getName() + "</td>" +
                                "<td>" + testResult.getParameters()[0].toString() + "</td>" +
                                "<td>" + testResult.getParameters()[1].toString() + " </td>" +
                                "</tr>");
            } else {
                PassedTestsReport.concat(
                        "</tr>" +
                                "<tr>" +
                                "<td>" + testResult.getTestClass().getName().substring(16) + "</td>" +
                                "<td>" + testResult.getName() + "</td>" +
                                "<td>" + "No test case currently" + "</td>" +
                                "<td>" + "No test case currently" + " </td>" +
                                "</tr>");
            }

        }
        PassedTestsReport.concat("</table>");
    }

    private void writeFailedTestReport(@NotNull ITestContext tc) {
        FailedTestsReport.concat(
                "<h2>Suite:" + tc.getName() + "</h2>" +
                        "<table style=\"width:100%\">" +
                        "<tr>" +
                        "<th>Class Name</th>" +
                        "<th>Test Name</th>" +
                        "<th>Test Case Number</th>" +
                        "<th>Test Description</th>" +
                        "<th>Reason</th>" +
                        "</tr>");
        for (ITestResult testResult : tc.getFailedTests().getAllResults()) {
            if (testResult.getParameters().length != 0) {
                FailedTestsReport.concat(
                        "<tr>" +
                                "<td>" + testResult.getTestClass().getName().substring(16) + "</td>" +
                                "<td>" + testResult.getName() + "</td>" +
                                "<td>" + testResult.getParameters()[0].toString() + "</td>" +
                                "<td>" + testResult.getParameters()[1].toString() + " </td>" +
                                "<td>" + testResult.getThrowable() + " </td>" +
                                "</tr>");

            } else {
                FailedTestsReport.concat(
                        "</tr>" +
                                "<tr>" +
                                "<td>" + testResult.getTestClass().getName().substring(16) + "</td>" +
                                "<td>" + testResult.getName() + "</td>" +
                                "<td>" + "No test case currently" + "</td>" +
                                "<td>" + "No test case currently" + " </td>" +
                                "<td>" + testResult.getThrowable() + " </td>" +
                                "</tr>");
            }
        }
        FailedTestsReport.concat("</table>");
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