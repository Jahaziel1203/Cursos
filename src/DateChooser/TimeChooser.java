package DateChooser;

import Modelo.ModeloVistaAltaCurso;
import vista.VistaAltaCurso;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
/**
 * @web http://www.jc-mouse.net/
 * @author Mouse
 */
public class TimeChooser extends JTextField implements ComponentListener {
    VistaAltaCurso vis;
    ModeloVistaAltaCurso m;
    String s;

    //Dimensiones del componente
    private Dimension dimension = new Dimension(120,24);
    //Boton para abrir el panel selector de tiempo
    private final JButton button = new JButton();
    //menu emergente
    private JPopupMenu popup = new JPopupMenu();    
    private TimePanel timePanel;    
    
    /** Constructor de clase*/
    public TimeChooser(VistaAltaCurso v, String ss, ModeloVistaAltaCurso m)
    {
        s = ss;
        vis = v;
        this.m = m;
        //Propiedades del jtextfield
        setSize(dimension);
        //setBounds(200,200,130,30);
        //setPreferredSize(dimension);
        setText("");
        
        //propiedades del boton
        button.setText("");                                
        button.setContentAreaFilled(true);
        button.setMargin(new Insets(1, 1, 1, 1));
        button.setVisible(true);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setIcon(new ImageIcon(getClass().getResource("/imagenes/clock.png")));
        updateButton();
        add( button );//se añade el boton al jtextfield
        
        //eventos del boton
        button.addMouseListener( new MouseListener(){

            //cuando se realice un clic sobre el boton, aparecera el menu emergente
            @Override
            public void mouseClicked(MouseEvent e) {   
                if((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK){
                    popup.show( e.getComponent() , 0 , button.getParent().getSize().height );                    
                }                
            }

            @Override
            public void mousePressed(MouseEvent e) {/*...*/}

            @Override
            public void mouseReleased(MouseEvent e) {/*...*/}

            @Override
            public void mouseEntered(MouseEvent e) {/*...*/}

            @Override
            public void mouseExited(MouseEvent e) {/*...*/}
            
        });
                 
        setVisible(true);
        addComponentListener(this);    
        
        // Modificado **************************************************************************************************************************************
        timePanel = new TimePanel(this, vis, s, m);
        popup.add(timePanel);
    }
    
    /** Ajusta la dimension y posicion del boton X*/
    private void updateButton()
    {
        //la altura del boton sera la misma que la del jtextfield        
        button.setSize( new Dimension(24, this.getSize().height));
        button.setPreferredSize( new Dimension(24, this.getSize().height) );        
        //posicion - extremo derecho
        button.setLocation(getWidth()-button.getWidth(), 0);        
    }
    
    //Metodos abstractos de ComponentListener
    @Override
    public void componentResized(ComponentEvent e) {
        //cuando el jtextfield cambie de tamaño, el boton tambien lo hara respecto a la altura
        //el ancho del boton es fijo
        updateButton(); 
    }
    
    public String getHora(){
        return timePanel.getHora();
    }

    @Override
    public void componentMoved(ComponentEvent e) {/*...*/}

    @Override
    public void componentShown(ComponentEvent e) {/*...*/}

    @Override
    public void componentHidden(ComponentEvent e) {/*...*/}
    
}
