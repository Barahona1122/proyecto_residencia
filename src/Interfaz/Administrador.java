/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import static Interfaz.PantallaPrincipal.ESCRITORIO;
import Objetos_clases.Invitado;
import conexiones.ProyectoResidencia;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import proyectoresidencia.ComboItem;
import proyectoresidencia.Exportar_excel;
import proyectoresidencia.FondoAdmin;
import proyectoresidencia.MiRender;

/**
 *
 * @author Carlos Barahona
 */
public class Administrador extends javax.swing.JInternalFrame {

    /**
     * Creates new form Administrador
     */
    public Administrador() {
     
        initComponents();
        llenar(); //LLENA LA TABLA
        admin(); //LLENA LOS ADMINISTRADORES PARA ASIGNAR TAREAS
        //atendido(); //LLENAR LOS ADMINISTRADORES PARA SELECCIONAR QUIEN LO ATENDIO 
        
      FondoAdmin p = new FondoAdmin("paso.jpg"); 
this.add( p , BorderLayout.EAST); 
p.repaint();
//---------------------------------------------------ICONO PARA EL CALENDARI
URL url = getClass().getResource("/iconos/calendario.png");  
FECHA_INICIO.setIcon(new ImageIcon(url)); 
URL url2 = getClass().getResource("/iconos/calendario.png");  
FECHA_FINAL.setIcon(new ImageIcon(url2)); 

//----------------------------PONER TITULO A LA VENTANA
   Calendar calendar=Calendar.getInstance();
Date now=calendar.getTime();
System.out.println(dateMonth(now));
this.setTitle("Peticiones del día "+ dateMonth(now));
    }
public static String dateMonth(Date date){
 String result="";
 Calendar calendar=Calendar.getInstance();
 calendar.setTime(date);
 int month=0; 
 try{
   month=calendar.get(Calendar.MONTH);
 }catch(Exception ex){}
 switch(month){
  case 0:
    {
      result="Enero";
      break;
    }
  case 1:
    {
      result="Febrero";
      break;
    }
  case 2:
    {
      result="Marzo";
      break;
    }
  case 3:
    {
      result="Abril";
      break;
    }
  case 4:
    {
      result="Mayo";
      break;
    }
  case 5:
    {
      result="Junio";
      break;
    }
  case 6:
    {
      result="Julio";
      break;
    }
  case 7:
    {
      result="Agosto";
      break;
    }
  case 8:
    {
      result="Septiembre";
      break;
    }
  case 9:
    {
      result="Octubre";
      break;
    }
  case 10:
    {
      result="Noviembre";
      break;
    }
  case 11:
    {
      result="Diciembre";
      break;
    }
  default:
    {
      result="Error";
      break;
    }
 }
 return result;
}
    private void llenar(){
    String [] titulos = {"# Tarea","Fecha inicial","Hora","Lugar",
        "Area","Usuario","Problema","Nota","Asignar a", "Estado"};   
     GregorianCalendar objFecha = new GregorianCalendar();
      int mes;
  mes=objFecha.get(Calendar.MONTH)+1;
    String sql = "select id_peticion, fecha_in_nohora, fecha_in_hora,nombre_locacion,"
            + "nombre_area,nombre,nombre_problema,nota, asignado, nombre_estado from vista_principal where id_estado IN(1,2)";//month(fecha_in_nohora)='"+mes+"'
    DefaultTableModel modelo = new DefaultTableModel(null, titulos);
    jTable1.setModel(modelo);
    modelo.fireTableDataChanged();
    try{
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);
    String [] datos = new String [13];
    while(rs.next()){
    datos[0] = rs.getString("id_peticion");
    datos[1] = rs.getString("fecha_in_nohora");
  //  datos[2] = rs.getString("fecha_fin_nohora"); //cambiar a FECHA FINAL 
    datos[2] = rs.getString("fecha_in_hora"); //HORA DE LA PETICION
    datos[3] = rs.getString("nombre_locacion");
    datos[4] = rs.getString("nombre_area");
    datos[5] = rs.getString("nombre");
    datos[6] = rs.getString("nombre_problema");
    datos[7] = rs.getString("nota");
    datos[8] = rs.getString("asignado");
    //datos[9] = rs.getString("nombre_atiende");
    datos[9] = rs.getString("nombre_estado");
    modelo.addRow(datos);
      jTable1.setDefaultRenderer(Object.class, new MiRender()); //COLOREA UNA CELDA EN ESPECIFICO    
          int[] anchos = {40, 80,50,70,100,50,300,210,70,70}; //ASIGNAR TAMAÑO A CADA UNA DE LAS CELDAS
for(int i = 0; i < jTable1.getColumnCount(); i++) {
jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
}
    }
    }catch(Exception ex){
    JOptionPane.showMessageDialog(null,"ERROR"+ex);
    }
    }   
    private void admin(){
    llenar();
    try{
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("select nombre, id_usuario from usuario where id_perfil=3"); 

    while(rs.next()){
        
        
        Invitado invi = new Invitado();
        invi.setId_usuario(rs.getInt("id_usuario"));
        invi.setNombre(rs.getString("nombre"));
        this.ADMINISTRADORES.addItem(invi);
          }
    }catch(Exception e){
    JOptionPane.showMessageDialog(null,"ERROR"+e);
    }  
    }
    
    /*public void atendido(){
          llenar();
    try{
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("select nombre, id_usuario from usuario where id_perfil=1");    
    while(rs.next()){
        this.ATENDIDO.addItem(new ComboItem(rs.getString("nombre"),rs.getString("id_usuario")));  
    //this.ADMINISTRADORES.addItem(rs.getString("nombre")); //NOMBRE DEL ADMINISTRADOR A MOSTRAR
    }
    }catch(Exception e){
    JOptionPane.showMessageDialog(null,"ERROR"+e);
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

        ID = new javax.swing.JTextField();
        CAMBIO_ESTADO = new javax.swing.JTextField();
        ESTADO = new javax.swing.JTextField();
        ADMIN_CONECTADO = new javax.swing.JTextField();
        ATENDIDO = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        FECHA_FINAL = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        ADMINISTRADORES = new javax.swing.JComboBox<Invitado>();
        jButton3 = new javax.swing.JButton();
        FECHA_INICIO = new com.toedter.calendar.JDateChooser();
        backgroundMenuBar1 = new proyectoresidencia.BackgroundMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();

        CAMBIO_ESTADO.setText("1");

        ESTADO.setText("2");

        ATENDIDO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Atendido..." }));

        setBackground(new java.awt.Color(255, 255, 255));
        setIconifiable(true);
        setResizable(true);
        setOpaque(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "null"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setGridColor(new java.awt.Color(0, 51, 51));
        jTable1.setSurrendersFocusOnKeystroke(true);
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

        FECHA_FINAL.setDateFormatString("yyyy-MM-dd");

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

        jButton2.setBackground(new java.awt.Color(0, 102, 153));
        jButton2.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Actualizar tabla");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        ADMINISTRADORES.setBackground(new java.awt.Color(51, 51, 255));
        ADMINISTRADORES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ADMINISTRADORES.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Asignar a..." }));
        ADMINISTRADORES.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ADMINISTRADORES.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ADMINISTRADORESMousePressed(evt);
            }
        });
        ADMINISTRADORES.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ADMINISTRADORESKeyPressed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 153));
        jButton3.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Asignar");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        FECHA_INICIO.setDateFormatString("yyyy-MM-dd");

        backgroundMenuBar1.setBackground(new java.awt.Color(0, 102, 153));

        jMenu7.setText("Archivo");
        jMenu7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu7.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem9.setText("Guardar");
        jMenuItem9.setContentAreaFilled(false);
        jMenuItem9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem9);

        backgroundMenuBar1.add(jMenu7);

        jMenu8.setText("Reportes del mes");
        jMenu8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu8.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Mostrar");
        jMenuItem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem1);

        backgroundMenuBar1.add(jMenu8);

        jMenu1.setText("Problemas");
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Mostrar");
        jMenuItem2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        backgroundMenuBar1.add(jMenu1);

        jMenu2.setText("Métricas");
        jMenu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu2.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Categorías");
        jMenuItem3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);
        jMenu2.add(jSeparator1);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Subcategorías");
        jMenuItem4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        backgroundMenuBar1.add(jMenu2);

        jMenu3.setText("Usuarios");
        jMenu3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu3.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Mostrar");
        jMenuItem5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        backgroundMenuBar1.add(jMenu3);

        jMenu4.setText("Lugares y Áreas");
        jMenu4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu4.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_7, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Lugares");
        jMenuItem6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);
        jMenu4.add(jSeparator2);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_8, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Áreas");
        jMenuItem7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        backgroundMenuBar1.add(jMenu4);

        jMenu5.setText("Historial");
        jMenu5.setContentAreaFilled(false);
        jMenu5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu5.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setText("Peticiones");
        jMenuItem8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem8);
        jMenu5.add(jSeparator3);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setText("Reportes");
        jMenuItem11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem11);

        backgroundMenuBar1.add(jMenu5);

        setJMenuBar(backgroundMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(FECHA_INICIO, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FECHA_FINAL, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 536, Short.MAX_VALUE)
                        .addComponent(ADMINISTRADORES, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(28, 28, 28)
                        .addComponent(jButton2)
                        .addContainerGap())
                    .addComponent(jScrollPane1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(FECHA_FINAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(ADMINISTRADORES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3))
                    .addComponent(FECHA_INICIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    llenar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ADMINISTRADORESMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ADMINISTRADORESMousePressed
    
// TODO add your handling code here:
    }//GEN-LAST:event_ADMINISTRADORESMousePressed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
 int fila = jTable1.getSelectedRow();
 if(fila>=0){
            ID.setText(jTable1.getValueAt(fila, 0).toString());
 }
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MousePressed

    private void ADMINISTRADORESKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ADMINISTRADORESKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ADMINISTRADORESKeyPressed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
 int fila = jTable1.getSelectedRow();
 if(fila>=0){
            ID.setText(jTable1.getValueAt(fila, 0).toString());
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 try {
     Date dato1 = FECHA_INICIO.getDate();
     Date dato2 = FECHA_FINAL.getDate();
     if(dato1==null || dato2==null){
         JOptionPane.showMessageDialog(null,"Seleccionar una fecha valida","Aviso",JOptionPane.ERROR_MESSAGE);
     
     }else{
        String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FECHA_INICIO.getDate());
        String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FECHA_FINAL.getDate());
        
        String[] titulos ={"# Tarea","Fecha inicial","Fecha final","Hora","Lugar","Area",
                            "Usuario","Problema","Asignar a","Realizado por", "Estado" };
        DefaultTableModel modelo = new DefaultTableModel(null,  titulos);
         jTable1.setModel(modelo);  
        
         String sql = "select id_peticion, fecha_in_nohora, fecha_fin_nohora,fecha_in_hora,nombre_locacion,nombre_area,nombre,nombre_problema,asignado, nombre_atiende, nombre_estado from vista_principal where fecha_in_nohora>='"+fecha1 +"'and vista_principal.fecha_in_nohora<='"+fecha2+"'and id_estado IN(1,2)";
        
         Statement  sts = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = sts.executeQuery(sql);
         String [] datos =   new String[11];
        while(rs.next()){
                   
    datos[0] = rs.getString("id_peticion");
    datos[1] = rs.getString("fecha_in_nohora");
    datos[2] = rs.getString("fecha_fin_nohora"); //cambiar a FECHA FINAL 
    datos[3] = rs.getString("fecha_in_hora"); //HORA DE LA PETICION
    datos[4] = rs.getString("nombre_locacion");
    datos[5] = rs.getString("nombre_area");
    datos[6] = rs.getString("nombre");
    datos[7] = rs.getString("nombre_problema");
    datos[8] = rs.getString("asignado");
    datos[9] = rs.getString("nombre_atiende");
    datos[10] = rs.getString("nombre_estado");
    modelo.addRow(datos);
               int[] anchos = {5, 80, 50, 50,70,100,50,300,70,70,70}; //ASIGNAR TAMAÑO A CADA UNA DE LAS CELDAS
for(int i = 0; i < jTable1.getColumnCount(); i++) {
jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
}  
        }
// TODO add your handling code here:
 }   
 } catch (SQLException ex) {
        Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        
        
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
//-----------------------------------------------------------ASIGNA UN USUARIO-ADMINISTRADOR ESA PETICION
      /*  String unoCombo =  (String) ADMINISTRADORES.getSelectedItem(); //CONVIERTE EL JCOMBOBOX A STRING 
       String decomponerCOMBO[] = unoCombo.split("-");
       Integer resultadoCombo = Integer.parseInt(decomponerCOMBO[0]);   */ 
       
       

    //String verificar  = ADMINISTRADORES.getSelectedItem().toString();
    if(ADMINISTRADORES.getSelectedItem().toString().equals("Asignar a...")){
        JOptionPane.showMessageDialog(null,"Seleccionar un usuario valido","Aviso",JOptionPane.ERROR_MESSAGE);
     
    }else{
        try{
  /*   Object item = ADMINISTRADORES.getSelectedItem();
     String value = ((ComboItem)item).getValue();*/
     
    PreparedStatement pst = con.prepareStatement("UPDATE peticion SET id_asignado='"+ADMINISTRADORES.getItemAt(ADMINISTRADORES.getSelectedIndex()).getId_usuario()+"'WHERE id_peticion='"+ID.getText()+"'");        
    pst.executeUpdate();
            llenar();//MUESTRA LOS CAMBIOS ACTUALIZANDO LA TABLA
        //    JOptionPane.showMessageDialog(null,"Asigando a: "+JOptionPane.INFORMATION_MESSAGE+ ADMINISTRADORES.getSelectedItem()); // SE MUESTRA CUANDO SE ASIGNA LA TAREA

    }catch(Exception e){
JOptionPane.showMessageDialog(null,"Error"+e);
}
     //-----------------------------------------------------------ACTUALIZA EL ESTADO DE LA PETICION
 
 try{
  /*   Object item = ADMINISTRADORES.getSelectedItem();
     String value = ((ComboItem)item).getValue();*/
     
    PreparedStatement pst2 = con.prepareStatement("UPDATE peticion SET id_estado='"+ESTADO.getText()+"'WHERE id_peticion='"+ID.getText()+"'");        
    pst2.executeUpdate();
            llenar();//MUESTRA LOS CAMBIOS ACTUALIZANDO LA TABLA
          //  JOptionPane.showMessageDialog(null,"Asignado a: "+ ADMINISTRADORES.getSelectedItem()); // SE MUESTRA CUANDO SE ASIGNA LA TAREA
}catch(Exception e){
JOptionPane.showMessageDialog(null,"Error"+e);
}
    
    }

 

 
 
 
 

// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
  JFileChooser dialog = new JFileChooser();
        int opcion = dialog.showSaveDialog(Administrador.this);

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
        }
        
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
  Estadistica_Subcategoria cat = new Estadistica_Subcategoria();
        ESCRITORIO.add(cat);
        cat.show();
        cat.toFront();
        Dimension desktopSize = ESCRITORIO.getSize();
        Dimension FrameSize = cat.getSize();
        cat.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
 Problemas_comunes pro = new Problemas_comunes();
        ESCRITORIO.add(pro);
        pro.show();
        pro.toFront();
        Dimension desktopSize = ESCRITORIO.getSize();
        Dimension FrameSize = pro.getSize();
        pro.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
   Menu_categorias cat = new Menu_categorias();
        ESCRITORIO.add(cat);
        cat.show();
        cat.toFront();
        Dimension desktopSize = ESCRITORIO.getSize();
        Dimension FrameSize = cat.getSize();
        cat.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

     Menu_subcategorias sub = new Menu_subcategorias();
        ESCRITORIO.add(sub);
        sub.show();
        sub.toFront();
        Dimension desktopSize = ESCRITORIO.getSize();
        Dimension FrameSize = sub.getSize();
        sub.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
 

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
Locacion loc = new Locacion();
        ESCRITORIO.add(loc);
        loc.show();
        loc.toFront();
        Dimension desktopSize = ESCRITORIO.getSize();
        Dimension FrameSize = loc.getSize();
        loc.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
  Menu_usuarios us = new Menu_usuarios();
        ESCRITORIO.add(us);
        us.show();
        us.toFront();
        Dimension desktopSize = ESCRITORIO.getSize();
        Dimension FrameSize = us.getSize();
        us.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
  Menu_areas ar = new Menu_areas();
        ESCRITORIO.add(ar);
        ar.show();
        ar.toFront();
        Dimension desktopSize = ESCRITORIO.getSize();
        Dimension FrameSize = ar.getSize();
        ar.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
  
        
// TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
   try{
   
   new Thread(new Administrador.HILO()).start();  
   }catch(Exception e){
   JOptionPane.showMessageDialog(null, "ERROR   :"+e);
   }
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameOpened

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
       Historia_Peticiones loc = new Historia_Peticiones();
        ESCRITORIO.add(loc);
        loc.show();
        loc.toFront();
        Dimension desktopSize = ESCRITORIO.getSize();
        Dimension FrameSize = loc.getSize();
        loc.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);

        
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
    Historial_reportes loc = new Historial_reportes();
        ESCRITORIO.add(loc);
        loc.show();
        loc.toFront();
        Dimension desktopSize = ESCRITORIO.getSize();
        Dimension FrameSize = loc.getSize();
        loc.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);

                // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem11ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Invitado> ADMINISTRADORES;
    public static javax.swing.JTextField ADMIN_CONECTADO;
    private javax.swing.JComboBox ATENDIDO;
    private javax.swing.JTextField CAMBIO_ESTADO;
    private javax.swing.JTextField ESTADO;
    private com.toedter.calendar.JDateChooser FECHA_FINAL;
    private com.toedter.calendar.JDateChooser FECHA_INICIO;
    private javax.swing.JTextField ID;
    private proyectoresidencia.BackgroundMenuBar backgroundMenuBar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
