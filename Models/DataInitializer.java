package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataInitializer {
    public static Hotel initializeHotel() {
        Hotel hotel = new Hotel("Grand Hotel", "123 Main Street");

        // Initialize Employees
        initializeEmployees(hotel);

        // Initialize Room Types
        List<RoomType> roomTypes = initializeRoomTypes(hotel);

        // Initialize Rooms
        initializeRooms(hotel, roomTypes);

        // Initialize Clients
        initializeClients(hotel);

        // Initialize Products
        initializeProducts(hotel);

        // Initialize Reservations
        initializeReservations(hotel, roomTypes);

        return hotel;
    }

    private static void initializeEmployees(Hotel hotel) {
        List<Employe> employees = new ArrayList<>();
        employees.add(new Admin(
                "Admin",
                "User",
                "Admin Address",
                "1234567890",
                "admin",
                hotel,
                "admin"
        ));
        employees.add(new Reception(
                "Receptionist",
                "Employee",
                "Reception Address",
                "0987654321",
                "reception@hotel.com",
                hotel,
                "reception123"
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

        for (Employe employee : employees) {
            hotel.addEmployee(employee);
        }
    }

    private static List<RoomType> initializeRoomTypes(Hotel hotel) {
        List<RoomType> roomTypes = new ArrayList<>();
        roomTypes.add(new RoomType("Single", 100.0f, 1));
        roomTypes.add(new RoomType("Double", 150.0f, 2));
        roomTypes.add(new RoomType("Suite", 300.0f, 3));
        roomTypes.add(new RoomType("Deluxe", 200.0f, 4));

        for (RoomType type : roomTypes) {
            hotel.getListTypes().add(type);
        }

        return roomTypes;
    }

    private static void initializeRooms(Hotel hotel, List<RoomType> roomTypes) {
        for (int i = 1; i <= 10; i++) {
            Chambre room = new Chambre(
                    100 + i, // Room number
                    i % 5 + 1, // Floor
                    roomTypes.get(i % roomTypes.size()), // Assign a room type in a round-robin fashion
                    hotel
            );
            hotel.addChambre(room);
        }
    }

    private static void initializeClients(Hotel hotel) {
        for (int i = 1; i <= 10; i++) {
            Client client = new Client(
                    "Client" + i,
                    "LastName" + i,
                    "55512345" + i,
                    "client" + i + "@example.com",
                    hotel
            );
            hotel.getListClient().add(client);
        }
    }

    private static void initializeProducts(Hotel hotel) {
        List<Produit> products = new ArrayList<>();
        products.add(new Produit("Water Bottle", 1.5f, 100, hotel));
        products.add(new Produit("Snack", 2.0f, 50, hotel));
        products.add(new Produit("Shampoo", 5.0f, 30, hotel));
        products.add(new Produit("Towel", 10.0f, 20, hotel));

        for (Produit product : products) {
            hotel.getListProd().add(product);
        }
    }

    private static void initializeReservations(Hotel hotel, List<RoomType> roomTypes) {
        for (int i = 0; i < 5; i++) {
            LocalDate startDate = LocalDate.now().plusDays(i);
            LocalDate endDate = startDate.plusDays(3);

            Reservation reservation = new Reservation(
                    startDate,
                    endDate,
                    "client" + (i + 1) + "@example.com",
                    roomTypes.get(i % roomTypes.size())
            );

            hotel.addReservation(reservation);
        }
    }
}