/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import com.sun.awt.AWTUtilities;
import conexiones.ProyectoResidencia;
import conexiones.coneBD;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Shape;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.management.Notification;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jcarrierpigeon.NotificationQueue;
import proyectoresidencia.Area;
import proyectoresidencia.PeticionUsuarioFondo;
import proyectoresidencia.Problema;
import proyectoresidencia.Usuario;


/**
 *
 * @author Carlos Barahona
 */
public class PeticionUsuario extends javax.swing.JFrame {
 
 private int x;
        private int y;
        private TrayIcon icono;
        private SystemTray sistema;
        private ImageIcon imagen;
    /**
     * Creates new form PeticionUsuario
     */
    coneBD cn=new coneBD();  
    
    ProyectoResidencia cc = new ProyectoResidencia();
Connection con=cc.conexion();
    
    public PeticionUsuario() {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
       AreaDisponible(); 
       UsuariosPorArea();
       ProblemasEmpresa();
       //FORMA REDONDA A LA VENTANA
 /* Shape forma = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 10, 10); 
  AWTUtilities.setWindowShape(this, forma);    
   PeticionUsuarioFondo p = new PeticionUsuarioFondo("fondo1.png"); 
this.add( p , BorderLayout.CENTER); 
p.repaint(); */
       
//ICONO DEL SOFTWAWE
      imagen = new ImageIcon(this.getClass().getResource("/iconos/Icono.png"));
      this.setIconImage(imagen.getImage());
      SegundoPlano();     
  
    }
    
        //Muestra una burbuja con la accion que se realiza
    public void MensajeTrayIcon(String texto, MessageType tipo)
    {
        icono.displayMessage("Help-Desk:", texto, tipo);        
    }
    
