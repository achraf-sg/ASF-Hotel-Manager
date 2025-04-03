import java.io.*;
import java.util.*;

public class Hotel {

    public String nom;
    public String adresse;
    public Vector <Client> listClient = new Vector <Client>();
    public Vector <Chambre> listCham = new Vector<Chambre>();
    public Vector <Produit> listProd = new Vector<Produit>();

    public Hotel(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    public void addClient(Client c) {
        listClient.add(c);
    }

    public void addChambre(Chambre c) {
        listCham.add(c);
    }

    public void addProduit(Produit p) {
        listProd.add(p);
    }

}