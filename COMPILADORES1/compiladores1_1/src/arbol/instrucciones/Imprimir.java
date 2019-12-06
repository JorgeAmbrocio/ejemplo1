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

/**
 *
 * @author David Ventura
 */
public class Imprimir extends Instruccion{

    Expresion  valor;
    boolean salto;

    public Imprimir(Expresion valor, boolean salto) {
        this.valor = valor;
        this.salto = salto;
    }
    
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        Expresion resultado = valor.getValor(ent);
        String auxiliar = "";
        
        if (this.salto) {
            auxiliar = "\n";
        }
        
        if (resultado.tipo.tipo != Tipo.EnumTipo.error) {
            System.out.print(resultado.valor + auxiliar);
        }
        
        return null;
    }
    
    
    
}
