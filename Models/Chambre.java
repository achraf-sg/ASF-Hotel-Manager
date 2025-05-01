package Models;
import java.util.*;

public class Chambre extends Observable{

    private int numero;
    private int etage;
    private Vector <Reservation> listReservation = new Vector <Reservation>();
    private Hotel hotel;
    private RoomType type;
    private boolean isClean = true;
    private boolean isAvailable = true;
    public Chambre(int numero, int etage, RoomType type, Hotel hotel) {
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
    public Vector <Reservation> getListReservation() {
        return listReservation;
    }
    public Hotel getHotel() {
        return hotel;
    }
    public RoomType getType() {
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
    public void setType(RoomType type) {
        this.type = type;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    } 
    public void setClean(boolean isClean) {
        this.isClean = isClean;
    }

    // Add methods

    public void addReservation(Reservation r) {
        listReservation.add(r);
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    public void changeCleanStatus(boolean isClean) {
        this.isClean = isClean;
    }


}