/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.listeners;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import pl.polsl.client.gui.ManagerGUI;

/**
 *
 * @author Paweł
 */
public class ManagerActionListeners {

    private ManagerGUI window;

    /**
     *default constructor
     */
    public ManagerActionListeners() {
    }

    /**
     *Constructor of action listeners class
     * @param managerWindow manager window frame
     */
    public ManagerActionListeners(ManagerGUI managerWindow) {
        window = managerWindow;
    }

    private int selectedRow(){
        return window.getTable().getSelectedRow();
    }
    
    /**
     *adds action listeners to manager window frame
     */
    public void addActionListeners() {

        //na podstawie wybanego wiersza w tablicy autoryzować go
        window.getAuthorizeButton().addActionListener((ActionEvent e) -> {


        });
        //powinno działać prawie jak wyżej
        window.getRefuseButton().addActionListener((ActionEvent e) -> {

        });

        window.getFileExit().addActionListener((ActionEvent e) -> {
            window.dispose();
        });

        window.getHelpAbout().addActionListener((ActionEvent e) -> {

            JOptionPane.showMessageDialog(window, "IO projekt \n\n Kierownik: \n Mateusz Sojka \n\n Członkowie: \n Krzysztof Stręk \n Wojciech Dębski \n Artur Bryk \n Krzysztof Warzecha \n Paweł Janeta");

        });

    }

}
