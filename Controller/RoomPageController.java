package Controller;

import Models.Chambre;
import Models.Employe;
import Models.Hotel;
import Models.Type;
import Models.Admin;
import View.RoomPage;
import View.UpdateEmployeePage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoomPageController {
  Hotel model;
  Admin admin;
  RoomPage view;

  public RoomPageController(Hotel model, Admin admin, RoomPage view) {
    this.model = model;
    this.admin = admin;
    this.view = view;

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
    
    //supprimer chambre
    view.getTable().addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int row = view.getTable().rowAtPoint(e.getPoint());
        int column = view.getTable().columnAtPoint(e.getPoint());
        int columnCount = view.getTable().getColumnCount();
        if (column == 6) { // Supprimer
          int numero = (int) view.getTable().getValueAt(row, 0);
          int etage = (int) view.getTable().getValueAt(row, 1);
          model.deleteChambre(numero, etage);
          ;
          view.showMessage("Chambre supprimé avec succès !");
          view.remplirTableChambres(model.getListCham());
        }
      }
    });

  }
}
