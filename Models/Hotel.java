package Models;

import java.time.LocalDate;
import java.util.*;

public class Hotel extends Observable{

    private String nom;
    private String adresse;
    private Vector<Client> listClient = new Vector<>();
    private Vector<Chambre> listCham = new Vector<>();
    private Vector<Produit> listProd = new Vector<>();
    private Vector<Employe> listEmp = new Vector<>();
    private Vector<Reservation> listRes = new Vector<>();

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

    public Vector<Client> getListClientByName(String name) {
        Vector<Client> filteredList = new Vector<>();
        for (Client client : listClient) {
            if (client.getNom().equalsIgnoreCase(name)) {
                filteredList.add(client);
            }
        }
        return filteredList;
    }

    public Vector<Chambre> getListChamByType(String type) {
        Vector<Chambre> filteredList = new Vector<>();
        for (Chambre cham : listCham) {
            if (cham.getType().getType().toString().equalsIgnoreCase(type)) {
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

    public Vector<Reservation> getReservationArray() {
        return listRes;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    
    /**
     * Prepares the data for the JTable.
     */
    public Object[][] prepareTableData() {
        int rowCount = listClient.size();
        Object[][] data = new Object[rowCount][7];

        for (int i = 0; i < rowCount; i++) {
            Client client = listClient.get(i);

            // If the client has reservations, check the reservation's start date
            if (!client.getListReservation().isEmpty()) {
                Reservation reservation = client.getListReservation().get(0); // Assuming one reservation per client

                if (LocalDate.now().isAfter(reservation.getDateFin())
                        || LocalDate.now().isEqual(reservation.getDateFin())) {
                    // Show reservation details if today's date >= reservation start date
                    data[i][0] = client.getIdClient(); // Client ID
                    data[i][1] = client.getNom(); // Client Name
                    data[i][2] = reservation.getDateDeb(); // Start Date
                    data[i][3] = reservation.getDateFin(); // End Date
                    data[i][4] = reservation.getChambre().getType().getType(); // Room Type
                    data[i][5] = reservation.getChambre().getNumero(); // Room Number
                    data[i][6] = reservation.getChambre().getType().getPrix() + "$"; // Total Price
                } else {
                    // Show reservation details if today's date < reservation start date
                    data[i][0] = client.getIdClient(); // Client ID
                    data[i][1] = client.getNom(); // Client Name
                    data[i][2] = reservation.getDateDeb(); // Start Date
                    data[i][3] = reservation.getDateFin(); // End Date
                    data[i][4] = reservation.getChambre().getType().getType(); // Room Type
                    data[i][5] = reservation.getChambre().getNumero(); // Room Number
                    data[i][6] = reservation.getChambre().getType().getPrix() + "$"; // Total Price
                }
            }
        }

        return data;
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
    
        public void updateEmployee(int id, String nom, String email, String telephone, String adresse, String password) {
            for (Employe emp : getListEmp()) {
                if (emp.getId() == id) {
                    if (nom != null) emp.setNom(nom);
                    if (email != null) emp.setEmail(email);
                    if (telephone != null) emp.setTelephone(telephone);
                    if (adresse != null) emp.setAdresse(adresse);
                    if (password != null) emp.setPassword(password);
        
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
    
        public void addChambre(int numero, int etage, Type type) {
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

        // Client methods

        public void addClient(Client client) {
            listClient.add(client);
            setChanged();
            notifyObservers("Client ajouté");
        }
}