package Controller;

import java.time.LocalDate;
import java.awt.event.ActionEvent;
import Models.*;

class AddReservationController  implements java.awt.event.ActionListener {


     private Hotel hotel;
        private Reservation reservation;
        private Client client;
        private Produit produit;


        public AddReservationController(Hotel hotel) {
            this.hotel = hotel;
        }
        


           public void actionPerformed( ActionEvent e){
            
             String nom=View.AddReservation.Nomfeild.getText();
              String prenom=View.AddReservation.Prenomfeild.getText();;
               String telephone=View.AddReservation.Telephonefeild.getText();;
                String email=View.AddReservation.Emailfeild.getText();;
            Chambre chambre= View.AddReservation.Chambrefeild.getText();;
             ;
            LocalDate dateDebut=View.AddReservation.DateDebutfeild.getText();; ;
             LocalDate dateFin=View.AddReservation.DateFinfeild.getText();;
            
             reservation.createReservation(nom, prenom, telephone, email,  chambre,  dateDebut,  dateFin, hotel);


           }

      




}