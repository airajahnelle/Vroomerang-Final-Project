import java.util.ArrayList;
import java.util.Scanner;

public class RentalSystem {
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public RentalSystem() {
        vehicles.add(new Car("C001", "Toyota", "Vios", 2000));
        vehicles.add(new Car("C002", "Honda", "City", 1800));
        vehicles.add(new Suv("S001", "Toyota", "Fortuner", 3000));
        vehicles.add(new Suv("S002", "Mitsubishi", "Montero", 3200));
        vehicles.add(new Truck("T001", "Mitsubishi", "L300", 4000));
        vehicles.add(new Truck("T002", "Isuzu", "D-Max", 4500));
    }

    public void viewAvailableVehicles() {
        System.out.println("ID     Brand      Model        Price/day Status");
        for (Vehicle v : vehicles) {
            v.displayInfo();
        }
        waitForEnter();
    }

    public void registerRental() {
        System.out.print("Enter customer name: ");
        String name = sc.nextLine().trim();

        int age;
        while (true) {
            System.out.print("Enter age: ");
            try {
                age = Integer.parseInt(sc.nextLine().trim());
                if (age >= 18) break;
                System.out.println("Must be at least 18 years old.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number!");
            }
        }

        String idType;
        while (true) {
            System.out.print("Select ID Type (Passport / UMID / DL / National ID): ");
            idType = sc.nextLine().trim();
            if (idType.equalsIgnoreCase("Passport") || idType.equalsIgnoreCase("UMID") ||
                    idType.equalsIgnoreCase("DL") || idType.equalsIgnoreCase("National ID")) break;
            System.out.println("Invalid ID type!");
        }

        System.out.print("Enter your ID number: ");
        String customerID = sc.nextLine().trim();

        Vehicle vehicle = selectVehicle();

        int days;
        while (true) {
            System.out.print("Number of days: ");
            try {
                days = Integer.parseInt(sc.nextLine().trim());
                if (days > 0) break;
                System.out.println("Must be at least 1 day.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number!");
            }
        }

        boolean withDriver;
        while (true) {
            System.out.print("With driver? (yes/no): ");
            String choice = sc.nextLine().trim().toLowerCase();
            if (choice.equals("yes")) { withDriver = true; break; }
            else if (choice.equals("no")) { withDriver = false; break; }
            else System.out.println("Type yes or no.");
        }

        Reservation res = new Reservation(name, idType, customerID, age, vehicle, days, withDriver);
        reservations.add(res);
        res.displaySummary();
        res.exportReceipt();
        waitForEnter();
    }

    public void listReservations() {
        if (reservations.isEmpty()) System.out.println("No reservations.");
        else for (Reservation r : reservations) r.displaySummary();
        waitForEnter();
    }

    public void searchBooking() {
        System.out.print("Enter Customer ID to search: ");
        String id = sc.nextLine().trim();
        boolean found = false;
        for (Reservation r : reservations) {
            if (r.getCustomerID().equals(id)) {
                r.displaySummary();
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Reservation not found.");
        waitForEnter();
    }

    public void returnVehicle() {
        System.out.print("Enter Customer ID to return vehicle: ");
        String id = sc.nextLine().trim();
        Reservation found = null;
        for (Reservation r : reservations) {
            if (r.getCustomerID().equals(id)) { found = r; break; }
        }
        if (found != null) {
            found.returnVehicle();
            reservations.remove(found);
            System.out.println("Vehicle returned successfully.");
        } else System.out.println("Reservation not found!");
        waitForEnter();
    }

    public Vehicle selectVehicle() {
        while (true) {
            System.out.println("Available Vehicles:");
            for (Vehicle v : vehicles) {
                v.displayInfo();
            }
            System.out.print("Enter vehicle ID to rent: ");
            String vid = sc.nextLine().trim();
            for (Vehicle v : vehicles) {
                if (v.getId().equalsIgnoreCase(vid) && !v.isRented()) return v;
            }
            System.out.println("Invalid ID or vehicle already rented!");
        }
    }

    public void waitForEnter() {
        System.out.println("Press ENTER to continue...");
        sc.nextLine();
    }
}
