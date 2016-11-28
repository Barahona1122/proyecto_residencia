/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoresidencia;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Carlos Barahona
 */
public class Fondo_Locacion extends javax.swing.JPanel { 

String imagen = ""; 
 
public Fondo_Locacion(String imagen){ 
this.imagen = imagen; 
this.setSize(700,370); 
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