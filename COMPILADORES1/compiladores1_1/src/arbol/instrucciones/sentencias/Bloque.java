/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones.sentencias;

import arbol.Instruccion;
import arbol.entorno.Entorno;
import arbol.instrucciones.Break;
import arbol.instrucciones.Continue;
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
            Object retorno = instruccion.ejecutar(ent);
            
            if (retorno != null ) {
                // si retorna un objeto de tipo break, entonces se deja de ejecutar el bloque
                return retorno;
            }
        }
        
        return null;
    }
    
    
    
}
