package Models;

import java.util.*;

public class Produit extends Observable{

    private int id;
    private static int idCounter = 0;
    private String nom;
    private float prixUnit;
    private int quantite;
    private Hotel hotel;

    public Produit(String nom, float prixUnit, int quantite, Hotel hotel) {
        this.id = ++idCounter; // Auto-increment ID
        this.nom = nom;
        this.prixUnit = prixUnit;
        this.quantite = quantite;
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
    public int getQuantite() {
        return quantite;
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
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

}