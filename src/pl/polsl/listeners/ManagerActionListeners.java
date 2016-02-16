package pl.polsl.listeners;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pl.polsl.client.gui.ManagerGUI;
import pl.polsl.client.protocol.ClientProtocol;

/**
 *
 * @author Paweł
 */
public class ManagerActionListeners {

    private ManagerGUI window;
    private ClientProtocol protocool;

    /**
     * default constructor
     */
    public ManagerActionListeners() {
    }

    /**
     * Constructor of action listeners class
     *
     * @param managerWindow manager window frame
     * @param clientProtocool connection protocool
     */
    public ManagerActionListeners(ManagerGUI managerWindow, ClientProtocol clientProtocool) {
        window = managerWindow;
        protocool = clientProtocool;
    }

    private int selectedRow() {
        return window.getTable().getSelectedRow();
    }

    /**
     * adds action listeners to manager window frame
     */
    public void addActionListeners() {

        //na podstawie wybanego wiersza w tablicy autoryzować go
        window.getAuthorizeButton().addActionListener((ActionEvent e) -> {

            if (selectedRow() >= 0) {
                DefaultTableModel dm = (DefaultTableModel) window.getTable().getModel();
                String transactions[] = protocool.getTransactions();
                String transactionID = "";
                for (String transaction : transactions) {
                    String[] data = transaction.split(";");
                    int counter = 0;
                    for (int j = 1; j < 5; j++) {
                        if (data[j].equalsIgnoreCase((dm.getValueAt(selectedRow(), j - 1)).toString())) {
                            counter++;
                        }
                    }
                    //to jest szukana transakcja
                    if (counter == 4) {
                        transactionID = data[0];
                    }
                }

                if (!protocool.acceptTransaction(transactionID)) {
                    JOptionPane.showMessageDialog(window, "Błąd akceptacji", "Accept error", JOptionPane.ERROR_MESSAGE);
                } else {
                    refreshTable();
                }

            }

        });
        //powinno działać prawie jak wyżej
        window.getRefuseButton().addActionListener((ActionEvent e) -> {

            if (selectedRow() >= 0) {
                DefaultTableModel dm = (DefaultTableModel) window.getTable().getModel();
                String transactions[] = protocool.getTransactions();
                String transactionID = "";
                for (String transaction : transactions) {
                    String[] data = transaction.split(";");
                    int counter = 0;
                    for (int j = 1; j < 5; j++) {
                        if (data[j].equalsIgnoreCase((dm.getValueAt(selectedRow(), j - 1)).toString())) {
                            counter++;
                        }
                    }
                    //to jest szukana transakcja
                    if (counter == 4) {
                        transactionID = data[0];
                    }
                }
                if (!protocool.refuseTransaction(transactionID)) {
                    JOptionPane.showMessageDialog(window, "Błąd odmowy", "Refuse error", JOptionPane.ERROR_MESSAGE);
                } else {
                    refreshTable();
                }
            }

        });

        window.getFileExit().addActionListener((ActionEvent e) -> {
            window.dispose();
        });

        window.getHelpAbout().addActionListener((ActionEvent e) -> {

            JOptionPane.showMessageDialog(window, "IO projekt \n\n Kierownik: \n Mateusz Sojka \n\n Członkowie: \n Krzysztof Stręk \n Wojciech Dębski \n Artur Bryk \n Krzysztof Warzecha \n Paweł Janeta");

        });

    }

    public void refreshTable() {
        String[] columns = {"Nazwa firmy", "Numer sali", "Data", "Godzina"};

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
    }

}
