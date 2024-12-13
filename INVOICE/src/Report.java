import java.util.Date;
import java.util.List;

class Report {
    private String reportId;
    private String reportType; // "Sales", "Outstanding Invoices"
    private Date generatedDate;
    private List<?> reportData; // List of Invoice or LineItem, based on report type

    // Getters and Setters
    public String getReportId() { return reportId; }
    public void setReportId(String reportId) { this.reportId = reportId; }

    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }

    public Date getGeneratedDate() { return generatedDate; }
    public void setGeneratedDate(Date generatedDate) { this.generatedDate = generatedDate; }

    public List<?> getReportData() { return reportData; }
    public void setReportData(List<?> reportData) { this.reportData = reportData; }
}
