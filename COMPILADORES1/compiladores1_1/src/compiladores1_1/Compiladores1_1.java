/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores1_1;

import arbol.AST;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.StringReader;
import interfaz.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class Compiladores1_1 {
    
    /**
     * @param args the command line arguments
     */
    
    public static fmrPrincipal fmrP ;
    public static ArrayList <pnlHoja> hojas;
    public static LinkedList <enumCiclo> pilaCiclos;
    public static void main(String[] args) {
        
        
        pilaCiclos = new LinkedList();
        hojas = new ArrayList();
        
        interpretar("Entradas/entrada2.txt");
        //fmrP =new fmrPrincipal();
        //fmrP.show();
    }

    private static void interpretar(String path) {
        
        
        analizadores.parser pars;
//        Instruccion bloque;
        AST arbol;
        try {
            pars = new analizadores.parser(new analizadores.Lexico(new FileInputStream(path)));
            pars.parse();
            arbol = pars.AST;

            if (arbol != null) { //Si no existió un error en el análisis
                arbol.Ejecutar();
            } else {
                System.out.println("<----------> Existió un error en el análisis, no se pudo construir el árbol <---------->");
            }

        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error fatal en compilación de entrada.");
        }
    }
    
    
    public static void InsertarHoja () {
        pnlHoja pnl = new pnlHoja ();
        hojas.add(pnl);
        fmrP.addHoja(pnl);
    }
    
    public static enum enumCiclo {
        Switch, Ciclo
    }
    
    public static boolean estoyDentro() {
        return !pilaCiclos.isEmpty();
    }
    
}
