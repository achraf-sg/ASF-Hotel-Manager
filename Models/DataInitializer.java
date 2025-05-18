package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataInitializer {
    private static Random random = new Random();
    
    public static Hotel initializeHotel() {
        Hotel hotel = new Hotel("ASF Grand Hotel & Suites", "123 Luxury Avenue, Paris");

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

        // Initialize some ongoing stays
        initializeStays(hotel);

        return hotel;
    }

    private static void initializeEmployees(Hotel hotel) {
        List<Employe> employees = new ArrayList<>();
        
        // Admin staff
        employees.add(new Admin("Martin", "Dubois", "15 Rue de Rivoli, Paris", "+33612345678", "admin", hotel, "admin"));
        employees.add(new Admin("Sophie", "Laurent", "27 Avenue Foch, Paris", "+33623456789", "sophie", hotel, "sophie123"));
        employees.add(new Admin("Thomas", "Moreau", "8 Place de la Concorde, Paris", "+33634567890", "thomas", hotel, "thomas123"));
        
        // Reception staff
        employees.add(new Reception("Emma", "Bernard", "42 Rue de la Paix, Paris", "+33645678901", "emma", hotel, "emma123"));
        employees.add(new Reception("Lucas", "Petit", "19 Avenue Montaigne, Paris", "+33656789012", "lucas", hotel, "lucas123"));
        employees.add(new Reception("Camille", "Leroy", "56 Boulevard Haussmann, Paris", "+33667890123", "camille", hotel, "camille123"));
        employees.add(new Reception("Antoine", "Girard", "34 Rue du Faubourg Saint-Honoré, Paris", "+33678901234", "antoine", hotel, "antoine123"));
        employees.add(new Reception("Léa", "Fournier", "21 Place Vendôme, Paris", "+33689012345", "lea", hotel, "lea123"));
        employees.add(new Reception("Hugo", "Dupont", "63 Rue Saint-Honoré, Paris", "+33690123456", "hugo", hotel, "hugo123"));
        
        // Cleaning staff
        employees.add(new Menage("Marie", "Rousseau", "17 Rue de Varenne, Paris", "+33701234567", "marie", hotel, "marie123"));
        employees.add(new Menage("Paul", "Mercier", "29 Rue de Grenelle, Paris", "+33712345678", "paul", hotel, "paul123"));
        employees.add(new Menage("Chloé", "Blanc", "41 Avenue George V, Paris", "+33723456789", "chloe", hotel, "chloe123"));
        employees.add(new Menage("Louis", "Guerin", "53 Rue de Passy, Paris", "+33734567890", "louis", hotel, "louis123"));
        employees.add(new Menage("Julie", "Muller", "76 Boulevard Saint-Germain, Paris", "+33745678901", "julie", hotel, "julie123"));

        for (Employe employee : employees) {
            hotel.addEmployee(employee);
        }
    }

    private static List<RoomType> initializeRoomTypes(Hotel hotel) {
        List<RoomType> roomTypes = new ArrayList<>();
        roomTypes.add(new RoomType("Single", 120.0f, 1));
        roomTypes.add(new RoomType("Double", 180.0f, 2));
        roomTypes.add(new RoomType("Twin", 190.0f, 2));
        roomTypes.add(new RoomType("Family", 250.0f, 4));
        roomTypes.add(new RoomType("Junior Suite", 320.0f, 2));
        roomTypes.add(new RoomType("Executive Suite", 450.0f, 2));
        roomTypes.add(new RoomType("Penthouse", 750.0f, 4));

        for (RoomType type : roomTypes) {
            hotel.getListTypes().add(type);
        }

        return roomTypes;
    }

    private static void initializeRooms(Hotel hotel, List<RoomType> roomTypes) {
        // Create 100 rooms spread across 10 floors
        for (int floor = 1; floor <= 10; floor++) {
            // Each floor has 10 rooms
            for (int roomNum = 1; roomNum <= 10; roomNum++) {
                int roomNumber = floor * 100 + roomNum;
                
                // Assign room types based on floor
                RoomType type;
                if (floor == 10) {
                    // Penthouse on top floor
                    type = roomTypes.get(6); // Penthouse
                } else if (floor >= 8) {
                    // Executive Suites on high floors
                    type = roomTypes.get(5); // Executive Suite
                } else if (floor >= 6) {
                    // Junior Suites on mid-high floors
                    type = roomTypes.get(4); // Junior Suite
                } else if (floor >= 4) {
                    // Mix of Family and Twin rooms on mid floors
                    type = roomTypes.get(roomNum % 2 == 0 ? 2 : 3); // Twin or Family
                } else {
                    // Single and Double rooms on lower floors
                    type = roomTypes.get(roomNum % 2); // Single or Double
                }
                
                Chambre room = new Chambre(roomNumber, floor, type, hotel);
                
                // Make 30% of rooms dirty for testing cleaning functionality
                if (random.nextInt(10) < 3) {
                    room.setClean(false);
                }
                
                hotel.addChambre(room);
            }
        }
    }

    private static void initializeClients(Hotel hotel) {
        List<Client> clients = new ArrayList<>();
        
        // French clients
        clients.add(new Client("Jean", "Dupont", "+33601020304", "jean.dupont@gmail.com", hotel));
        clients.add(new Client("Marie", "Lefebvre", "+33606070809", "marie.lefebvre@yahoo.fr", hotel));
        clients.add(new Client("Pierre", "Martin", "+33610111213", "pierre.martin@hotmail.com", hotel));
        clients.add(new Client("Sophie", "Bernard", "+33614151617", "sophie.bernard@orange.fr", hotel));
        clients.add(new Client("Nicolas", "Robert", "+33618192021", "nicolas.robert@free.fr", hotel));
        clients.add(new Client("Isabelle", "Richard", "+33622232425", "isabelle.richard@gmail.com", hotel));
        clients.add(new Client("François", "Durand", "+33626272829", "francois.durand@yahoo.fr", hotel));
        clients.add(new Client("Catherine", "Moreau", "+33630313233", "catherine.moreau@gmail.com", hotel));
        clients.add(new Client("Michel", "Lambert", "+33634353637", "michel.lambert@sfr.fr", hotel));
        clients.add(new Client("Nathalie", "Girard", "+33638394041", "nathalie.girard@outlook.com", hotel));
        
        // International clients
        clients.add(new Client("John", "Smith", "+44712345678", "john.smith@gmail.com", hotel));
        clients.add(new Client("Emma", "Johnson", "+447987654321", "emma.johnson@hotmail.co.uk", hotel));
        clients.add(new Client("Michael", "Williams", "+14155552671", "michael.williams@yahoo.com", hotel));
        clients.add(new Client("Sarah", "Brown", "+14155552672", "sarah.brown@gmail.com", hotel));
        clients.add(new Client("David", "Jones", "+61234567890", "david.jones@outlook.com.au", hotel));
        clients.add(new Client("Lisa", "Miller", "+61987654321", "lisa.miller@gmail.com", hotel));
        clients.add(new Client("Hans", "Schmidt", "+491234567890", "hans.schmidt@gmx.de", hotel));
        clients.add(new Client("Anna", "Müller", "+491987654321", "anna.mueller@web.de", hotel));
        clients.add(new Client("Carlos", "Garcia", "+34612345678", "carlos.garcia@gmail.com", hotel));
        clients.add(new Client("Maria", "Rodriguez", "+34698765432", "maria.rodriguez@hotmail.es", hotel));
        
        // Business clients
        clients.add(new Client("Robert", "Wilson", "+14155552673", "rwilson@acmetech.com", hotel));
        clients.add(new Client("Jennifer", "Taylor", "+14155552674", "jtaylor@globalinc.com", hotel));
        clients.add(new Client("Thomas", "Anderson", "+14155552675", "tanderson@matrix.net", hotel));
        clients.add(new Client("Elizabeth", "Clark", "+44712345679", "e.clark@britishtel.co.uk", hotel));
        clients.add(new Client("Alessandro", "Rossi", "+393123456789", "a.rossi@italiacom.it", hotel));
        clients.add(new Client("Sophia", "Martinez", "+14155552676", "smartinez@skynet.com", hotel));
        clients.add(new Client("Takashi", "Yamamoto", "+819012345678", "t.yamamoto@tokyotech.jp", hotel));
        clients.add(new Client("Olivia", "Johnson", "+14155552677", "o.johnson@westcoast.com", hotel));
        clients.add(new Client("Luis", "Fernandez", "+34612345679", "lfernandez@espana.es", hotel));
        clients.add(new Client("Charlotte", "Walker", "+61234567891", "c.walker@ausmail.com.au", hotel));
        
        // Set some clients as banned for testing
        clients.get(7).setBanned(true); // Catherine Moreau
        clients.get(13).setBanned(true); // Sarah Brown
        clients.get(23).setBanned(true); // Elizabeth Clark
        
        for (Client client : clients) {
            hotel.getListClient().add(client);
        }
    }

    private static void initializeProducts(Hotel hotel) {
        List<Produit> products = new ArrayList<>();
        
        // Beverages
        products.add(new Produit("Evian Water 500ml", 4.50f, 200, hotel));
        products.add(new Produit("Perrier Sparkling Water 330ml", 4.75f, 150, hotel));
        products.add(new Produit("Coca-Cola 330ml", 5.00f, 120, hotel));
        products.add(new Produit("Orangina 330ml", 5.00f, 100, hotel));
        products.add(new Produit("Fresh Orange Juice 250ml", 6.75f, 80, hotel));
        products.add(new Produit("Espresso Coffee", 3.50f, 500, hotel));
        products.add(new Produit("Cappuccino", 4.50f, 300, hotel));
        products.add(new Produit("Bottle of House Wine 750ml", 28.00f, 50, hotel));
        products.add(new Produit("Champagne Moët & Chandon 750ml", 95.00f, 30, hotel));
        
        // Snacks
        products.add(new Produit("Salted Peanuts 50g", 4.25f, 100, hotel));
        products.add(new Produit("Potato Chips 45g", 4.00f, 100, hotel));
        products.add(new Produit("Chocolate Bar 40g", 3.75f, 150, hotel));
        products.add(new Produit("Fruit Salad", 7.50f, 40, hotel));
        products.add(new Produit("Cheese Plate", 12.50f, 30, hotel));
        products.add(new Produit("Club Sandwich", 15.75f, 50, hotel));
        
        // Toiletries & Amenities
        products.add(new Produit("Luxury Shampoo 50ml", 8.50f, 200, hotel));
        products.add(new Produit("Conditioner 50ml", 8.50f, 200, hotel));
        products.add(new Produit("Shower Gel 50ml", 8.00f, 200, hotel));
        products.add(new Produit("Body Lotion 50ml", 9.00f, 200, hotel));
        products.add(new Produit("Dental Kit", 5.50f, 300, hotel));
        products.add(new Produit("Shaving Kit", 6.50f, 150, hotel));
        products.add(new Produit("Sewing Kit", 3.00f, 200, hotel));
        products.add(new Produit("Shower Cap", 2.00f, 400, hotel));
        products.add(new Produit("Extra Towel Set", 10.00f, 100, hotel));
        
        // Services
        products.add(new Produit("Laundry Service (per item)", 7.50f, 999, hotel));
        products.add(new Produit("Ironing Service (per item)", 5.00f, 999, hotel));
        products.add(new Produit("Shoe Shine", 12.00f, 999, hotel));
        products.add(new Produit("Room Service Delivery Fee", 5.00f, 999, hotel));
        products.add(new Produit("Late Checkout (per hour)", 30.00f, 999, hotel));
        
        for (Produit product : products) {
            hotel.getListProd().add(product);
        }
    }

    private static void initializeReservations(Hotel hotel, List<RoomType> roomTypes) {
        List<String> clientEmails = hotel.getListClient().stream()
                .map(Client::getEmail)
                .toList();
        
        // Past reservations (last month)
        LocalDate lastMonth = LocalDate.now().minusMonths(1);
        for (int i = 0; i < 15; i++) {
            LocalDate startDate = lastMonth.plusDays(i);
            LocalDate endDate = startDate.plusDays(2 + random.nextInt(5));
            
            String email = clientEmails.get(random.nextInt(clientEmails.size()));
            RoomType type = roomTypes.get(random.nextInt(roomTypes.size()));
            
            Reservation reservation = new Reservation(startDate, endDate, email, type);
            hotel.addReservation(reservation);
        }
        
        // Current reservations (this month)
        LocalDate thisMonth = LocalDate.now().withDayOfMonth(1);
        for (int i = 0; i < 20; i++) {
            LocalDate startDate = thisMonth.plusDays(i);
            LocalDate endDate = startDate.plusDays(1 + random.nextInt(7));
            
            // Skip if we're beyond today's date
            if (startDate.isAfter(LocalDate.now())) {
                continue;
            }
            
            String email = clientEmails.get(random.nextInt(clientEmails.size()));
            RoomType type = roomTypes.get(random.nextInt(roomTypes.size()));
            
            Reservation reservation = new Reservation(startDate, endDate, email, type);
            hotel.addReservation(reservation);
        }
        
        // Future reservations (next 3 months)
        LocalDate nextMonth = LocalDate.now();
        for (int i = 0; i < 35; i++) {
            LocalDate startDate = nextMonth.plusDays(i + 1);
            LocalDate endDate = startDate.plusDays(1 + random.nextInt(10));
            
            String email = clientEmails.get(random.nextInt(clientEmails.size()));
            RoomType type = roomTypes.get(random.nextInt(roomTypes.size()));
            
            Reservation reservation = new Reservation(startDate, endDate, email, type);
            hotel.addReservation(reservation);
        }
    }
    
    private static void initializeStays(Hotel hotel) {
        // Create ongoing stays (check-ins without checkout)
        List<Client> clients = hotel.getListClient();
        List<Chambre> availableRooms = new ArrayList<>();
        
        // Find available rooms
        for (Chambre room : hotel.getListCham()) {
            if (room.isAvailable() && room.isClean()) {
                availableRooms.add(room);
            }
        }
        
        // Create 5-8 ongoing stays
        int stayCount = 5 + random.nextInt(4);
        for (int i = 0; i < stayCount && i < availableRooms.size() && i < clients.size(); i++) {
            Client client = clients.get(i);
            Chambre room = availableRooms.get(i);
            
            // Set room as unavailable
            room.setAvailable(false);
            
            // Create a stay with check-in date between yesterday and today
            LocalDate checkInDate = LocalDate.now().minusDays(random.nextInt(2));
            LocalDate checkOutDate = checkInDate.plusDays(1 + random.nextInt(5));
            
            // First create a reservation
            Reservation reservation = new Reservation(checkInDate, checkOutDate, client.getEmail(), room.getType());
            reservation.setCheckedIn(true);
            hotel.addReservation(reservation);
            
            // Then create a stay with the reservation
            Sejour stay = new Sejour(reservation, client, room);
            hotel.getListSejours().add(stay);
        }
    }
}