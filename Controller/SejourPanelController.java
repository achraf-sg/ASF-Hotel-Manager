package Controller;

import Models.Chambre;
import Models.Consommation;
import Models.FactureTemplate;
import Models.Hotel;
import Models.Sejour;
import View.SejourPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.JOptionPane;

public class SejourPanelController {
  private Hotel model;
  private SejourPanel view;

  public SejourPanelController(Hotel model, SejourPanel view) {
    this.model = model;
    this.view = view;

    view.remplirTableSejours(model.getOngoingSejours());
    //go to consommation
    view.getSejoursTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              int row = view.getSejoursTable().rowAtPoint(e.getPoint());
              int column = view.getSejoursTable().columnAtPoint(e.getPoint());
              
              if (column == view.getSejoursTable().getColumnCount() - 2) { 
                int id = (int) view.getSejoursTable().getValueAt(row, 0);
                Sejour sejour = model.searchSejourById(id);
               // ConsommationPage page = new ConsommationPage();
               // new ConsommationController(model, page, sejour);
              }
              // checkOut
        else if (column == view.getSejoursTable().getColumnCount() - 1) {
          int sejourId = (int) view.getSejoursTable().getValueAt(row, 0);
          Sejour sejour = model.searchSejourById(sejourId);
          if (sejour == null) {
            view.showError("No Ongoing stay found");
            return;
          }
          int confirm = JOptionPane.showConfirmDialog(
              view,
              "Are you sure you want to check-out?",
              "Confirm Check-out",
              JOptionPane.YES_NO_OPTION);

          if (confirm == JOptionPane.YES_OPTION) {
            handleCheckout(sejour);
          }
        }
            }
          });

    // search
    view.getSearchButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String email = view.getSearchField().getText().trim();
        if (email.isEmpty()) {
          return;
        }

        Vector<Sejour> result = model.searchSejoursByEmail(model.getOngoingSejours(), email);
        view.remplirTableSejours(result);
      }
    });
  }

  private void handleCheckout(Sejour sejour) {
    Chambre chambre = sejour.getChambre();
    if (chambre != null) {
      chambre.setClean(false);
    }

    if (sejour.getReservation().getDateFin().isAfter(LocalDate.now())) {
      sejour.getReservation().setDateFin(LocalDate.now());
    }
    sejour.getReservation().setCheckedOut(true);

    // Créer la facture
    FactureTemplate facture = new FactureTemplate(
        "Factures/" + sejour.getClient().getNom() + sejour.getClient().getPrenom() + "_" + LocalDate.now() + ".txt",
        sejour.getClient().getNom());

    facture.addRoom(
        "Room Number: " + chambre.getNumero() + ", Price: " + sejour.getReservation().getType().getPrix() + "€/night");
    for (Consommation consommation : sejour.getListConsommation()) {
      facture.addBar(
          consommation.getProduit().getNom(),
          consommation.getQuantite(),
          consommation.getTotal());
    }
    facture.setTotal(sejour.getTotal());
    facture.createTextFile();

    // Supprimer le séjour
    view.showMessage(" Check-out success !\n" +"Your invoice has been generated in the Factures folder");
    view.remplirTableSejours(model.getOngoingSejours());
  }

}
