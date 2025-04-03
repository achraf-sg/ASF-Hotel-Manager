import java.io.*;
import java.util.*;

public class Type {

  public int id;
  public String type;
  public float prix;
  private Vector<Chambre> listChambre = new Vector<Chambre>();

  public Type(int id, String type, float prix) {
    this.id = id;
    this.type = type;
    this.prix = prix;
  }

  public void addChambre(Chambre c) {
    listChambre.add(c);
  }
  
}