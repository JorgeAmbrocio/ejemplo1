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
import interfaz.Errores;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class Acceso extends Expresion {

    public LinkedList<Id> accesos;

    public Acceso(LinkedList<Id> accesos ) {
        this.accesos = accesos;
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
        
        Expresion retorno = new Literal (new Tipo(Tipo.EnumTipo.error), "@error@") ;
        
        LinkedList<Id> aux = this.accesos;
        
        Entorno busqueda = ent;
        
        // recorrer todos los id
        for (Id id : aux){
            Simbolo l = id.getSimbolo(busqueda); // obtiene el objeto en el entorno indicado
            //aux.pollFirst();
            
            //verificar si es el último elemento
            if (id == aux.getLast()) {
                // estamos en el ùltimo id
                // no debe ser un objeto
                retorno = new Literal(l.tipo, l.valor); // retorno del ojeto final
                break;
            }else { 
                // no es el último id
                // verifica sea de tipo objeto
                if (l.tipo.tipo != Tipo.EnumTipo.objeto) {
                    // eeror
                    Errores  err = new Errores(Errores.enumTipoError.semantico , "El valor del id " + id.id + " ya es de tipo primitivo no se le pueden adjudicar más capas de id.");
                    break;
                }
                
                // sí es de tipo objeto, ahora vamos a buscar en el entorno del objeto
                busqueda = ((Objeto)l.valor).global;
            }
        }
        return retorno;
    }
    
}
