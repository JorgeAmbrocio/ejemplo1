/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Instruccion;
import arbol.entorno.Entorno;
import interfaz.Errores;

/**
 *
 * @author David Ventura
 */
public class Continue extends Instruccion {

    public Continue  (int linea , int columna) {
        this.linea = linea;
        this.columna = columna;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        
        if (compiladores1_1.Compiladores1_1.estoyDentro()) {
            // si está dentro de un ciclo enonces returna sí mismo
            return this;
        }else {
            System.out.println("Se esperaba continue dentro de un ciclo o switch");
            Errores ee = new Errores(Errores.enumTipoError.semantico  , "Se esperaba continue dentro de un ciclo o switch. Línea " + this.linea + " Columna " + this.columna);
        }
        
        return null;
    }
    
}
