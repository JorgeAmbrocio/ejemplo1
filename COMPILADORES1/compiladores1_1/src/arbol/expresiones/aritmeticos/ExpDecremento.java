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
import arbol.expresiones.Acceso;
import arbol.expresiones.Literal;
import arbol.instrucciones.Asignacion;
import interfaz.Errores;

/**
 *
 * @author David Ventura
 */
public class ExpDecremento extends Expresion {
    String id;
    
    Acceso id_;

    public ExpDecremento(String id, int linea, int columna) {
        this.id = id;
        this.linea = linea;
        this.columna= columna;
    }
    
    public ExpDecremento(Acceso id, int linea, int columna) {
        this.id_ = id;
        this.linea = linea;
        this.columna= columna;
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
        
        Literal retorno  = null;
        Simbolo e = ent.buscar(id_, linea, columna, "La variable"); //Busco la variable en la tabla de símbolos
        Asignacion a;
        switch (e.tipo.tipo) {
            case entero:
               int valor = Integer.parseInt(e.valor.toString());
               retorno = new Literal (new Tipo (Tipo.EnumTipo.entero) , valor);
               valor--;
               a = new Asignacion (this.id_ , this.linea , this.columna , new Literal(new Tipo (Tipo.EnumTipo.entero) , valor ));
               a.ejecutar(ent);
               break;
            case doble:
                double valor2 = Double.parseDouble(e.valor.toString());
                retorno = new Literal (new Tipo (Tipo.EnumTipo.doble) , valor2);
                valor2--; 
                a = new Asignacion (this.id_ , this.linea , this.columna , new Literal(new Tipo (Tipo.EnumTipo.doble) , valor2 ));
                a.ejecutar(ent);
                break;
            case caracter:
                char valor3 = e.valor.toString().charAt(0);
                retorno = new Literal (new Tipo (Tipo.EnumTipo.caracter) , valor3);
                valor3 -- ;
                a = new Asignacion (this.id_ , this.linea , this.columna , new Literal(new Tipo (Tipo.EnumTipo.caracter) , valor3 ));
                a.ejecutar(ent);
                break;
        }
        
        if (retorno == null) {
            System.out.println("Error");
            Errores errr = new Errores(Errores.enumTipoError.semantico , "No se pueden ejecutar el decremento con los tipos " + e.tipo.tipo.toString() + " Linea " + this.linea + " Columan " +  this.columna);
        
        }
        
        return retorno;
    }

}
