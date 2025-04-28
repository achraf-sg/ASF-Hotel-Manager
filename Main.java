import Models.*;
import View.*;

public class Main {
    public static void main(String[] args) {
        // Initialize the hotel with sample data
        Hotel hotel = DataInitializer.initializeHotel();

        // Open the AdminClientPage
        new AdminClientPage(hotel);
    }
}
