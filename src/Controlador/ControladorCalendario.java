/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import com.jcalendar.pane.calendar.CalendarPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author ACER
 */
public class ControladorCalendario implements ActionListener, KeyListener
{
    private CalendarPane calendario;
    
    public ControladorCalendario(CalendarPane calendario)
    {
        this.calendario = calendario;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String comando  = e.getActionCommand();
        
        switch (comando) {
            case "ACEPTAR":
            	calendario.getJFrame().dispose();
            break;
            default:
                System.err.println("Comando no definido");
            break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
    
}
