public class Admin extends Employe {

    public Admin(String nom, String prenom, String adresse, String telephone, String email, Hotel hotel, String login, String password) {
        super(nom, prenom, adresse, telephone, email, hotel, login, password);
    }
    // methods for rooms
    public void addChambre(int numero, int etage, Type type) {
        Chambre newChambre = new Chambre(numero, etage, type, super.getHotel());
        super.getHotel().addChambre(newChambre);
    }

    public void delChambre(Chambre c) {
        super.getHotel().delChambre(c);
    }

    public void modifyChamStatus(int num, int etage) {
        super.getHotel().modifyChamStatus(num, etage);
    }
    //methods for employees
    public void addEmploye(Employe e) {
        super.getHotel().addEmploye(e);//////////////////////////////////////
    }

    public void delEmploye(Employe e) {
        super.getHotel().delEmploye(e);
    }
}