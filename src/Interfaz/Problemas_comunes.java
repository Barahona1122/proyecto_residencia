/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;


import static Interfaz.PantallaPrincipal.ESCRITORIO;
import Objetos_clases.Problema_clase;
import conexiones.ProyectoResidencia;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectoresidencia.FondoAdmin;

/**
 *
 * @author Carlos Barahona
 */
public class Problemas_comunes extends javax.swing.JInternalFrame {

    /**
     * Creates new form Problemas_comunes
     */
    public Problemas_comunes() {
        initComponents();
        llenar();
        Mostrar();
     //ACTUALIZAR.setVisible(false);
        
          //------------------------------------------------IMAGEN DE FONDO
        FondoAdmin p = new FondoAdmin("paso.jpg"); 
this.add( p , BorderLayout.EAST); 
p.repaint();
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
    
    
    private void llenar(){
    String [] titulos ={"# Problema","Nombre del problema","Asigado a: Subcategoria"};
    DefaultTableModel modelo = new DefaultTableModel(null, titulos);
    jTable1.setModel(modelo);
    String sql = "select id_problema, nombre_problema, nombre_subcategoria from vwproblema";
    
    jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(230);
    
    try{
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);
    String [] datos = new String[4];
    while(rs.next()){
    datos[0] = rs.getString("id_problema");
    datos[1] = rs.getString("nombre_problema");
    datos[2] = rs.getString("nombre_subcategoria");
    modelo.addRow(datos);
    }
    
        
        
    }catch(Exception e){
    JOptionPane.showMessageDialog(null,"ERROR"+e);
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
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MODIFICAR = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        PROBLEMA = new proyectoresidencia.JCTextField();
        SUBCATEGORIAS = new javax.swing.JComboBox<Problema_clase>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        ID_PROBLEMA.setEnabled(false);

        MODIFICAR.setText("Modificar");
        MODIFICAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODIFICARActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MODIFICAR);

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Problemas disponibles");

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
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        PROBLEMA.setPlaceholder("Escribe un problema");
        PROBLEMA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PROBLEMAKeyTyped(evt);
            }
        });

        SUBCATEGORIAS.setBackground(new java.awt.Color(51, 51, 255));
        SUBCATEGORIAS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar subcategoria" }));

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SUBCATEGORIAS, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PROBLEMA, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addContainerGap(249, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SUBCATEGORIAS, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PROBLEMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
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
     ResultSet resultado = st.executeQuery("select nombre_problema from problema where problema.nombre_problema like'"+PROBLEMA.getText()+"'");
if (resultado.next()==true){  //insert id en cliente.codecliente
    JOptionPane.showMessageDialog(null,"El registro ya existe \n por favor ingresa otro");
}else{
        
        if(PROBLEMA.getText().equals("") || SUBCATEGORIAS.getSelectedItem().equals("Seleccionar subcategoria")){ 
    JOptionPane.showMessageDialog(null, "Campos vacíos","MSG", JOptionPane.ERROR_MESSAGE );
    
}else{
        String sql ="insert into problema(id_subcategoria, nombre_problema) values (?,?)";

      /* String unoCombo =  (String) SUBCATEGORIAS.getSelectedItem(); //CONVIERTE EL JCOMBOBOX A STRING 
       String decomponerCOMBO[] = unoCombo.split("-");
       Integer subcategorias = Integer.parseInt(decomponerCOMBO[0]);*/
try{
  PreparedStatement pst = con.prepareStatement(sql);
                 pst.setInt(1, SUBCATEGORIAS.getItemAt(SUBCATEGORIAS.getSelectedIndex()).getId_subcategoria());
                 pst.setString(2, PROBLEMA.getText()); 
                 int n= pst.executeUpdate();
if(n>0){
SUBCATEGORIAS.setSelectedIndex(0);
PROBLEMA.setText(null);
}
    
}catch(Exception e){
 JOptionPane.showMessageDialog(null,"ERROR"+e);
}
}
        llenar();
} //FIN DEL ELSE
}catch(Exception e){
JOptionPane.showMessageDialog(null,"ERROR "+e);
}   
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed

        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MousePressed

    private void MODIFICARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODIFICARActionPerformed
//ACTUALIZAR.setEnabled(true);
        
              Modificar_problema pro = new Modificar_problema();
        ESCRITORIO.add(pro);
        pro.show();
        pro.toFront();
        Dimension desktopSize = ESCRITORIO.getSize();
        Dimension FrameSize = pro.getSize();
        pro.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
   
        
//jButton1.setVisible(false);
        int fila = jTable1.getSelectedRow();
 if(fila>=0){
    String hola = SUBCATEGORIAS.getSelectedItem().toString();
     Modificar_problema.ID_PROBLEMA.setText(jTable1.getValueAt(fila, 0).toString());            
         Modificar_problema.PROBLEMA.setText(jTable1.getValueAt(fila, 1).toString());
            String subca=(String)jTable1.getValueAt(fila,2);
            //Modificar_problema.SUBCATEGORIAS.setSelectedItem(subca); //COMBO_CATEGORIA.getSelectedIndex()).getId_categoria()
    Modificar_problema.SUBCATEGORIAS.getItemAt(SUBCATEGORIAS.getSelectedIndex());
    
 }
 
 
       
 
        // TODO add your handling code here:
    }//GEN-LAST:event_MODIFICARActionPerformed

    private void PROBLEMAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PROBLEMAKeyTyped
char c = evt.getKeyChar();
if(Character.isDigit(c)){
getToolkit().beep();
evt.consume();
JOptionPane.showMessageDialog(null,"Carácter no permitido","Aviso",JOptionPane.ERROR_MESSAGE);
PROBLEMA.setCursor(null);
}
else if((int)evt.getKeyChar()>32 && (int)evt.getKeyChar()<=47 
        || (int)evt.getKeyChar()>57 && (int)evt.getKeyChar()<=64 ||
        (int)evt.getKeyChar()>90 && (int)evt.getKeyChar()<=96 || 
        (int)evt.getKeyChar()>122 && (int)evt.getKeyChar()<=255
        ){

getToolkit().beep();
evt.consume();
JOptionPane.showMessageDialog(null,"Carácter no permitido","Aviso",JOptionPane.ERROR_MESSAGE);
PROBLEMA.setCursor(null);
}
        
// TODO add your handling code here:
    }//GEN-LAST:event_PROBLEMAKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ID_PROBLEMA;
    private javax.swing.JMenuItem MODIFICAR;
    private proyectoresidencia.JCTextField PROBLEMA;
    private javax.swing.JComboBox<Problema_clase> SUBCATEGORIAS;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
ProyectoResidencia cc = new ProyectoResidencia();
Connection con=cc.conexion();
}