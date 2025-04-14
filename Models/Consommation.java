import java.util.*;

public class Consommation {

    public static int counter = 0;
    private int id;
    private int quantite;
    private Vector <Produit> listProduit = new Vector <Produit>();
    private Sejour sejour;

    public Consommation(int quantite, Sejour sejour) {
        this.id = counter++;
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
}