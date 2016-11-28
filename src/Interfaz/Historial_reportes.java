/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import conexiones.ProyectoResidencia;
import java.awt.BorderLayout;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import proyectoresidencia.Exportar_excel;
import proyectoresidencia.FondoAdmin;
import proyectoresidencia.Fondo_Locacion;

/**
 *
 * @author Carlos Barahona
 */
public class Historial_reportes extends javax.swing.JInternalFrame {

    /**
     * Creates new form Historial_reportes
     */
    public Historial_reportes() {
        initComponents();
       FondoAdmin p = new FondoAdmin("paso.jpg"); 
this.add( p , BorderLayout.EAST); 
p.repaint();

//--------------ICONO PARA EL CALENDARIO
URL url = getClass().getResource("/iconos/calendario.png");  
FECHA_1.setIcon(new ImageIcon(url)); 
URL url2 = getClass().getResource("/iconos/calendario.png");  
FECHA_2.setIcon(new ImageIcon(url2)); 

BUS.setVisible(false);
COMBO_USUARIO.setVisible(false);

FECHA_1.setVisible(false);
FECHA_2.setVisible(false);
BUSCAR_FECHA.setVisible(false);

admin();
    }
    
        private void admin(){
    try{
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("select nombre, id_usuario from usuario where id_perfil=3"); 
    String [] datos = new String[2];
    while(rs.next()){
        
        datos[0]=rs.getString("nombre");
        this.COMBO_USUARIO.addItem(datos[0]);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        COMBO_BUSCA = new javax.swing.JComboBox();
        FECHA_1 = new com.toedter.calendar.JDateChooser();
        FECHA_2 = new com.toedter.calendar.JDateChooser();
        BUSCAR_FECHA = new javax.swing.JButton();
        COMBO_USUARIO = new javax.swing.JComboBox();
        BUS = new javax.swing.JButton();
        backgroundMenuBar1 = new proyectoresidencia.BackgroundMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Historial de reportes");

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setBackground(new java.awt.Color(0, 102, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Buscar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        COMBO_BUSCA.setBackground(new java.awt.Color(51, 51, 255));
        COMBO_BUSCA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Buscar por...", "General", "Persona" }));
        COMBO_BUSCA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        FECHA_1.setDateFormatString("yyyy-MM-dd");

        FECHA_2.setDateFormatString("yyyy-MM-dd");

        BUSCAR_FECHA.setBackground(new java.awt.Color(0, 102, 153));
        BUSCAR_FECHA.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        BUSCAR_FECHA.setForeground(new java.awt.Color(255, 255, 255));
        BUSCAR_FECHA.setText("Buscar");
        BUSCAR_FECHA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BUSCAR_FECHA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUSCAR_FECHAActionPerformed(evt);
            }
        });

        COMBO_USUARIO.setBackground(new java.awt.Color(51, 51, 255));
        COMBO_USUARIO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar usuario..." }));
        COMBO_USUARIO.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        BUS.setBackground(new java.awt.Color(0, 102, 153));
        BUS.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        BUS.setForeground(new java.awt.Color(255, 255, 255));
        BUS.setText("Buscar");
        BUS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BUS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUSActionPerformed(evt);
            }
        });

        jMenu1.setText("Archivo");
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Guardar");
        jMenuItem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Salir");
        jMenuItem2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        backgroundMenuBar1.add(jMenu1);

        setJMenuBar(backgroundMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1055, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(COMBO_BUSCA, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(COMBO_USUARIO, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BUS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FECHA_1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FECHA_2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BUSCAR_FECHA)
                        .addGap(19, 19, 19)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(COMBO_USUARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BUS))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(COMBO_BUSCA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addComponent(FECHA_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(BUSCAR_FECHA)
                        .addComponent(FECHA_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String busca = COMBO_BUSCA.getSelectedItem().toString();
     if(busca.equals("General")){ //---------------------CUANDO SE SELECCIONE LA OPCION GENERAL
        BUS.setVisible(false);
        COMBO_USUARIO.setVisible(false); 
        BUSCAR_FECHA.setVisible(true);
        FECHA_1.setVisible(true);
        FECHA_2.setVisible(true); 
        String [] titulos  = {"Fecha inicial","Fecha final","Total peticiones","Total dias", "Categoria","SubCategoria" };  
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        jTable1.setModel(modelo);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(230);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(300);
       String sql = "select fecha_in_nohora, fecha_fin_nohora, Peticiones, sumdias, nombre_categoria, nombre_subcategoria from vwsubcategoria";
    try{
       Statement st = con.createStatement();
       ResultSet rs = st.executeQuery(sql);
       String [] datos = new String [6];
    while(rs.next()){
       datos[0] = rs.getString("fecha_in_nohora");    
       datos[1] = rs.getString("fecha_fin_nohora");
       datos[2] = rs.getString("Peticiones");
       datos[3] = rs.getString("sumdias");
       datos[4] = rs.getString("nombre_categoria");
       datos[5] = rs.getString("nombre_subcategoria");
       modelo.addRow(datos);
       }     
   }catch(Exception e){
    JOptionPane.showMessageDialog(null,"ERROR"+"MSG"+JOptionPane.WARNING_MESSAGE+e);
    }
  }   
     if(busca.equals("Persona")){  //---------------------CUANDO SE SELECCIONE LA OPCION PERSONA  
        BUS.setVisible(true);
        COMBO_USUARIO.setVisible(true);
        BUSCAR_FECHA.setVisible(true);
        FECHA_1.setVisible(true);
        FECHA_2.setVisible(true);  
        String [] titulos  = {"Fecha inicial","Fech final","Total peticiones","Total días", "Categoria","SubCategoria", "Atendido por" };   
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        jTable1.setModel(modelo);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(230);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(300);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(80);
    
       GregorianCalendar objFecha = new GregorianCalendar();
     int mes;
       mes=objFecha.get(Calendar.MONTH)+1;
       String sql = "select fecha_in_nohora, fecha_fin_nohora, total, sumdias, nombre_categoria, nombre_subcategoria, atendio from estadistico";
     try{
       Statement st = con.createStatement();
       ResultSet rs = st.executeQuery(sql);
       String [] datos = new String [7];     
      while(rs.next()){
       datos[0] = rs.getString("fecha_in_nohora");    
       datos[1] = rs.getString("fecha_fin_nohora");
       datos[2] = rs.getString("total");
       datos[3] = rs.getString("sumdias");
       datos[4] = rs.getString("nombre_categoria");
       datos[5] = rs.getString("nombre_subcategoria");
       datos[6] = rs.getString("atendio");      
       modelo.addRow(datos);
       }  
        }catch(Exception e){
    JOptionPane.showMessageDialog(null,"ERROR"+"MSG"+JOptionPane.WARNING_MESSAGE+e);
    }
      }
        if(busca.equals("Buscar por...")){ //-------------------CUANDO SE SELECCIONE LA OPCION BUSCAR POR..
        BUSCAR_FECHA.setVisible(false);
        FECHA_1.setVisible(false);
        FECHA_2.setVisible(false);   
        COMBO_USUARIO.setVisible(false);
        BUS.setVisible(false);   
        String [] titulos  = { "Contenido no disponible" };  
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        jTable1.setModel(modelo);
      }
        


// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BUSCAR_FECHAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUSCAR_FECHAActionPerformed

           String busca = COMBO_BUSCA.getSelectedItem().toString();
      if(busca.equals("Persona")){ //---------------------CUANDO SE SELECCIONE LA OPCION GENERAL
        try{
            
                                Date dato1 = FECHA_1.getDate();
     Date dato2 = FECHA_2.getDate();
     if(dato1==null || dato2==null){
         JOptionPane.showMessageDialog(null,"Seleccionar una fecha valida","Aviso",JOptionPane.ERROR_MESSAGE);
     
     }else{
     
        String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FECHA_1.getDate());
        String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FECHA_2.getDate());
        String [] titulos  = {"Fecha inicial","Fech final","Total peticiones","Total días", "Categoria","SubCategoria", "Atendido por" };
           
    DefaultTableModel modelo = new DefaultTableModel(null, titulos);
    jTable1.setModel(modelo);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(230);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(300);
         jTable1.getColumnModel().getColumn(6).setPreferredWidth(80);
    
    
    String sql = "select fecha_in_nohora, fecha_fin_nohora, total, sumdias, nombre_categoria, nombre_subcategoria, atendio from estadistico where fecha_in_nohora>='"+fecha1+"'and estadistico.fecha_in_nohora<='"+fecha2+"'";
    Statement  sts = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = sts.executeQuery(sql);    
         String [] datos = new String [7];     
      while(rs.next()){
       datos[0] = rs.getString("fecha_in_nohora");    
       datos[1] = rs.getString("fecha_fin_nohora");
       datos[2] = rs.getString("total");
       datos[3] = rs.getString("sumdias");
       datos[4] = rs.getString("nombre_categoria");
       datos[5] = rs.getString("nombre_subcategoria");
       datos[6] = rs.getString("atendio");      
       modelo.addRow(datos);
       } 
 
        }
        }catch(Exception e){
 JOptionPane.showMessageDialog(null,"ERORR: "+JOptionPane.WARNING_MESSAGE+e);
 }     
      }
      
    if(busca.equals("General")){ //---------------------CUANDO SE SELECCIONE LA OPCION GENERAL
        try{
    Date dato1 = FECHA_1.getDate();
     Date dato2 = FECHA_2.getDate();
     if(dato1==null || dato2==null){
         JOptionPane.showMessageDialog(null,"Seleccionar una fecha valida","Aviso",JOptionPane.ERROR_MESSAGE);
     }else{ 
          
        String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FECHA_1.getDate());
        String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FECHA_2.getDate());
        
        String [] titulos  = {"Fecha inicial","Fecha final","Total peticiones","Total dias", "Categoria","SubCategoria" };
           
    DefaultTableModel modelo = new DefaultTableModel(null, titulos);
    jTable1.setModel(modelo);
    jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(230);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(300);
    
      String sql = "select fecha_in_nohora, fecha_fin_nohora, Peticiones, sumdias, nombre_categoria, nombre_subcategoria from vwsubcategoria where fecha_in_nohora>='"+fecha1+"'and vwsubcategoria.fecha_in_nohora<='"+fecha2+"'";
     Statement st = con.createStatement();
       ResultSet rs = st.executeQuery(sql);
       String [] datos = new String [6];
       
       while(rs.next()){
       datos[0] = rs.getString("fecha_in_nohora");    
       datos[1] = rs.getString("fecha_fin_nohora");
       datos[2] = rs.getString("Peticiones");
       datos[3] = rs.getString("sumdias");
       datos[4] = rs.getString("nombre_categoria");
       datos[5] = rs.getString("nombre_subcategoria");

       
       modelo.addRow(datos);
       
       }
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERROR"+"MSG"+JOptionPane.WARNING_MESSAGE+e);
        }  
    }        // TODO add your handling code here:
    }//GEN-LAST:event_BUSCAR_FECHAActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
 JFileChooser dialog = new JFileChooser();
        int opcion = dialog.showSaveDialog(Historial_reportes.this);

        if (opcion == JFileChooser.APPROVE_OPTION) {

            File dir = dialog.getSelectedFile();

            try {
                List<JTable> tb = new ArrayList<JTable>();
                tb.add(jTable1);
                //-------------------
                Exportar_excel excelExporter = new Exportar_excel(tb, new File(dir.getAbsolutePath() + ".xls"));
                if (excelExporter.export()) {
                    JOptionPane.showMessageDialog(null, "Correcto...");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
this.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void BUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUSActionPerformed
        String buscarCombo = COMBO_USUARIO.getSelectedItem().toString();
        //---------------------CUANDO SE SELECCIONE LA OPCION PERSONA
        BUSCAR_FECHA.setVisible(true);
        FECHA_1.setVisible(true);
        FECHA_2.setVisible(true);
        String [] titulos  = {"Fecha inicial","Fech final","Total peticiones","Total días", "Categoria","SubCategoria", "Atendido por" };
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        jTable1.setModel(modelo);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(230);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(300);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(80);
       /* GregorianCalendar objFecha = new GregorianCalendar();
        int mes;
        mes=objFecha.get(Calendar.MONTH)+1;*/
        String sql = "select fecha_in_nohora, fecha_fin_nohora, total, sumdias, nombre_categoria, nombre_subcategoria, atendio from estadistico where atendio='"+buscarCombo+"'"; //and month(fecha_in_nohora)='"+mes+"'
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String [] datos = new String [7];
            while(rs.next()){
                datos[0] = rs.getString("fecha_in_nohora");
                datos[1] = rs.getString("fecha_fin_nohora");
                datos[2] = rs.getString("total");
                datos[3] = rs.getString("sumdias");
                datos[4] = rs.getString("nombre_categoria");
                datos[5] = rs.getString("nombre_subcategoria");
                datos[6] = rs.getString("atendio");
                modelo.addRow(datos);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERROR"+"MSG"+JOptionPane.WARNING_MESSAGE+e);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_BUSActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BUS;
    private javax.swing.JButton BUSCAR_FECHA;
    private javax.swing.JComboBox COMBO_BUSCA;
    private javax.swing.JComboBox COMBO_USUARIO;
    private com.toedter.calendar.JDateChooser FECHA_1;
    private com.toedter.calendar.JDateChooser FECHA_2;
    private proyectoresidencia.BackgroundMenuBar backgroundMenuBar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
ProyectoResidencia cc = new ProyectoResidencia();
Connection con=cc.conexion();
}