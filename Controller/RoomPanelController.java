package Controller;

import Models.*;
import View.RoomPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class RoomPanelController {
    private Hotel model;
    private RoomPanel view;

    public RoomPanelController(Hotel model, RoomPanel view) {
        this.model = model;
        this.view = view;

        initAddListener();
        initTableListeners();
    }

    private void initAddListener() {
        view.getAddChamButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numero = Integer.parseInt(view.getNumChamField().getText());
                    int etage = Integer.parseInt(view.getEtageField().getText());
                    RoomType type = (RoomType) view.getTypeChamBox().getSelectedItem();

                    // Validate that room doesn't already exist
                    boolean exists = false;
                    for (Chambre c : model.getListCham()) {
                        if (c.getNumero() == numero && c.getEtage() == etage) {
                            exists = true;
                            break;
                        }
                    }

                    if (exists) {
                        view.showError("This room already exists!");
                        return;
                    }

                    // Add the room
                    model.addChambre(numero, etage, type);
                    view.populateRoomTable(model.getListCham());
                    view.showMessage("Room added successfully!");
                    view.clearForm();
                } catch (NumberFormatException ex) {
                    view.showError("Room number and floor must be numeric values!");
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
                    int roomNumber = (int) view.getTable().getValueAt(row, 0);
                    int floor = (int) view.getTable().getValueAt(row, 1);

                    int confirm = JOptionPane.showConfirmDialog(
                        view,
                        "Are you sure you want to delete this room?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        model.deleteChambre(roomNumber, floor);
                        view.showMessage("Room deleted successfully!");
                        view.populateRoomTable(model.getListCham());
                    }
                }
            }
        });
    }
}