package Controller;

import Models.*;
import View.ReservationPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

public class ReservationPanelController {
    private Hotel model;
    private ReservationPanel view;
    private LocalDate startDate = null, endDate = null;

    public ReservationPanelController(Hotel model, ReservationPanel view) {
        this.model = model;
        this.view = view;

        initListeners();
        initTableListeners();
    }

    private void initListeners() {
        // Confirm Dates button listener
        this.view.getConfirmDateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    startDate = LocalDate.parse(view.getStartDate().getText());
                    endDate = LocalDate.parse(view.getEndDate().getText());
                    
                    if (startDate.isAfter(endDate)) {
                        view.showError("Start date cannot be after end date!");
                        return;
                    }
                    
                    if (startDate.isBefore(LocalDate.now())) {
                        view.showError("Start date cannot be in the past!");
                        return;
                    }
                    
                    view.updateRoomTypeDropdown(model.getListTypes(), model, startDate, endDate);
                } catch (DateTimeParseException ex) {
                    view.showError("Invalid Date Format. Use YYYY-MM-DD.");
                }
            }
        });

        // Add Reservation button listener
        this.view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (startDate == null || endDate == null) {
                        view.showError("Please confirm dates first!");
                        return;
                    }
                    
                    String fullTypeName = view.getTypeComboBox().getSelectedItem().toString();
                    // Extract just the room type name (everything before the opening parenthesis)
                    String typeName = fullTypeName.split(" \\(")[0];
                    String email = view.getEmailField().getText();
                    
                    // Validate email
                    if (email == null || email.isEmpty()) {
                        view.showError("Please enter a client email!");
                        return;
                    }
                    
                    RoomType roomType = model.findTypeByName(typeName);
                    if (roomType == null) {
                        view.showError("Non-existing room type!");
                        return;
                    }
                    
                    // Create and add reservation
                    Reservation res = new Reservation(startDate, endDate, email, roomType);
                    model.addReservation(res);
                    view.populateReservationTable(model.getListRes());
                    view.showMessage("Reservation added successfully!");
                    view.clearForm();
                    
                    // Reset dates
                    startDate = null;
                    endDate = null;
                } catch (Exception ex) {
                    view.showError("Error adding reservation: " + ex.getMessage());
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
                int lastColumn = view.getTable().getColumnCount() - 1;
                
                if (column == lastColumn) { // Delete column
                    int reservationId = (int) view.getTable().getValueAt(row, 0);
                    
                    int confirm = JOptionPane.showConfirmDialog(
                        view, 
                        "Are you sure you want to delete this reservation?", 
                        "Confirm Deletion", 
                        JOptionPane.YES_NO_OPTION
                    );
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        model.deleteReservation(reservationId);
                        view.showMessage("Reservation deleted successfully!");
                        view.populateReservationTable(model.getListRes());
                    }
                }
            }
        });
    }
}