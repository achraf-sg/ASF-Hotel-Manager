import java.util.*;

public class Client {

  public String nom;
  public String prenom;
  public String telephone;
  public String email;
  public boolean isBanned;
  public Vector <Reservation> listReservation = new Vector <Reservation>();
  public Hotel hotel;
  
  public Client( String nom, String prenom,String telephone, String email, Hotel hotel) {
    this.nom = nom;
    this.prenom = prenom;
    this.telephone = telephone;
    this.email = email;
    this.hotel = hotel;
    this.isBanned = false;
  }

  public void addReservation(Reservation r) {
    listReservation.add(r);
  }



}