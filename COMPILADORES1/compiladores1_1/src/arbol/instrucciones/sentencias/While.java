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
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class While extends Instruccion {

    Bloque bloque;
    Expresion valorWhile;

    public While(Expresion valorWhile , Bloque bloque) {
        this.bloque = bloque;
        this.valorWhile = valorWhile;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
    
        Expresion valorWhile_ = this.valorWhile.getValor(ent);
        
        if (valorWhile_.tipo.tipo == Tipo.EnumTipo.booleano) {
            // verifica que el valor sea de tipo booleano
            
            
            boolean condicion = Boolean.parseBoolean(valorWhile_.valor.toString());
            
            while (condicion) {
                Entorno entornoWhile = new Entorno (ent);
                bloque.ejecutar(entornoWhile);
                
                valorWhile_  = this.valorWhile.getValor(ent);
                condicion = Boolean.parseBoolean(valorWhile_.valor.toString());
            }
            
        } else {
            System.out.println("Error sintáctico: se esperaba valro booleano");
        }
        return null;
    }
    
}
