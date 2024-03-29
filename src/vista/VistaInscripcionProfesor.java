/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Controlador.ControladorInscripcion;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ErikHernandez
 */
public class VistaInscripcionProfesor extends javax.swing.JFrame {
    protected JScrollPane jScrollPane1;
    protected Object[][] datosProf;
    public DefaultTableModel dtm;
    protected String[] cabecera;
    public JTable tabla;

    protected JScrollPane jScrollPane11;
    protected Object[][] datosCurso;
    public DefaultTableModel dtm1;
    protected String[] cabecera1;
    public JTable tabla1;
    
    /**
     * Creates new form VistaInscripcionProfesor
     */
    public VistaInscripcionProfesor() {
        initComponents();
        
        jScrollPane1 = new JScrollPane();
        cabecera = new String[] {"NOMBRE", "AP PAT", "AP MAT", "CÉDULA", "ESTUDIO", "CARRERA", "CORREO"};
        dtm = new DefaultTableModel(datosCurso,cabecera);
        tabla = new JTable(dtm);
        jScrollPane1.setViewportView(tabla);
        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 167, 680, 39);
        
        jScrollPane11 = new JScrollPane();
        cabecera1 = new String[] {"Nombre Curso", "Fecha Inicio", "Fecha Term", "Prof. Res", "Aula", "Hora Inicio", "Hora Fin"};
        dtm1 = new DefaultTableModel(datosProf,cabecera1);
        tabla1 = new JTable(dtm1);
        jScrollPane11.setViewportView(tabla1);
        getContentPane().add(jScrollPane11);
        jScrollPane11.setBounds(20, 253, 680, 110);
        
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getColumnModel().getColumn(0).setMaxWidth(110);
        tabla.getColumnModel().getColumn(0).setMinWidth(115);
        tabla.getColumnModel().getColumn(1).setMaxWidth(80);
        tabla.getColumnModel().getColumn(1).setMinWidth(75);
        tabla.getColumnModel().getColumn(2).setMaxWidth(80);
        tabla.getColumnModel().getColumn(2).setMinWidth(75);
        tabla.getColumnModel().getColumn(3).setMaxWidth(70);
        tabla.getColumnModel().getColumn(3).setMinWidth(65);
        tabla.getColumnModel().getColumn(4).setMaxWidth(85);
        tabla.getColumnModel().getColumn(4).setMinWidth(80);
        
        tabla1.getTableHeader().setReorderingAllowed(false);
        tabla1.getColumnModel().getColumn(1).setMaxWidth(90);
        tabla1.getColumnModel().getColumn(1).setMinWidth(85);
        tabla1.getColumnModel().getColumn(2).setMaxWidth(90);
        tabla1.getColumnModel().getColumn(2).setMinWidth(85);
        tabla1.getColumnModel().getColumn(3).setMaxWidth(130);
        tabla1.getColumnModel().getColumn(3).setMinWidth(125);
        tabla1.getColumnModel().getColumn(4).setMaxWidth(45);
        tabla1.getColumnModel().getColumn(4).setMinWidth(40);
        tabla1.getColumnModel().getColumn(5).setMaxWidth(77);
        tabla1.getColumnModel().getColumn(5).setMinWidth(72);
        tabla1.getColumnModel().getColumn(6).setMaxWidth(75);
        tabla1.getColumnModel().getColumn(6).setMinWidth(70);
        
        this.setLocation(200, 200);
        this.setVisible(true);
        this.setTitle("Sistema de gestión de aprendizaje online");
        this.setResizable(false);
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
        profesores = new javax.swing.JComboBox<>();
        botonInscribir = new javax.swing.JButton();
        cursos = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnBaja = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 255), null));

        jLabel2.setText("Profesor: ");

        profesores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        botonInscribir.setText("Inscribir");
        botonInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonInscribirActionPerformed(evt);
            }
        });

        cursos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Curso:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(profesores, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cursos, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonInscribir)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(profesores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonInscribir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Inscripción Profesor - Curso");

        btnRegresar.setText("Menú Principal");

        jLabel4.setText("Datos Profesor");

        jLabel5.setText("Inscrito en Cursos");

        btnBaja.setText("Darse de Baja");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBaja)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(289, 289, 289))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnRegresar)
                                        .addGap(11, 11, 11))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(316, 316, 316)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(71, 71, 71)
                        .addComponent(jLabel5)
                        .addGap(8, 152, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBaja)
                            .addComponent(btnRegresar))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonInscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonInscribirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonInscribirActionPerformed

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
            java.util.logging.Logger.getLogger(VistaInscripcionProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaInscripcionProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaInscripcionProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaInscripcionProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaInscripcionProfesor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonInscribir;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnRegresar;
    public javax.swing.JComboBox<String> cursos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JComboBox<String> profesores;
    // End of variables declaration//GEN-END:variables

    /**
     * Método para realizar el enlace con el controlador de las inscripciones
     * @param con 
     */
    public void conectaControlador(ControladorInscripcion con) {
        botonInscribir.addActionListener((ActionListener) con);
        botonInscribir.setActionCommand("Inscribir");
        
        btnRegresar.addActionListener((ActionListener) con);
        btnRegresar.setActionCommand("Regresar");
        
        btnBaja.addActionListener((ActionListener) con);
        btnBaja.setActionCommand("Baja");
        
        profesores.addItemListener((ItemListener) con);
        profesores.setActionCommand("Profesores");
        
        cursos.addItemListener((ItemListener) con);
        cursos.setActionCommand("Cursos");
        
        //tabla1.addMouseListener((MouseListener) con);
    }
    
    /**
     * Método para asignar un modelo de datos al JComboBox que contiene los nombres de los 
     * datos de alta en la BD
     * @param listado 
     */
    public void setModeloProfesor(List<String> listado){
        Vector aux = new Vector(0,1);
        for(int x = 0; x < listado.size(); x++)
            aux.addElement(listado.get(x));
        DefaultComboBoxModel modelo = new DefaultComboBoxModel(aux);
        profesores.setModel(modelo);
    }
            
    /**
     * Método para asignar el modelo de datos al JComboBox que lista los cursos registrados
     * en la BD
     * @param listado 
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
     * profesores
     * @param nombre 
     */
    public void setProfSeleccionado(String nombre) {
        profesores.setSelectedItem(nombre);
    }
            
    /**
     * Método encargado de realizar la asignacion del elemento seleccionado dentro del JComboBox
     * cursos
     * @param nombre 
     */
    public void setCursoSeleccionado(String nombre){
        cursos.setSelectedItem(nombre);
    }
    
    /**
     * Método utilizado para eliminar un elemento del modelo de datos contenido en el JComboBox
     * profesores, este sera utilizado al aplicar los filtros de seleccion una vez seleccionado un curso
     * @param nombre 
     */
    public void setProfEliminar(String nombre) {
        profesores.removeItem(nombre);
    }
    
    /**
     * Método encargado de remover cursos contenidos en el JComboBox cursos, los nombres de los cursos
     * a eliminar seran determinados al seleccionar a un profesor, ya que se quitaran los que ya no estan
     * disponibles para dicho profesor, ya sea por que es el encargado del curso, ya esta inscrito o
     * se cruzan los horarios con otro en el que ya esta inscrito.
     * @param nombre 
     */
    public void setCursoEliminar(String nombre) {
        cursos.removeItem(nombre);
    }
    
    
    /**
     * Método encargado de regresar el nombre del profesor seleccionado actualmente en el JComboBox
     * profesores
     * @return 
     */
    public String getProfSeleccionado() {
        return profesores.getSelectedItem().toString();
    }
    
    /**
     * Método encargado de regresar el nombre del profesor seleccionado dentro de los contenidos en 
     * el JComboBox cursos
     * @return 
     */
    public String getCursoSeleccionado() {
        String a = "";
        if(cursos.getSelectedItem() != null)
            a  = cursos.getSelectedItem().toString();
        return a;
    }
}