package com.exalt.report;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReporterListener implements IReporter {
    static List details = new ArrayList<String>();
    static List methodsPassed = new ArrayList<String>();
    static List methodsSkipped = new ArrayList<String>();

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        for (ISuite suite : suites) {
            String suiteName = suite.getName();
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult sr : suiteResults.values()) {
                ITestContext tc = sr.getTestContext();
                details.add("Passed tests for suite '" + suiteName +
                        " and test name " + sr + "' is:" + tc.getPassedTests().getAllResults().size());
                System.out.println("Passed tests for suite '" + suiteName +
                        " and test name " + sr + "' is:" + tc.getPassedTests().getAllResults().size());
                System.out.println("Failed tests for suite '" + suiteName +
                        " and test name " + sr + "' is:" + tc.getFailedTests().getAllResults().size());
                System.out.println("Skipped tests for suite '" + suiteName +
                        " and test name " + sr + "' is:" + tc.getSkippedTests().getAllResults().size());
                System.out.println("\n");
            }
            writeResults();

        }
    }

    public static void writeResults() {
        try {
            String reportIn = new String(Files.readAllBytes(Paths.get("C:\\Users\\wesamM\\Desktop\\selenium\\r.html")));
            for (int i = 0; i < details.size(); i++) {
                reportIn = reportIn.concat(
                        "<tr>" +
                                "<td>" +
                                "        <td width=\"5%\">Ttttotal Time</td>\n" + "\n" + "    \n" +
                                "    </tr>\n" +
                                "    \n" +
                                "</table>\n" +
                                "<br>\n" +
                                "\n" +
                                "\n" +
                                "</body>\n" +
                                "</html>\n");
            }
            String reportPath = "C:\\Users\\wesamM\\Desktop\\selenium\\rr.html";
            Files.write(Paths.get(reportPath), reportIn.getBytes(), StandardOpenOption.CREATE);

        } catch (Exception e) {
            System.out.println("Error when writing report file:\n" + e.toString());
        }
    }
}
