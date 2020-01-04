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
        
        String lastLetter = this.nombre.substring(0, 1);
        
        if (!lastLetter.equals("#")) {
            // si no se ha utilizado el nombre, se le añade un nuevo #
            this.nombre = "#" +  nombre ;
        }
        
        if (this.parametros != null) {
           
           // setear los datos
           s.setDatos(this.parametros, this.bloque);
           
           // obtener el nombre completo de la fución
           for (Declaracion declaracion : this.parametros) {
               this.nombre += declaracion.tipo.tipo.toString();
           }
           
        } else {
            s.setDatos(this.bloque);
        }
        
        if (this.tipo.tipo == Tipo.EnumTipo.metodo) {
            // si es un método
            ent.insertar(this.nombre, s, linea, columna, "El metodo");
        }else {
            // si es una funcion
            ent.insertar(this.nombre, s, linea, columna, "La funcion");
        }
        
        return null;
    }
    
}
