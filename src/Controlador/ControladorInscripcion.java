/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Curso;
import Modelo.CursoProfesor;
import Modelo.ModeloInscripcion;
import Modelo.Profesor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import vista.VistaConsulta;
import vista.VistaInscripcionProfesor;
import vista.VistaPrincipal;

/**
 *
 * @author ErikHernandez
 */
public class ControladorInscripcion implements ActionListener, ItemListener{
    private VistaPrincipal prin;
    private VistaInscripcionProfesor view;
    private ModeloInscripcion modelo;

    ControladorInscripcion(VistaInscripcionProfesor vistaIn, VistaPrincipal view) {
        prin = view;
        this.view = vistaIn;
        modelo = new ModeloInscripcion("CursosActualizacion");
        cargarDatos();
    }

    public void cargarDatos() {
        this.view.setModeloProfesor(modelo.cargarNombres());
        this.view.setModeloCurso(modelo.cargarCursos());
        cargarTablaProf();
        cargarTablaCurs();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "Inscribir":
                String profesor = this.view.getProfSeleccionado();
                
                if(this.view.getCursoSeleccionado() != ""){
                    modelo.inscribir(view.getCursoSeleccionado(), view.getProfSeleccionado());
                    cargarDatos();
                    this.view.setProfSeleccionado(profesor);
                
                    Curso cur = new Curso();
                    cur.setNombre(this.view.getCursoSeleccionado());
                    Curso cur2 = modelo.selectCursoNom(cur);

                    String[] aux = profesor.split(" ");
                    String nom = aux[0];
                    System.out.println(nom);

                    Profesor prof = new Profesor();
                    prof.setNombre(nom);
                    Profesor prof2 = modelo.selectProfesorNombre(prof);

                    CursoProfesor consulta = new CursoProfesor(prof2.getId(), cur2.getId());
                    VistaConsulta login = new VistaConsulta();
                    ControladorConsulta co = new ControladorConsulta(view, login, consulta);
                    login.conectaControlador(co);
                    view.dispose();
                } else{
                    JOptionPane.showMessageDialog(null, "No puede tomar mas cursos");
                }
                break;
                
            case "Baja":
                int filaSelec = view.tabla1.getSelectedRow();
                if (filaSelec >= 0) {
                    String profesorS = this.view.getProfSeleccionado();
                    modelo.deleteInscrito(view.tabla1.getValueAt(filaSelec, 0).toString(), view.getProfSeleccionado());
                    cargarDatos();
                    this.view.setProfSeleccionado(profesorS);
                } else
                    JOptionPane.showMessageDialog(null, "Para dar de baja a alguien debe de seleccionar "
                            + "el curso del que lo dara de baja");
                break;
                
            case "Regresar":
                view.dispose();
                prin.setVisible(true);
                break;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox aux = (JComboBox) e.getSource();
        if(aux.equals(view.profesores)){
            this.view.setModeloCurso(modelo.cargarCursos());
            modelo.borrarCursosCombo(view);
            cargarTablaProf();
            cargarTablaCurs();
        } else if(aux.equals(view.cursos)){
            //this.view.setModeloProfesor(modelo.cargarNombres());
            //modelo.borrarProfesoresCombo(view);
        }
    }
    
    protected void cargarTablaProf(){
        Vector<Object> fila;
 
        for(int i=this.view.dtm.getRowCount(); i>0; i--){
            this.view.dtm.removeRow(i-1);
        }	
	List<Profesor> profesores = modelo.listProfesor(view);
        for(Profesor a: profesores){
            //Añadir registro a registro en el vector
            fila    = new Vector<Object>();
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
    
    protected void cargarTablaCurs(){
        Vector<Object> fila;
 
        for(int i=this.view.dtm1.getRowCount(); i>0; i--){
            this.view.dtm1.removeRow(i-1);
        }	
	List<Curso> cursos = modelo.listCursos(view);
        for(Curso a: cursos){
            //Añadir registro a registro en el vector
            fila    = new Vector<Object>();
            fila.add(a.getNombre());
            fila.add(a.getfInicio());
            fila.add(a.getfFin());
            fila.add(modelo.buscarNombreResponsable(a.getNombre()));
            fila.add(a.getAula());
            fila.add(a.gethInicio());
            fila.add(a.gethFin());
            //Añadir el vector a la tabla de la clase View
            this.view.dtm1.addRow(fila);
        }
    }
}