    //OCULTA LA APLICACION EN LA BARRA DE TAREAS
    private void SegundoPlano(){
    icono = new TrayIcon(imagen.getImage(),"Solicitudes de soporte", popupMenu1);
    icono.setImageAutoSize(true);
    sistema = SystemTray.getSystemTray();
    
    MouseListener mouseListener = new MouseListener() { 

        @Override
        public void mouseClicked(MouseEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
sistema.remove(icono);  
setVisible(true);

        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    icono.addMouseListener(mouseListener);
    }
    
    
   private void AreaDisponible(){
 try{
 Statement stm = con.createStatement();
 ResultSet rs = stm.executeQuery("select * from area_empresa where id_locacion='"+ID_AREA.getText()+"'and area_empresa.activo=1");
 String datos[] = new String[3];
 while(rs.next()){
 // this.AREA.addItem(rs.getString("nombre_area"));
       
     Area ar = new Area(); 
     ar.setId_area(rs.getInt("id_area"));
     ar.setNombre_area(rs.getString("nombre_area"));
     
     this.AREA.addItem(ar);    
 }    
 }catch(Exception e){JOptionPane.showMessageDialog(null,"Error en la conexion"+e); }
         }
     private void UsuariosPorArea(){
   if(AREA.getSelectedIndex()>0){
         String sql ="select * from usuario where id_area='"+AREA.getItemAt(AREA.getSelectedIndex()).getId_area()+"'";
         
            
         try{
         Statement stm = con.createStatement();
 ResultSet rs = stm.executeQuery(sql);
 while(rs.next()){
 Usuario usu = new Usuario();
 usu.setId_usuario(rs.getInt("id_usuario"));
 usu.setId_area(rs.getInt("id_area"));
 usu.setNombre_usuario(rs.getString("nombre"));
USUA.addItem(usu);

 }
    }catch(Exception e){ JOptionPane.showMessageDialog(this, e.toString());}
     }
     }
     
         //---------------------------PROBLEMAS INGRESADOS POR LA EMPRESA                            OKKKK
     private void ProblemasEmpresa(){
try{
 Statement stm = con.createStatement();
 ResultSet rs = stm.executeQuery("select * from problema");
 String [] datos = new String[2];
 
 while(rs.next()){
    Problema prob = new Problema();
    prob.setId_problema(rs.getInt("id_problema"));
    prob.setNombre_problema(rs.getString("nombre_problema"));
 this.PROBLEMA.addItem(prob);
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

        ESTADO = new javax.swing.JTextField();
        backgroundMenuBar1 = new proyectoresidencia.BackgroundMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        popupMenu1 = new java.awt.PopupMenu();
        Mostrar = new java.awt.MenuItem();
        Salir = new java.awt.MenuItem();
        jTextField1 = new javax.swing.JTextField();
        ID_AREA = new javax.swing.JTextField();
        DISPOSITIVO = new javax.swing.JTextField();
        OTROS = new javax.swing.JTextField();
        ID = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        PROBLEMA = new javax.swing.JComboBox<Problema>();
        AREA = new javax.swing.JComboBox<Area>();
        USUA = new javax.swing.JComboBox<Usuario>();
        labelMinimizar = new javax.swing.JLabel();
        labelCerrar = new javax.swing.JLabel();
        barra = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        ESTADO.setText("1");

        jMenu2.setText("File");
        backgroundMenuBar1.add(jMenu2);

        jMenu3.setText("Edit");
        backgroundMenuBar1.add(jMenu3);

        popupMenu1.setLabel("popupMenu1");

        Mostrar.setLabel("Mostrar");
        Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarActionPerformed(evt);
            }
        });
        popupMenu1.add(Mostrar);

        Salir.setLabel("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        popupMenu1.add(Salir);

        DISPOSITIVO.setText("1");

        ID.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Realizar petición");
        setBounds(new java.awt.Rectangle(400, 400, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(0, 102, 153));
        jButton2.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Salir");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 172, -1, -1));

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
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 172, -1, -1));

        PROBLEMA.setBackground(new java.awt.Color(0, 102, 153));
        PROBLEMA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar problema" }));
        PROBLEMA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PROBLEMA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PROBLEMAMousePressed(evt);
            }
        });
        getContentPane().add(PROBLEMA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 129, 290, 32));

        AREA.setBackground(new java.awt.Color(0, 102, 153));
        AREA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar área" }));
        AREA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AREA.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                AREAItemStateChanged(evt);
            }
        });
        AREA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AREAKeyPressed(evt);
            }
        });
        getContentPane().add(AREA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 53, 290, 32));

        USUA.setBackground(new java.awt.Color(0, 102, 153));
        USUA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar usuario" }));
        USUA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(USUA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 91, 290, 32));

        labelMinimizar.setBackground(new java.awt.Color(0, 102, 153));
        labelMinimizar.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        labelMinimizar.setForeground(new java.awt.Color(255, 255, 255));
        labelMinimizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMinimizar.setText("_");
        labelMinimizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(11, 139, 203)));
        labelMinimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelMinimizar.setOpaque(true);
        labelMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMinimizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelMinimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelMinimizarMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                labelMinimizarMouseReleased(evt);
            }
        });
        getContentPane().add(labelMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 30, 20));

        labelCerrar.setBackground(new java.awt.Color(0, 102, 153));
        labelCerrar.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        labelCerrar.setForeground(new java.awt.Color(255, 255, 255));
        labelCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCerrar.setText("x");
        labelCerrar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(11, 139, 203)));
        labelCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelCerrar.setOpaque(true);
        labelCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelCerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelCerrarMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                labelCerrarMouseReleased(evt);
            }
        });
        getContentPane().add(labelCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 30, 20));

        barra.setBackground(new java.awt.Color(0, 102, 153));
        barra.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        barra.setForeground(new java.awt.Color(255, 255, 255));
        barra.setText("Peticion de usuario");
        barra.setOpaque(true);
        getContentPane().add(barra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 40));

        fondo.setBackground(new java.awt.Color(0, 102, 153));
        fondo.setOpaque(true);
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -6, 320, 220));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if( AREA.getSelectedIndex()==0 && USUA.getSelectedIndex()==0 || PROBLEMA.getSelectedIndex() ==0 ){

    JOptionPane.showMessageDialog(null,"Campos vacíos","MSG",JOptionPane.WARNING_MESSAGE);
    
}else{
//-----------------FECHA RECURRENTE DEL SERVIDOR
java.util.Date date = Calendar.getInstance().getTime();
       java.util.Date date2= new java.util.Date();
	 System.out.println(new Timestamp(date2.getTime()));

     /*  String unoCombo =  (String) USUA.getSelectedItem(); //CONVIERTE EL JCOMBOBOX A STRING 
       String decomponerCOMBO[] = unoCombo.split("-");
       Integer resultadoCombo = Integer.parseInt(decomponerCOMBO[0]);
       
       String prob =  (String) PROBLEMA.getSelectedItem(); //CONVIERTE EL JCOMBOBOX A STRING 
       String deprob[] = prob.split("-");
       Integer proble = Integer.parseInt(deprob[0]);*/
       
String insertar="insert into peticion(id_problema,id_usuario,id_tipo,id_estado,fecha_in) values (?,?,?,?,?)";
try{
PreparedStatement pst = con.prepareStatement(insertar);
                 pst.setInt(1, PROBLEMA.getItemAt(PROBLEMA.getSelectedIndex()).getId_problema());                    //PROBLEMA
                 pst.setInt(2, USUA.getItemAt(USUA.getSelectedIndex()).getId_usuario());            //USUARIO
                 pst.setString(3, DISPOSITIVO.getText());   //TIPO DE DISPOSITIVO
                 pst.setString(4,ESTADO.getText());        //ESTADO
                 pst.setTimestamp(5,new Timestamp(date2.getTime()) ); //FECHA
 int n= pst.executeUpdate();     
                 /*
                 
                    pst.setInt(1, proble);                    //PROBLEMA
                 pst.setInt(2, resultadoCombo);            //USUARIO
                 pst.setInt(3, AREA.getSelectedIndex());   //TIPO DE DISPOSITIVO
                 pst.setString(4,ESTADO.getText());        //ESTADO
                 pst.setTimestamp(5,new Timestamp(date2.getTime()) ); //FECHA
                 int n= pst.executeUpdate();
                 
                 
                 */
               ResultSet rs=pst.getGeneratedKeys(); //obtengo las ultimas llaves generadas
while(rs.next()){ 
int clave=rs.getInt(1);
ID.setText(""+clave);
}  
if(n>0){
    JOptionPane.showMessageDialog(null,"Su requerimiento ha sido enviado \n GRACIAS!","AVISO",JOptionPane.INFORMATION_MESSAGE );
AREA.setSelectedIndex(0);
USUA.addItem(null);
PROBLEMA.setSelectedIndex(0);

}     
}catch(Exception e){
 JOptionPane.showMessageDialog(null,"ERROR"+"MSG"+JOptionPane.WARNING_MESSAGE+e);
}


//---------------------------------------------------------------------OPERACION PARA INSETAR NOTAS..
if(OTROS.getText().equals("")){
//JOptionPane.showMessageDialog(null, "Sin nota","Aviso",JOptionPane.INFORMATION_MESSAGE);
}else{

String sql ="insert into peticion_nota(id_peticion,nota) values(?,?)";
try{
PreparedStatement pst = con.prepareStatement(sql);
pst.setString(1,ID.getText());
pst.setString(2,OTROS.getText());

 int n2= pst.executeUpdate();
if(n2>0){
OTROS.setText(null);
}
}catch(Exception ex){
 JOptionPane.showMessageDialog(null,"EROR: "+ex,"AVISO",JOptionPane.WARNING_MESSAGE);       
        
        }

}




}
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
x = evt.getX();
                y = evt.getY(); 
        
