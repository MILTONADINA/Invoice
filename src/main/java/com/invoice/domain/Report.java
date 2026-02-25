package com.invoice.domain;

import java.time.LocalDate;
import java.util.List;

public class Report {
    private String reportId;
    private String reportType; // "Sales", "Outstanding Invoices"
    private LocalDate generatedDate;
    private List<?> reportData; // List of Invoice or LineItem, based on report type

    // Getters and Setters
    public String getReportId() { return reportId; }
    public void setReportId(String reportId) { this.reportId = reportId; }

    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }

    public LocalDate getGeneratedDate() { return generatedDate; }
    public void setGeneratedDate(LocalDate generatedDate) { this.generatedDate = generatedDate; }

    public List<?> getReportData() { return reportData; }
    public void setReportData(List<?> reportData) { this.reportData = reportData; }
}
