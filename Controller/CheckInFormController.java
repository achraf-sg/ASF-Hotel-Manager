package Controller;

import Models.*;
import View.CheckInFormPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

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
        String numStr = (String) view.getRoomComboBox().getSelectedItem();
        if (numStr == null || numStr.isEmpty()) {
          view.showError("Please select a room number.");
          return;
        }

        int numero = Integer.parseInt(numStr);
        Chambre chambre = model.findChambreByNumber(numero);
        if (chambre == null) {
          view.showError("Error: Room not found.");
          return;
        }
        chambre.setAvailable(false);

        // Retrieve client information dynamically
        Vector<Client> clients = new Vector<>();
        for (int i = 0; i < maxClients; i++) {
          try{
          JTextField nameField = view.getClientNameField(i);
          JTextField surnameField = view.getClientSurnameField(i);
          JTextField emailField = view.getClientEmailField(i);
          JTextField addressField = view.getClientAddressField(i);
          JTextField phoneField = view.getClientPhoneField(i);

          if (nameField != null && !nameField.getText().isEmpty()) {
            Client client = new Client(
                nameField.getText(),
                surnameField.getText(),
                phoneField.getText(),
                emailField.getText(),
                model);
            clients.add(client);
          }}
          catch (NullPointerException ex){
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
          Sejour sejour = new Sejour(reservation, c);

          if (existingClient != null) {
            existingClient.addSejour(sejour);
          } else {
            c.addSejour(sejour);
            model.addClient(c);;
          }
        }

        // Add stay to the global list and remove the reservation
        model.addSejour(new Sejour(reservation, clients.get(0)));
        view.showMessage("Check-in success !");
        view.dispose();
      }
    });
  }
}