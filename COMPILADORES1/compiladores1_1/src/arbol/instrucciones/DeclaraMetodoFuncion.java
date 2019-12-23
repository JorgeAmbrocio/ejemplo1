/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Instruccion;
import arbol.entorno.Entorno;
import arbol.entorno.SimboloMF;
import arbol.entorno.Tipo;
import arbol.instrucciones.sentencias.Bloque;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class DeclaraMetodoFuncion  extends Instruccion{

    Tipo tipo;
    String nombre;
    LinkedList<Declaracion> parametros;
    Bloque bloque;

    public DeclaraMetodoFuncion(Tipo tipo, String nombre, LinkedList<Declaracion> parametros, Bloque bloque) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.parametros = parametros;
        this.bloque = bloque;
    }

    public DeclaraMetodoFuncion(Tipo tipo, String nombre, Bloque bloque) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.bloque = bloque;
    }
    
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        // crear el objeto símbolo 
        SimboloMF s = new SimboloMF (this.tipo, this.nombre);
        
        return null;
    }
    
}
