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
import vista.VistaAltaProfesor;
import vista.VistaPrincipal;
import Modelo.ModeloVistaAltaProfesor;
import Modelo.Profesor;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

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
        this.view.setModelo(modelo.cargarCursos());
        cargarTabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of generated methods, choose Tools | Templates.
        String comando  = e.getActionCommand();
        Profesor l;
        int filaPulsada;
        switch (comando) {
            case "Agregar":
                String nombre = view.getNombre();
                if(!Pattern.matches("^[a-zA-Z]+( [a-zA-z]+)*$", nombre)){
                    JOptionPane.showMessageDialog(null, "El NOMBRE no debe de tener espacios en blanco al principio y solo "
                            + "debe contener letras");
                    break;
                }
                
                String apPat = view.getAp1();
                if(!Pattern.matches("^[a-zA-Z]+( [a-zA-z]+)*$", apPat)){
                    JOptionPane.showMessageDialog(null, "El APELLIDO PATERNO no debe de tener espacios en blanco al principio y solo "
                            + "debe contener letras");
                    break;
                }
                
                String apMat = view.getAp2();
                if(!Pattern.matches("^[a-zA-Z]+( [a-zA-z]+)*$", apMat)){
                    JOptionPane.showMessageDialog(null, "El APELLIDO MATERNO no debe de tener espacios en blanco al principio y solo "
                            + "debe contener letras");
                    break;
                }
                
                int cedula = 0;
                try {
                    Integer.parseInt(view.getCedula());
                    cedula = Integer.parseInt(view.getCedula());
                } catch (NumberFormatException nfe){
                    JOptionPane.showMessageDialog(null, "La cedula solo debe de contener numeros");
                    break;
                }
                
                String nivelMax = view.getNivelEstudio();
                String carrera = view.getCarrera();
                
                String correo = view.getCorreo();
                if(!Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*"
                        + "(\\.[A-Za-z]{2,})$", correo)){
                    JOptionPane.showMessageDialog(null, "El dato CORREO ELECTRONICO es INVALIDO");
                    break;
                }
                
                String area = view.getArea();
                if(!Pattern.matches("^[a-zA-Z]+( [a-zA-z]+)*$", area)){
                    JOptionPane.showMessageDialog(null, "El AREA en el que se desempeña solo debe contener letras");
                    break;
                }
                
                l = new Profesor(nombre, apPat, apMat, cedula, nivelMax, carrera, area, correo);
                modelo.insertProfesor(l);
                cargarTabla();
                limpia();
            break;
            
            case "Guardar Cambios":
                int filaSelec = view.tabla.getSelectedRow();
                if(!(filaSelec >= 0)) {
                    JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla");
                    break;
                }
                
                String nombre2 = view.getNombre();
                if(!Pattern.matches("^[a-zA-Z]+( [a-zA-z]+)*$", nombre2)){
                    JOptionPane.showMessageDialog(null, "El NOMBRE no debe de tener espacios en blanco al principio y solo "
                            + "debe contener solo letras");
                    break;
                }
                
                String apPat2 = view.getAp1();
                if(!Pattern.matches("^[a-zA-Z]+( [a-zA-z]+)*$", apPat2)){
                    JOptionPane.showMessageDialog(null, "El APELLIDO PATERNO no debe de tener espacios en blanco al principio y solo "
                            + "debe contener solo letras");
                    break;
                }
                
                String apMat2 = view.getAp2();
                if(!Pattern.matches("^[a-zA-Z]+( [a-zA-z]+)*$", apMat2)){
                    JOptionPane.showMessageDialog(null, "El APELLIDO MATERNO no debe de tener espacios en blanco al principio y solo "
                            + "debe contener solo letras");
                    break;
                }
                
                int cedula2 = 0;
                try {
                    Integer.parseInt(view.getCedula());
                    cedula2 = Integer.parseInt(view.getCedula());
                } catch (NumberFormatException nfe){
                    JOptionPane.showMessageDialog(null, "La cedula solo debe de contener numeros");
                    break;
                }
                
                String nivelMax2 = view.getNivelEstudio();
                String carrera2 = view.getCarrera();
                
                String correo2 = view.getCorreo();
                if(!Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*"
                        + "(\\.[A-Za-z]{2,})$", correo2)){
                    JOptionPane.showMessageDialog(null, "El dato CORREO ELECTRONICO es INVALIDO");
                    break;
                }
                
                String area2 = view.getArea();
                if(!Pattern.matches("^[a-zA-Z]+( [a-zA-z]+)*$", area2)){
                    JOptionPane.showMessageDialog(null, "El AREA en el que se desempeña solo debe contener letras");
                    break;
                }
                
                l = new Profesor((int) this.view.dtm.getValueAt(filaSelec, 0) ,nombre2, apPat2, apMat2, cedula2, 
                        nivelMax2, carrera2, area2, correo2);
                modelo.updateProfesor(l);
                cargarTabla();
                limpia();
            break;
            
            case "Salir Del Curso":
                
            break;
            
            case "Asignar Curso":
                int id_Curso = modelo.getIds().get(this.view.getIndiceCurso());
                int id_Profesor = (int) this.view.dtm.getValueAt(this.view.tabla.getSelectedRow(), 0);
                String nombreCurso = modelo.getNombres().get(this.view.getIndiceCurso());
                String nombre_Profesor = (String) this.view.dtm.getValueAt(this.view.tabla.getSelectedRow(), 1) + " " +
                        (String) this.view.dtm.getValueAt(this.view.tabla.getSelectedRow(), 2);
                modelo.inscribirProfesor(id_Profesor, id_Curso, nombreCurso, nombre_Profesor);
                
            break;
            
            case "Eliminar":
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
                limpia();
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
        this.view.setArea("");
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
            fila.add(a.getArea());
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
                this.view.setArea(l2.getArea());
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
