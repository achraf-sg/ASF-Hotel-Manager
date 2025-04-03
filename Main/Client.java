import java.io.*;
import java.util.*;

public class Client {

  public int id;
  public String nom;
  public String prenom;
  public String email;
  public boolean isBanned;
  public Vector <Reservation> listReservation = new Vector <Reservation>();
  public Hotel hotel;
  
  public Client(int id, String nom, String prenom, String email, Hotel hotel) {
    this.id = id;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.hotel = hotel;
    this.isBanned = false;
  }

  public void addReservation(Reservation r) {
    listReservation.add(r);
  }



}