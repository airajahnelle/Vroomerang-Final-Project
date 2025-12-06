import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Reservation {
    private String customerName;
    private int age;
    private String idType;
    private String customerID;
    private Vehicle vehicle;
    private int days;
    private boolean withDriver;
    private String paymentMethod;
    private double reservationFee;
    private String status;

    public Reservation(String customerName, int age, String idType, String customerID,
                       Vehicle vehicle, int days, boolean withDriver,
                       String paymentMethod, double reservationFee) {
        this.customerName = customerName;
        this.age = age;
        this.idType = idType;
        this.customerID = customerID;
        this.vehicle = vehicle;
        this.days = days;
        this.withDriver = withDriver;
        this.paymentMethod = paymentMethod;
        this.reservationFee = reservationFee;
        this.status = "Paid";
    }

    public Vehicle getVehicle() { return vehicle; }
    public String getCustomerID() { return customerID; }

    public double getTotal() {
        return vehicle.calculateRent(days, withDriver) + reservationFee;
    }

    public void saveReceipt() {
        try {
            File folder = new File("receipts");
            if (!folder.exists()) folder.mkdir();

            String filename = "receipts/Reservation_" + customerID + ".txt";
            FileWriter fw = new FileWriter(filename);
            fw.write("========== VROOMERANG RECEIPT ==========\n");
            fw.write("Customer Name : " + customerName + "\n");
            fw.write("ID Type       : " + idType + "\n");
            fw.write("Customer ID   : " + customerID + "\n");
            fw.write("Age           : " + age + "\n");
            fw.write("Payment Method: " + paymentMethod + "\n");
            fw.write("---------------------------------------\n");
            fw.write(vehicle.getId() + " " + vehicle.getBrand() + " " + vehicle.getModel() +
                    " ₱" + vehicle.getPricePerDay() + "\n");
            fw.write("Days Rented   : " + days + "\n");
            fw.write("With Driver   : " + (withDriver ? "Yes (₱500/day)" : "No") + "\n");
            fw.write("Reservation Fee: ₱" + reservationFee + "\n");
            fw.write("---------------------------------------\n");
            fw.write("TOTAL COST    : ₱" + getTotal() + "\n");
            fw.write("Status        : " + status + "\n");
            fw.write("=======================================\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error generating receipt: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return customerName + " | " + customerID + " | " + vehicle.getBrand() + " " + vehicle.getModel()
                + " | " + days + " days | " + (withDriver ? "With Driver" : "No Driver")
                + " | " + status;
    }
}
