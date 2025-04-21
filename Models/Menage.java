package Models;

public class Menage extends Employe {

    private boolean cleaning = false; 
    public Menage(String nom, String prenom, String adresse, String telephone, String email, Hotel hotel,  String password) {
        super(nom, prenom, adresse, telephone, email, hotel,  password);
    }
    public boolean isCleaning() {
        return cleaning;
    }
    
}
