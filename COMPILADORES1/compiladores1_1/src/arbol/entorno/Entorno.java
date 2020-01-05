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
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.JOptionPane;

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
    
    
    public void GraficarEntorno(){
        
        String c = "";
        
        c += "digraph g{\n";
        c += "  node[shape = record];\n";
        c += "  rankdir=LR;\n";
        
        for (Entorno e = this; e != null; e = e.anterior) {
            c += e.getContenido() + "\n";
            
            if (e.anterior != null) {
                
                String stre = e.toString().replace(".", "_").replace("@", "");
                String stre_ = e.anterior.toString().replace(".", "_").replace("@", "");
                
                c += stre + "->" + stre_ +"\n";
            }else {
                String stre = e.toString().replace(".", "_").replace("@", "");
                c += stre + "-> null\n";
            }
        }
        
        c += "}";
        
        try {
            FileWriter fw = new FileWriter("entorno.dot");
            PrintWriter pw = new PrintWriter(fw);
            pw.print(c);
            pw.close();
            fw.close();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo crear el reporte entorno", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        try {
            String comando = "dot entorno.dot -o entorno.png -Tpng";
            Runtime.getRuntime().exec(comando);
        }catch (Exception e){
           
        }
    }
    
    public String getContenido(){
        String c ="" ; 
        
        c += this.toString().replace(".", "_").replace("@", "") + "[label = \" ";
        
        int iterador = 0;
        
        for (Map.Entry<String, Simbolo> contenido : this.tabla.entrySet()) {
            String tipo ="Variable ";
            
            if (contenido.getKey().substring(0, 1).equals("#")) {
                tipo = "Metodo | Funcion ";
            }
            
            Simbolo s = contenido.getValue();
            Object object = s.valor;
            String valor = "";
            
            if (object.getClass() == Objeto.class){
                String [] vector = object.toString().split("@");
                valor = " Instancia: " + vector[vector.length - 1];
            }else {
                valor = object.toString();
            }
            
            
            c += "<f" + iterador + "> " + tipo + 
                    " de tipo: " + s.tipo.tipo + " " + s.tipo.tr +
                    " con valor: " + valor + " | ";
            iterador ++;
        }
        
        c += "";
        c += "<f" + (iterador ) + "> \"];\n" ;
        
        return c;
    }
    
}
