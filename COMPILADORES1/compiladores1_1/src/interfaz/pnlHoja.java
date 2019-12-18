/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author David Ventura
 */
public class pnlHoja extends JPanel implements ActionListener {
    
    public String rutaGuardar= "";
    public String nombreArchivo="";
    public JTextArea txt;
    public JPopupMenu menu;
    public JMenuItem itemGuardar;
    public JMenuItem itemGuardarComo;
    public JMenuItem itemCorrer;
    public JScrollPane pn;
    
    public pnlHoja () {
        this.setBounds(0,0,1000,455);
        this.setLayout(null);
        this.nombreArchivo = JOptionPane.showInputDialog(null, "Ingrese el nombre del nuevo archivo");
        this.txt = new JTextArea();
        
        this.itemGuardar = new JMenuItem ();
        this.itemGuardar.setText("Guardar cambios");
        this.itemGuardar.addActionListener(this);
        
        this.itemCorrer = new JMenuItem();
        this.itemCorrer.setText("Ejecutar");
        this.itemCorrer.addActionListener(this);
        
        this.itemGuardarComo = new JMenuItem();
        this.itemGuardarComo.setText("Guardar como");
        this.itemGuardarComo.addActionListener(this);
        
        this.menu = new JPopupMenu ();
        this.menu.add(itemGuardar);
        this.menu.add(itemCorrer);
        this.menu.add(this.itemGuardarComo);
        
        
        
        this.txt.setBounds(0,0,1000,400);
        this.txt.setText("este es un texo");
        this.txt.setLineWrap(true);
        this.txt.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        this.txt.setComponentPopupMenu(menu);
        this.txt.setRows(5);
        this.pn = new JScrollPane();
        this.pn.setBounds(this.txt.getBounds());
        this.pn.setViewportView(this.txt);
        
        this.add(this.pn);
        //this.add(this.txt);
        
    }
        
    
    public void Guardar () {
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == this.itemCorrer) {
            compiladores1_1.Compiladores1_1.interpretar_(this.txt.getText());
        }else if (ae.getSource() == this.itemGuardar) {
            // guardar
            if (this.rutaGuardar.equals("")) {
                // si la ruta es en blanco, obtiene la ruta con la que desea guardar el archivo
                compiladores1_1.Compiladores1_1.mensaje("Selecciona la carpeta en la que deseas guardar el archivo");
                this.rutaGuardar = compiladores1_1.Compiladores1_1.getFolder();
            }
            
            // guardar en la ruta indicada
            // crear archivo 
            try {
                FileWriter archivo = new FileWriter (this.rutaGuardar + "\\" + this.nombreArchivo + ".NM");
                PrintWriter pw = new PrintWriter(archivo) ;
                pw.print(this.txt.getText());
                pw.close();
                archivo.close();
            } catch (Exception e) {Errores rr = new Errores(Errores.enumTipoError.ejecucion, "No se ha podido guardar el archivo " + this.nombreArchivo);}
            

        }else if (this.itemGuardarComo == ae.getSource()) {
            
            // guardar
            
            // obtiene una nueva ruta para guardar los datos
            compiladores1_1.Compiladores1_1.mensaje("Selecciona la carpeta en la que deseas guardar el archivo");
            this.rutaGuardar = compiladores1_1.Compiladores1_1.getFolder();

            
            // guardar en la ruta indicada
            // crear archivo 
            try {
                FileWriter archivo = new FileWriter (this.rutaGuardar + "\\" + this.nombreArchivo + ".NM");
                PrintWriter pw = new PrintWriter(archivo) ;
                pw.print(this.txt.getText());
                pw.close();
                archivo.close();
            } catch (Exception e) {Errores rr = new Errores(Errores.enumTipoError.ejecucion, "No se ha podido guardar el archivo " + this.nombreArchivo);}
            

            
            
        }
    }
    
    
}
