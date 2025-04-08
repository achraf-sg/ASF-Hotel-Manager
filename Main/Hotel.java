import java.util.*;

public class Hotel {

    public String nom;
    public String adresse;
    public Vector <Client> listClient = new Vector <Client>();
    public Vector <Chambre> listCham = new Vector<Chambre>();
    public Vector <Produit> listProd = new Vector<Produit>();
    public Vector <Employe> listEmp = new Vector<Employe>();

    public Hotel(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    // Getters
    public String getNom() {
        return nom;
    }
    public String getAdresse() {
        return adresse;
    }
    public Vector <Client> getListClient() {
        return listClient;
    }
    public Vector <Chambre> getListCham() {
        return listCham;
    }
    public Vector <Produit> getListProd() {
        return listProd;
    }
    public Vector <Employe> getListEmp() {
        return listEmp;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    // Add methods
    public void addClient(Client c) {
        listClient.add(c);
    }
    public void addChambre(Chambre c) {
        listCham.add(c);
    }
    public void addProduit(Produit p) {
        listProd.add(p);
    }
    public void addEmploye(Employe e) {
        listEmp.add(e);
    }

}