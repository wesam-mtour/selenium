package com.exalt.report.generatedreports;


public class FailedTestsReport {
    static private String code =
            "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<style>" +
                    "table, th, td {" +
                    "  border: 1px solid black;" +
                    "  border-collapse: collapse;" +
                    "}" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "" +
                    "<h1 style=\"text-align:center\">Failure Tests</h1>";
//                    "<table style=\"width:100%\">" +
//                    "  <tr>" +
//                    "<th>Class Name</th>" +
//                    "    <th>Test Case</th> " +
//                    "    <th>Test Case Number </th>" +
//                    "    <th>Reason </th>" +
//                    "</tr>";

    public static String getCode() {
        return code;
    }

    public static String concat(String addedCode) {
        FailedTestsReport.code = FailedTestsReport.code.concat(addedCode);
        return getCode();
    }

    public static String concatEndTags() {
        FailedTestsReport.code = FailedTestsReport.code.concat(
                "</table>" +
                        "<br>" +
                        "</body>" +
                        "</html>"
        );
        return getCode();
    }

}
