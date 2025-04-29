package Models;
import java.util.*;

public class RoomType {

  private String type; 
  private float prix;
  private Vector<Chambre> listChambre = new Vector<Chambre>();

  // Constructor
  public RoomType(String type, float prix) {
    this.type = type;
    this.prix = prix;
  }

  // Getters
  public String getName() {
    return type;
  }
  public float getPrix() {
    return prix;
  }
  public Vector<Chambre> getListChambre() {
    return listChambre;
  }

  // Setters
  public void setType(String type) {
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