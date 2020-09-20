package com.exalt.report;

public class SummaryReport {

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
                    "        <td style=\"font-weight: bold;\" width=\"5%\">Tests</td>" +
                    "        <td style=\"font-weight: bold;\" width=\"5%\">Methods Passed</td>" +
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
