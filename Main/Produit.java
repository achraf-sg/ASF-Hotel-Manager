
import java.io.*;
import java.util.*;

public class Produit {

    public int id;
    public String nom;
    public float prixUnit;
    private Vector <Type> listpProduit = new Vector <Type>();
    private Vector <Consommation> listpConsommation = new Vector <Consommation>();
    public Hotel hotel;

    public Produit(int id, String nom, float prixUnit, Hotel hotel) {
        this.id = id;
        this.nom = nom;
        this.prixUnit = prixUnit;
        this.hotel = hotel;
    }

    public void addType(Type t) {
        listpProduit.add(t);
    }

    public void addConsommation(Consommation c) {
        listpConsommation.add(c);
    }

}