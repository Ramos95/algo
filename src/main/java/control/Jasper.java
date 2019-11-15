/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

/**
 *
 * @author estuardo
 */
@WebServlet(name = "Jasper", urlPatterns = {"/Jasper"})
public class Jasper extends HttpServlet {

    @Resource(name = "jdbc/cine")
    DataSource pool;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        try {
            Connection conexion = pool.getConnection();
            HashMap<String, Object> parametros = new HashMap();
           // parametros.put("id_Sala", Integer.parseInt(request.getParameter("sala")));
            InputStream report = null;

            if (request.getParameter("reporte").equals("director")) {
                report = getClass().getResourceAsStream("/recursosJasper/Director.jasper");
            } else if (request.getParameter("reporte").equals("funcion")) {
               // parametros.put("id_Sala", Integer.parseInt(request.getParameter("sala")));
                parametros.put("desde", request.getParameter("desde"));
                parametros.put("hasta", request.getParameter("hasta"));
                report = getClass().getResourceAsStream("/recursosJasper/pelicula.jasper");
            }
            
            JasperPrint impresor = JasperFillManager.fillReport(report, parametros, conexion);

            JRPdfExporter exportador = new JRPdfExporter();
            SimplePdfExporterConfiguration configuracion = new SimplePdfExporterConfiguration();
            configuracion.setMetadataAuthor("fernando");
            exportador.setConfiguration(configuracion);
            exportador.setExporterInput(new SimpleExporterInput(impresor));
            exportador.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            exportador.exportReport();
        } catch (Exception e) {
            e.printStackTrace();
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