ProyectoResidencia cc = new ProyectoResidencia();
Connection con=cc.conexion();

private class HILO implements Runnable {
 @Override
public void run(){
llenar();
}
public void llenar(){
    while(true){
String [] titulos = {"# Tarea","Fecha inicial","Hora","Lugar",
        "Area","Usuario","Problema","Nota","Asignar a", "Estado"};   
     GregorianCalendar objFecha = new GregorianCalendar();
      int mes;
  mes=objFecha.get(Calendar.MONTH)+1;
    String sql = "select id_peticion, fecha_in_nohora, fecha_in_hora,nombre_locacion,"
            + "nombre_area,nombre,nombre_problema,nota, asignado, nombre_estado from vista_principal where id_estado IN(1,2)";//month(fecha_in_nohora)='"+mes+"'
    DefaultTableModel modelo = new DefaultTableModel(null, titulos);
    jTable1.setModel(modelo);
    modelo.fireTableDataChanged();
    try{
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);
    String [] datos = new String [13];
    while(rs.next()){
    datos[0] = rs.getString("id_peticion");
    datos[1] = rs.getString("fecha_in_nohora");
  //  datos[2] = rs.getString("fecha_fin_nohora"); //cambiar a FECHA FINAL 
    datos[2] = rs.getString("fecha_in_hora"); //HORA DE LA PETICION
    datos[3] = rs.getString("nombre_locacion");
    datos[4] = rs.getString("nombre_area");
    datos[5] = rs.getString("nombre");
    datos[6] = rs.getString("nombre_problema");
    datos[7] = rs.getString("nota");
    datos[8] = rs.getString("asignado");
    //datos[9] = rs.getString("nombre_atiende");
    datos[9] = rs.getString("nombre_estado");
    modelo.addRow(datos);
      jTable1.setDefaultRenderer(Object.class, new MiRender()); //COLOREA UNA CELDA EN ESPECIFICO    
          int[] anchos = {40, 80,50,70,100,50,300,210,70,70}; //ASIGNAR TAMAÑO A CADA UNA DE LAS CELDAS
for(int i = 0; i < jTable1.getColumnCount(); i++) {
jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
}
    }
    }catch(Exception ex){
    JOptionPane.showMessageDialog(null,"ERROR"+ex);
    }

        
        
        
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
