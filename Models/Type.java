import java.util.*;

public class Type {

  private String type;
  private float prix;
  private Vector<Chambre> listChambre = new Vector<Chambre>();

  public Type(String type, float prix) {
    this.type = type;
    this.prix = prix;
  }

  // Getters
  public String getType() {
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