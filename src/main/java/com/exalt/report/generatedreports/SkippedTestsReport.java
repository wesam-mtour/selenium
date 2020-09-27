package com.exalt.report.generatedreports;


public class SkippedTestsReport {
    static private String code =
            "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<title>Skipped Report</title>" +
                    "<style>" +
                    "table, th, td {" +
                    "  border: 1px solid black;" +
                    "  border-collapse: collapse;" +
                    "}" +
                    "th {" +
                    "padding: 15px;" +
                    "background-color:#ffff4c" +
                    "}" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<h1 style=\"text-align:center\">Skipped Tests</h1>";

    public static String getCode() {
        return code;
    }

    public static String concat(String addedCode) {
        SkippedTestsReport.code = SkippedTestsReport.code.concat(addedCode);
        return getCode();
    }

    public static String concatEndTags() {
        SkippedTestsReport.code = SkippedTestsReport.code.concat(
                "</table>" +
                        "<br>" +
                        "</body>" +
                        "</html>"
        );
        return getCode();
    }

}
