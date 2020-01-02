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

/**
 *
 * @author David Ventura
 */
public class InsIncremento extends Instruccion {

    String id;
    
    Acceso id_;

    public InsIncremento(String id, int linea, int columna) {
        this.id = id;
        this.linea = linea;
        this.columna= columna;
    }
    
    public InsIncremento(Acceso id, int linea, int columna) {
        this.id_ = id;
        this.linea = linea;
        this.columna= columna;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        
        Object retorno  = null;
        Simbolo e = ent.buscar(id_, linea, columna, "La variable"); //Busco la variable en la tabla de símbolos
        Asignacion a;
        switch (e.tipo.tipo) {
            case entero:
               int valor = Integer.parseInt(e.valor.toString());
               valor++;
               a = new Asignacion (this.id_ , this.linea , this.columna , new Literal(new Tipo (Tipo.EnumTipo.entero) , valor ));
               a.ejecutar(ent);
               return null;
            case doble:
                double valor2 = Double.parseDouble(e.valor.toString());
                valor2++; 
                a = new Asignacion (this.id_ , this.linea , this.columna , new Literal(new Tipo (Tipo.EnumTipo.doble) , valor2 ));
                a.ejecutar(ent);
                return null;
            case caracter:
                char valor3 = e.valor.toString().charAt(0);
                valor3 ++ ;
                a = new Asignacion (this.id_ , this.linea , this.columna , new Literal(new Tipo (Tipo.EnumTipo.caracter) , valor3 ));
                a.ejecutar(ent);
                return null;
        }
        
        System.out.println("error");
        return null;
    }
    
}
