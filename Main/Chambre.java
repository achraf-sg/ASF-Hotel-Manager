import java.util.*;

public class Chambre {

    public int numero;
    public int etage;
    public Vector <Type> listType = new Vector <Type>();
    public Vector <Reservation> listReservation = new Vector <Reservation>();
    public Hotel hotel;
    public Type type;

    public Chambre(int numero, int etage, Type type) {
        this.numero = numero;
        this.etage = etage;
        this.type = type;
    }

    public Chambre(int numero, int etage, Type type, Hotel hotel) {
        this.numero = numero;
        this.etage = etage;
        this.type = type;
        this.hotel = hotel;
    }

    public void addType(Type t) {
        listType.add(t);
    }

    public void addReservation(Reservation r) {
        listReservation.add(r);
    }

}