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
public class Multiplicacion extends Expresion {

    Expresion izquierda;
    Expresion derecha;

    public Multiplicacion(Expresion izquierda, Expresion derecha, int linea, int columna) {
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.linea = linea;
        this.columna  = columna;
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
        Expresion e1 = this.izquierda.getValor(ent);
        Expresion e2 = this.derecha.getValor(ent);
        
        String str1 = e1.valor.toString();
        String str2 = e2.valor.toString();
        
        switch (e1.tipo.tipo) {
            case entero :
                switch (e2.tipo.tipo) {
                    case entero :
                        int multiEntero = Integer.parseInt(str1) * Integer.parseInt(str2);
                        return new Literal(new Tipo (Tipo.EnumTipo.entero) , multiEntero);
                    case doble :
                        Double multiDoble = Integer.parseInt(str1) * Double.parseDouble(str2);
                        return new Literal(new Tipo (Tipo.EnumTipo.doble) , multiDoble);
                    case caracter :
                        int multiCaracter = Integer.parseInt(str1) * (int) str2.charAt(0);
                        return new Literal(new Tipo (Tipo.EnumTipo.entero) , multiCaracter);
                }
                break;
            case doble :
                switch (e2.tipo.tipo) {
                    case entero :
                        Double multiEntero = Double.parseDouble(str1) * Double.parseDouble(str2);
                        return new Literal(new Tipo (Tipo.EnumTipo.doble) , multiEntero);
                    case doble :
                        Double multiDoble = Double.parseDouble(str1) * Double.parseDouble(str2);
                        return new Literal(new Tipo (Tipo.EnumTipo.doble) , multiDoble);
                    case caracter :
                        Double multiCaracter = Double.parseDouble(str1) * (int)str2.charAt(0);
                        return new Literal(new Tipo (Tipo.EnumTipo.doble) , multiCaracter);
                }
                break;
            case caracter :
                switch (e2.tipo.tipo) {
                    case entero :
                        int multiEntero = (int) str1.charAt(0) * Integer.parseInt(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.entero), multiEntero);
                    case doble :
                        Double multiDoble = (int) str1.charAt(0) * Double.parseDouble(str2);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble) , multiDoble);
                    case caracter :
                        int multiCaracter = (int) str1.charAt(0) * (int) str2.charAt(0) ;
                        return new Literal (new Tipo (Tipo.EnumTipo.entero), multiCaracter) ;
                }
                break;
            
        }
        Errores errr = new Errores(Errores.enumTipoError.semantico , "No se pueden ejecutar la multiplicación con los tipos " + e1.tipo.tipo.toString() + "-" + e2.tipo.tipo.toString() + " Linea " + this.linea + " Columna " +  this.columna);
        return new Literal (new Tipo(Tipo.EnumTipo.error ) , "@Error@");
    }
    
}
