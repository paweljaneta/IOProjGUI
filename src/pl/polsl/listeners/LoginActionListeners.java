/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.listeners;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import pl.polsl.client.gui.ConsultantGUI;
import pl.polsl.client.gui.Login;
import pl.polsl.client.gui.ManagerGUI;

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

    /**
     *default constructor
     */
    public LoginActionListeners() {
    }

    /**
     *Constructor for action listeners for login window
     * @param window login frame
     */
    public LoginActionListeners(Login window) {
        loginWindow = window;
    }

    /**
     *Method that adds action listeners to login window
     */
    public void addActionListeners() {

        loginWindow.getLoginButton().addActionListener((ActionEvent e) -> {

            if (loginWindow.getUserText().getText().equals(managerName)) {
                if (loginWindow.getPasswordText().getText().equals(managerPassword)) {

                    javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            ManagerGUI window = new ManagerGUI();
                            
                            String[] columns ={"Nazwa firmy","Numer sali","Godzina","Data"};
                            String[][] values = {{"Andrzej i spóła","1","11:20","22.11.1990"},{"Andrzej","2","15:20","11.11.2222"},{"spóła","3","11:50","22.11.3333"}};
                            
                            window.setTable(columns, values);
                            
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
                            
                            String[] columns={"Film","Numer Sali","Data rozpoczęcia","Data zakończenia","Zarezerwowano"};
                            String[][] data = {{"Kaj je Nemo","1","22.10.2017","23.10.2017","1"},{"Elektronicky mordulec","2","16.08.2016","16.08.2016","1"},{"Jeszcze inszy film","3","13.02.2222","13.02.2223","22,22"}};

                            
                            window.setTable(columns,data);
                            
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
