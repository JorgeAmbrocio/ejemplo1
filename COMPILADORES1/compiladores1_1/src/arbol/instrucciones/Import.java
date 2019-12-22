/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.AST;
import arbol.Instruccion;
import arbol.entorno.Entorno;
import interfaz.Errores;

/**
 *
 * @author David Ventura
 */
public class Import extends Instruccion {

    String archivo;
    Entorno e;
    public Import(String archivo, int linea , int columna) {
        this.archivo = archivo;
        this.linea = linea;
        this.columna = columna;
        
        // verificar si el nombre del archivo existe
        AST arbol =compiladores1_1.Compiladores1_1.obtenerArbol_(this.archivo);
        this.e = arbol.tablaGlobal;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        Entorno aux = ent;
        while (aux.anterior != null) {
            aux = aux.anterior;
        }
        
        // ya estamos en el entorno inicial
        aux.anterior =this.e;
        
        return null;
    }
    
}
