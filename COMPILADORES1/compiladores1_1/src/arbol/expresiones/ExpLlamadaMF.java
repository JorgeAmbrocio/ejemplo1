/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones;

import arbol.Expresion;
import arbol.entorno.Entorno;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class ExpLlamadaMF extends Expresion {

    LinkedList <Expresion> e;
    String nombre;

    public ExpLlamadaMF(String nombre ,LinkedList<Expresion> e, int linea, int columna) {
        this.e = e;
        this.nombre = nombre;
        this.linea = linea;
        this.columna = columna;
    }
    
    public ExpLlamadaMF( String nombre, int linea , int columna) {
        this.nombre = nombre;
        this.linea = linea;
        this.columna = columna;
    }
    
    
    @Override
    public Expresion getValor(Entorno ent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
