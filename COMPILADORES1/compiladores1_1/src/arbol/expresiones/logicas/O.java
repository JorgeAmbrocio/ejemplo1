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
import interfaz.Errores;

/**
 *
 * @author David Ventura
 */
public class O extends Expresion {

    Expresion izquierdo, derecho;

    public O(Expresion izquierda, Expresion derecha, int linea, int columna) {
        this.izquierdo = izquierda;
        this.derecho = derecha;
        this.linea  = linea;
        this.columna =columna;
    }
    
    
    
    @Override
    public Expresion getValor(Entorno ent) {
        
        Expresion e1 = this.izquierdo.getValor(ent);
        Expresion e2 = this.derecho.getValor(ent);
        
        String str1 = e1.valor.toString();
        String str2 = e2.valor.toString();
        
        Boolean resultado;
        
        switch (e1.tipo.tipo) {
            case booleano:
                Boolean resultado1 = Boolean.parseBoolean(str1);
                if (resultado1) {
                    // si el lado izquierdo es verdadero, no analizamos el resto
                    return new Literal (new Tipo (Tipo.EnumTipo.booleano), resultado1.toString());
                }
                switch (e2.tipo.tipo) {
                    case booleano:
                        resultado = Boolean.parseBoolean(str1) || Boolean.parseBoolean(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.booleano), resultado.toString());
                }
        }
        Errores errr = new Errores(Errores.enumTipoError.semantico , "No se pueden ejecutar O con los tipos " + e1.tipo.tipo.toString() + "-" + e2.tipo.tipo.toString() + " Linea " + this.linea + "Columan " +  this.columna);
        return new Literal(new Tipo(Tipo.EnumTipo.error) , "@Error@");
    }
    
}
