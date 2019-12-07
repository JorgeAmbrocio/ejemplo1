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

    public Asignacion(String id, int linea, int columna, Expresion valor) {
        this.id = id;
        this.valor = valor;
        this.columna = columna;
        this.linea = linea;
    }

    @Override
    public Object ejecutar(Entorno ent) {
        Simbolo sim = ent.buscar(id, linea, columna, "La variable"); //Busco la variable en la tabla de símbolos

        if (sim != null) { //Si la variable existe

            Expresion resultado = valor.getValor(ent);

            switch (sim.tipo.tipo) { //Tipo de la variable
                case entero:
                    switch (resultado.tipo.tipo) {
                        case entero:
                            sim.valor = resultado.valor;
                            return null;
                        case caracter:
                            int ascii = (int) resultado.valor.toString().charAt(0);
                            sim.valor = ascii;
                            return null;
                    }
                    break;
                case doble:
                    switch (resultado.tipo.tipo) {
                        case caracter:
                            int ascii = (int) resultado.valor.toString().charAt(0);
                            sim.valor = ascii;
                            return null;
                        case entero:
                        case doble:
                            sim.valor = resultado.valor;
                            return null;
                    }
                    break;
                case caracter:
                    switch (resultado.tipo.tipo) {
                        case caracter:
                            sim.valor = resultado.valor;
                            return null;
                    }
                    break;
                case booleano:
                    switch (resultado.tipo.tipo) {
                        case booleano:
                            sim.valor = resultado.valor;
                            return null;
                    }
                    break;
                case cadena:
                    switch (resultado.tipo.tipo) {
                        case cadena:
                            sim.valor = resultado.valor;
                            return null;
                    }
                    break;
            }

            //Si llega aquí el tipo de dato que se le quiere asignar a la variable es incorrecto
            System.out.println("El tipo de dato que se le quiere asignar a la variable '" + id + "' es incorrecto. " + sim.tipo.tipo + " = " + resultado.tipo.tipo + ". Línea: " + linea + " Columna: " + columna);

        } //Si la variable NO existe ya se marcó el error
        return null;
    }
    
    
    
    
}
