package pl.polsl.client;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.Socket;
import pl.polsl.client.gui.Login;
import javax.swing.JOptionPane;
import pl.polsl.client.protocol.ClientProtocol;
import pl.polsl.client.protocol.ConnectionVariables;
import pl.polsl.listeners.LoginActionListeners;

/**
 * Main client class
 *
 * @author MateuszSojka
 * @version 1.0
 */
public class Client {

    /**
     * Main method, creates 2 threads - GUI and messanger
     *
     * @param args command line paramters, may conatiain port and IP address
     */
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) {
        Integer port = 4404;
        String ip = "localhost"; //192.168.130.1
        if (args.length == 2) {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }
        try {
            ClientProtocol protocol = new ClientProtocol(new ConnectionVariables(new Socket(ip, port)));
            EventQueue.invokeLater(() -> {
                Login dialog = new Login();
                LoginActionListeners actionListeners = new LoginActionListeners(dialog, protocol);
                actionListeners.addActionListeners();
                dialog.setVisible(true);
            });
        } catch (IOException ex) {
            new JOptionPane(ex.getMessage(), JOptionPane.ERROR_MESSAGE, JOptionPane.OK_OPTION);
        }
    }

}
