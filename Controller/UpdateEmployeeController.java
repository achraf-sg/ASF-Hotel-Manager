package Controller;

import Models.Employe;
import Models.Hotel;
import View.UpdateEmployeePage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateEmployeeController {
  private Hotel model;
  private Employe employe;
  private UpdateEmployeePage view;

  public UpdateEmployeeController(Hotel model, Employe employe, UpdateEmployeePage view) {
    this.model = model;
    this.employe = employe;
    this.view = view;

    initListeners();
    populateFields();
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
  }>

  private void populateFields() {
    // Remplir les champs avec les données actuelles de l'employé
    view.getNomField().setText(employe.getNom());
    view.getPrenomField().setText(employe.getPrenom());
    view.getAdresseField().setText(employe.getAdresse());
    view.getTelephoneField().setText(employe.getTelephone());
    view.getEmailField().setText(employe.getEmail());
    view.getPasswordField().setText(employe.getPassword());
  }

  private void handleUpdate() {
    // Récupérer les nouvelles valeurs des champs
    String nom = view.getNomField().getText();
    String prenom = view.getPrenomField().getText();
    String adresse = view.getAdresseField().getText();
    String telephone = view.getTelephoneField().getText();
    String email = view.getEmailField().getText();
    String password = new String(view.getPasswordField().getPassword());

    // Mettre à jour les informations de l'employé
    model.updateEmployee(employe.getId(), nom, email, telephone, adresse, password);

    // Afficher un message de succès et fermer la page
    view.showMessage("Employé mis à jour avec succès !");
    view.dispose();
  }
}
