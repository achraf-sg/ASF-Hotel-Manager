package Models;
import java.time.LocalDate;
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
                "admin@hotel.com", // Add a valid email here
                hotel,
                "admin123" // Password
        ));
        employees.add(new Employe(
                "Regular",
                "Employee",
                "Employee Address",
                "0987654321",
                "employee@hotel.com",
                hotel,
                
                "emp123"
        ));
        employees.add(new Menage(
                "Cleaner",
                "Worker",
                "Cleaner Address",
                "1122334455",
                "cleaner@hotel.com",
                hotel,
                "clean123"
        ));

        // Add all employees to the hotel
        for (Employe employee : employees) {
            hotel.addEmployee(employee);
        }

        // Create a list of clients
        List<Client> clients = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            clients.add(new Client(
                    "Client" + i,
                    "LastName" + i,
                    "55512345" + i,
                    "client" + i + "@example.com",
                    hotel
            ));
        }

        
        for (Client client : clients) {
            hotel.addClient(client);
        }

        List<Chambre> rooms = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            rooms.add(new Chambre(
                    100 + i, // Room number
                    i % 5 + 1, // Floor
                    new Type(Type.RoomType.DELUXE, 200.0f), // Room type
                    hotel
            ));
        }

        for (Chambre room : rooms) {
            hotel.addChambre(room);
        }
        for (int i = 0; i < 10; i++) {
            LocalDate startDate = LocalDate.now().plusDays(i);
            LocalDate endDate = startDate.plusDays(3);

            Reservation reservation = new Reservation(
                    startDate,
                    endDate,
                    clients.get(i),
                    rooms.get(i)
            );

            // Add reservation to client, room, and hotel
            clients.get(i).addReservation(reservation);
            rooms.get(i).addReservation(reservation);
            hotel.getReservationArray().add(reservation);
        }
        return hotel;
    }
}