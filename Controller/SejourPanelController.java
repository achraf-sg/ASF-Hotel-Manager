package Controller;

import Models.Hotel;
import Models.Reservation;
import Models.Sejour;
import View.SejourPanel;
import View.ConsommationPage;
import Controller.ConsommationController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SejourPanelController {
  private Hotel model;
  private SejourPanel view;

  public SejourPanelController(Hotel model, SejourPanel view) {
    this.model = model;
    this.view = view;
    
    view.remplirTableSejours(model.getOngoingSejours());
    //go to consommation
    view.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              int row = view.getTable().rowAtPoint(e.getPoint());
              int column = view.getTable().columnAtPoint(e.getPoint());
              
              if (column == view.getTable.getColumnCount() - 1) { 
                int id = (int) view.getTable().getValueAt(row, 0);
                Sejour sejour = model.searchSejourById(id);
                ConsommationPage page = new ConsommationPage();
                new ConsommationController(model, page, sejour);
              }
            }
          });

    //search
    view.getSearchButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String email = view.getSearchField().getText().trim();
        if (email.isEmpty()) {
          return;
        }

        Vector<Sejour> result = model.searchSejoursByEmail(model.getOngoingSejours(), email);
        view.remplirTableSejours(result);
      }
    });
  }

  
}

