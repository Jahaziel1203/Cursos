/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class ModeloConsulta {
    private String  host     = "localhost";
    private String  usuario     = "postgres";
    private String  clave       = "1rv1ng4rc14";
    private int     puerto      = 5432;
    private String  servidor    = "";
    private String  baseDatos = "CusosActualizacion";
    private static Connection conexion  = null;
    
    public ModeloConsulta(String baseDatos){
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
    
    public Profesor selectProfesor(CursoProfesor e){
        PreparedStatement ps;
        ResultSet rs;
        Profesor profesorEncontrado = null;
        String sqlSelectProfesor = "select * from profesor where id_profesor = " + e.getIdP();
        try {
            ps = getConexion().prepareStatement(sqlSelectProfesor);
            rs = ps.executeQuery();
            if(rs.next()) {
                profesorEncontrado = new Profesor(rs.getInt(1), rs.getString(2),rs.getString(3), 
                        rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
        }
        catch(SQLException exception)
        {
            //System.err.println("Error al cargar una autobus por matricula "+exception);
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR UN PROFESOR" + exception);
        }
        return profesorEncontrado;
    }
    
    public List<CursoProfesor> listInscritos(CursoProfesor c){
	PreparedStatement ps;
        //Objeto para recoger los datos devueltos por el procedimiento almacenado
        ResultSet rs;
        //Objeto para recorrer el resultado del procedimiento almacenado y
        //  añadirlo a la tabla definida en la clase View
      
        //Cargar datos de todos los estudiantes
	String consultaSQL = "select id_profesor, id_curso from profesor_curso order by id_profesor;";
	List<CursoProfesor> profesores = new ArrayList<CursoProfesor>();
        try {
            //Preparar la llamada
            ps  = getConexion().prepareStatement(consultaSQL);
                      
            //Ejecutarla y recoger el resultado
            rs  = ps.executeQuery();
			
            //Recorrer el resultado
            while(rs.next()){
		CursoProfesor a = new CursoProfesor();
                //Añadir registro a registro en el vector
                a.setIdP(rs.getInt("id_profesor"));
		a.setIdC(rs.getInt("id_curso"));
                if(c.getIdC() == a.getIdC())
                    profesores.add(a);
            }
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS");
        }
	return profesores;
    }
    
    public List<Profesor> listProfesores(Profesor p){
	PreparedStatement ps;
        //Objeto para recoger los datos devueltos por el procedimiento almacenado
        ResultSet rs;
        //Objeto para recorrer el resultado del procedimiento almacenado y
        //  añadirlo a la tabla definida en la clase View
      
        //Cargar datos de todos los estudiantes
	String consultaSQL = "select id_profesor, nombre, ap_paterno, ap_materno, "
                + "cedula, nivel_max, carrera, correo from profesor order by id_profesor;";
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
                a.setCorreo(rs.getString("correo"));
                if(p.getId() == a.getId())
                    profesores.add(a);
            }
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS");
        }
	return profesores;
    }
    
    public Curso selectCurso(Curso e){
        //Objeto para ejecutar los procedimientos almacenados en la base de datos
        PreparedStatement ps;
        //Objeto para recoger los datos devueltos por el procedimiento almacenado
        ResultSet rs;
		
		// Objeto con el cliente encontrado
		Curso editorialEncontrada= null;
		
		
		String sqlConsulta = "select id_curso, nombrecurso, fechainicio, fechafin, id_profrespon, aula, horainicio, horafin, estatus from public.curso where id_curso = ?;";
        try{
            //Preparar la llamada
            ps  = getConexion().prepareStatement(sqlConsulta);
                       
            //Indicar qué información se pasa al procedimiento
            ps.setInt(1, e.getId());
            //Ejecutar el procedimiento
            rs  = ps.executeQuery();
            //Cargar los datos devueltos en los cuadros de texto
            if(rs.next()){
				editorialEncontrada = new Curso(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9));  
            }
			
            //System.out.println(this.view.dtm.getValueAt(filaPulsada, 0));
        }catch (SQLException exception) {
            System.err.println("Error al CARGAR UNA EDITORIAL");
        }
		return editorialEncontrada;
    }
    
    public Profesor selectProfesorNombre(Profesor e){
        //Objeto para ejecutar los procedimientos almacenados en la base de datos
        PreparedStatement ps;
        //Objeto para recoger los datos devueltos por el procedimiento almacenado
        ResultSet rs;
		
		// Objeto con el cliente encontrado
		Profesor editorialEncontrada= null;
		
		
		String sqlConsulta = "select id_profesor, nombre, ap_paterno, ap_materno, cedula, nivel_max, carrera, correo from public.profesor where nombre = ?;";
                
        try{
            //Preparar la llamada
            ps  = getConexion().prepareStatement(sqlConsulta);
                       
            //Indicar qué información se pasa al procedimiento
            ps.setString(1, e.getNombre());
            //Ejecutar el procedimiento
            rs  = ps.executeQuery();
            //Cargar los datos devueltos en los cuadros de texto
            if(rs.next()){
				editorialEncontrada = new Profesor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));  
            }
			
            //System.out.println(this.view.dtm.getValueAt(filaPulsada, 0));
        }catch (SQLException exception) {
            System.err.println("Error al CARGAR UNA EDITORIAL");
        }
		return editorialEncontrada;
    }
    
    public void closeConexion(){
        if (getConexion() != null){
            try {
                getConexion().close();
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, "ERROR AL CERRAR LA BASE DE DATOS");
            }
        }
    }
}
