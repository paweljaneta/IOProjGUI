/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.listeners;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import pl.polsl.client.gui.ConsultantGUI;

/**
 *
 * @author Paweł
 */
public class ConsultantActionListeners {
    
    private ConsultantGUI window;

    public ConsultantActionListeners() {
    }
    
    public ConsultantActionListeners(ConsultantGUI consultantWindow){
        window=consultantWindow;
    }
    
     public void addActionListeners() {

        window.getAcceptButton().addActionListener((ActionEvent e) -> {
            
            
        });
        
        window.getResignButton().addActionListener((ActionEvent e)->{
        
        
        });
        
        window.getFileExit().addActionListener((ActionEvent e)->{
        window.dispose();
        });
        
        window.getHelpAbout().addActionListener((ActionEvent e)->{
        
            JOptionPane.showMessageDialog(window, "IO projekt \n\n Kierownik: \n Mateusz Sojka \n\n Członkowie: \n Krzysztof Stręk \n Wojciech Dębski \n Artur Bryk \n Krzysztof Warzecha \n Paweł Janeta");
        
        });
        
     }
    
}
