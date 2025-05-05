package Controller;

import Models.*;
import View.EmployeePanel;
import View.UpdateEmployeePage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
public class EmployeePanelController {
    private Hotel model;
    private Admin admin;
    private EmployeePanel view;

    public EmployeePanelController(Hotel model, Admin admin, EmployeePanel view) {
        this.model = model;
        this.admin = admin;
        this.view = view;

        initAddListener();
        initTableListeners(); // Add this line if it's missing
    }

    private void initAddListener() {
        view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = view.getNameField().getText();
                String prenom = view.getSurnameField().getText();
                String adresse = view.getAddressField().getText();
                String telephone = view.getPhoneField().getText();
                String email = view.getEmailField().getText();
                String password = view.getPasswordField().getText();
                String function = (String) view.getFunctionComboBox().getSelectedItem();

                // Validate input
                if (nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || 
                    telephone.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    view.showError("All fields are required!");
                    return;
                }

                // Check if email is already in use
                if (model.findEmployeeByEmail(email) != null) {
                    view.showError("Email already in use!");
                    return;
                }

                // Create employee based on function
                Employe emp;
                if(model.isValidEmail(email)&& model.isValidPhoneNumber(telephone)&& model.isValidName(nom)&& model.isValidName(prenom)) {
                    

               
                if (function.equals("Receptionist")) {
                    
                    emp = new Reception(nom, prenom, adresse, telephone, email, model, password);
                } else {
                    emp = new Menage(nom, prenom, adresse, telephone, email, model, password);
                }

                // Add employee to model
                model.addEmployee(emp);
                view.showMessage("Employee added successfully!");
                view.clearForm();
                view.remplirTableEmployes(model.getListEmp());
            } else {
                    view.showError("Invalid  informations format!");
                    view.clearForm();
                }
            }
        });
    }

    private void initTableListeners() {
        view.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().rowAtPoint(e.getPoint());
                int column = view.getTable().columnAtPoint(e.getPoint());
                
                if (row >= 0) {
                    // Edit column - this is the second-to-last column
                    if (column == view.getTable().getColumnCount() - 2) {
                        int id = (int) view.getTable().getValueAt(row, 0);
                        Employe emp = model.findEmployeeById(id);
                        
                        if (emp != null) {
                            // Open edit dialog or panel
                            UpdateEmployeePage updatePage = new UpdateEmployeePage(emp, model);
                            
                            // Add window listener to refresh the table when update window is closed
                            updatePage.addWindowListener(new java.awt.event.WindowAdapter() {
                                @Override
                                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                    view.remplirTableEmployes(model.getListEmp());
                                }
                            });
                            
                            // Position the update window relative to the main frame
                            updatePage.setLocationRelativeTo(SwingUtilities.getWindowAncestor(view));
                            
                            // Create controller and show update page
                            new UpdateEmployeeController(model, emp, updatePage, view);
                            updatePage.setVisible(true);
                        }
                    }
                    // Delete column
                    else if (column == view.getTable().getColumnCount() - 1) {
                        int id = (int) view.getTable().getValueAt(row, 0);
                        
                        // Prevent admin from deleting themselves
                        if (id == admin.getId()) {
                            view.showError("You cannot delete yourself!");
                            return;
                        }
                        
                        int confirm = JOptionPane.showConfirmDialog(
                            view,
                            "Are you sure you want to delete this employee?",
                            "Confirm Deletion",
                            JOptionPane.YES_NO_OPTION
                        );
                        
                        if (confirm == JOptionPane.YES_OPTION) {
                            model.deleteEmployee(id);
                            view.showMessage("Employee deleted successfully!");
                            view.remplirTableEmployes(model.getListEmp());
                        }
                    }
                }
            }
        });
    }
}