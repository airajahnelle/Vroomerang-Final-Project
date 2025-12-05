# Vroomerang: Book it, Ride it, Return it

A **console-based Java Vehicle Rental System** that lets users book cars, SUVs, and trucks, choose driver options, verify age and ID, and automatically generate receipts.

---

## Features

- **Reservation Fee:** ₱500 (fixed, non-refundable)  
- **Driver Fee:** ₱800/day if requested  
- **Age Verification:** Minimum **18 years old**  
- **ID Verification:** Passport / UMID / DL / National ID  
- **Reservation Receipt:** Automatically exports a TXT file in the `receipts/` folder  
- **Vehicle Management:** Track **rented** and **available** vehicles  
- **Return Vehicle:** Remove reservations and mark vehicles as available again  

---

## How to Run

1. Open terminal or command prompt in the project root folder.  
2. Compile all Java files:

```bash
javac src/*.java
