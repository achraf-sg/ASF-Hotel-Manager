package Models;
import java.util.*;

public class RoomType {

  private String type; 
  private float prix;
  private Vector<Chambre> listChambre = new Vector<Chambre>();
  private int maxPeople ;

  // Constructor
  public RoomType(String type, float prix, int maxPeople) {
    this.type = type;
    this.prix = prix;
    this.maxPeople = maxPeople;
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

  public int getMaxPeople() {
    return maxPeople;
  }

  // Setters
  public void setType(String type) {
    this.type = type;
  }
  public void setPrix(float prix) {
    this.prix = prix;
  }

  public void setMaxPeople(int max) {
    this.maxPeople = max;
  }

  // Add methods
  public void addChambre(Chambre c) {
    listChambre.add(c);
  }
}