/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones.sentencias;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entorno.Entorno;
import arbol.entorno.Tipo;
import arbol.instrucciones.*;
import compiladores1_1.Compiladores1_1;
import interfaz.Errores;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class While extends Instruccion {

    Bloque bloque;
    Expresion valorWhile;
    
    public While(Expresion valorWhile , Bloque bloque, int fila, int columna) {
        this.bloque = bloque;
        this.valorWhile = valorWhile;
        this.linea = fila;
        this.columna  = columna;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        compiladores1_1.Compiladores1_1.pilaCiclos.addLast(Compiladores1_1.enumCiclo.Ciclo);
        
        Expresion valorWhile_ = this.valorWhile.getValor(ent);
        
        if (valorWhile_.tipo.tipo == Tipo.EnumTipo.booleano) {
            // verifica que el valor sea de tipo booleano
            
            boolean condicion = Boolean.parseBoolean(valorWhile_.valor.toString());
            
            do {
                Entorno entornoWhile = new Entorno (ent);
                Object retorno = bloque.ejecutar(entornoWhile);
                
                if(retorno != null) {
                    
                    if (retorno.getClass() == Break.class) {
                        // si es de tipo break, se debe salir del while
                        break;
                    }else if (retorno.getClass() == Continue.class)  {
                        // si es de tipo continue, se no se hace nada
                        // solo se deja cotinuar el programa
                        
                    }
                }
                
                
                valorWhile_  = this.valorWhile.getValor(ent);
                condicion = Boolean.parseBoolean(valorWhile_.valor.toString());
            } while (condicion);
            
        } else {
            System.out.println("Error sintáctico: se esperaba valro booleano");
            Errores e = new Errores(Errores.enumTipoError.sintactico , " se esperaba valor boleano en la condicion while. Fila " + this.linea );
        }
        
        /// verificar si pooll funciona igual que el pop
        compiladores1_1.Compiladores1_1.pilaCiclos.pollLast();
        return null;
    }
    
}
