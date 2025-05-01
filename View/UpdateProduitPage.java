package View;

import javax.swing.*;
import java.awt.*;
import Models.Produit;

public class UpdateProduitPage extends JFrame {
    private JTextField nomField, prixField, quantiteField;
    private JButton updateButton, cancelButton;

    public UpdateProduitPage(Produit produit) {
        setTitle("Update Product");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Product Name Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);
        nomField = new JTextField(produit.getNom(), 20);
        gbc.gridx = 1;
        add(nomField, gbc);

        // Product Price Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Price:"), gbc);
        prixField = new JTextField(String.valueOf(produit.getPrixUnit()), 20);
        gbc.gridx = 1;
        add(prixField, gbc);

        // Product Quantity Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Quantity:"), gbc);
        quantiteField = new JTextField(String.valueOf(produit.getQuantite()), 20);
        gbc.gridx = 1;
        add(quantiteField, gbc);

        // Update Button
        updateButton = new JButton("Update");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(updateButton, gbc);

        // Cancel Button
        cancelButton = new JButton("Cancel");
        gbc.gridx = 1;
        add(cancelButton, gbc);

        setVisible(true);
    }

    // Getters for fields and buttons
    public JTextField getNomField() {
        return nomField;
    }

    public JTextField getPrixField() {
        return prixField;
    }

    public JTextField getQuantiteField() {
        return quantiteField;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        // Example usage
        Produit produit = new Produit("Product A", 10.0f, 5, null);
        SwingUtilities.invokeLater(() -> new UpdateProduitPage(produit));
    }
}
