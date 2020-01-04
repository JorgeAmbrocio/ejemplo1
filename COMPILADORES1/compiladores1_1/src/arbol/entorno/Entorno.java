/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.entorno;
 
import arbol.Expresion;
import arbol.expresiones.Acceso;
import arbol.expresiones.Id;
import arbol.expresiones.Literal;
import arbol.expresiones.Objeto;
import interfaz.Errores;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class Entorno {
    public Entorno anterior, global;
    public HashMap <String , Simbolo> tabla;
    
    public Entorno (Entorno anterior) {
        this.anterior = anterior;
        this.tabla = new HashMap<>();
    }
    
    public Entorno (Entorno anterior, Entorno global) {
        this.anterior = anterior;
        this.global = global;
        this.tabla = new HashMap<>();
    }
    
    // nombre : nombre de la variable a insertar
    public void insertar (String nombre, Simbolo sim, int linea, int columna, String cadenaError) {
        if (tabla.containsKey(nombre)) {
            System.out.println("Error semántico: " + cadenaError + " '" + nombre + "' ya existe. Línea:" + linea+ " Columna:" + columna);
            Errores errr = new Errores(Errores.enumTipoError.semantico , "Error semántico: " + cadenaError + " '" + nombre + "' ya existe. Línea:" + linea+ " Columna:" + columna);
            return;
        }
        
        tabla.put(nombre, sim);
    }
    
    public Simbolo buscar (String nombre, int linea, int columna, String cadenaError) {
        
        for (Entorno e = this; e != null; e = e.anterior) {
            if (e.tabla.containsKey(nombre)) {
                Simbolo sim = e.tabla.get(nombre);
                return sim;
            }
        }
        
        System.out.println("Error semántico: " + cadenaError + " '" + nombre + "' No existe en Linea:" + linea + " Columna:" + columna);
        Errores errr = new Errores(Errores.enumTipoError.semantico , "Error semántico: " + cadenaError + " '" + "' No existe en Linea:" + linea + " Columna:" + columna);
        return null;
    }
    
    
    
    
    public Simbolo buscar (Acceso nombre, int linea, int columna, String cadenaError) {
        
        Simbolo retorno = null;
        
        LinkedList<Id> aux = nombre.accesos;
        
        Entorno busqueda = this;
        
        // recorrer todos los id
        for (Id id : aux){
            Simbolo l = id.getSimbolo(busqueda); // obtiene el objeto en el entorno indicado
            //aux.pollFirst();
            
            //verificar si es el último elemento
            if (id == aux.getLast()) {
                // estamos en el ùltimo id
                // no debe ser un objeto
                retorno = l; // retorno del ojeto final
                break;
            }else { 
                // no es el último id
                // verifica sea de tipo objeto
                if (l.tipo.tipo != Tipo.EnumTipo.objeto) {
                    // eeror
                    Errores  err = new Errores(Errores.enumTipoError.semantico , "El valor del id " + id.id + " ya es de tipo primitivo no se le pueden adjudicar más capas de id.");
                    break;
                }
                
                // sí es de tipo objeto, ahora vamos a buscar en el entorno del objeto
                busqueda = ((Objeto)l.valor).global;
            }
            
        }
        
        return retorno;
    }
    
    public Entorno getEntornoAcceso (Acceso nombre) {
        Entorno retorno = null;
        
        LinkedList<Id> aux = nombre.accesos;
        
        if (aux.size() > 1) {
            aux.removeLast(); // elimina el último elemento
        }        
        
        Entorno busqueda = this;
        
        // recorrer todos los id
        for (Id id : aux){
            Simbolo l = id.getSimbolo(busqueda); // obtiene el objeto en el entorno indicado
            //aux.pollFirst();
            
            //verificar si es el último elemento
            if (id == aux.getLast()) {
                // estamos en el ùltimo id
                // no debe ser un objeto
                retorno = ((Objeto)l.valor).global; // retorno del ojeto final
                break;
            }else { 
                // no es el último id
                // verifica sea de tipo objeto
                if (l.tipo.tipo != Tipo.EnumTipo.objeto) {
                    // eeror
                    Errores  err = new Errores(Errores.enumTipoError.semantico , "El valor del id " + id.id + " ya es de tipo primitivo no se le pueden adjudicar más capas de id.");
                    break;
                }
                
                // sí es de tipo objeto, ahora vamos a buscar en el entorno del objeto
                busqueda = ((Objeto)l.valor).global;
            }
            
        }
        
        return retorno;
    }
    
}
