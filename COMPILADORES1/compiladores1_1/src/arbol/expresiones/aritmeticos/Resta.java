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
                        
                }
        }
        
        
        
        return new Literal (new Tipo(Tipo.EnumTipo.error ) , "@Error@");
    }
    
}
