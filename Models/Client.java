package Models;
import java.util.*;

public class Client {

  private String nom;
  private String prenom;
  private String telephone;
  private String email;
  private boolean isBanned;
  private Vector<Reservation> listReservation = new Vector<Reservation>();
  private Vector<Sejour> listSejour = new Vector<Sejour>(); // New field for storing stays
  private Hotel hotel;
  private int idClient;
  private static int idCounter = 0;
  private String preferance;

  public Client(String nom, String prenom, String telephone, String email, Hotel hotel, String preferance) {
    this.idClient = idCounter++;
    this.nom = nom;
    this.prenom = prenom;
    this.telephone = telephone;
    this.email = email;
    this.hotel = hotel;
    this.isBanned = false;
    this.preferance = preferance;
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

  public Vector<Reservation> getListReservation() {
    return listReservation;
  }

  public Vector<Sejour> getListSejour() { // Getter for stays
    return listSejour;
  }

  public Hotel getHotel() {
    return hotel;
  }

  public int getIdClient() {
    return idClient;
  }

  public String getPreferance() {
    return preferance;
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

  public void setPreferance(String preferance) {
    this.preferance = preferance;
  }

  // Add methods
  public void addReservation(Reservation r) {
    listReservation.add(r);
  }

  public void addSejour(Sejour s) { // Add method for stays
    listSejour.add(s);
  }
}