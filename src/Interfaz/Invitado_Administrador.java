/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Objetos_clases.Perfil_invitado;
import conexiones.ProyectoResidencia;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectoresidencia.FondoAdmin;
import proyectoresidencia.MiRender;

/**
 *
 * @author Carlos Barahona
 */
public class Invitado_Administrador extends javax.swing.JFrame {

    /**
     * Creates new form Invitado_Administrador
     */
    public Invitado_Administrador() {
      
        initComponents();
       // llenar();
        setLocationRelativeTo(null);
   //   admin();
      llenar();
      
//ICONO DEL SOFTWAWE
         Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconos/Icono.png"));
       setIconImage(icon);
setVisible(true);

  //------------------------------------------------IMAGEN DE FONDO
        FondoAdmin p = new FondoAdmin("paso.jpg"); 
this.add( p , BorderLayout.EAST); 
p.repaint();
  

//--------------ICONO PARA EL CALENDARIO
URL url = getClass().getResource("/iconos/calendario.png");  
FECHA_IN.setIcon(new ImageIcon(url)); 
URL url2 = getClass().getResource("/iconos/calendario.png");  
FECHA_FIN.setIcon(new ImageIcon(url2)); 

    }
    public void cerrar(){
Object [] opciones ={"Aceptar","Cancelar"};
int eleccion = JOptionPane.showOptionDialog(rootPane,"¿Estas seguro?","Mensaje de Confirmacion",
JOptionPane.YES_NO_OPTION,
JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
if (eleccion == JOptionPane.YES_OPTION)
{
System.exit(0);
}else{
    
}
  }
    
    
    private void llenar(){
  String [] titulos = {"# Tarea","Fecha inicial","Hora","Lugar",
        "Area","Usuario","Problema","Nota","Asignado a", "Estado"};
   
    String conec = CONECTADO.getText();
      GregorianCalendar objFecha = new GregorianCalendar();
      int mes;
  mes=objFecha.get(Calendar.MONTH)+1;
    String sql = "select id_peticion, fecha_in_nohora,fecha_in_hora,nombre_locacion,nombre_area,nombre,nombre_problema,nota, asignado,nombre_atiende, nombre_estado from vista_principal where id_asignado='"+conec+"'AND  id_estado=2"; 
            DefaultTableModel modelo = new DefaultTableModel(null, titulos);
    jTable1.setModel(modelo);
    try{
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);
    String [] datos = new String [12];
    while(rs.next()){
    datos[0] = rs.getString("id_peticion");
    datos[1] = rs.getString("fecha_in_nohora");
    datos[2] = rs.getString("fecha_in_hora"); //cambiar a FECHA FINAL 
    datos[3] = rs.getString("nombre_locacion");
    datos[4] = rs.getString("nombre_area");
    datos[5] = rs.getString("nombre");
    datos[6] = rs.getString("nombre_problema");
    datos[7] = rs.getString("nota");
    datos[8] = rs.getString("asignado");
    datos[9] = rs.getString("nombre_estado");
    modelo.addRow(datos);
      jTable1.setDefaultRenderer(Object.class, new MiRender()); //COLOREA UNA CELDA EN ESPECIFICO
      
          int[] anchos = {40, 80,50, 70,100,50,300,210,70,70}; //ASIGNAR TAMAÑO A CADA UNA DE LAS CELDAS
for(int i = 0; i < jTable1.getColumnCount(); i++) {
jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
}
    }
    }catch(Exception ex){
    JOptionPane.showMessageDialog(null,"ERROR"+ex);
    }         // TODO add your handling code here:
    
    }
    /*private void admin(){
          //llenar();
        
    try{
         String conect = CONECTADO.getText();
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("select nombre, id_usuario from usuario where id_perfil=3 and id_usuario='"+conect+"'"); 
    String [] datos = new String[2];
    while(rs.next()){
        datos[0]=rs.getString("id_usuario");
        datos[1]=rs.getString("nombre");
        this.ADMINISTRADORES.addItem(datos[0]+"-"+datos[1]);
          }
    }catch(Exception e){
    JOptionPane.showMessageDialog(null,"ERROR"+e);
    }  
    }*/
    
/*private void llenar(){
    String [] titulos = {"# Tarea","Fecha inicial","Fecha final","Lugar",
        "Area","Usuario","Problema","Asignado","Realizado", "Estado"};
   
    String conec = CONECTADO.getText();
    String sql = "select id_peticion, fecha_in_nohora,fecha_fin_nohora,nombre_locacion,"
            + "nombre_area,nombre,nombre_problema, asignado,nombre_atiende, nombre_estado from vista_principal where id_asignado='"+conec+"'";
    DefaultTableModel modelo = new DefaultTableModel(null, titulos);
    jTable1.setModel(modelo);
    try{
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);
    String [] datos = new String [10];
    while(rs.next()){
    datos[0] = rs.getString("id_peticion");
    datos[1] = rs.getString("fecha_in_nohora");
    datos[2] = rs.getString("fecha_fin_nohora"); //cambiar a FECHA FINAL 
    datos[3] = rs.getString("nombre_locacion");
    datos[4] = rs.getString("nombre_area");
    datos[5] = rs.getString("nombre");
    datos[6] = rs.getString("nombre_problema");
    datos[7] = rs.getString("asignado");
    datos[8] = rs.getString("nombre_atiende");
    datos[9] = rs.getString("nombre_estado");
    modelo.addRow(datos);
      jTable1.setDefaultRenderer(Object.class, new MiRender()); //COLOREA UNA CELDA EN ESPECIFICO
      
          int[] anchos = {5, 80, 50, 70,100,50,300,70,70,70}; //ASIGNAR TAMAÑO A CADA UNA DE LAS CELDAS
for(int i = 0; i < jTable1.getColumnCount(); i++) {
jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
}
    }
    }catch(Exception ex){
    JOptionPane.showMessageDialog(null,"ERROR"+ex);
    }
    
    }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ESTADO = new javax.swing.JTextField();
        CONECTADO = new javax.swing.JTextField();
        ID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        ADMINISTRADORES = new javax.swing.JComboBox<Perfil_invitado>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        FECHA_IN = new com.toedter.calendar.JDateChooser();
        FECHA_FIN = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();

        ESTADO.setText("0");

        CONECTADO.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                CONECTADOAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Administrador");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
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

        ADMINISTRADORES.setBackground(new java.awt.Color(51, 51, 255));
        ADMINISTRADORES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ADMINISTRADORES.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Atendido por..." }));

        jButton1.setBackground(new java.awt.Color(0, 102, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 153));
        jButton2.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Actualizar tabla");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        FECHA_IN.setDateFormatString("yyyy-MM-dd");

        FECHA_FIN.setDateFormatString("yyyy-MM-dd");

        jButton3.setBackground(new java.awt.Color(0, 102, 153));
        jButton3.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(FECHA_IN, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(FECHA_FIN, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton3)
                        .addGap(401, 401, 401)
                        .addComponent(ADMINISTRADORES, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton1)
                        .addGap(45, 45, 45)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1240, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(FECHA_IN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(FECHA_FIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(ADMINISTRADORES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton2)))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        llenar();
        
           try{
         String conect = CONECTADO.getText();
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("select nombre, id_usuario from usuario where id_perfil=3 and id_usuario='"+conect+"'"); 
    
    while(rs.next()){
        
        Perfil_invitado invi= new Perfil_invitado();
        invi.setId_usuario(rs.getInt("id_usuario"));
        invi.setNombre(rs.getString("nombre"));
        
        this.ADMINISTRADORES.addItem(invi);
          }
    }catch(Exception e){
    JOptionPane.showMessageDialog(null,"ERROR"+e);
    }
        /*String [] titulos = {"# Tarea","Fecha inicial","Fecha final","Hora","Lugar",
        "Area","Usuario","Problema","Asignado","Realizado", "Estado"};
   
    String conec = CONECTADO.getText();
      GregorianCalendar objFecha = new GregorianCalendar();
      int mes;
  mes=objFecha.get(Calendar.MONTH)+1;
 String sql = "select id_peticion, fecha_in_nohora,fecha_fin_nohora,fecha_in_hora,nombre_locacion,nombre_area,nombre,nombre_problema, asignado,nombre_atiende, nombre_estado from vista_principal where id_asignado='"+conec+"'AND  id_estado=2"; 
         
  DefaultTableModel modelo = new DefaultTableModel(null, titulos);
    jTable1.setModel(modelo);
    try{
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);
    String [] datos = new String [11];
    while(rs.next()){
    datos[0] = rs.getString("id_peticion");
    datos[1] = rs.getString("fecha_in_nohora");
    datos[2] = rs.getString("fecha_fin_nohora"); //cambiar a FECHA FINAL 
    datos[3] = rs.getString("fecha_in_hora"); //cambiar a FECHA FINAL 
    datos[4] = rs.getString("nombre_locacion");
    datos[5] = rs.getString("nombre_area");
    datos[6] = rs.getString("nombre");
    datos[7] = rs.getString("nombre_problema");
    datos[8] = rs.getString("asignado");
    datos[9] = rs.getString("nombre_atiende");
    datos[10] = rs.getString("nombre_estado");
    modelo.addRow(datos);
      jTable1.setDefaultRenderer(Object.class, new MiRender()); //COLOREA UNA CELDA EN ESPECIFICO
      
          int[] anchos = {40, 80, 50,50, 70,100,50,300,70,70,70}; //ASIGNAR TAMAÑO A CADA UNA DE LAS CELDAS
for(int i = 0; i < jTable1.getColumnCount(); i++) {
jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
}
    }
    }catch(Exception ex){
    JOptionPane.showMessageDialog(null,"ERROR"+ex);
    }     */   // TODO add your handling code here:
           
