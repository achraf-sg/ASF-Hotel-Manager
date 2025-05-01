package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;
import Models.Produit;
import Models.Admin;
import Models.Hotel;

public class ProduitPage extends JFrame {
    private JTable produitTable;
    private JTextField nomField, prixField, quantiteField;
    private JButton addButton;

    public ProduitPage(Vector<Produit> produits) {
        setTitle("Product Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(37, 99, 235));
        headerPanel.setPreferredSize(new Dimension(800, 100));
        JLabel titleLabel = new JLabel("Product Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Center Panel - Product Table
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel tableTitle = new JLabel("Products");
        tableTitle.setFont(new Font("Arial", Font.BOLD, 18));
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
        centerPanel.add(tableTitle, BorderLayout.NORTH);

        String[] columnNames = {"Name", "Price", "Quantity", "Update"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        produitTable = new JTable(model);
        produitTable.setRowHeight(40);
        produitTable.setFont(new Font("Arial", Font.PLAIN, 14));

        // Populate table
        populateProduitTable(produits);

        JScrollPane scrollPane = new JScrollPane(produitTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Right Panel - Add Product Form
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel formTitle = new JLabel("Add Product");
        formTitle.setFont(new Font("Arial", Font.BOLD, 18));
        formTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(formTitle);
        rightPanel.add(Box.createVerticalStrut(25));

        // Product Name Field
        rightPanel.add(createLabeledField("Name:", nomField = new JTextField()));
        rightPanel.add(Box.createVerticalStrut(10));

        // Product Price Field
        rightPanel.add(createLabeledField("Price:", prixField = new JTextField()));
        rightPanel.add(Box.createVerticalStrut(10));

        // Product Quantity Field
        rightPanel.add(createLabeledField("Quantity:", quantiteField = new JTextField()));
        rightPanel.add(Box.createVerticalStrut(25));

        // Add Button
        addButton = new JButton("Add Product");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(37, 99, 235));
        addButton.setForeground(Color.WHITE);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.setMaximumSize(new Dimension(200, 40));
        rightPanel.add(addButton);

        add(rightPanel, BorderLayout.EAST);

        setVisible(true);
    }

    // Populate the product table with product data
    public void populateProduitTable(Vector<Produit> produits) {
        DefaultTableModel tableModel = (DefaultTableModel) produitTable.getModel();
        tableModel.setRowCount(0); // Clear the table

        for (Produit produit : produits) {
            tableModel.addRow(new Object[]{
                produit.getNom(),
                produit.getPrixUnit(),
                produit.getQuantite(),
                "Update" // Placeholder for update button
            });
        }
    }

    private JPanel createLabeledField(String label, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(jLabel, BorderLayout.NORTH);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    // Getters
    public JTextField getNomField() {
        return nomField;
    }

    public JTextField getPrixField() {
        return prixField;
    }

    public JTextField getQuantiteField() {
        return quantiteField;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JTable getTable() {
        return produitTable;
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void clearForm() {
        nomField.setText("");
        prixField.setText("");
        quantiteField.setText("");
    }

    
}
