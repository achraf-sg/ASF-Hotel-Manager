package Controller;

import Models.Admin;
import Models.Employe;
import Models.Hotel;
import Models.Menage;
import Models.Reception;
import View.LoginPage;
import View.AdminEmployePage;
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

        /*// Redirection selon le rôle
        if (emp instanceof Admin) {
            AdminEmployeePage adminPage = new AdminEmployeePage();
            new AdminEmployeeController(model, (Admin) emp, adminPage);
            adminPage.setVisible(true);
            view.dispose();
        } else if (emp instanceof Reception) {
            ReceptionHomePage receptionPage = new ReceptionHomePage();
            new ReceptionController(model, (Reception) emp, receptionPage); // tu peux le créer
            receptionPage.setVisible(true);
            view.dispose();
        } else if (emp instanceof Menage) {
            MenageHomePage menagePage = new MenageHomePage();
            new MenageController(model, (Menage) emp, menagePage); // tu peux le créer aussi
            menagePage.setVisible(true);
            view.dispose();
        } else {
            view.showError("Type d'utilisateur inconnu.");
        }*/
    }

}