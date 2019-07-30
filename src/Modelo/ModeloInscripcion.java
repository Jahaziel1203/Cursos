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
import vista.VistaInscripcionProfesor;

/**
 *
 * @author ErikHernandez
 */
public class ModeloInscripcion {
    private String  host     = "localhost";
    private String  usuario     = "postgres";
    private String  clave       = "admi";
    private int     puerto      = 5432;
    private String  servidor    = "";
    private String  baseDatos;
    private static Connection conexion  = null;
    private List<String> nombres;
    private List<String> cursos;
    
    public ModeloInscripcion(String baseDatos){
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
    }
    
    private Connection getConexion() {
        return conexion;
    }
    
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
    
    public List<String> cargarCursos(){
        PreparedStatement ps;
        ResultSet rs;
        String comandoSQL = "select nombrecurso from curso order by nombrecurso";
        nombres = new ArrayList<String>();
        try {
            ps = getConexion().prepareStatement(comandoSQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                nombres.add(rs.getString(1));
            }
            return nombres;
        } catch (SQLException ex) { }
        return null;
    }
    
    public boolean inscribir(String nomCurso, String nomProf){
        PreparedStatement ps;
        ResultSet rs;
        String comandoSQL = "SELECT p.id_profesor, c.id_curso FROM curso c, profesor p WHERE c.nombrecurso = '" + nomCurso
                + "' and concat(p.nombre, ' ', p.ap_paterno, ' ', p.ap_materno) = '" + nomProf + "';";
        
        int idProf = 0;
        int idCurso = 0;
        try {
            ps = getConexion().prepareStatement(comandoSQL);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                idProf = rs.getInt(1);
                idCurso = rs.getInt(2);
            }
            
            PreparedStatement ps2;
            ResultSet rs2;
            String sqlValidarInscritos = "select count(*) from profesor_curso where id_curso = " + idCurso;
            String sqlActualizarStatus = "update curso set estatus = true where id_curso = " + idCurso;
            int inscritos = 0;
                
            ps2 = getConexion().prepareStatement(sqlValidarInscritos);
            rs2 = ps2.executeQuery();
            while (rs2.next()) {
                inscritos = rs2.getInt(1);
                if(rs2.getInt(1) >= 30){
                    JOptionPane.showMessageDialog(null, "El cupo esta completo\nImposible inscribir a otro profesor");
                    return false;
                } else {
                    try {
                        
                        if(idProf != 0 && idCurso != 0) {
                            String comandoIns = "insert into profesor_curso(id_profesor, id_curso) values(" + idProf + ", " + idCurso + ");";
                            PreparedStatement ps3;

                            ps3 = getConexion().prepareStatement(comandoIns);
                            ps3.executeUpdate();

                            JOptionPane.showMessageDialog(null, nomProf + "\nAgregado correctamente al curso: " + nomCurso);
                        }   
                        
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
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la inscripcion", "¡Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public boolean borrarCursosCombo(VistaInscripcionProfesor view){
        PreparedStatement ps;
        ResultSet rs;
        
        String prof = view.getProfSeleccionado();
        
        //Consulta para eliminar los cursos de los cuales es tutor un profesor
        String comandoSQL = "select c.nombrecurso from curso c, profesor p where c.id_profrespon = p.id_profesor \n" +
        "and concat(p.nombre, ' ', p.ap_paterno, ' ', p.ap_materno) = '" + prof + "';";
        try {
            ps = getConexion().prepareStatement(comandoSQL);
            rs = ps.executeQuery();
            while(rs.next()) {
                view.setCursoEliminar(rs.getString(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la inscripcion", "¡Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //Consulta para elimianr los cursos en los cuales esta inscrito
        comandoSQL = "select c.nombrecurso, c.fechainicio, c.fechafin, c.horainicio, c.horafin from profesor p, "
                + "curso c, profesor_curso pc where concat(p.nombre, ' ', p.ap_paterno, ' ', p.ap_materno) = '" 
                + prof + "' and c.id_curso = pc.id_curso and p.id_profesor = pc.id_profesor;";
        try {
            ps = getConexion().prepareStatement(comandoSQL);
            rs = ps.executeQuery();
            while(rs.next()) {
                view.setCursoEliminar(rs.getString(1));
                
                PreparedStatement ps2;
                ResultSet rs2;
                
                //Comando para eliminar los cursos que se solapan con los cursos en los que esta inscrito un profesor
                String comandoSQL2 = "SELECT nombrecurso FROM curso WHERE ((fechainicio BETWEEN '" + rs.getString(2).replace("-", "") +
                        "' AND '" + rs.getString(3).replace("-", "") + "') or (fechafin BETWEEN '" + rs.getString(2).replace("-", "") + 
                        "' AND '" + rs.getString(3).replace("-", "") + "')) and ((horainicio BETWEEN '" + rs.getString(4) + 
                        "' AND '" + rs.getString(5) + "') or (horafin BETWEEN '" + rs.getString(4) +
                        "' AND '" + rs.getString(5) + "'));";
                
                ps2 = getConexion().prepareStatement(comandoSQL2);
                rs2 = ps2.executeQuery();
                
                while(rs2.next())
                    view.setCursoEliminar(rs2.getString(1));
                
                comandoSQL2 = "SELECT nombrecurso FROM curso WHERE (('" + rs.getString(2).replace("-", "") + "' BETWEEN fechainicio "
                        + "AND fechafin) or('" + rs.getString(3).replace("-", "") + "' BETWEEN fechainicio AND fechafin)) and (\n" +
                "('" + rs.getString(4) + "' BETWEEN horainicio AND horafin) or ('" + rs.getString(5) + "' BETWEEN horainicio AND horafin));";
                
                ps2 = getConexion().prepareStatement(comandoSQL2);
                rs2 = ps2.executeQuery();
                
                while(rs2.next())
                    view.setCursoEliminar(rs2.getString(1));
            }
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
    
    public boolean borrarProfesoresCombo (VistaInscripcionProfesor view) {
        PreparedStatement ps;
        ResultSet rs;
        
        String curso = view.getCursoSeleccionado();
        
        //Consulta para eliminar al responsable del curso
        String comandoSQL = "select p.nombre, p.ap_paterno, p.ap_materno from profesor p, curso c where \n" +
        "c.nombrecurso = '" + curso + "' and c.id_profrespon = p.id_profesor;";
        try {
            ps = getConexion().prepareStatement(comandoSQL);
            rs = ps.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                view.setProfEliminar(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la inscripcion", "¡Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public List<Profesor> listProfesor(VistaInscripcionProfesor view){
	PreparedStatement ps;
        ResultSet rs;
        
        //Consulta para obtener los datos del profesor releccionado
        String consultaSQL = "select id_profesor, nombre, ap_paterno, ap_materno, "
                + "cedula, nivel_max, carrera, correo from profesor p where concat(p.nombre, ' ', "
                + "p.ap_paterno, ' ', p.ap_materno) = '" + view.getProfSeleccionado() + "';";
        
        List<Profesor> profesores = new ArrayList<Profesor>();
        try {
            ps  = getConexion().prepareStatement(consultaSQL);
            rs  = ps.executeQuery();
			
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
                profesores.add(a);
            }
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS" + exception);
        }
	return profesores;
    }
    
    public List<Curso> listCursos(VistaInscripcionProfesor view){
	PreparedStatement ps;
        ResultSet rs;
        
        //Consulta para obtener los datos del profesor releccionado
        String consultaSQL = "select c.id_curso, c.nombrecurso, c.fechainicio, c.fechafin, c.id_profrespon,"
                + "c.aula, c.horainicio, c.horafin, c.estatus  from profesor p, curso c, profesor_curso pc where \n" +
                "concat(p.nombre, ' ', p.ap_paterno, ' ', p.ap_materno) = '" + view.getProfSeleccionado() + "' \n" +
                "and c.id_curso = pc.id_curso and p.id_profesor = pc.id_profesor;";
        
        List<Curso> cursos = new ArrayList<Curso>();
        try {
            ps  = getConexion().prepareStatement(consultaSQL);
            rs  = ps.executeQuery();
			
            while(rs.next()){
		Curso a = new Curso();
                //Añadir registro a registro en el vector
		a.setId(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setfInicio(rs.getString(3));
                a.setfFin(rs.getString(4));
                a.setIdProf(rs.getInt(5));
                a.setAula(rs.getString(6));
                a.sethInicio(rs.getString(7).substring(0, 5));
                a.sethFin(rs.getString(8).substring(0, 5));
                a.setEstado(rs.getBoolean(9));
                cursos.add(a);
            }
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS" + exception);
        }
	return cursos;
    }
    
    public String buscarNombreResponsable(String nombreCurso) {
        PreparedStatement ps;
        ResultSet rs;
        
        //Consulta para eliminar los cursos de los cuales es tutor un profesor
        String comandoSQL = "select p.nombre, p.ap_paterno from curso c, profesor p where c.id_profrespon = "
                + "p.id_profesor and c.nombrecurso = '" + nombreCurso + "';";
        try {
            ps = getConexion().prepareStatement(comandoSQL);
            rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getString(1) + " " + rs.getString(2);
            }
        } catch (SQLException ex) {
            return "";
        }
        return "";
    }
    
    public boolean deleteInscrito(String curso, String nombre){
        PreparedStatement ps;
        ResultSet rs;
        String sqlDeleteInscrito = "SELECT p.id_profesor, c.id_curso FROM curso c, profesor p WHERE "
                + "c.nombrecurso = '" + curso + "' and concat(p.nombre, ' ', p.ap_paterno, ' ', p.ap_materno) = '"
                + nombre + "';";
        try {
            ps = getConexion().prepareStatement(sqlDeleteInscrito);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                String sqlDelete = "delete from profesor_curso where id_profesor = " + rs.getInt(1) + 
                        " and id_curso = " + rs.getInt(2) + ";";
                PreparedStatement ps2;
                ps2 = getConexion().prepareStatement(sqlDelete);
                ps2.executeUpdate();
            }
            return true;
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, "El curso que desea borrar ya tiene profesores inscritos");
        }
        return false;
    }
    
    public Profesor selectProfesorNombre(Profesor e){
        PreparedStatement ps;
        String sqlSelectProfesor="select id_profesor, nombre, ap_paterno, ap_materno, cedula, nivel_max, "
                + "carrera, correo from public.profesor where nombre = ?;";

        ResultSet rs;
        Profesor cursoEncontrado=null;
        try {
            ps = getConexion().prepareStatement(sqlSelectProfesor);
            ps.setString(1, e.getNombre());
            rs = ps.executeQuery();
            if(rs.next()) {
                cursoEncontrado = new Profesor(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), 
                        rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
        } catch(SQLException exception) {
            System.err.println("Error al cargar un curso por nombre");
        }
        return cursoEncontrado;
    }
    
    public Curso selectCursoNom(Curso e){
        PreparedStatement ps;
        String sqlSelectProfesor="select id_curso, nombrecurso, fechainicio, fechafin, id_profrespon, aula, "
                + "horainicio, horafin, estatus from public.curso where nombrecurso = ?;";

        ResultSet rs;
        Curso cursoEncontrado=null;
        try {
            ps = getConexion().prepareStatement(sqlSelectProfesor);
            ps.setString(1, e.getNombre());
            rs = ps.executeQuery();
            if(rs.next()) {
                cursoEncontrado = new Curso(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), 
                        rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9));
            }
        } catch(SQLException exception) {
            System.err.println("Error al cargar un curso por nombre");
        }
        return cursoEncontrado;
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
        if (getConexion() != null){
            try {
                getConexion().close();
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, "ERROR AL CERRAR LA BASE DE DATOS");
            }
        }
    }
}