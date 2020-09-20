package com.exalt.report;

public class TestsReport {

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
                    "        <td style=\"font-weight: bold;\" width=\"5%\">MethodsPassed</td>" +
                    "        <td style=\"font-weight: bold;\" width=\"5%\"># skipped</td>" +
                    "        <td style=\"font-weight: bold;\" width=\"5%\"># failed</td>" +
                    "        <td style=\"font-weight: bold;\" width=\"5%\">Total Time</td>" +
                    "    </tr>";

    public static String getCode() {
        return code;
    }
    public static String concat(String addedCode) {
        TestsReport.code = TestsReport.code.concat(addedCode);
        return getCode();
    }

    public static String concatEndTags() {
        TestsReport.code = TestsReport.code.concat(
                "</table>" +
                        "<br>" +
                        "</body>" +
                        "</html>"
        );
        return getCode();
    }

}
