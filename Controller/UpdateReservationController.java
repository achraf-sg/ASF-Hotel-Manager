// package Controller;

// import java.time.LocalDate;

// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import javax.swing.JOptionPane;
// import Models.*;
// import View.UpdateReservationPage;

// public class UpdateReservationController {
    
//     private Hotel hotel;
//     private AUpdateeservationPage view;


//        public UpdateReservationController(Hotel hotel, UpdateReservationPage view) {
//         this.hotel = hotel;
//         this.view = view;

//         // Attach the controller to the "Update Reservation" button
//         this.view.getUpdateButton().addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 Reservation reservation = view.Table.getSelectedReservation();
//                  // elle retourne la ligne de la reservation
// String selecteClientName = view.Table.getSelectedRow()[0];//
//                 Client client = hotel.getListClientByName(selecteClientName);// we get clients id or name sa depand de la situation
                
//                 LocalDate newDateDebut = view.getNewDateDebutField().getDate();
//                 LocalDate newDateFin = view.getNewDateFinField().getDate();
//                 updateReservation(client, reservation, newDateDebut, newDateFin);
                  
//             }
//         });
   


//      public static void updateReservation(Client client, Reservation reservation, LocalDate newDateDebut,
//                                            LocalDate newDateFin) {
//         // Check if the reservation exists in the client's list
//         if (!client.getListReservation().contains(reservation)) {
//             ShowError ("La réservation n'existe pas pour ce client.");
//         }

//         // Check if the current date is at least one full day before the start date
//         if (LocalDate.now().isAfter(reservation.getDateDeb().minusDays(1))) {
//             ShowError ("La réservation ne peut être modifiée qu'au moins un jour avant la date de début.");
//         }

//         // Check if the new start date is before the new end date
//         if (newDateDebut.isAfter(newDateFin) || newDateDebut.isEqual(newDateFin)) {
//             ShowError  ("La nouvelle date de début doit être avant la nouvelle date de fin.");
//         }

//         // Check if the room is available for the new dates
//         for (Reservation existingReservation : reservation.getChambre().getListReservation()) {
//             if (existingReservation != reservation && datesOverlap(existingReservation.getDateDeb(),
//                     existingReservation.getDateFin(), newDateDebut, newDateFin)) {
//                 ShowError ("La chambre est déjà réservée pour les nouvelles dates sélectionnées.");
//             }
//         }

//         // Update the reservation
//         reservation.setDateDeb(newDateDebut);
//         reservation.setDateFin(newDateFin);

//         showMessage( "Réservation mise à jour avec succès !");
//     }

//     // Helper method to check if two date ranges overlap
//     private static boolean datesOverlap(LocalDate existingStart, LocalDate existingEnd, LocalDate newStart,
//                                         LocalDate newEnd) {
//         return !(newEnd.isBefore(existingStart) || newStart.isAfter(existingEnd));
//     }
// }
