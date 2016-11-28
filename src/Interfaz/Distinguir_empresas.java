/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.SpellChecker;
import com.sun.awt.AWTUtilities;
import com.sun.glass.events.KeyEvent;
import conexiones.ProyectoResidencia;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import proyectoresidencia.Fondo_empresas;

/**
 *
 * @author Carlos Barahona
 */
public class Distinguir_empresas extends javax.swing.JFrame {
private int x;
private int y;
private Border wrongBorder = BorderFactory.createLineBorder(Color.RED);
    /**
     * Creates new form Distinguir_empresas
     */
    public Distinguir_empresas() {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
 //FORMA REDONDA A LA VENTANA
   
 /*  Shape forma = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 60, 70); 
  AWTUtilities.setWindowShape(this, forma);     

//--------------------IMAMGEN DE FONDO
  Fondo_empresas p = new Fondo_empresas("PETIl.png"); 
this.add( p , BorderLayout.EAST); 
p.repaint();
*/
//ICONO DEL SOFTWAWE
         Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconos/Icono.png"));
       setIconImage(icon);
setVisible(true);
/*
SpellChecker.setUserDictionaryProvider(new FileUserDictionary());
SpellChecker.registerDictionaries(null, null);
SpellChecker.register(CLAVE);
SpellChecker.register(USUARIO);

*/

    }
    
    File file = new File(System.getProperty("user.home")+"/AppData/Local/save.txt");
    
     public void SAVE(){      //Save the UserName and Password (for one user)
        try {
            if(!file.exists()) file.createNewFile();  //if the file !exist create a new one

            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
            bw.write(USUARIO.getText());
            bw.write("\n"); //AGREGA UN ESPACIO PARA QE ESTE SEA RECONOCIDO POR EL SISTEMA...
            bw.write(CLAVE.getText()); //write the name
            bw.newLine(); //leave a new Line
           // bw.write(password.getPassword()); //write the password
            bw.close(); //close the BufferdWriter

        } catch (IOException e) { e.printStackTrace(); }        

 }//End Of Save
     public void UPDATE(){ //UPDATE ON OPENING THE APPLICATION
        try {
          if(file.exists()){    //if this file exists

            Scanner scan = new Scanner(file);   //Use Scanner to read the File
            USUARIO.setText(scan.nextLine());  //append the text to name field
            CLAVE.setText(scan.nextLine()); //append the text to password field
           
               CLAVE.setEnabled(false); //SI EXISTE EL ARCHIVO CON EL USUARIO Y LA CONTRASEÑA ESTOS SE OCULTAN
               USUARIO.setEnabled(false);
               jCheckBox1.setEnabled(false);             
            scan.close();
          }

        } catch (FileNotFoundException e) {         
            e.printStackTrace();
        }                

   }//End OF UPDATE
    
