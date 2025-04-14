import javax.swing.*;

import View.HomePage;
import View.Login;
import Models.*;
public class Main {
    public static void main(String[] args) {
        Hotel hotel = DataInitializer.initializeHotel();
        Login login = new Login(hotel);

    }
}
