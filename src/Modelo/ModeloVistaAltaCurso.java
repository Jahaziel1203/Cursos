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
import javax.swing.JOptionPane;
/**
 *
 * @author ACER
 */
public class ModeloVistaAltaCurso {
    private String  host     = "localhost";
    private String  usuario     = "postgres";
    private String  clave       = "1rv1ng4rc14";
    private int     puerto      = 5432;
    private String  servidor    = "";
    private String  baseDatos;
    private static Connection conexion  = null;
    private List<String> nombres;
    private List<Integer> id;
    
    public ModeloVistaAltaCurso(String baseDatos){
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
            conexion = DriverManager.getConnection(this.servidor, this.usuario, this.clave);
        } catch (SQLException e) {
            System.err.println("ERROR AL CONECTAR CON EL SERVIDOR");
            System.exit(0); //parar la ejecución
        }
        //System.out.println("Conectado a "+baseDatos);
    }
    
    private Connection getConexion() {
        return conexion;
    }
    
    public boolean insertCurso(Curso a){
            PreparedStatement ps;
            String sqlInsertCurso = ""; //Aqui nada mas pones en que esquema está la tabla profesor
            try
            {
                sqlInsertCurso = "insert into curso(nombrecurso, fechainicio, fechafin, id_profrespon," 
                        + "aula, horainicio, horafin) values(' " + a.getNombre() + "', '" + a.getfInicio() 
                        + "', '" + a.getfFin() + "', " + a.getIdProf() + ", '" + a.getAula() + "', ' " + a.gethInicio() 
                        + "', ' " + a.gethFin() + "');";
                ps = getConexion().prepareStatement(sqlInsertCurso);
                ps.executeUpdate();
                return true;
            }
            catch(SQLException exception)
            {
                System.err.println("ERROR EN LA INSERCIÓN  "+exception);
                //JOptionPane.showMessageDialog(null, "ERROR EN LA INSERCIÓN");
            }
            return false;
    }
    
    public boolean deleteCurso(Curso l){
            PreparedStatement ps;
            String sqlDeleteCurso = "delete from public.curso where nombrecurso = ?;"; //Igual pones el esquema
            try
            {
                ps = getConexion().prepareStatement(sqlDeleteCurso);
                ps.setString(1, l.getNombre());
                ps.executeUpdate();
                return true;
            }
            catch(SQLException exception)
            {
                //System.err.println("ERROR en el BORRADO "+exception);
                JOptionPane.showMessageDialog(null, "ERROR EN EL BORRADO");
            }
            return false;
    }
    
    public boolean updateCurso(Curso a){
            PreparedStatement ps;
            //String sqlUpdalibroteLibro = "update public.curso set nombrecurso= ?, fechainicio =?, fechafin = ?, id_proferespon = ?, duracioncurso = ?, aula = ?, horainicio= ?, horafin =?, estatus=? where id_curso = ?; ";
            String sqlUpdateLibro = "update curso set nombrecurso = '" + a.getNombre() + "', fechainicio = '" + a.getfInicio() + 
                    "', fechafin = '" + a.getfFin() + "', id_profrespon = " + a.getIdProf() + ", aula = '" + a.getAula() +
                    "', horainicio = '" + a.gethInicio() + "', horafin = '" + a.gethFin() + "', estatus = '" + a.getEstado() +
                    "' where id_curso = " + a.getId();
            
            try
            {
                ps = getConexion().prepareStatement(sqlUpdateLibro);
                ps.executeUpdate();
                return true;
            }
            catch(SQLException exception)
            {
                System.err.println("Error en la MODIFICACIÓN "+exception);
                //JOptionPane.showMessageDialog(null, "ERROR EN LA MODIFICACIÓN");
            }
            return false;
    }
    
    public Curso selectCurso(Curso e){
            PreparedStatement ps;
            String sqlSelectProfesor="nombrecurso, fechainicio, fechafin, id_proferespon, duracioncurso, aula, horainicio, horafin, estatus where id_curso = ?;";
            
            ResultSet rs;
            Curso cursoEncontrado=null;
            try
            {
                ps = getConexion().prepareStatement(sqlSelectProfesor);
                ps.setInt(1, e.getId());
                rs = ps.executeQuery();
                if(rs.next())
                {
                    cursoEncontrado = new Curso(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9));
                }
            }
            catch(SQLException exception)
            {
                //System.err.println("Error al cargar una autobus por matricula "+exception);
                JOptionPane.showMessageDialog(null, "ERROR AL CARGAR UN PROFESOR");
            }
            return cursoEncontrado;
    }
    
    public Curso selectCursoNom(Curso e){
            PreparedStatement ps;
            String sqlSelectProfesor="select id_curso, nombrecurso, fechainicio, fechafin, id_profrespon, aula, horainicio, horafin, estatus from public.curso where nombrecurso = ?;";
            
            ResultSet rs;
            Curso cursoEncontrado=null;
            try
            {
                ps = getConexion().prepareStatement(sqlSelectProfesor);
                ps.setString(1, e.getNombre());
                rs = ps.executeQuery();
                if(rs.next())
                {
                    cursoEncontrado = new Curso(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9));
                }
            }
            catch(SQLException exception)
            {
                System.err.println("Error al cargar un curso por nombre "+exception);
                //JOptionPane.showMessageDialog(null, "ERROR AL CARGAR UN PROFESOR");
            }
            return cursoEncontrado;
    }
    
    public Profesor selectProfesor(Profesor e){
            PreparedStatement ps;
            String sqlSelectProfesor="select id_profesor, nombre, ap_paterno, ap_materno, cedula, nivel_max, carrera, area, correo from public.profesor where id_profesor = ?;";
            
            ResultSet rs;
            Profesor cursoEncontrado=null;
            try
            {
                ps = getConexion().prepareStatement(sqlSelectProfesor);
                ps.setInt(1, e.getId());
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
    
    public List<Curso> listCursos(){
	PreparedStatement ps;
        //Objeto para recoger los datos devueltos por el procedimiento almacenado
        ResultSet rs;
        //Objeto para recorrer el resultado del procedimiento almacenado y
        //  añadirlo a la tabla definida en la clase View
      
 
        //Cargar datos de todos los estudiantes
		String consultaSQL = "Select id_curso, nombrecurso, fechainicio, fechafin, id_profrespon, aula, horainicio, horafin, estatus from public.curso;";
		
		List<Curso> cursos = new ArrayList<Curso>();
        try {
            //Preparar la llamada
            ps  = getConexion().prepareStatement(consultaSQL);
                      
            //Ejecutarla y recoger el resultado
            rs  = ps.executeQuery();
			
            //Recorrer el resultado
            while(rs.next()){
		Curso a = new Curso();
                //Añadir registro a registro en el vector
		a.setId(rs.getInt("id_curso"));
                a.setNombre(rs.getString("nombrecurso"));
                a.setfInicio(rs.getString("fechainicio"));
                a.setfFin(rs.getString("fechafin"));
                a.setIdProf(rs.getInt("id_profrespon"));
                a.setAula(rs.getString("aula"));
                a.sethInicio(rs.getString("horainicio"));
                a.sethFin(rs.getString("horafin"));
                a.setEstado(rs.getBoolean("estatus"));
                cursos.add(a);
            }
 
        } catch (SQLException exception) {
            System.err.println("Error al CARGAR DATOS " + exception);
            //JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS");
        }
		return cursos;
    }
    
    public List<String> cargarNombres(){
        PreparedStatement ps;
        ResultSet rs;
        String comandoSQL = "select id_profesor, nombre from profesor";
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
    
    public void closeConexion(){
		if ( getConexion() != null){
			try {
				getConexion().close();
			} catch(SQLException e){
				//System.err.println("Error al cerrar la bd "+ e);
                                JOptionPane.showMessageDialog(null, "ERROR AL CERRAR LA BASE DE DATOS");
			}
		}
    }
}
