/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vista.VistaAltaCurso;
import vista.VistaAltaProfesor;
import vista.VistaInscripcionProfesor;
import vista.VistaPrincipal;

/**
 *
 * @author ACER
 */
public class ControladorPrincipal implements ActionListener, MouseListener{
    private vista.VistaPrincipal view;
    

    public ControladorPrincipal(VistaPrincipal v) {
        //To change body of generated methods, choose Tools | Templates.
        view = v;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
//To change body of generated methods, choose Tools | Templates.
    String comando  = e.getActionCommand();
    switch (comando) {
            case "Agregar Curso":
                VistaAltaCurso login = new VistaAltaCurso();
                ControladorCurso co = new ControladorCurso(login,view);
                login.conectaControlador(co);
                view.dispose();
                break;
                
            case "Agregar Profesor":
                VistaAltaProfesor vista = new VistaAltaProfesor();
                ControladorProfesor c = new ControladorProfesor(vista,view);
                vista.conectaControlador(c);
                view.dispose();
                break;
                
            case "Inscribir Profesor":
                VistaInscripcionProfesor vistaIn = new VistaInscripcionProfesor();
                ControladorInscripcion con = new ControladorInscripcion(vistaIn,view);
                vistaIn.conectaControlador(con);
                view.dispose();
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
//To change body of generated methods, choose Tools | Templates.
    }
    
}
