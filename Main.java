import Models.*;
import View.*;
import Controller.*;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Initialize the hotel with sample data
        Hotel hotel = DataInitializer.initializeHotel();

        // Launch the LoginPage
        SwingUtilities.invokeLater(() -> {
            LoginPage loginPage = new LoginPage(hotel);
            new LoginController(hotel, loginPage);
            loginPage.setVisible(true);
        });
    }
}
