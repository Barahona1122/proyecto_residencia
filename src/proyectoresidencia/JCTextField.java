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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.undo.UndoManager;


public class JCTextField extends JTextField{

    private Dimension d = new Dimension(200,32);
    private String mostrar_texto = "";
    private Color phColor= new Color(0,0,0);
    private boolean HayQueMostrar = true;
  

    /** Constructor de clase */
    public JCTextField(){
        super();
        setSize(d);
        setPreferredSize(d);
        setVisible(true);
        setMargin( new Insets(3,6,3,6));
        //atento a cambios 
        getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                HayQueMostrar = (getText().length()>0) ? false:true ;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                HayQueMostrar = false;
            }

            @Override
            public void changedUpdate(DocumentEvent de) {}

        });       
        
  
    }

    public void setPlaceholder(String placeholder)
    {
        this.mostrar_texto=placeholder;
    }

    public String getPlaceholder()
    {
        return mostrar_texto;
    }

    public Color getPhColor() {
        return phColor;
    }

    public void setPhColor(Color phColor) {
        this.phColor = phColor;
    }    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //color de placeholder 
       g.setColor( new Color(phColor.getRed(),phColor.getGreen(),phColor.getBlue(),90));
        //g.setColor(Color.BLUE);
        //dibuja texto
        g.drawString((HayQueMostrar)?mostrar_texto:"", getMargin().left, (getSize().height)/2 + getFont().getSize()/2 );
      }

}//JCTextField:end

    
