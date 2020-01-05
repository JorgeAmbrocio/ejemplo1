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
import arbol.instrucciones.InsLlamadaMF;
import arbol.instrucciones.Metodo;
import interfaz.Errores;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author David Ventura
 */
public class Objeto extends Expresion {
    public Entorno global;
    //public Entorno anterior;
    public LinkedList<Expresion> parametros;

    public Objeto(Tipo tipo, LinkedList<Expresion> parametros, int linea, int columna) {
        this.tipo = tipo;
        this.parametros = parametros;
        this.linea = linea;
        this.columna = columna;
    }
    
    public Objeto(Tipo tipo,  int linea, int columna) {
        this.tipo = tipo;
        this.parametros = null;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Expresion getValor(Entorno ent) {
        
        Expresion retorno = new Literal(new Tipo(Tipo.EnumTipo.error), "@ERROR@");
        
        // si no es un objeto, entonces es una expresion normal que retorna el valor de su
        // parámetro
        if (this.tipo.tipo != Tipo.EnumTipo.objeto) {
            if (this.parametros!= null && this.parametros.size() == 1) {
                Expresion expresion = this.parametros.getFirst().getValor(ent);
                    
                if (this.tipo.tipo == expresion.tipo.tipo) {
                    return new Literal(expresion.tipo, expresion.valor);
                }else {
                    Errores errr = new Errores(Errores.enumTipoError.semantico, "El valor de la declaración no coincide con el valor del parámetro " + this.tipo.tipo + "-" + expresion.tipo.tipo);
                    return retorno;
                }
            }else {
                Errores errr = new Errores(Errores.enumTipoError.semantico, "Se esperaba un único parámetro en la declaración de tipo " + this.tipo.tipo + " línea " + this.linea + " columna " + this.columna);
                return retorno;
            }
        }
        
        // el valor del objeto
        this.valor = this;
        
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
                this.global.anterior = ent;
                
                // obtener el bloque de datps y declaraciones del objeto
                DeclaracionClase dclase  = (DeclaracionClase) simbolo.valor;
                LinkedList<Instruccion> instrucciones  = dclase.bloque.instrucciones;
                String strClase =new String(dclase.nombre);
                // recorrer las instrucciones, para insertar los datos en el objeto
                // recorres declaraciones de metodos
                
                
                // recorres declaraciones de metodos
                for (Instruccion instruccion: instrucciones) {
                    if (instruccion.getClass() == DeclaraMetodoFuncion.class){
                        // si es la declaracion de un metodo o funcion
                        instruccion.ejecutar(this.global);
                    }
                }
                
                /// declaracion de variables
                    // declarar la variable this
                    Simbolo simThis = new Simbolo(this.tipo, this);
                    this.global.insertar("this", simThis, 0, 0, "La variable ");
                for (Instruccion instruccion: instrucciones) {
                    if (instruccion.getClass() == Declaracion.class){
                        // si es la declaracion de un metodo o funcion
                        instruccion.ejecutar(this.global);
                    }
                }
                
                // verificar si tiene parámetros
                String nombreClase = "#" + strClase;
                LinkedList<Expresion> resueltos = new LinkedList<>();
                if (this.parametros != null) {
                    // crear el nuevo anexo de parámetros al nombre
                    for (Expresion e : this.parametros){
                        Expresion resuelto = e.getValor(ent);
                        resueltos.add(e);
                        nombreClase += resuelto.tipo.tipo + resuelto.tipo.tr;
                    }
                }

                boolean tieneConstructorAdecuado = false; // indica si el constructor adecuado existe
                boolean tieneConstructor = false; // indica si se encontró algún constructor                
                for (Map.Entry<String, Simbolo>  dato : this.global.tabla.entrySet()) {
                    
                    String nombreClave = dato.getKey();
                    // verifica si tiene al menos un constructor
                    if ( nombreClave.contains("#"+ strClase)) {
                        tieneConstructor = true;
                    }
                    
                    // verifica que se haya encontrado el construcctor adecuado
                    if ( nombreClave.equals(nombreClase)) {
                        // ejecutar la llamada a funcionamiento del método
                        tieneConstructorAdecuado = true;
                        // crear Acceso
                        LinkedList<Id> lid = new LinkedList<>();
                        lid.add(new Id(nombreClase, 0 , 0));
                        InsLlamadaMF llamada = new InsLlamadaMF(new Acceso(lid), resueltos, 0 , 0 );
                        llamada.ejecutar(this.global);
                    }
                }
                
                // verificar si se ejecutó algún contructor y si tiene contructor
                if (!tieneConstructor && this.parametros == null) {
                    // si no tiene constructor y no tiene parámetros
                    // se simula que tiene un construcctor vacío
                    retorno = this;
                }
                
                if (!tieneConstructorAdecuado) {
                    // si se encontró el constructor adecuado para la instancia
                    retorno = this;
                }
                
                // si no se pudo crear el objeto, se crea el error para mostrar en la tabla
                if (retorno.tipo.tipo == Tipo.EnumTipo.error) {
                    Errores errr = new Errores (Errores.enumTipoError.semantico, "No se encontró el constructor adecuado para la instancia solicitada de la clase " + dclase.nombre + ".");
                }
                
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
