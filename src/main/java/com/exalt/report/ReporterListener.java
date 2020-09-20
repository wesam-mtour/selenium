package com.exalt.report;

import org.jetbrains.annotations.NotNull;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ReporterListener implements IReporter {
    final String SUITE_REPORT_PATH = "Suite Report.html";
    final String TESTS_REPORT_PATH = "Test Report.html";
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
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        for (ISuite suite : suites) {
            suiteName = suite.getName();
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult sr : suiteResults.values()) {
                ITestContext tc = sr.getTestContext();

                checkAllMethodsAcceptance(tc);
                /*
                To add new row in the test report table
                 */
                addRowToTestsReport(sr, tc);
            }
        }

        /*
        To check the type of tests "parallel or not"
         */
        if (!(xmlSuites.get(0).getParallel().name().equals("NONE"))) {
            parallel = true;
            writeAsParallelTest();
        } else {
            writeAsSequentialTest();
        }
        /*
         To add new row in the suite report table
         */
        addRowToSuiteReport();
        /*
        To print the final report
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


    private void addRowToTestsReport(@NotNull ISuiteResult sr, @NotNull ITestContext tc) {
        totalNumberOfPassedMethods += tc.getPassedTests().getAllResults().size();
        totalNumberOfFailedMethods += tc.getFailedTests().getAllResults().size();
        totalNumberOfSkippedMethods += tc.getSkippedTests().getAllResults().size();
        testsTime.add(((tc.getEndDate().getTime() - tc.getStartDate().getTime()) / 1000.0));
        TestsReport.concat(
                "<tr>" +
                        "<td>" + sr.toString().substring(21, sr.toString().length() - 1) + "</td>" +
                        "<td style=\"background-color:yellowgreen;color:black;font-weight: bold;\">" + tc.getPassedTests().getAllResults().size() + "</td>" +
                        "<td style=\"background-color:red;color:black;font-weight: bold;\">" + tc.getFailedTests().getAllResults().size() + "</td>" +
                        "<td style=\"background-color:yellow;color:black;font-weight: bold;\">" + tc.getSkippedTests().getAllResults().size() + "</td>" +
                        "<td style=\"color:black;font-weight: bold;\">" + ((tc.getEndDate().getTime() - tc.getStartDate().getTime()) / 1000.0) + "</td>" +
                        "</tr>");
    }

    private void writeAsParallelTest() {
        TestsReport.concat(
                "<tr>" +
                        "<td style=\"color:black;font-weight:bold;\">" + "Total" + "</td>" +
                        "<td style=\"color:black;font-weight:bold;\">" + totalNumberOfPassedMethods + "</td>" +
                        "<td style=\"color:black;font-weight:bold;\">" + totalNumberOfFailedMethods + "</td>" +
                        "<td style=\"color:black;font-weight:bold;\">" + totalNumberOfSkippedMethods + "</td>" +
                        "<td style=\"color:black;font-weight:bold;\">" + Collections.max(testsTime) + "</td>" +
                        "</tr>");
    }

    private void writeAsSequentialTest() {
        TestsReport.concat(
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
            Files.write(Paths.get(SUITE_REPORT_PATH), SuiteReport.concatEndTags().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(Paths.get(TESTS_REPORT_PATH), TestsReport.concatEndTags().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
