package Models;

import java.util.*;

public class Consommation {

    private int id;
    private int quantite;
    private Vector <Produit> listProduit = new Vector <Produit>();
    private Sejour sejour;

    public Consommation(int id, int quantite, Sejour sejour) {
        this.id = id;
        this.quantite = quantite;
        this.sejour = sejour;
    }

    // Getters
    public int getId() {
        return id;
    }
    public int getQuantite() {
        return quantite;
    }
    public Vector <Produit> getListProduit() {
        return listProduit;
    }
    public Sejour getSejour() {
        return sejour;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public void setSejour(Sejour sejour) {
        this.sejour = sejour;
    }

    // Add methods
    public void addProduit(Produit p) {
        listProduit.add(p);
    }
    public void removeProduit(Produit p) {
        listProduit.remove(p);
    }
    public void removeProduitByName(String name) {
        for (Produit p : listProduit) {
            if (p.getNom().equalsIgnoreCase(name)) {
                listProduit.remove(p);
                break;
            }
        }
    }

    
}