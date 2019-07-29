package Controlador;

import Modelo.ConexionCorreos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarCorreo {
    //Variables que hacen referencia al correo electronico emisor
    private String correoRemitente = "cursosact2@gmail.com";
    private String contrasenaRemitente = "cursosjava";
    
    
    /**
     * 
     * @param correoDestino
     * @param nombre
     * @return
     * Este método es el encargado de mandar los correos electronicos a cada uno de los profesores
     * la variable de retorno es utilizada para saber si fue o no enviado el correo
     */
    public boolean mandarCorreo(String correoDestino, String nombre,  String[] datosCurso){
        try {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "Usuario Colegio");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoRemitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
            message.setSubject("Cursos de Actualización");
            
            //Construccion del mensaje en HTML
            String mensaje = "Hola <b>" + nombre + "</b> <br>Es un placer informarle "
                    + "que está disponible el curso de actualización: <b>"
                    + datosCurso[0] + "</b> siendo sus datos los siguientes: <br><br> Fecha de inicio: <b>"
                    + datosCurso[1] + "</b> <br> Fecha de termino: <b>" + datosCurso[2] + "</b>"
                    + "<br> Horario: <b>" + datosCurso[3] + "</b> <br> Aula: <b>" + datosCurso[5] + "</b> "
                    + "<br> Impartido por: <b>" + datosCurso[4] + "</b><br><br><br> "
                    + "Para anotarse en el curso favor de pasar con la secretaria de control escolar";
            message.setText(mensaje, "ISO-8859-1", "html");
            
            // Lo enviamos
            Transport t = session.getTransport("smtp");
            t.connect(correoRemitente, contrasenaRemitente);
            t.sendMessage(message, message.getAllRecipients());
            // Se cierra la conexion establecida
            t.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * Este método es el encargado de realizar la consulta y en base a lo que recupere
     * ejecuta el metodo enviarCorreo el mismo numero de maestros existentes en la base de datos
     */
    public void correoMasivo(String[] datosCurso){
        PreparedStatement ps;
        ResultSet rs;
        ConexionCorreos conn = new ConexionCorreos();
        Connection con = (Connection) conn.getConexion();
        String sql = "SELECT nombre, ap_paterno, correo FROM profesor";
        List<String> correos = new ArrayList<String>();
        List<String> nombres = new ArrayList<String>();

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
           
            while (rs.next()) {
                correos.add(rs.getString(3));
                nombres.add(rs.getString(1) + " " + rs.getString(2));
            }
            con.close();
        } catch (SQLException ex) { }
            int j = 0;
            while (j < correos.size()) {
                mandarCorreo(correos.get(j), nombres.get(j), datosCurso);
                j++;
            }
    }
}