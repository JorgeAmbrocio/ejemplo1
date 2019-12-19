/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class Errores {
    
    public enumTipoError error;
    public String strError;
    public String descripcion;

    public Errores(enumTipoError error, String descripcion) {
        this.error = error;
        this.descripcion = descripcion;
        this.strError = this.getStrError();
        compiladores1_1.Compiladores1_1.errores.add(this);
    }
    
    public enum enumTipoError {
        lexico, sintactico, semantico, ejecucion
    }
    
    public String getStrError () {
        String strE  = "";
        
        switch (this.error){ 
            case lexico:
                strE = "LEXICO";
                break;
            case sintactico:
                strE = "SINTACTICO";
                break;
            case semantico:
                strE = "SEMANTICO";
                break;
            case ejecucion:
                strE = "EJECUCION";
                break;
        }
        
        return strE;
    }
    
    
    
}
