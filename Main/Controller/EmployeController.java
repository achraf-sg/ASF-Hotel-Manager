package Controller;

import Models.Employe;
import Models.Admin;
import Models.Hotel;
import javax.swing.JOptionPane;

public class EmployeController {
    private Hotel hotel;

    public EmployeController(Hotel hotel) {
        this.hotel = hotel;
    }

    public boolean authenticateEmploye(String email, String password) {
        for (Employe emp : hotel.getListEmp()) {
            if (!(emp instanceof Admin) &&
                    emp.getEmail().equals(email) &&
                    emp.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void showEmployeDashboard() {
        JOptionPane.showMessageDialog(null, "Welcome to Employee Dashboard", "Employee Access", JOptionPane.INFORMATION_MESSAGE);
        // Implement employee dashboard here
    }
}