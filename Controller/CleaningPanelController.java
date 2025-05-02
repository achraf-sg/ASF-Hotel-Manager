package Controller;

import Models.*;
import View.MenagePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class CleaningPanelController {
    private Hotel model;
    private MenagePanel view;

    public CleaningPanelController(Hotel model, MenagePanel view) {
        this.model = model;
        this.view = view;

        initSearchListener();
        initTableListeners();
    }

    private void initSearchListener() {
        view.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String searchText = view.getSearchField().getText().trim();
                    
                    if (searchText.isEmpty()) {
                        // If search is empty, show all rooms
                        view.showTable(model.getListCham());
                        return;
                    }
                    
                    int roomNumber = Integer.parseInt(searchText);
                    
                    // Find the room in the model
                    boolean found = false;
                    for (Chambre room : model.getListCham()) {
                        if (room.getNumero() == roomNumber && !room.isClean()) {
                            // Found the room and it's not clean
                            view.getTable().setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][]{
                                    {room.getNumero(), room.getEtage(), "No", "Change to Clean"}
                                },
                                new String[]{"Room Number", "Floor", "Is Clean", "Change Clean Status"}
                            ));
                            found = true;
                            break;
                        }
                    }
                    
                    if (!found) {
                        view.showError("Room " + roomNumber + " not found or is already clean!");
                    }
                } catch (NumberFormatException ex) {
                    view.showError("Please enter a valid room number!");
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
                
                if (column == lastColumn) { // "Change to Clean" column
                    int roomNumber = (int) view.getTable().getValueAt(row, 0);
                    int floor = (int) view.getTable().getValueAt(row, 1);
                    
                    // Find the room in the model
                    for (Chambre room : model.getListCham()) {
                        if (room.getNumero() == roomNumber && room.getEtage() == floor) {
                            int confirm = JOptionPane.showConfirmDialog(
                                view,
                                "Mark room " + roomNumber + " as clean?",
                                "Confirm Status Change",
                                JOptionPane.YES_NO_OPTION
                            );
                            
                            if (confirm == JOptionPane.YES_OPTION) {
                                room.setClean(true);
                                view.showMessage("Room marked as clean successfully!");
                                view.showTable(model.getListCham()); // Refresh the table
                            }
                            break;
                        }
                    }
                }
            }
        });
    }
}