/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import Controlador.EnviarCorreo;
import java.awt.Color;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import vista.VistaAltaCurso;
/**
 *
 * @author ACER
 */
public class ModeloVistaAltaCurso {
    private String  host     = "localhost";
    private String  usuario     = "postgres";
    private String  clave       = "admi";
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
    }
    
    private Connection getConexion() {
        return conexion;
    }
    
    public boolean insertCurso(Curso a){
        PreparedStatement ps;
        String sqlInsertCurso = ""; //Aqui nada mas pones en que esquema está la tabla profesor
        try {
            sqlInsertCurso = "insert into curso(nombrecurso, fechainicio, fechafin, id_profrespon," 
                    + "aula, horainicio, horafin) values(' " + a.getNombre() + "', '" + a.getfInicio() 
                    + "', '" + a.getfFin() + "', " + a.getIdProf() + ", '" + a.getAula() + "', ' " + a.gethInicio() 
                    + "', ' " + a.gethFin() + "');";
            ps = getConexion().prepareStatement(sqlInsertCurso);
            ps.executeUpdate();
            
            String[] datosCurso = new String[6];
            datosCurso[0] = a.getNombre();
            datosCurso[1] = a.getfInicio();
            datosCurso[2] = a.getfFin();
            datosCurso[3] = a.gethInicio() + " - " + a.gethFin();

            Profesor auxiliar = new Profesor();
            auxiliar.setId(a.getIdProf());
            auxiliar = selectProfesor(auxiliar);

            datosCurso[4] = auxiliar.getNombre() + " " + auxiliar.getApPaterno() + " " + auxiliar.getApMaterno();
            datosCurso[5] = a.getAula();
                //******************************************************************************************************************************************
            if(verificarInternet()) {
                EnviarCorreo en1 = new EnviarCorreo();
                en1.correoMasivo(datosCurso);
            }
                //******************************************************************************************************************************************
            return true;
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, "ERROR EN EL GUARDADO:  " + exception);
        }
        return false;
    }
    
    
    //******************************************************************************************************************************************
    public boolean verificarInternet() {
        String dirWeb = "www.lineadecodigo.com";
        int puerto = 80;
        
        try{
            Socket s = new Socket(dirWeb, puerto);
            if(s.isConnected()){
                return true;
            } 
        }catch(Exception e){
            return false;
        }
        return true;
    }
    
    public boolean deleteCurso(Curso l){
        PreparedStatement ps;
        String sqlDeleteCurso = "delete from public.curso where nombrecurso = ?;"; //Igual pones el esquema
        try {
            ps = getConexion().prepareStatement(sqlDeleteCurso);
            ps.setString(1, l.getNombre());
            ps.executeUpdate();
            return true;
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, "El curso que desea borrar ya tiene profesores inscritos");
        }
        return false;
    }
    
    public boolean updateCurso(Curso a){
        PreparedStatement ps;
        String sqlUpdateLibro = "update curso set nombrecurso = '" + a.getNombre() + "', fechainicio = '" + a.getfInicio() + 
                "', fechafin = '" + a.getfFin() + "', id_profrespon = " + a.getIdProf() + ", aula = '" + a.getAula() +
                "', horainicio = '" + a.gethInicio() + "', horafin = '" + a.gethFin() + "', estatus = '" + a.getEstado() +
                "' where id_curso = " + a.getId();

        try {
            ps = getConexion().prepareStatement(sqlUpdateLibro);
            ps.executeUpdate();
            return true;
        } catch(SQLException exception) {
            System.err.println("Error en la MODIFICACIÓN");
        }
        return false;
    }
    
    public Curso selectCurso(Curso e){
        PreparedStatement ps;
        String sqlSelectProfesor="nombrecurso, fechainicio, fechafin, id_proferespon, duracioncurso, aula, horainicio, horafin,"
                + " estatus where id_curso = ?;";

        ResultSet rs;
        Curso cursoEncontrado=null;
        try {
            ps = getConexion().prepareStatement(sqlSelectProfesor);
            ps.setInt(1, e.getId());
            rs = ps.executeQuery();
            if(rs.next()) {
                cursoEncontrado = new Curso(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), 
                        rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9));
            }
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR UN PROFESOR");
        }
        return cursoEncontrado;
    }
    
    public Color determinarColor(String nombre){
        PreparedStatement ps;
        ResultSet rs;
        String sqlDetermina = "select count(*) from profesor_curso pc, curso c where c.nombrecurso = '" 
                + nombre + "' and pc.id_curso = c.id_curso";
        try {
            ps = getConexion().prepareStatement(sqlDetermina);
            rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getInt(1) > 0)
                    return Color.PINK;
                else
                    return Color.GREEN;
            }
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
        return null;
    }
    
    /**
     * Método en cargado de determinar que aulas se van a eliminar del 
     * JComboBox de la interfaz VistaAltaCurso
     * @param view
     * @return 
     */
    public boolean eliminarAulas(VistaAltaCurso view) {
        PreparedStatement ps;
        ResultSet rs;
        
        //Variables necesarias para recuperar fragmentos de la fechaInicio
        //Esos fragmentos son el dia, mes y año
        String diaIn = view.fechaInicio.getDate().toString().substring(8,10);
        String mesIn = "";
        if(view.fechaInicio.getDate().getMonth() + 1 < 10) 
            mesIn = "0" + (view.fechaInicio.getDate().getMonth() + 1);
        else
            mesIn = (view.fechaInicio.getDate().getMonth() + 1) + "" ;
        int anioIn = view.fechaInicio.getDate().getYear() + 1900;
        //Variable para obtener la fecha de inicio en el formato necesario para la consulta
        String fechInicio = anioIn + mesIn + diaIn;
        
        //Variables necesarias para recuperar fragmentos de la fechFin
        //Esos fragmentos son el dia, mes y año
        String diaFi = view.fechaFin.getDate().toString().substring(8,10);
        String mesFi = "";
        if(view.fechaFin.getDate().getMonth() + 1 < 10)
            mesFi = "0" + (view.fechaFin.getDate().getMonth() + 1);
        else
            mesFi = (view.fechaFin.getDate().getMonth() + 1) + "";
        int anioFi = view.fechaFin.getDate().getYear() + 1900;
        //Variable para obtener la fecha de inicio en el formato necesario para la consulta
        String fechaFin = anioFi + mesFi + diaFi;
        
        //En las siguientes variables se obtiene la hora en el formato necesario para la busqueda
        String horaInicio = obtenerHora(view.getHoraInicio());
        String horaFin = obtenerHora(view.getHoraFin());
        
        //Cadena que representa la consulta a realizar
        String sqlDetermina = "SELECT aula FROM curso WHERE "
                + "((fechainicio BETWEEN '" + fechInicio + "' AND '" + fechaFin + "') or\n" + 
                "(fechafin BETWEEN '" + fechInicio + "' AND '"  + fechaFin + "')) and (\n" + 
                "(horainicio BETWEEN '" + horaInicio + "' AND '" 
                + horaFin + "') or (horafin BETWEEN '" +  horaInicio + "' AND '" + horaFin + "'));";
        
        try {
            ps = getConexion().prepareStatement(sqlDetermina);
            rs = ps.executeQuery();
            
            while(rs.next()){
                view.setAulaEliminar(rs.getString(1));
            }
            
            PreparedStatement ps2;
            ResultSet rs2 ;
            String sqlDetermina2 = "SELECT aula FROM curso WHERE (('" + fechInicio + 
                    "' BETWEEN fechainicio AND fechafin) or ('" + fechaFin +
                    "' BETWEEN fechainicio AND fechafin)) and (('" + horaInicio + "' BETWEEN "
                    + "horainicio AND horafin) or ('" + horaFin+ "' BETWEEN horainicio AND horafin));";

            ps2 = getConexion().prepareStatement(sqlDetermina2);
            rs2 = ps2.executeQuery();

            while(rs2.next()){
                view.setAulaEliminar(rs2.getString(1));
                System.out.println(rs2.getString(1));
            }
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
        return false;
    }
    
    
    /**
     * Método encargado de regresar las cadenas hora en un formato legible para la consulta SQL
     * 
     * @param hora
     * @return 
     */
    public String obtenerHora (String hora ) {
        String hour = hora;
        String h = "";
        String mi = "";
        String form = "";
        for(int x = 0; x < hour.length(); x++){
            if(x == 0 || x == 1){
                char letra = hour.charAt(x);
                h += letra;
            }
            if(x == 3 || x ==4){
                char le = hour.charAt(x);
                mi += le;
            }
            if(x == 6 || x ==7){
                char l = hour.charAt(x);
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
                    h = "00";
                break;
            }
        }
        String hora24 = h + ":" + mi + ":" + "00";
        return hora24;
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
    
    public Profesor selectProfesor(Profesor e){
        PreparedStatement ps;
        String sqlSelectProfesor="select id_profesor, nombre, ap_paterno, ap_materno, cedula, nivel_max, "
                + "carrera, correo from public.profesor where id_profesor = ?;";

        ResultSet rs;
        Profesor cursoEncontrado=null;
        try {
            ps = getConexion().prepareStatement(sqlSelectProfesor);
            ps.setInt(1, e.getId());
            rs = ps.executeQuery();
            if(rs.next()) {
                cursoEncontrado = new Profesor(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), 
                        rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
        }
        catch(SQLException exception) { }
        return cursoEncontrado;
    }
    
    public List<Curso> listCursos(){
	PreparedStatement ps;
        //Objeto para recoger los datos devueltos por el procedimiento almacenado
        ResultSet rs;
        //Objeto para recorrer el resultado del procedimiento almacenado y
        //  añadirlo a la tabla definida en la clase View
      
 
        //Cargar datos de todos los estudiantes
        String consultaSQL = "Select id_curso, nombrecurso, fechainicio, fechafin, id_profrespon, "
                + "aula, horainicio, horafin, estatus from public.curso;";

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
            System.err.println("Error al CARGAR DATOS");
        }
	return cursos;
    }
    
    public List<Curso> listCursosAula(Curso c){
	PreparedStatement ps;
        //Objeto para recoger los datos devueltos por el procedimiento almacenado
        ResultSet rs;
        //Objeto para recorrer el resultado del procedimiento almacenado y
        //  añadirlo a la tabla definida en la clase View
      
 
        //Cargar datos de todos los estudiantes
        String consultaSQL = "Select id_curso, nombrecurso, fechainicio, fechafin, id_profrespon, "
                + "aula, horainicio, horafin, estatus from public.curso;";

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
                if(c.getAula().equals(a.getAula()))
                    cursos.add(a);
            }
        } catch (SQLException exception) {
            System.err.println("Error al CARGAR DATOS");
        }
	return cursos;
    }
    
    //Modificado
    public List<String> cargarNombres(){
        PreparedStatement ps;
        ResultSet rs;
        String comandoSQL = "select id_profesor, nombre, ap_paterno, ap_materno from profesor";
        id = new ArrayList<Integer>();
        nombres = new ArrayList<String>();
        try {
            ps = getConexion().prepareStatement(comandoSQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                id.add(rs.getInt(1));
                nombres.add(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }
            return nombres;
        } catch (SQLException ex) { }
        return null;
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
    
    public List<Integer> getIds() {
        return id;
    }
}