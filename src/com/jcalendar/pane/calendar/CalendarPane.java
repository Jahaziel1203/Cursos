/*********************************************************************
 * This software is Copyright (c) under LGPL license  see 
 * http://www.gnu.org/copyleft/lesser.html#SEC1 for more
 * information.
 * 
 * JCalendar is developed by Suman Mummaneni
 * CalendarPane.java has been created on 27 April, 2010.
 * 
 *********************************************************************/
package com.jcalendar.pane.calendar;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimeZone;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import com.jcalendar.event.CalendarEvent;
import com.jcalendar.model.JCalModel;
import com.jcalendar.pane.clock.Clock;
import java.awt.Color;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vista.VistaAltaCurso;

/**
 * The Class CalendarPane.
 * @author ACER
 */
public class CalendarPane extends JPanel implements ListSelectionListener {
        
        private JButton aceptar;
	/** Serial version UID for this container. */
	private static final long serialVersionUID = -7714183535212294537L;

	/** The monthlist. */
	private JComboBox<String> monthlist;
	
	/** The year. */
	private JSpinner year;
	
	/** The month. */
	private JTable month;
	
	/** The top panel. */
	private JPanel topPanel;
	
	/** The model. */
	private JCalModel model;
	
	/** The listeners. */
	private ArrayList<CalendarSelectionListener> listeners;

        private VistaAltaCurso ventana;
        private JFrame frame;
	/**
	 * Instantiates a new calendar pane.
	 */
	public CalendarPane(VistaAltaCurso ventana) {
                this.ventana = ventana;
		listeners = new ArrayList<CalendarSelectionListener>();
		initPaneles();
		model = new JCalModel();
		initWidgets();
		setPanels();
                aceptar = new JButton("ACEPTAR");
                this.setVisible(true);
	}
	
	public CalendarPane(TimeZone timeZone){
		listeners = new ArrayList<CalendarSelectionListener>();
		initPaneles();
		model = new JCalModel(timeZone);
		initWidgets();
		setPanels();
	}

	/**
	 * Initializes the paneles.
	 */
	protected void initPaneles() {
		topPanel = new JPanel();
	}
        
        public JPanel getPanel(){
            return topPanel;
        }