    void iniciar(String usua, String contra){
        try {
            String capturar=""; //Valida el tipo de usuario               
            String sql="SELECT * FROM usuario WHERE usuario_login='"+usua+"'&& clave='"+contra+"'";
            
            Statement stp = con.createStatement();
            ResultSet rsl=stp.executeQuery(sql);
            String[] datos = new String[3];
            while(rsl.next()){
          datos[0]=rsl.getString("id_usuario"); // ID_USUARIO DE LA TABLA USUARIOS
          datos[1]=rsl.getString("id_perfil");  // PERFIL DEL USUARIO, 1,2,3
          
            capturar=rsl.getString("id_perfil");// PERFIL DEL USUARIO 1,2,3 EN UN ARRAY
            }
            if(capturar.equals("3")){ // ----------------------PERFIL 3 INVITADO
            this.setVisible(false);
            //JOptionPane.showMessageDialog(this,"Administrador");
            Invitado_Administrador admin = new Invitado_Administrador();
            admin.setVisible(true);
            admin.pack();
           Invitado_Administrador.CONECTADO.setText(datos[0]);
            } 
          
            if(capturar.equals("2")){ //-----------------PERFIL 2  ADMINISTRADOR
            this.setVisible(false);
            //JOptionPane.showMessageDialog(this,"Administrador");
            PantallaPrincipal admin = new PantallaPrincipal();
            admin.setVisible(true);
            admin.pack();
            Administrador.ADMIN_CONECTADO.setText(datos[0]);
           //Invitado_Administrador.CONECTADO.setText(usua);
            } 
            
            if(capturar.equals("1")){ //-----------------PERFIL 1        USUARIO
            this.setVisible(false);
            PeticionUsuario admin = new PeticionUsuario();
            admin.setVisible(true);
            admin.pack();  
            String sql2 = "select area_empresa.id_locacion from "
                    + "area_empresa inner join usuario on usuario.id_area = area_empresa.id_area "
                    + "where usuario.id_usuario='"+datos[0]+"'";
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery(sql2);
            String[] datos2 = new String[3];
            
            while(rs.next()){
            datos2[0]=rs.getString("id_locacion");
            PeticionUsuario.ID_AREA.setText(datos2[0]);
            }
            }
           /*
            
            if(capturar.equals("1")){ //-----------------PERFIL 1        USUARIO
            this.setVisible(false);
            PeticionUsuario admin = new PeticionUsuario();
            admin.setVisible(true);
            admin.pack();  
            String sql2 = "select area_empresa.id_locacion from area_empresa inner join usuario on usuario.id_area = area_empresa.id_area where usuario.id_usuario='"+datos[0]+"'";
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery(sql2);
            String[] datos2 = new String[3];
            
            while(rs.next()){
            datos2[0]=rs.getString("id_locacion");
            PeticionUsuario.ID_AREA.setText(datos2[0]);
            
            }
            }
            
            */
            if((!capturar.equals("3"  ))&& (!capturar.equals("2"))){
             // USUARIO.setToolTipText("Error de usuario o contraseña");
              //USUARIO.setBorder(wrongBorder);
              //  JOptionPane.showMessageDialog(null,"Usuario o contraseña falsos!","Aviso", JOptionPane.WARNING_MESSAGE);
               
       //        jLabel2.setText("No existe ese usuario ");
               //jLabel2.setForeground(Color.red); 
                // JOptionPane.showMessageDialog(this,"No existe ese usuario ");
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Distinguir_empresas.class.getName()).log(Level.SEVERE, null, ex);
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

        CLAVE = new proyectoresidencia.JCPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        USUARIO = new proyectoresidencia.JCTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        ACTIVADO = new javax.swing.JLabel();
        labelMinimizar = new javax.swing.JLabel();
        labelCerrar = new javax.swing.JLabel();
        labelBarra = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CLAVE.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        CLAVE.setPlaceholder("Escribir contraseña");
        CLAVE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CLAVEKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CLAVEKeyTyped(evt);
            }
        });
        getContentPane().add(CLAVE, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 130, 210, -1));

        jButton1.setBackground(new java.awt.Color(0, 102, 153));
        jButton1.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Iniciar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/calendario.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        jButton2.setBackground(new java.awt.Color(0, 102, 153));
        jButton2.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cancelar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, -1));

        USUARIO.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        USUARIO.setPlaceholder("Escribir usuario");
        USUARIO.setSelectionColor(new java.awt.Color(0, 0, 0));
        USUARIO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                USUARIOMousePressed(evt);
            }
        });
        USUARIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                USUARIOActionPerformed(evt);
            }
        });
        USUARIO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                USUARIOKeyTyped(evt);
            }
        });
        getContentPane().add(USUARIO, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 80, 210, -1));

        jCheckBox1.setBackground(new java.awt.Color(0, 102, 153));
        jCheckBox1.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Recordar usuario");
        jCheckBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 210, -1));

        ACTIVADO.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        ACTIVADO.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(ACTIVADO, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 210, -1));

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
        getContentPane().add(labelMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 30, 20));

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
        getContentPane().add(labelCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 30, 20));

        labelBarra.setBackground(new java.awt.Color(0, 102, 140));
        labelBarra.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        labelBarra.setForeground(new java.awt.Color(255, 255, 255));
        labelBarra.setText("Iniciar sesion");
        labelBarra.setOpaque(true);
        labelBarra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                labelBarraMouseDragged(evt);
            }
        });
        labelBarra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelBarraMousePressed(evt);
            }
        });
        getContentPane().add(labelBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 40));

        fondo.setBackground(new java.awt.Color(0, 102, 153));
        fondo.setOpaque(true);
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -6, 280, 270));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
x = evt.getX();
                y = evt.getY();          // TODO add your handling code here:
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
Point point = MouseInfo.getPointerInfo().getLocation();
                setLocation(point.x - x, point.y - y);         // TODO add your handling code here:
    }//GEN-LAST:event_formMouseDragged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 String usu=USUARIO.getText();
    String cont=new String(CLAVE.getPassword());
    iniciar(usu, cont);
    
 if(jCheckBox1.isSelected()){
                  SAVE(); //Save This UserName and his PassWord     
               }
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
String usu=USUARIO.getText();
    String cont=new String(CLAVE.getPassword());
    iniciar(usu, cont);         // TODO add your handling code here:
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
System.exit(0);         // TODO add your handling code here:
    }//GEN-LAST:event_jButton2KeyPressed

    private void USUARIOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_USUARIOKeyTyped
