/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.entorno;

import arbol.Instruccion;
import arbol.instrucciones.Declaracion;
import arbol.instrucciones.sentencias.Bloque;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class SimboloMF extends Simbolo {
    
    LinkedList<Declaracion> parametros;
    Bloque bloque;
    
    public SimboloMF(Tipo tipo, Object valor) {
        super(tipo, valor);
    }
    
    public void setDatos (LinkedList<Declaracion> parametros, Bloque bloque){
        this.parametros = parametros;
        this.bloque = bloque;
    }
    
    public void setDatos (Bloque bloque) {
        this.bloque = bloque;
    }    
    
    public Bloque getBloque(){ return this.bloque;}
    
    public LinkedList<Declaracion> getParametros () { return this.parametros;}
}
