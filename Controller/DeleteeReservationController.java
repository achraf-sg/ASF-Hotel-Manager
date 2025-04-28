package Controller;

import javax.swing.JOptionPane;
import Models.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class DeleteeReservationController implements ActionListener {
      private Hotel hotel;
        private Reservation reservation;
        Client client, Reservation ;
        public DeleteeReservationController(Hotel hotel) {
            this.hotel = hotel;
        }
        public void actionPerformed( ActionEvent e){
             
               int selectedRow = View.DeleteReservationPage.getReservationTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(View.DeleteReservationPage, "Please select a reservation to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int reservationId = (int) View.DeleteeReservationPage.getReservationTable().getValueAt(selectedRow, 0);
 

        if (reservationId == -1) {
            JOptionPane.showMessageDialog(View.DeleteeReservationPage, "Invalid reservation ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
          
        }
        
        Reservation reservationToDelete =View.DeleteeReservationPage.getReservationTable().getValueAt(selectedRow);
       
        String result = Reservation.deleteReservation(selectedRow.getClient(), reservationToDelete);

        // Show the result message
        JOptionPane.showMessageDialog(View.DeleteeReservationPage, result);

        // Refresh the table
        View.DeleteReservation.refreshTable(hotel.prepareTableData());
            }
    
}
