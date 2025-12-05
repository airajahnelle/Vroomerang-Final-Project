public abstract class Vehicle {
    private String id;
    private String brand;
    private String model;
    private double pricePerDay;
    private boolean rented;

    public Vehicle(String id, String brand, String model, double pricePerDay) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.rented = false;
    }

    public String getId() { return id; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getPricePerDay() { return pricePerDay; }
    public boolean isRented() { return rented; }

    public void rentOut() { rented = true; }
    public void returnBack() { rented = false; }

    // Default rent calculation: base price * days + driver fee (handled here)
    // Subclasses can override to add extra fees
    public double calculateRent(int days, boolean withDriver) {
        double total = pricePerDay * days;
        if (withDriver) total += 800.0 * days;
        return total;
    }

    // display core info; subclasses may add details
    public void displayInfo() {
        System.out.printf("%-6s %-10s %-12s ₱%7.2f %s\n",
                id, brand, model, pricePerDay, rented ? "(RENTED)" : "");
    }
}
