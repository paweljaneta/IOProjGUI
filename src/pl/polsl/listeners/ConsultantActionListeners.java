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

            if (!protocool.sendTransaction(window.getDateText().getText() + " " + window.getTimeText().getText(), window.getEndDateText().getText() + " " + window.getEndTimeText().getText(), window.getPriceText().getText(), window.getCompanyNameText().getText(), (String) window.getRoomCombo().getSelectedItem(), "0")) {
                JOptionPane.showMessageDialog(window, "Błąd akceptacji", "Accept error", JOptionPane.ERROR_MESSAGE);
            }

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

        });

        window.getResignButton().addActionListener((ActionEvent e) -> {
            //   DefaultTableModel dm = (DefaultTableModel) window.getTable().getModel();
            //   int rowCount = dm.getRowCount();
            //Remove rows one by one from the end of the table
            //    for (int i = rowCount - 1; i >= 0; i--) {
            //        dm.removeRow(i);
            //    }
            window.getRoomCombo().setSelectedIndex(0);
            window.getCompanyNameText().setText("");
            window.getDateText().setText("");
            window.getTimeText().setText("");
            window.getEndTimeText().setText("");
            window.getEndDateText().setText("");
            window.getPriceText().setText("");
        });

        window.getFileExit().addActionListener((ActionEvent e) -> {
            window.dispose();
        });

        window.getHelpAbout().addActionListener((ActionEvent e) -> {

            JOptionPane.showMessageDialog(window, "IO projekt \n\n Kierownik: \n Mateusz Sojka \n\n Członkowie: \n Krzysztof Stręk \n Wojciech Dębski \n Artur Bryk \n Krzysztof Warzecha \n Paweł Janeta");

        });

    }

}
