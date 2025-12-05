import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RentalSystem system = new RentalSystem();
        Scanner sc = new Scanner(System.in);

        clearScreen();
        System.out.println("==============================================");
        System.out.println("                 VROOMERANG SYSTEM            ");
        System.out.println("           Book it ~ Ride it ~ Return it      ");
        System.out.println("==============================================");
        System.out.println("Press ENTER to start...");
        sc.nextLine();

        int choice = 0;
        do {
            clearScreen();
            System.out.println("=== Vroomerang Vehicle Rental ===");
            System.out.println("1. View Available Vehicles");
            System.out.println("2. Register Rental");
            System.out.println("3. List Reservations");
            System.out.println("4. Search Booking");
            System.out.println("5. Return Vehicle");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Please enter a number (1-6).");
                sc.next(); 
                System.out.print("Enter choice: ");
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: system.viewAvailableVehicles(); break;
                case 2: system.registerRental(); break;
                case 3: system.listReservations(); break;
                case 4: system.searchBooking(); break;
                case 5: system.returnVehicle(); break;
                case 6: System.out.println("Exiting... Goodbye!"); break;
                default: System.out.println("Invalid choice. Try again."); system.waitForEnter();
            }
        } while (choice != 6);

        sc.close();
    }

    public static void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else System.out.print("\033[H\033[2J"); System.out.flush();
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }
}
