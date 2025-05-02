package Models;

import java.time.LocalDate;
import java.util.Observable;

public class Reservation extends Observable {

    private int id;
    private LocalDate dateDeb;
    private LocalDate dateFin;
    private String clientMail;
    private RoomType type;
    private static int idCounter = 0;
    private boolean isCheckedIn = false, isCheckedOut = false;

    public Reservation(LocalDate dateDeb, LocalDate dateFin, String mail, RoomType type) {
        this.id = idCounter++;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.clientMail = mail;
        this.type = type;
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

    public String getClientMail() {
        return clientMail;
    }

    public RoomType getType() {
        return type;
    }

    public boolean getIsCheckedIn() {
        return isCheckedIn;
    }

    public boolean getIsCheckedOut() {
        return isCheckedOut;
    }

    // Setters
    public void setDateDeb(LocalDate dateDeb) {
        this.dateDeb = dateDeb;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public void setClientMail(String mail) {
        this.clientMail = mail;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.isCheckedIn = checkedIn;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.isCheckedOut = checkedOut;
    }

    public boolean datesOverlap(LocalDate existingStart, LocalDate existingEnd, LocalDate newStart,
            LocalDate newEnd) {
        return !(newEnd.isBefore(existingStart) || newStart.isAfter(existingEnd));
    }
}