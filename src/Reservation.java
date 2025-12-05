import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Reservation {
    private static final double RESERVATION_FEE = 500.0;

    private String customerName;
    private String idType;
    private String customerID;
    private int age;
    private Vehicle vehicle;
    private int days;
    private boolean withDriver;
    private String paymentMethod;
    private String paymentReference;

    public Reservation(String customerName, String idType, String customerID, int age,
                       Vehicle vehicle, int days, boolean withDriver) {
        this.customerName = customerName;
        this.idType = idType;
        this.customerID = customerID;
        this.age = age;
        this.vehicle = vehicle;
        this.days = days;
        this.withDriver = withDriver;
    }

    public double getTotalCost() {
        double baseCost = vehicle.calculateRent(days, withDriver);
        return baseCost + RESERVATION_FEE;
    }

    public void setPayment(String method, String reference) {
        this.paymentMethod = method;
        this.paymentReference = reference;
    }

    public void displaySummary() {
        System.out.println("============================================");
        System.out.println("              BOOKING SUMMARY");
        System.out.println("============================================");
        System.out.println("Customer Name : " + customerName);
        System.out.println("ID Type       : " + idType);
        System.out.println("Customer ID   : " + customerID);
        System.out.println("Age           : " + age);
        System.out.println("Payment Method: " + paymentMethod + " (" + paymentReference + ")");
        System.out.println("--------------------------------------------");
        vehicle.displayInfo();
        System.out.println("Days Rented   : " + days);
        System.out.println("With Driver   : " + (withDriver ? "Yes (Php 500/day)" : "No"));
        System.out.println("Reservation Fee: Php" + RESERVATION_FEE);
        System.out.println("--------------------------------------------");
        System.out.println("TOTAL COST    : Php" + getTotalCost());
        System.out.println("Status        : Paid");
        System.out.println("============================================\n");
    }

    public void exportReceipt() {
        try {
            File folder = new File("receipts");
            if (!folder.exists()) folder.mkdir();

            String filename = "receipts/Reservation_" + customerID + ".txt";
            FileWriter writer = new FileWriter(filename);

            writer.write("=========== VROOMERANG RECEIPT ===========\n");
            writer.write("Customer Name : " + customerName + "\n");
            writer.write("ID Type       : " + idType + "\n");
            writer.write("Customer ID   : " + customerID + "\n");
            writer.write("Age           : " + age + "\n");
            writer.write("Payment Method: " + paymentMethod + " (" + paymentReference + ")\n");
            writer.write("----------------------------------------\n");
            writer.write(vehicle.getId() + "   " + vehicle.getBrand() + "   " + vehicle.getModel() + "   ₱" + vehicle.getPricePerDay() + "\n");
            writer.write("Days Rented   : " + days + "\n");
            writer.write("With Driver   : " + (withDriver ? "Yes (Php 500/day)" : "No") + "\n");
            writer.write("Reservation Fee: Php" + RESERVATION_FEE + "\n");
            writer.write("----------------------------------------\n");
            writer.write("TOTAL COST    : Php" + getTotalCost() + "\n");
            writer.write("Status        : Paid\n");
            writer.write("========================================\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error generating receipt: " + e.getMessage());
        }
    }
}
