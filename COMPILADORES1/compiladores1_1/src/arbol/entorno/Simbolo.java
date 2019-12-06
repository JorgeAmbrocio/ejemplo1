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
public class Simbolo {
    
    public Tipo tipo;
    public Object valor;
    
    public Simbolo (Tipo tipo, Object valor) {
        this.tipo = tipo;
        this.valor = valor;
    }
    
}
