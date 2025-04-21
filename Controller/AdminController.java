package Controller;

import Models.Admin;
import Models.Hotel;
import View.HomePage;
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
                // Open HomePage for the authenticated Admin
                new HomePage(emp, hotel);
                return true;
            }
        }
        return false;
    }

    public void showAdminDashboard() {
        JOptionPane.showMessageDialog(null, "Welcome to Admin Dashboard", "Admin Access", JOptionPane.INFORMATION_MESSAGE);
    }





}