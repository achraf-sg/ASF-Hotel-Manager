
import java.io.*;
import java.util.*;

public class Produit {

    public int id;
    public String nom;
    public float prixUnit;
    private Vector <Type> listpProduit = new Vector <Type>();
    private Vector <Consommation> listpConsommation = new Vector <Consommation>();
    public Hotel hotel;

    public Produit() {
    }
}