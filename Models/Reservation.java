package Models;

import java.time.LocalDate;
import java.util.Observable;

public class Reservation extends Observable{

    private int id;
    private LocalDate dateDeb;
    private LocalDate dateFin;
    private Client client;
    private Chambre chambre;
    private static int idCounter = 0;

    public Reservation(LocalDate dateDeb, LocalDate dateFin, Client client, Chambre chambre) {
        this.id = idCounter++;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.client = client;
        this.chambre = chambre;
    }

    // Getters
    public int getId() {
        return id;
    }

    public LocalDate getDateDeb() {
        return dateDeb;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public Client getClient() {
        return client;
    }

    public Chambre getChambre() {
        return chambre;
    }

    // Setters
    public void setDateDeb(LocalDate dateDeb) {
        this.dateDeb = dateDeb;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    // Reservation Logic

    /**
     * Creates a reservation.
     */
    public static String createReservation(Client client, Chambre chambre, LocalDate dateDebut, LocalDate dateFin) {
        // Check if the start date is before the end date
        if (dateDebut.isAfter(dateFin) || dateDebut.isEqual(dateFin)) {
            return "La date de début doit être avant la date de fin.";
        }

        // Check if the client is banned
        if (client.isBanned()) {
            return "Le client est banni et ne peut pas effectuer de réservation.";
        }

        // Check if the room type matches the client's preference
        if (!chambre.getType().getType().toString().equalsIgnoreCase(client.getPreferance())) {
            return "La chambre ne correspond pas à la préférence du client.";
        }

        // Check if the room is already reserved for the given dates
        for (Reservation reservation : chambre.getListReservation()) {
            if (datesOverlap(reservation.getDateDeb(), reservation.getDateFin(), dateDebut, dateFin)) {
                return "La chambre est déjà réservée pour les dates sélectionnées.";
            }
        }

        // Create the reservation
        Reservation reservation = new Reservation(dateDebut, dateFin, client, chambre);
        client.getListReservation().add(reservation);
        chambre.addReservation(reservation);

        return "Réservation créée avec succès !";
    }

    /**
     * Deletes a reservation.
     */
    public static String deleteReservation(Client client, Reservation reservation) {
        // Check if the reservation exists in the client's list
        if (!client.getListReservation().contains(reservation)) {
            return "La réservation n'existe pas pour ce client.";
        }

        // Remove the reservation from the client's list and the room's list
        client.getListReservation().remove(reservation);
        reservation.getChambre().getListReservation().remove(reservation);

        return "Réservation supprimée avec succès !";
    }

    /**
     * Updates a reservation.
     */
    public static String updateReservation(Client client, Reservation reservation, LocalDate newDateDebut,
                                           LocalDate newDateFin) {
        // Check if the reservation exists in the client's list
        if (!client.getListReservation().contains(reservation)) {
            return "La réservation n'existe pas pour ce client.";
        }

        // Check if the current date is at least one full day before the start date
        if (LocalDate.now().isAfter(reservation.getDateDeb().minusDays(1))) {
            return "La réservation ne peut être modifiée qu'au moins un jour avant la date de début.";
        }

        // Check if the new start date is before the new end date
        if (newDateDebut.isAfter(newDateFin) || newDateDebut.isEqual(newDateFin)) {
            return "La nouvelle date de début doit être avant la nouvelle date de fin.";
        }

        // Check if the room is available for the new dates
        for (Reservation existingReservation : reservation.getChambre().getListReservation()) {
            if (existingReservation != reservation && datesOverlap(existingReservation.getDateDeb(),
                    existingReservation.getDateFin(), newDateDebut, newDateFin)) {
                return "La chambre est déjà réservée pour les nouvelles dates sélectionnées.";
            }
        }

        // Update the reservation
        reservation.setDateDeb(newDateDebut);
        reservation.setDateFin(newDateFin);

        return "Réservation mise à jour avec succès !";
    }

    // Helper method to check if two date ranges overlap
    private static boolean datesOverlap(LocalDate existingStart, LocalDate existingEnd, LocalDate newStart,
                                        LocalDate newEnd) {
        return !(newEnd.isBefore(existingStart) || newStart.isAfter(existingEnd));
    }
}