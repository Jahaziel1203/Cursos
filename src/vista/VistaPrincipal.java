package vista;

import Controlador.ControladorPrincipal;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ACER
 */
public class VistaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VistaPrincipal
     */
    public VistaPrincipal() {
        initComponents();
        ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/ESCUELA.png"));
        Icon fondo = new ImageIcon(imagen.getImage().getScaledInstance(Imagen.getWidth(), Imagen.getHeight(), Image.SCALE_DEFAULT));
        Imagen.setIcon(fondo);
        cerrar();
        this.setBounds(200, 200, 475, 420);
        //btnAgregarCurso.setLocation(24, 240);
        //btnAgregarProfesor.setLocation(225,240);
        btnInscribir.setSize(140, 30);
        //btnConsultarCurso.setSize(127, 32);
        //btnConsultarProfesor.setSize(150,32);
        this.repaint();
        this.setVisible(true);    
    }
    
    public void cerrar(){
        try{
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter(){
                public void windowClosing(java.awt.event.WindowEvent evt){
                close();
                }
            });
                
        }catch(Exception e){
        }
    }
    
    private void close(){
        if(JOptionPane.showConfirmDialog(rootPane, "¿Desea salir realmente?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnAgregarCurso = new javax.swing.JButton();
        btnAgregarProfesor = new javax.swing.JButton();
        Imagen = new javax.swing.JLabel();
        btnInscribir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(102, 255, 255));
        setSize(new java.awt.Dimension(391, 344));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("ELIGE LA OPCIÓN QUE DESEES REALIZAR");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 10, 380, 30);

        btnAgregarCurso.setText("Agregar Curso");
        btnAgregarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCursoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregarCurso);
        btnAgregarCurso.setBounds(10, 330, 120, 32);

        btnAgregarProfesor.setText("Agregar Profesor");
        getContentPane().add(btnAgregarProfesor);
        btnAgregarProfesor.setBounds(150, 330, 140, 32);

        Imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ESCUELA.png"))); // NOI18N
        Imagen.setText("jLabel2");
        getContentPane().add(Imagen);
        Imagen.setBounds(30, 50, 400, 260);

        btnInscribir.setText("Inscribir Profesor");
        getContentPane().add(btnInscribir);
        btnInscribir.setBounds(310, 330, 130, 32);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarCursoActionPerformed

    public void conectaControlador(ControladorPrincipal c ){
 
                btnAgregarCurso.addActionListener(c);
                btnAgregarCurso.setActionCommand("Agregar Curso");
      
		btnAgregarProfesor.addActionListener(c);
		btnAgregarProfesor.setActionCommand("Agregar Profesor");
                
                btnInscribir.addActionListener(c);
                btnInscribir.setActionCommand("Inscribir Profesor");
                
                //btnConsultarCurso.addActionListener(c);
		//btnConsultarCurso.setActionCommand("Consultar Curso");
                
                //btnConsultarProfesor.addActionListener(c);
		//btnConsultarProfesor.setActionCommand("Consultar Profesor");
                
                //tConductor.addMouseListener(c);
 
        //tabla.addMouseListener(c);
        //sólo se permite pulsar una fila a la vez.
        //tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Imagen;
    private javax.swing.JButton btnAgregarCurso;
    private javax.swing.JButton btnAgregarProfesor;
    private javax.swing.JButton btnInscribir;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
