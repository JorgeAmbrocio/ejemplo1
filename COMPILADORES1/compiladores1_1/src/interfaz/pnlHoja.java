/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

/**
 *
 * @author David Ventura
 */
public class pnlHoja extends JPanel implements ActionListener {
    
    public String rutaGuardar;
    public String nombreArchivo;
    public JTextArea txt;
    public JPopupMenu menu;
    public JMenuItem itemGuardar;
    public JMenuItem itemCorrer;
    
    public pnlHoja () {
        this.setBounds(0,0,1000,455);
        this.setLayout(null);
        this.nombreArchivo = JOptionPane.showInputDialog(null, "Ingrese el nombre del nuevo archivo");
        this.txt = new JTextArea();
        
        this.itemGuardar = new JMenuItem ();
        this.itemGuardar.setText("Guardar cambios");
        this.itemGuardar.addActionListener(this);
        
        this.itemCorrer = new JMenuItem();
        this.itemCorrer.setText("Correr archivo");
        this.itemCorrer.addActionListener(this);
        
        this.menu = new JPopupMenu ();
        this.menu.add(itemGuardar);
        this.menu.add(itemCorrer);
        
        
        this.txt.setBounds(this.getBounds());
        this.txt.setLineWrap(true);
        this.txt.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        this.txt.setComponentPopupMenu(menu);
        
        
        this.add(this.txt);
        
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
                
            }
            
            // guardar en la ruta indicada
            
            
        }
    }
    
    
}
