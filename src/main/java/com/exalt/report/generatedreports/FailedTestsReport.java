package com.exalt.report.generatedreports;


public class FailedTestsReport {
    static private String code =
            "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<title>Failed Report</title>" +
                    "<style>" +
                    "table, th, td {" +
                    "  border: 1px solid black;" +
                    "  border-collapse: collapse;" +
                    "}" +
                    "th {" +
                    "padding: 15px;" +
                    "background-color:#ff3232" +
                    "}" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<h1 style=\"text-align:center\">Failure Tests</h1>";

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
