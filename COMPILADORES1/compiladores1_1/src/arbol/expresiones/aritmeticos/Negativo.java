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
public class Negativo extends Expresion {

    Expresion e ;

    public Negativo(Expresion e, int linea, int columna) {
        this.e = e;
        this.linea = linea;
        this.columna = columna;
    }
    
    
    
    @Override
    public Expresion getValor(Entorno ent) {
        
        Expresion e= this.e.getValor(ent);
        
        String str1 = e.valor.toString();
        
        switch (e.tipo.tipo ) {
            case entero :
                int entero_ = - Integer.parseInt(str1);
                return new Literal (new Tipo(Tipo.EnumTipo.entero), entero_) ;
                
            case doble:
                double doble_ = -Double.parseDouble(str1);
                return new Literal (new Tipo(Tipo.EnumTipo.entero), doble_) ;
            case caracter :
                int caracter_ = - str1.charAt(linea);
                return new Literal (new Tipo (Tipo.EnumTipo.entero) , caracter_);
        }
        
        Errores ee = new Errores(Errores.enumTipoError.semantico , "No se puede ejecutar el negativo de un tipo " + e.tipo.tipo.toString()) ;
        return new Literal (new Tipo(Tipo.EnumTipo.error) , "@ERROR@") ;
    }
    
}
