/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones.relacionales;

/**
 *
 * @author David Ventura
 */
public class Relacional {
    
    public EnumRelacional relacional;
    public String tr;

    public Relacional(EnumRelacional relacional) {
        this.relacional = relacional;
    }
    
    
    
    public enum EnumRelacional {
        mayor, menor, mayorIgual, menorIgual, igual, diferente
    }
}
