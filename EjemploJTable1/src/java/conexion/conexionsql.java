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
public class conexionsql {
    String bd = "usuario";
    String connectionurl = "jdbc:sqlserver://queen_&_king; databaseName=usuario";
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String user = "root";
    String pass = "";

    public Connection cx;

    public Connection conectar() {
        try {            
            Class.forName(driver);
            cx = (Connection) DriverManager.getConnection(connectionurl, user, pass);
            System.out.println("SE CONECTO");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("NO SE CONECTO");
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
