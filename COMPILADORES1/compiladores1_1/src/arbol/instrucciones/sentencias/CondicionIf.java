/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones.sentencias;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entorno.Entorno;
import static arbol.entorno.Tipo.EnumTipo.booleano;

/**
 *
 * @author David Ventura
 */
public class CondicionIf extends Instruccion  {
    
    Expresion condicion;
    Bloque bloque;
    boolean ejecutado;
    
    public CondicionIf(Expresion condicion, Bloque bloque) {
        this.condicion = condicion;
        this.bloque = bloque;
    }
    
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        
        this.ejecutado = false;
        Expresion condicion_ = this.condicion.getValor(ent);
        
        if (condicion_.tipo.tipo == booleano) {
            boolean ejecutar = Boolean.parseBoolean(condicion_.valor.toString());
            if (ejecutar) {
                this.ejecutado = true;
                Entorno nuevo = new Entorno(ent);
                this.bloque.ejecutar(nuevo);
            }   
        }    
        return null;
    }
    
    
    
}
