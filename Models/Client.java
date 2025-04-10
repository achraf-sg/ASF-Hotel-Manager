import java.util.*;

public class Client {

  private String nom;
  private String prenom;
  private String telephone;
  private String email;
  private boolean isBanned;
  private Vector <Reservation> listReservation = new Vector <Reservation>();
  private Hotel hotel;
  
  public Client( String nom, String prenom,String telephone, String email, Hotel hotel) {
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
  public Vector <Reservation> getListReservation() {
    return listReservation;
  }
  public Hotel getHotel() {
    return hotel;
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
  public void addReservation(Reservation r) {
    listReservation.add(r);
  }



}