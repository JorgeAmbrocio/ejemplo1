/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones;

import arbol.Expresion;
import arbol.entorno.Entorno;
import arbol.entorno.Simbolo;
import arbol.entorno.SimboloMF;
import arbol.entorno.Tipo;
import arbol.instrucciones.Declaracion;
import interfaz.Errores;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class ExpLlamadaMF extends Expresion {

    LinkedList <Expresion> e;
    String nombre;
    Acceso id_;

    public ExpLlamadaMF(String nombre ,LinkedList<Expresion> e, int linea, int columna) {
        this.e = e;
        this.nombre = nombre;
        this.linea = linea;
        this.columna = columna;
    }
    
    public ExpLlamadaMF( String nombre, int linea , int columna) {
        this.nombre = nombre;
        this.linea = linea;
        this.columna = columna;
    }
    
    public ExpLlamadaMF(Acceso nombre ,LinkedList<Expresion> e, int linea, int columna) {
        this.e = e;
        this.id_ = nombre;
        this.linea = linea;
        this.columna = columna;
    }
    
    public ExpLlamadaMF( Acceso nombre, int linea , int columna) {
        this.id_ = nombre;
        this.linea = linea;
        this.columna = columna;
    }
    
    
    @Override
    public Expresion getValor(Entorno ent) {
        Entorno entornoNuevo = new Entorno( ent, ent.global);
        Expresion retorno = new Literal (new Tipo (Tipo.EnumTipo.error) , "@ERROR@");
        
        // crea un clon del acceso
        Acceso acceso = new Acceso(null);
        acceso.accesos = (LinkedList) this.id_.accesos.clone();
        
        LinkedList<Expresion> resueltos = new LinkedList<>();
        
        // preprar el nombre
        
        String aux = "";
        String strId = acceso.accesos.getLast().id;
        String lastLetter = strId.substring(0,1);
        if ( !lastLetter.equals("#")) {
            aux = "#";
        }
        
        String nombre_ =  aux + acceso.accesos.getLast().id;
        
        if  (this.e != null) {
            // sí tiene parámetros, crear nuevo nombre
            for (Expresion expresion : this.e) {
                Expresion expre = expresion.getValor(ent);
                nombre_ += expre.tipo.tipo.toString();
                resueltos.add(expre);
            }
        }
        
        // adjuntar el último valor modificado para el método
        if (!aux.equalsIgnoreCase("")) {
            Id id = acceso.accesos.pollLast();
            acceso.accesos.addLast(new Id (nombre_ , id.linea, id.columna));
        }
        // buscar que exista la función o método creados
        Simbolo simbolo = ent.global.buscar(acceso, linea, columna, "El metodo ");
        
        // verifica rexistencia del simbolo metodo funcion
        if (simbolo != null) {
            // la el simbolo sí existe
            
            Simbolo prueba = new SimboloMF (null , null);
            int iterador = 0 ;
            prueba = simbolo;
            
            if (this.e != null  &&  ((SimboloMF) simbolo).getParametros() != null ) {
                
                // iniciar las declaraciones para la llamada con los valores resutletos 
                for (Declaracion declaracion : ((SimboloMF)simbolo).getParametros() ) {
                    // declarar cada parámetro
                    declaracion.valor = resueltos.get(iterador);
                    declaracion.ejecutar(entornoNuevo);
                    iterador ++;
                }
            }
            
            // obtener el entorno global del objeto con elque se ejecutará la llamada
            if (acceso.accesos.size() > 1) {
                Entorno entorno = ent.getEntornoAcceso(acceso);
                if (entorno != null) {
                    entornoNuevo.global = entorno.global;
                }
            }
            
            // ejecutar el bloque del metodo o funcion
            retorno =(Expresion) ((SimboloMF)simbolo).getBloque().ejecutar(entornoNuevo);
            if (retorno != null) {
                // el objeto sí retonó un objeto
                Literal l = (Literal) retorno;
                if (simbolo.tipo.tipo == Tipo.EnumTipo.metodo) {
                    Errores eeee = new Errores(Errores.enumTipoError.semantico,"Error semántico, no se puede retornar un objeto desde el método: "  + nombre_ + " en la fila: " + this.linea + " columna: " + this.columna );
                    retorno = new Literal (new Tipo (Tipo.EnumTipo.error) , "@ERROR@"); // se evita el retorno del objeto
                }else if (simbolo.tipo.tipo != l.tipo.tipo && l.tipo.tipo !=Tipo.EnumTipo.nulo) {
                    // los tipos del retorno y de la función no son los mismos
                    Errores eeee = new Errores(Errores.enumTipoError.semantico,"Error semántico, no se puede retornar un casteo directo del tipo " + l.tipo.tipo.toString() + " a " + simbolo.tipo.tipo.toString() + " desde el método: "  + nombre_ + " en la fila: " + this.linea + " columna: " + this.columna );
                    retorno =new Literal (new Tipo (Tipo.EnumTipo.error) , "@ERROR@"); // se evita el retorno del objeto
                }
            }
        }else{
            // la el simbolo no existe
            Errores eeee = new Errores(Errores.enumTipoError.semantico,"No se ha declarado la propiedad utilizada: " + nombre_ + " en la fila " + this.linea + " columna: " + this.columna );
        }
        return retorno;
        //return new Literal (new Tipo (Tipo.EnumTipo.error) , "@ERROR@") ;
    }
    
}
