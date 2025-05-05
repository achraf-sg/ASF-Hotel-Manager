package View;

import Models.Client;
import Models.Hotel;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ClientPanel extends JPanel {
    private JTable clientTable;
    private Hotel hotel;

    public ClientPanel(Hotel hotel) {
        this.hotel = hotel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Center Panel - Client Table
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel tableTitle = new JLabel("Clients");
        tableTitle.setFont(StyleConfig.TITLE_FONT);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
        centerPanel.add(tableTitle, BorderLayout.NORTH);

        String[] columnNames = {"ID", "First Name", "Last Name", "Email", "Phone", "Status", "Ban"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        clientTable = new JTable(model);
        clientTable.setRowHeight(40);
        clientTable.setFont(StyleConfig.NORMAL_FONT);
        clientTable.getTableHeader().setReorderingAllowed(false);
        // Populate table
        populateClientTable(hotel.getListClient());

        JScrollPane scrollPane = new JScrollPane(clientTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
    }

    // Populate the client table with client data
    public void populateClientTable(Vector<Client> clients) {
        DefaultTableModel tableModel = (DefaultTableModel) clientTable.getModel();
        tableModel.setRowCount(0); // Clear the table

        for (Client client : clients) {
            tableModel.addRow(new Object[]{
                client.getIdClient(),
                client.getNom(),
                client.getPrenom(),
                client.getEmail(),
                client.getTelephone(),
                client.isBanned() ? "Banned" : "Active",
                client.isBanned() ? "Unbann" : "Ban"
            });
        }
    }

    // Getters
    public JTable getTable() {
        return clientTable;
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}