package Models;
import java.util.*;

public class Client {

  private String nom;
  private String prenom;
  private String telephone;
  private String email;
  private boolean isBanned;
  private Vector<Sejour> listSejours = new Vector<Sejour>();
  private Hotel hotel;
  private int idClient;
  private static int idCounter = 0;

  public Client(String nom, String prenom, String telephone, String email, Hotel hotel) {
    this.idClient = idCounter++;
    this.nom = nom;
    this.prenom = prenom;
    this.telephone = telephone;
    this.email = email;
    this.hotel = hotel;
    this.isBanned = false;
    
  }

  // Getters
  public String getNom() {
    return nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public String getTelephone() {
    return telephone;
  }

  public String getEmail() {
    return email;
  }

  public boolean isBanned() {
    return isBanned;
  }

  public Vector<Sejour> getListSejours() {
    return listSejours;
  }

  public Hotel getHotel() {
    return hotel;
  }

  public int getIdClient() {
    return idClient;
  }


  // Setters
  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setBanned(boolean isBanned) {
    this.isBanned = isBanned;
  }

  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
  }

  // Add methods
  public void addSejour(Sejour s) {
    listSejours.add(s);
  }
}