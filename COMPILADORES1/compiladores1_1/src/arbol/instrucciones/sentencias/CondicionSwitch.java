/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones.sentencias;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entorno.Entorno;
import arbol.entorno.Tipo;
import arbol.expresiones.relacionales.Igual;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class CondicionSwitch extends Instruccion {

    LinkedList<Instruccion> bloque;
    Expresion valorCaso;
    boolean ejecutado;

    public CondicionSwitch( Expresion valor, LinkedList<Instruccion> bloque) {
        this.bloque = bloque;
        this.valorCaso = valor;
        this.ejecutado = false;
    }
    
    public CondicionSwitch(  LinkedList<Instruccion> bloque) {
        this.bloque = bloque;
        this.valorCaso = null;
        this.ejecutado = false;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        
        for (Instruccion instruccion : bloque) {
            Object retorno = instruccion.ejecutar(ent);
            
            if (retorno != null ) {
                
                return retorno;
            }
        }
        return null;
    }
    
}
