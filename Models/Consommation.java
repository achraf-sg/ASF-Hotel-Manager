package Models;

import java.util.Observable;

public class Consommation extends Observable{
    private Produit produit;
    private int quantite;

    public Consommation(Produit produit, int quantite) {
        if (produit.getQuantite() < quantite) {
            throw new IllegalArgumentException("Quantité demandée supérieure au stock !");
        }
        this.produit = produit;
        this.quantite = quantite;
        produit.setQuantite(produit.getQuantite() - quantite); // mise à jour automatique du stock
    }

    public double getTotal() {
        return produit.getPrixUnit() * quantite;
    }

    // Getters
    public Produit getProduit() { return produit; }
    public int getQuantite() { return quantite; }
}