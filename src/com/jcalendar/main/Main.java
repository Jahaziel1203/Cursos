/*********************************************************************
 * This software is Copyright (c) under LGPL license  see 
 * http://www.gnu.org/copyleft/lesser.html#SEC1 for more
 * information.
 * 
 * JCalendar is developed by Suman Mummaneni
 * Main.java has been created on 20 Feb, 2010.
 * 
 *********************************************************************/
package com.jcalendar.main;

import vista.VistaPrincipal;
import com.jcalendar.pane.calendar.CalendarPane;
import vista.VistaAltaCurso;
/**
 * The Class Main.
 */
public class Main {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
                VistaAltaCurso ventana = new VistaAltaCurso();
		CalendarPane c = new CalendarPane(ventana);
                //c.crearDosCalendarios();
                c.crearUnCalendario();
	}
}
