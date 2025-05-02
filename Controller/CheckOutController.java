package Controller;

import Models.*;
import View.CheckoutPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Vector;

public class CheckOutController {

  private Hotel model;
  private CheckoutPage view;

  public CheckOutController(Hotel model, CheckoutPage view) {
    this.model = model;
    this.view = view;

    initTableListeners();

    view.getSearchButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String email = view.getSearchField().getText().trim();
        if (email.isEmpty()) {
          return;
        }

        Vector<Sejour> result = model.searchSejoursByEmail(model.getOngoingSejours(),email);
        view.remplirTableSejours(result);
      }
    });
  }

  private void initTableListeners() {
    view.getTable().addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        JTable table = view.getTable();
        int selectedRow = table.rowAtPoint(e.getPoint());
        int selectedColumn = table.columnAtPoint(e.getPoint());

        if (selectedColumn == table.getColumnCount() - 1) { 
          int sejourId = (int) table.getValueAt(selectedRow, 0);
          Sejour sejour = model.searchSejourById(sejourId);

          if (sejour == null) {
            view.showError("No Ongoing stay found");
            return;
          }
          handleCheckout(sejour);
        }
      }
    });
  }

  private void handleCheckout(Sejour sejour) {
    Chambre chambre = sejour.getChambre();
    if (chambre != null) {
      chambre.setClean(false); 
    }

    if(sejour.getReservation().getDateFin().isAfter(LocalDate.now())) {
      sejour.getReservation().setDateFin(LocalDate.now());
    }
    sejour.getReservation().setCheckedOut(true);

    // Créer la facture
    FactureTemplate facture = new FactureTemplate(
        "factures/" + sejour.getClient().getNom() + sejour.getClient().getPrenom() + "_" + LocalDate.now() + ".txt",
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
    view.showMessage("Checkout réussi. Facture générée.");
    view.remplirTableSejours(model.getListSejours());
  }

}
