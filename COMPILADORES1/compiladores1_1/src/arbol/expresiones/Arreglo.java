/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.expresiones;

import arbol.Expresion;
import arbol.entorno.Entorno;
import arbol.entorno.Tipo;
import interfaz.Errores;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class Arreglo extends Expresion {
    
    public LinkedList<Expresion> contenido;
    
    public int profundidad ;
    
    public int tamano;
    
    LinkedList<Integer> dimensiones;
    
    
    public Arreglo() {
        this.contenido = new LinkedList<>();
        this.tipo = null;
    }
    
    public Arreglo(LinkedList<Expresion> contenido) {
        this.contenido = contenido;
        this.profundidad = 1;
        this.tamano = this.contenido.size();
        this.tipo = null;
    }

    public Arreglo(Tipo tipo ,LinkedList<Integer> dimensiones) {
        this.tipo =tipo;
        this.tipo.dimension = dimensiones.size();
        this.profundidad =dimensiones.size();
        this.tamano = dimensiones.getFirst();
        
        this.contenido = null;
        this.dimensiones = dimensiones;
    }

    public void agregar () {this.tamano ++;}
    
    public void calcularProfundidad() {
        // si la lista actual no está vacía, recalculamos la dimensión
        if (!this.contenido.isEmpty()) {
            this.profundidad = ((Arreglo)this.contenido.get(0)).profundidad + 1;
        }
        this.agregar();
        
    }

    @Override
    public Expresion getValor(Entorno ent) {
        // verificar si el contenido está vacío
        Expresion retorno = new Literal(new Tipo(Tipo.EnumTipo.error) , "@Error@");
        if (this.contenido == null) {
            
            if (this.tipo.dimension >= 1 || this.tamano >= 1) {
                this.crearArreglo(this.tipo.dimension,this.tamano);
                retorno  = new Literal(this.tipo , this);
            }else {
                Errores errr = new Errores(Errores.enumTipoError.semantico , "Los valores al declarar el vector deben ser enteros positivos mayores a cero.");
            }
        }else if (this.esValido(this, ent).equals("")) {
            this.tipo.dimension = this.profundidad;
            retorno = new Literal(this.tipo , this);
        }else {
            Errores errr = new Errores(Errores.enumTipoError.semantico, "");
        }
        
        return retorno;
    }
    
    public String esValido (Arreglo a, Entorno ent) {
        String retorno = "";
        
        LinkedList<Expresion> lista = a.contenido;
        
        // obtener los datos iniciales
        int profundidad = -1;
        int tamano = -1 ;
        Tipo tipo = null ;
        
        boolean esArreglo = false;
        if (lista.get(0).getClass() == Arreglo.class) {
            esArreglo = true;
            profundidad = ((Arreglo)lista.get(0)).profundidad;
            tamano = ((Arreglo)lista.get(0)).tamano;
        }else {
            esArreglo = false;
            tipo = ((Expresion)lista.get(0)).tipo;
            this.tipo =tipo;
        }
        
        for (Expresion e : lista) {
            
            if (e.getClass() == Arreglo.class ) {
                // es un arreglo, entonces también lo verificamos
                Arreglo arreglo = (Arreglo) e;
                
                if (!esArreglo) {
                    retorno += " Los valores del arreglo no poseen la misma profundidad : " + profundidad + ":" + arreglo.profundidad;
                    break;
                }
                
                if (profundidad != arreglo.profundidad) {
                    retorno += " La profundidad no es homogenea en los valores del arreglo: " + profundidad + ":" + arreglo.profundidad;
                    break;
                }
                
                if (tamano != arreglo.tamano) {
                    retorno += " El tamaño no e homogeneo en los valores del arreglo: " + tamano + ":" + arreglo.tamano;
                    break;
                }
                
                retorno += this.esValido((Arreglo)e, ent);
                
            }else {
                // es un valor primitivo u objeto
                e = e.getValor(ent);
                if (tipo != null) {
                    if ( tipo.tipo != e.tipo.tipo || !tipo.tr.equals(e.tipo.tr)) {
                        retorno += " El tipo de los objetos no es homogeneo en los valores del arreglo: " + tipo.tipo + "-" + tipo.tr + ":" + e.tipo.tipo + "-" + e.tipo.tr;
                    break;
                    }
                }else {
                    retorno += " El objeto base del arrreglo no posee un tipo definido. ";
                    break;
                }
                
            }
            
        }
        
        return retorno;
    }
    
    public Arreglo crearArreglo (int profundidadActual, int tamanoActual) {
        this.contenido = new LinkedList<>();
        this.profundidad = profundidadActual;
        this.tamano = tamanoActual;
        
        if (profundidadActual != 1) {
            // si aún no es la profundidad , entonces cramos otro arreglo con profundidad -- y tamano correspondiente
            // recorrer todas las posiciones actuales
            LinkedList<Integer> aux = (LinkedList) this.dimensiones.clone();
            aux.removeFirst();
            for (int i = 0 ; i < tamanoActual; i++){
                Arreglo arreglo = this.crearSubArreglo(profundidadActual -1, (LinkedList) aux.clone());
                this.contenido.add(arreglo);
            }
            
        }else {
            // si ya es de profundidad uno, solo llenamos los espacios con valores primitivos u objetos
            this.contenido = new LinkedList<>();
            for(int i = 0 ; i < tamanoActual ; i++){
                this.contenido.add(i, this.getLiteralBase());
            }
        }
        
        return this;
    }
    
    public Arreglo crearSubArreglo (int profundidadActual , LinkedList<Integer> aux) {
        int tamanoActual = aux.removeFirst();
        Arreglo arreglo = new Arreglo();
        arreglo.contenido = new LinkedList<>();
        arreglo.tipo = new Tipo(Tipo.EnumTipo.arreglo, profundidadActual );
        arreglo.tamano = tamanoActual;
        arreglo.profundidad = profundidadActual;
        
        if (profundidadActual != 1) {
            for (int i = 0 ; i < tamanoActual ; i++) {
                Arreglo subArreglo = this.crearSubArreglo(profundidadActual-1, (LinkedList) aux.clone());
                arreglo.contenido.add(subArreglo);
            }
        }else {
            for (int i = 0 ; i < tamanoActual ; i ++) {
                arreglo.contenido.add(i, this.getLiteralBase());
            }
        }
        
        return arreglo;
    }
    
    public Literal getLiteralBase () {
        Literal l = null;
        switch(this.tipo.tipo) {
            case entero:
                l = new Literal(this.tipo,0);
                break;
            case doble:
                l = new Literal(this.tipo,0.0);
                break;
            case caracter:
                l = new Literal(this.tipo,'\0');
                break;
            case cadena:
                l = new Literal(this.tipo,"");
                break;
            case booleano:
                l = new Literal(this.tipo,false);
                break;
            case objeto:
                l = new Literal(this.tipo,Tipo.EnumTipo.nulo);
                break;
        }
        
        return l;
        
    }
    
}
