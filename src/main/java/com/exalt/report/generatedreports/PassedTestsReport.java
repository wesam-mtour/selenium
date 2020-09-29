package com.exalt.report.generatedreports;


public class PassedTestsReport {
    static private String code =
            "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<title>Passed Report</title>" +
                    "<style>" +
                    "table, th, td {" +
                    "  border: 1px solid black;" +
                    "  border-collapse: collapse;" +
                    "}" +
                    "th {" +
                    "padding: 15px;" +
                    "background-color:#4ca64c" +
                    "}" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "" +
                    "<h1 style=\"text-align:center\">Passed Tests</h1>";

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
                        "</body>" +
                        "</html>"
        );
        return getCode();
    }

}
