package Models;

import java.util.Observable;
import java.util.Vector;

public class Sejour extends Observable{

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
}