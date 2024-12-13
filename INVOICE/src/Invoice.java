import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice {
    private String invoiceNumber;
    private Date dateIssued;
    private Date dueDate;
    private Client client;
    private List<LineItem> lineItems;
    private BigDecimal totalAmount;
    private BigDecimal taxAmount;
    private BigDecimal discountAmount;
    private String paymentStatus; // "Paid", "Unpaid", "Overdue"

    // Getters and Setters
    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public Date getDateIssued() { return dateIssued; }
    public void setDateIssued(Date dateIssued) { this.dateIssued = dateIssued; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public List<LineItem> getLineItems() { return lineItems; }
    public void setLineItems(List<LineItem> lineItems) { this.lineItems = lineItems; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public BigDecimal getTaxAmount() { return taxAmount; }
    public void setTaxAmount(BigDecimal taxAmount) { this.taxAmount = taxAmount; }

    public BigDecimal getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    // Main method
    public static void main(String[] args) {
        // Create a client
        Client client = new Client();
        client.setClientId("C001");
        client.setName("Tech Solutions Inc.");
        client.setBillingAddress("123 Business Park, Tech City");
        client.setContactEmail("billing@techsolutions.com");
        client.setContactPhone("+123456789");

        // Create line items
        LineItem item1 = new LineItem();
        item1.setDescription("Software License - Enterprise Plan");
        item1.setQuantity(2);
        item1.setUnitPrice(new BigDecimal("500.00"));
        item1.setTotalPrice(item1.getUnitPrice().multiply(new BigDecimal(item1.getQuantity())));

        LineItem item2 = new LineItem();
        item2.setDescription("Cloud Storage - 1TB");
        item2.setQuantity(5);
        item2.setUnitPrice(new BigDecimal("100.00"));
        item2.setTotalPrice(item2.getUnitPrice().multiply(new BigDecimal(item2.getQuantity())));

        List<LineItem> lineItems = new ArrayList<>();
        lineItems.add(item1);
        lineItems.add(item2);

        // Create the invoice
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber("INV001");
        invoice.setDateIssued(new Date());
        invoice.setDueDate(new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000)); // Due in 7 days
        invoice.setClient(client);
        invoice.setLineItems(lineItems);
        invoice.setTaxAmount(new BigDecimal("150.00"));
        invoice.setDiscountAmount(new BigDecimal("100.00"));
        invoice.setTotalAmount(new BigDecimal("1400.00"));
        invoice.setPaymentStatus("Unpaid");

        // Display invoice details
        System.out.println("Invoice Details:");
        System.out.println("Invoice Number: " + invoice.getInvoiceNumber());
        System.out.println("Client: " + invoice.getClient().getName());
        System.out.println("Billing Address: " + invoice.getClient().getBillingAddress());
        System.out.println("Contact Email: " + invoice.getClient().getContactEmail());
        System.out.println("Contact Phone: " + invoice.getClient().getContactPhone());

        System.out.println("\nLine Items:");
        for (LineItem item : invoice.getLineItems()) {
            System.out.println("Description: " + item.getDescription());
            System.out.println("Quantity: " + item.getQuantity());
            System.out.println("Unit Price: $" + item.getUnitPrice());
            System.out.println("Total Price: $" + item.getTotalPrice());
            System.out.println("---------------------------");
        }

        System.out.println("Tax Amount: $" + invoice.getTaxAmount());
        System.out.println("Discount Amount: $" + invoice.getDiscountAmount());
        System.out.println("Total Amount: $" + invoice.getTotalAmount());
        System.out.println("Payment Status: " + invoice.getPaymentStatus());
    }
}
