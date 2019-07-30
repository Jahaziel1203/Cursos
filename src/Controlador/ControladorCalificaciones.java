/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloCalificaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import vista.VistaCalificaciones;
import vista.VistaPrincipal;

/**
 *
 * @author ErikHernandez
 */
public class ControladorCalificaciones implements ActionListener, ItemListener{
    private VistaPrincipal prin;
    private VistaCalificaciones view;
    private ModeloCalificaciones modelo;
    
    /**
     * Constructor de la clase ControladorCalificaciones
     * @param login vista que es necesaria para el control de sus eventos
     * @param view vista necesaria para posteriormente regresar a ella al presionar el boton menu principal
     */
    ControladorCalificaciones(VistaCalificaciones login, VistaPrincipal view) {
        //To change body of generated methods, choose Tools | Templates.
        prin = view;
        this.view = login;
        modelo = new ModeloCalificaciones("CursosActualizacion");
        cargarDatos();
    }
    
    
    /**
     * Método que ayuda a cargar los datos en los JComboBox de la vistaCalificaciones,
     * estos datos se cargan al instanciar una vistaCalificaciones
     */
    public void cargarDatos() {
        this.view.setModeloProfesor(modelo.cargarNombres());
        this.view.setModeloCurso(modelo.cursosProfesor(view.getProfSeleccionado()));
        cargarTablaCurs();
    }
    
    /**
     * Método sobreescrito, indispensable para el control de eventos producidos por
     * los JButton de la vistaCalificaiones
     * @param e evento capturado para definir la accion a realizar
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando  = e.getActionCommand();
        switch (comando) {
            case "Guardar":
                //Se obtienen los datos necesarios para guardar la calificacion
                String nombreProf = view.getProfSeleccionado();
                String nombreCurso = view.getCursoSeleccionado();
                int calificacion = 0;
                
                if(view.getCalificacion() != null){ //Verificacion de una calificacion no nula
                    calificacion = view.getCalificacion();
                }
                if(nombreProf == "" || nombreProf == null) { //Verificacion de un nombre de profesor no nulo
                    JOptionPane.showMessageDialog(null, "El campo profesor no puede estar vacio");
                    break;
                } 
                if(nombreCurso == "") { //Verificacion de un nombre de curso no nulo
                    JOptionPane.showMessageDialog(null, "No tiene cursos registrados para asignar la calificacion");
                    break;
                }
                // Despues de verificar que ninguno de los tres datos sea nulo se guarda la calificación
                //correspondiente al curso y profesor seleccionados
                modelo.guardarCalificacion(nombreProf, nombreCurso, calificacion);
                break;
                
            case "Calificaciones":
                String nombreProfe = view.getProfSeleccionado();
                String nombreCur = view.getCursoSeleccionado();
                
                if(nombreProfe == "" || nombreProfe == null) { //Verificacion de un nombre de profesor no nulo
                    JOptionPane.showMessageDialog(null, "El campo profesor no puede estar vacio");
                    break;
                } 
                if(nombreCur == "") { //Verificacion de un nombre de curso no nulo
                    JOptionPane.showMessageDialog(null, "No tiene cursos registrados para consultar calificaciones");
                    break;
                }
                modelo.listarCalificaciones(nombreProfe, nombreCur);
                break;
                
            case "Promedio":
                nombreProfe = view.getProfSeleccionado();
                nombreCur = view.getCursoSeleccionado();
                
                if(nombreProfe == "" || nombreProfe == null) { //Verificacion de un nombre de profesor no nulo
                    JOptionPane.showMessageDialog(null, "El campo profesor no puede estar vacio");
                    break;
                } 
                if(nombreCur == "") { //Verificacion de un nombre de curso no nulo
                    JOptionPane.showMessageDialog(null, "No tiene cursos registrados para consultar promedios");
                    break;
                }
                modelo.consultarPromedio(nombreProfe, nombreCur);
                break;
            
            case "Regresar":
                view.dispose();
                prin.setVisible(true);
                break;
        }
        
    }

    
    /**
     * Método encargado del manejo de eventos producidos por el JComboBox profesores de la
     * vistaCalificaciones, dentro de este se actualiza el contenido de los cursos, ya que
     * solo se listan los cursos en los que esta inscrito el profesor seleccionado
     * @param e evento producido por el ComboBox profesores
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        this.view.setModeloCurso(modelo.cursosProfesor(view.getProfSeleccionado()));
        cargarTablaCurs();
    }
    
    /**
     * Método cargarTablaCurs, carga los detalles de cada curso en los que esta inscrito
     * el profesor seleccionado en la vista de las calificaciones
     */
    protected void cargarTablaCurs(){
        Vector<Object> fila;
 
        for(int i=this.view.dtm.getRowCount(); i>0; i--){
            this.view.dtm.removeRow(i-1);
        }	
	List<String[]> cursos = modelo.listPromedios(view);
        for(String[] a: cursos){
            //Añadir registro a registro en el vector
            fila    = new Vector<Object>();
            fila.add(a[0]);
            fila.add(a[1]);
            //Añadir el vector a la tabla de la clase View
            this.view.dtm.addRow(fila);
        }
    }
}