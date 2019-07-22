/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author ErikHernandez
 */

import Modelo.ModeloVistaAltaProfesor;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import vista.VistaAltaProfesor;

public class PintarCeldaProf extends DefaultTableCellRenderer {
    private VistaAltaProfesor v1;
    private ModeloVistaAltaProfesor m1;
        
    public PintarCeldaProf(VistaAltaProfesor v1, ModeloVistaAltaProfesor m1){
        this.v1 = v1;
        this.m1 = m1;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        cell.setBackground(this.m1.determinarColor((int) this.v1.getTabla().getValueAt(row, 0)));
        cell.setForeground(Color.BLACK);
        return cell;
    }
}