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
import dao.daoUsuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import net.sf.json.JSONObject;
import modelo.informacion;

/**
 *
 * @author skriom
 */
@WebServlet(name = "Control", urlPatterns = {"/Control"})
public class Control extends HttpServlet {
    
    private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;
    private PrintWriter varOut = null;
    private HttpSession varSession = null;
    private daoUsuario daoUsuario = null;
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
        daoUsuario=new daoUsuario();
        try {
            response.setContentType("txt/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            varRequest = request;
            varResponse = response;
            varSession = request.getSession();
            varOut = varResponse.getWriter();
            switch (varRequest.getParameter("action")) {
                case "list":
                    list();
                    break;
                case "create":
                    create();
                    break;
                case "update":
                    update();
                    break;
                case "delete":
                    delete();
                    break;
                case "GenerarPDF":
                    GenerarPDF pdf1 = new GenerarPDF();
                    pdf1.processRequest(request, response);
                    break;
                case "GenerarPDFALL":
                    GenerarPDFALL pdf2 = new GenerarPDFALL();
                    pdf2.processRequest(request, response);
                    break;
                default:
                    break;
            }
            varOut.close();
        } catch (SQLException e) {

        }
    }
    private void list() throws SQLException {
        int jtStartIndex = Integer.parseInt(varRequest.getParameter("jtStartIndex"));
        String jtSorting = varRequest.getParameter("jtSorting");
        int jtPageSize = Integer.parseInt(varRequest.getParameter("jtPageSize"));
        JSONObject varJObjectLista = daoUsuario.select(jtStartIndex, jtPageSize, jtSorting);
        varOut.print(varJObjectLista);
    }
//    public boolean esNumero(String texto) {
//        boolean es = false;
//        try {
//            Integer.parseInt(texto);
//            es = true;
//        } catch (Exception e) {
//            es = false;
//        }
//        return es;
//    }
    private void create() throws SQLException {
        Usuario dat = new Usuario();
        dat.setUsuario(varRequest.getParameter("nombre"));
        dat.setEdad(Integer.parseInt(varRequest.getParameter("edad")));
        dat.setCorreo(varRequest.getParameter("correo"));
        dat.setTelefono(varRequest.getParameter("telefono"));
        dat.setApellidos(varRequest.getParameter("apellidos"));
        JSONObject varJObjectNuevoRegistro = daoUsuario.insert(dat);
        varOut.print(varJObjectNuevoRegistro);
    }
    private void update() throws SQLException {
        Usuario dat = new Usuario();
        dat.setIdUsuario(Integer.parseInt(varRequest.getParameter("idusuario").trim()));
        dat.setUsuario(varRequest.getParameter("nombre"));
        dat.setEdad(Integer.parseInt(varRequest.getParameter("edad")));
        dat.setCorreo(varRequest.getParameter("correo"));
        dat.setTelefono(varRequest.getParameter("telefono"));
        dat.setApellidos(varRequest.getParameter("apellidos"));
        JSONObject varJObjectNuevoRegistro = daoUsuario.update(dat);
        varOut.print(varJObjectNuevoRegistro);
    }
    private void delete() throws SQLException {
        String IdUsuario = varRequest.getParameter("idusuario").trim();
        JSONObject varJObjectLista = daoUsuario.delete(IdUsuario);
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
