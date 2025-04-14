// DataInitializer.java
package Models;

import java.util.ArrayList;
import java.util.List;

public class DataInitializer {
    public static Hotel initializeHotel() {
        Hotel hotel = new Hotel("Grand Hotel", "123 Main Street");

        // Create a list of employees (including admins and other roles)
        List<Employe> employees = new ArrayList<>();
        employees.add(new Admin(
                "Admin",
                "User",
                "Admin Address",
                "1234567890",
                "admin@hotel.com",
                hotel,
                "admin",
                "admin123"
        ));
        employees.add(new Employe(
                "Regular",
                "Employee",
                "Employee Address",
                "0987654321",
                "employee@hotel.com",
                hotel,
                "employee",
                "emp123"
        ));
        employees.add(new Menage(
                "Cleaner",
                "Worker",
                "Cleaner Address",
                "1122334455",
                "cleaner@hotel.com",
                hotel,
                "cleaner",
                "clean123"
        ));

        // Add all employees to the hotel
        for (Employe employee : employees) {
            hotel.addEmploye(employee);
        }

        // Create a list of clients
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(
                "John",
                "Doe",
                "5551234567",
                "john.doe@example.com",
                hotel
        ));
        clients.add(new Client(
                "Jane",
                "Smith",
                "5559876543",
                "jane.smith@example.com",
                hotel
        ));

        // Add all clients to the hotel (if there's a method for this)
        for (Client client : clients) {
            // Assuming there's a method like hotel.addClient(client);
        }

        return hotel;
    }
}