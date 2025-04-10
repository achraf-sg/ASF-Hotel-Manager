import java.time.LocalDate;
public class Reservation {

    private int id;
    private LocalDate dateDeb;
    private LocalDate dateFin;
    private Client client;
    private Chambre chambre;

    public Reservation(int id, LocalDate dateDeb, LocalDate dateFin, Client client, Chambre chambre) {
        this.id = id;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.client = client;
        this.chambre = chambre;
    }

    // Getters
    public int getId() {
        return id;
    }
    public LocalDate getDateDeb() {
        return dateDeb;
    }
    public LocalDate getDateFin() {
        return dateFin;
    }
    public Client getClient() {
        return client;
    }
    public Chambre getChambre() {
        return chambre;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setDateDeb(LocalDate dateDeb) {
        this.dateDeb = dateDeb;
    }
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }


}