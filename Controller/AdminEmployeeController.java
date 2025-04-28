package Controller;

import Models.Admin;
import Models.Chambre;
import Models.Hotel;
import Models.Menage;
import Models.Reception;
import Models.Employe;
import View.AdminEmployeePage;
import View.UpdateEmployeePage;
import Models.Type;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminEmployeeController {
    AdminEmployeePage view;
    Hotel model;
    Admin admin;

    public AdminEmployeeController(Hotel model, Admin admin, AdminEmployeePage view) {
        this.model = model;
        this.admin = admin;
        this.view = view;
        //initialize table listeners for delete and modify employee
        initTableListeners();

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
    }

    private void initTableListeners() {
        view.getTable().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().rowAtPoint(e.getPoint());
                int column = view.getTable().columnAtPoint(e.getPoint());
    
                if (column == 6) { // Modifier
                    int id = (int) view.getTable().getValueAt(row, 0);
                    Employe emp = model.findEmployeeById(id);
                    if (emp != null) {
                        UpdateEmployeePage modifierPage = new UpdateEmployeePage(emp, model);
                        new UpdateEmployeeController(model, emp, modifierPage);
                        modifierPage.setVisible(true);
                        view.dispose(); // optionnel : fermer l'ancienne page
                    }
                }
    
                if (column == 7) { // Supprimer
                    int id = (int) view.getTable().getValueAt(row, 0);
                    model.deleteEmployee(id);
                    view.showMessage("Employé supprimé avec succès !");
                    view.remplirTableEmployes(model.getListEmp());
                }
            }
        });
    }
}