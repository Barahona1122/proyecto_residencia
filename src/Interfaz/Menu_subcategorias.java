/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import static Interfaz.PantallaPrincipal.ESCRITORIO;
import Objetos_clases.Modificar_subcategoria_class;
import conexiones.ProyectoResidencia;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectoresidencia.FondoAdmin;

/**
 *
 * @author Carlos Barahona
 */
public class Menu_subcategorias extends javax.swing.JInternalFrame {

    /**
     * Creates new form Menu_subcategorias
     */
    public Menu_subcategorias() {
        initComponents();
        llenar();
        Mostrar();
        
          //------------------------------------------------IMAGEN DE FONDO
        FondoAdmin p = new FondoAdmin("paso.jpg"); 
this.add( p , BorderLayout.EAST); 
p.repaint();
    }
    
    void Mostrar(){
           //this.SOPORTE.removeAllItems();
 try{
 Statement stm = con.createStatement();
 ResultSet rs = stm.executeQuery("select * from categoria");
 String datos[] = new String[3];
 
 while(rs.next()){
 
     Modificar_subcategoria_class mo = new Modificar_subcategoria_class();
     mo.setNombre_categoria(rs.getString("nombre_categoria"));
     mo.setId_subcategoria(rs.getInt("id_categoria"));
     this.SUBCATEGORIAS.addItem(mo);
 }    
 }catch(Exception e){
     JOptionPane.showMessageDialog(null,"Error en la conexion"+e);
 }
         }
    
    public void llenar(){
    String titulos[] ={"# Subcategoria","Nombre de la subcategoria","Nombre de la categoria"};
    DefaultTableModel modelo = new DefaultTableModel(null, titulos);
    jTable1.setModel(modelo);
    String sql="select id_subcategoria, nombre_subcategoria, nombre_categoria from vwcategoria";
            int[] anchos = {40, 100, 200}; //ASIGNAR TAMAÑO A CADA UNA DE LAS CELDAS
for(int i = 0; i < jTable1.getColumnCount(); i++) {
jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
}
    try{
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);
    String [] datos = new String[4];
    while(rs.next()){
    datos[0]= rs.getString("id_subcategoria");
    datos[1]= rs.getString("nombre_subcategoria");
    datos[2]= rs.getString("nombre_categoria");
    modelo.addRow(datos);
    
    }
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

        ID_SUBCATEGORIA = new javax.swing.JTextField();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MODIFICAR = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        SUBCATEGORIA_TEXTO = new proyectoresidencia.JCTextField();
        REGISTRAR = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        SUBCATEGORIAS = new javax.swing.JComboBox<Modificar_subcategoria_class>();

        MODIFICAR.setText("Modificar");
        MODIFICAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODIFICARActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MODIFICAR);

        setClosable(true);
        setResizable(true);
        setTitle("Sucategorías disponibles");
        setToolTipText("");

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
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        SUBCATEGORIA_TEXTO.setPlaceholder("Escribir subcategoria");
        SUBCATEGORIA_TEXTO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SUBCATEGORIA_TEXTOKeyTyped(evt);
            }
        });

        REGISTRAR.setBackground(new java.awt.Color(0, 102, 153));
        REGISTRAR.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        REGISTRAR.setForeground(new java.awt.Color(255, 255, 255));
        REGISTRAR.setText("Registrar");
        REGISTRAR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REGISTRARActionPerformed(evt);
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

        SUBCATEGORIAS.setBackground(new java.awt.Color(51, 51, 255));
        SUBCATEGORIAS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar categoria" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SUBCATEGORIAS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SUBCATEGORIA_TEXTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(REGISTRAR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SUBCATEGORIAS, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SUBCATEGORIA_TEXTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(REGISTRAR)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
this.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REGISTRARActionPerformed
try{
 
     Statement st = con.createStatement();
     ResultSet resultado = st.executeQuery("select nombre_subcategoria from subcategoria where subcategoria.nombre_subcategoria like'"+SUBCATEGORIA_TEXTO.getText()+"'");
if (resultado.next()==true){  //insert id en cliente.codecliente
    JOptionPane.showMessageDialog(null,"El registro ya existe \n por favor ingresa otro");
}else{
        
        if(SUBCATEGORIA_TEXTO.getText().equals("") || SUBCATEGORIAS.getSelectedItem().equals("Seleccionar categoria")){ 
    JOptionPane.showMessageDialog(null, "Campos vacíos","Aviso",JOptionPane.ERROR_MESSAGE);
    
}else{
        String sql ="insert into subcategoria(id_categoria, nombre_subcategoria) values (?,?)";

      /* String unoCombo =  (String) SUBCATEGORIAS.getSelectedItem(); //CONVIERTE EL JCOMBOBOX A STRING 
       String decomponerCOMBO[] = unoCombo.split("-");
       Integer subcategorias = Integer.parseInt(decomponerCOMBO[0]);*/
try{
  PreparedStatement pst = con.prepareStatement(sql);
                 pst.setInt(1, SUBCATEGORIAS.getItemAt(SUBCATEGORIAS.getSelectedIndex()).getId_subcategoria());
                 pst.setString(2, SUBCATEGORIA_TEXTO.getText()); 
                 int n= pst.executeUpdate();
if(n>0){
SUBCATEGORIAS.setSelectedIndex(0);
SUBCATEGORIA_TEXTO.setText(null);
}
    
}catch(Exception e){
 JOptionPane.showMessageDialog(null,"ERROR"+e);
}
}
        llenar();
}//FIN DEL ELSE
}catch(Exception ex){
JOptionPane.showMessageDialog(null,"ERROR "+ex);
}
        
// TODO add your handling code here:
    }//GEN-LAST:event_REGISTRARActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
//ACTUALIZAR.setVisible(true);
REGISTRAR.setVisible(false);
        int fila = jTable1.getSelectedRow();
 if(fila>=0){
    String hola = SUBCATEGORIAS.getSelectedItem().toString();
     ID_SUBCATEGORIA.setText(jTable1.getValueAt(fila, 0).toString());            
            SUBCATEGORIA_TEXTO.setText(jTable1.getValueAt(fila, 1).toString());
            String subca=(String)jTable1.getValueAt(fila,2);
            SUBCATEGORIAS.setSelectedItem(subca);
    
 }
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed

        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MousePressed

    private void MODIFICARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODIFICARActionPerformed
        
      Modificar_subcategoria cat = new Modificar_subcategoria();
        ESCRITORIO.add(cat);
        cat.show();
        cat.toFront();
        Dimension desktopSize = ESCRITORIO.getSize();
        Dimension FrameSize = cat.getSize();
        cat.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
      
        /*ACTUALIZAR.setEnabled(true);
REGISTRAR.setVisible(false);
        int fila = jTable1.getSelectedRow();
 if(fila>=0){
     ID_SUBCATEGORIA.setText(jTable1.getValueAt(fila, 0).toString());            
     SUBCATEGORIA_TEXTO.setText(jTable1.getValueAt(fila, 1).toString());    
    SUBCATEGORIAS.setSelectedItem(jTable1.getValueAt(fila, 0).toString());
 }*/
        

        int fila = jTable1.getSelectedRow();
 if(fila>=0){
 Modificar_subcategoria.ID_SUBCATEGORIA.setText(jTable1.getValueAt(fila, 0).toString()); //SUBCATEGORIA      
 Modificar_subcategoria.TXT_SUBCATEGORIA.setText(jTable1.getValueAt(fila, 1).toString());//NOMBRE SUBCATEGORIA
 //Modificar_subcategoria.COMBO_CATEGORIA.setSelectedItem(jTable1.getValueAt(fila, 0).toString());
 }
        
// TODO add your handling code here:
    }//GEN-LAST:event_MODIFICARActionPerformed

    private void SUBCATEGORIA_TEXTOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SUBCATEGORIA_TEXTOKeyTyped
char c = evt.getKeyChar();
if(Character.isDigit(c)){
getToolkit().beep();
evt.consume();
JOptionPane.showMessageDialog(null,"Carácter no permitido","Aviso",JOptionPane.ERROR_MESSAGE);
SUBCATEGORIA_TEXTO.setCursor(null);
}
else if((int)evt.getKeyChar()>32 && (int)evt.getKeyChar()<=47 
        || (int)evt.getKeyChar()>57 && (int)evt.getKeyChar()<=64 ||
        (int)evt.getKeyChar()>90 && (int)evt.getKeyChar()<=96 || 
        (int)evt.getKeyChar()>122 && (int)evt.getKeyChar()<=255
        ){

getToolkit().beep();
evt.consume();
JOptionPane.showMessageDialog(null,"Carácter no permitido","Aviso",JOptionPane.ERROR_MESSAGE);
SUBCATEGORIA_TEXTO.setCursor(null);
}
        
// TODO add your handling code here:
    }//GEN-LAST:event_SUBCATEGORIA_TEXTOKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ID_SUBCATEGORIA;
    private javax.swing.JMenuItem MODIFICAR;
    private javax.swing.JButton REGISTRAR;
    private javax.swing.JComboBox<Modificar_subcategoria_class> SUBCATEGORIAS;
    private proyectoresidencia.JCTextField SUBCATEGORIA_TEXTO;
    private javax.swing.JButton jButton2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
ProyectoResidencia cc = new ProyectoResidencia();
Connection con=cc.conexion();
}
