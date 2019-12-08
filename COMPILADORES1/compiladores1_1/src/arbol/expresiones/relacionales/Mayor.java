/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones.relacionales;

import arbol.Expresion;
import arbol.entorno.Entorno;
import arbol.entorno.Tipo;
import arbol.expresiones.Literal;

/**
 *
 * @author David Ventura
 */
public class Mayor extends Expresion {
    
    Expresion izquierdo;
    Expresion derecho ;
    

    public Mayor( int linea , int columna, Expresion izquierdo, Expresion derecho) {
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.columna= columna;
        this.linea = linea;
    }
    
    
    @Override
    public Expresion getValor(Entorno ent) {
        
        
        
        
        return new Literal(new Tipo(Tipo.EnumTipo.error) , "@Error@");
    }
    
}
