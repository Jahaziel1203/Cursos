package DateChooser;
import Atxy2k.CustomTextField.RestrictedTextField;
import Modelo.ModeloVistaAltaCurso;
import java.awt.Image;
import java.awt.event.KeyEvent;
import vista.VistaAltaCurso;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
/**
 * @web http://jc-mouse.net/
 * @author Mouse
 */
public class TimePanel extends javax.swing.JPanel {
    private javax.swing.JTextField Hora;
    private javax.swing.JTextField Minuto;
    String aux;
    VistaAltaCurso vista;
    ModeloVistaAltaCurso modelo;
    //--------------
    Calendar cal = Calendar.getInstance();
    private JTextField texto;
    //--------------
    
    /**
     * Creates new form TimePanel
     */
    public TimePanel() {
        initComponents();
    }
   
// <editor-fold desc="Nuevo codigo">
    
    /** 
     * Constructor de clase con parametro de entrada
     * @param JTextField componente padre
     */
    public TimePanel(JTextField jtextField, VistaAltaCurso v, String s, ModeloVistaAltaCurso m) {
        aux = s;
        //this.setUndecorated(true);
        initComponents2();
        texto = jtextField;
        vista = v;
        modelo = m;
        //Se coloca el tiempo del sistema
        Hora.setText( (cal.get(Calendar.HOUR)<10)?"0"+cal.get(Calendar.HOUR):String.valueOf(cal.get(Calendar.HOUR)) );
        Minuto.setText( (cal.get(Calendar.MINUTE)<10)?"0"+cal.get(Calendar.MINUTE):String.valueOf(cal.get(Calendar.MINUTE)) );
        
        if(v.getHoraInicio() == "" && v.getHoraFin() != ""){
            //Hora.setText(v.getHoraFin());
            String xx = "";
            String xxx = "";
            for(int x = 0; x < v.getHoraFin().length(); x++){
                        if(x == 0 || x == 1){
                            char letra = v.getHoraFin().charAt(x);
                            xx += letra;
                        }
                        if(x == 3 || x == 4){
                            char letra = v.getHoraFin().charAt(x);
                            xxx += letra;
                        }
            }
            int x = Integer.parseInt(xx);
            x = x - 1;
            if(x < 10)
                Hora.setText("0" + x);
            else
                Hora.setText(x + "");
            int xy = Integer.parseInt(xxx);
            if(xy < 10)
                Minuto.setText("0" + xy);
            else
                Minuto.setText(xy + "");
        }
        if(v.getHoraInicio() != "" && v.getHoraFin() == ""){
            //Hora.setText(v.getHoraInicio());
            String xx = "";
            String xxx = "";
            for(int x = 0; x < v.getHoraInicio().length(); x++){
                        if(x == 0 || x == 1){
                            char letra = v.getHoraInicio().charAt(x);
                            xx += letra;
                        }
                        if(x == 3 || x == 4){
                            char letra = v.getHoraInicio().charAt(x);
                            xxx += letra;
                        }
            }
            int x = Integer.parseInt(xx);
            x = x + 1;
            if(x < 10)
                Hora.setText("0" + x);
            else
                Hora.setText(x + "");
            int xy = Integer.parseInt(xxx);
            if(xy < 10)
                Minuto.setText("0" + xy);
            else
                Minuto.setText(xy + "");
        }
        texto.setEditable(false);
        
        if( cal.get( Calendar.AM_PM ) == 0 ){  
            AmPm.setText("AM");
        }else
        {
            AmPm.setText("PM");
        }
        
        //Eventos de los controles del componente
        btnHora1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                ajustarHora("-");                
            }
        });
        btnHora2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                ajustarHora("+");                
            }
        });
        
        btnMinuto1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                ajustarMinuto("-");                
            }
        });
        btnMinuto2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                ajustarMinuto("+");                
            }
        });
        btnAmPm1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                AmPm.setText( (AmPm.getText().equals("AM"))?"PM":"AM" );             
            }
        });
        btnAmPm2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                AmPm.setText( (AmPm.getText().equals("AM"))?"PM":"AM" );             
            }
        }); 
        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JPopupMenu menu = ((JPopupMenu) getParent());
                menu.setVisible(false);
            }
        }); 
        btnSet.addMouseListener(new MouseAdapter() {
            
            //************************************************************************************************** Modificado
            @Override
            public void mouseClicked(MouseEvent evt) {
                JPopupMenu menu = ((JPopupMenu) getParent());
                menu.setVisible(false);
                try{
                    if(aux.equals("Hora Fin:")) {
                    int hr = Integer.parseInt(Hora.getText());
                    String hrr = "";
                    if(hr <= 12){
                        if(hr < 10){
                            hrr = "0" + hr;
                            int min = Integer.parseInt(Minuto.getText());
                            String min1 = "";
                            if(min < 60){
                                if(min < 10){
                                    min1 = "0" + min;
                                    String hora = hrr + ":" + min1 + " " + AmPm.getText();
                                    texto.setText(hora);
                                    vista.setHoraFin(hora);
                                } else{
                                    String hora = hrr + ":" + min + " " + AmPm.getText();
                                    texto.setText(hora);
                                    vista.setHoraFin(hora);
                                }
                            } else{
                                JOptionPane.showMessageDialog(null, "Los minutos no pueden ser mayores a 60");
                            }
                        } else{
                            int min = Integer.parseInt(Minuto.getText());
                            if(min < 60){
                                String min1 = "";
                                if(min < 10){
                                    min1 = "0" + min;
                                    String hora = hr + ":" + min1 + " " + AmPm.getText();
                                    texto.setText(hora);
                                    vista.setHoraFin(hora);
                                } else{
                                    String hora = hr + ":" + min + " " + AmPm.getText();
                                    texto.setText(hora);
                                    vista.setHoraFin(hora);
                                }
                            } else{
                                JOptionPane.showMessageDialog(null, "Los minutos no pueden ser mayores a 60");
                            }
                        }
                    } if(hr > 12){
                        JOptionPane.showMessageDialog(null, "La hora no puede ser mayor a 12");
                    }
                
                    //vista.setHoraFin(texto.getText());
                    if(vista.fechaInicio.getDate() != null && vista.fechaFin.getDate() != null && 
                            vista.getHoraInicio() != "" && vista.getHoraFin() != ""){
                        modelo.eliminarAulas(vista);
                    }
                } else { //Acaba la hora de fin
                    
                    int hr = Integer.parseInt(Hora.getText());
                    String hrr = "";
                    if(hr <= 12){
                        if(hr < 10){
                            hrr = "0" + hr;
                            int min = Integer.parseInt(Minuto.getText());
                            String min1 = "";
                            if(min < 60){
                                if(min < 10){
                                    min1 = "0" + min;
                                    String hora = hrr + ":" + min1 + " " + AmPm.getText();
                                    texto.setText(hora);
                                    vista.setHoraInicio(hora);
                                } else{
                                    String hora = hrr + ":" + min + " " + AmPm.getText();
                                    texto.setText(hora);
                                    vista.setHoraInicio(hora);
                                }
                            } else{
                                JOptionPane.showMessageDialog(null, "Los minutos no pueden ser mayores a 60");
                            }
                        } else{
                            int min = Integer.parseInt(Minuto.getText());
                            if(min < 60){
                                String min1 = "";
                                if(min < 10){
                                    min1 = "0" + min;
                                    String hora = hr + ":" + min1 + " " + AmPm.getText();
                                    texto.setText(hora);
                                    vista.setHoraInicio(hora);
                                } else{
                                    String hora = hr + ":" + min + " " + AmPm.getText();
                                    texto.setText(hora);
                                    vista.setHoraInicio(hora);
                                }
                            } else{
                                JOptionPane.showMessageDialog(null, "Los minutos no pueden ser mayores a 60");
                            }
                        }
                    } if(hr > 12){
                        JOptionPane.showMessageDialog(null, "La hora no puede ser mayor a 12");
                    }
                    if(vista.fechaInicio.getDate() != null && vista.fechaFin.getDate() != null && 
                            vista.getHoraInicio() != "" && vista.getHoraFin() != ""){
                        modelo.eliminarAulas(vista);
                    }
                }
                    
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Error en el formato de la hora");
                }
                    
            }
        }); 
    }//end constructor
    
    /** 
     * Metodo privado que ajusta el valor de la hora
     * @param String value cadena que puede tener los valores 
     *      "+" para incrementar el valor
     *      "-" para decrementar el valor
     */
    private void ajustarHora(String value)
    {
        int h = Integer.valueOf( Hora.getText() );                
        
        if( value.equals("+"))
        {
            h = (h==12)?1:(h+1);    
        }
        else if( value.equals("-"))
        {
            h = (h==1)?12:(h-1);
        }
        String s = (h<10)?"0"+h:String.valueOf(h);
        Hora.setText(s);
    }
    
    /** 
     * Metodo privado que ajusta el valor del minuto
     * @param String value cadena que puede tener los valores 
     *      "+" para incrementar el valor
     *      "-" para decrementar el valor
     */
    private void ajustarMinuto(String value)
    {
        int m = Integer.valueOf( Minuto.getText() );                
        
        if( value.equals("+"))
        {
            m = (m==59)?0:(m+1);    
        }
        else if( value.equals("-"))
        {
            m = (m==1)?59:(m-1);
        }
        String s = (m<10)?"0"+m:String.valueOf(m);
        Minuto.setText(s);
    }
    
