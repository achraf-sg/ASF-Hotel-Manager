package Controller;

import Models.Admin;
import Models.Hotel;
import Models.Menage;
import Models.Reception;
import Models.Employe;
import View.AdminEmployeePage;
import View.UpdateEmployeePage;

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
        this.view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = view.getFunctionComboBox().getSelectedItem().toString();
                String nom = view.getNameField().getText();
                String prenom = view.getSurnameField().getText();
                String adresse = view.getAddressField().getText();
                String tel = view.getPhoneField().getText();
                String email = view.getEmailField().getText();
                String password = view.getPasswordField().getText();

                Employe newEmp = null;

                if (type.equalsIgnoreCase("Cleaner")) {
                    newEmp = new Menage(nom, prenom, adresse, tel, email, model, password);
                } else if (type.equalsIgnoreCase("Receptionist")) {
                    newEmp = new Reception(nom, prenom, adresse, tel, email, model, password);
                } else {
                    view.showError("Invalid employee type.");
                    return;
                }

                model.addEmployee(newEmp);
                view.remplirTableEmployes(model.getListEmp());
                view.showMessage("Employee added succesfully !");
                view.clearForm();
            }
        });      
    }


    
    private void initTableListeners() {
        view.getTable().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().rowAtPoint(e.getPoint());
                int column = view.getTable().columnAtPoint(e.getPoint());
    
                if (column == 6) { // Edit
                    int id = (int) view.getTable().getValueAt(row, 0);
                    Employe emp = model.findEmployeeById(id);
                    if (emp != null) {
                        UpdateEmployeePage modifierPage = new UpdateEmployeePage(emp, model);
                        new UpdateEmployeeController(model, emp, modifierPage);
                        modifierPage.setVisible(true);
                        view.dispose(); // Optionally close the current page
                    }
                }
    
                if (column == 7) { // Delete
                    int id = (int) view.getTable().getValueAt(row, 0);
                    model.deleteEmployee(id);
                    view.showMessage("Employee deleted successfully!");
                    view.remplirTableEmployes(model.getListEmp());
                }
            }
        });
    }
}