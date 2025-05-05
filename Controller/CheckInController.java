package Controller;

import Models.*;
import View.CheckInFormPanel;
import View.CheckInPanel;
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

          res.setCheckedIn(true);
          Vector<Chambre> chambresDisponibles = model.getAvailableRoomsByType(res.getType().getName());
          if (chambresDisponibles.isEmpty()) {
            view.showError("Aucune chambre disponible pour ce type.");
            return;
          }

          CheckInFormPanel formPanel = new CheckInFormPanel(res, chambresDisponibles);
          JFrame formFrame = new JFrame("Check In");
          formFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          formFrame.getContentPane().add(formPanel);
          formFrame.setSize(800, 600);
          formFrame.setLocationRelativeTo(null);

          // Add the controller logic for the validate button
          formPanel.getValidateButton().addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  // Your check-in logic here
              }
          });

          // Add cancel button logic
          formPanel.getCancelButton().addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  formFrame.dispose();
              }
          });

          formFrame.setVisible(true);
        }
      }
    });
  }

}