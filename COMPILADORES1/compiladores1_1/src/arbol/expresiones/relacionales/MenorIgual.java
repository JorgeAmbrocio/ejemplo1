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
public class MenorIgual extends Expresion{
    
    Expresion izquierdo, derecho;

    public MenorIgual(Expresion izquierda, Expresion derecha, int linea, int columna) {
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
        
        Boolean mayor;
        
        switch (e1.tipo.tipo) {
            case entero:
                switch (e2.tipo.tipo) {
                    case entero:
                        mayor = Integer.parseInt(str1) <= Integer.parseInt(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.booleano), mayor.toString()) ;
                    case doble:
                        mayor = Double.parseDouble(str1) <= Double.parseDouble(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.booleano), mayor.toString()) ;
                    case caracter:
                        mayor = Integer.parseInt(str1) <= (int) str2.charAt(0);
                        return new Literal (new Tipo (Tipo.EnumTipo.booleano), mayor.toString()) ;
                }
                break;
            case doble:
                switch (e2.tipo.tipo) {
                    case entero:
                        mayor = Integer.parseInt(str1) <= Integer.parseInt(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.booleano), mayor.toString()) ;
                    case doble:
                        mayor = Double.parseDouble(str1) <= Double.parseDouble(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.booleano), mayor.toString()) ;
                    case caracter:
                        mayor = Integer.parseInt(str1) <= (int) str2.charAt(0);
                        return new Literal (new Tipo (Tipo.EnumTipo.booleano), mayor.toString()) ;
                }
                
                break;
            case caracter:
                switch (e2.tipo.tipo) {
                    case entero:
                        mayor = (int) str1.charAt(0) <= Integer.parseInt(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.booleano), mayor.toString()) ;
                    case doble:
                        mayor = (int) str1.charAt(0) <= Double.parseDouble(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.booleano), mayor.toString()) ;
                    case caracter:
                        mayor = (int) str1.charAt(0) <= (int) str2.charAt(0);
                        return new Literal (new Tipo (Tipo.EnumTipo.booleano), mayor.toString()) ;
                }
                break;
        }
        
        
        
        return new Literal(new Tipo(Tipo.EnumTipo.error) , "@Error@");
    }
}
