/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entorno.Entorno;

/**
 *
 * @author David Ventura
 */
public class Return extends Instruccion {
    
    Expresion retorno;

    public Return(Expresion retorno) {
        this.retorno = retorno;
    }
    
    public Return() {}
    
    @Override
    public Object ejecutar(Entorno ent) {
        // retorna la expresión ya con sus valores obtenidos en el entorno que trae
        Expresion valorRetorno = this.retorno.getValor(ent);
        return valorRetorno;
    }
    
}
