/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import conexiones.ProyectoResidencia;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import proyectoresidencia.Fondo_Locacion;

/**
 *
 * @author Carlos Barahona
 */
public class Locacion extends javax.swing.JInternalFrame {

    /**
     * Creates new form Locacion
     */
    public Locacion() {
        initComponents();
        llenar();
        Fondo_Locacion p = new Fondo_Locacion("paso.jpg"); 
this.add( p , BorderLayout.EAST); 
p.repaint();

    }
    private void llenar(){
    String [] titulos = {"# Locacion", "Nombre de la locacion"};
    DefaultTableModel modelo = new DefaultTableModel(null, titulos);
    jTable1.setModel(modelo);
    String sql="select id_locacion, nombre_locacion from locacion";
    try{
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);
    String [] datos = new String[2];
    while(rs.next()){
    datos[0]= rs.getString("id_locacion");
    datos[1]= rs.getString("nombre_locacion");
    modelo.addRow(datos);
    }
    //-----------------------------------------------------------ACTUALIZA EL NOMBRE_LOCACION DE LA BD
      modelo.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
   
           if(e.getType() == TableModelEvent.UPDATE){
           int columna = e.getColumn();
           int fila= e.getFirstRow();
           
            //----------------------------------------------------------------------columna numero 1
               if(columna==1){
           try{
           PreparedStatement pst = con.prepareStatement("UPDATE locacion SET nombre_locacion='"+jTable1.getValueAt(fila,columna)+"'WHERE id_locacion='"+jTable1.getValueAt(fila, 0)+"'");
           pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Correcto","MSG", JOptionPane.INFORMATION_MESSAGE);
           }catch(Exception ex){
           JOptionPane.showMessageDialog(null,"Eror:"+ex);
           }
           }
           }
            }
               });
      //----------------------------------------------------POR SI SURGE ALGUN ERROR EN LA EJECUCION
    }catch(Exception e){
    JOptionPane.showMessageDialog(null,"ERROR: "+"MSG"+JOptionPane.WARNING_MESSAGE+e);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        LOCACION = new proyectoresidencia.JCTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Lugares disponibles");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(0, 0, 255));
        jScrollPane1.setViewportView(jTable1);

        LOCACION.setPlaceholder("Nombre de la locacion");
        LOCACION.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                LOCACIONKeyTyped(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 102, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Registrar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 153));
        jButton2.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cancelar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LOCACION, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LOCACION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
this.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

 try{
 
     Statement st = con.createStatement();
     ResultSet resultado = st.executeQuery("select nombre_locacion from locacion where locacion.nombre_locacion like'"+LOCACION.getText()+"'");
if (resultado.next()==true){  //insert id en cliente.codecliente
    JOptionPane.showMessageDialog(null,"El registro ya existe \n por favor ingresa otro");
}else{

      if(LOCACION.getText().equals("")){ 
    JOptionPane.showMessageDialog(null, "Campos vacíos","Aviso",JOptionPane.ERROR_MESSAGE);
}else{
        String sql ="insert into locacion(nombre_locacion) values (?)";
try{
  PreparedStatement pst = con.prepareStatement(sql);
pst.setString(1, LOCACION.getText()); 
 int n= pst.executeUpdate();
if(n>0){
LOCACION.setText(null);
}   
}catch(Exception e){
 JOptionPane.showMessageDialog(null,"ERROR"+e);
}
}

    
}
llenar();//mensaje de codeclient ya existente
  
 }catch(Exception ex){
 JOptionPane.showMessageDialog(null,"ERROR: "+ex);
 }
 
        
      
        
        
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void LOCACIONKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LOCACIONKeyTyped
char c = evt.getKeyChar();
if(Character.isDigit(c)){
getToolkit().beep();
evt.consume();
JOptionPane.showMessageDialog(null,"Carácter no permitido","Aviso",JOptionPane.ERROR_MESSAGE);
LOCACION.setCursor(null);
}
else if((int)evt.getKeyChar()>32 && (int)evt.getKeyChar()<=47 
        || (int)evt.getKeyChar()>57 && (int)evt.getKeyChar()<=64 ||
        (int)evt.getKeyChar()>90 && (int)evt.getKeyChar()<=96 || 
        (int)evt.getKeyChar()>122 && (int)evt.getKeyChar()<=255
        ){

getToolkit().beep();
evt.consume();
JOptionPane.showMessageDialog(null,"Carácter no permitido","Aviso",JOptionPane.ERROR_MESSAGE);
LOCACION.setCursor(null);
}
        
// TODO add your handling code here:
    }//GEN-LAST:event_LOCACIONKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private proyectoresidencia.JCTextField LOCACION;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
ProyectoResidencia cc = new ProyectoResidencia();
Connection con=cc.conexion();
}
