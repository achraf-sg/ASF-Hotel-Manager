import java.io.*;
import java.util.*;

public class Consommation {

    public int id;
    public int quantite;
    public Vector <Produit> listProduit = new Vector <Produit>();
    public Sejour sejour;

    public Consommation(int id, int quantite, Sejour sejour) {
        this.id = id;
        this.quantite = quantite;
        this.sejour = sejour;
    }

    public void addProduit(Produit p) {
        listProduit.add(p);
    }
}