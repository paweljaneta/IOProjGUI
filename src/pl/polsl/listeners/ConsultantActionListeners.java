/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.listeners;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pl.polsl.client.gui.ConsultantGUI;
import pl.polsl.client.protocol.ClientProtocol;

/**
 *
 * @author Paweł
 */
public class ConsultantActionListeners {

    private ConsultantGUI window;
    private ClientProtocol protocool;

    /**
     * default constructor
     */
    public ConsultantActionListeners() {
    }

    /**
     * Constructor of action listeners class
     *
     * @param consultantWindow consultant window frame
     * @param clientProtocool communication protocool
     */
    public ConsultantActionListeners(ConsultantGUI consultantWindow, ClientProtocol clientProtocool) {
        window = consultantWindow;
        protocool = clientProtocool;
    }

    /**
     * Method that adds action listeners to window
     */
    public void addActionListeners() {

        window.getAcceptButton().addActionListener((ActionEvent e) -> {

            if (!protocool.sendTransaction(window.getCompanyNameText().getText(), (String) window.getRoomCombo().getSelectedItem(), window.getTimeText().getText(), window.getDateText().getText())) {
                JOptionPane.showMessageDialog(window, "Błąd akceptacji", "Accept error", JOptionPane.ERROR_MESSAGE);
            }

        });

        window.getResignButton().addActionListener((ActionEvent e) -> {
            DefaultTableModel dm = (DefaultTableModel) window.getTable().getModel();
            int rowCount = dm.getRowCount();
            //Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
            window.getCompanyNameText().setText("");
            window.getDateText().setText("");
            window.getTimeText().setText("");
        });

        window.getFileExit().addActionListener((ActionEvent e) -> {
            window.dispose();
        });

        window.getHelpAbout().addActionListener((ActionEvent e) -> {

            JOptionPane.showMessageDialog(window, "IO projekt \n\n Kierownik: \n Mateusz Sojka \n\n Członkowie: \n Krzysztof Stręk \n Wojciech Dębski \n Artur Bryk \n Krzysztof Warzecha \n Paweł Janeta");

        });

    }

}
