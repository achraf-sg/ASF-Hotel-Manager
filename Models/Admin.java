package Models;

public class Admin extends Employe {

    public Admin(String nom, String prenom, String adresse, String telephone, String email, Hotel hotel,
            String password) {
        super(nom, prenom, adresse, telephone, email, hotel, password);
    }
}