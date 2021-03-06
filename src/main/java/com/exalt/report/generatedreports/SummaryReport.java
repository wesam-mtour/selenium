package com.exalt.report.generatedreports;

public class SummaryReport {
    static private String code =
            "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<title>Summary Report</title>" +
                    "<style>" +
                    "table, td {" +
                    "  border: 1px solid black;" +
                    "  border-collapse: collapse;" +
                    " column-width: 60px;"+
                    "}" +
                    "" +
                    "" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<h1>Summary Report for all Suites</h1>" +
                    "<br>" ;

    public static String getCode() {
        return code;
    }

    public static String concat(String addedCode) {
        SummaryReport.code = SummaryReport.code.concat(addedCode);
        return getCode();
    }

    public static String concatEndTags() {
        SummaryReport.code = SummaryReport.code.concat(
                        "<br>" +
                        "</body>" +
                        "</html>"
        );
        return getCode();
    }

}
