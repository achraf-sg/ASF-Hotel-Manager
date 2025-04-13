// DataInitializer.java
package Models;

public class DataInitializer {
    public static Hotel initializeHotel() {
        Hotel hotel = new Hotel("Grand Hotel", "123 Main Street");

        // Create admin
        Admin admin = new Admin(
                "Admin",
                "User",
                "Admin Address",
                "1234567890",
                "admin@hotel.com",
                hotel,
                "admin",
                "admin123"
        );

        // Create employee
        Employe employee = new Employe(
                "Regular",
                "Employee",
                "Employee Address",
                "0987654321",
                "employee@hotel.com",
                hotel,
                "employee",
                "emp123"
        );

        hotel.addEmploye(admin);
        hotel.addEmploye(employee);

        return hotel;
    }
}