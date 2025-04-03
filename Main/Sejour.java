import java.io.*;
import java.util.*;

public class Sejour {

    public int id;
    public boolean status;
    private Vector <Consommation> listConsommation = new Vector <Consommation>();
    public Reservation res;

    public Sejour(int id, boolean status, Reservation res) {
        this.id = id;
        this.status = status;
        this.res = res;
    }

    public void addConsommation(Consommation c) {
        listConsommation.add(c);
    }
}