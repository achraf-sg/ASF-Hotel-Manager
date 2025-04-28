package Controller;

import Models.Chambre;
import Models.Hotel;

import javax.swing.JOptionPane;

public class MenageController {
    private Hotel hotel;

    public MenageController(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Changes the cleanliness status of a room.
     *
     * @param roomNumber The room number of the `Chambre` to update.
     * @param isClean    The new cleanliness status (true for clean, false for dirty).
     */
    public void changeRoomCleanStatus(int roomNumber, boolean isClean) {
        Chambre targetRoom = null;

        // Find the room by its number
        for (Chambre room : hotel.getListCham()) {
            if (room.getNumero() == roomNumber) {
                targetRoom = room;
                break;
            }
        }

        // Update the cleanliness status if the room is found
        if (targetRoom != null) {
            targetRoom.changeCleanStatus(isClean);
            String status = isClean ? "clean" : "dirty";
            JOptionPane.showMessageDialog(null, "Room " + roomNumber + " marked as " + status + ".", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Room " + roomNumber + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
