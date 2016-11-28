/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoresidencia;

/**
 *
 * @author Carlos Barahona
 */
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Fondo_empresas extends javax.swing.JPanel { 

String imagen = ""; 

public Fondo_empresas(String imagen){ 
this.imagen = imagen; 
this.setSize(300,176); 
} 

@Override 
public void paint(Graphics g){ 
Dimension tamanio = getSize(); 
ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/imagenes/" + imagen)); 
g.drawImage(imagenFondo.getImage(),0,0,tamanio.width, tamanio.height, null); 
setOpaque(false); 
super.paintComponent(g); 
}
}