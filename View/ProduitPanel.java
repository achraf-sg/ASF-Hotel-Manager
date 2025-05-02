package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import Models.Produit;
import Models.Hotel;
import java.util.Vector;

public class ProduitPanel extends JPanel {
    private JTable produitTable;
    private JTextField nomField, prixField, quantiteField;
    private JButton addButton;
    private Hotel hotel;

    public ProduitPanel(Hotel hotel) {
        this.hotel = hotel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Center Panel - Product Table
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel tableTitle = new JLabel("Products");
        tableTitle.setFont(StyleConfig.TITLE_FONT);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
        centerPanel.add(tableTitle, BorderLayout.NORTH);

        String[] columnNames = {"Name", "Price", "Quantity", "Update", "Delete"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        produitTable = new JTable(model);
        produitTable.setRowHeight(40);
        produitTable.setFont(StyleConfig.NORMAL_FONT);

        // Populate table
        populateProduitTable(hotel.getListProd());

        JScrollPane scrollPane = new JScrollPane(produitTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Right Panel - Add Product Form
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel formTitle = new JLabel("Add Product");
        formTitle.setFont(StyleConfig.TITLE_FONT);
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
        addButton.setFont(StyleConfig.BUTTON_FONT);
        addButton.setBackground(StyleConfig.PRIMARY_COLOR);
        addButton.setForeground(Color.WHITE);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.setMaximumSize(new Dimension(200, 40));
        rightPanel.add(addButton);

        add(rightPanel, BorderLayout.EAST);
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
                "Update",
                "Delete" 
            });
        }
    }

    private JPanel createLabeledField(String label, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(StyleConfig.NORMAL_FONT);
        panel.add(jLabel, BorderLayout.NORTH);
        field.setFont(StyleConfig.NORMAL_FONT);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    // Getters
    public JTextField getNomField() { return nomField; }
    public JTextField getPrixField() { return prixField; }
    public JTextField getQuantiteField() { return quantiteField; }
    public JButton getAddButton() { return addButton; }
    public JTable getTable() { return produitTable; }

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