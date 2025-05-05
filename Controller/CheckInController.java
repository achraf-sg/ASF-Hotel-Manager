package Controller;

import Models.*;
import View.CheckInFormPage;
import View.CheckInPanel;
import View.UpdateEmployeePage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.*;

public class CheckInController {
  private Hotel model;
  private CheckInPanel view;

  public CheckInController(Hotel model, CheckInPanel view) {
    this.model = model;
    this.view = view;

    view.remplirTableReservation(model.getUpcomingReservations());

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

        if (column == view.getReservationTable().getColumnCount() - 1) {
          int reservationId = (int) view.getReservationTable().getValueAt(row, 0);
          Reservation res = model.findReservationById(reservationId);

          if (res == null) {
            view.showError("Réservation introuvable.");
            return;
          }
          Vector<Chambre> chambresDisponibles = model.getAvailableRoomsByType(res.getType().getName());
          if (chambresDisponibles.isEmpty()) {
            view.showError("Aucune chambre disponible pour ce type.");
            return;
          }

          // Open edit dialog or panel
          CheckInFormPage checkInForm = new CheckInFormPage(res, chambresDisponibles);

          // Add window listener to refresh the table when update window is closed
          checkInForm.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
              view.remplirTableReservation(model.getUpcomingReservations());
            }
          });

          // Position the update window relative to the main frame
          checkInForm.setLocationRelativeTo(SwingUtilities.getWindowAncestor(view));

          // Create controller and show update page
          new CheckInFormController(model, checkInForm, res);
          checkInForm.setVisible(true);
        }
      }
    });
  }

}