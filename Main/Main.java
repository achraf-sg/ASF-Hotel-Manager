import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Hotel de Paris", "123 Rue de Paris");
        Type type1 = new Type(1, "Suite", 200.0f);
        Chambre chambre1 = new Chambre(101, 1, type1, hotel);
        type1.addChambre(chambre1);
        hotel.addChambre(chambre1);
        Client client1 = new Client(1, "Salah", "Idir", "test@gmail.com", hotel);
        hotel.addClient(client1);
        Reservation reservation1 = new Reservation(1, LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 20), client1, chambre1);
        client1.addReservation(reservation1);
        chambre1.addReservation(reservation1);

        System.out.println("Details of the reservation:");
        System.out.println("Client: " + client1.nom + " " + client1.prenom);
        System.out.println("Chambre: " + chambre1.numero + ", Type: " + type1.type);
        System.out.println("Date de debut: " + reservation1.dateDeb);
        System.out.println("Date de fin: " + reservation1.dateFin);
        System.out.println("Hotel: " + hotel.nom);
    }


}
