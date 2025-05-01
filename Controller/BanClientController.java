package Controller;

import Models.Client;
import Models.Hotel;
import View.ClientPage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JTable;

public class BanClientController {

    private Hotel hotel;
    private ClientPage view;
    private Client client;


    public BanClientController(Hotel hotel, ClientPage view, Client client) {
        this.hotel = hotel;
        this.view = view;
        this.client = client;

      view.getTable().addMouseListener(new MouseAdapter() {
     

          public void mouseClicked(MouseEvent e) {
                JTable table = view.getTable();
                int selectedRow = table.rowAtPoint(e.getPoint());
                int selectedColumn = table.columnAtPoint(e.getPoint());
                int columnCount = table.getColumnCount();
    
    
                if (selectedColumn == columnCount - 1) { // Check if the last column is clicked
                    int clientNum = (int) table.getValueAt(selectedRow, 0);
                    Client client = hotel.findClientById(clientNum);
                    if (client != null) {
                        client.setBanned(true);
                        view.populateClientTable(hotel.getListClient());
                    }
                }
          }
        });

    }

}
