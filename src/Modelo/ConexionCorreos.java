package Modelo;

/**
 *
 * @author ErikHernandez
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionCorreos{
    private final String usuario = "postgres";
    private final String contrasena = "1rv1ng4rc14";
    private final String url =  "jdbc:postgresql://localhost:5432/CursosActualizacion";
    Connection con = null;
    
    public Connection getConexion(){ 
        try {
            Class.forName("org.postgresql.Driver");
            con = (Connection) DriverManager.getConnection(url,  usuario, contrasena);
        } catch (Exception e) {
            System.out.println("Ocurrio un error : "+e.getMessage());
        }
        System.out.println("Conexión realizada correctamente");
        return con;
    }
}