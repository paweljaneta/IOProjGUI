/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.client.gui;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Paweł
 */
public class LoginActionListeners {

    private Login loginWindow;


    String managerName = "andrzej";
    String managerPassword = "12345";

    String consultantName = "konsultant";
    String consultantPassword = "haslo";

    public LoginActionListeners() {
    }

    public LoginActionListeners(Login window) {
        loginWindow = window;
    }

    public void addActionListeners() {

        loginWindow.getLoginButton().addActionListener((ActionEvent e) -> {

            if (loginWindow.getUserText().getText().equals(managerName)) {
                if (loginWindow.getPasswordText().getText().equals(managerPassword)) {

                    javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            ManagerGUI window = new ManagerGUI();
                            ManagerActionListeners listeners = new ManagerActionListeners(window);
                            listeners.addActionListeners();
                            window.setVisible(true);
                        }
                    });
                    loginWindow.dispose();
                } else {
                    JOptionPane.showMessageDialog(loginWindow, "Zle haslo", "Login error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (loginWindow.getUserText().getText().equals(consultantName)) {
                if (loginWindow.getPasswordText().getText().equals(consultantPassword)) {

                    javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            ConsultantGUI window = new ConsultantGUI();
                            ConsultantActionListeners listeners = new ConsultantActionListeners(window);
                            listeners.addActionListeners();
                            window.setVisible(true);
                        }
                    });
                    loginWindow.dispose();
                    
                    
                } else {
                    JOptionPane.showMessageDialog(loginWindow, "Zle haslo", "Login error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(loginWindow, "Zly login", "Login error", JOptionPane.ERROR_MESSAGE);
            }

        });
    }

}
