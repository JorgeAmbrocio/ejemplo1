/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones.aritmeticos;

import arbol.Expresion;
import arbol.entorno.Entorno;
import arbol.entorno.Simbolo;
import arbol.entorno.Tipo;
import arbol.expresiones.Id;
import arbol.expresiones.Literal;
import arbol.instrucciones.Asignacion;

/**
 *
 * @author David Ventura
 */
public class ExpIncremento extends Expresion {

    String id;

    public ExpIncremento(String id, int linea, int columna) {
        this.id = id;
        this.linea = linea;
        this.columna= columna;
    }
    
    
    @Override
    public Expresion getValor(Entorno ent) {
        
        Literal retorno  = null;
        Simbolo e = ent.buscar(id, linea, columna, "La variable"); //Busco la variable en la tabla de símbolos
        Asignacion a;
        switch (e.tipo.tipo) {
            case entero:
               int valor = Integer.parseInt(e.valor.toString());
               retorno = new Literal (new Tipo (Tipo.EnumTipo.entero) , valor);
               valor++;
               a = new Asignacion (this.id , this.linea , this.columna , new Literal(new Tipo (Tipo.EnumTipo.entero) , valor ));
               a.ejecutar(ent);
               break;
            case doble:
                double valor2 = Double.parseDouble(e.valor.toString());
                retorno = new Literal (new Tipo (Tipo.EnumTipo.doble) , valor2);
                valor2++; 
                a = new Asignacion (this.id , this.linea , this.columna , new Literal(new Tipo (Tipo.EnumTipo.doble) , valor2 ));
                a.ejecutar(ent);
                break;
            case caracter:
                char valor3 = e.valor.toString().charAt(0);
                retorno = new Literal (new Tipo (Tipo.EnumTipo.caracter) , valor3);
                valor3 ++ ;
                a = new Asignacion (this.id , this.linea , this.columna , new Literal(new Tipo (Tipo.EnumTipo.caracter) , valor3 ));
                a.ejecutar(ent);
                break;
        }
        
        if (retorno == null) {
            System.out.println("Error");
        }
        
        return retorno;
    }

    
}
