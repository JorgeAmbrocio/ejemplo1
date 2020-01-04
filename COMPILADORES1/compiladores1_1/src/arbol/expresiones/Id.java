/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones;

import arbol.Expresion;
import arbol.entorno.Entorno;
import arbol.entorno.Simbolo;
import arbol.entorno.Tipo;

/**
 *
 * @author David Ventura
 */
public class Id extends Expresion {
    
    public String id; // nombre de la variable que quiero buscar

    public Id(String id ,int linea, int columna) {
        this.id = id;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Expresion getValor(Entorno ent) {
        Simbolo sim = ent.buscar(id, linea, columna, "La variable ");
        
        if (sim != null){
            Literal retorno = new Literal (sim.tipo, sim.valor );
            return retorno;
        }
        
        return new Literal(new Tipo (Tipo.EnumTipo.error) , "@Error@");
        
    }
    
    public Simbolo getSimbolo (Entorno ent){
        Simbolo sim = ent.buscar(id, linea, columna, "La variable ");
        
        if (sim != null){
            return sim;
        }else {
            sim = ent.global.buscar(id, linea, columna, "La variable ");
            
            if (sim != null) {
                return sim;
            }
        }
        
        return new Simbolo(new Tipo(Tipo.EnumTipo.error), new Literal(new Tipo (Tipo.EnumTipo.error) , "@Error@"));
        
    }
    
    
    
    
    
    
    
}
