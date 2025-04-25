import java.util.*;

public class Hotel {

    private String nom;
    private String adresse;
    private Vector <Client> listClient = new Vector <Client>();
    private Vector <Chambre> listCham = new Vector<Chambre>();
    private Vector <Produit> listProd = new Vector<Produit>();
    private Vector <Employe> listEmp = new Vector<Employe>();

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

    public void modifyChamStatus(int num, int etage) {
        for (Chambre ch : listCham) {
            if(ch.getNumero() == num && ch.getEtage() == etage) {
                ch.changeCleanStatus();
            }
        }
    }

    public void delChambre(Chambre c) {
        listCham.remove(c);
    }
    
    public void addProduit(Produit p) {
        listProd.add(p);
    }

    public void delProduit(Produit p) {
        listProd.remove(p);
    }

    public void addEmploye(Employe e) {
        listEmp.add(e);
    }

    public void delEmploye(Employe e) {
        listEmp.remove(e);
    }
}