import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
    private String gcashNumber;
    private String status;

    public Reservation(String customerName, String idType, String customerID, int age,
                       Vehicle vehicle, int days, boolean withDriver) {
        this.customerName = customerName;
        this.idType = idType;
        this.customerID = customerID;
        this.age = age;
        this.vehicle = vehicle;
        this.days = days;
        this.withDriver = withDriver;
        this.status = "Paid";
        this.paymentMethod = "GCash";
        this.gcashNumber = "";
        vehicle.rentOut();
        payReservationFee();
    }

    // Getters
    public String getCustomerID() { return customerID; }
    public String getCustomerName() { return customerName; }
    public String getStatus() { return status; }
    public Vehicle getVehicle() { return vehicle; }

    public double getTotalCost() {
        double baseCost = vehicle.calculateRent(days, withDriver);
        return baseCost + RESERVATION_FEE;
    }

    public void displaySummary() {
        System.out.println("============================================");
        System.out.println("              BOOKING SUMMARY");
        System.out.println("============================================");
        System.out.println("Customer Name : " + customerName);
        System.out.println("ID Type       : " + idType);
        System.out.println("Customer ID   : " + customerID);
        System.out.println("Age           : " + age);
        System.out.println("Payment Method: " + paymentMethod +
                           " (" + gcashNumber + ")");
        System.out.println("--------------------------------------------");

        vehicle.displayInfo();

        System.out.println("Days Rented   : " + days);
        System.out.println("With Driver   : " + (withDriver ? "Yes (Php 500/day)" : "No"));
        System.out.println("Reservation Fee: Php" + RESERVATION_FEE);
        System.out.println("--------------------------------------------");

        System.out.println("TOTAL COST    : Php" + getTotalCost());
        System.out.println("Status        : " + status);
        System.out.println("============================================\n");
    }

    public void exportReceipt() {
        try {
            File folder = new File("receipts");
            if (!folder.exists()) folder.mkdir();

            String filename = "receipts/Reservation_" + customerID + ".txt";
            FileWriter writer = new FileWriter(filename);

            writer.write("========== VROOMERANG RECEIPT ==========\n");
            writer.write("Customer Name : " + customerName + "\n");
            writer.write("ID Type       : " + idType + "\n");
            writer.write("Customer ID   : " + customerID + "\n");
            writer.write("Age           : " + age + "\n");
            writer.write("Payment Method: " + paymentMethod +
                         " (" + gcashNumber + ")\n");
            writer.write("----------------------------------------\n");
            writer.write(vehicle.getId() + " " + vehicle.getBrand() + " " + vehicle.getModel() +
                         " ₱" + vehicle.getPricePerDay() + "\n");
            writer.write("Days Rented   : " + days + "\n");
            writer.write("With Driver   : " + (withDriver ? "Yes (Php 500/day)" : "No") + "\n");
            writer.write("Reservation Fee: ₱" + RESERVATION_FEE + "\n");
            writer.write("----------------------------------------\n");
            writer.write("TOTAL COST    : Php" + getTotalCost() + "\n");
            writer.write("Status        : " + status + "\n");
            writer.write("========================================\n");

            writer.close();
        } catch (IOException e) {
            System.out.println("Error generating receipt: " + e.getMessage());
        }
    }

    private void payReservationFee() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Payment Method for Reservation Fee:");
        System.out.println("1. GCash");
        System.out.print("Choose (1): ");
        sc.nextLine();

        System.out.print("Enter GCash number (e.g., 0917-123-4567): ");
        gcashNumber = sc.nextLine().trim();
        System.out.println("Payment confirmed. Thank you!");
    }

    public void returnVehicle() {
        vehicle.returnBack();
        status = "Returned";
    }
}