              try{
   
   new Thread(new Invitado_Administrador.HILO()).start();  
   }catch(Exception e){
   JOptionPane.showMessageDialog(null, "ERROR   :"+e);
   }
    }//GEN-LAST:event_formWindowOpened

    private void CONECTADOAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_CONECTADOAncestorAdded
/*
        
        String [] titulos = {"# Tarea","Fecha inicial","Fecha final","Hora","Lugar",
        "Area","Usuario","Problema","Asignado","Realizado", "Estado"};
   
    String conec = CONECTADO.getText();
      GregorianCalendar objFecha = new GregorianCalendar();
      int mes;
  mes=objFecha.get(Calendar.MONTH)+1;
 String sql = "select id_peticion, fecha_in_nohora,fecha_fin_nohora,fecha_in_hora,nombre_locacion,nombre_area,nombre,nombre_problema, asignado,nombre_atiende, nombre_estado from vista_principal where id_asignado='"+conec+"'AND  id_estado=2"; 
         
  DefaultTableModel modelo = new DefaultTableModel(null, titulos);
    jTable1.setModel(modelo);
    try{
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);
    String [] datos = new String [11];
    while(rs.next()){
    datos[0] = rs.getString("id_peticion");
    datos[1] = rs.getString("fecha_in_nohora");
    datos[2] = rs.getString("fecha_fin_nohora"); //cambiar a FECHA FINAL 
    datos[3] = rs.getString("fecha_in_hora"); //cambiar a FECHA FINAL 
    datos[4] = rs.getString("nombre_locacion");
    datos[5] = rs.getString("nombre_area");
    datos[6] = rs.getString("nombre");
    datos[7] = rs.getString("nombre_problema");
    datos[8] = rs.getString("asignado");
    datos[9] = rs.getString("nombre_atiende");
    datos[10] = rs.getString("nombre_estado");
    modelo.addRow(datos);
      jTable1.setDefaultRenderer(Object.class, new MiRender()); //COLOREA UNA CELDA EN ESPECIFICO
      
          int[] anchos = {40, 80, 50,50, 70,100,50,300,70,70,70}; //ASIGNAR TAMAÑO A CADA UNA DE LAS CELDAS
for(int i = 0; i < jTable1.getColumnCount(); i++) {
jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
}
    }
    }catch(Exception ex){
    JOptionPane.showMessageDialog(null,"ERROR"+ex);
    } 
        
        */
        
// TODO add your handling code here:
    }//GEN-LAST:event_CONECTADOAncestorAdded

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
llenar();
        /* String [] titulos = {"# Tarea","Fecha inicial","Fecha final","Hora","Lugar",
        "Area","Usuario","Problema","Asignado","Realizado", "Estado"};
   
    String conec = CONECTADO.getText();
      GregorianCalendar objFecha = new GregorianCalendar();
      int mes;
  mes=objFecha.get(Calendar.MONTH)+1;
   String sql = "select id_peticion, fecha_in_nohora,fecha_fin_nohora,fecha_in_hora,nombre_locacion,nombre_area,nombre,nombre_problema, asignado,nombre_atiende, nombre_estado from vista_principal where id_asignado='"+conec+"'AND  id_estado=2"; 
         
    DefaultTableModel modelo = new DefaultTableModel(null, titulos);
    jTable1.setModel(modelo);
    try{
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);
    String [] datos = new String [11];
    while(rs.next()){
    datos[0] = rs.getString("id_peticion");
    datos[1] = rs.getString("fecha_in_nohora");
    datos[2] = rs.getString("fecha_fin_nohora"); //cambiar a FECHA FINAL 
    datos[3] = rs.getString("fecha_in_hora"); //cambiar a FECHA FINAL 
    datos[4] = rs.getString("nombre_locacion");
    datos[5] = rs.getString("nombre_area");
    datos[6] = rs.getString("nombre");
    datos[7] = rs.getString("nombre_problema");
    datos[8] = rs.getString("asignado");
    datos[9] = rs.getString("nombre_atiende");
    datos[10] = rs.getString("nombre_estado");
    modelo.addRow(datos);
      jTable1.setDefaultRenderer(Object.class, new MiRender()); //COLOREA UNA CELDA EN ESPECIFICO
      
          int[] anchos = {40, 80, 50,50, 70,100,50,300,70,70,70}; //ASIGNAR TAMAÑO A CADA UNA DE LAS CELDAS
for(int i = 0; i < jTable1.getColumnCount(); i++) {
jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
}
    }
    }catch(Exception ex){
    JOptionPane.showMessageDialog(null,"ERROR"+ex);
    }     */       // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 
  /*   Object item = ADMINISTRADORES.getSelectedItem();
     String value = ((ComboItem)item).getValue();*/
        
        if(ADMINISTRADORES.getSelectedItem().toString().equals("Atendido por...")){
        
        JOptionPane.showMessageDialog(null,"Selecciona un usuario","Aviso", JOptionPane.WARNING_MESSAGE);
        }else{
     //-----------------FECHA RECURRENTE DEL SERVIDOR
java.util.Date date = Calendar.getInstance().getTime();
java.sql.Date sqlDate = new java.sql.Date(date.getTime());

java.util.Date date2 = Calendar.getInstance().getTime();
java.sql.Time stime = new java.sql.Time(date2.getTime());

String ho = sqlDate + " " + stime;
// create the java mysql update preparedstatement
 
 try{
      //..............cehcar
      

    PreparedStatement pst = con.prepareStatement("UPDATE peticion SET fecha_fin='"+ho+"'WHERE id_peticion='"+ID.getText()+"'");        
     
    pst.executeUpdate();
            llenar();//MUESTRA LOS CAMBIOS ACTUALIZANDO LA TABLA
          //  JOptionPane.showMessageDialog(null,"Asignado a: "+ ADMINISTRADORES.getSelectedItem()); // SE MUESTRA CUANDO SE ASIGNA LA TAREA
}catch(Exception e){
JOptionPane.showMessageDialog(null,"Error"+e);
}
 
//-----------------------------------------------------------ASIGNA UN USUARIO-ADMINISTRADOR ESA PETICION
        
        
   /*     String unoCombo =  (String) ADMINISTRADORES.getSelectedItem(); //CONVIERTE EL JCOMBOBOX A STRING 
       String decomponerCOMBO[] = unoCombo.split("-");
       Integer resultadoCombo = Integer.parseInt(decomponerCOMBO[0]); */   
try{
  /*   Object item = ADMINISTRADORES.getSelectedItem();
     String value = ((ComboItem)item).getValue();*/
     
    PreparedStatement pst = con.prepareStatement("UPDATE peticion SET id_atiende='"+ADMINISTRADORES.getItemAt(ADMINISTRADORES.getSelectedIndex()).getId_usuario()+"'WHERE id_peticion='"+ID.getText()+"'");        
    pst.executeUpdate();
            llenar();//MUESTRA LOS CAMBIOS ACTUALIZANDO LA TABLA
          //  JOptionPane.showMessageDialog(null,"Asigando a: "+JOptionPane.INFORMATION_MESSAGE+ ADMINISTRADORES.getSelectedItem()); // SE MUESTRA CUANDO SE ASIGNA LA TAREA
}catch(Exception e){
JOptionPane.showMessageDialog(null,"Error"+e);
}

 //-----------------------------------------------------------ACTUALIZA EL ESTADO DE LA PETICION
 
 try{
  /*   Object item = ADMINISTRADORES.getSelectedItem();
     String value = ((ComboItem)item).getValue();*/
     
    PreparedStatement pst = con.prepareStatement("UPDATE peticion SET id_estado='"+ESTADO.getText()+"'WHERE id_peticion='"+ID.getText()+"'");        
    pst.executeUpdate();
            llenar();//MUESTRA LOS CAMBIOS ACTUALIZANDO LA TABLA
          //  JOptionPane.showMessageDialog(null,"Asignado a: "+ ADMINISTRADORES.getSelectedItem()); // SE MUESTRA CUANDO SE ASIGNA LA TAREA
}catch(Exception e){
JOptionPane.showMessageDialog(null,"Error"+e);
}

 
 
     }   

// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
 int fila = jTable1.getSelectedRow();
 if(fila>=0){
            ID.setText(jTable1.getValueAt(fila, 0).toString());
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
 int fila = jTable1.getSelectedRow();
 if(fila>=0){
            ID.setText(jTable1.getValueAt(fila, 0).toString());
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

try{
     Date dato1 = FECHA_IN.getDate();
     Date dato2 = FECHA_FIN.getDate();
     if(dato1==null || dato2==null){
         JOptionPane.showMessageDialog(null,"Seleccionar una fecha valida","Aviso",JOptionPane.ERROR_MESSAGE);
     }else{
        String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FECHA_IN.getDate());
        String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FECHA_FIN.getDate());
        
String [] titulos = {"# Tarea","Fecha inicial","Hora","Lugar",
        "Area","Usuario","Problema","Nota","Asignado","Realizado", "Estado"};
   
    String conec = CONECTADO.getText();
      GregorianCalendar objFecha = new GregorianCalendar();
      int mes;
  mes=objFecha.get(Calendar.MONTH)+1;
    String sql = "select id_peticion, fecha_in_nohora,fecha_in_hora,nombre_locacion,nombre_area,nombre,nombre_problema,nota, asignado,nombre_atiende, nombre_estado from vista_principal where id_asignado='"+conec+"'AND  id_estado=2"; 
            DefaultTableModel modelo = new DefaultTableModel(null, titulos);
    jTable1.setModel(modelo);
   
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);
    String [] datos = new String [12];
    while(rs.next()){
    datos[0] = rs.getString("id_peticion");
    datos[1] = rs.getString("fecha_in_nohora");
    datos[2] = rs.getString("fecha_in_hora"); //cambiar a FECHA FINAL 
    datos[3] = rs.getString("nombre_locacion");
    datos[4] = rs.getString("nombre_area");
    datos[5] = rs.getString("nombre");
    datos[6] = rs.getString("nombre_problema");
    datos[7] = rs.getString("nota");
    datos[8] = rs.getString("asignado");
    datos[9] = rs.getString("nombre_atiende");
    datos[10] = rs.getString("nombre_estado");
    modelo.addRow(datos);
      jTable1.setDefaultRenderer(Object.class, new MiRender()); //COLOREA UNA CELDA EN ESPECIFICO
      
          int[] anchos = {40, 80,50, 70,100,50,300,210,70,70,70}; //ASIGNAR TAMAÑO A CADA UNA DE LAS CELDAS
for(int i = 0; i < jTable1.getColumnCount(); i++) {
jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
}
    }
    }
}catch(Exception ex){
    JOptionPane.showMessageDialog(null,"ERROR"+ex);
    }         // TODO add your handling code here:          

// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
cerrar();    // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
    try{
   
   new Thread(new Invitado_Administrador.HILO()).start();  
   }catch(Exception e){
   JOptionPane.showMessageDialog(null, "ERROR   :"+e);
   }
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Invitado_Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Invitado_Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Invitado_Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Invitado_Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Invitado_Administrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Perfil_invitado> ADMINISTRADORES;
    public static javax.swing.JTextField CONECTADO;
    private javax.swing.JTextField ESTADO;
    private com.toedter.calendar.JDateChooser FECHA_FIN;
    private com.toedter.calendar.JDateChooser FECHA_IN;
    private javax.swing.JTextField ID;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
ProyectoResidencia cc = new ProyectoResidencia();
Connection con=cc.conexion();
private class HILO implements Runnable{

        @Override
        public void run() {
        
        llenar();
        }
        
        public void llenar(){
        while(true){
  
 String [] titulos = {"# Tarea","Fecha inicial","Hora","Lugar",
        "Area","Usuario","Problema","Nota","Asignado a", "Estado"};
   
    String conec = CONECTADO.getText();
      GregorianCalendar objFecha = new GregorianCalendar();
      int mes;
  mes=objFecha.get(Calendar.MONTH)+1;
    String sql = "select id_peticion, fecha_in_nohora,fecha_in_hora,nombre_locacion,nombre_area,nombre,nombre_problema,nota, asignado,nombre_atiende, nombre_estado from vista_principal where id_asignado='"+conec+"'AND  id_estado=2"; 
            DefaultTableModel modelo = new DefaultTableModel(null, titulos);
    jTable1.setModel(modelo);
    try{
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);
    String [] datos = new String [12];
    while(rs.next()){
    datos[0] = rs.getString("id_peticion");
    datos[1] = rs.getString("fecha_in_nohora");
    datos[2] = rs.getString("fecha_in_hora"); //cambiar a FECHA FINAL 
    datos[3] = rs.getString("nombre_locacion");
    datos[4] = rs.getString("nombre_area");
    datos[5] = rs.getString("nombre");
    datos[6] = rs.getString("nombre_problema");
    datos[7] = rs.getString("nota");
    datos[8] = rs.getString("asignado");
    datos[9] = rs.getString("nombre_estado");
    modelo.addRow(datos);
      jTable1.setDefaultRenderer(Object.class, new MiRender()); //COLOREA UNA CELDA EN ESPECIFICO
      
          int[] anchos = {40, 80,50, 70,100,50,300,210,70,70}; //ASIGNAR TAMAÑO A CADA UNA DE LAS CELDAS
for(int i = 0; i < jTable1.getColumnCount(); i++) {
jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
}
    }
    }catch(Exception ex){
    JOptionPane.showMessageDialog(null,"ERROR"+ex);
    }             
// TODO add your handling code here:
    
       
             try{
Thread.sleep(60000); //ACTUALIZAR LA TABLA CLADA CIERTO TIEMPO
}catch(Exception ex)
{
    JOptionPane.showMessageDialog(null,"ERROR"+ex);
}
        
        }
        }


}
}
