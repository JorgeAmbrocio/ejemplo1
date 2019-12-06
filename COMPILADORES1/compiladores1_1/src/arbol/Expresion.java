/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import arbol.entorno.Entorno;
import arbol.entorno.Tipo;

/**
 *
 * @author David Ventura
 */
public abstract class Expresion extends Nodo {
    public Tipo tipo;
    public Object valor; // aqí se guarda la expresion
    
    public abstract Expresion getValor(Entorno ent);
}
