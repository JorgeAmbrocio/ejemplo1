/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.entorno;

import arbol.Instruccion;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class SimboloClase extends Simbolo {
    
    LinkedList<Instruccion> instrucciones;
    
    
    public SimboloClase(Tipo tipo, Object valor) {
        super(tipo, valor);
    }
    
    
    
}
