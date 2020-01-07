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
import arbol.expresiones.Acceso;
import arbol.expresiones.Arreglo;
import arbol.expresiones.Literal;
import arbol.expresiones.Objeto;

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
        
        String rv = resultado.valor.toString();
        
        if (resultado.valor.getClass() == Literal.class) {
            rv = ((Literal)resultado.valor).valor.toString();
        }
        
        if (resultado.valor.getClass() == Objeto.class) {
            String [] vector = resultado.toString().split("@");
            
            rv = "Instancia Objeto->" + resultado.tipo.tr + "." + vector[1] ;
        }
        
        if (resultado.valor.getClass() == Arreglo.class) {
            Arreglo arreglo = (Arreglo) resultado.valor;
            rv = arreglo.getContenido(ent);
        }
        
        
        String auxiliar = "";
        
        if (this.salto) {
            auxiliar = "\n";
        }
        
        if (rv.contains("creado nodoBinario")) {
            int a= 5;
        }
        
        if (resultado.tipo.tipo != Tipo.EnumTipo.error) {
            System.out.print(rv + auxiliar);
                
            try {
                compiladores1_1.Compiladores1_1.fmrP.setData(rv + auxiliar);
            } catch (Exception e) {}
        }
        
        return null;
    }
    
    
    
}
