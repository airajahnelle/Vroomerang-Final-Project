import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Reservation {
    public String customerName;
    public String customerID;
    public String idType;
    public int age;
    public Vehicle vehicle;
    public int days;
    public boolean withDriver;

    private static final double RESERVATION_FEE = 500.0;
    private static final double CANCELLATION_FEE = 300.0;

    public Reservation(String customerName, String idType, String customerID, int age,
                       Vehicle vehicle, int days, boolean withDriver) {
        this.customerName = customerName;
        this.idType = idType;
        this.customerID = customerID;
        this.age = age;
        this.vehicle = vehicle;
        this.days = days;
        this.withDriver = withDriver;
        this.vehicle.rentOut();
    }

    public double getTotalCost() {
        return vehicle.calculateRent(days, withDriver) + RESERVATION_FEE;
    }

    public void displaySummary() {
        System.out.println("============================================");
        System.out.println("              BOOKING SUMMARY");
        System.out.println("============================================");
        System.out.println("Customer Name : " + customerName);
        System.out.println("ID Type       : " + idType);
        System.out.println("Customer ID   : " + customerID);
        System.out.println("Age           : " + age);
        System.out.println("--------------------------------------------");
        vehicle.displayInfo();
        System.out.println("Days Rented   : " + days);
        System.out.println("With Driver   : " + (withDriver ? "Yes (Php 500/day)" : "No"));
        System.out.println("Reservation Fee: Php " + RESERVATION_FEE);
        System.out.println("--------------------------------------------");
        System.out.println("TOTAL COST    : Php " + getTotalCost());
        System.out.println("============================================\n");
    }

    public void exportReceipt() {
        try {
            // Ensure "receipts" folder exists
            File folder = new File("receipts");
            if (!folder.exists()) {
                folder.mkdir();
            }

            String filename = "receipts/Reservation_" + customerID + ".txt";
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                writer.println("============================================");
                writer.println("              RESERVATION RECEIPT");
                writer.println("============================================");
                writer.println("Customer Name : " + customerName);
                writer.println("ID Type       : " + idType);
                writer.println("Customer ID   : " + customerID);
                writer.println("Age           : " + age);
                writer.println("--------------------------------------------");
                writer.printf("%-6s %-10s %-12s Php%7.2f\n",
                        vehicle.getId(), vehicle.getBrand(), vehicle.getModel(), vehicle.getPricePerDay());
                writer.println("Days Rented   : " + days);
                writer.println("With Driver   : " + (withDriver ? "Yes (Php 500/day)" : "No"));
                writer.println("Reservation Fee: Php " + RESERVATION_FEE);
                writer.println("--------------------------------------------");
                writer.println("TOTAL COST    : Php " + getTotalCost());
                writer.println("============================================");
            }

            System.out.println("Receipt saved as " + filename);
        } catch (IOException e) {
            System.out.println("Error generating receipt: " + e.getMessage());
        }
    }

    public double cancelReservation() {
        vehicle.returnBack();
        System.out.println("Cancelling reservation for " + customerName);
        System.out.println("Cancellation fee: Php " + CANCELLATION_FEE);
        double refund = getTotalCost() - CANCELLATION_FEE;
        System.out.println("Refund amount: Php " + refund);
        return refund;
    }
}
