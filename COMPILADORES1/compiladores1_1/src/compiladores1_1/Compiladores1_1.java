/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores1_1;

import arbol.AST;
import arbol.entorno.Entorno;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.StringReader;
import interfaz.*;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java_cup.runtime.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
    public static LinkedList <Errores> errores ;
    public static Entorno general;
    
    public static void main(String[] args) {
        
        errores  = new LinkedList();
        pilaCiclos = new LinkedList();
        hojas = new ArrayList();
        
        
        //interpretar("Entradas/entrada2.txt");
        fmrP =new fmrPrincipal();
        fmrP.show();
    }

    public static void interpretar(String path) {
        
        
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
    
    public static void interpretar_(String contenido) {
        
        
        analizadores.parser pars;
//        Instruccion bloque;
        AST arbol;
        try {
            BufferedReader bf ;
            bf = new BufferedReader (new StringReader (contenido));
            pars = new analizadores.parser(new analizadores.Lexico(bf));
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
    
    public static AST obtenerArbol  (String contenido) {
        analizadores.parser pars;
//        Instruccion bloque;
        AST arbol;
        try {
            BufferedReader bf ;
            bf = new BufferedReader (new StringReader (contenido));
            pars = new analizadores.parser(new analizadores.Lexico(bf));
            pars.parse();
            arbol = pars.AST;

            if (arbol != null) { //Si no existió un error en el análisis
                arbol.Ejecutar();
            } else {
                System.out.println("<----------> Existió un error en el análisis, no se pudo construir el árbol <---------->");
            }
            
            return arbol; // retorna el arbol
            
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error fatal en compilación de entrada.");
        }
        
        return null;
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
    
    public static String getContenidoHoja (String nombre) {
        
        for (pnlHoja hoja : hojas) {
            if (hoja.nombreArchivo.equals(nombre)) {
                // si existe una hoja con el nombre indicado,
                // devuelve el contenido texto de la hoja
                return hoja.txt.getText();
            }
        }
        
        return "";
    }
    
    public static String getFolder () {
        String ruta = "";
        
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(1);
        int seleccion = fc.showOpenDialog(null);
        
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            FileReader fr;
            ruta = fc.getSelectedFile().getAbsolutePath();
        }else {
            // hubo error
            Errores e = new Errores(Errores.enumTipoError.ejecucion, "No se pudo obtener la ruta para guardar los archivos.");
        }
        
        
        return ruta;
    }
    
    public static String getArchivo () {
        String ruta = "";
        
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(0);
        int seleccion = fc.showOpenDialog(null);
        
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            FileReader fr;
            ruta = fc.getSelectedFile().getAbsolutePath();
        }else {
            // hubo error
            Errores e = new Errores(Errores.enumTipoError.ejecucion, "No se pudo obtener la ruta del archivo.");
        }
        return ruta ;
    }
    
    public static String getContenidoArchivo (){
        mensaje("Selecciona el archivo a leer.");
        String ruta = getArchivo ();
        
        String contenido ="";
        try {
            File archivo = new File (ruta);
            FileReader reader = new FileReader(archivo);
            BufferedReader bf = new BufferedReader(reader);
            String linea;
            while ((linea = bf.readLine()) !=  null){
                contenido += linea + "\n";
            }
            
        } catch (Exception e) {
            Errores ee = new Errores(Errores.enumTipoError.ejecucion, "No se ha podido leer el archivo " + ruta);
        }
        
        
        return contenido;
    }
    
    public static void mensaje(String contenido) {
        JOptionPane.showMessageDialog(null, contenido);
    }
    
    public static void abrirHoja () {
        
        
        
    }
    
}
