package Controller;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Models.*;
import View.*;

public class DeleteReservationController implements MouseListener {
    private Hotel hotel;

    public DeleteReservationController(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable table = DeleteReservationPage.getReservationTable();
        int selectedRow = table.rowAtPoint(e.getPoint());

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(DeleteReservationPage.getFrame(), "Please select a reservation to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int reservationId = (int) table.getValueAt(selectedRow, 0);

        // Find reservation by ID
        Reservation reservationToDelete = hotel.findReservationById(reservationId);

        if (reservationToDelete == null) {
            JOptionPane.showMessageDialog(DeleteReservationPage.getFrame(), "Reservation not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Confirm deletion
        int confirm = JOptionPane.showConfirmDialog(DeleteReservationPage.getFrame(), "Are you sure you want to delete this reservation?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Delete the reservation
        String result = hotel.deleteReservation(reservationToDelete);

        // Show the result
        JOptionPane.showMessageDialog(DeleteReservationPage.getFrame(), result);

        // Refresh the table
        DeleteReservationPage.refreshTable(hotel.prepareTableData());
    }

}
