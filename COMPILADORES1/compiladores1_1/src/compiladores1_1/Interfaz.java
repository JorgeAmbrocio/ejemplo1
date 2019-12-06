/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores1_1;

import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class Interfaz extends javax.swing.JFrame {
    
    public static LinkedList<cError> lista_errores;
    
    public Interfaz () {
        lista_errores = new LinkedList<cError>();
    }
    
    
}
