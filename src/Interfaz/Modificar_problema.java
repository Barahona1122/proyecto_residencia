/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Objetos_clases.Problema_clase;
import conexiones.ProyectoResidencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Barahona
 */
public class Modificar_problema extends javax.swing.JInternalFrame {

    /**
     * Creates new form Modificar_problemas
     */
    public Modificar_problema() {
        initComponents();
        Mostrar();
    }
     private void Mostrar(){
           //this.SOPORTE.removeAllItems();
 try{
 Statement stm = con.createStatement();
 ResultSet rs = stm.executeQuery("select * from subcategoria where activo=1");
 
 
 while(rs.next()){
 Problema_clase pro = new Problema_clase();
 pro.setId_subcategoria(rs.getInt("id_subcategoria"));
 pro.setNombre_problema(rs.getString("nombre_subcategoria"));
 
     
     this.SUBCATEGORIAS.addItem(pro);
 }    
 }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error en la conexion"+e);
 }
         }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ID_PROBLEMA = new javax.swing.JTextField();
        SUBCATEGORIAS = new javax.swing.JComboBox<Problema_clase>();
        PROBLEMA = new proyectoresidencia.JCTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Modificar problema");

        SUBCATEGORIAS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar subcategoria" }));

        PROBLEMA.setPlaceholder("Escribir subcategoria");

        jButton1.setText("Actualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(PROBLEMA, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SUBCATEGORIAS, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(SUBCATEGORIAS, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PROBLEMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       // TODO add your handling code here:
        
        if(SUBCATEGORIAS.getSelectedIndex() ==0  && ID_PROBLEMA.getText().length()==0 && PROBLEMA.getText().length()==0)
       {
         JOptionPane.showMessageDialog(null,"Campos vacíos");
       }else{
     /*   String unoCombo =  (String) SUBCATEGORIAS.getSelectedItem(); //CONVIERTE EL JCOMBOBOX A STRING 
       String decomponerCOMBO[] = unoCombo.split("-");
       Integer resultadoCombo = Integer.parseInt(decomponerCOMBO[0]);  */
 
       try{
    PreparedStatement pst = con.prepareStatement("UPDATE problema SET nombre_problema='"+PROBLEMA.getText()+"' WHERE id_problema='"+ID_PROBLEMA.getText()+"'");
            pst.executeUpdate();     
}catch(Exception e){
JOptionPane.showMessageDialog(null,"Error"+e);
}
            try{
    PreparedStatement pst = con.prepareStatement("UPDATE problema SET id_subcategoria='"+SUBCATEGORIAS.getItemAt(SUBCATEGORIAS.getSelectedIndex()).getId_subcategoria()+"' WHERE id_problema='"+ID_PROBLEMA.getText()+"'");
            pst.executeUpdate();      
}catch(Exception e){
JOptionPane.showMessageDialog(null,"Error"+e);
}
            
            PROBLEMA.setText(null);
            SUBCATEGORIAS.setSelectedIndex(0);
            ID_PROBLEMA.setText(null);
       }
           //llenar();      
           jButton1.setVisible(true);
          // ACTUALIZAR.setEnabled(false); 
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
this.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField ID_PROBLEMA;
    public static proyectoresidencia.JCTextField PROBLEMA;
    public static javax.swing.JComboBox<Problema_clase> SUBCATEGORIAS;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    // End of variables declaration//GEN-END:variables
ProyectoResidencia cc = new ProyectoResidencia();
Connection con=cc.conexion();
}