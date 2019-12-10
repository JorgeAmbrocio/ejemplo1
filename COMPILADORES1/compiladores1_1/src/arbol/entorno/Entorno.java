/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.entorno;
 
import java.util.HashMap;

/**
 *
 * @author David Ventura
 */
public class Entorno {
    public Entorno anterior;
    public HashMap <String , Simbolo> tabla;
    
    public Entorno (Entorno anterior) {
        this.anterior = anterior;
        this.tabla = new HashMap<>();
    }
    
    
    // nombre : nombre de la variable a insertar
    public void insertar (String nombre, Simbolo sim, int linea, int columna, String cadenaError) {
        if (tabla.containsKey(nombre)) {
            System.out.println("Error semántico: " + cadenaError + " '" + nombre + "' ya existe. Línea:" + linea+ " Columna:" + columna);
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
        
        System.out.println("Error semántico: " + cadenaError + " '" + "' No existe en Linea:" + linea + " Columna:" + columna);
        return null;
    }
    
    
    
    
}
