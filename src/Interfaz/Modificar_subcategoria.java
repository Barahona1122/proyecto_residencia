/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Objetos_clases.Modificar_subcategoria_class;
import Objetos_clases.Ver_categoria;
import conexiones.ProyectoResidencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos Barahona
 */
public class Modificar_subcategoria extends javax.swing.JInternalFrame {

    /**
     * Creates new form Modificar_subcategoria
     */
    public Modificar_subcategoria() {
        initComponents();
        LlenarCategoria();
       
    }
    private void LlenarCategoria(){
    try{
 Statement stm = con.createStatement();
 ResultSet rs = stm.executeQuery("select * from categoria");
 
 while(rs.next()){
 Ver_categoria cate = new Ver_categoria();
cate.setId_categoria(rs.getInt("id_categoria"));
cate.setNombre_categoria(rs.getString("nombre_categoria"));
     this.COMBO_CATEGORIA.addItem(cate);
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

        ID_SUBCATEGORIA = new javax.swing.JTextField();
        TXT_SUBCATEGORIA = new proyectoresidencia.JCTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        COMBO_CATEGORIA = new javax.swing.JComboBox<Ver_categoria>();

        setClosable(true);
        setTitle("Modificar subcategorias");

        TXT_SUBCATEGORIA.setPlaceholder("Escribir subcategoria");

        jButton1.setText("Modificar");
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

        COMBO_CATEGORIA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar categoria" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(0, 71, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(COMBO_CATEGORIA, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(TXT_SUBCATEGORIA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(COMBO_CATEGORIA, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TXT_SUBCATEGORIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton1)))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
this.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  if(COMBO_CATEGORIA.getSelectedItem().equals("Seleccionar categoria") || ID_SUBCATEGORIA.getText().equals("") || TXT_SUBCATEGORIA.getText().equals(""))
       {
         JOptionPane.showMessageDialog(null,"Campos vacíos","Aviso",JOptionPane.ERROR_MESSAGE);
       }else{
       /* String unoCombo =  (String) COMBO_CATEGORIA.getSelectedItem(); //CONVIERTE EL JCOMBOBOX A STRING 
       String decomponerCOMBO[] = unoCombo.split("-");
       Integer resultadoCombo = Integer.parseInt(decomponerCOMBO[0]); */ 
 
       
       try{
    PreparedStatement pst = con.prepareStatement("UPDATE subcategoria SET id_categoria='"+COMBO_CATEGORIA.getItemAt(COMBO_CATEGORIA.getSelectedIndex()).getId_categoria()+"',nombre_subcategoria='"+TXT_SUBCATEGORIA.getText()+"'WHERE id_subcategoria='"+ID_SUBCATEGORIA.getText()+"'");
           int n = pst.executeUpdate();
            Menu_subcategorias ni = new Menu_subcategorias();
ni.llenar();
           if(n>0){
               
               
          JOptionPane.showMessageDialog(null,"Acutalización exitosa","Aviso",JOptionPane.INFORMATION_MESSAGE);

           ID_SUBCATEGORIA.setText(null);
            COMBO_CATEGORIA.setSelectedIndex(0);
            TXT_SUBCATEGORIA.setText(null);
           }
           
           
}catch(Exception e){
JOptionPane.showMessageDialog(null,"Error"+e);
}
     
            
       }
    
                          // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox<Ver_categoria> COMBO_CATEGORIA;
    public static javax.swing.JTextField ID_SUBCATEGORIA;
    public static proyectoresidencia.JCTextField TXT_SUBCATEGORIA;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
ProyectoResidencia cc = new ProyectoResidencia();
Connection con=cc.conexion();
}

