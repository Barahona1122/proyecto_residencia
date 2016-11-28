/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexiones;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**@author Carlos Barahona */
public class ProyectoResidencia{
    Connection con = null;
    public Connection conexion(){
         try{
            Class.forName("com.mysql.jdbc.Driver"); //http://soportesistemasvt.servehttp.com/
           //con=DriverManager.getConnection("jdbc:mysql://soportesistemasvt.servehttp.com/proyecto_residencia","sistema","S15t3m45"); 
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/proyecto_residencia","root","1234"); //se cambio esto 127.0.0.1
//JOptionPane.showMessageDialog(null,"SI HAY conexion");
            System.out.println("Conexion establecida");
        }catch(Exception e ){
            JOptionPane.showMessageDialog(null,"Error en la conexion");
          System.out.println("Error en la conexion "+e);     
        }
        return con; 
    }
    Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    PreparedStatement prepareStatement(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }