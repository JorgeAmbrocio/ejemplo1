/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entorno.Entorno;
import arbol.entorno.Tipo;
import arbol.expresiones.Literal;

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
        
        if (this.retorno != null) {
            Expresion valorRetorno = this.retorno.getValor(ent);
            //System.out.println(" Se ha retornado un tipo: " + valorRetorno.tipo.tipo.toString() + " = " + valorRetorno.valor.toString());
            return valorRetorno;
        }
        
        //System.out.println(" Se ha retornado un tipo null");
        //return new Literal (new Tipo (Tipo.EnumTipo.nulo) , "@null@") ;
        return null;
    }
    
}
