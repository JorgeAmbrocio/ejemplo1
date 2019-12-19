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
import interfaz.Errores;

/**
 *
 * @author David Ventura
 */
public class Modulo extends Expresion {

    Expresion izquierda;
    Expresion derecha;

    public Modulo(Expresion izquierda, Expresion derecha, int linea , int columna) {
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.linea = linea;
        this.columna = columna;
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
        
        Expresion e1 = this.izquierda.getValor(ent);
        Expresion e2 = this.derecha.getValor(ent);
        
        String str1 = e1.valor.toString();
        String str2 = e2.valor.toString();
        
        switch (e1.tipo.tipo) {
            case entero:
                switch (e2.tipo.tipo) {
                    case entero:
                        double modEntero  = (double ) Double.parseDouble(str1) % (double) Double.parseDouble(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble), modEntero) ;
                    case doble:
                        double modDoble  = (double ) Double.parseDouble(str1) % (double) Double.parseDouble(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble), modDoble) ;
                    case caracter:
                        double modCaracter  = (double ) Double.parseDouble(str1) % (double) str2.charAt(0);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble), modCaracter) ;
                }
                break;
            case doble:
                switch (e2.tipo.tipo) {
                    case entero:
                        double modEntero  = (double ) Double.parseDouble(str1) % (double) Double.parseDouble(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble), modEntero) ;
                    case doble:
                        double modDoble  = (double ) Double.parseDouble(str1) % (double) Double.parseDouble(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble), modDoble) ;
                    case caracter:
                        double modCaracter  = (double ) Double.parseDouble(str1) % (double) str2.charAt(0);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble), modCaracter) ;
                }
                break;
            case caracter:
                switch (e2.tipo.tipo) {
                    case entero:
                        double modEntero = (double) str1.charAt(0) % (double) Double.parseDouble(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble), modEntero);
                    case doble:
                        double modDoble = (double) str1.charAt(0) % (double) Double.parseDouble(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble), modDoble);
                    case caracter:
                        double modCaracter = (double) str1.charAt(0) % (double) str2.charAt(0) ;
                        return new Literal (new Tipo (Tipo.EnumTipo.doble), modCaracter);
                }
                break;
        }
        
       Errores errr = new Errores(Errores.enumTipoError.semantico , "No se pueden ejecutar el módulo con los tipos " + e1.tipo.tipo.toString() + "/" + e2.tipo.tipo.toString() + " Linea " + this.linea + " Columna "+  this.columna);
        return new Literal (new Tipo (Tipo.EnumTipo.error) , "@Error@") ;
    }
    
}
