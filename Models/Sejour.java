package Models;

import java.util.Observable;
import java.util.Vector;

public class Sejour extends Observable{

    private int id;
    private Vector<Consommation> listConsommation = new Vector<>();
    private Reservation res;
    private Client client;
    private Chambre chambre;
    private static int idCounter = 0;

    public Sejour(Reservation res, Client client, Chambre chambre) {
        this.chambre = chambre;
        this.id = idCounter++;
        this.res = res;
        this.client = client;
    }

    // Getters
    public int getId() {
        return id;
    }

    public Vector<Consommation> getListConsommation() {
        return listConsommation;
    }

    public Reservation getReservation() {
        return res;
    }

    public double getTotal() {
        long jours = java.time.temporal.ChronoUnit.DAYS.between(res.getDateDeb(), res.getDateFin());
        double prixChambre = chambre.getType().getPrix() * jours;
        double totalConsos = 0;
        for (Consommation c : listConsommation) {
            totalConsos += c.getTotal();
        }
        return prixChambre + totalConsos;
    }

    public Client getClient() {
        return client;
    }

    public Chambre getChambre() {
        return chambre;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setReservation(Reservation res) {
        this.res = res;
    }

    // Add methods
    public void addConsommation(Consommation c) {
        listConsommation.add(c);
        setChanged();
        notifyObservers();
    }

    public void deleteConsommation(Consommation c) {
        c.getProduit().setQuantite(c.getProduit().getQuantite() + c.getQuantite());
        listConsommation.remove(c);
        setChanged();
        notifyObservers();
    }

    public Consommation searchConsommationById(int id) {
        for (Consommation consommation : listConsommation) {
            if (consommation.getId() == id) {
                return consommation;
            }
        }
        return null;
    }
}