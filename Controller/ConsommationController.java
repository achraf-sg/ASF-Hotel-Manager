// package Controller;

// import Models.*;
// import View.ConsommationPage;

// import javax.swing.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;
// import java.util.Vector;

// public class ConsommationController {
//   private Hotel model;
//   private ConsommationPage view;
//   private Sejour sejour;
  

//   public ConsommationController(Hotel model, ConsommationPage view, Sejour sejour) {
//     this.model = model;
//     this.view = view;
//     this.sejour = sejour;
//     Vector<Consommation> consommations = new Vector<>();

//     view.getAddButton().addActionListener(new ActionListener() {
//       @Override
//       public void actionPerformed(ActionEvent e) {
//         String productName = view.getNameField().getText().toLowerCase().trim();
//         int quantite = Integer.parseInt(view.getQuantite().getText());
//         Produit prod = model.searchProductByName(productName);
//         if (prod == null) {
//           view.showError("Error: Product Not Found");
//           return;
//         }
//         if (quantite > prod.getQuantite()) {
//           view.showError("Error: there are only" + prod.getQuantite() + "items left of this product");
//           return;
//         }
//         Consommation consommation = new Consommation(prod, quantite);
//         consommations.add(consommation);
//         view.remplirTableConsommation(consommation);
//       }
//     });

//     view.getConfirmButton().addActionListener(new ActionListener() {
//       @Override
//       public void actionPerformed(ActionEvent e) {
//         if(!consommations.isEmpty()){
//         for (Consommation c : consommations) {
//           sejour.addConsommation(c);
//         }}
//         else{
//           view.showError("Please Enter a product first");
//         }
//       }
//     });

//     // delete consommation
//     view.getTable().addMouseListener(new MouseAdapter() {
//       @Override
//       public void mouseClicked(MouseEvent e) {
//         int row = view.getTable().rowAtPoint(e.getPoint());
//         int column = view.getTable().columnAtPoint(e.getPoint());
//         if (column == view.getTable().getColumnCount - 1) {
//           int consommationId = (int) view.getTable().getValueAt(row, 0);
//           Consommation consommationToDelete = sejour.searchConsommationById(consommationId);
//           if (consommationToDelete != null) {
//             consommations.remove(consommationToDelete);
//             view.remplirTableConsommation(consommations);
//             view.showMessage("Deletion Success!");
//           } else {
//             view.showError("Error: product not found");
//           }
//         }
//       }
//     });
//   }
// }
