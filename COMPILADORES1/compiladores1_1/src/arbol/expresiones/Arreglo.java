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
public class Arreglo extends Expresion {
    
    LinkedList<Object> contenido;
    int profundidad;
    int tamano;
    
    

    @Override
    public Expresion getValor(Entorno ent) {
        
        return null;
    }
    
}
