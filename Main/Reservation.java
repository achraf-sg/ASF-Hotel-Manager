import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Reservation {

    public int id;
    public LocalDate dateDeb;
    public LocalDate dateFin;
    public Client client;
    public Chambre chambre;

    public Reservation(int id, LocalDate dateDeb, LocalDate dateFin, Client client, Chambre chambre) {
        this.id = id;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.client = client;
        this.chambre = chambre;
    }


}