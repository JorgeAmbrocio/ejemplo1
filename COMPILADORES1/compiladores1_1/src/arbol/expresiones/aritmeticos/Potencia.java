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
public class Potencia extends Expresion {

    Expresion izquierda ;
    Expresion derecha;

    public Potencia(Expresion izquierda, Expresion derecha, int linea , int columna) {
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.linea = linea;
        this.columna = columna;
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
    
        Expresion e1 = this.izquierda.getValor(ent);
        Expresion e2 = this.derecha.getValor(ent);
        
        String str1 =e1.valor.toString();
        String str2  = e2.valor.toString();
        
        switch (e1.tipo.tipo) {
            case entero:
                switch (e2.tipo.tipo) {
                    case entero:
                        double potEntero =  (double) Math.pow(Double.parseDouble(str1), Double.parseDouble(str2));
                        return new Literal (new Tipo (Tipo.EnumTipo.doble) , potEntero) ;
                    case doble :
                        double potDoble =  (double) Math.pow(Double.parseDouble(str1), Double.parseDouble(str2));
                        return new Literal (new Tipo (Tipo.EnumTipo.doble) , potDoble ) ;
                    case caracter:
                        double potCaracter =  (double) Math.pow(Double.parseDouble(str1), (double) str2.charAt(0) );
                        return new Literal (new Tipo (Tipo.EnumTipo.doble) , potCaracter ) ;
                }
                break;
            case doble:
                switch (e2.tipo.tipo) {
                    case entero:
                        double potEntero =  (double) Math.pow(Double.parseDouble(str1), Double.parseDouble(str2));
                        return new Literal (new Tipo (Tipo.EnumTipo.doble) , potEntero) ;
                    case doble :
                        double potDoble =  (double) Math.pow(Double.parseDouble(str1), Double.parseDouble(str2));
                        return new Literal (new Tipo (Tipo.EnumTipo.doble) , potDoble ) ;
                    case caracter:
                        double potCaracter =  (double) Math.pow(Double.parseDouble(str1), (double) str2.charAt(0) );
                        return new Literal (new Tipo (Tipo.EnumTipo.doble) , potCaracter ) ;
                }
                break;
            case caracter:
                switch (e2.tipo.tipo) {
                    case entero:
                        double potEntero = (double) Math.pow((double) str1.charAt(0), Double.parseDouble(str2));
                        return new Literal (new Tipo (Tipo.EnumTipo.doble) , potEntero ) ;
                    case doble :
                        double potDoble = (double) Math.pow((double) str1.charAt(0), Double.parseDouble(str2));
                        return new Literal (new Tipo (Tipo.EnumTipo.doble) , potDoble ) ;
                    case caracter:
                        double potCaracter = (double) Math.pow((double) str1.charAt(0), Double.parseDouble(str2));
                        return new Literal (new Tipo (Tipo.EnumTipo.doble) , potCaracter ) ;
                }
                break;
             
        }
        Errores errr = new Errores(Errores.enumTipoError.semantico , "No se pueden ejecutar la potencia con los tipos " + e1.tipo.tipo.toString() + "-" + e2.tipo.tipo.toString() + " Linea " + this.linea + " Columna " +  this.columna);
        return new Literal (new Tipo (Tipo.EnumTipo.error) , "@Error@") ;
    }
    
}
