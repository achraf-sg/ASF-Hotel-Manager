package Controller;

import Models.*;
import View.ClientPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class ClientPanelController {
    private Hotel model;
    private ClientPanel view;

    public ClientPanelController(Hotel model, ClientPanel view) {
        this.model = model;
        this.view = view;

        initTableListeners();
    }

    private void initTableListeners() {
        view.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().rowAtPoint(e.getPoint());
                int column = view.getTable().columnAtPoint(e.getPoint());
                int lastColumn = view.getTable().getColumnCount() - 1;
                
                if (column == lastColumn) { // Ban column
                    int clientId = (int) view.getTable().getValueAt(row, 0);
                    Client client = model.findClientById(clientId);
                    
                    if (client != null && !client.isBanned()) {
                        int confirm = JOptionPane.showConfirmDialog(
                            view,
                            "Are you sure you want to ban this client?",
                            "Confirm Ban",
                            JOptionPane.YES_NO_OPTION
                        );
                        
                        if (confirm == JOptionPane.YES_OPTION) {
                            client.setBanned(true);
                            view.showMessage("Client banned successfully!");
                            view.populateClientTable(model.getListClient());
                        }
                    } else if (client != null && client.isBanned()) {
                        int confirm = JOptionPane.showConfirmDialog(
                            view,
                            "Are you sure you want to unban this client?",
                            "Confirm unBan",
                            JOptionPane.YES_NO_OPTION
                        );
                        
                        if (confirm == JOptionPane.YES_OPTION) {
                            client.setBanned(false);
                            view.showMessage("Client unbanned successfully!");
                            view.populateClientTable(model.getListClient());
                        }
                    }
                }
            }
        });
    }
}