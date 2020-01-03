/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Instruccion;
import arbol.entorno.Entorno;
import arbol.entorno.Simbolo;
import arbol.entorno.SimboloClase;
import arbol.entorno.Tipo;
import arbol.instrucciones.sentencias.Bloque;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class DeclaracionClase extends Instruccion {

    public String nombre;
    public Bloque bloque;
    
    public Entorno imports;
    
    public DeclaracionClase(String nombre, Bloque bloque,int linea , int columna) {
        this.nombre = nombre;
        this.bloque = bloque;
        
        this.linea = linea;
        this.columna = columna;
        
    }
    
    public DeclaracionClase(String nombre, int linea, int columna) {
        this.nombre = nombre;
        this.bloque = null;
        this.linea = linea;
        this.columna  = columna;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        
        // ejecutar los imports del archivo para enlazar el entorno
        
        
    
        // crear un un nuevo simbolo que contiene un objeto clase
        
        Simbolo sc = new Simbolo (new Tipo (Tipo.EnumTipo.nulo) , this );
        ent.insertar(this.nombre, sc, linea, columna, "La clase ");
        return null;
    }
    
}
