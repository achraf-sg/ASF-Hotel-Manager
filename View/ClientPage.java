// package View;

// import javax.swing.*;
// import javax.swing.border.EmptyBorder;
// import javax.swing.table.DefaultTableModel;
// import java.awt.*;
// import java.util.Vector;
// import Models.Client;

// public class ClientPage extends JFrame {
//     private JTable clientTable;
   
//     public ClientPage(Vector<Client> clients) {
//         setTitle("Client Management");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setSize(1200, 700);
//         setLocationRelativeTo(null);
//         setLayout(new BorderLayout());
//         getContentPane().setBackground(Color.WHITE);

//         // Header Panel
//         JPanel headerPanel = new JPanel();
//         headerPanel.setBackground(new Color(37, 99, 235));
//         headerPanel.setPreferredSize(new Dimension(800, 100));
//         JLabel titleLabel = new JLabel("Client Management", SwingConstants.CENTER);
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//         titleLabel.setForeground(Color.WHITE);
//         headerPanel.add(titleLabel);
//         add(headerPanel, BorderLayout.NORTH);

//         // Center Panel - Client Table
//         JPanel centerPanel = new JPanel(new BorderLayout());
//         centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

//         JLabel tableTitle = new JLabel("Clients");
//         tableTitle.setFont(new Font("Arial", Font.BOLD, 18));
//         tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
//         centerPanel.add(tableTitle, BorderLayout.NORTH);

//         String[] columnNames = {"First Name", "Last Name", "Email", "Phone"};
//         DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
//             @Override
//             public boolean isCellEditable(int row, int column) {
//                 return false; // Make all cells non-editable
//             }
//         };

//         clientTable = new JTable(model);
//         clientTable.setRowHeight(40);
//         clientTable.setFont(new Font("Arial", Font.PLAIN, 14));

//         // Populate table
//         populateClientTable(clients);

//         JScrollPane scrollPane = new JScrollPane(clientTable);
//         scrollPane.setBorder(BorderFactory.createEmptyBorder());
//         centerPanel.add(scrollPane, BorderLayout.CENTER);
//         add(centerPanel, BorderLayout.CENTER);

//         setVisible(true);
//     }

//     // Populate the client table with client data
//     public void populateClientTable(Vector<Client> clients) {
//         DefaultTableModel tableModel = (DefaultTableModel) clientTable.getModel();
//         tableModel.setRowCount(0); // Clear the table

//         for (Client client : clients) {
//             tableModel.addRow(new Object[]{
//                 client.getNom(),
//                 client.getPrenom(),
//                 client.getEmail(),
//                 client.getTelephone()
//             });
//         }
//     }

//     // Getters
//     public JTable getTable() {
//         return clientTable;
//     }


// }
