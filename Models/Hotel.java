package Models;

import java.time.LocalDate;
import java.util.*;

public class Hotel extends Observable {

    private String nom;
    private String adresse;
    private Vector<Client> listClient = new Vector<Client>();
    private Vector<Chambre> listCham = new Vector<Chambre>();
    private Vector<Produit> listProd = new Vector<Produit>();
    private Vector<Employe> listEmp = new Vector<Employe>();
    private Vector<Reservation> listRes = new Vector<Reservation>();
    private Vector<RoomType> listTypes = new Vector<RoomType>();
    private Vector<Sejour> listSejours = new Vector<Sejour>();

    public Hotel(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public Vector<Client> getListClient() {
        return listClient;
    }

    public Vector<Chambre> getListCham() {
        return listCham;
    }

    public Vector<Produit> getListProd() {
        return listProd;
    }

    public Vector<Employe> getListEmp() {
        return listEmp;
    }

    public Vector<RoomType> getListTypes() {
        return listTypes;
    }

    public Vector<Reservation> getListRes() {
        return listRes;
    }

    public Vector<Sejour> getListSejours() {
        return listSejours;
    }

    public Vector<Chambre> getListChamByType(String type) {
        Vector<Chambre> filteredList = new Vector<>();
        for (Chambre cham : listCham) {
            if (cham.getType().getName().equalsIgnoreCase(type)) {
                filteredList.add(cham);
            }
        }
        return filteredList;
    }

    public Vector<Employe> getListEmpByRole(String role) {
        Vector<Employe> filteredList = new Vector<>();
        for (Employe emp : listEmp) {
            if (emp.getClass().getSimpleName().equals(role)) {
                filteredList.add(emp);
            }
        }
        return filteredList;
    }

    public Vector<Client> getBannedClients() {
        Vector<Client> bannedClients = new Vector<>();
        for (Client client : listClient) {
            if (client.isBanned()) {
                bannedClients.add(client);
            }
        }
        return bannedClients;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void addChambre(Chambre chambre) {
        listCham.add(chambre);

    }

    public Chambre findChambreByNumber(int num) {
        for (Chambre c : listCham) {
            if (c.getNumero() == num) {
                return c;
            }
        }
        return null;
    }

    // Employee methods
    public void addEmployee(Employe employe) {
        getListEmp().add(employe);
        setChanged();
        notifyObservers("Employé ajouté");
    }

    public void deleteEmployee(int employeeId) {
        Employe empToRemove = null;
        for (Employe emp : getListEmp()) {
            if (emp.getId() == employeeId) {
                empToRemove = emp;
                break;
            }
        }
        if (empToRemove != null) {
            getListEmp().remove(empToRemove);
            setChanged();
            notifyObservers("Employé Supprimé");
        }
    }

    public void updateEmployee(int id, String nom, String prenom, String email, String telephone, String adresse,
            String password) {
        for (Employe emp : getListEmp()) {
            if (emp.getId() == id) {
                if (nom != null)
                    emp.setNom(nom);
                if (prenom != null)
                    emp.setPrenom(prenom);
                if (email != null)
                    emp.setEmail(email);
                if (telephone != null)
                    emp.setTelephone(telephone);
                if (adresse != null)
                    emp.setAdresse(adresse);
                if (password != null)
                    emp.setPassword(password);

                setChanged();
                notifyObservers("Employé Modifié");
                return;
            }
        }
    }

    public Employe findEmployeeById(int employeeId) {
        for (Employe emp : getListEmp()) {
            if (emp.getId() == employeeId) {
                return emp;
            }
        }
        return null;
    }

    public Employe findEmployeeByEmail(String email) {
        for (Employe emp : getListEmp()) {
            if (emp.getEmail().equalsIgnoreCase(email)) {
                return emp;
            }
        }
        return null;
    }

    // Chambre methods

    public void addChambre(int numero, int etage, RoomType type) {
        Chambre chambre = new Chambre(numero, etage, type, this);
        listCham.add(chambre);
        setChanged();
        notifyObservers(chambre);
    }

    public void deleteChambre(int numero, int etage) {
        Chambre toRemove = null;

        for (Chambre c : listCham) {
            if (c.getNumero() == numero && c.getEtage() == etage) {
                toRemove = c;
                break;
            }
        }

        if (toRemove != null) {
            listCham.remove(toRemove);
            setChanged();
            notifyObservers();
        }
    }

    public void addType(String nom, float prix, int maxPeople) {
        RoomType type = new RoomType(nom, prix, maxPeople);
        listTypes.add(type);
        setChanged();
        notifyObservers();
    }

    // reservation methods

    public int countChambresDisponibles(String type, LocalDate dateDebut, LocalDate dateFin) {
        int totalChambres = 0;

        // Nombre total de chambres de ce type
        for (Chambre c : listCham) {
            if (c.getType().getName().equalsIgnoreCase(type)) {
                totalChambres++;
            }
        }

        // Nombre de réservations de ce type qui chevauchent la période
        for (Reservation res : listRes) {
            if (res.getType().getName().equalsIgnoreCase(type)) {
                // Vérifie s’il y a un chevauchement de dates
                if (!(res.getDateFin().isBefore(dateDebut) || res.getDateDeb().isAfter(dateFin))) {
                    totalChambres--;
                }
            }
        }

        return totalChambres;
    }

    public void addReservation(Reservation reservation) {
        getListRes().add(reservation);
        setChanged();
        notifyObservers();
    }

    public RoomType findTypeByName(String name) {
        for (RoomType type : listTypes) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

    public Reservation findReservationById(int id) {
        for (Reservation res : listRes) {
            if (res.getId() == id) {
                return res;
            }
        }
        return null;
    }

    public void deleteReservation(int id) {
        Reservation resToRemove = null;
        for (Reservation res : getListRes()) {
            if (res.getId() == id) {
                resToRemove = res;
                break;
            }
        }
        if (resToRemove != null) {
            getListRes().remove(resToRemove);
            setChanged();
            notifyObservers("Reservation Supprimé");
        }
    }

    public Produit findProduitByName(String product) {
        for (Produit p : listProd) {
            if (p.getNom().equalsIgnoreCase(product)) {
                return p;
            }
        }
        return null;
    }

    public Client findClientById(int id) {
        for (Client c : listClient) {
            if (c.getIdClient() == id) {
                return c;
            }
        }
        return null;
    }

    public void cleanupExpiredReservations() {
        LocalDate oneDayAfterStartDate = LocalDate.now().minusDays(1);
        List<Reservation> toRemove = new ArrayList<>();
        
        for (Reservation reservation : listRes) {
            // If reservation started yesterday or earlier AND not checked in
            if (reservation.getDateDeb().compareTo(oneDayAfterStartDate) <= 0 && 
                !reservation.getIsCheckedIn()) {
                toRemove.add(reservation);
            }
        }
        
        // Remove identified reservations
        if (!toRemove.isEmpty()) {
            for (Reservation reservation : toRemove) {
                listRes.remove(reservation);
            }
            setChanged();
            notifyObservers("Removed " + toRemove.size() + " expired unclaimed reservations");
            }
        }
    public void addSejour(Sejour sejour) {
        listSejours.add(sejour);
        setChanged();
        notifyObservers();
    }

    public Vector<Reservation> getUpcomingReservations() {
        Vector<Reservation> result = new Vector<>();
        for (Reservation res : listRes) {
            if (!res.getIsCheckedIn() &&
                    (res.getDateDeb().isBefore(LocalDate.now()) || res.getDateDeb().isEqual(LocalDate.now()))
                    && !res.getIsCheckedIn()) {
                result.add(res);
            }
        }
        return result;
    }

    public Vector<Sejour> getOngoingSejours() {
        Vector<Sejour> result = new Vector<>();
        for (Sejour sej : listSejours) {
            if (!sej.getReservation().getIsCheckedOut()) {
                result.add(sej);
            }
        }
        return result;
    }

    public Vector<Reservation> searchReservationsByEmail(Vector<Reservation> list, String mail) {
        Vector<Reservation> result = new Vector<>();
        for (Reservation res : list) {
            if (res.getClientMail().equalsIgnoreCase(mail)) {
                result.add(res);
            }
        }
        return result;
    }

    public Vector<Chambre> getAvailableRoomsByType(String Type) {
        Vector<Chambre> list = getListChamByType(Type);
        Vector<Chambre> result = new Vector<>();
        for (Chambre ch : list) {
            if (ch.isAvailable()) {
                result.add(ch);
            }
        }
        return result;
    }

    public Client findClientByEmail(String email) {
        for (Client client : listClient) {
            if (client.getEmail().equalsIgnoreCase(email)) {
                return client;
            }
        }
        return null;
    }

    public void addClient(Client client) {
        listClient.add(client);
        setChanged();
        notifyObservers();
    }

    public Produit searchProductByName(String name) {
        for (Produit prod : listProd) {
            if (prod.getNom() == name) {
                return prod;
            }
        }
        return null;
    }

    public Vector<Reservation> searchReservationsById(int id) {
        Vector<Reservation> result = new Vector<>();
        for (Reservation res : listRes) {
            if (res.getId() == id) {
                result.add(res);
            }
        }
        return result;
    }

    public Sejour searchSejourById(int id) {
        for(Sejour sejour: listSejours) {
            if(sejour.getId() == id) {
                return sejour;
            }
        }
        return null;
    }

    public Vector<Sejour> searchSejoursByEmail(Vector<Sejour> list, String mail) {
        Vector<Sejour> result = new Vector<>();
        for (Sejour res : list) {
            if (res.getClient().getEmail().equalsIgnoreCase(mail)) {
                result.add(res);
            }
        }
        return result;
    }
}