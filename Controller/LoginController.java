package Controller;

import Models.Admin;
import Models.Employe;
import Models.Hotel;
import Models.Menage;
import Models.Reception;
import View.LoginPage;
import View.AdminHomePage;
import View.ReceptionHomePage;
import View.MenageHomePage;

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
            view.showError("Aucun employé trouvé avec cet email.");
            return;
        }

        if (!emp.getPassword().equals(password)) {
            view.showError("Mot de passe incorrect.");
            return;
        }

        if (emp instanceof Admin) {
            // Redirect to AdminHomePage
            AdminHomePage adminPage = new AdminHomePage(model);
            adminPage.setVisible(true);
            view.dispose();
            System.out.println("Admin logged in: " + emp.getNom());
        } else if (emp instanceof Reception) {
            // Redirect to ReceptionHomePage
            ReceptionHomePage receptionPage = new ReceptionHomePage(model);
            receptionPage.setVisible(true);
            view.dispose();
        } else if (emp instanceof Menage) {
            // Redirect to MenageHomePage
            MenageHomePage menagePage = new MenageHomePage(model);
            menagePage.setVisible(true);
            view.dispose();
        } else {
            view.showError("Type d'utilisateur inconnu.");
        }
    }
}