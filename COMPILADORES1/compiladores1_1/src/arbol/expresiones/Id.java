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
public class Id extends Expresion {
    
    public String id; // nombre de la variable que quiero buscar
    
    public LinkedList<Integer> accesoArreglo = null;
    
    public LinkedList<Expresion> parametros = null;

    public Id(String id ,int linea, int columna) {
        this.id = id;
        this.linea = linea;
        this.columna = columna;
    }
    
    public Id(String id , LinkedList<Integer> accesoArreglo , int linea , int columna) {
        this.id = id;
        this.accesoArreglo = accesoArreglo;
        this.linea = linea;
        this.columna = columna;
    }
    
    public Id(  LinkedList<Expresion> parametros ,String id ,int linea, int columna) {
        this.id = id;
        this.parametros = parametros;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Expresion getValor(Entorno ent) {
        
        if (this.parametros == null && this.accesoArreglo == null) {
            // es un id normal
            return this.getValorId(ent);
        }else if(this.accesoArreglo != null) {
            // es un acceso a arreglo
            return this.getValorArreglo(ent);
        }else if (this.parametros != null) {
            // es una llamada a función
            return this.getValorLlamada(ent);
        }
        
        
        return new Literal(new Tipo (Tipo.EnumTipo.error) , "@Error@");
    }
    
    public Expresion getValorId (Entorno ent){
        
        Simbolo sim = ent.buscar(id, linea, columna, "La variable ");
        
        if (sim != null){
            Literal retorno = new Literal (sim.tipo, sim.valor );
            return retorno;
        }
        return new Literal(new Tipo (Tipo.EnumTipo.error) , "@Error@");
    }
    
    public Expresion getValorArreglo (Entorno ent){
        Expresion retorno = new Literal(new Tipo (Tipo.EnumTipo.error) , "@Error@");
        Simbolo sim = ent.buscar(id, linea, columna, "La variable ");
        
        if (sim != null  ){
            if (sim.valor.getClass() == Arreglo.class) {
                // es un arreglo
                Arreglo arreglo = (Arreglo) sim.valor;
                retorno = this.getValorArreglo(arreglo, (LinkedList) this.accesoArreglo.clone());
            }
        }
        return retorno;
    }
    
    public Expresion getValorArreglo (Arreglo a , LinkedList<Integer> index) {
        
        Expresion retorno = new Literal(new Tipo (Tipo.EnumTipo.error) , "@Error@");
        // verifica que tengan la profundidad adecuada
        if (a.profundidad >= index.size()) {
            // verifica que tenga el tamaño adecuado
            if (a.tamano >= index.get(0)) {
                // la primera posición existe
                
                
                
                if (a.profundidad != 1 ) {
                    // aún hay más arreglos que inspeccionar
                    Arreglo aa = (Arreglo) a.contenido.get(index.removeFirst());
                    retorno = this.getValorArreglo(aa, (LinkedList) index.clone());
                }else {
                    // profundidad es uno, ya podemos objeter el valor de la posición final
                    retorno = a.contenido.get(index.getFirst());
                }
            }
        }
        
        return retorno;
    }
    
    public Expresion getValorLlamada (Entorno ent){
        
        return new Literal(new Tipo (Tipo.EnumTipo.error) , "@Error@");
    }
    
    public Simbolo getSimbolo (Entorno ent){
        if (this.parametros == null && this.accesoArreglo == null) {
            // es un id normal
            return this.getSimboloId(ent);
        }else if(this.accesoArreglo != null) {
            // es un acceso a arreglo
            return this.getSimboloArreglo(ent);
        }else if (this.parametros != null) {
            // es una llamada a función
            return this.getSimboloLlamada(ent);
        }
        
        return new Simbolo(new Tipo(Tipo.EnumTipo.error), new Literal(new Tipo (Tipo.EnumTipo.error) , "@Error@"));
    }
    
    public Simbolo getSimboloId (Entorno ent){
        
        Simbolo sim = ent.buscar(id, linea, columna, "La variable ");
        
        if (id.equals("this")) {
            sim = null;
        }
        
        if (sim != null){
            return sim;
        }else {
            sim = ent.global.buscar(id, linea, columna, "La variable ");
            
            if (sim != null) {
                return sim;
            }
        }
        return new Simbolo(new Tipo(Tipo.EnumTipo.error), new Literal(new Tipo (Tipo.EnumTipo.error) , "@Error@"));
    }
    
    public Simbolo getSimboloArreglo (Entorno ent){
        Simbolo retorno =  new Simbolo(new Tipo(Tipo.EnumTipo.error), new Literal(new Tipo (Tipo.EnumTipo.error) , "@Error@"));
        
        Simbolo sim = this.getSimboloId(ent);
        
        if (sim != null){
            if (sim.valor.getClass() == Arreglo.class) {
                // es un arreglo
                Arreglo arreglo = (Arreglo) sim.valor;
                retorno = this.getSimboloArreglo(arreglo, (LinkedList) this.accesoArreglo.clone());
            }
        }
        return retorno;
    }
    
    public Simbolo getSimboloArreglo (Arreglo a , LinkedList<Integer> index) {
        
        Simbolo retorno =  new Simbolo(new Tipo(Tipo.EnumTipo.error), new Literal(new Tipo (Tipo.EnumTipo.error) , "@Error@"));
        
        // verifica que tengan la profundidad adecuada
        if (a.profundidad >= index.size()) {
            // verifica que tenga el tamaño adecuado
            if (a.tamano >= index.get(0)) {
                // la primera posición existe
                
                if (a.profundidad != 1 && index.size() != 1) {
                    // aún hay más arreglos que inspeccionar
                    Arreglo aa = (Arreglo) a.contenido.get(index.removeFirst());
                    if (aa.tipo == null) {
                        aa.tipo = new Tipo (a.tipo.tipo, a.profundidad -1);
                    }
                    retorno = this.getSimboloArreglo(aa, (LinkedList) index.clone());
                }else {
                    // profundidad es uno, ya podemos objeter el valor de la posición final
                    Expresion retorno_ = a.contenido.get(index.getFirst());
                    
                    if (retorno_.tipo == null) {
                        retorno_.tipo = new Tipo (a.tipo.tipo, a.profundidad -1);
                    }
                    
                    retorno = new Simbolo(retorno_.tipo, retorno_);
                }
            }
        }
        
        return retorno;
    }
    
    public Simbolo getSimboloLlamada (Entorno ent){
        Simbolo sim = ent.buscar(id, linea, columna, "La variable ");
        
        if (sim != null){
            return sim;
        }else {
            sim = ent.global.buscar(id, linea, columna, "La variable ");
            
            if (sim != null) {
                return sim;
            }
        }
        
        return new Simbolo(new Tipo(Tipo.EnumTipo.error), new Literal(new Tipo (Tipo.EnumTipo.error) , "@Error@"));
        
    }
    
    
    
    
    
}
