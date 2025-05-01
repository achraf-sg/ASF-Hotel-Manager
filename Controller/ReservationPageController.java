package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Models.*;
import View.ReservationPage;
import View.UpdateEmployeePage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import javax.swing.*;
import java.awt.event.MouseListener;  

public class ReservationPageController {

  private Hotel model;
  private ReservationPage view;
  private int SingleRoomsAvailable = 0, DoubleRoomsAvailable = 0, SuitsAvailable = 0, KingRoomsAvailable = 0;
  private LocalDate startDate = null, endDate = null;

  public ReservationPageController(Hotel hotel, ReservationPage page) {
    this.model = hotel;
    this.view = page;

    initTableListeners();
    // bouton "confirmer date"
    this.view.getConfirmDateButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          LocalDate startDate = LocalDate.parse(view.getStartDate().getText());
          LocalDate endDate = LocalDate.parse(view.getEndDate().getText());
          view.updateRoomTypeDropdown(model.getListTypes(), model, startDate, endDate);
        } catch (DateTimeParseException ex) {
          view.showError("Invalid Date Format. Use YYYY-MM-DD.");
        }
      }
    });
    // bouton "ajouter"
    this.view.getAddButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String type = view.getTypeComboBox().getSelectedItem().toString();
        String email = view.getEmailField().getText();
        RoomType roomType = hotel.findTypeByName(type);

        if (roomType == null) {
          view.showError("non existing type");
          return;
        }

        if (startDate == null || endDate == null) {
          view.showError("Please choose a start and an end date for your reservation");
          return;
        }
        Reservation res = new Reservation(startDate, endDate, email, roomType);
        model.addReservation(res);
        view.populateReservationTable(model.getListRes());
        view.showMessage("Reservation added succesfully !");
        view.clearForm();
      }
    });
  }

  private void initTableListeners() {
    int columnCount = view.getTable().getColumnCount();
    view.getTable().addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        int row = view.getTable().rowAtPoint(e.getPoint());
        int column = view.getTable().columnAtPoint(e.getPoint());

        // if (column == columnCount - 2) { // Edit
        //   int id = (int) view.getTable().getValueAt(row, 0);
        //   Reservation res = model.findReservationById(id);
        //   if (res != null) {
        //     UpdateReservationPage modifierPage = new UpdateReservationPage(res, model);
        //     new UpdateReservationController(model, res, modifierPage);
        //     modifierPage.setVisible(true);
        //     view.dispose(); // Optionally close the current page
        //   }
        // }

        if (column == columnCount - 1) { // Delete
          int id = (int) view.getTable().getValueAt(row, 0);
          model.deleteReservation(id);
          view.showMessage("Reservation deleted successfully!");
          view.populateReservationTable(model.getListRes());
        }
      }
    });

  }

  public int getAvailableRoomsByType(String type) {
    if (type.equalsIgnoreCase("Single"))
      return SingleRoomsAvailable;
    else if (type.equalsIgnoreCase("Double"))
      return DoubleRoomsAvailable;
    else if (type.equalsIgnoreCase("Suite"))
      return SuitsAvailable;
    else if (type.equalsIgnoreCase("King"))
      return KingRoomsAvailable;
    else {
      view.showError("Wrong Room Type");
      return -1;
    }
    
  }
}