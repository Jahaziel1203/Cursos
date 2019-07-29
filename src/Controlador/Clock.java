/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DateChooser.TimeChooser;
import Modelo.ModeloVistaAltaCurso;
import javax.swing.JFrame;
import vista.VistaAltaCurso;


/**
 *
 * @author ACER
 */
public class Clock extends JFrame{
    VistaAltaCurso v;
    ModeloVistaAltaCurso m;
    String s;
    public TimeChooser t;
    
    
    // Modificado **************************************************************************************************************************************
    public Clock(VistaAltaCurso v, String ss, ModeloVistaAltaCurso m){
        this.setBounds(320, 340, 130, 70);
        this.setUndecorated(true);
        this.v = v;
        this.m = m;
        //this.setBounds(100,100,200,150);
        this.setVisible(true);
        s = ss;
        //t.setBounds(200, 200, 130, 30);
        
        // Modificado **************************************************************************************************************************************
        t = new TimeChooser(v, s, m);
        this.add(t);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
