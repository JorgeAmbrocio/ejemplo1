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
import arbol.instrucciones.Asignacion;
import arbol.instrucciones.Break;
import arbol.instrucciones.Continue;
import compiladores1_1.Compiladores1_1;

/**
 *
 * @author David Ventura
 */
public class For  extends Instruccion{

    Instruccion inicializacion;
    Expresion condicion;
    //Instruccion actualizacion;
    Expresion actualizacion;
    Bloque bloque;

    public For(Instruccion inicializacion, Expresion condicion, Expresion actualizacion, Bloque bloque) {
        this.inicializacion = inicializacion;
        this.condicion = condicion;
        this.actualizacion = actualizacion;
        this.bloque = bloque;
    }
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        compiladores1_1.Compiladores1_1.pilaCiclos.addLast(Compiladores1_1.enumCiclo.Ciclo);
        Entorno entCondiciones = new Entorno (ent);
        Object retorno;
        
        // ejecutar la inicialización de ciclo for
        this.inicializacion.ejecutar(entCondiciones);
        
        // ejecutar la condicion
        Expresion condicion_ = this.condicion.getValor(entCondiciones);
        
        // validar si se cumple la condicion
        if (condicion_.tipo.tipo == Tipo.EnumTipo.booleano) {
            // sí es un valor booleano
            
            boolean condicion__ = Boolean.parseBoolean(condicion_.valor.toString());
            while (condicion__) {
                // sí es válida la condición, ejecutar el bloque en un entorno nuevo
                Entorno entBloque = new Entorno (entCondiciones);
                retorno = this.bloque.ejecutar(entBloque);
                
                /// verificar si viene un break o un contirnue
                if  (retorno != null ) {
                    
                    if (retorno.getClass() == Break.class) {
                        // si viene un break, se debe terminar el prceso del for
                        break;
                    }else if (retorno.getClass() == Continue.class) {
                        // si viene un continue, no se hace nada
                        
                    }        
                }
                
                // luego de ejecutar el bloque
                // ejecutar la actualización en el entorno de condiciones
                this.actualizacion.getValor(entCondiciones);
                
                // revalidar la condicion
                condicion_ = this.condicion.getValor(entCondiciones);
                condicion__ = Boolean.parseBoolean(condicion_.valor.toString());
            }
        }else {
            System.out.println("Se esperaba una expresion booleana en la condición del for");
        }
        
        compiladores1_1.Compiladores1_1.pilaCiclos.pollLast();
        return null;
    }
    
}
