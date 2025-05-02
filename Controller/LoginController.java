package Controller;

import Models.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private Hotel model;
    private LoginPage view;

    public LoginController(Hotel model, LoginPage view) {
        this.model = model;
        this.view = view;

        initListeners();
    }

    private void initListeners() {
        view.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String email = view.getEmailField().getText();
        String password = new String(view.getPasswordField().getPassword());

        Employe emp = model.findEmployeeByEmail(email);

        if (emp == null) {
            view.showError("No employee found with this email.");
            return;
        }

        if (!emp.getPassword().equals(password)) {
            view.showError("Incorrect password.");
            return;
        }

        // Create the MainFrame with the appropriate user
        MainFrame mainFrame = new MainFrame(model, emp);
        mainFrame.setVisible(true);
        view.dispose();
    }
}