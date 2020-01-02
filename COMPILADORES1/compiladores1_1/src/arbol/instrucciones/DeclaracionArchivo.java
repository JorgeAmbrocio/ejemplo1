/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Instruccion;
import arbol.entorno.Entorno;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class DeclaracionArchivo extends Instruccion {

    LinkedList<Instruccion> instruccionesImportSuperior;
    Instruccion instruccionesClase;
    LinkedList<Instruccion> instruccionesImportInferior;
    public LinkedList<Instruccion> instrucciones;
    
    Entorno entorno;

    
    
    public DeclaracionArchivo( Instruccion Clase) {
        this.instruccionesClase = Clase;
        
        this.setDatos();
    }
    
    public DeclaracionArchivo(LinkedList<Instruccion> ImportS) {
        this.instruccionesImportSuperior = ImportS;
        
        this.setDatos();
    }
    
    public DeclaracionArchivo(LinkedList<Instruccion> ImportS, Instruccion Clase) {
        this.instruccionesImportSuperior = ImportS;
        this.instruccionesClase = Clase;
        
        this.setDatos();
    }
    
    public DeclaracionArchivo( Instruccion Clase, LinkedList<Instruccion> ImportI) {
        this.instruccionesClase = Clase;
        this.instruccionesImportInferior = ImportI;
        
        this.setDatos();
    }
    
    public DeclaracionArchivo(LinkedList<Instruccion> ImportS, Instruccion Clase, LinkedList<Instruccion> ImportI) {
        this.instruccionesImportSuperior = ImportS;
        this.instruccionesClase = Clase;
        this.instruccionesImportInferior = ImportI;
        
        this.setDatos();
    }
    
    private void setDatos(){
        
        if (this.instruccionesImportSuperior != null) {
            for (Instruccion i : this.instruccionesImportSuperior) {
                this.instrucciones.addFirst(i);
            }
        }
        
        if (this.instruccionesImportInferior != null) {
            for (Instruccion i : this.instruccionesImportInferior) {
                this.instrucciones.addFirst(i);
            }
        }
        
        if (this.instruccionesClase != null) {
            this.instrucciones.addLast(instruccionesClase);
        }
        
    }
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
