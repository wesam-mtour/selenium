package com.exalt.report;

public class MethodsReport {
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
                    "        <td style=\"font-weight: bold;\" width=\"5%\">Test name </td>" +
                    "        <td style=\"font-weight: bold;\" width=\"5%\">Method name</td>" +
                    "        <td style=\"font-weight: bold;\" width=\"5%\">Total Time</td>" +
                    "    </tr>";

    public static String getCode() {
        return code;
    }

    public static String concat(String addedCode) {
        MethodsReport.code = MethodsReport.code.concat(addedCode);
        return getCode();
    }

    public static String concatEndTags() {
        MethodsReport.code = MethodsReport.code.concat(
                "</table>" +
                        "<br>" +
                        "<h><a href=\"" + SUMMARY_REPORT_URL + "\"\" target=\"_blank\">Back to summary report </a></h>\n" +
                        "</body>" +
                        "</html>"
        );
        return getCode();
    }

}
