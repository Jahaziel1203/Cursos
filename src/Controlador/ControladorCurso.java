/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Curso;
import Modelo.ModeloVistaAltaCurso;
import Modelo.ModeloVistaAltaProfesor;
import Modelo.Profesor;
import com.jcalendar.pane.calendar.CalendarPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import vista.VistaAltaCurso;
import vista.VistaPrincipal;

/**
 *
 * @author ACER
 */
public class ControladorCurso implements ActionListener, MouseListener{
    private VistaPrincipal prin;
    private VistaAltaCurso view;
    private ModeloVistaAltaCurso modelo;
    

    ControladorCurso(VistaAltaCurso login, VistaPrincipal view) {
        //To change body of generated methods, choose Tools | Templates.
        prin = view;
        this.view = login;
        modelo = new ModeloVistaAltaCurso("CursosActualizacion");
        cargarTabla();
        this.view.setModelo(modelo.cargarNombres());
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of generated methods, choose Tools | Templates.
        Curso c;
        int filaPulsada;
        String comando  = e.getActionCommand();
        switch (comando) {
            case "Agregar":
                String nombreCurso = this.view.getNombre();
                if(!Pattern.matches("^[a-zA-Z]+( [a-zA-z]+)*$", nombreCurso)){
                    JOptionPane.showMessageDialog(null, "El NOMBRE no debe de tener espacios en blanco al principio y solo "
                            + "debe contener letras");
                    break;
                }
                
                String fechaIn = this.view.getFechaInicio();
                if(fechaIn.equals("")){
                    JOptionPane.showMessageDialog(null, "Debe seleccionar la fecha de Inicio.");
                    break;
                }
                String fechaFin = this.view.getFechaFin();
                if(fechaFin.equals("")){
                    JOptionPane.showMessageDialog(null, "Debe seleccionar la fecha de Término.");
                    break;
                }
                String profRes = this.view.getProfesorResponsable();
                Profesor p = new Profesor();
                p.setNombre(profRes);
                ModeloVistaAltaProfesor m = new ModeloVistaAltaProfesor("CursosActualizacion");
                Profesor p2 = m.selectProfesorNombre(p);
                String aula = this.view.getAula();
                if(aula.length() != 2){
                    JOptionPane.showMessageDialog(null, "El aula debe contener una letra y un número.");
                    break;
                }
                String hIn = this.view.getHoraInicio();
                String mIn = this.view.getMinutoInicio();
                String horaInicio = hIn + ":" + mIn + ":" + "00";
                String hFin = this.view.getHoraFin();
                String mFin = this.view.getMinutofin();
                String horaFin = hFin + ":" + mFin + ":" + "00";
                c = new Curso(nombreCurso, fechaIn, fechaFin, p2.getId(), aula, horaInicio, horaFin);
                modelo.insertCurso(c);
                m.closeConexion();
                limpia();
                cargarTabla();
            break;
            
            case "Eliminar":
                filaPulsada = this.view.tabla.getSelectedRow();
                if(filaPulsada>=0){
                    c = new Curso();
                    String nom = (String)this.view.dtm.getValueAt(filaPulsada, 0);
                    c.setNombre(nom);
                    modelo.deleteCurso(c);
                }
                limpia();
                cargarTabla();
            break;
            
            case "Guardar Cambios":
                filaPulsada = this.view.tabla.getSelectedRow();
                if(filaPulsada>=0)
                {
                    c = new Curso();
                    String nom = (String)this.view.dtm.getValueAt(filaPulsada, 0);
                    c.setNombre(nom);
                    Curso c3 = modelo.selectCursoNom(c);
                    
                    Curso c2 = new Curso();
                    c2.setId(c3.getId());
                    c2.setNombre(this.view.getNombre());
                    c2.setfInicio(this.view.getFechaInicio());
                    c2.setfFin(this.view.getFechaFin());
                    String nomProf = this.view.getProfesorResponsable();
                    Profesor pro = new Profesor();
                    pro.setNombre(nomProf);
                    ModeloVistaAltaProfesor m1 = new ModeloVistaAltaProfesor("CursosActualizacion");
                    Profesor pro2 = m1.selectProfesorNombre(pro);
                    c2.setIdProf(pro2.getId());
                    c2.setAula(this.view.getAula());
                    String hIn2 = this.view.getHoraInicio();
                    String mIn2 = this.view.getMinutoInicio();
                    String horaInicio2 = hIn2 + ":" + mIn2 + ":" + "00";
                    c2.sethInicio(horaInicio2);
                    String hFin2 = this.view.getHoraFin();
                    String mFin2 = this.view.getMinutofin();
                    String horaFin2 = hFin2 + ":" + mFin2 + ":" + "00";
                    c2.sethFin(horaFin2);
                    c2.setEstado(c3.getEstado());
                    //System.out.println(l.getId());
                    this.modelo.updateCurso(c2);
                    limpia();
                    cargarTabla();
                    m1.closeConexion();
                }
            break;
            case "Fecha Termino:":
                CalendarPane cP = new CalendarPane(view);
                cP.crearUnCale();
               
                ControladorCalendario cC = new ControladorCalendario(cP);
                cP.conectaControlador(cC);
            break;
            
            case "Fecha Inicio:":
                CalendarPane a = new CalendarPane(view);
                a.crearUnCalendario();
               
                ControladorCalendario a1 = new ControladorCalendario(a);
                a.conectaControlador(a1);
            break;
            
            case "Regresar":
                view.dispose();
                prin.setVisible(true);
            break;
        }
    }
    
    private void limpia(){
        this.view.setNombre("");
        this.view.setFechaInicio("");
        this.view.setFechaFin("");
        this.view.setProfesorResponsable("");
        this.view.setAula("");
    }
    
    protected void cargarTabla(){
 
        Vector<Object> fila;
 
        //Limpiar los datos de la tabla
        for(int i=this.view.dtm.getRowCount(); i>0; i--){
            this.view.dtm.removeRow(i-1);
        }
		
		List<Curso> cursos = modelo.listCursos();
        for(Curso a: cursos){
                //Añadir registro a registro en el vector
                fila    = new Vector<Object>();
                fila.add(a.getNombre());
                fila.add(a.getfInicio());
                fila.add(a.getfFin());
                Profesor p = new Profesor();
                p.setId(a.getIdProf());
                Profesor p2 = modelo.selectProfesor(p);
                fila.add(p2.getNombre());
                fila.add(a.getAula());
                fila.add(a.gethInicio());
                fila.add(a.gethFin());
                fila.add(a.getEstado());
                //Añadir el vector a la tabla de la clase View
                this.view.dtm.addRow(fila);
            }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //To change body of generated methods, choose Tools | Templates.
        int filaPulsada = this.view.tabla.getSelectedRow();
        if(filaPulsada>=0)
        {
            Curso l = new Curso();
            String nom = (String)this.view.dtm.getValueAt(filaPulsada, 0);
            l.setNombre(nom);
            Curso l2 = this.modelo.selectCursoNom(l);
            
            if(l2!=null)
            {
                this.view.setNombre(l2.getNombre());
                this.view.setFechaInicio(l2.getfInicio());
                this.view.setFechaFin(l2.getfFin());
                Profesor p = new Profesor();
                p.setId(l2.getIdProf());
                Profesor p2 = modelo.selectProfesor(p);
                this.view.setProfesorResponsable(p2.getNombre());
                this.view.setAula(l2.getAula());
                this.view.setHoraInicio(l2.gethInicio());
                this.view.setHoraFin(l2.gethFin());
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
