package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

import Models.Admin;
import Models.Hotel;
import Models.Produit;
import View.ProduitPage;
import View.UpdateProduitPage;

public class ProduitController {

    private Hotel hotel;
    private ProduitPage view;

    public ProduitController(Hotel hotel, Admin admin, ProduitPage view) {
        this.hotel = hotel;
        this.view = view;

        // Initialize table listeners for update and delete functionality
        initTableListeners();

        // Add product
        view.getAddButton().addActionListener(e -> {
            try {
                String nom = view.getNomField().getText().toLowerCase().trim();

                // Check if the product already exists
                if (hotel.findProduitByName(nom) != null) {
                    view.showError("Ce produit existe déjà !");
                    return;
                }

                float prixUnit = Float.parseFloat(view.getPrixField().getText());
                int qte = Integer.parseInt(view.getQuantiteField().getText());

                // Ensure quantity is positive
                if (qte < 0) {
                    view.showError("La quantité ne peut pas être négative !");
                    return;
                }

                Produit newProduit = new Produit(nom, prixUnit, qte, hotel);
                hotel.getListProd().add(newProduit);
                view.showMessage("Produit ajouté avec succès !");
                view.clearForm();

            } catch (NumberFormatException ex) {
                view.showError("Veuillez entrer des valeurs valides pour le prix et la quantité !");
            }
        });

        
    }

    /**
     * Initializes the table listeners for updating and deleting products.
     */
    private void initTableListeners() {
        view.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().rowAtPoint(e.getPoint());
                int column = view.getTable().columnAtPoint(e.getPoint());
                int lastColumn = view.getTable().getColumnCount() - 1;

                // Update column
                if (column == lastColumn - 1) { // Assuming the second-to-last column is for updates
                    String name = view.getTable().getValueAt(row, 0).toString(); // Assuming the name is in column 0
                    Produit produit = hotel.findProduitByName(name);
                    if (produit != null) {
                        UpdateProduitPage updatePage = new UpdateProduitPage(produit);
                        new UpdateProduitController(hotel, produit, updatePage);
                        updatePage.setVisible(true);
                    }
                }

                // Delete column
                if (column == lastColumn) { // Assuming the last column is for deletion
                    String name = view.getTable().getValueAt(row, 0).toString(); // Assuming the name is in column 0
                    Produit produit = hotel.findProduitByName(name);
                    if (produit != null) {
                        int confirm = JOptionPane.showConfirmDialog(
                            view,
                            "Are you sure you want to delete the product: " + produit.getNom() + "?",
                            "Confirm Deletion",
                            JOptionPane.YES_NO_OPTION
                        );
                        if (confirm == JOptionPane.YES_OPTION) {
                            hotel.getListProd().remove(produit);
                            view.showMessage("Product deleted successfully!");
                            view.populateProduitTable(hotel.getListProd());
                        }
                    }
                }
            }
        });
    }
}
