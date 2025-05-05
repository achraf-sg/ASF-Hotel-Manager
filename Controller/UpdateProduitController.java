package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Models.Hotel;
import Models.Produit;
import View.ProduitPanel;
import View.UpdateProduitPage;

public class UpdateProduitController {

    private Hotel hotel;
    private Produit produit;
    private UpdateProduitPage view;
    private ProduitPanel mainView; // Change from ProduitPage to ProduitPanel

    public UpdateProduitController(Hotel hotel, Produit produit, UpdateProduitPage view, ProduitPanel mainView) {
        this.hotel = hotel;
        this.produit = produit;
        this.view = view;
        this.mainView = mainView;

        // Pre-fill the form with the current product data
        view.getNomField().setText(produit.getNom());
        view.getPrixField().setText(String.valueOf(produit.getPrixUnit()));
        view.getQuantiteField().setText(String.valueOf(produit.getQuantite()));

        // Add listener for the update button
        view.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUpdate();
            }
        });
        
        // Add window listener to refresh table when update window closes
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (mainView != null) {
                    mainView.populateProduitTable(hotel.getListProd());
                }
            }
        });
        
        // Add listener for the cancel button
        view.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

    /**
     * Handles the update logic for the product.
     */
    private void handleUpdate() {
        try {
            String nom = view.getNomField().getText();
            float prixUnit = Float.parseFloat(view.getPrixField().getText());
            int qte = Integer.parseInt(view.getQuantiteField().getText());

            // Ensure quantity is positive
            if (qte < 0 || prixUnit < 0) {
                view.showError("The quantity and price must be positive values!");
                return;
            }

            // Update the product data
            produit.setNom(nom);
            produit.setPrixUnit(prixUnit);
            produit.setQuantite(qte);

            view.showMessage(" Product updated successfully!");
            
            // Refresh the table in the main view
            if (mainView != null) {
                mainView.populateProduitTable(hotel.getListProd());
            }
            
            view.dispose(); // Close the update page

        } catch (NumberFormatException ex) {
            view.showError(" Please enter valid numeric values for price and quantity!");
        }
    }
}