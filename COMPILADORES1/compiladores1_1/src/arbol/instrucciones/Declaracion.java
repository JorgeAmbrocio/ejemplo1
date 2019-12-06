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

/**
 *
 * @author David Ventura
 */
public class Declaracion extends Instruccion {

    public Tipo tipo;
    public String id;
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
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        
        if (valor != null) {
            Expresion resultado = valor.getValor(ent);
            Simbolo simbolo;
            
            switch (tipo.tipo) { // variable a delcarar
                case entero:
                    
                    
                    switch (resultado.tipo.tipo){ // varibale del retorno para saber si si puedo asignarlo
                        case entero:
                            simbolo = new Simbolo(tipo, resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La variable fue insertada");
                            return null;
                        
                        case caracter:
                            int ascii = (int) resultado.valor.toString().charAt(0);
                            simbolo = new Simbolo (tipo , ascii);
                            ent.insertar(id, simbolo, linea, columna, "La variable ");
                            return null;
                    }
                    
                    break;
                
                case doble :
                    
                    switch (resultado.tipo.tipo) { // variable del retrno para saber si lepuedo asgnar el valor
                        case doble:
                            simbolo = new Simbolo(tipo, resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La variale fue insertada");
                            return null;
                        case entero:
                            simbolo = new Simbolo (tipo , resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La variable fue insertada");
                    }
               
            } 
            
            /// si salimos del switch, significa que no coinciden los tipos
            System.out.println("No se puee asignar el tipo porque no coincide" + tipo.toString() + "=" + resultado.tipo.toString());
        }else {
            
            switch (tipo.tipo){
                case entero:
                    ent.insertar(id, new Simbolo(tipo, 0), linea, columna, id);
                    break;
                case caracter:
                    ent.insertar(id, new Simbolo(tipo, '\0'), linea, columna, id);
                    break;
                case booleano:
                    ent.insertar(id, new Simbolo(tipo, false), linea, columna, id);
                    break;
                case doble:
                    ent.insertar(id, new Simbolo(tipo, 0.0), linea, columna, id);
                    break;
                case cadena:
                    ent.insertar(id, new Simbolo(tipo, ""), linea, columna, id);
                    break;
                
            }
            
            
        }
        
        return null;
    }
    
    
}
