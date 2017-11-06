/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.Conexion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.daoUsuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 *
 * @author skriom
 */
@WebServlet(name = "GenerarPDFALL", urlPatterns = {"/GenerarPDFALL"})
public class GenerarPDFALL extends HttpServlet {
    private PrintWriter varOut = null;
    private daoUsuario daoUsuario = null;
    private HttpServletRequest varRequest = null;
    JSONObject varJsonObjectP = new JSONObject();
    int total = 0;
    int total1 = 0;
    JSONArray varJsonArrayP = new JSONArray();
    JSONObject varJsonObjectResultado = new JSONObject();
    String varSql = "";
    String sql = "";
    String varSql1 = "";
    String sql1 = "";
    int jtStartIndex; int jtPageSize; String jtSorting;
    String file_path = "";
    File file;
    Document doc;
    OutputStream out;
    String dir = "", sub = "";
    Conexion cx;
    int tam = 0, filas = 0;

    public GenerarPDFALL() {
        cx=new Conexion();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/pdf");
        response.setCharacterEncoding("UTF-8");
        doc = new Document();
        out = response.getOutputStream();
        crear();
        doc.close();

    }
    public void crear()throws ServletException, IOException{
        
        try {
            varSql = "SELECT * FROM usuariotabla ;";
                sql = "SELECT COUNT(*) as TotalRecordCount FROM usuariotabla" ;
            PreparedStatement varPst = cx.conectar().prepareStatement(varSql);
            PreparedStatement tt = cx.conectar().prepareStatement(sql);
            ResultSet rs = tt.executeQuery();
            if (rs.next()) {
                total = Integer.parseInt(rs.getString("TotalRecordCount"));
            }
            ResultSet varResultado = varPst.executeQuery();
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            varSql1 = "SELECT * FROM inf ;";
            //varSql1 = "SELECT * FROM inf WHERE idusuario ="+total+" ;";
                sql1 = "SELECT COUNT(*) as TotalRecordCount FROM inf" ;
            PreparedStatement varPst1 = cx.conectar().prepareStatement(varSql1);
            PreparedStatement tt1 = cx.conectar().prepareStatement(sql1);
            ResultSet rs1 = tt1.executeQuery();
            if (rs1.next()) {
                total1 = Integer.parseInt(rs1.getString("TotalRecordCount"));
            }
            ResultSet varResultado1 = varPst1.executeQuery();
            
            
            PdfWriter.getInstance(doc, out);
            doc.open();

            String ruta = this.getServletContext().getRealPath("/imagen.jpg");
            Image img = Image.getInstance(ruta);
            img.setAlignment(Element.ALIGN_CENTER);
            img.scaleToFit(520, 80);
            doc.add(img);
            Paragraph p1 = new Paragraph(7);
            Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK);
            Font n = new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.NORMAL, BaseColor.BLACK);
            p1.add(Chunk.NEWLINE);
            p1.add(new Phrase("GRUPO: ", normal));
            p1.add(Chunk.TABBING);
            p1.add(Chunk.TABBING);
            p1.add(new Phrase("SEMESTRE: ", normal));
            p1.add(Chunk.TABBING);
            p1.add(Chunk.TABBING);
            p1.add(new Phrase("ESPECIALIDAD: ", normal));
            p1.add(Chunk.NEWLINE);
            p1.add(new Phrase("TURNO: ", normal));
            p1.add(Chunk.TABBING);
            p1.add(Chunk.TABBING);
            p1.add(new Phrase("AULA: ", normal));
            p1.add(Chunk.NEWLINE);
            p1.add(Chunk.NEWLINE);
            p1.add(Chunk.NEWLINE);
            p1.setAlignment(Element.ALIGN_CENTER);
            doc.add(p1);
            Font negrit = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK);
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100);
            PdfPCell f11 = new PdfPCell(new Phrase("NOMBRE", negrit));
            PdfPCell f12 = new PdfPCell(new Phrase("EDAD", negrit));
            PdfPCell f13 = new PdfPCell(new Phrase("CORREO", negrit));
            PdfPCell f14 = new PdfPCell(new Phrase("TELEFONO", negrit));
            PdfPCell f15 = new PdfPCell(new Phrase("APELLIDOS", negrit));
            f11.setHorizontalAlignment(Element.ALIGN_CENTER);
            f12.setHorizontalAlignment(Element.ALIGN_CENTER);
            f13.setHorizontalAlignment(Element.ALIGN_CENTER);
            f14.setHorizontalAlignment(Element.ALIGN_CENTER);
            f15.setHorizontalAlignment(Element.ALIGN_CENTER);
            f11.setBackgroundColor(GrayColor.LIGHT_GRAY);
            f12.setBackgroundColor(GrayColor.LIGHT_GRAY);
            f13.setBackgroundColor(GrayColor.LIGHT_GRAY);
            f14.setBackgroundColor(GrayColor.LIGHT_GRAY);
            f15.setBackgroundColor(GrayColor.LIGHT_GRAY);
            tabla.addCell(f11);
            tabla.addCell(f12);
            tabla.addCell(f13);
            tabla.addCell(f14);
            tabla.addCell(f15);
            while (varResultado.next()) {
                tabla.addCell( varResultado.getString("nombre"));
                tabla.addCell(varResultado.getString("edad"));
                tabla.addCell(varResultado.getString("correo"));
                tabla.addCell(varResultado.getString("telefono"));
                tabla.addCell( varResultado.getString("apellidos"));
                //System.out.println(varJsonArrayP);
            }
            varResultado.close();
            varResultado = null;
            varPst.close();
            varPst = null; 
            cx.desconectar();
            
            doc.add(tabla);

        } catch (DocumentException ex) {
            Logger.getLogger(GenerarPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e);
        }
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
