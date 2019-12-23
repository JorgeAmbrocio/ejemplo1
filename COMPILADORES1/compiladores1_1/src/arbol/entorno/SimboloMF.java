/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.entorno;

import arbol.Instruccion;
import arbol.instrucciones.sentencias.Bloque;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class SimboloMF extends Simbolo {
    
    LinkedList<Instruccion> parametros;
    Bloque bloque;
    
    public SimboloMF(Tipo tipo, Object valor) {
        super(tipo, valor);
    }
    
    public void setTipo (LinkedList<Instruccion> parametros, Bloque bloque){
        this.parametros = parametros;
        this.bloque = bloque;
    }
    
    public void setTipo (Bloque bloque) {
        this.bloque = bloque;
    }    
    
    public Bloque getBloque(){ return this.bloque;}
    
    public LinkedList<Instruccion> getParametros () { return this.parametros;}
}
