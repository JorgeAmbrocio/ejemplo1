/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.entorno;

/**
 *
 * @author David Ventura
 */
public class Tipo {
    
    public EnumTipo tipo;
    public String tr;
    public Integer dimension = 0;
    
    
    public Tipo (EnumTipo tipo) {
        this.tipo = tipo;
        this.tr = "";
    }
    
    public Tipo (EnumTipo tipo , String tr) {
        this.tipo = tipo;
        this.tr = tr;
    }
    
    public Tipo (EnumTipo tipo ,  int dimension) {
        this.tipo = tipo;
        this.tr = tr;
        this.dimension = dimension;
    }
    
    public enum EnumTipo {
        entero, caracter, booleano, doble,cadena,error, objeto, nulo, metodo, arreglo
    }
}

