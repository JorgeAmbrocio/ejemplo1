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
import arbol.expresiones.Acceso;
import arbol.expresiones.Id;
import arbol.expresiones.Literal;
import interfaz.Errores;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class Asignacion extends Instruccion {
    
    String id;
    Acceso id_;
    Expresion valor;

    public Asignacion(String id, int linea, int columna, Expresion valor) {
        this.id = id;
        this.valor = valor;
        this.columna = columna;
        this.linea = linea;
    }
    
    public Asignacion(Acceso id, int linea, int columna, Expresion valor) {
        this.id_ = id;
        this.valor = valor;
        this.columna = columna;
        this.linea = linea;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        Simbolo sim = ent.buscar(id_, linea, columna, "La variable"); //Busco la variable en la tabla de símbolos
        
        //Simbolo sim = (Simbolo) this.id_.getValor(ent).valor;
        
        if (sim != null) { //Si la variable existe

            Expresion resultado = valor.getValor(ent);
            
            if (sim.tipo.dimension  != resultado.tipo.dimension) {
                // no coinciden en su tipo y dimension
                System.out.println("Error Semántico: " + "El tipo de dato que se le quiere asignar a la variable '" + id + "' es incorrecto. " + sim.tipo.tipo + "-" + sim.tipo.tr + " ARREGLO[ " + sim.tipo.dimension + " dimensiones]  " + resultado.tipo.tipo + "-" + resultado.tipo.tr  + " ARREGLO[ " + resultado.tipo.dimension + " dimensiones]" + ". Línea: " + linea + " Columna: " + columna);
                Errores errr = new Errores(Errores.enumTipoError.semantico , "Error Semántico: " + "El tipo de dato que se le quiere asignar a la variable '" + id + "' es incorrecto. " + sim.tipo + "-" + sim.tipo.tr + " ARREGLO[ " + sim.tipo.dimension + " dimensiones]   " + resultado.tipo.tipo + "-" + resultado.tipo.tr  + " ARREGLO[ " + resultado.tipo.dimension + " dimensiones]" + ". Línea: " + linea + " Columna: " + columna);
        
                return null;
            }
            

            switch (sim.tipo.tipo) { //Tipo de la variable
                case entero:
                    switch (resultado.tipo.tipo) {
                        case entero:
                            if (sim.valor.getClass() != Literal.class) {
                                sim.valor = resultado.valor;
                            }else{
                                Literal ll = (Literal) sim.valor;
                                ll.valor = resultado.valor;
                            }
                            return null;
                        case caracter:
                            int ascii = (int) resultado.valor.toString().charAt(0);
                            if (sim.valor.getClass() != Literal.class) {
                                sim.valor = ascii;
                            }else{
                                Literal ll = (Literal) sim.valor;
                                ll.valor = resultado.valor;
                            }
                            return null;
                    }
                    break;
                case doble:
                    switch (resultado.tipo.tipo) {
                        case caracter:
                            int ascii = (int) resultado.valor.toString().charAt(0);
                            if (sim.valor.getClass() != Literal.class) {
                                sim.valor = ascii;
                            }else{
                                Literal ll = (Literal) sim.valor;
                                ll.valor = resultado.valor;
                            }
                            return null;
                        case entero:
                        case doble:
                            if (sim.valor.getClass() != Literal.class) {
                                sim.valor = resultado.valor;
                            }else{
                                Literal ll = (Literal) sim.valor;
                                ll.valor = resultado.valor;
                            }
                            return null;
                    }
                    break;
                case caracter:
                    switch (resultado.tipo.tipo) {
                        case caracter:
                            if (sim.valor.getClass() != Literal.class) {
                                sim.valor = resultado.valor;
                            }else{
                                Literal ll = (Literal) sim.valor;
                                ll.valor = resultado.valor;
                            }
                            return null;
                    }
                    break;
                case booleano:
                    switch (resultado.tipo.tipo) {
                        case booleano:
                            if (sim.valor.getClass() != Literal.class) {
                                sim.valor = resultado.valor;
                            }else{
                                Literal ll = (Literal) sim.valor;
                                ll.valor = resultado.valor;
                            }
                            return null;
                    }
                    break;
                case cadena:
                    switch (resultado.tipo.tipo) {
                        case cadena:
                            if (sim.valor.getClass() != Literal.class) {
                                sim.valor = resultado.valor;
                            }else{
                                Literal ll = (Literal) sim.valor;
                                ll.valor = resultado.valor;
                            }
                            return null;
                    }
                    break;
                case objeto:
                    switch (resultado.tipo.tipo) {
                        case nulo:
                            if (sim.valor.getClass() != Literal.class) {
                                sim.valor = resultado.valor;
                            }else{
                                Literal ll = (Literal) sim.valor;
                                ll.valor = resultado.valor;
                            }
                            return null;
                        case objeto:
                            //verificar que sean el mismo tipo de objeto
                            if (sim.tipo.tr.equals(resultado.tipo.tr)) {
                                // sí son del mismo tipo
                                if (sim.valor.getClass() != Literal.class) {
                                sim.valor = resultado.valor;
                            }else{
                                Literal ll = (Literal) sim.valor;
                                ll.valor = resultado.valor;
                            }
                                return null;
                            }
                    }
            }

            //Si llega aquí el tipo de dato que se le quiere asignar a la variable es incorrecto
            System.out.println("El tipo de dato que se le quiere asignar a la variable '" + id  + "' es incorrecto. " + sim.tipo.tipo + " " + sim.tipo.tr  + " = " + resultado.tipo.tipo + " " + resultado.tipo.tr + ". Línea: " + linea + " Columna: " + columna);
            Errores errr = new Errores(Errores.enumTipoError.semantico , "El tipo de dato que se le quiere asignar a la variable '" + id + "' es incorrecto. " + sim.tipo.tipo + " = " + resultado.tipo.tipo + ". Línea: " + linea + " Columna: " + columna);
        
        } //Si la variable NO existe ya se marcó el error
        return null;
    }
    
    
    
    
}
