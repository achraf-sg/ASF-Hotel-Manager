package Models;

import java.util.*;

public class Produit extends Observable{

    private int id;
    private String nom;
    private float prixUnit;
    private Vector <RoomType> listpProduit = new Vector <RoomType>();
    private Hotel hotel;

    public Produit(int id, String nom, float prixUnit, Hotel hotel) {
        this.id = id;
        this.nom = nom;
        this.prixUnit = prixUnit;
        this.hotel = hotel;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public float getPrixUnit() {
        return prixUnit;
    }
    public Vector <RoomType> getListpProduit() {
        return listpProduit;
    }
    public Hotel getHotel() {
        return hotel;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrixUnit(float prixUnit) {
        this.prixUnit = prixUnit;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


    public void addType(RoomType t) {
        listpProduit.add(t);
    }

}