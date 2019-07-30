/*
 * Clase para realizar la conexion con la base de datos y realizar las diversas operaciones
 * necesarias para la captura de calificaciones
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import vista.VistaCalificaciones;

/**
 *
 * @author ErikHernandez
 * @version 29 de julio de 2019
 */
public class ModeloCalificaciones {
    //Variables relacionadas con la base de datos
    private String  host     = "localhost";
    private String  usuario     = "postgres";
    private String  clave       = "admi";
    private int     puerto      = 5432;
    private String  servidor    = "";
    private String  baseDatos = "CusosActualizacion";
    private static Connection conexion  = null;
    private List<String> nombres;
    private List<String> cursos;

    /**
     * Constructor de la clase ModeloCalificaciones
     * @param baseDatos Como parametro recibe el nombre de la base de datos
     */
    public ModeloCalificaciones(String baseDatos) {
        this.baseDatos = baseDatos;
        ConexionBd();
    }
    
    /**
     * Método conexionBd encargado de realizar la conexion a la base de datos para asi poder realizar
     * las diversas operaciones necesarias para el correcto funcionamiento del sistema.
     */
    protected void ConexionBd(){
        //Url de la base de datos
        this.servidor="jdbc:postgresql://"+host+":"+ puerto+"/"+baseDatos;
 
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR AL REGISTRAR EL DRIVER");
            System.exit(0); //parar la ejecución
        }
 
