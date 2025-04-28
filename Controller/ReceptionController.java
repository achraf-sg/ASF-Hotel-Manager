package Controller;

import Models.*;
import View.HomePage;

import javax.swing.JOptionPane;
import java.time.LocalDate;

public class ReceptionController {
    private Hotel hotel;

    public ReceptionController(Hotel hotel) {
        this.hotel = hotel;
    }

    public boolean authenticateReception(String email, String password) {
        for (Employe emp : hotel.getListEmp()) {
            if (emp instanceof Reception &&
                    emp.getEmail().equals(email) &&
                    emp.getPassword().equals(password)) {
                new HomePage(emp, hotel);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a reservation. Creates a new client if the client does not already exist.
     *
     * @param clientInfo  The client information (name, email, etc.).
     * @param chambre     The room to reserve.
     * @param dateDebut   The start date of the reservation.
     * @param dateFin     The end date of the reservation.
     */
    public void addReservation(Client clientInfo, Chambre chambre, LocalDate dateDebut, LocalDate dateFin) {
        // Check if the client already exists
        Client existingClient = findClientByEmail(clientInfo.getEmail());

        if (existingClient == null) {
            // Create a new client if not found
            hotel.addClient(clientInfo);
            existingClient = clientInfo;
        }

        // Create the reservation
        String result = Reservation.createReservation(existingClient, chambre, dateDebut, dateFin);
        JOptionPane.showMessageDialog(null, result, "Add Reservation", result.contains("succès") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Finds a client by their email.
     *
     * @param email The email of the client to find.
     * @return The existing client, or null if not found.
     */
    private Client findClientByEmail(String email) {
        for (Client client : hotel.getListClient()) {
            if (client.getEmail().equalsIgnoreCase(email)) {
                return client;
            }
        }
        return null;
    }
}
