# **Vroomerang: Book it, Ride it, Return it**

---

## 📌**Overview**
*Vroomerang* is a **console-based vehicle rental system** that allows users to **book, rent, and return vehicles** easily.  
It provides a **professional and smooth interface** with features such as:  

- **Viewing available vehicles** (Car, SUV, Truck)  
- **Registering rentals** with age and valid **ID verification**  
- Choosing optional **driver service**  
- Paying a **reservation fee via GCash**  
- Generating **automated receipts**  
- Returning vehicles and managing reservations  

This system solves the problem of managing vehicle rentals manually, providing an organized and **conflict-free workflow**.

---

## **OOP Concepts Applied**

### **1. Encapsulation**
- Vehicle and Reservation data are **private**, accessible via public getter/setter methods.  
- Example: `private double pricePerDay;` in `Vehicle.java`.

### **2. Inheritance**
- Base class `Vehicle` is **extended** by `Car`, `Suv`, and `Truck`.  
- This allows shared behavior (rent calculation, info display) with specific vehicle types.

### **3. Polymorphism**
- The `calculateRent()` and `displayInfo()` methods are **overridden** in subclasses if needed.  
- A `Vehicle` reference can hold any type: `Car`, `Suv`, or `Truck`.

### **4. Abstraction**
- Abstract class `Vehicle` hides complex details of vehicle types and rent calculation from main system logic.  
- Users interact with a simple `RentalSystem` interface without worrying about internal computation.

---

## **Program Structure**
**Classes and Responsibilities:**

| **Class** | **Role** |
|-----------|----------|
| `Main` | Starts the program, handles menu navigation |
| `RentalSystem` | Core logic: register rentals, return vehicles, view/search reservations |
| `Vehicle` (abstract) | Base class for all vehicle types, handles rent calculation and display |
| `Car`, `Suv`, `Truck` | Specific vehicle types inheriting from `Vehicle` |
| `Reservation` | Stores rental information, calculates total cost, handles receipt export and payment status |

---

## **How to Run the Program**
**Requirements:** Java JDK installed  

1. **Open command prompt/terminal.**  
   Navigate to your project folder where all `.java` files are located.

2. **Navigate to the project folder.**  
   Use the `cd` command to go to the folder containing your Java files.  

   ```bash
   cd "C:\Users\airaj\.vscode\java\final project\Vroomerang\VROOMERANG\VROOMERANG"

3. **Compile all Java files.**
   Run the following command in the terminal:

   ```bash
   javac *.java

4. **Run the program**
   Start the program using this command:

   ```bash
   java Main

5. **Follow on-screen instructions.**
   - Book vehicles, choose driver option, pay the **reservation fee via GCash**, and return vehicles when done.
   - The system will automatically generate a **receipt** and update the **status as Paid**

---

## **Sample Output**

```text
==============================================
                 VROOMERANG SYSTEM            
           Book it ~ Ride it ~ Return it      
==============================================
Press ENTER to start...

=== Vroomerang Vehicle Rental ===
1. View Available Vehicles
2. Register Rental
3. List Reservations
4. Search Booking
5. Return Vehicle
6. Exit
Enter choice: 2

Enter customer name: John Doe
Enter age: 22
Select ID Type (Passport / UMID / DL / National ID): Passport
Enter your ID number: P1234567
Available Vehicles:
C001  Toyota     Vios         ₱2000.00 
C002  Honda      City         ₱1800.00
Enter vehicle ID to rent: C001
Number of days: 3
With driver? (yes/no): yes
Payment Method for Reservation Fee:
Choose (1): 1
Enter GCash number (e.g., 0917-123-4567): 09171234567
Payment confirmed. Thank you!

============================================
              BOOKING SUMMARY
============================================
Customer Name : John Doe
ID Type       : Passport
Customer ID   : P1234567
Age           : 22
Payment Method: GCash (09171234567)
--------------------------------------------
C001   Toyota     Vios         ₱2000.00
Days Rented   : 3
With Driver   : Yes (₱500/day)
Reservation Fee: ₱500.0
--------------------------------------------
TOTAL COST    : ₱7100.0
Status        : Paid
============================================

--- Listing Reservations ---
Customer ID: P1234567
John Doe - Toyota Vios - 3 days - Paid

--- Searching Booking ---
Enter Customer ID to search: P1234567
Booking Found!
John Doe - Toyota Vios - 3 days - Paid

--- Returning Vehicle ---
Enter Customer ID to return vehicle: P1234567
Vehicle returned successfully.
Press ENTER to continue...
