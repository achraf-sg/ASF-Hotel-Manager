import java.io.*;
import java.util.*;

public class Chambre {

    public int numero;
    public int etage;
    private Vector <Type> listType = new Vector <Type>();
    private Vector <Reservation> listReservation = new Vector <Reservation>();
    public Hotel hotel;
    public Type type;

    public Chambre() {
    }
}