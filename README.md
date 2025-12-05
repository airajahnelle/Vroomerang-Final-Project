# **Vroomerang: Book it, Ride it, Return it**

---

## 📌**Description / Overview**
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

1. Open **command prompt/terminal**.  
2. Navigate to your project folder (where `.java` files are located).  
3. Compile all Java files:
```bash
javac *.java
