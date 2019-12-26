/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entorno.Entorno;
import arbol.entorno.Simbolo;
import arbol.entorno.SimboloMF;
import interfaz.Errores;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class LlamadaMF extends Instruccion {

    LinkedList <Expresion> e;
    String nombre;

    public LlamadaMF(String nombre ,LinkedList<Expresion> e) {
        this.e = e;
        this.nombre = nombre;
    }
    
    public LlamadaMF( String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        Entorno entornoNuevo = new Entorno(ent);
        
        // preprar el nombre
        String nombre_ =  "#" + this.nombre;
        
        if  (this.e != null) {
            // sí tiene parámetros, crear nuevo nombre
            for (Expresion expresion : this.e) {
                Expresion expre = expresion.getValor(ent);
                nombre_ += expre.tipo.tipo.toString();
            }
        }
        
        // buscar que exista la función o método creados
        Simbolo simbolo = ent.buscar(nombre_, linea, columna, "El metodo");
        
        
        // verifica rexistencia del simbolo
        if (simbolo != null) {
            // la el simbolo sí existe
            
            Simbolo prueba = new SimboloMF (null , null);
            int iterador = 0 ;
            prueba = simbolo;
            
            if (this.e != null  &&  ((SimboloMF) simbolo).getParametros() != null ) {
                LinkedList<Expresion> resueltos = new LinkedList<>();
                // resolver las asignaciones que se realizarán en la llamada
                for(Expresion expresion : this.e) {
                    resueltos.add(expresion.getValor(ent));
                }
                
                // iniciar las declaraciones para la llamada con los valores resutletos 
                for (Declaracion declaracion : ((SimboloMF)simbolo).getParametros() ) {
                    // declarar cada parámetro
                    declaracion.valor = resueltos.get(iterador);
                    declaracion.ejecutar(entornoNuevo);
                    iterador ++;
                }
            }
            
            // ejecutar el bloque del metodo o funcion
            Object retorno = ((SimboloMF)simbolo).getBloque().ejecutar(entornoNuevo);
            if (retorno != null) {
                return retorno;
            }
            
        }else{
            // la el simbolo no existe
            Errores eeee = new Errores(Errores.enumTipoError.semantico,"No se ha declarado la propiedad utilizada: " + nombre_ + " en la fila " + this.linea + " columna: " + this.columna );
        }
        
        return null;
    }
    
    
    
}
