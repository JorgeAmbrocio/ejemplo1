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
public class Suma extends Expresion {

    Expresion izquierdo;
    Expresion derecho ;
    

    public Suma( int linea , int columna, Expresion izquierdo, Expresion derecho) {
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.columna= columna;
        this.linea = linea;
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
        
        Expresion s1 = this.izquierdo.getValor(ent);
        Expresion s2 = this.derecho.getValor(ent);
        
        String strS1 = s1.valor.toString();
        String strS2 = s2.valor.toString();
                        
        
        switch (s1.tipo.tipo) {
            
            case entero :
                
                switch (s2.tipo.tipo) {
                    case entero:
                        
                        int suma = Integer.parseInt(strS1) + Integer.parseInt(strS2);
                        return new Literal ( new Tipo (Tipo.EnumTipo.entero) , suma );
                    
                    case doble:
                        
                        Double sumaDoble = Double.parseDouble(strS1) + Double.parseDouble(strS2);
                        return new Literal ( new Tipo (Tipo.EnumTipo.doble) , sumaDoble);
                    
                    case caracter:
                        
                        int sumaCaracter = Integer.parseInt(strS1) + (int) strS2.charAt(0);
                        return new Literal (new Tipo(Tipo.EnumTipo.entero), sumaCaracter) ;
                    
                    case cadena:
                        
                        String sumaCadena  = strS1 + strS2;
                        return new Literal (new Tipo(Tipo.EnumTipo.cadena), sumaCadena);
                    
                }
                
                
                break;
            case doble :
                
                switch (s2.tipo.tipo) {
                    case entero:
                        
                        Double suma = Double.parseDouble(strS1) + Double.parseDouble(strS2);
                        return new Literal ( new Tipo (Tipo.EnumTipo.doble) , suma );
                    
                    case doble:
                        
                        Double sumaDoble = Double.parseDouble(strS1) + Double.parseDouble(strS2);
                        return new Literal ( new Tipo (Tipo.EnumTipo.doble) , sumaDoble);
                    
                    case caracter :
                        
                        Double sumaCaracter = Double.parseDouble(strS1) + (int) strS2.charAt(0);
                        return new Literal (new Tipo (Tipo.EnumTipo.doble) , sumaCaracter);
                        
                    case cadena:
                        
                        String sumaCadena  = strS1 + strS2;
                        return new Literal (new Tipo(Tipo.EnumTipo.cadena), sumaCadena);
                }
                
                break;
                
            case caracter:
                
                switch (s2.tipo.tipo){
                    case entero :
                        int sumaEntero = (int) strS1.charAt(0) + Integer.parseInt(strS2) ;
                        return new Literal (new Tipo(Tipo.EnumTipo.entero), sumaEntero) ;
                    
                    case doble:
                        Double sumaDoble =  (int) strS1.charAt(0) + Double.parseDouble(strS2) ;
                        return new Literal (new Tipo (Tipo.EnumTipo.doble) , sumaDoble);
                    
                    case caracter:
                        int sumaCaracter  = (int) strS1.charAt(0) + (int) strS2.charAt(0) ;
                        return new Literal (new Tipo(Tipo.EnumTipo.entero) , sumaCaracter);
                    
                    case cadena:
                        String sumaCadena = strS1 + strS2;
                        return new Literal (new Tipo (Tipo.EnumTipo.cadena) , sumaCadena);
                        
                }
                
            case cadena :
                
                String sumaCadena = strS1 + strS2;
                
                switch (s2.tipo.tipo) {
                    case entero:
                        
                        return new Literal ( new Tipo (Tipo.EnumTipo.cadena) , sumaCadena );
                    
                    case doble:
                        
                        return new Literal ( new Tipo (Tipo.EnumTipo.cadena) , sumaCadena);
                    
                    case cadena:
                        
                        return new Literal (new Tipo(Tipo.EnumTipo.cadena), sumaCadena);
                    
                    case caracter:
                        return new Literal (new Tipo (Tipo.EnumTipo.cadena) , sumaCadena);
                     
                    case booleano:
                        return new Literal (new Tipo(Tipo.EnumTipo.cadena) , sumaCadena);
                }
                
                break;
            
            case booleano :
                switch (s2.tipo.tipo) {
                    case cadena:
                        String sumaCadenaBooleano = strS1 + strS2;
                        return new Literal (new Tipo(Tipo.EnumTipo.cadena) , sumaCadenaBooleano) ;
                }
            
        }
        
        
        
        return new Literal ( new Tipo (Tipo.EnumTipo.error) , "@Error@") ;
    }
    
}
