/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.hermanos;
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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 *
 * @author queen_&_king
 */
public class daoinfo {
    Conexion cx;
    //conexionsql cx;
    //conexionoracle cx;
    String tabla1="info";

    public daoinfo() {
        cx=new Conexion();
        //cx=new conexionsql();
        //cx = new conexionoracle();
    }
    
    public JSONObject select1(int id) {
        JSONObject varJsonObjectP = new JSONObject();
        JSONArray varJsonArrayP = new JSONArray();
        JSONObject varJsonObjectResultado = new JSONObject();
        String varSql = "";
        String sql = "";
        int total = 0;
        try {
                varSql = "SELECT * FROM info WHERE idinf ="+id+" ;";
                sql = "SELECT COUNT(*) as TotalRecordCount FROM info" ;
            PreparedStatement varPst = cx.conectar().prepareStatement(varSql);
            PreparedStatement tt = cx.conectar().prepareStatement(sql);
            ResultSet rs = tt.executeQuery();
            if (rs.next()) {
                total = Integer.parseInt(rs.getString("TotalRecordCount"));
            }
            ResultSet varResultado = varPst.executeQuery();
            while (varResultado.next()) {
                varJsonObjectP.put("id", Integer.parseInt(varResultado.getString("id")));
                varJsonObjectP.put("idinf", Integer.parseInt(varResultado.getString("idinf")));
                varJsonObjectP.put("hermanomayor", varResultado.getString("hermanomayor"));
                varJsonObjectP.put("hermanomenor", varResultado.getString("hermanomenor"));
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
    
    public JSONObject insert1(hermanos a) {
        JSONObject varJsonObjectResultado = new JSONObject();
        JSONObject varJsonObjectRegistro = new JSONObject();
        try {
            insertarBD1(a);
            String varSql = "SELECT * FROM " + tabla1 + ";";
            PreparedStatement varPst = cx.conectar().prepareStatement(varSql);
            ResultSet varResultado = varPst.executeQuery();
            while (varResultado.next()) {
                varJsonObjectRegistro.put("id", varResultado.getInt("id"));
//                varJsonObjectRegistro.put("idusuario", varResultado.getInt("iusuario"));
                varJsonObjectRegistro.put("hermanomayor", varResultado.getString("hermanomayor"));
                varJsonObjectRegistro.put("hermanomenor", varResultado.getString("hermanomenor"));
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
    
    private void insertarBD1(hermanos a) {
        try {
            String sql = "INSERT INTO " + tabla1 + " "
                    + "(idinf,hermanomayor, hermanomenor) "
                    + "VALUES (?,?,?)";
            PreparedStatement varPst = cx.conectar().prepareStatement(sql);
            varPst.setInt(1, a.getIdinf());
            varPst.setString(2, a.getMayor());
            varPst.setString(3, a.getMenor());
            varPst.execute();
            varPst.close();
            varPst = null;
            cx.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e);
        }
    }
    
    public JSONObject update1(hermanos a) {
        JSONObject varJsonObjectResultado = new JSONObject();
        try {
            System.out.println("id=" + a.getId());
            String sql = "UPDATE " + tabla1 + " "
                    + "SET  "
                    + " hermanomayor= ?,"
                    + " hermanomenor= ?,"
                    + " WHERE id= ? ;";           
            PreparedStatement varPst = cx.conectar().prepareStatement(sql);
            varPst.setString(1, a.getMayor());
            varPst.setString(2, a.getMenor());
            varPst.setInt(3, a.getId());   
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
    
    public JSONObject delete1(String Idinfo) {
        JSONObject varJsonObjectResultado = new JSONObject();
        try {
            String sql = "DELETE FROM  " + tabla1 + " "
                    + "WHERE id =?;";
            PreparedStatement varPst = cx.conectar().prepareStatement(sql);
            varPst.setInt(1, Integer.parseInt(Idinfo));
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
    
}
