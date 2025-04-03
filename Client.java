import java.io.*;
import java.util.*;

public class Client {

  public int id;
  public String nom;
  public String Prenom;
  public String Email;
  public boolean IsBanned;
  private Vector <Reservation> listpReservation = new Vector <Reservation>();
  public Hotel hotel;
  
  public Client() {
  }

}