/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones.sentencias;

import arbol.Instruccion;
import arbol.entorno.Entorno;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class Bloque extends Instruccion {
    
    LinkedList <Instruccion> instrucciones;

    public Bloque(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public Bloque() {
        this.instrucciones = new LinkedList();
    }

    
    @Override
    public Object ejecutar(Entorno ent) {
        
        for (Instruccion instruccion : instrucciones) {
            instruccion.ejecutar(ent);
        }
        
        return null;
    }
    
    
    
}
