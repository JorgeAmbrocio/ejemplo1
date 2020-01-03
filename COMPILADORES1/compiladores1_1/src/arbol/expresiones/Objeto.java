/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entorno.Entorno;
import arbol.entorno.Simbolo;
import arbol.entorno.Tipo;
import arbol.instrucciones.Asignacion;
import arbol.instrucciones.DeclaraMetodoFuncion;
import arbol.instrucciones.Declaracion;
import arbol.instrucciones.DeclaracionClase;
import arbol.instrucciones.Import;
import arbol.instrucciones.Metodo;
import interfaz.Errores;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class Objeto extends Expresion {
    public Entorno global;
    //public Entorno anterior;
    public LinkedList<Expresion> parametros;

    public Objeto(String id, LinkedList<Expresion> parametros, int linea, int columna) {
        this.tipo = new Tipo(Tipo.EnumTipo.objeto,id );
        this.tipo.tr = id;
        this.parametros = parametros;
        this.linea = linea;
        this.columna = columna;
    }
    
    public Objeto(String id,  int linea, int columna) {
        this.tipo = new Tipo(Tipo.EnumTipo.objeto,id );
        this.tipo.tr = id;
        this.parametros = null;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Expresion getValor(Entorno ent) {
        
        // el valor del objeto
        this.valor = this;
        
        Expresion retorno = new Literal(new Tipo(Tipo.EnumTipo.error), "@ERROR@");
        
        // buscar la clase en el entorno
        Simbolo simbolo = ent.buscar(this.tipo.tr, linea, columna, "La clase ");
        
        // verificar si existe la clase
        if (simbolo != null) {
            // sí existe la clase
            
            // verificar que es una clase
            if (simbolo.valor.getClass() == DeclaracionClase.class) {
                // sí es una clase
                
                this.global = new Entorno(null); // crea un entorno blobal de la clase
                this.global.global = this.global;
                
                // obtener el bloque de datps y declaraciones del objeto
                DeclaracionClase dclase  = (DeclaracionClase) simbolo.valor;
                LinkedList<Instruccion> instrucciones  = dclase.bloque.instrucciones;
                
                // recorrer las instrucciones, para insertar los datos en el objeto
                // recorres declaraciones de metodos
                for (Instruccion instruccion: instrucciones) {
                    if (instruccion.getClass() == Import.class){
                        // si es la declaracion de un metodo o funcion
                        instruccion.ejecutar(this.global);
                    }
                }
                
                // recorres declaraciones de metodos
                for (Instruccion instruccion: instrucciones) {
                    if (instruccion.getClass() == DeclaraMetodoFuncion.class){
                        // si es la declaracion de un metodo o funcion
                        instruccion.ejecutar(this.global);
                    }
                }
                
                /// declaracion de variables
                for (Instruccion instruccion: instrucciones) {
                    if (instruccion.getClass() == Declaracion.class){
                        // si es la declaracion de un metodo o funcion
                        instruccion.ejecutar(this.global);
                    }
                }
                
                retorno = this;
                
            }else {
                // error, se quiere crear un nuevo objeto y el id no pertenece a una clase
                Errores errr = new Errores (Errores.enumTipoError.semantico , "El id proporcionado no pertenece a una clase \"" + this.tipo.tr + "\" no puede ser un tipo de objeto.");   
            }
        }else {
            // no existe la clase
            Errores errr = new Errores (Errores.enumTipoError.semantico , "No se ha encontrado la clase " + this.tipo.tr + " para crear el objeto.");
                
        }
        
        
        return this;
    }
    
}
