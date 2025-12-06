import java.util.*;

public class RentalSystem {
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public RentalSystem() {
        vehicles.add(new Car("C001", "Toyota", "Vios", 2000));
        vehicles.add(new Car("C002", "Honda", "City", 1800));
        vehicles.add(new Suv("S001", "Mitsubishi", "Montero", 3500));
        vehicles.add(new Suv("S002", "Toyota", "Fortuner", 3800));
        vehicles.add(new Truck("T001", "Isuzu", "Elf", 4500));
        vehicles.add(new Truck("T002", "Fuso", "Canter", 5000));
    }

    public void viewAvailableVehicles() {
        System.out.println("\n=== AVAILABLE VEHICLES ===");
        for (Vehicle v : vehicles) {
            if (!v.isRented()) v.displayInfo();
        }
        waitForEnter();
    }

    public void waitForEnter() {
        System.out.println("\nPress ENTER to continue...");
        sc.nextLine();
    }

    public void registerRental() {
        sc.nextLine();
        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        System.out.print("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter ID Type (Passport/UMID/DL/National ID): ");
        String idType = sc.nextLine();

        System.out.print("Enter ID Number: ");
        String idNum = sc.nextLine();

        System.out.println("\nAvailable Vehicles:");
        for (Vehicle v : vehicles) {
            if (!v.isRented()) v.displayInfo();
        }

        System.out.print("Enter Vehicle ID to rent: ");
        String vid = sc.nextLine();

        Vehicle selected = null;
        for (Vehicle v : vehicles) {
            if (v.getId().equalsIgnoreCase(vid) && !v.isRented()) {
                selected = v;
                break;
            }
        }

        if (selected == null) {
            System.out.println("Invalid or unavailable vehicle.");
            waitForEnter();
            return;
        }

        System.out.print("Number of days: ");
        int days = sc.nextInt();
        sc.nextLine();

        System.out.print("With driver? (yes/no): ");
        boolean withDriver = sc.nextLine().equalsIgnoreCase("yes");

        // PAYMENT
        System.out.println("\n=== PAYMENT METHOD ===");
        System.out.println("Reservation Fee: ₱500 (GCash Only)");
        System.out.println("Send payment to: 0917-000-1122 (Vroomerang Owner)");
        System.out.print("Enter your GCash number: ");
        String gcashNum = sc.nextLine();

        while (!gcashNum.matches("\\d{11}") && !gcashNum.matches("09\\d{9}")) {
            System.out.print("Invalid number. Enter again: ");
            gcashNum = sc.nextLine();
        }

        System.out.println("Payment confirmed. Thank you!");

        // CREATE RESERVATION
        Reservation res = new Reservation(name, age, idType, idNum,
                selected, days, withDriver,
                "GCash (" + gcashNum + ")", 500);

        selected.setRented(true);
        reservations.add(res);
        res.saveReceipt();

        System.out.println("\nReservation Completed! Receipt Generated.");
        waitForEnter();
    }

    public void listReservations() {
        System.out.println("\n=== ALL RESERVATIONS ===");
        for (Reservation r : reservations) {
            System.out.println(r);
        }
        waitForEnter();
    }

    public void searchBooking() {
        sc.nextLine();
        System.out.print("Enter Customer ID: ");
        String id = sc.nextLine();

        for (Reservation r : reservations) {
            if (r.getCustomerID().equals(id)) {
                System.out.println("\nFOUND:");
                System.out.println(r);
                waitForEnter();
                return;
            }
        }

        System.out.println("NOT FOUND.");
        waitForEnter();
    }

    public void returnVehicle() {
        sc.nextLine();
        System.out.print("Enter Customer ID: ");
        String id = sc.nextLine();

        for (Reservation r : reservations) {
            if (r.getCustomerID().equals(id)) {
                r.getVehicle().setRented(false);
                System.out.println("Vehicle returned successfully!");
                waitForEnter();
                return;
            }
        }

        System.out.println("Reservation not found.");
        waitForEnter();
    }
}
