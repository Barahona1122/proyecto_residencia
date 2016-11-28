/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoresidencia;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 * @web http://jc-mouse.blogspot.com/
 * @author Mouse
 */
public class MiRender extends DefaultTableCellRenderer{    
    Font normal = new Font( "Comic Sans",Font.PLAIN,13 );
 private Component componente;

    @Override 
    public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column ) 
    {
       // setEnabled(table == null || table.isEnabled()); 
     componente = super.getTableCellRendererComponent(table, value, selected, focused, row, column);
     
     if (row%2==0){
     setBackground(Color.WHITE);//color de fondo
     }else{
     setBackground(new Color(240,240,240));//color de fondo[0,102,153]
     }
        
        //setBackground(Color.WHITE);//color de fondo
      // table.setBackground(Color.BLACK);
        table.setFont(normal);//tipo de fuente
        table.setForeground(Color.black);//color de texto
        //setHorizontalAlignment(0);//derecha
                
       
        /*--------*/
        if(String.valueOf(table.getValueAt(row,column)).equals("Nuevo")){
            setBackground(Color.RED); 
            table.setFont(normal);                
            //setHorizontalAlignment(0);//centro
        }  
        
     
        /*--------*/
        if(String.valueOf(table.getValueAt(row,column)).equals("Atendido")){
            setBackground(Color.GREEN);         
            table.setFont(normal);                
            //setHorizontalAlignment(0);//centro
        }
     
         /*--------*/   
        if(String.valueOf(table.getValueAt(row,column)).equals("Asignado")){
            setBackground(Color.YELLOW); 
            table.setFont(normal);                
            //setHorizontalAlignment(0);//centro
        }  
        
         /*--------*/   
        if(String.valueOf(table.getValueAt(row,column)).equals("Asignar a...")){
            setBackground(Color.RED); 
            table.setFont(normal);                
            //setHorizontalAlignment(0);//centro
        }
  
        
        
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);         
        return this;
 }
    
 /*
 private boolean isNumber(String cadena){
         try {
             Double.parseDouble(cadena.replace(",", ""));
         } catch (NumberFormatException nfe){
             String newCadena = cadena.replace(".", "").replace(',', '.');
             try{
                 Double.parseDouble(newCadena);
             } catch (NumberFormatException nfe2){
                 return false;
             }
        }
         return true;
    }*/

}