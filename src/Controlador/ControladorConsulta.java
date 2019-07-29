/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Curso;
import Modelo.CursoProfesor;
import Modelo.ModeloConsulta;
import Modelo.Profesor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;
import vista.VistaAltaProfesor;
import vista.VistaConsulta;
import vista.VistaInscripcionProfesor;

/**
 *
 * @author ACER
 */
public class ControladorConsulta implements ActionListener{
    private VistaInscripcionProfesor vista;
    private VistaConsulta view;
    private ModeloConsulta modelo;
    private CursoProfesor con;
    
    ControladorConsulta(VistaInscripcionProfesor login, VistaConsulta view, CursoProfesor c) {
        //To change body of generated methods, choose Tools | Templates.
        vista = login;
        this.view = view;
        modelo = new ModeloConsulta("CursosActualizacion");
        con = c;
        cargarTablaProfesor();
        cargarTablaCurso();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String comando  = ae.getActionCommand();//To change body of generated methods, choose Tools | Templates.
        switch(comando){
            case "ACEPTAR":
                view.dispose();
                vista.setVisible(true);
            break;
        }
    }

    private void cargarTablaCurso() {
        Vector<Object> fila;
 
        //Limpiar los datos de la tabla
        for(int i=this.view.dtm1.getRowCount(); i>0; i--){
            this.view.dtm1.removeRow(i-1);
        }
		
        Curso cur = new Curso();
        cur.setId(con.getIdC());
	Curso a = modelo.selectCurso(cur);
            //Añadir registro a registro en el vector
            fila    = new Vector<Object>();
            fila.add(a.getNombre());
            fila.add(a.getfInicio());
            fila.add(a.getfFin());
            Profesor p = new Profesor();
            p.setId(a.getIdProf());
            Profesor p2 = modelo.selectProfesor(con);
            fila.add(p2.getNombre() + " " + p2.getApPaterno());
            fila.add(a.getAula());
            fila.add(a.gethInicio());
            fila.add(a.gethFin());
            fila.add(a.getEstado());
            //Añadir el vector a la tabla de la clase View
            this.view.dtm1.addRow(fila);
    }
    
    protected void cargarTablaProfesor(){
 
        Vector<Object> fila;
 
        //Limpiar los datos de la tabla
        for(int i=this.view.dtm.getRowCount(); i>0; i--){
            this.view.dtm.removeRow(i-1);
        }	
        Profesor f = new Profesor();
        f.setId(con.getIdP());
	List<CursoProfesor> profesores = modelo.listInscritos(con);
        for(CursoProfesor z: profesores){
            Profesor a = modelo.selectProfesor(z);
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
    
}
