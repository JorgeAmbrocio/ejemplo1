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
import arbol.entorno.Tipo;
import arbol.expresiones.Acceso;
import arbol.expresiones.Id;
import arbol.expresiones.Literal;
import interfaz.Errores;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class Declaracion extends Instruccion {

    public Tipo tipo;
    public String id;
    public Acceso id_;
    public Expresion valor;

    public Declaracion(Tipo tipo, String id, Expresion valor, int linea, int columna) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }
    
    public Declaracion(Tipo tipo, String id, int linea, int columna) {
        this.tipo = tipo;
        this.id = id;
        this.valor = null;
        this.linea = linea;
        this.columna = columna;
    }
    
    public Declaracion(Tipo tipo, Acceso id, Expresion valor, int linea, int columna) {
        this.tipo = tipo;
        this.id_ = id;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        if (valor != null) { //Si se le asignó valor a la variable
            Expresion resultado = valor.getValor(ent);

            Simbolo simbolo;
            
            if (this.tipo.dimension != resultado.tipo.dimension) {
                // no coinciden en su tipo y dimension
                System.out.println("Error Semántico: " + "El tipo de dato que se le quiere asignar a la variable '" + id + "' es incorrecto. " + tipo.tipo + "-" + tipo.tr + " ARREGLO[ " + this.tipo.dimension + " dimensiones]  " + resultado.tipo.tipo + "-" + resultado.tipo.tr  + " ARREGLO[ " + resultado.tipo.dimension + " dimensiones]" + ". Línea: " + linea + " Columna: " + columna);
                Errores errr = new Errores(Errores.enumTipoError.semantico , "Error Semántico: " + "El tipo de dato que se le quiere asignar a la variable '" + id + "' es incorrecto. " + tipo.tipo + "-" + tipo.tr + " ARREGLO[ " + this.tipo.dimension + " dimensiones]   " + resultado.tipo.tipo + "-" + resultado.tipo.tr  + " ARREGLO[ " + resultado.tipo.dimension + " dimensiones]" + ". Línea: " + linea + " Columna: " + columna);
        
                return null;
            }
            
            switch (tipo.tipo) { //Tipo de la variable
                case entero:
                    switch (resultado.tipo.tipo) {
                        case entero:
                            simbolo = new Simbolo(tipo, resultado.valor);
                            if (simbolo.valor.getClass() != Literal.class) {
                                simbolo = new Simbolo(tipo, resultado.valor);
                            }else{
                                Literal ll = (Literal) simbolo.valor;
                                simbolo = new Simbolo(tipo, ll.valor);
                            }
                            ent.insertar(id, simbolo, linea, columna, "La variable ");
                            return null;
                        case caracter:
                            int ascii = (int) resultado.valor.toString().charAt(0);
                            simbolo = new Simbolo(tipo, ascii);
                            ent.insertar(id, simbolo, linea, columna, "La variable ");
                            return null;
                    }
                    break;
                case doble:
                    switch (resultado.tipo.tipo) {
                        case caracter:
                            int ascii = (int) resultado.valor.toString().charAt(0);
                            simbolo = new Simbolo(tipo, ascii);
                            ent.insertar(id, simbolo, linea, columna, "La variable ");
                            return null;
                        case entero:
                        case doble:
                            simbolo = new Simbolo(tipo, resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La variable ");
                            return null;
                    }
                    break;
                case caracter:
                    switch (resultado.tipo.tipo) {
                        case caracter:
                            simbolo = new Simbolo(tipo, resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La variable ");
                            return null;
                    }
                    break;
                case booleano:
                    switch (resultado.tipo.tipo) {
                        case booleano:
                            simbolo = new Simbolo(tipo, resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La variable ");
                            return null;
                    }
                    break;
                case cadena:
                    switch (resultado.tipo.tipo) {
                        case cadena:
                            simbolo = new Simbolo(tipo, resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La variable ");
                            return null;
                    }
                    break;
                case objeto:
                    switch (resultado.tipo.tipo) {
                        case nulo:
                            // el valor que se le desea asignar a un objeto es nulo
                            simbolo = new Simbolo(tipo, resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La variable");
                        case objeto:
                            if (this.tipo.tr.equals(resultado.tipo.tr)) {
                                simbolo = new Simbolo(tipo, resultado.valor);
                                ent.insertar(id, simbolo, linea, columna, "La variable ");
                                return null;
                            }
                    }
                    break;
            }

            //Si llega aquí es porque hubo error de tipos
//            proyecto1.Interfaz.lista_errores.add(new CError("Semántico", "El tipo de dato que se le quiere asignar a la variable '" + id + "' es incorrecto. " + tipo.tipo + " = " + resultado.tipo.tipo, linea, columna));
            System.out.println("Error Semántico: " + "El tipo de dato que se le quiere asignar a la variable '" + id + "' es incorrecto. " + tipo.tipo + "-" + tipo.tr + " = " + resultado.tipo.tipo + "-" + resultado.tipo.tr + ". Línea: " + linea + " Columna: " + columna);
            Errores errr = new Errores(Errores.enumTipoError.semantico , "Error Semántico: " + "El tipo de dato que se le quiere asignar a la variable '" + id + "' es incorrecto. " + tipo.tipo + "-" + tipo.tr + " = " + resultado.tipo.tipo + "-" + resultado.tipo.tr + ". Línea: " + linea + " Columna: " + columna);
        
        } else { //Si no se le asignó valor a la variable le pongo uno por defecto
            
            if (this.tipo.dimension > 0) {
                // es un arreglo y se debe iniciar con valor null
                ent.insertar(id, new Simbolo(this.tipo, Tipo.EnumTipo.nulo), linea, columna, "La variable ");
                return null;
            }
            
            switch (tipo.tipo) {
                case entero:
                    ent.insertar(id, new Simbolo(tipo, 0), linea, columna, "La variable");
                    break;
                case caracter:
                    ent.insertar(id, new Simbolo(tipo, '\0'), linea, columna, "La variable");
                    break;
                case booleano:
                    ent.insertar(id, new Simbolo(tipo, false), linea, columna, "La variable");
                    break;
                case doble:
                    ent.insertar(id, new Simbolo(tipo, 0.0), linea, columna, "La variable");
                    break;
                case cadena:
                    ent.insertar(id, new Simbolo(tipo, ""), linea, columna, "La variable");
                    break;
                case objeto:
                    ent.insertar(id, new Simbolo(tipo, Tipo.EnumTipo.nulo), linea, columna, "La variable");
                    break;
            }
        }
        return null;
    }

    
}
