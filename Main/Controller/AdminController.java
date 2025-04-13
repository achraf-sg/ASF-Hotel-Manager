package Controller;

import Models.Admin;
import Models.Hotel;
import Models.Employe;
import javax.swing.JOptionPane;

public class AdminController {
    private Hotel hotel;

    public AdminController(Hotel hotel) {
        this.hotel = hotel;
    }

    public boolean authenticateAdmin(String email, String password) {
        for (Employe emp : hotel.getListEmp()) {
            if (emp instanceof Admin &&
                    emp.getEmail().equals(email) &&
                    emp.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void showAdminDashboard() {
        JOptionPane.showMessageDialog(null, "Welcome to Admin Dashboard", "Admin Access", JOptionPane.INFORMATION_MESSAGE);
        // Implement admin dashboard here
    }
}