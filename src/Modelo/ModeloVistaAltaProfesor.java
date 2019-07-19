/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class ModeloVistaAltaProfesor {
    private String  host     = "localhost";
    private String  usuario     = "postgres";
    private String  clave       = "1rv1ng4rc14";
    private int     puerto      = 5432;
    private String  servidor    = "";
    private String  baseDatos = "CusosActualizacion";
    private static Connection conexion  = null;
    private List<String> nombres;
    private List<Integer> id;

    public ModeloVistaAltaProfesor(String baseDatos){
        this.baseDatos = baseDatos;
        ConexionBd();
    }
    
    protected void ConexionBd(){
        this.servidor="jdbc:postgresql://"+host+":"+ puerto+"/"+baseDatos;
 
        //Registrar el driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR AL REGISTRAR EL DRIVER");
            System.exit(0); //parar la ejecución
        }
 
        //Establecer la conexión con el servidor
        try {
            conexion = DriverManager.getConnection(this.servidor,
                        this.usuario, this.clave);
        } catch (SQLException e) {
            System.err.println("ERROR AL CONECTAR CON EL SERVIDOR");
            System.exit(0); //parar la ejecución
        }
        //System.out.println("Conectado a "+baseDatos);
    }
    
    private Connection getConexion() {
        return conexion;
    }
   
    public boolean insertProfesor(Profesor a){
        PreparedStatement ps;
        String sqlInsertProfesor = "";
        try {
            sqlInsertProfesor = "insert into profesor(nombre, ap_paterno, ap_materno, cedula, nivel_max,"
                + "carrera, area, correo) values('" + a.getNombre() + "', '" + a.getApPaterno() +"', '" + a.getApMaterno()
                    + "', " + a.getCedula() + ", '" + a.getNivelMax() +"', '" + a.getCarrera() + "', '" + a.getArea()
                    + "', '" + a.getCorreo() + "')";
            ps = getConexion().prepareStatement(sqlInsertProfesor);
            ps.executeUpdate();
            return true;
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCIÓN");
        }
        return false;
    }
    
    public boolean deleteProfesor(Profesor l){
        PreparedStatement ps;
        String sqlDeleteProfesor = "delete from profesor where id_profesor = " + l.getId();
        try {
            ps = getConexion().prepareStatement(sqlDeleteProfesor);
            ps.executeUpdate();
            return true;
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, "ERROR EN EL BORRADO \n El profesor que intenta borrar esta inscrito en algún curso");
        }
        return false;
    }
    
    public boolean updateProfesor(Profesor l){
        PreparedStatement ps;
        String sqlUpdateLibro = "update profesor set nombre = '" + l.getNombre() + "', ap_paterno = '" + l.getApPaterno() +
                "', ap_materno = '" + l.getApMaterno() + "', cedula = " + l.getCedula() + ", nivel_max = '" + l.getNivelMax() + 
                "', carrera = '" + l.getCarrera() + "', area= '" + l.getArea() + "', correo = '" + l.getCorreo() + 
                "' where id_profesor = " + l.getId();
        try {
            ps = getConexion().prepareStatement(sqlUpdateLibro);
            ps.executeUpdate();
            return true;
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, "ERROR EN LA MODIFICACIÓN");
        }
        return false;
    }
    
    public Profesor selectProfesor(Profesor e){
        PreparedStatement ps;
        ResultSet rs;
        Profesor profesorEncontrado = null;
        String sqlSelectProfesor = "select * from profesor where id_profesor = " + e.getId();
        try {
            ps = getConexion().prepareStatement(sqlSelectProfesor);
            rs = ps.executeQuery();
            if(rs.next()) {
                profesorEncontrado = new Profesor(rs.getInt(1), rs.getString(2),rs.getString(3), 
                        rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
            }
        }
        catch(SQLException exception)
        {
            //System.err.println("Error al cargar una autobus por matricula "+exception);
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR UN PROFESOR");
        }
        return profesorEncontrado;
    }
    
    public Profesor selectProfesorNombre(Profesor e){
            PreparedStatement ps;
            String sqlSelectProfesor="select id_profesor, nombre, ap_paterno, ap_materno, cedula, nivel_max, carrera, area, correo from public.profesor where nombre = ?;";
            
            ResultSet rs;
            Profesor cursoEncontrado=null;
            try
            {
                ps = getConexion().prepareStatement(sqlSelectProfesor);
                ps.setString(1, e.getNombre());
                rs = ps.executeQuery();
                if(rs.next())
                {
                    cursoEncontrado = new Profesor(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
                }
            }
            catch(SQLException exception)
            {
                System.err.println("Error al cargar un curso por nombre "+exception);
                //JOptionPane.showMessageDialog(null, "ERROR AL CARGAR UN PROFESOR");
            }
            return cursoEncontrado;
    }
    
    public List<Profesor> listProfesores(){
	PreparedStatement ps;
        //Objeto para recoger los datos devueltos por el procedimiento almacenado
        ResultSet rs;
        //Objeto para recorrer el resultado del procedimiento almacenado y
        //  añadirlo a la tabla definida en la clase View
      
        //Cargar datos de todos los estudiantes
	String consultaSQL = "select id_profesor, nombre, ap_paterno, ap_materno, "
                + "cedula, nivel_max, carrera, area, correo from profesor order by id_profesor;";
	List<Profesor> profesores = new ArrayList<Profesor>();
        try {
            //Preparar la llamada
            ps  = getConexion().prepareStatement(consultaSQL);
                      
            //Ejecutarla y recoger el resultado
            rs  = ps.executeQuery();
			
            //Recorrer el resultado
            while(rs.next()){
		Profesor a = new Profesor();
                //Añadir registro a registro en el vector
		a.setId(rs.getInt("id_profesor"));
                a.setNombre(rs.getString("nombre"));
                a.setApPaterno(rs.getString("ap_paterno"));
                a.setApMaterno(rs.getString("ap_materno"));
                a.setCedula(rs.getInt("cedula"));
                a.setNivelMax(rs.getString("nivel_max"));
                a.setCarrera(rs.getString("carrera"));
                a.setArea(rs.getString("area"));
                a.setCorreo(rs.getString("correo"));
                profesores.add(a);
            }
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS");
        }
	return profesores;
    }
    
    public List<String> cargarCursos(){
        PreparedStatement ps;
        ResultSet rs;
        String comandoSQL = "select id_curso, nombrecurso from curso";
        id = new ArrayList<Integer>();
        nombres = new ArrayList<String>();
        try {
            ps = getConexion().prepareStatement(comandoSQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                id.add(rs.getInt(1));
                nombres.add(rs.getString(2));
            }
            return nombres;
        } catch (SQLException ex) { }
        return null;
    }
    
    public boolean inscribirProfesor(int id_profesor, int id_curso, String nomCurso, String nomProfesor) {
        PreparedStatement ps;
        ResultSet rs;
        String sqlInscribirProf = "insert into profesor_curso (id_profesor, id_curso) values (" + id_profesor + 
                ", " + id_curso + ")";
        String sqlValidarInscritos = "select count(*) from profesor_curso where id_curso = " + id_curso;
        String sqlActualizarStatus = "update curso set estatus = true where id_curso = " + id_curso;
        int inscritos = 0;
        try {
            ps = getConexion().prepareStatement(sqlValidarInscritos);
            rs = ps.executeQuery();
            while (rs.next()) {
                inscritos = rs.getInt(1);
                if(rs.getInt(1) >= 30){
                    JOptionPane.showMessageDialog(null, "El cupo esta completo\nImposible inscribir a otro profesor");
                    return false;
                } else {
                    try {
                        ps = getConexion().prepareStatement(sqlInscribirProf);
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, nomProfesor + "\nAgregado correctamente al curso: " + nomCurso);
                        
                        ps = getConexion().prepareStatement(sqlValidarInscritos);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            inscritos = rs.getInt(1);
                            if(rs.getInt(1) == 2){
                                JOptionPane.showMessageDialog(null, "!Curso Autorizado!");
                                ps = getConexion().prepareStatement(sqlActualizarStatus);
                                ps.executeUpdate();
                            }
                        }
                        return true;
                    } catch(SQLException exception) {
                        JOptionPane.showMessageDialog(null, "ERROR EN LA INSCRIPCIÓN");
                    }
                }
            }
        } catch (SQLException ex) { }
        return false;
    }
    
    public void closeConexion(){
        if ( getConexion() != null){
            try {
                getConexion().close();
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, "ERROR AL CERRAR LA BASE DE DATOS");
            }
        }
    }
    
    public List<Integer> getIds(){
        return id;
    }
    
    public List<String> getNombres(){
        return nombres;
    }
}
