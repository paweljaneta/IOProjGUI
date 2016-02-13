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
public class ManagerActionListeners {
    
      private ManagerGUI window;

    public ManagerActionListeners() {
    }
    
    public ManagerActionListeners(ManagerGUI consultantWindow){
        window=consultantWindow;
    }
    
     public void addActionListeners() {

        window.getAuthorizeButton().addActionListener((ActionEvent e) -> {
            
            
        });
        
        window.getRefuseButton().addActionListener((ActionEvent e)->{
        
        
        });
        
        window.getFileExit().addActionListener((ActionEvent e)->{
        window.dispose();
        });
        
        window.getHelpAbout().addActionListener((ActionEvent e)->{
        
            JOptionPane.showMessageDialog(window, "IO projekt \n\n Kierownik: \n Mateusz Sojka \n\n Członkowie: \n Krzysztof Stręk \n Wojciech Dębski \n Artur Bryk \n Krzysztof Warzecha \n Paweł Janeta");
        
        });
        
     }
    
}