	/**
	 * Initializes the widgets.
	 */
	protected void initWidgets() {
		monthlist = new JComboBox<String>(model.getMonthList());
		monthlist.setSelectedIndex(model.getCurrentMonth());
		monthlist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> box = (JComboBox<String>) e.getSource();
				if (box.getSelectedIndex() != model.getCurrentMonth()) {
					model.reInitModel(box.getSelectedIndex());
					notifyListeners(new CalendarEvent(monthlist, model
							.getCurrentMonth(), model.getCurrentYear(), model
							.getCurrentDay(), model.getCurrentDate()));
				}
			}
		});
		monthlist.setEditable(false);
                //monthlist.setBackground(Color.yellow);
                //monthlist.setForeground(Color.red);
		year = new JSpinner(model.getYearModel());
		year.setEditor(new JSpinner.NumberEditor(year,"#"));
		year.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				SpinnerModel spinnerModel = year.getModel();
				int value = Integer
						.parseInt(spinnerModel.getValue().toString());
				if (value != model.getCurrentYear()) {
					model.reInitModel(monthlist.getSelectedIndex(), value);
					notifyListeners(new CalendarEvent(monthlist, model
							.getCurrentMonth(), model.getCurrentYear(), model
							.getCurrentDay(), model.getCurrentDate()));
				}
			}

		});
		// year.setValue(model.getCurrentYear());
		month = new JTable(model);
		month.setCellSelectionEnabled(true);
		month.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//month.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setColumnWidth();
		month.getSelectionModel().addListSelectionListener(this);
		month.getColumnModel().getSelectionModel().addListSelectionListener(this);
		setLayout(new BorderLayout());
               // month.setBackground(Color.yellow);
	}

	/**
	 * Sets the column width.
	 */
	private void setColumnWidth() {
		TableColumn column = null;
		for (int i = 0; i < 7; i++) {
			column = month.getColumnModel().getColumn(i);
			column.setPreferredWidth(35);
		}
	}

        public void crearUnCalendario(){
            frame = new JFrame();
		CalendarPane pane = new CalendarPane(ventana);
                //******************************
                //************************
		pane.addCalendarSelectionListener(new CalendarSelectionListener() {
                    @Override
                    public void selectionChanged(CalendarEvent e) {
                        String fecha = "";
                        Date hoy = new Date();
                        if(e.getDate().compareTo(hoy) > 0 || e.getDate().equals(hoy)) {
                            if(e.getMonth() < 10)
                                fecha = e.getYear() + "/0" + e.getMonth() + "/";
                            else
                                fecha = e.getYear() + "/" + e.getMonth()+ "/";
                            if(e.getDay() < 10)
                            fecha += "0" + e.getDay();
                            else
                                fecha += e.getDay();
                        } else
                            JOptionPane.showMessageDialog(null, "Fecha invalida", "Error", JOptionPane.ERROR_MESSAGE);
                        ventana.getLIda().setText(fecha);
                    }
		});
		JPanel main = new JPanel(new BorderLayout());
		Clock clock = new Clock();
		frame.setDefaultLookAndFeelDecorated(true);
		
                //pane1.add(clock, BorderLayout.WEST);
		main.add(pane, BorderLayout.CENTER);
                main.add(aceptar,BorderLayout.SOUTH);
		main.repaint();
		frame.setContentPane(main);
		frame.setTitle("Calendario");
		frame.setBounds(200, 200, 500, 220);
                frame.getContentPane().setBackground(Color.green);
                frame.setVisible(true);
            //clock.startClock();
                
        }
        
        public void crearUnCale(){
            frame = new JFrame();
		CalendarPane pane = new CalendarPane(ventana);
                //******************************
                //************************
		pane.addCalendarSelectionListener(new CalendarSelectionListener() {
                    @Override
                    public void selectionChanged(CalendarEvent e) {
                        String fecha = "";
                        Date hoy = new Date();
                        System.out.println(hoy + "/n" + e.getDate());
                        if(e.getDate().compareTo(hoy) > 0 || e.getDate().equals(hoy)) {
                            if(e.getDay() < 10)
                            fecha = "0" + e.getDay() + "/";
                            else
                                fecha = e.getDay() + "/";
                            if(e.getMonth() < 10)
                                fecha += "0" + e.getMonth() + "/" + e.getYear();
                            else
                                fecha += e.getMonth() + "/" + e.getYear();
                        } else
                            JOptionPane.showMessageDialog(null, "Fecha invalida", "Error", JOptionPane.ERROR_MESSAGE);
                        ventana.getFechaF().setText(fecha);
                    }
		});
		JPanel main = new JPanel(new BorderLayout());
		Clock clock = new Clock();
		frame.setDefaultLookAndFeelDecorated(true);
		
                //pane1.add(clock, BorderLayout.WEST);
		main.add(pane, BorderLayout.CENTER);
                main.add(aceptar,BorderLayout.SOUTH);
		main.repaint();
		frame.setContentPane(main);
		frame.setTitle("Calendario");
		frame.setBounds(200, 200, 500, 220);
                frame.getContentPane().setBackground(Color.green);
                frame.setVisible(true);
            //clock.startClock();
                
        }
        
        public JFrame getJFrame()
        {
            return frame;
        }
	/**
	 * Sets the panels.
	 */
	protected void setPanels() {
		topPanel.add(monthlist);
		topPanel.add(year);
		add(topPanel, BorderLayout.NORTH);
		JScrollPane pane = new JScrollPane(month);
                //pane.setBackground(Color.yellow);
		add(pane, BorderLayout.CENTER);
	}

	/**
	 * Adds the calendar selection listener.
	 * 
	 * @param l
	 *            the l
	 */
	public void addCalendarSelectionListener(CalendarSelectionListener l) {
		listeners.add(l);
	}

	/**
	 * Removes the calendar selection listener.
	 * 
	 * @param l
	 *            the l
	 */
	public void removeCalendarSelectionListener(CalendarSelectionListener l) {
		if (listeners.contains(l)) {
			listeners.remove(l);
		}
	}

	/**
	 * Notify listeners.
	 * 
	 * @param e
	 *            the e
	 */
	private void notifyListeners(CalendarEvent e) {
		if (listeners != null && listeners.size() > 0) {
			for (int i = 0; i < listeners.size(); i++) {
				listeners.get(i).selectionChanged(e);
			}
		}
	}

	/**
	 * This provides the implementation of the <code>ListSelectionListener</code>
	 * Fires an event when user selects a date in the table view.
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {

		if (e.getValueIsAdjusting()) {
			return;
		}
		int row = month.getSelectedRow();
		int col = month.getSelectedColumn();
		String date = (String) month.getValueAt(row, col);
		if (date != null && !date.equals(" ") && !date.equals("")) {
			model.setDay(Integer.parseInt(date));
			notifyListeners(new CalendarEvent(month, model.getCurrentMonth(),
					model.getCurrentYear(), model.getCurrentDay(), model
							.getCurrentDate()));
		}
	}

	/**
	 * This method basically set the <code>JTable</code> grid on and off
	 * by default the grid is set to true. If user want to set it false
	 * this API can be used. Note: this API will also repaint the <code>
	 * JTable/<code>
	 * @param gridLook
	 */
	public void setTableGridLook(boolean gridLook){
		month.setShowGrid(gridLook);
		month.repaint();
	}	
        
        public void conectaControlador(Controlador.ControladorCalendario c  )
        {
            aceptar.addActionListener(c);
            aceptar.addKeyListener(c);
            aceptar.setActionCommand("ACEPTAR");
        }
}
