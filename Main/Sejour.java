import java.util.*;

public class Sejour {

    private int id;
    private boolean status;
    private Vector <Consommation> listConsommation = new Vector <Consommation>();
    private Reservation res;

    public Sejour(int id, boolean status, Reservation res) {
        this.id = id;
        this.status = status;
        this.res = res;
    }

    // Getters
    public int getId() {
        return id;
    }
    public boolean isStatus() {
        return status;
    }
    public Vector <Consommation> getListConsommation() {
        return listConsommation;
    }
    public Reservation getReservation() {
        return res;
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

    // Add methods
    public void addConsommation(Consommation c) {
        listConsommation.add(c);
    }
}