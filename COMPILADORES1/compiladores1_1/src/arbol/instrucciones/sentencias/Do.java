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
import arbol.instrucciones.Break;
import arbol.instrucciones.Continue;
import arbol.instrucciones.Return;
import compiladores1_1.Compiladores1_1;

/**
 *
 * @author David Ventura
 */
public class Do extends Instruccion {

    Bloque bloque;
    Expresion valorWhile;

    public Do(Expresion valorWhile , Bloque bloque) {
        this.bloque = bloque;
        this.valorWhile = valorWhile;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        compiladores1_1.Compiladores1_1.pilaCiclos.addLast(Compiladores1_1.enumCiclo.Ciclo);
        
        Object retorno =null;
        
        Expresion valorWhile_ = this.valorWhile.getValor(ent);
        
        if (valorWhile_.tipo.tipo == Tipo.EnumTipo.booleano) {
            // verifica que el valor sea de tipo booleano
            
            boolean condicion = Boolean.parseBoolean(valorWhile_.valor.toString());
            
            do {
                Entorno entornoWhile = new Entorno (ent);
                retorno = bloque.ejecutar(entornoWhile);
                
                if(retorno != null) {
                    
                    if (retorno.getClass() == Break.class) {
                        // si es de tipo break, se debe salir del while
                        retorno = null; // pero no retorna el break, pues ya se salió de un ciclo
                        break;
                    }else if (retorno.getClass() == Continue.class)  {
                        // si es de tipo continue, se no se hace nada
                        // solo se deja cotinuar el programa
                        
                    }else if (retorno.getClass() == Return.class) {
                        // si el objeto es un return, se debe salir del ciclo y retornar el objeto
                        break;
                    }
                }
                
                
                valorWhile_  = this.valorWhile.getValor(ent);
                condicion = Boolean.parseBoolean(valorWhile_.valor.toString());
            } while (condicion);
            
        } else {
            System.out.println("Error sintáctico: se esperaba valro booleano");
        }
        
        /// verificar si pooll funciona igual que el pop
        compiladores1_1.Compiladores1_1.pilaCiclos.pollLast();
        return retorno;
    }
    
}