// </editor-fold>
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    private void initComponents2() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        btnHora1 = new javax.swing.JLabel();
        btnHora2 = new javax.swing.JLabel();
        Hora = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnMinuto2 = new javax.swing.JLabel();
        btnMinuto1 = new javax.swing.JLabel();
        Minuto = new javax.swing.JTextField();
        btnAmPm1 = new javax.swing.JLabel();
        btnAmPm2 = new javax.swing.JLabel();
        AmPm = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JLabel();
        btnSet = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(200, 172));
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(224, 224, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(134, 148, 162)));
        jPanel1.setPreferredSize(new java.awt.Dimension(180, 110));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        btnHora1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/arrowup.png"))); // NOI18N
        btnHora1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(btnHora1, gridBagConstraints);

        btnHora2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/arrowdown.png"))); // NOI18N
        btnHora2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel1.add(btnHora2, gridBagConstraints);

        Hora.setBackground(new java.awt.Color(255, 255, 255));
        Hora.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Hora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Hora.setText("00");
        Hora.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(134, 148, 162)));
        Hora.setOpaque(true);
        Hora.setPreferredSize(new java.awt.Dimension(44, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Hora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                HoraKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                HoraKeyReleased(evt);
            }
        });
        RestrictedTextField rest = new RestrictedTextField(Hora);
        rest.setLimit(2);
        jPanel1.add(Hora, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText(":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel1.add(jLabel10, gridBagConstraints);

        btnMinuto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/arrowdown.png"))); // NOI18N
        btnMinuto2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        jPanel1.add(btnMinuto2, gridBagConstraints);

        btnMinuto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/arrowup.png"))); // NOI18N
        btnMinuto1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel1.add(btnMinuto1, gridBagConstraints);

        Minuto.setBackground(new java.awt.Color(255, 255, 255));
        Minuto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Minuto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Minuto.setText("00");
        Minuto.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(134, 148, 162)));
        Minuto.setOpaque(true);
        Minuto.setPreferredSize(new java.awt.Dimension(44, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Minuto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MinutoaKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MinutoKeyReleased(evt);
            }
        });
        RestrictedTextField res = new RestrictedTextField(Minuto);
        res.setLimit(2);
        jPanel1.add(Minuto, gridBagConstraints);

        btnAmPm1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/arrowup.png"))); // NOI18N
        btnAmPm1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        jPanel1.add(btnAmPm1, gridBagConstraints);

        btnAmPm2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/arrowdown.png"))); // NOI18N
        btnAmPm2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        jPanel1.add(btnAmPm2, gridBagConstraints);

        AmPm.setBackground(new java.awt.Color(255, 255, 255));
        AmPm.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AmPm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AmPm.setText("00");
        AmPm.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(134, 148, 162)));
        AmPm.setOpaque(true);
        AmPm.setPreferredSize(new java.awt.Dimension(44, 44));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        jPanel1.add(AmPm, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jPanel1, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("HORA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        add(jLabel1, gridBagConstraints);

        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCancel.setText("CANCEl");
        btnCancel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(134, 148, 162)));
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.setPreferredSize(new java.awt.Dimension(99, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(btnCancel, gridBagConstraints);

        btnSet.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSet.setText("ACEPTAR");
        btnSet.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(134, 148, 162)));
        btnSet.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSet.setPreferredSize(new java.awt.Dimension(99, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(btnSet, gridBagConstraints);
        
        this.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        btnHora1 = new javax.swing.JLabel();
        btnHora2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnMinuto2 = new javax.swing.JLabel();
        btnMinuto1 = new javax.swing.JLabel();
        btnAmPm1 = new javax.swing.JLabel();
        btnAmPm2 = new javax.swing.JLabel();
        AmPm = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JLabel();
        btnSet = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(200, 172));
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(224, 224, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(134, 148, 162)));
        jPanel1.setPreferredSize(new java.awt.Dimension(180, 110));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        btnHora1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/bolivia/resources/arrowup.png"))); // NOI18N
        btnHora1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(btnHora1, gridBagConstraints);

        btnHora2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/bolivia/resources/arrowdown.png"))); // NOI18N
        btnHora2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel1.add(btnHora2, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText(":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel1.add(jLabel10, gridBagConstraints);

        btnMinuto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/bolivia/resources/arrowdown.png"))); // NOI18N
        btnMinuto2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        jPanel1.add(btnMinuto2, gridBagConstraints);

        btnMinuto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/bolivia/resources/arrowup.png"))); // NOI18N
        btnMinuto1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel1.add(btnMinuto1, gridBagConstraints);

        btnAmPm1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/bolivia/resources/arrowup.png"))); // NOI18N
        btnAmPm1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        jPanel1.add(btnAmPm1, gridBagConstraints);

        btnAmPm2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/bolivia/resources/arrowdown.png"))); // NOI18N
        btnAmPm2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        jPanel1.add(btnAmPm2, gridBagConstraints);

        AmPm.setBackground(new java.awt.Color(255, 255, 255));
        AmPm.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        AmPm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AmPm.setText("00");
        AmPm.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(134, 148, 162)));
        AmPm.setOpaque(true);
        AmPm.setPreferredSize(new java.awt.Dimension(44, 44));
        AmPm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AmPmKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AmPmKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        jPanel1.add(AmPm, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jPanel1, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("SET TIME");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        add(jLabel1, gridBagConstraints);

        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCancel.setText("CANCEL");
        btnCancel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(134, 148, 162)));
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCancel.setPreferredSize(new java.awt.Dimension(99, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(btnCancel, gridBagConstraints);

        btnSet.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSet.setText("SET");
        btnSet.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(134, 148, 162)));
        btnSet.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSet.setPreferredSize(new java.awt.Dimension(99, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(btnSet, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void AmPmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AmPmKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AmPmKeyPressed

    private void AmPmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AmPmKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_AmPmKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AmPm;
    private javax.swing.JLabel btnAmPm1;
    private javax.swing.JLabel btnAmPm2;
    private javax.swing.JLabel btnCancel;
    private javax.swing.JLabel btnHora1;
    private javax.swing.JLabel btnHora2;
    private javax.swing.JLabel btnMinuto1;
    private javax.swing.JLabel btnMinuto2;
    private javax.swing.JLabel btnSet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public String getHora(){
        return Hora.getText();
    }
    
    private void HoraKeyReleased(java.awt.event.KeyEvent evt) {                                 
        // TODO add your handling code here:
        try{
            if(Hora.getText().length() <= 2){
                int hora = Integer.parseInt(Hora.getText());
                if(hora <= 12){

                }else{
                    Hora.setText("01");
                }
            } else{
                evt.consume();
            }
        } catch(Exception e){}
    }
    
    private void MinutoKeyReleased(java.awt.event.KeyEvent evt) {                                 
        // TODO add your handling code here:
        try{
            if(Minuto.getText().length() <= 2){
                int hora = Integer.parseInt(Minuto.getText());
                if(hora <= 59){

                }else{
                    Minuto.setText("01");
                }
            } else{
                evt.consume();
            }
        } catch(Exception e){}
    }
    
    private void HoraKeyTyped(java.awt.event.KeyEvent evt) {                               
        // TODO add your handling code here:
        char validar = evt.getKeyChar();
        
        if(validar < '0' || validar > '9'){
            evt.consume();
        }
    } 

    private void MinutoaKeyTyped(java.awt.event.KeyEvent evt) {                               
        // TODO add your handling code here:
        char validar = evt.getKeyChar();
        
        if(validar < '0' || validar > '9'){
            evt.consume();
        }
    }  
}
