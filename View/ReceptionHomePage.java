package View;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import Models.Hotel;

public class ReceptionHomePage extends JFrame {
    public ReceptionHomePage(Hotel hotel) {
        setTitle("Receptionist Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new HeaderPanel("Welcome, Receptionist");
        add(header, BorderLayout.NORTH);

        // Sidebar
        List<String> pages = Arrays.asList("Reservations", "Clients");
        Runnable[] actions = {
            () -> new ReservationPage(hotel.getListRes(), hotel.getListTypes()).setVisible(true),
            () -> new ClientPage(hotel.getListClient()).setVisible(true)
        };
        add(new LeftPanel(pages, actions), BorderLayout.WEST);

        // Main Content
        JPanel mainContent = new JPanel();
        mainContent.setBackground(Color.WHITE);
        add(mainContent, BorderLayout.CENTER);

        setVisible(true);
    }
}
