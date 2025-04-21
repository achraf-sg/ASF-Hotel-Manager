package Tests;

import Models.*;
import Controller.*;

public class ControllerTest {
    public static void main(String[] args) {
        // Initialize the hotel
        Hotel hotel = DataInitializer.initializeHotel();

        // Test AdminController
        AdminController adminController = new AdminController(hotel);
        System.out.println("Testing AdminController...");
        boolean isAuthenticated = adminController.authenticateAdmin("admin@hotel.com", "admin123");
        System.out.println("Admin Authentication: " + (isAuthenticated ? "Passed" : "Failed"));

        // Test adding an employee
        Employe newEmployee = new Employe("John", "Doe", "123 Street", "123456789", "john.doe@hotel.com", hotel, "password123");
        adminController.addEmployee(newEmployee);
        System.out.println("Employee Added: " + (hotel.getListEmp().contains(newEmployee) ? "Passed" : "Failed"));

        // Test deleting an employee
        adminController.deleteEmployee(newEmployee.getId());
        System.out.println("Employee Deleted: " + (!hotel.getListEmp().contains(newEmployee) ? "Passed" : "Failed"));

        // Test updating an employee
        Employe employeeToUpdate = hotel.getListEmp().get(0);
        adminController.updateEmployee(employeeToUpdate.getId(), "UpdatedName", "updated.email@hotel.com", "987654321");
        System.out.println("Employee Updated: " + (employeeToUpdate.getNom().equals("UpdatedName") ? "Passed" : "Failed"));

        // Test finding an employee by ID
        Employe foundEmployee = adminController.findEmployeeById(employeeToUpdate.getId());
        System.out.println("Find Employee: " + (foundEmployee != null ? "Passed" : "Failed"));

        // Test EmployeController
        EmployeController employeController = new EmployeController(hotel);
        System.out.println("Testing EmployeController...");
        boolean isEmployeeAuthenticated = employeController.authenticateEmploye("employee@hotel.com", "emp123");
        System.out.println("Employee Authentication: " + (isEmployeeAuthenticated ? "Passed" : "Failed"));
    }
}
