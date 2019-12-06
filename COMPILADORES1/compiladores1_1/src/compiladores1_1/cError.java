/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores1_1;

/**
 *
 * @author David Ventura
 */
public class cError {
    public String tipo; // lexico sintactico semandico
    public String error;
    public int linea;
    public int columna;

    public cError(String tipo, String error, int linea, int columna) {
        this.tipo = tipo;
        this.error = error;
        this.linea = linea;
        this.columna = columna;
    }
    
    
    
}
