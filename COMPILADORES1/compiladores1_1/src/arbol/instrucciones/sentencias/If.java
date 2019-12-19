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
public class If extends Instruccion {

    LinkedList <CondicionIf> condiciones;
    Bloque bloqueElse ;

    public If(LinkedList<CondicionIf> instrucciones, Bloque bloqueElse) {
        this.condiciones = instrucciones;
        this.bloqueElse = bloqueElse;
    }

    public If(LinkedList<CondicionIf> instrucciones) {
        this.condiciones = instrucciones;
        //this.bloqueElse = bloqueElse;
    }

    
    
    @Override
    public Object ejecutar(Entorno ent) {
        
        for (CondicionIf condicion : condiciones) {
            Object retorno = condicion.ejecutar(ent);
            
            if (condicion.ejecutado) {
                return retorno;
            }   
        }
        
        if (this.bloqueElse != null) {
            Entorno nuevo  = new Entorno (ent);
            Object retorno  =bloqueElse.ejecutar(nuevo);
            return retorno;
        }
        
        return null;
    }
    
    
    
}
