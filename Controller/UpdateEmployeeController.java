package Controller;

import Models.Employe;
import Models.Hotel;
import View.UpdateEmployeePage;
import View.EmployeePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UpdateEmployeeController {
  private Hotel model;
  private Employe employe;
  private UpdateEmployeePage view;
  private EmployeePanel mainView; // Add reference to main view

  public UpdateEmployeeController(Hotel model, Employe employe, UpdateEmployeePage view, EmployeePanel mainView) {
    this.model = model;
    this.employe = employe;
    this.view = view;
    this.mainView = mainView;

    initListeners();
    
    // Add window listener to refresh table when window closes
    view.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        if (mainView != null) {
          mainView.remplirTableEmployes(model.getListEmp());
        }
      }
    });
  }

  private void initListeners() {
    // Bouton pour sauvegarder les modifications
    view.getSaveButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        handleUpdate();
      }
    });

    // Bouton pour annuler et fermer la page
    view.getCancelButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        view.dispose();
      }
    });
  }

  private void handleUpdate() {
    // Récupérer les nouvelles valeurs des champs
    String nom = view.getNameField().getText();
    String prenom = view.getSurnameField().getText();
    String adresse = view.getAddressField().getText();
    String telephone = view.getPhoneField().getText();
    String email = view.getEmailField().getText();
    String password = new String(view.getPasswordField().getPassword());

    // Mettre à jour les informations de l'employé
    model.updateEmployee(employe.getId(), nom, prenom, email, telephone, adresse, password);

    // Afficher un message de succès et fermer la page
    view.showMessage("Employé mis à jour avec succès !");
    
    view.dispose();
  }
}
