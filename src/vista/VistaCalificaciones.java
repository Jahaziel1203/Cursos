/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Controlador.ControladorCalificaciones;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ErikHernandez
 */
public class VistaCalificaciones extends javax.swing.JFrame {
    protected JScrollPane jScrollPane1;
    protected Object[][] datosCurso;
    public DefaultTableModel dtm;
    protected String[] cabecera;
    public JTable tabla;
    
    /**
     * Creates new form VistaCalificaciones
     */
    public VistaCalificaciones() {
        initComponents();
        
        btnGuardar.setSize(160, 30);
        btnPromedio.setSize(160, 30);
        
        jScrollPane1 = new JScrollPane();
        cabecera = new String[] {"Curso de actualización", "Promedio"};
        dtm = new DefaultTableModel(datosCurso,cabecera);
        tabla = new JTable(dtm);
        jScrollPane1.setViewportView(tabla);
        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(15, 280, 425, 100);
        
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getColumnModel().getColumn(1).setMaxWidth(100);
        tabla.getColumnModel().getColumn(1).setMinWidth(105);
        
        this.setTitle("Sistema de gestión de aprendizaje online");
        this.setLocation(200, 200);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nombres = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cursos = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        dCalificacion = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnPromedio = new javax.swing.JButton();
        btnCalificaciones = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 255), null));

        jLabel2.setText("Nombre del profesor:");

        jLabel3.setText("Nombre del curso:");

        jLabel4.setText("Capturar nueva calificación:");

        dCalificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dCalificacionActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");

        btnPromedio.setText("Consultar Promedio");
        btnPromedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPromedioActionPerformed(evt);
            }
        });

        btnCalificaciones.setText("Ver Calificaciones");
        btnCalificaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalificacionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cursos, 0, 230, Short.MAX_VALUE)
                            .addComponent(nombres, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCalificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(btnPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(dCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalificaciones)
                    .addComponent(btnPromedio))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Captura de calificaciones");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Calificaciones");

        btnRegresar.setText("Menú Principal");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addComponent(btnRegresar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCalificacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalificacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCalificacionesActionPerformed

    private void btnPromedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPromedioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPromedioActionPerformed

    private void dCalificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dCalificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dCalificacionActionPerformed

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
            java.util.logging.Logger.getLogger(VistaCalificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaCalificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaCalificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaCalificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaCalificaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalificaciones;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnPromedio;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cursos;
    private javax.swing.JTextField dCalificacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> nombres;
    // End of variables declaration//GEN-END:variables

    public void conectaControlador(ControladorCalificaciones con) {
        btnRegresar.addActionListener((ActionListener) con);
        btnRegresar.setActionCommand("Regresar");
        
        btnGuardar.addActionListener((ActionListener) con);
        btnGuardar.setActionCommand("Guardar");
        
        btnCalificaciones.addActionListener((ActionListener) con);
        btnCalificaciones.setActionCommand("Calificaciones");
        
        btnPromedio.addActionListener((ActionListener) con);
        btnPromedio.setActionCommand("Promedio");
        
        nombres.addItemListener((ItemListener) con);
        
        dCalificacion.addKeyListener( new KeyAdapter() {
            public void keyTyped(KeyEvent e){
                char caracter = e.getKeyChar();
                if (dCalificacion.getText().length() < 3) {
                    if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
                        e.consume();
                    }
                    
                    if(dCalificacion.getText().length() == 2) {
                        int primerDigito = Integer.parseInt(dCalificacion.getText().substring(0, 2));
                        if(primerDigito > 10)
                        e.consume();
                    }
                } else
                    e.consume();
            }
        });
    }
    
    /**
     * Método para asignar un modelo de datos al JComboBox que contiene los nombres 
     * de los profesores contenidos en la BD
     * @param listado Lista que contiene los nombres obtenidos de la BD
     */
    public void setModeloProfesor(List<String> listado){
        Vector aux = new Vector(0,1);
        for(int x = 0; x < listado.size(); x++)
            aux.addElement(listado.get(x));
        DefaultComboBoxModel modelo = new DefaultComboBoxModel(aux);
        nombres.setModel(modelo);
    }
            
    /**
     * Método para asignar el modelo de datos al JComboBox que lista los cursos registrados
     * en la BD
     * @param listado Lista que contiene los nombres obtenidos de la base de datos
     */
    public void setModeloCurso(List<String> listado){
        Vector aux = new Vector(0,1);
        for(int x = 0; x < listado.size(); x++)
            aux.addElement(listado.get(x));
        DefaultComboBoxModel modelo = new DefaultComboBoxModel(aux);
        cursos.setModel(modelo);
    }
    
    /**
     * Método encargado de realizar la asignacion del elemento seleccionado dentro del JComboBox
     * nombres
     * @param nombre Nombre del profesor a seleccionar
     */
    public void setProfSeleccionado(String nombre) {
        nombres.setSelectedItem(nombre);
    }
            
    /**
     * Método encargado de realizar la asignacion del elemento seleccionado dentro del JComboBox
     * cursos
     * @param curso Nombre del curso a seleccionar
     */
    public void setCursoSeleccionado(String curso){
        cursos.setSelectedItem(curso);
    }
    
    /**
     * Método para asignar una calificacion dentro del campo calificacion
     * @param calificacion Calificacion del 0 al 100 para asignar coom calificacion del curso
     */
    public void setCalificacion(int calificacion) {
        dCalificacion.setText(calificacion + "");
    }
    
    /**
     * Método encargado de regresar el nombre del profesor seleccionado actualmente en el JComboBox
     * profesores
     * @return nombre del profesor seleccionado en un momento dado
     */
    public String getProfSeleccionado() {
        String nombre = "";
        if(nombres.getSelectedItem() != null)
            nombre = nombres.getSelectedItem().toString();
        return nombre;
    }
    
    /**
     * Método encargado de regresar el nombre del curso seleccionado dentro de los contenidos en 
     * el JComboBox cursos
     * @return nombre del curso seleccionado
     */
    public String getCursoSeleccionado() {
        String a = "";
        if(cursos.getSelectedItem() != null)
            a  = cursos.getSelectedItem().toString();
        return a;
    }
    
    /**
     * Método para obtener la calificacion del profesor en un curso
     * @return calificacion contenida en el campo calificacion
     */
    public Integer getCalificacion() {
        if(dCalificacion.getText() != "")
            try {
                Integer calificacion = Integer.parseInt(dCalificacion.getText());
                return calificacion;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "El campo de la calificación no debe estar vacío");
            }
        return null;
    }
    
    
    /**
     * Método getTamanioCursos, sirve para obtener el numero de elementos contenidos
     * en el JComboBox cursos
     * @return numero de cursos contenidos en el JComboBox
     */
    public int getTamanioCursos() {
        return cursos.getItemCount();
    }
}