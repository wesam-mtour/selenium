package com.exalt.reports.generatedreports;

public class SummaryReport {
    final static String METHODS_REPORT_URL = "http://localhost:63342/automation-project/test-output/Methods%20Report.html?_ijt=sr3628kumtgp99nloghuk5bpei";
    static private String code =
            "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<title>Summary Report</title>"+
                    "    <style>" +
                    "table, td {" +
                    "  border: 1px solid black;" +
                    "}" +
                    "" +
                    "" +
                    "    </style>" +
                    "</head>" +
                    "<body>" +
                    "<p style=\"text-indent: 500px\">Summary Report</p>" +
                    "<br>" +
                    "<table style=\"width:60%\">" +
                    "    <tr>" +
                    "        <td style=\"font-weight: bold;\" width=\"5%\">Tests</td>" +
                    "        <td style=\"font-weight: bold;\" width=\"5%\"> <a href=\"" + METHODS_REPORT_URL + "\" target=\"_blank\">Methods passed</a></td>" +
                    "        <td style=\"font-weight: bold;\" width=\"5%\">Methods failed</td>" +
                    "        <td style=\"font-weight: bold;\" width=\"5%\">Methods skipped</td>" +
                    "        <td style=\"font-weight: bold;\" width=\"5%\">Total Time</td>" +
                    "    </tr>";

    public static String getCode() {
        return code;
    }
    public static String concat(String addedCode) {
        SummaryReport.code = SummaryReport.code.concat(addedCode);
        return getCode();
    }

    public static String concatEndTags() {
        SummaryReport.code = SummaryReport.code.concat(
                "</table>" +
                        "<br>" +
                        "</body>" +
                        "</html>"
        );
        return getCode();
    }

}
