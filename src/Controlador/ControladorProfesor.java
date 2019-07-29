/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.CursoProfesor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vista.VistaAltaProfesor;
import vista.VistaPrincipal;
import Modelo.ModeloVistaAltaProfesor;
import Modelo.Profesor;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import vista.VistaConsulta;

/**
 *
 * @author ACER
 */
public class ControladorProfesor implements ActionListener, MouseListener{
    private VistaPrincipal prin;
    private VistaAltaProfesor view;
    private ModeloVistaAltaProfesor modelo;
    
    ControladorProfesor(VistaAltaProfesor login, VistaPrincipal view) {
        //To change body of generated methods, choose Tools | Templates.
        prin = view;
        this.view = login;
        modelo = new ModeloVistaAltaProfesor("CursosActualizacion");
        cargarTabla();
        pintar();
        
    }
    
    private void pintar(){
        TableCellRenderer renderer = new PintarCeldaProf(this.view, this.modelo);
        this.view.getTabla().setDefaultRenderer(Object.class, renderer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of generated methods, choose Tools | Templates.
        String comando  = e.getActionCommand();
        Profesor l;
        int filaPulsada;
        switch (comando) {
            case "Aceptar":
                pintar();
                String nombre = view.getNombre();
                int filaSelec = view.tabla.getSelectedRow();
                
                if(nombre.length() == 0) {
                    JOptionPane.showMessageDialog(null, "El campo NOMBRE no debe estar vacío", "¡Alerta!", JOptionPane.WARNING_MESSAGE);
                    break;
                } else if(nombre.charAt(0) == ' '){
                    JOptionPane.showMessageDialog(null, "El campo NOMBRE no debe tener espacios al principio",
                    "¡Alerta!", JOptionPane.WARNING_MESSAGE);
                    break;
                } else if(!Pattern.matches("^[a-zA-Z]+( [a-zA-z]+)*$", nombre)){
                    JOptionPane.showMessageDialog(null, "El campo NOMBRE debe contener solo letras", "¡Error!", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                
                String apPat = view.getAp1();
                if(apPat.length() == 0) {
                    JOptionPane.showMessageDialog(null, "El campo APELLIDO PATERNO no debe estar vacío", 
                            "¡Alerta!", JOptionPane.WARNING_MESSAGE);
                    break;
                } else if(apPat.charAt(0) == ' '){
                    JOptionPane.showMessageDialog(null, "El campo APELLIDO PATERNO no debe tener espacios al principio",
                            "¡Alerta!", JOptionPane.WARNING_MESSAGE);
                    break;
                } else if(!Pattern.matches("^[a-zA-Z]+( [a-zA-z]+)*$", apPat)){
                    JOptionPane.showMessageDialog(null, "El APELLIDO PATERNO debe contener solo letras", "Error!", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                
                String apMat = view.getAp2();
                if(apMat.length() == 0) {
                    JOptionPane.showMessageDialog(null, "El campo APELLIDO MATERNO no debe estar vacio", 
                            "¡Alerta!", JOptionPane.WARNING_MESSAGE);
                    break;
                } else if(apMat.charAt(0) == ' '){
                    JOptionPane.showMessageDialog(null, "El campo APELLIDO MATERNO no debe tener espacios al principio",
                            "¡Alerta!", JOptionPane.WARNING_MESSAGE);
                    break;
                } else if(!Pattern.matches("^[a-zA-Z]+( [a-zA-z]+)*$", apMat)){
                    JOptionPane.showMessageDialog(null, "El APELLIDO MATERNO debe contener solo letras", "Error!", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                
                int cedula = 0;
                try {
                    if(!(view.getCedula().length() == 8 || view.getCedula().length() == 7)) {
                        JOptionPane.showMessageDialog(null, "Una cedula profesional, consta de 7 u 8 dígitos",
                                "¡Alerta!", JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                    Integer.parseInt(view.getCedula());
                    cedula = Integer.parseInt(view.getCedula());
                } catch (NumberFormatException nfe){
                    JOptionPane.showMessageDialog(null, "La CÉDULA solo debe contener números", "Error!", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                
                String nivelMax = view.getNivelEstudio();
                String carrera = view.getCarrera();
                
                String correo = view.getCorreo();
                if(correo.length() == 0) {
                    JOptionPane.showMessageDialog(null, "El campo CORREO no debe estar vacío", "¡Alerta!", 
                            JOptionPane.WARNING_MESSAGE);
                    break;
                } else if(!Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*"
                        + "(\\.[A-Za-z]{2,})$", correo)){
                    JOptionPane.showMessageDialog(null, "El dato CORREO ELECTRÓNICO es INVÁLIDO", "Error!", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                
                if(filaSelec >= 0) {
                    l = new Profesor((int) this.view.dtm.getValueAt(filaSelec, 0) ,nombre, apPat, apMat, cedula, 
                        nivelMax, carrera, correo);
                    modelo.updateProfesor(l);
                    cargarTabla();
                } else {
                    l = new Profesor(nombre, apPat, apMat, cedula, nivelMax, carrera, correo);
                    modelo.insertProfesor(l);
                    cargarTabla();
                }
                limpia();
                break;
            case "Eliminar":
                pintar();
                filaPulsada = this.view.tabla.getSelectedRow();
                
                if(filaPulsada >= 0){
                    l = new Profesor();
                    int iD = (int)this.view.dtm.getValueAt(filaPulsada, 0);
                    l.setId(iD);
                    modelo.deleteProfesor(l);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla");
                    break;
                }
                cargarTabla();
            break;
            
            case "Regresar":
                view.dispose();
                prin.setVisible(true);
            break;
        }
    }
    
    private void limpia(){
        this.view.setNombre("");
        this.view.setAp1("");
        this.view.setAp2("");
        this.view.setCedula("");
        this.view.setCorreo("");
    }
    
    protected void cargarTabla(){
 
        Vector<Object> fila;
 
        //Limpiar los datos de la tabla
        for(int i=this.view.dtm.getRowCount(); i>0; i--){
            this.view.dtm.removeRow(i-1);
        }	
	List<Profesor> profesores = modelo.listProfesores();
        for(Profesor a: profesores){
            //Añadir registro a registro en el vector
            fila    = new Vector<Object>();
            fila.add(a.getId());
            fila.add(a.getNombre());
            fila.add(a.getApPaterno());
            fila.add(a.getApMaterno());
            fila.add(a.getCedula());
            fila.add(a.getNivelMax());
            fila.add(a.getCarrera());
            fila.add(a.getCorreo());
            //Añadir el vector a la tabla de la clase View
            this.view.dtm.addRow(fila);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int filaPulsada = this.view.tabla.getSelectedRow();
        if(filaPulsada>=0) {
            Profesor l = new Profesor();
            int id = (int)this.view.dtm.getValueAt(filaPulsada, 0);
            l.setId(id);
            Profesor l2 = this.modelo.selectProfesor(l);
            
            if(l2!=null) {
                this.view.setNombre(l2.getNombre());
                this.view.setAp1(l2.getApPaterno());
                this.view.setAp2(l2.getApMaterno());
                this.view.setCedula(l2.getCedula()+"");
                this.view.setEstudios(l2.getNivelMax());
                this.view.setCarrera(l2.getCarrera());
                this.view.setCorreo(l2.getCorreo());
            }
        }
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
