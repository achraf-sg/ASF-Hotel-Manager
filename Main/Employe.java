public class Employe {
    public String nom;
    public String prenom;
    public String adresse;
    public String telephone;
    public String email;
    public Hotel hotel; 


    public Employe(String nom, String prenom, String adresse, String telephone, String email, Hotel hotel) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.hotel = hotel; 
    }

    // Getters
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getAdresse() {
        return adresse;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getEmail() {
        return email;
    }
    public Hotel getHotel() {
        return hotel;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

}
