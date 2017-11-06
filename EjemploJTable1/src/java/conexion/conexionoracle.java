/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author queen_&_king
 */
public class conexionoracle {
    String connectionurl = "jdbc:oracle:thin:@localhost:1521:xe";
    String user = "system";
    String pass = "";

    public Connection cx;

    public Connection conectar() {
        try {            
            Class.forName("oracle.jdbc.OracleDriver").newInstance();
            cx = (Connection) DriverManager.getConnection(connectionurl, user, pass);
            System.out.println("SE CONECTO");
        }catch(Exception e){ 
            System.out.println("no se conecto"+e.toString());
        }
        return cx;
    }
    public void desconectar(){
        try {
            cx.close();
        } catch (SQLException ex) {
            ex.printStackTrace();            
        }
        
    }
    public static void main(String[] args) {
         Conexion c = new Conexion();
         c.conectar();
    }
}
