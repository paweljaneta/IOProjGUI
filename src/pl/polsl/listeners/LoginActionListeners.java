package pl.polsl.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import pl.polsl.client.gui.ConsultantGUI;
import pl.polsl.client.gui.Login;
import pl.polsl.client.gui.ManagerGUI;
import pl.polsl.client.protocol.ClientProtocol;

/**
 *
 * @author Paweł
 */
public class LoginActionListeners {

    private Login loginWindow;
    private ClientProtocol protocool;

    KeyListener listener = new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                login();

            }
        }
    };

    /**
     * default constructor
     */
    public LoginActionListeners() {
    }

    /**
     * Constructor for action listeners for login window
     *
     * @param window login frame
     */
    // public LoginActionListeners(Login window, ClientProtocol protocol) {
    public LoginActionListeners(Login window, ClientProtocol clientProtocool) {
        loginWindow = window;
        protocool = clientProtocool;
    }

    private void login() {

        String username = loginWindow.getUserText().getText();
        String password = loginWindow.getPasswordText().getText();
        String privLevel = protocool.login(username, password);
        if (privLevel == null) {
            JOptionPane.showMessageDialog(loginWindow, "Blad logowania", "Login error", JOptionPane.ERROR_MESSAGE);
            loginWindow.dispose();
        } else if (privLevel.equalsIgnoreCase("CHIEF")) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ManagerGUI window = new ManagerGUI();

                    String[] columns = {"Nazwa firmy", "Numer sali","Data", "Godzina"};

                    String[] values = protocool.getTransactions();

                    String[][] data;

                    data = new String[values.length][4];

                    String colValues[];
                    for (int i = 0; i < values.length; i++) {
                        colValues = values[i].split(";");
                        for (int j = 0; j < 4; j++) {
                            data[i][j] = colValues[j + 1];
                        }
                    }

                    window.setTable(columns, data);

                    ManagerActionListeners listeners = new ManagerActionListeners(window, protocool);
                    listeners.addActionListeners();
                    window.setVisible(true);
                }
            });
            loginWindow.dispose();
        } else if (privLevel.equalsIgnoreCase("CONSULTANT")) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ConsultantGUI window = new ConsultantGUI();

                    String[] columns = {"Film", "Numer Sali", "Data rozpoczęcia", "Data zakończenia", "Zarezerwowano"};

                    String[] values = protocool.getRoomsOccupancy();

                    String[][] data;

                    data = new String[values.length][5]; //jekby nie działało to zamienic

                    String colValues[];
                    for (int i = 0; i < values.length; i++) {
                        colValues = values[i].split(";");
                        for (int j = 0; j < 5; j++) {
                            data[i][j] = colValues[j];
                        }
                    }

                    window.setTable(columns, data);

                    ConsultantActionListeners listeners = new ConsultantActionListeners(window, protocool);
                    listeners.addActionListeners();
                    window.setVisible(true);
                }
            });
            loginWindow.dispose();
        }
    }
        /**
         * Method that adds action listeners to login window
         */
    public void addActionListeners() {

        loginWindow.getUserText().addKeyListener(listener);
        loginWindow.getPasswordText().addKeyListener(listener);

        loginWindow.getLoginButton().addActionListener((ActionEvent e) -> {

            login();

        });
    }

}
