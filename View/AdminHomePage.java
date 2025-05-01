package View;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import Models.Hotel;
import Models.Admin;
import Models.Employe;
import Controller.*;

public class AdminHomePage extends JFrame {
    private Admin admin;
    
    public AdminHomePage(Hotel hotel) {
        // Store the admin user from LoginController
        for (Employe emp : hotel.getListEmp()) {
            if (emp instanceof Admin) {
                this.admin = (Admin) emp;
                break;
            }
        }
        
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new HeaderPanel("Welcome, Admin");
        add(header, BorderLayout.NORTH);

        // Sidebar
        List<String> pages = Arrays.asList("Employees", "Clients", "Rooms", "Products");
        Runnable[] actions = {
            () -> {
                EmployeePage employeePage = new EmployeePage(hotel.getListEmp());
                new AdminEmployeeController(hotel, admin, employeePage);
                employeePage.setVisible(true);
                this.dispose();
            },
            () -> {
                ClientPage clientPage = new ClientPage(hotel.getListClient());
                clientPage.setVisible(true);
                this.dispose();
            },
            () -> {
                RoomPage roomPage = new RoomPage(hotel.getListCham(), hotel.getListTypes());
                new RoomPageController(hotel, admin, roomPage);
                roomPage.setVisible(true);
                this.dispose();
            },
            () -> {
                ProduitPage produitPage = new ProduitPage(hotel.getListProd());
                new ProduitController(hotel, admin, produitPage);
                produitPage.setVisible(true);
                this.dispose();
            }
        };
        add(new LeftPanel(pages, actions), BorderLayout.WEST);

        // Main Content
        JPanel mainContent = new JPanel();
        mainContent.setBackground(Color.WHITE);
        add(mainContent, BorderLayout.CENTER);

        setVisible(true);
    }
}
