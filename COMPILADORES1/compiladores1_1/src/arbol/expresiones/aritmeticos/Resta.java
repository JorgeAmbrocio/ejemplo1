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
public class Resta extends Expresion {
    
    Expresion izquierda;
    Expresion derecha;

    public Resta(Expresion izquierda, Expresion derecha, int linea, int columna) {
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.linea = linea;
        this.columna = columna;
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
        
        Expresion ei = this.izquierda.getValor(ent);
        Expresion ed = this.derecha.getValor(ent);
        
        String str1 = ei.valor.toString();
        String str2 = ed.valor.toString();
        
        
        switch (ei.tipo.tipo) {
            case entero:
                switch (ed.tipo.tipo){
                    case entero:
                        int restaEntero = Integer.parseInt(str1) - Integer.parseInt(str2);
                        return new Literal (new Tipo(Tipo.EnumTipo.entero) , restaEntero);
                    case doble:
                        double restaDoble = Double.parseDouble(str1) - Double.parseDouble(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble) , restaDoble);
                    case caracter:
                        int restaCaracter = Integer.parseInt(str1) - (int) str2.charAt(0);
                        return new Literal (new Tipo (Tipo.EnumTipo.entero) , restaCaracter);
                }
                break;
            case doble:
                switch (ed.tipo.tipo) {
                    case entero:
                        double restaEntero = Double.parseDouble(str1) - Double.parseDouble(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble), restaEntero);
                    case doble :
                        double restaDoble = Double.parseDouble(str1) - Double.parseDouble(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble), restaDoble);
                    case caracter:
                        double restaCaracter = Double.parseDouble(str1) - (int) str2.charAt(0);
                        return new Literal (new Tipo(Tipo.EnumTipo.doble) , restaCaracter) ;
                }
                
            case caracter:
                switch (ed.tipo.tipo) {
                    case entero:
                        int restaEntero = (int) str1.charAt(0) - Integer.parseInt(str2); 
                        return new Literal (new Tipo (Tipo.EnumTipo.entero), restaEntero) ;
                    case doble:
                        double restaDoble = (int) str1.charAt(0) - Double.parseDouble(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble) , restaDoble);
                    case caracter:
                        int restaCaracter = (int) str1.charAt(0) - (int) str2.charAt(0);
                        return new Literal (new Tipo (Tipo.EnumTipo.entero), restaCaracter) ;
                }
        }
        
        
        Errores errr = new Errores(Errores.enumTipoError.semantico , "No se pueden ejecutar la división con los tipos " + ei.tipo.tipo.toString() + "-" + ed.tipo.tipo.toString() + " Linea " + this.linea + " Columna " +  this.columna);
        return new Literal (new Tipo(Tipo.EnumTipo.error ) , "@Error@");
    }
    
}
