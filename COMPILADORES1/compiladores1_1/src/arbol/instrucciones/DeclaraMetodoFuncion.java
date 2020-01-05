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
import arbol.expresiones.Acceso;
import arbol.instrucciones.sentencias.Bloque;
import java.util.LinkedList;


/**
 *
 * @author David Ventura
 */
public class DeclaraMetodoFuncion  extends Instruccion{

    Tipo tipo;
    String nombre;
    Acceso nombre_;
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
    
    public DeclaraMetodoFuncion(Acceso nombre, Bloque bloque) {
        this.tipo = new Tipo(Tipo.EnumTipo.metodo);
        this.nombre_ = nombre;
        this.nombre = null;
        this.bloque = bloque;
    }
    
    public DeclaraMetodoFuncion(Acceso nombre, LinkedList<Declaracion> parametros, Bloque bloque) {
        this.tipo = new Tipo(Tipo.EnumTipo.metodo);
        this.nombre_ = nombre;
        this.nombre = null;
        this.parametros = parametros;
        this.bloque = bloque;
    }
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        
        // verificar si el el nombre es limpio
        // si lo es, entonces se le da el nombre que existe en el acceso
        if (this.nombre == null) {
            this.nombre = this.nombre_.accesos.getFirst().id;
        }
        
        String strNombre = new String(this.nombre);
        
        // crear el objeto símbolo 
        SimboloMF s = new SimboloMF (this.tipo, strNombre);
        
        String lastLetter = strNombre.substring(0, 1);
        
        if (!lastLetter.equals("#")) {
            // si no se ha utilizado el nombre, se le añade un nuevo #
            strNombre = "#" +  strNombre ;
        }
        
        if (this.parametros != null) {
           
           // setear los datos
           s.setDatos(this.parametros, this.bloque);
           
           // obtener el nombre completo de la fución
           for (Declaracion declaracion : this.parametros) {
               strNombre += declaracion.tipo.tipo.toString();
           }
           
        } else {
            s.setDatos(this.bloque);
        }
        
        if (this.tipo.tipo == Tipo.EnumTipo.metodo) {
            // si es un método
            ent.insertar(strNombre, s, linea, columna, "El metodo ");
        }else {
            // si es una funcion
            ent.insertar(strNombre, s, linea, columna, "La funcion ");
        }
        
        return null;
    }
    
}
