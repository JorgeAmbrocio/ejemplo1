/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entorno.Entorno;
import arbol.entorno.Simbolo;

/**
 *
 * @author David Ventura
 */
public class Asignacion extends Instruccion {
    
    String id;
    Expresion valor;

    public Asignacion(String id, Expresion valor, int linea, int columna) {
        this.id = id;
        this.valor = valor;
        this.columna = columna;
        this.linea = linea;
    }

    @Override
    public Object ejecutar(Entorno ent) {
        Simbolo sim =  ent.buscar(id, linea, columna, "La varibale");
         
        if (sim != null) {
            
            Expresion resultado = valor.getValor(ent);
            
            switch (sim.tipo.tipo) {
                
                case entero :
                    
                    switch (resultado.tipo.tipo){
                        case entero:
                            sim.valor = resultado.valor;
                            return null;
                            
                        case caracter:
                            int ascii = (int) resultado.valor.toString().charAt(linea);
                            sim.valor = ascii;
                            return null;
                    }
                    
                    
                    
                    break;
                    
                    
                
            }
            
            System.out.println("El tipo de dato es incorrecto para la variable" + this.id);
        }else {
            
        }
        
        return null;
    }
    
    
    
    
}
