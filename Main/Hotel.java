import java.io.*;
import java.util.*;

public class Hotel {

    public String nom;
    public String adresse;
    private Vector <Client> listClient = new Vector <Client>();
    private Vector <Chambre> listCham = new Vector<Chambre>();
    private Vector <Produit> listProd = new Vector<Produit>();

    public Hotel() {
    }
}