package Controller;

import Models.*;
import View.ProduitPanel;
import View.UpdateProduitPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ProductPanelController {
    private Hotel model;
    private ProduitPanel view;

    public ProductPanelController(Hotel model, ProduitPanel view) {
        this.model = model;
        this.view = view;

        initAddListener();
        initTableListeners();
    }

    private void initAddListener() {
        view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = view.getNomField().getText().toLowerCase().trim();
                    
                    // Check if product already exists
                    if (model.findProduitByName(name) != null) {
                        view.showError("This product already exists!");
                        return;
                    }
                    
                    float price = Float.parseFloat(view.getPrixField().getText());
                    int quantity = Integer.parseInt(view.getQuantiteField().getText());
                    
                    if (quantity < 0) {
                        view.showError("Quantity cannot be negative!");
                        return;
                    }
                    
                    if (price <= 0) {
                        view.showError("Price must be greater than zero!");
                        return;
                    }
                    
                    // Create and add the product
                    Produit product = new Produit(name, price, quantity, model);
                    model.getListProd().add(product);
                    view.populateProduitTable(model.getListProd());
                    view.showMessage("Product added successfully!");
                    view.clearForm();
                } catch (NumberFormatException ex) {
                    view.showError("Price and quantity must be valid numbers!");
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
                
                // Update column
                if (column == lastColumn - 1) {
                    String name = view.getTable().getValueAt(row, 0).toString();
                    Produit product = model.findProduitByName(name);
                    
                    if (product != null) {
                        // Create the update page as before
                        UpdateProduitPage updatePage = new UpdateProduitPage(product);
                        
                        // Add window listener to refresh the table when update window is closed
                        updatePage.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                view.populateProduitTable(model.getListProd());
                            }
                        });
                        
                        // Make update window position relative to main window
                        updatePage.setLocationRelativeTo(SwingUtilities.getWindowAncestor(view));
                        
                        // Create the controller and show the update page
                        new UpdateProduitController(model, product, updatePage, view);
                        updatePage.setVisible(true);
                    }
                }
                // Delete column
                else if (column == lastColumn) {
                    String name = view.getTable().getValueAt(row, 0).toString();
                    Produit product = model.findProduitByName(name);
                    
                    if (product != null) {
                        int confirm = JOptionPane.showConfirmDialog(
                            view,
                            "Are you sure you want to delete this product?",
                            "Confirm Deletion",
                            JOptionPane.YES_NO_OPTION
                        );
                        
                        if (confirm == JOptionPane.YES_OPTION) {
                            model.getListProd().remove(product);
                            view.showMessage("Product deleted successfully!");
                            view.populateProduitTable(model.getListProd());
                        }
                    }
                }
            }
        });
    }
}


