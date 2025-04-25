package Models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

public class Sejour {

    private int id;
    private boolean status;
    private Vector<Consommation> listConsommation = new Vector<>();
    private Reservation res;
    private float prixTotal;
    private static int idCounter = 0;

    public Sejour(boolean status, Reservation res, float prixTotal) {
        this.id = idCounter++;
        this.status = status;
        this.res = res;
        this.prixTotal = prixTotal;
    }

    // Getters
    public int getId() {
        return id;
    }

    public boolean isStatus() {
        return status;
    }

    public Vector<Consommation> getListConsommation() {
        return listConsommation;
    }

    public Reservation getReservation() {
        return res;
    }

    public float getPrixTotal() {
        return prixTotal;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setReservation(Reservation res) {
        this.res = res;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    // Add methods
    public void addConsommation(Consommation c) {
        listConsommation.add(c);
    }

    // Sejour Logic

    /**
     * Creates a stay (Sejour) for a reservation.
     */
    public static String createSejour(Reservation reservation) {
        Client client = reservation.getClient();
        Chambre chambre = reservation.getChambre();

        // Check if the client is banned
        if (client.isBanned()) {
            return "Client is banned. Cannot create a stay.";
        }

        // Check if today is the start date of the reservation
        if (!LocalDate.now().equals(reservation.getDateDeb())) {
            return "Today is not the start date of the reservation. Cannot create the stay.";
        }

        // Check if the room is available
        if (!chambre.isAvailable()) {
            return "Room is not available. Cannot create a stay.";
        }

        // Calculate the number of days for the stay
        long numberOfDays = ChronoUnit.DAYS.between(reservation.getDateDeb(), reservation.getDateFin());

        // Calculate the total price
        float prixTotal = chambre.getType().getPrix() * numberOfDays;

        // Create the stay (Sejour)
        Sejour sejour = new Sejour(true, reservation, prixTotal);

        // Add the stay to the client's list of stays
        client.addSejour(sejour);

        // Set the room as unavailable
        chambre.setAvailable(false);

        return "Stay created successfully for client: " + client.getNom() +
                " in room: " + chambre.getNumero() +
                " for " + numberOfDays + " days. Total price: " + prixTotal;
    }

    /**
     * Deletes a stay (Sejour).
     */
    public static String deleteSejour(Client client, Sejour sejour) {
        // Check if the stay exists in the client's list
        if (!client.getListSejour().contains(sejour)) {
            return "The stay does not exist for this client.";
        }

        // Remove the stay from the client's list
        client.getListSejour().remove(sejour);

        // Mark the room as available again
        sejour.getReservation().getChambre().setAvailable(true);

        return "Stay deleted successfully for client: " + client.getNom();
    }

    /**
     * Adds the total price of all consumptions to the stay.
     */
    public void addPrixTotal() {
        for (Consommation consommation : listConsommation) {
            for (Produit produit : consommation.getListProduit()) {
                this.prixTotal += produit.getPrixUnit() * consommation.getQuantite();
            }
        }
    }
}