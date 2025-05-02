// package Controller;

// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;
// import javax.swing.JTable;
// import java.util.Vector;

// import Models.Chambre;
// import Models.Hotel;
// import View.MenagePage;

// public class MenagePageController {

//     private Hotel hotel;
//     private MenagePage view;

//     public MenagePageController(Hotel hotel, MenagePage menagePage) {
//         this.hotel = hotel;
//         this.view = menagePage;

//         // Add ActionListener for the search button
//         view.getSearchButton().addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 try {
//                     int roomNumber = Integer.parseInt(view.getSearchField().getText());
//                     Chambre room = hotel.findChambreByNumber(roomNumber);
//                     if (room != null) {
//                         Vector<Chambre> filteredRooms = new Vector<>();
//                         filteredRooms.add(room);
//                         view.showTable(filteredRooms);
//                     } else {
//                         view.showError("Room not found.");
//                     }
//                 } catch (NumberFormatException ex) {
//                     view.showError("Invalid room number. Please enter a valid number.");
//                 }
//             }
//         });
        

//         // Add MouseListener for the cleaning table
//         view.getTable().addMouseListener(new MouseAdapter() {
//             @Override
//             public void mouseClicked(MouseEvent e) {
//                 JTable cleaningTable = view.getTable();
//                 int selectedRow = cleaningTable.rowAtPoint(e.getPoint());
//                 int selectedColumn = cleaningTable.columnAtPoint(e.getPoint());
//                 int columnCount = cleaningTable.getColumnCount();

//                 if (selectedColumn == columnCount - 1) { // Check if the last column is clicked
//                     int chambreNum = (int) cleaningTable.getValueAt(selectedRow, 0);
//                     Chambre chamber = hotel.findChambreByNumber(chambreNum);
//                     if (chamber != null) {
//                         chamber.setClean(true);
//                         chamber.setAvailable(true);
//                         view.showTable(hotel.getListCham());
//                     }
//                 }
//             }
//         });
//     }
// }
