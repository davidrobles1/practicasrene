
package dao;

import modelo.informacion;
import conexion.Conexion;
import conexion.conexionsql;
import conexion.conexionoracle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class daoUsuario {
    Conexion cx;
    //conexionsql cx;
    //conexionoracle cx;
    String tabla="usuariotabla";
    String tabla1="inf";
    
    public daoUsuario(){
        cx=new Conexion();
        //cx=new conexionsql();
        //cx = new conexionoracle();
    }
    
    public JSONObject select(int jtStartIndex, int jtPageSize, String jtSorting) {
        JSONObject varJsonObjectP = new JSONObject();
        JSONArray varJsonArrayP = new JSONArray();
        JSONObject varJsonObjectResultado = new JSONObject();
        String varSql = "";
        String sql = "";
        int total = 0;
        try {
                varSql = "SELECT * FROM usuariotabla ORDER BY " + jtSorting + " LIMIT " + jtStartIndex + ", " + jtPageSize + ";";
                sql = "SELECT COUNT(*) as TotalRecordCount FROM usuariotabla" ;
            PreparedStatement varPst = cx.conectar().prepareStatement(varSql);
            PreparedStatement tt = cx.conectar().prepareStatement(sql);
            ResultSet rs = tt.executeQuery();
            if (rs.next()) {
                total = Integer.parseInt(rs.getString("TotalRecordCount"));
            }
            ResultSet varResultado = varPst.executeQuery();
            while (varResultado.next()) {
                varJsonObjectP.put("idusuario", varResultado.getInt("idusuario"));
                varJsonObjectP.put("nombre", varResultado.getString("nombre"));
                varJsonObjectP.put("edad", varResultado.getInt("edad"));
                varJsonObjectP.put("correo", varResultado.getString("correo"));
                varJsonObjectP.put("telefono", varResultado.getString("telefono"));
                varJsonObjectP.put("apellidos", varResultado.getString("apellidos"));
                varJsonArrayP.add(varJsonObjectP);
                //System.out.println(varJsonArrayP);
            }
            varResultado.close();
            varResultado = null;
            varPst.close();
            varPst = null;
            cx.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e);
        }
        
        varJsonObjectResultado.put("Result", "OK");
        varJsonObjectResultado.put("TotalRecordCount", total);
        varJsonObjectResultado.put("Records", varJsonArrayP);
        
        return varJsonObjectResultado;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public JSONObject insert(Usuario a) {
        JSONObject varJsonObjectResultado = new JSONObject();
        JSONObject varJsonObjectRegistro = new JSONObject();
        try {
            insertarBD(a);
            String varSql = "SELECT * FROM " + tabla + ";";
            PreparedStatement varPst = cx.conectar().prepareStatement(varSql);
            ResultSet varResultado = varPst.executeQuery();
            while (varResultado.next()) {
                varJsonObjectRegistro.put("idusuario", varResultado.getInt("idusuario"));
                varJsonObjectRegistro.put("nombre", varResultado.getString("nombre"));
                varJsonObjectRegistro.put("edad", varResultado.getInt("edad"));
                varJsonObjectRegistro.put("correo", varResultado.getString("correo"));
                varJsonObjectRegistro.put("telefono", varResultado.getString("telefono"));
                varJsonObjectRegistro.put("apellidos", varResultado.getString("apellidos"));
            }
            varJsonObjectResultado.put("Result", "OK");
            varJsonObjectResultado.put("Record", varJsonObjectRegistro);
            varResultado.close();
            varResultado = null;
            varPst.close();
            varPst = null;
            cx.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e);
        }
        return varJsonObjectResultado;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void insertarBD(Usuario a) {
        try {
            String sql = "INSERT INTO " + tabla + " "
                    + "(nombre,edad,correo,telefono,apellidos) "
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement varPst = cx.conectar().prepareStatement(sql);
            varPst.setString(1, a.getUsuario());
            varPst.setInt(2, a.getEdad());
            varPst.setString(3, a.getCorreo());
            varPst.setString(4, a.getTelefono());
            varPst.setString(5, a.getApellidos());
            varPst.execute();
            varPst.close();
            varPst = null;
            cx.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e);
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
     
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public JSONObject update(Usuario a) {
        JSONObject varJsonObjectResultado = new JSONObject();
        try {
            System.out.println("id" + a.getIdUsuario());
            String sql = "UPDATE " + tabla + " "
                    + "SET  "
                    + " nombre= ?,"
                    + " edad= ?,"
                    + " correo= ?,"
                    + " telefono= ?,"
                    + " apellidos= ? "
                    + " WHERE idusuario= ? ;";           
            PreparedStatement varPst = cx.conectar().prepareStatement(sql);
            varPst.setString(1, a.getUsuario());
            varPst.setInt(2, a.getEdad());
            varPst.setString(3, a.getCorreo());
            varPst.setString(4, a.getTelefono());
            varPst.setString(5, a.getApellidos());
            varPst.setInt(6, a.getIdUsuario());   
            varPst.executeUpdate();
            varJsonObjectResultado.put("Result", "OK");
            varPst.close();
            varPst = null;
            cx.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e);
        }
        return varJsonObjectResultado;
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public JSONObject delete(String IdUsuario) {
        JSONObject varJsonObjectResultado = new JSONObject();
        try {
            String sql = "DELETE FROM  " + tabla + " "
                    + "WHERE idusuario =?;";
            PreparedStatement varPst = cx.conectar().prepareStatement(sql);
            varPst.setInt(1, Integer.parseInt(IdUsuario));
            varPst.executeUpdate();
            varJsonObjectResultado.put("Result", "OK");
            varPst.close();
            varPst = null;
            cx.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e);
        }
        return varJsonObjectResultado;
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
}
