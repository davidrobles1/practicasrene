/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import dao.daoinformacion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import net.sf.json.JSONObject;
import modelo.informacion;

/**
 *
 * @author queen_&_king
 */
@WebServlet(name = "controlo", urlPatterns = {"/controlo"})
public class controlo extends HttpServlet {
    
    private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;
    private PrintWriter varOut = null;
    private HttpSession varSession = null;
    private daoinformacion daoUsuario = null;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        daoUsuario=new daoinformacion();
        try {
            response.setContentType("txt/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            varRequest = request;
            varResponse = response;
            varSession = request.getSession();
            varOut = varResponse.getWriter();
            //int id1=Integer.parseInt(varRequest.getParameter("idusuario"));
            switch (varRequest.getParameter("action")) {
                case "list":
                    list1();
                    break;
                case "create":
                    create1();
                    break;
                case "update":
                    update1();
                    break;
                case "delete":
                    delete1();
                    break;
                default:
                    break;
            }
            varOut.close();
        } catch (SQLException e) {

        }
    }
    private void list1() throws SQLException {
        int z = Integer.parseInt(varRequest.getParameter("idusuario"));
        JSONObject varJObjectLista = daoUsuario.select1(z);
        varOut.print(varJObjectLista);
    }
    public boolean esNumero(String texto) {
        boolean es = false;
        try {
            Integer.parseInt(texto);
            es = true;
        } catch (Exception e) {
            es = false;
        }
        return es;
    }
    private void create1() throws SQLException {
        informacion dat = new informacion();
        dat.setIdusuario(Integer.parseInt(varRequest.getParameter("idusuario").trim()));
        dat.setInfo(varRequest.getParameter("info"));
        dat.setApodo(varRequest.getParameter("apodo"));
        JSONObject varJObjectNuevoRegistro = daoUsuario.insert1(dat);
        varOut.print(varJObjectNuevoRegistro);
    }
    private void update1() throws SQLException {
        informacion dat = new informacion();
        dat.setId(Integer.parseInt(varRequest.getParameter("id").trim()));
        dat.setIdusuario(Integer.parseInt(varRequest.getParameter("idusuario").trim()));
        dat.setInfo(varRequest.getParameter("info"));
        dat.setApodo(varRequest.getParameter("apodo"));
        JSONObject varJObjectNuevoRegistro = daoUsuario.update1(dat);
        varOut.print(varJObjectNuevoRegistro);
    }
    private void delete1() throws SQLException {
        String Idinfo = varRequest.getParameter("id").trim();
        JSONObject varJObjectLista = daoUsuario.delete1(Idinfo);
        varOut.print(varJObjectLista);
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

