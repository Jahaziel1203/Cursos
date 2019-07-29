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
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
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
    Clock clo;
    

    ControladorCurso(VistaAltaCurso login, VistaPrincipal view) {
        //To change body of generated methods, choose Tools | Templates.
        prin = view;
        this.view = login;
        modelo = new ModeloVistaAltaCurso("CursosActualizacion");
        cargarTabla();
        this.view.setModelo(modelo.cargarNombres());
        pintar();
        
    }

    private void pintar(){
        TableCellRenderer renderer = new PintarCeldaCur(this.view, this.modelo);
        this.view.getTabla().setDefaultRenderer(Object.class, renderer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of generated methods, choose Tools | Templates.
        Curso c;
        int filaPulsada;
        String comando  = e.getActionCommand();
        switch (comando) {
            case "Agregar":
                comprobar();
                pintar();
                filaPulsada = this.view.tabla.getSelectedRow();
                if(filaPulsada>=0) {
                    c = new Curso();
                    String nom = (String)this.view.dtm.getValueAt(filaPulsada, 0);
                    c.setNombre(nom);
                    Curso c3 = modelo.selectCursoNom(c);
                    
                    Curso c2 = new Curso();
                    c2.setId(c3.getId());
                    c2.setNombre(this.view.getNombre());
                    
                    if(this.view.fechaInicio.getDate() == null) {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar la fecha de Inicio.");
                        break;
                    } else if(this.view.fechaFin.getDate() == null) {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar la fecha de Inicio.");
                        break;
                    } else if (this.view.fechaInicio.getDate().compareTo(this.view.fechaFin.getDate()) < 0){
                        String dia = this.view.fechaInicio.getDate().toString().substring(8,10);
                        String mes = (this.view.fechaInicio.getDate().getMonth() + 1) + "" ;
                        int anio = this.view.fechaInicio.getDate().getYear() + 1900;
                        if(this.view.fechaFin.getDate().getMonth() + 1 < 10)
                            mes = "0" + mes;
                        c2.setfInicio(anio + "-" + mes + "-" + dia);
                        
                        dia = this.view.fechaFin.getDate().toString().substring(8,10);
                        mes = (this.view.fechaFin.getDate().getMonth() + 1) + "" ;
                        anio = this.view.fechaFin.getDate().getYear() + 1900;
                        if(this.view.fechaFin.getDate().getMonth() + 1 < 10)
                            mes = "0" + mes;
                        c2.setfFin(anio + "-" + mes + "-" + dia);
                    } else {
                        JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser menor a la de término");
                        break;
                    }
                    
                    Profesor pro = new Profesor();
                    pro.setId(modelo.getIds().get(this.view.getIndiceCurso()));
                    ModeloVistaAltaProfesor m1 = new ModeloVistaAltaProfesor("CursosActualizacion");
                    Profesor pro2 = m1.selectProfesorNombre(pro);
                    
                    c2.setIdProf(pro2.getId());
                    c2.setAula(this.view.getAulaCombo());
                    
                    String hoIn = this.view.getHoraInicio();
                    String h = "";
                    String mi = "";
                    String form = "";
                    for(int x = 0; x < hoIn.length(); x++){
                        if(x == 0 || x == 1){
                            char letra = hoIn.charAt(x);
                            h += letra;
                        }
                        if(x == 3 || x ==4){
                            char le = hoIn.charAt(x);
                            mi += le;
                        }
                        if(x == 6 || x ==7){
                            char l = hoIn.charAt(x);
                            form += l;
                        }
                    }
                    if(form.equals("PM")){
                        switch(h){
                            case "01":
                                h = "13";
                            break;
                            
                            case "02":
                                h = "14";
                            break;
                            
                            case "03":
                                h = "15";
                            break;
                            
                            case "04":
                                h = "16";
                            break;
                            
                            case "05":
                                h = "17";
                            break;
                            
                            case "06":
                                h = "18";
                            break;
                            
                            case "07":
                                h = "19";
                            break;
                            
                            case "08":
                                h = "20";
                            break;
                            
                            case "09":
                                h = "21";
                            break;
                            
                            case "10":
                                h = "22";
                            break;
                            
                            case "11":
                                h = "23";
                            break;
                            
                            case "12":
                                h = "24";
                            break;
                        }
                    }
                    int hAux = Integer.parseInt(h);
                    String horaInicio = h + ":" + mi + ":" + "00";
                    if(hAux >= 7 && hAux <= 19){
                    c2.sethInicio(horaInicio);
                    } else{
                        JOptionPane.showMessageDialog(null, "La hora de inicio debes estar entre las 7 am y 7 pm");
                        break;
                    }   
                    
                    String hoFin = this.view.getHoraFin();
                    String h1 = "";
                    String mi1 = "";
                    String form1 = "";
                    for(int x = 0; x < hoFin.length(); x++){
                        if(x == 0 || x == 1){
                            char letra = hoFin.charAt(x);
                            h1 += letra;
                        }
                        if(x == 3 || x ==4){
                            char le = hoFin.charAt(x);
                            mi1 += le;
                        }
                        if(x == 6 || x ==7){
                            char l = hoFin.charAt(x);
                            form1 += l;
                        }
                    }
                    if(form1.equals("PM")){
                        switch(h1){
                            case "01":
                                h1 = "13";
                            break;
                            
                            case "02":
                                h1 = "14";
                            break;
                            
                            case "03":
                                h1 = "15";
                            break;
                            
                            case "04":
                                h1 = "16";
                            break;
                            
                            case "05":
                                h1 = "17";
                            break;
                            
                            case "06":
                                h1 = "18";
                            break;
                            
                            case "07":
                                h1 = "19";
                            break;
                            
                            case "08":
                                h1 = "20";
                            break;
                            
                            case "09":
                                h1 = "21";
                            break;
                            
                            case "10":
                                h1 = "22";
                            break;
                            
                            case "11":
                                h1 = "23";
                            break;
                            
                            case "12":
                                h1 = "24";
                            break;
                        }
                    } 
                    int h1Aux = Integer.parseInt(h1);
                    String horaFin = h1 + ":" + mi1 + ":" + "00";
                    if(h1Aux >= 8 && h1Aux <= 20){
                    c2.sethFin(horaFin);
                    } else{
                        JOptionPane.showMessageDialog(null, "La hora de termino debes estar entre las 8 am y 8 pm");
                        break;
                    }  
                    
                    if(hAux < h1Aux){
                        c2.setEstado(c3.getEstado());
                            modelo.updateCurso(c2);
                    } else{
                        JOptionPane.showMessageDialog(null, "La hora de termino no puede ser menor a la de inicio.");
                        break;
                    }
                    
                    m1.closeConexion();
                } else {  ///Termina el de actualizar ***************************************
                    String nombreCurso = this.view.getNombre();
                    if(nombreCurso.length() == 0){
                        JOptionPane.showMessageDialog(null, "El campo NOMBRE DEL CURSO no debe estar vacío");
                        break;
                    } else if(nombreCurso.length() > 35){
                        JOptionPane.showMessageDialog(null, "El NOMBRE DEL CURSO excede los 35 carácteres permitidos");
                        break;
                    }
                
                    String fechaIn = null;
                    if(this.view.fechaInicio.getDate() == null){
                        JOptionPane.showMessageDialog(null, "Debe seleccionar la fecha de Inicio.");
                        break;
                    } else {
                        String dia = this.view.fechaInicio.getDate().toString().substring(8,10);
                        String mes = (this.view.fechaInicio.getDate().getMonth() + 1) + "" ;
                        int anio = this.view.fechaInicio.getDate().getYear() + 1900;
                        if(this.view.fechaFin.getDate().getMonth() + 1 < 10)
                            mes = "0" + mes;
                        fechaIn = anio + "-" + mes + "-" + dia;
                    }

                    String fechaFin = null;
                    if(this.view.fechaFin.getDate() == null){
                        JOptionPane.showMessageDialog(null, "Debe seleccionar la fecha de Término.");
                        break;
                    } else {
                        String dia = this.view.fechaFin.getDate().toString().substring(8,10);
                        String mes = (this.view.fechaFin.getDate().getMonth() + 1) + "" ;
                        int anio = this.view.fechaFin.getDate().getYear() + 1900;
                        if(this.view.fechaFin.getDate().getMonth() + 1 < 10)
                            mes = "0" + mes;
                        fechaFin = anio + "-" + mes + "-" + dia;
                    }
                    c = new Curso();
                    String profRes = this.view.getProfesorResponsable();
                    Profesor p = new Profesor();
                    p.setId(modelo.getIds().get(this.view.getIndiceCurso()));
                    ModeloVistaAltaProfesor m = new ModeloVistaAltaProfesor("CursosActualizacion");
                    Profesor p2 = m.selectProfesorNombre(p);

                    String aula = this.view.getAulaCombo();
                    
                    String hoIn = this.view.getHoraInicio();
                    String h = "";
                    String mi = "";
                    String form = "";
                    for(int x = 0; x < hoIn.length(); x++){
                        if(x == 0 || x == 1){
                            char letra = hoIn.charAt(x);
                            h += letra;
                        }
                        if(x == 3 || x ==4){
                            char le = hoIn.charAt(x);
                            mi += le;
                        }
                        if(x == 6 || x ==7){
                            char l = hoIn.charAt(x);
                            form += l;
                        }
                    }
                    if(form.equals("PM")){
                        switch(h){
                            case "01":
                                h = "13";
                            break;
                            
                            case "02":
                                h = "14";
                            break;
                            
                            case "03":
                                h = "15";
                            break;
                            
                            case "04":
                                h = "16";
                            break;
                            
                            case "05":
                                h = "17";
                            break;
                            
                            case "06":
                                h = "18";
                            break;
                            
                            case "07":
                                h = "19";
                            break;
                            
                            case "08":
                                h = "20";
                            break;
                            
                            case "09":
                                h = "21";
                            break;
                            
                            case "10":
                                h = "22";
                            break;
                            
                            case "11":
                                h = "23";
                            break;
                            
                            case "12":
                                h = "24";
                            break;
                        }
                    }
                    int hAux = Integer.parseInt(h);
                    String horaInicio = h + ":" + mi + ":" + "00";
                    if(hAux >= 7 && hAux <= 19){
                    c.sethInicio(horaInicio);
                    } else{
                        JOptionPane.showMessageDialog(null, "La hora de inicio debes estar entre las 7 am y 7 pm");
                        break;
                    }   
                    
                    String hoFin = this.view.getHoraFin();
                    String h1 = "";
                    String mi1 = "";
                    String form1 = "";
                    for(int x = 0; x < hoFin.length(); x++){
                        if(x == 0 || x == 1){
                            char letra = hoFin.charAt(x);
                            h1 += letra;
                        }
                        if(x == 3 || x ==4){
                            char le = hoFin.charAt(x);
                            mi1 += le;
                        }
                        if(x == 6 || x ==7){
                            char l = hoFin.charAt(x);
                            form1 += l;
                        }
                    }
                    if(form1.equals("PM")){
                        switch(h1){
                            case "01":
                                h1 = "13";
                            break;
                            
                            case "02":
                                h1 = "14";
                            break;
                            
                            case "03":
                                h1 = "15";
                            break;
                            
                            case "04":
                                h1 = "16";
                            break;
                            
                            case "05":
                                h1 = "17";
                            break;
                            
                            case "06":
                                h1 = "18";
                            break;
                            
                            case "07":
                                h1 = "19";
                            break;
                            
                            case "08":
                                h1 = "20";
                            break;
                            
                            case "09":
                                h1 = "21";
                            break;
                            
                            case "10":
                                h1 = "22";
                            break;
                            
                            case "11":
                                h1 = "23";
                            break;
                            
                            case "12":
                                h1 = "24";
                            break;
                        }
                    }
                    int h1Aux = Integer.parseInt(h1);
                    String horaFin = h1 + ":" + mi1 + ":" + "00";
                    if(h1Aux >= 8 && h1Aux <= 20){
                    c.sethFin(horaFin);
                    } else{
                        JOptionPane.showMessageDialog(null, "La hora de termino debes estar entre las 8 am y 8 pm");
                        break;
                    }
                    
                    if(hAux < h1Aux){
                        c = new Curso(nombreCurso, fechaIn, fechaFin, p2.getId(), aula, horaInicio, horaFin);
                            modelo.insertCurso(c);
                    
                        } else{
                            JOptionPane.showMessageDialog(null, "La hora de termino no puede ser menor a la de inicio.");
                        break;
                        }
                    m.closeConexion();
                }
                limpia();
                cargarTabla();
            break;
            
            case "Hora Fin:":
                comprobar();
                
                clo = new Clock(view, "Hora Fin:", modelo);
                clo.setBounds(603, 328, 90, 30);
                clo.setVisible(true);
            break;
            
            case "Hora Inicio:":
                comprobar();
                
                clo = new Clock(view, "Hora Inicio:", modelo);
                clo.setBounds(603, 293, 90, 30);
                clo.setVisible(true);
                
            break;
            
            case "Eliminar":
                comprobar();
                pintar();
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
            
            case "Fecha Termino:":
                comprobar();
                CalendarPane cP = new CalendarPane(view);
                cP.crearUnCale();
               
                ControladorCalendario cC = new ControladorCalendario(cP);
                cP.conectaControlador(cC);
            break;
            
            case "Fecha Inicio:":
                comprobar();
                CalendarPane a = new CalendarPane(view);
                a.crearUnCalendario();
               
                ControladorCalendario a1 = new ControladorCalendario(a);
                a.conectaControlador(a1);
            break;
            
            case "Regresar":
                comprobar();
                view.dispose();
                prin.setVisible(true);
            break;
        }
    }
    
    private void limpia(){
        this.view.setNombre("");
        this.view.setProfesorResponsable("");
        this.view.setAulaCombo("01");
        this.view.setHoraInicio("");
        this.view.setHoraFin("");
        this.view.fechaInicio.setDate(null);
        this.view.fechaFin.setDate(null);
        this.view.setProfRespon(0);
        
        String [] aulas = new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13" };
        this.view.setModeloAulas(aulas);
    }
    
    private void comprobar(){
        if( clo != null && clo.isShowing()){
            clo.dispose();
        }
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
            fila.add(p2.getNombre() + " " + p2.getApPaterno());
            fila.add(a.getAula());
            fila.add(a.gethInicio());
            fila.add(a.gethFin());
            fila.add(a.getEstado());
            //Añadir el vector a la tabla de la clase View
            this.view.dtm.addRow(fila);
            }
    }

    
    //Modificado
    @Override
    public void mouseClicked(MouseEvent me) {
        //To change body of generated methods, choose Tools | Templates.
        int filaPulsada = this.view.tabla.getSelectedRow();
        
        if(filaPulsada>=0) {
            Curso l = new Curso();
            String nom = (String)this.view.dtm.getValueAt(filaPulsada, 0);
            l.setNombre(nom);
            Curso l2 = this.modelo.selectCursoNom(l);
            
            if(l2!=null) {
                this.view.setNombre(l2.getNombre());
                
                int dia = Integer.parseInt(l2.getfInicio().substring(8, 10));
                int mes = Integer.parseInt(l2.getfInicio().substring(5, 7));
                int anio = Integer.parseInt(l2.getfInicio().substring(0, 4));
                Date aux = new Date();
                aux.setYear(anio - 1900);
                aux.setMonth(mes - 1);
                aux.setDate(dia);
                this.view.fechaInicio.setDate(aux);
                

                dia = Integer.parseInt(l2.getfFin().substring(8, 10));
                mes = Integer.parseInt(l2.getfFin().substring(5, 7));
                anio = Integer.parseInt(l2.getfFin().substring(0, 4));
                aux = new Date();
                aux.setYear(anio - 1900);
                aux.setMonth(mes - 1);
                aux.setDate(dia);
                this.view.fechaFin.setDate(aux);
                
                Profesor p = new Profesor();
                p.setId(l2.getIdProf());
                Profesor p2 = modelo.selectProfesor(p);
                this.view.setProfesorResponsable(p2.getNombre() + " " + p2.getApPaterno() + " " + p2.getApMaterno());
                this.view.setAulaCombo(l2.getAula());
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