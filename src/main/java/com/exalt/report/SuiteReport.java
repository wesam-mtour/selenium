package com.exalt.report;

public class SuiteReport {
    final static String TEST_REPORT_URL = "http://localhost:63342/automation-project/Passed%20Tests%20Report.html?_ijt=sr3628kumtgp99nloghuk5bpei";
    final static String SUMMARY_REPORT_URL = "http://localhost:63342/automation-project/Summary%20Report.html?_ijt=sr3628kumtgp99nloghuk5bpei";

    static private String code =
            "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "    <style>" +
                    "table, td {" +
                    "  border: 1px solid black;" +
                    "}" +
                    "" +
                    "" +
                    "    </style>" +
                    "</head>" +
                    "<body>" +
                    "<p style=\"text-indent: 500px\">TESTS RESULTS</p>" +
                    "<br>" +
                    "<table style=\"width:60%\">" +
                    "    <tr>" +
                    "        <td style=\"font-weight: bold;\" width=\"5%\">Suite name </td>" +
                    "        <td style=\"font-weight: bold;\" width=\"5%\"> <a href=\"" + TEST_REPORT_URL + "\" target=\"_blank\">Tests Passed</a></td>" +
                    "        <td style=\"font-weight: bold;\" width=\"5%\">Tests failed</td>" +
                    "        <td style=\"font-weight: bold;\" width=\"5%\">Total Time</td>" +
                    "    </tr>";


    public static String getCode() {
        return code;
    }

    public static String concat(String addedCode) {
        SuiteReport.code = SuiteReport.code.concat(addedCode);
        return getCode();
    }

    public static String concatEndTags() {
        SuiteReport.code = SuiteReport.code.concat(
                "</table>" +
                        "<br>" +
                        "<h><a href=\"" + SUMMARY_REPORT_URL + "\"\" target=\"_blank\">Back to summary report </a></h>\n" +
                        "</body>" +
                        "</html>"
        );
        return getCode();
    }
}
