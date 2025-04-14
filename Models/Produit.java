import java.util.*;

public class Produit {
    public static int count = 0;

    private int id;
    private String nom;
    private float prixUnit;
    private Vector <Type> listpProduit = new Vector <Type>();
    private Vector <Consommation> listpConsommation = new Vector <Consommation>();
    private Hotel hotel;

    public Produit(String nom, float prixUnit, Hotel hotel) {
        this.id = count++;
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
    public Vector <Type> getListpProduit() {
        return listpProduit;
    }
    public Vector <Consommation> getListpConsommation() {
        return listpConsommation;
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


    public void addType(Type t) {
        listpProduit.add(t);
    }

    public void addConsommation(Consommation c) {
        listpConsommation.add(c);
    }

}