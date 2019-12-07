/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones.aritmeticos;

import arbol.Expresion;
import arbol.entorno.Entorno;
import arbol.entorno.Tipo;
import arbol.expresiones.Literal;

/**
 *
 * @author David Ventura
 */
public class Division extends Expresion {

    Expresion izquierda;
    Expresion derecha;

    public Division(Expresion izquierda, Expresion derecha, int linea , int columna) {
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.linea = linea;
        this.columna= columna;
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
        
        Expresion e1 = this.izquierda.getValor(ent);
        Expresion e2 = this.derecha.getValor(ent);
        
        String str1 = e1.valor.toString();
        String str2 = e2.valor.toString();
        
        switch (e1.tipo.tipo) {
            case entero:
                switch (e2.tipo.tipo ){
                    case entero:
                        int divEntero  = Integer.parseInt(str1) / Integer.parseInt(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.entero) , divEntero);
                    case doble:
                        double divDoble = Integer.parseInt(str1) / Double.parseDouble(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble) , divDoble) ;
                    case caracter:
                        int divCaracter = Integer.parseInt(str1) / (int) str2.charAt(0);
                        return new Literal (new Tipo (Tipo.EnumTipo.entero), divCaracter);
                }
                break;
            case doble:
                switch (e2.tipo.tipo) {
                    case entero:
                        double divEntero =Double.parseDouble(str1) / Double.parseDouble(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble), divEntero);
                    case doble:
                        double divDoble =Double.parseDouble(str1) / Double.parseDouble(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble), divDoble);
                    case caracter:
                        double divCaracter =Double.parseDouble(str1) / (int) str2.charAt(0);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble), divCaracter);
                }
                break;
            case caracter:
                switch (e2.tipo.tipo) {
                    case entero:
                        int divEntero = (int) str1.charAt(0) / Integer.parseInt(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.entero), divEntero);
                    case doble:
                        double divDoble = (double) str1.charAt(0) / Double.parseDouble(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble), divDoble) ;
                    case caracter:
                        int divCaracter = (int) str1.charAt(0) / (int) str2.charAt(0);
                        return new Literal (new Tipo (Tipo.EnumTipo.entero), divCaracter);
                }
        }
        
        
        return new Literal (new Tipo (Tipo.EnumTipo.error) , "@@Error") ;
    }
    
}
