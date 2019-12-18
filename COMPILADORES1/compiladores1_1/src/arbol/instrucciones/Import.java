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
        String contenidoHoja = compiladores1_1.Compiladores1_1.getContenidoHoja(archivo);
        
        if (contenidoHoja.equals("")) {
            // si el contenido de lahoja es vacío, lo indica como errror
            System.out.println("No se ha encontrado el contenido de la hoja a importar\n");
            Errores e = new Errores(Errores.enumTipoError.ejecucion , "No se ha encontrado el contenido de la hoja a importar " + this.archivo);
        }else {
            // en caso de que exista la hoja que contiene el import
            // se debe analizar priemro esa hoja
            AST arbol =compiladores1_1.Compiladores1_1.obtenerArbol(contenidoHoja);
            this.e = arbol.tablaGlobal;
            
        }
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
