package Models;

import java.util.*;

public class Chambre {

    private int numero;
    private int etage;
    private Vector <Type> listType = new Vector <Type>();
    private Vector <Reservation> listReservation = new Vector <Reservation>();
    private Hotel hotel;
    private Type type;
    private boolean isClean = true;

   private  boolean isAvailable = true;
    public Chambre(int numero, int etage, Type type, Hotel hotel) {
        this.numero = numero;
        this.etage = etage;
        this.type = type;
        this.hotel = hotel;
    }

    // Getters
    public int getNumero() {
        return numero;
    }
    public int getEtage() {
        return etage;
    }
    public Vector <Type> getListType() {
        return listType;
    }
    public Vector <Reservation> getListReservation() {
        return listReservation;
    }
    public Hotel getHotel() {
        return hotel;
    }
    public Type getType() {
        return type;
    }

    public boolean isClean() {
        return isClean;
    }

public boolean isAvailable() {
        return isAvailable;
    }
    // Setters
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public void setEtage(int etage) {
        this.etage = etage;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    // Add methods
    public void addType(Type t) {
        listType.add(t);
    }

    public void addReservation(Reservation r) {
        listReservation.add(r);
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void changeCleanStatus() {
        isClean = !isClean;
    }
}