package Models;
import java.util.*;

public class Type {

  // Enum for predefined room types
  public enum RoomType {
    SINGLE,
    DOUBLE,
    SUITE,
    DELUXE
  }

  private RoomType type; 
  private float prix;
  private Vector<Chambre> listChambre = new Vector<Chambre>();

  // Constructor
  public Type(RoomType type, float prix) {
    this.type = type;
    this.prix = prix;
  }

  // Getters
  public RoomType getType() {
    return type;
  }
  public float getPrix() {
    return prix;
  }
  public Vector<Chambre> getListChambre() {
    return listChambre;
  }

  // Setters
  public void setType(RoomType type) {
    this.type = type;
  }
  public void setPrix(float prix) {
    this.prix = prix;
  }

  // Add methods
  public void addChambre(Chambre c) {
    listChambre.add(c);
  }
}