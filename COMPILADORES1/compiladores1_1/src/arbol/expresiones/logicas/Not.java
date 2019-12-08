/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones.logicas;

import arbol.Expresion;
import arbol.entorno.Entorno;
import arbol.entorno.Tipo;
import arbol.expresiones.Literal;

/**
 *
 * @author David Ventura
 */
public class Not extends Expresion {
    Expresion izquierdo;

    public Not(Expresion izquierda, int linea, int columna) {
        this.izquierdo = izquierda;
        this.linea  = linea;
        this.columna =columna;
    }
    
    
    
    @Override
    public Expresion getValor(Entorno ent) {
        
        Expresion e1 = this.izquierdo.getValor(ent);
        
        String str1 = e1.valor.toString();
        
        Boolean resultado;
        
        switch (e1.tipo.tipo) {
            case booleano:
                resultado = (!Boolean.parseBoolean(str1) ) ;
                return new Literal (new Tipo (Tipo.EnumTipo.booleano), resultado.toString());
        }
        
        return new Literal(new Tipo(Tipo.EnumTipo.error) , "@Error@");
    }
}