// TODO add your handling code here:
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
Point point = MouseInfo.getPointerInfo().getLocation();
                setLocation(point.x - x, point.y - y);        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseDragged

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
/* try{
 Statement stm = con.createStatement();
 ResultSet rs = stm.executeQuery("select * from area_empresa where id_locacion='"+ID_AREA.getText()+"'and area_empresa.activo=1");
 
 while(rs.next()){
 // this.AREA.addItem(rs.getString("nombre_area"));
       
     Area ar = new Area(); 
     ar.setId_area(rs.getInt("id_area"));
     ar.setNombre_area(rs.getString("nombre_area"));
     
     this.AREA.addItem(ar);    
 }    
 }catch(Exception e){JOptionPane.showMessageDialog(null,"Error en la conexion"+e); }*/
        
        AreaDisponible();
              
// TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void MostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarActionPerformed
sistema.remove(icono);        // TODO add your handling code here:
this.setVisible(true);
    }//GEN-LAST:event_MostrarActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_SalirActionPerformed

    private void labelMinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizarMouseEntered
        labelMinimizar.setBackground(new Color(15, 136, 197));
    }//GEN-LAST:event_labelMinimizarMouseEntered

    private void labelMinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizarMouseExited
        labelMinimizar.setBackground(new Color(0, 102, 153));
    }//GEN-LAST:event_labelMinimizarMouseExited

    private void labelMinimizarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizarMouseReleased
        this.setExtendedState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_labelMinimizarMouseReleased

    private void labelCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerrarMouseEntered
        labelCerrar.setBackground(new Color(255, 0, 53));
        //labelCerrar.setBackground(new Color(15, 136, 197));
    }//GEN-LAST:event_labelCerrarMouseEntered

    private void labelCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerrarMouseExited
        labelCerrar.setBackground(new Color(0, 102, 153));
    }//GEN-LAST:event_labelCerrarMouseExited

    private void labelCerrarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerrarMouseReleased
        System.exit(0);
    }//GEN-LAST:event_labelCerrarMouseReleased

    private void labelMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizarMouseClicked
//this.setExtendedState(javax.swing.JFrame.ICONIFIED);        // TODO add your handling code here:
        
        try{
if(SystemTray.isSupported()){
sistema.add(icono);
this.setVisible(false);
      MensajeTrayIcon(" \n Puedes abrir la aplicacion \n con un solo clic\"", MessageType.INFO);
             
}
}catch(Exception ex){
JOptionPane.showMessageDialog(this, ex.getMessage());
}
    }//GEN-LAST:event_labelMinimizarMouseClicked

    private void labelCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerrarMouseClicked
System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_labelCerrarMouseClicked

    private void AREAItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_AREAItemStateChanged
        if(AREA.getSelectedIndex()>-1){
            USUA.removeAllItems();
            UsuariosPorArea();
        }                 // TODO add your handling code here:
    }//GEN-LAST:event_AREAItemStateChanged

    private void AREAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AREAKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AREAKeyPressed

    private void PROBLEMAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PROBLEMAMousePressed
   //INSETA CUANDO SE SELECCIONA LA OPCION DE OTROS
String otro = PROBLEMA.getSelectedItem().toString();

if(otro.equals("Otros")){
String InsertaOtros = JOptionPane.showInputDialog("Escribir el problema");
OTROS.setText(InsertaOtros);


//LEFT join peticion_nota pno on pe.id_peticion =  pno.id_peticion
} 
 
// TODO add your handling code here:
    }//GEN-LAST:event_PROBLEMAMousePressed

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
            java.util.logging.Logger.getLogger(PeticionUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PeticionUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PeticionUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PeticionUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PeticionUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Area> AREA;
    private javax.swing.JTextField DISPOSITIVO;
    private javax.swing.JTextField ESTADO;
    private javax.swing.JLabel ID;
    public static javax.swing.JTextField ID_AREA;
    private java.awt.MenuItem Mostrar;
    private javax.swing.JTextField OTROS;
    private javax.swing.JComboBox<Problema> PROBLEMA;
    private java.awt.MenuItem Salir;
    private javax.swing.JComboBox<Usuario> USUA;
    private proyectoresidencia.BackgroundMenuBar backgroundMenuBar1;
    private javax.swing.JLabel barra;
    private javax.swing.JLabel fondo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    public static javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelCerrar;
    private javax.swing.JLabel labelMinimizar;
    private java.awt.PopupMenu popupMenu1;
    // End of variables declaration//GEN-END:variables
}
