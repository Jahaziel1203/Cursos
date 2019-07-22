/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Controlador.ControladorPrincipal;
import vista.VistaPrincipal;

/**
 *
 * @author ACER
 */
public class Principal {
    public static void main(String[] args) {
        VistaPrincipal v = new VistaPrincipal();
        //ModeloPrincipal m = new ModeloPrincipal();
        ControladorPrincipal c = new ControladorPrincipal(v);
        v.conectaControlador(c);
    }
}
