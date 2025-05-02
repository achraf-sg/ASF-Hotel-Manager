package Controller;

import Models.*;
import View.CheckInPage;
import View.CheckInFormPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class CheckInController {
  private Hotel model;
  private CheckInPage view;

  public CheckInController(Hotel model, CheckInPage view) {
    this.model = model;
    this.view = view;

    // Bouton de recherche par e-mail
    view.getSearchButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String email = view.getSearchField().getText().trim();
        if (email.isEmpty()) {
          return;
        }

        Vector<Reservation> result = model.searchReservationsByEmail(model.getUpcomingReservations(), email);
        view.remplirTableReservation(result);
      }
    });

    // Bouton dans la table : \"Confirm Check-in\"
    view.getReservationTable().addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int row = view.getReservationTable().rowAtPoint(e.getPoint());
        int column = view.getReservationTable().columnAtPoint(e.getPoint());

        if (column == view.getReservationTable().columnCount - 1) {
          int reservationId = (int) view.getReservationTable().getValueAt(row, 0);
          Reservation res = model.findReservationById(reservationId);

        if (res == null) {
          view.showError("Réservation introuvable.");
          return;
        }

          res.setCheckedIn(true);
          Vector<Chambre> chambresDisponibles = model.getAvailableRoomsByType(res.getType().getName());
          if (chambresDisponibles.isEmpty()) {
            view.showError("Aucune chambre disponible pour ce type.");
            return;
          }

          CheckInFormPage formPage = new CheckInFormPage(res, chambresDisponibles);
          new CheckInFormController(model, formPage, res);
          formPage.setVisible(true);
          view.dispose(); // Ferme la page de check-in après l'ouverture du formulaire
        }
      }
    });
  }

}