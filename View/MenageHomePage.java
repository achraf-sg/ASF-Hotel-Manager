package View;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import Models.Hotel;

public class MenageHomePage extends JFrame {
    public MenageHomePage(Hotel hotel) {
        setTitle("Cleaner Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new HeaderPanel("Welcome, Cleaner");
        add(header, BorderLayout.NORTH);

        // Sidebar
        List<String> pages = Arrays.asList("Cleaning");
        Runnable[] actions = {
            () -> new MenagePage(hotel.getListCham()).setVisible(true)
        };
        add(new LeftPanel(pages, actions), BorderLayout.WEST);

        // Main Content
        JPanel mainContent = new JPanel();
        mainContent.setBackground(Color.WHITE);
        add(mainContent, BorderLayout.CENTER);

        setVisible(true);
    }
}
