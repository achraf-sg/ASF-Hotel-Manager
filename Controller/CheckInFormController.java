package Controller;

import Models.*;
import View.CheckInFormPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;

public class CheckInFormController {
  private Hotel model;
  private CheckInFormPage view;
  private Reservation reservation;

  public CheckInFormController(Hotel model, CheckInFormPage view, Reservation reservation) {
    this.model = model;
    this.view = view;
    this.reservation = reservation;

    // Dynamically generate client input fields based on the room's max capacity
    int maxClients = reservation.getType().getMaxPeople();
    view.generateClientFields(maxClients);

    view.getValidateButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Validate button clicked!");
        String numStr = view.getRoomComboBox().getSelectedItem().toString();
        if (numStr == null || numStr.isEmpty()) {
          view.showError("Please select a room number.");
          return;
        }

        int numero = Integer.parseInt(numStr.split(" ")[1]);
        System.out.println(numero);
        Chambre chambre = model.findChambreByNumber(numero);
        if (chambre == null) {
          view.showError("Error: Room not found.");
          return;
        }
        chambre.setAvailable(false);

        // Retrieve client information dynamically
        Vector<Client> clients = new Vector<>();
        for (int i = 0; i < maxClients; i++) {
          try {
            JTextField nameField = view.getClientNameField(i);
            JTextField surnameField = view.getClientSurnameField(i);
            JTextField emailField = view.getClientEmailField(i);
            JTextField phoneField = view.getClientPhoneField(i);

            if (nameField != null && !nameField.getText().isEmpty()&& model.isValidName(nameField.getText()) &&
                 surnameField != null && !surnameField.getText().isEmpty()&& model.isValidName(surnameField.getText()) &&phoneField != null && !phoneField.getText().isEmpty()&& model.isValidPhoneNumber(phoneField.getText())) {
              Client client = new Client(
                  nameField.getText(),
                  surnameField.getText(),
                  phoneField.getText(),
                  emailField.getText(),
                  model);
              clients.add(client);
            }else {
              view.showError("Please fill in all client fields correctly.");
              
return;
            }
          } catch (NullPointerException ex) {
            view.showError("Please fill in all client fields.");
            return;
          }
        }

        // Check if the number of clients exceeds the room's max capacity
        if (clients.size() > maxClients) {
          view.showError("Maximum number of clients exceeded for this room type.");
          return;
        }

        // Create and associate stays for each client
        for (Client c : clients) {
          Client existingClient = model.findClientByEmail(c.getEmail());
          

          if (existingClient != null) {
            if (existingClient.isBanned()) {
              view.showError("Client banned, unable to check-in");
              view.dispose();
              return;
            } else {
              Sejour sejour = new Sejour(reservation, c, chambre);
              existingClient.addSejour(sejour);
              try {
                model.addSejour(sejour);
              } catch (ArrayIndexOutOfBoundsException ex) {
                view.showError("Please fill in all client fields.");
                return;
              }
              reservation.setCheckedIn(true);
              view.showMessage("Check-in success !");
              view.dispose();
            }
            }
            else {
            Sejour sejour = new Sejour(reservation, c, chambre);
            c.addSejour(sejour);
            model.addClient(c);
            try {
              model.addSejour(sejour);
            } catch (ArrayIndexOutOfBoundsException ex) {
              view.showError("Please fill in all client fields.");
              return;
            }
            reservation.setCheckedIn(true);
            view.showMessage("Check-in success !");
            view.dispose();
          }
            ;
          }
        

        // Add stay to the global list and remove the reservation
        // try {
        //   model.addSejour(sejour);
        // } catch (ArrayIndexOutOfBoundsException ex) {
        //   view.showError("Please fill in all client fields.");
        //   return;
        // }
        // reservation.setCheckedIn(true);
        // view.showMessage("Check-in success !");
        // view.dispose();
      }
    });

    view.getCancelButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        view.dispose();
      }
    });
  }
}