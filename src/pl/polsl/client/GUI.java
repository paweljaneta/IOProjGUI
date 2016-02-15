/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.client;

import java.io.IOException;
import java.net.Socket;
import pl.polsl.client.gui.Login;
import pl.polsl.client.protocol.ClientProtocol;
import pl.polsl.client.protocol.ConnectionVariables;
import pl.polsl.listeners.LoginActionListeners;

/**
 *
 * @author Paweł
 */
public class GUI {

    private static void createAndShowGUI() {
        Login dialog = new Login();
        LoginActionListeners actionListeners;

        actionListeners = new LoginActionListeners(dialog);
        actionListeners.addActionListeners();
        dialog.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }

}