        try {
            conexion = DriverManager.getConnection(this.servidor,
            this.usuario, this.clave);
        } catch (SQLException e) {
            System.err.println("ERROR AL CONECTAR CON EL SERVIDOR");
            System.exit(0);
        }        
    }
    
    /**
     * Método getConexion necesario para saber si la conexion con la base de datos se 
     * realizo de manera correcta
     * @return objeto con el que se puede verificar si la conexion se realizo correctamente
     */
    private Connection getConexion() {
        return conexion;
    }
    
    /**
     * Método cargarNombres encargado de regresar la lista en la que se guardan los nombres
     * de los profesores guardados en la base de datos, dicha lista es ocupada para
     * cargar los datos en el JComboBox de la vistaCalificaciones.
     * @return Lista con los nombres de los profesores guardados
     */
    public List<String> cargarNombres(){
        PreparedStatement ps;
        ResultSet rs;
        String comandoSQL = "select nombre, ap_paterno, ap_materno from profesor order by nombre, ap_paterno";
        nombres = new ArrayList<String>();
        try {
            ps = getConexion().prepareStatement(comandoSQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                nombres.add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
            return nombres;
        } catch (SQLException ex) { }
        return null;
    }
    
    /**
     * Método cursosProfesor encargado de listar los cursos en los que esta
     * inscrito el profesor seleccionado en el JComboBox de la vistaCalificaiones
     * @param nombre valor que hace referencia al nombre del profesor
     * seleccionado en la vista calificaciones
     * @return lista con los nombres de los cursos en los que esta inscrito el profesor seleccionado
     */
    public List<String> cursosProfesor(String nombre){
        PreparedStatement ps;
        ResultSet rs;
        String comandoSQL = "select c.nombrecurso from curso c, profesor p, profesor_curso pc where " +
            "concat(p.nombre, ' ', p.ap_paterno, ' ', p.ap_materno) = '" + nombre + "' and\n" +
            "c.id_curso = pc.id_curso and p.id_profesor = pc.id_profesor;";
        cursos = new ArrayList<String>();
        try {
            ps = getConexion().prepareStatement(comandoSQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                cursos.add(rs.getString(1));
            }
            return cursos;
        } catch (SQLException ex) { }
        return null;
    }
    
    /**
     * Método guardarCalificacion, este es ocupado para guardar una calificacion la en base de datos
     * para el funcionamiento de este, son necesarios tres parametros
     * @param nombreProfesor nombre del profesor seleccionado en la vistaCalificaiones
     * @param nombreCurso nombre del curso seleccionado en la vistaCalificaciones
     * @param calificacion valor recuperado del campo calificacion (VistaCalificaciones)
     * @return valor que sirve para saber si la insercion fue éxitosa
     */
    public boolean guardarCalificacion(String nombreProfesor, String nombreCurso, int calificacion) {
        PreparedStatement ps;
        ResultSet rs;
        
        //Consulta para obtener los id tanto del profesor como del curso
        String comandoSQL = "SELECT p.id_profesor, c.id_curso FROM curso c, profesor p WHERE c.nombrecurso = '" + nombreCurso
                + "' and concat(p.nombre, ' ', p.ap_paterno, ' ', p.ap_materno) = '" + nombreProfesor + "';";
        
        //Variales que almacenan los id recuperados
        int idProf = 0;
        int idCurso = 0;
        try {
            ps = getConexion().prepareStatement(comandoSQL);
            rs = ps.executeQuery();
            
            if(rs.next()) { //Se asignan los id
                idProf = rs.getInt(1);
                idCurso = rs.getInt(2);
                
                if(idProf != 0 && idCurso != 0) {
                    //Instruccion necesaria para la insercion de la calificacion
                    String comandoIns = "insert into calificaciones(id_profesor, id_curso, calificacion) "
                            + "values(" + idProf + ", " + idCurso + ", " + calificacion + ");";
                    
                    PreparedStatement ps2;
                    ps2 = getConexion().prepareStatement(comandoIns);
                    ps2.executeUpdate();
                    //Mensaje para saber el estado de la insercion
                    JOptionPane.showMessageDialog(null, "Calificación capturada correctamente");
                }   
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }
    
    /**
     * Método listarCalificaciones, encargado de listar las calificaciones
     * que tiene un profesor dado el nombre del profesor y el nombre del curso,
     * estos valores se obtienen de los JComboBox contenidos en la listaCalificaciones
     * @param nombreProf nombre del profesor a consultar
     * @param nombreCurso nombre del curso a consultar
     * @return variable que sirve para saber si la consulta fue éxitosa
     */
    public boolean listarCalificaciones(String nombreProf, String nombreCurso) {
        PreparedStatement ps;
        ResultSet rs;
        
        //Consulta para obtener las calificaciones de un profesor en un curso
        String comandoSQL = "select ca.calificacion from curso c, profesor p, calificaciones ca where " +
                        "concat(p.nombre, ' ', p.ap_paterno, ' ', p.ap_materno) = '" + nombreProf + "' and" +
                        " c.nombrecurso = '" + nombreCurso + "' and ca.id_profesor = p.id_profesor and" +
                        " c.id_curso = ca.id_curso;";
        try {
            ps = getConexion().prepareStatement(comandoSQL);
            rs = ps.executeQuery();
            
            //Variable en la que se van a almacenar las calificaciones obtenidas
            String mensaje = "";
            //Variable para enumerar las calificaciones obtenidas en la consulta
            int numeroCalificacion = 1;
            while (rs.next()) { 
                //Concatenacion de los valores obtenidos
                mensaje += "Calificación " + numeroCalificacion++ + ".- " + rs.getInt(1) + "\n";
            }
            if(mensaje == "")
                //Se evalua que se hayan obtenido calificaciones
                JOptionPane.showMessageDialog(null, "No hay calificaciones registradas para ese curso");
            else
                //Se muestra el mensaje que contiene las calificaciones del curso
                JOptionPane.showMessageDialog(null, mensaje);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }
    
    
    /**
     * Método diseñado para obtener el promedio que tiene un profesor en un curso, este puede
     * tener unas variantes dependiendo de la situacion en la que este el sistema
     * 1.- El curso concluyo y ya se capturo alguna calificacion, por lo tanto se puede dar un promedio
     * 2.- El curso aun no concluye y ya existe al menos una calificacion capturada, entonces
     * se puede dar una calificacion parcial
     * 3.- El curso aun no concluye y tampoco hay calificaciones capturadas
     * @param nombreProf ayuda en la busqueda de cursos por el nombre del profesor
     * @param nombreCurso ayuda a buscar el curso a partir de su nombre
     * @return 
     */
    public boolean consultarPromedio(String nombreProf, String nombreCurso) {
        PreparedStatement ps;
        ResultSet rs;
        PreparedStatement ps2;
        ResultSet rs2;
        
        //Consulta para obtener la fecha de termino del curso
        String consultafecha = "select fechafin from curso where nombrecurso = '" + nombreCurso + "';";
        
        //Consulta para obtener las calificaciones de un profesor en un curso
        String comandoSQL = "select ca.calificacion from curso c, profesor p, calificaciones ca where " +
                        "concat(p.nombre, ' ', p.ap_paterno, ' ', p.ap_materno) = '" + nombreProf + "' and" +
                        " c.nombrecurso = '" + nombreCurso + "' and ca.id_profesor = p.id_profesor and" +
                        " c.id_curso = ca.id_curso;";
        try {
            ps2 = getConexion().prepareStatement(consultafecha);
            rs2 = ps2.executeQuery();
            ps = getConexion().prepareStatement(comandoSQL);
            rs = ps.executeQuery();
            
            //Variable en la que se van a almacenar las calificaciones obtenidas
            int calificacion = 0;
            //Variable para enumerar las calificaciones obtenidas en la consulta
            int numeroCalificacion = 0;
            while (rs.next()) { 
                //Concatenacion de los valores obtenidos
                calificacion += rs.getInt(1);
                numeroCalificacion++;
            }
            
            Date fin = null;
            //Se obtiene la fecha de fin del curso para poder saber si ya se 
            //puede sar un promedio final o no
            if(rs2.next()) {
                fin = rs2.getDate(1);
            }
            
            //Variable para obtener el promedio
            int promedio = 0;
            if(numeroCalificacion > 0)
                promedio = calificacion/numeroCalificacion;
            
            //Se compara la fecha de termino del cursp y la fecha de hoy
            Date hoy = new Date();
            if(fin.compareTo(hoy) < 0) {
                if(promedio >= 70)
                    JOptionPane.showMessageDialog(null, "Su promedio es: " + promedio + "\n"
                    + "Es acreedor a su constancia de asistencia");
                else
                    JOptionPane.showMessageDialog(null, "Su promedio es: " + promedio + "\n"
                    + "No acreedor a su constancia de asistencia");
            } else {
                JOptionPane.showMessageDialog(null, "Su curso aún no concluye\n"
                        + "Su promedio hasta el momento es: " + promedio);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }
    
    
    /**
     * Método para listar el nombre de los cursos y sus respectivos promedios
     * en base al nombre del profesor seleccionado en la vistaCalificaciones
     * @param view vistaCalificaciones necesaria para obtenerl el nombre del profesor
     * seleccionado
     * @return lista de arreglos de String que contiene el nombre de cada curso y su promedio
     */
    public List<String[]> listPromedios(VistaCalificaciones view){
        //Lista que contendra los datos(nombre, promedio) de cada curso
	List<String[]> detalle = new ArrayList<String[]>();
        
        PreparedStatement ps;
        ResultSet rs;
        PreparedStatement ps2;
        ResultSet rs2;
        
        //Consulta para obtener los cursos en los que esta inscrito el profesor seleccionado
        String consultaCursosIns = "select c.nombrecurso, c.fechafin from profesor p, curso c, profesor_curso pc where " +
                        "concat(p.nombre, ' ', p.ap_paterno, ' ', p.ap_materno) = '" + view.getProfSeleccionado() + "' " +
                        "and c.id_curso = pc.id_curso and p.id_profesor = pc.id_profesor;";
        
        try {
            ps = getConexion().prepareStatement(consultaCursosIns);
            rs = ps.executeQuery();
            
            while (rs.next()) { 
                //Consulta para obtener las calificaciones de un profesor en un curso
                String comandoSQL = "select ca.calificacion from curso c, profesor p, calificaciones ca where " +
                    "concat(p.nombre, ' ', p.ap_paterno, ' ', p.ap_materno) = '" + view.getProfSeleccionado() + "' and" +
                    " c.nombrecurso = '" + rs.getString(1) + "' and ca.id_profesor = p.id_profesor and" +
                    " c.id_curso = ca.id_curso;";
                
                ps2 = getConexion().prepareStatement(comandoSQL);
                rs2 = ps2.executeQuery();
                
                //Variable en la que se van a almacenar las calificaciones obtenidas
                int calificacion = 0;
                //Variable para enumerar las calificaciones obtenidas en la consulta
                int numeroCalificacion = 0;
                
                while(rs2.next()) {
                    //Suma de las calificaciones obtenidas
                    calificacion += rs2.getInt(1);
                    numeroCalificacion++;
                }
                
                Date fin = rs.getDate(2);
                
                int promedio = 0;
                if(numeroCalificacion != 0)
                //Variable para obtener el promedio
                promedio = calificacion/numeroCalificacion;

                //Se compara la fecha de termino del curso y la fecha de hoy
                Date hoy = new Date();
                
                String[] aux = new String[2];
                if(fin.compareTo(hoy) < 0) {
                    aux[0] = rs.getString(1);
                    aux[1] = promedio + "";                    
                } else {
                    aux[0] = rs.getString(1);
                    aux[1] = "Pendiente";   
                }
                detalle.add(aux);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
        return detalle;
   }
}