char c = evt.getKeyChar();
 if((int)evt.getKeyChar()>32 && (int)evt.getKeyChar()<=47 
        || (int)evt.getKeyChar()>57 && (int)evt.getKeyChar()<=63 ||
        (int)evt.getKeyChar()>90 && (int)evt.getKeyChar()<=96 || 
        (int)evt.getKeyChar()>122 && (int)evt.getKeyChar()<=255
        ){

getToolkit().beep();
evt.consume();
JOptionPane.showMessageDialog(null,"Carácter no permitido","Aviso",JOptionPane.ERROR_MESSAGE);
USUARIO.setCursor(null);


}
        
// TODO add your handling code here:
    }//GEN-LAST:event_USUARIOKeyTyped

    private void CLAVEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CLAVEKeyTyped
    
char c = evt.getKeyChar();
 if((int)evt.getKeyChar()>32 && (int)evt.getKeyChar()<=47 
        || (int)evt.getKeyChar()>57 && (int)evt.getKeyChar()<=63 ||
        (int)evt.getKeyChar()>90 && (int)evt.getKeyChar()<=96 || 
        (int)evt.getKeyChar()>122 && (int)evt.getKeyChar()<=255
        ){

getToolkit().beep();
evt.consume();
JOptionPane.showMessageDialog(null,"Carácter no permitido","Aviso",JOptionPane.ERROR_MESSAGE);
CLAVE.setCursor(null);
}  
  String usu=USUARIO.getText();
    String cont=new String(CLAVE.getPassword());
    iniciar(usu, cont);
    
 if(jCheckBox1.isSelected()){
                  SAVE(); //Save This UserName and his PassWord     
               }
 
 
// TODO add your handling code here:
    }//GEN-LAST:event_CLAVEKeyTyped

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
UPDATE();
String usu=USUARIO.getText();
    String cont=new String(CLAVE.getPassword());
    iniciar(usu, cont);

               // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void labelBarraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBarraMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_labelBarraMouseDragged

    private void labelBarraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBarraMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_labelBarraMousePressed

    private void labelMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizarMouseClicked
        this.setExtendedState(javax.swing.JFrame.ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_labelMinimizarMouseClicked

    private void labelMinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizarMouseEntered
        labelMinimizar.setBackground(new Color(15, 136, 197));
    }//GEN-LAST:event_labelMinimizarMouseEntered

    private void labelMinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizarMouseExited
        labelMinimizar.setBackground(new Color(0, 102, 153));
    }//GEN-LAST:event_labelMinimizarMouseExited

    private void labelMinimizarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizarMouseReleased
        this.setExtendedState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_labelMinimizarMouseReleased

    private void labelCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerrarMouseClicked
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_labelCerrarMouseClicked

    private void labelCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerrarMouseEntered
        labelCerrar.setBackground(new Color(255, 0, 53));
    }//GEN-LAST:event_labelCerrarMouseEntered

    private void labelCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerrarMouseExited
        labelCerrar.setBackground(new Color(0, 102, 153));
    }//GEN-LAST:event_labelCerrarMouseExited

    private void labelCerrarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerrarMouseReleased
        System.exit(0);
    }//GEN-LAST:event_labelCerrarMouseReleased

    private void USUARIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_USUARIOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_USUARIOActionPerformed

    private void CLAVEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CLAVEKeyPressed

        
        // TODO add your handling code here:
    }//GEN-LAST:event_CLAVEKeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

//VERIFICA SI LA TECLA MAYUSCULA ESTA ACTIVADA O NO
        if (true) {
    boolean isOn = Toolkit.getDefaultToolkit().getLockingKeyState(
        KeyEvent.VK_CAPS_LOCK);
    ACTIVADO.setText("" + (isOn ? "Mayuscula activada" : ""));
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
    }
}        

// TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void USUARIOMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_USUARIOMousePressed
         // TODO add your handling code here:
    }//GEN-LAST:event_USUARIOMousePressed

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
            java.util.logging.Logger.getLogger(Distinguir_empresas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Distinguir_empresas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Distinguir_empresas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Distinguir_empresas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Distinguir_empresas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ACTIVADO;
    private proyectoresidencia.JCPasswordField CLAVE;
    private proyectoresidencia.JCTextField USUARIO;
    private javax.swing.JLabel fondo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel labelBarra;
    private javax.swing.JLabel labelCerrar;
    private javax.swing.JLabel labelMinimizar;
    // End of variables declaration//GEN-END:variables
ProyectoResidencia cc = new ProyectoResidencia();
Connection con=cc.conexion();
}
