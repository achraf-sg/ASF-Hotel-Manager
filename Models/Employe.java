package Models;

import java.util.Observable;

public class Employe extends Observable{
    private int id;
    private static int idCounter = 0;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private Hotel hotel; 
    private String password;

    public Employe(String nom, String prenom, String adresse, String telephone, String email, Hotel hotel,  String password) {
        this.id = ++idCounter; 
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.hotel = hotel; 
        this.password = password;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getAdresse() {
        return adresse;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getEmail() {
        return email;
    }
    public Hotel getHotel() {
        return hotel;
    }
  
    public String getPassword() {
        return password;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    public void setPassword(String password) {
        this.password = password;
    }

   



}
