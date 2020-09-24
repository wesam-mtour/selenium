package com.exalt.report.generatedreports;

public class PassedTestsReport {
    final static String SUMMARY_REPORT_URL = "http://localhost:63342/automation-project/test-output/Summary%20Report.html?_ijt=sr3628kumtgp99nloghuk5bpei";
    final static String METHODS_REPORT_URL = "http://localhost:63342/automation-project/test-output/Methods%20Report.html?_ijt=sr3628kumtgp99nloghuk5bpei";

    static private String code =
            "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<title>Passed tests Report</title>"+
                    "    <style>" +
                    "table, td {" +
                    "  border: 1px solid black;" +
                    "}" +
                    "" +
                    "" +
                    "    </style>" +
                    "</head>" +
                    "<body>" +
                    "<p style=\"text-indent: 500px\">PASSED TESTS</p>" +
                    "<br>" +
                    "<table style=\"width:60%\">" +
                    "    <tr>" +
                    "        <td style=\"font-weight: bold;\" width=\"5%\">Tests</td>" +
                    "        <td style=\"font-weight: bold;\" width=\"5%\"> <a href=\"" + METHODS_REPORT_URL + "\" target=\"_blank\">Passed methods</a></td>" +
                    "        <td style=\"font-weight: bold;\" width=\"5%\">Total Time</td>" +
                    "    </tr>";

    public static String getCode() {
        return code;
    }

    public static String concat(String addedCode) {
        PassedTestsReport.code = PassedTestsReport.code.concat(addedCode);
        return getCode();
    }

    public static String concatEndTags() {
        PassedTestsReport.code = PassedTestsReport.code.concat(
                "</table>" +
                        "<br>" +
                        "<h><a href=\"" + SUMMARY_REPORT_URL + "\"\" target=\"_blank\">Back to summary report </a></h>\n" +
                        "</body>" +
                        "</html>"
        );
        return getCode();
    }

}
