/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones;

import arbol.Instruccion;
import arbol.entorno.Entorno;

/**
 *
 * @author David Ventura
 */
public class GraficarEntornos extends Instruccion {

    
    @Override
    public Object ejecutar(Entorno ent) {
        
        ent.GraficarEntorno();
        
        return null;
    }
    
}
