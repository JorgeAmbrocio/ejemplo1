package arbol;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David Ventura
 */
import arbol.entorno.Entorno;
import arbol.instrucciones.DeclaracionClase;
import arbol.instrucciones.Import;
import java.util.LinkedList;
public class AST {
    
    public LinkedList<Instruccion> lista_instrucciones;
    public Entorno tablaGlobal;

    public AST(LinkedList<Instruccion> lista_instrucciones) {
        this.lista_instrucciones = lista_instrucciones;
        tablaGlobal = new Entorno(null);
        tablaGlobal.global = tablaGlobal;
    }
    
    public void Ejecutar () {
        
        DeclaracionClase clase = null;
        
        for (Instruccion instruccion : lista_instrucciones) {
            if (instruccion.getClass() == Import.class) {
                // ejecutar todos los imports
                instruccion.ejecutar(tablaGlobal);
            }
        }
        
        for (Instruccion instruccion : lista_instrucciones) {
            if (instruccion.getClass() != Import.class) {
                // ejecutar todos los imports
                instruccion.ejecutar(tablaGlobal);
                
                if (instruccion.getClass() == DeclaracionClase.class) {
                    clase =(DeclaracionClase) instruccion;
                }
                
            }
        }
        
        //luego de ejecutar todas las sntencias
        // ejecutar el main del archivo 
        if (true && clase != null){
            // ejecutar main
            clase.getObjeto(tablaGlobal);
        }
        
        
    }
    
    
    
}
