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

    public void addEmployee(Employe newEmp) {
        hotel.getListEmp().add(newEmp);
        JOptionPane.showMessageDialog(null, "Employee added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }


    public void deleteEmployee(int employeeId) {
        Employe empToRemove = null;
        for (Employe emp : hotel.getListEmp()) {
            if (emp.getId() == employeeId) {
                empToRemove = emp;
                break;
            }
        }
        if (empToRemove != null) {
            hotel.getListEmp().remove(empToRemove);
            JOptionPane.showMessageDialog(null, "Employee deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Employee not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateEmployee(int employeeId, String newName, String newEmail, String newPhone) {
        for (Employe emp : hotel.getListEmp()) {
            if (emp.getId() == employeeId) {
                emp.setNom(newName);
                emp.setEmail(newEmail);
                emp.setTelephone(newPhone);
                JOptionPane.showMessageDialog(null, "Employee updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Employee not found.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public Employe findEmployeeById(int employeeId) {
        for (Employe emp : hotel.getListEmp()) {
            if (emp.getId() == employeeId) {
                return emp;
            }
        }
        JOptionPane.showMessageDialog(null, "Employee not found.", "Error", JOptionPane.ERROR_MESSAGE);
        return null;
    }


}