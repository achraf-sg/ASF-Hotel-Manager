package Controller;

import Models.Admin;
import Models.Chambre;
import Models.Hotel;
import Models.Menage;
import Models.Reception;
import Models.Employe;
import View.AdminHomePage;
import Models.Type;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController {
    private AdminHomePage view;
    private Hotel model;
    private Admin admin;

    public AdminController(Hotel Hmodel, Admin Aadmin, AdminHomePage view) {
        this.model = Hmodel;
        this.admin = Aadmin;
        this.view = view;

        // bouton "ajouter"
        this.view.getAjouterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = view.getTypeCombo().getSelectedItem().toString();
                String nom = view.getNomField().getText();
                String prenom = view.getPrenomField().getText();
                String adresse = view.getAdresseField().getText();
                String tel = view.getTelephoneField().getText();
                String email = view.getEmailField().getText();
                String password = new String(view.getPasswordField().getPassword());

                Employe newEmp = null;

                if (type.equalsIgnoreCase("Menage")) {
                    newEmp = new Menage(nom, prenom, adresse, tel, email, model, password);
                } else if (type.equalsIgnoreCase("Reception")) {
                    newEmp = new Reception(nom, prenom, adresse, tel, email, model, password);
                } else {
                    view.showError("Type d'employé invalide.");
                    return;
                }

                model.addEmployee(newEmp);
                view.showMessage("Employé ajouté avec succès !");
                view.clearForm();
            }
        });

        // "supprimer employee"

        view.getSupprimerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(view.getIdToDeleteField().getText());
                    boolean found = false;

<<<<<<< HEAD

    public void deleteEmployee(int employeeId) {
        Employe empToRemove = null;
        for (Employe emp : hotel.getListEmp()) {
            if (emp.getId() == employeeId) {
                empToRemove = emp;
                break;
=======
                    for (Employe emp : model.getListEmp()) {
                        if (emp.getId() == id) {
                            found = true;
                            break;
                        }
                    }

                    if (found) {
                        model.deleteEmployee(id);
                        view.showMessage("Employé supprimé avec succès !");
                        view.getIdToDeleteField().setText("");
                    } else {
                        view.showError("Employé non trouvé.");
                    }
                } catch (NumberFormatException ex) {
                    view.showError("ID invalide.");
                }
>>>>>>> a4a18ea60e75b5311303f1c585cd13a2c8811613
            }
        });

        // Update Employee
        view.getModifierButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(view.getIdModifField().getText());

                    // Tous les champs peuvent être vides (on garde les anciens)
                    String nom = view.getNomModifField().getText();
                    String email = view.getEmailModifField().getText();
                    String tel = view.getTelModifField().getText();
                    String adresse = view.getAdresseModifField().getText();
                    String mdp = new String(view.getPasswordModifField().getPassword());

                    // Vérifie si l'employé existe
                    Employe emp = model.findEmployeeById(id);
                    if (emp == null) {
                        view.showError("Employé introuvable.");
                        return;
                    }

                    // Appel du modèle : on ne change que ce qui a été saisi
                    model.updateEmployee(id, nom, email, tel, adresse, mdp);
                    view.showMessage("Employé mis à jour avec succès !");
                    view.clearFormUpdate(); // optionnel

                } catch (NumberFormatException ex) {
                    view.showError("ID invalide.");
                }
            }
        });

        // ajouter chambre
        view.getAddChamButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numero = Integer.parseInt(view.getNumChamField().getText());
                    int etage = Integer.parseInt(view.getEtageField().getText());
                    Type type = (Type) view.getTypeChamBox().getSelectedItem();

                    // Vérifie si la chambre existe déjà
                    boolean exists = false;
                    for (Chambre c : model.getListCham()) {
                        if (c.getNumero() == numero && c.getEtage() == etage) {
                            exists = true;
                            break;
                        }
                    }

                    if (exists) {
                        view.showError("Cette chambre existe déjà.");
                        return;
                    }

                    model.addChambre(numero, etage, type);
                    view.showMessage("Chambre ajoutée avec succès !");
                    view.clearChambreForm(); // méthode à créer si tu veux

                } catch (NumberFormatException ex) {
                    view.showError("Numéro ou étage invalide.");
                }
            }
        });
        // supprimer chambre
        view.getDelChamButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numero = Integer.parseInt(view.getDelNumChamField().getText());
                    int etage = Integer.parseInt(view.getDelEtageChamField().getText());

                    if (!(admin instanceof Admin)) {
                        view.showError("Accès refusé. Seul un administrateur peut supprimer une chambre.");
                        return;
                    }

                    Chambre target = null;
                    for (Chambre c : model.getListCham()) {
                        if (c.getNumero() == numero && c.getEtage() == etage) {
                            target = c;
                            break;
                        }
                    }

                    if (target == null) {
                        view.showError("Chambre introuvable.");
                    } else {
                        model.deleteChambre(numero, etage);
                        view.showMessage("Chambre supprimée avec succès !");
                        view.getDelNumChamField().setText("");
                        view.getDelEtageChamField().setText("");
                    }

                } catch (NumberFormatException ex) {
                    view.showError("Veuillez entrer des valeurs valides.");
                }
            }
        });
    }


}