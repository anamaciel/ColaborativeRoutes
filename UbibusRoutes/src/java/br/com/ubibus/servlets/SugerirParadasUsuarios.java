/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ubibus.servlets;

import br.com.ubibus.model.facade.LinhaFacade;
import br.com.ubibus.model.facade.ParadaFacade;
import br.com.ubibus.model.facade.PontosInteressesFacade;
import br.com.ubibus.model.pojo.Linha;
import br.com.ubibus.model.pojo.Parada;
import br.com.ubibus.model.pojo.PontosInteresse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AnaaMaciel
 */
@WebServlet(name = "SugerirParadasUsuarios", urlPatterns = {"/SugerirParadasUsuarios"})
public class SugerirParadasUsuarios extends HttpServlet {
    
    @EJB
    private PontosInteressesFacade pontosInteressesFacade;
    @EJB
    private ParadaFacade paradaFacade;
    @EJB
    private LinhaFacade linhaFacade;
    private List<PontosInteresse> listaPontosInteresse;
    private List<Parada> paradasProximas;
    private List<Linha> linhas;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Integer id_parada= Integer.parseInt(request.getParameter("parada"));
        System.out.println(id_parada);
        linhas = linhaFacade.findByParadas(id_parada);
        System.out.println("tamanho linhas:" + linhas.size());
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>SugerirParadasUsuarios</title>");
        out.println("</head>");
        out.println("<body>");
        
        //out.println("<h1>Servlet SugerirParadasUsuarios at " + request.getContextPath() + "</h1>");
        //out.println("<h1>" + linhas + "</h1>");
        out.println("<table border='1'>");
        out.println("<tr><th colspan='2'>Linhas que passam nessa parada</th></tr>");
        out.println("<tr>");
        out.println("<th>Nome</th>");
        out.println("<th>Empresa</th>");
        out.println("</tr>");
        for (Linha linha : linhas) {
            out.println("<tr onMouseOver=desenhaLinha("+linha.getId() +")>");
            out.println("<td>" + linha.getNome()+ "</td>");
            out.println("<td>" + linha.getEmpresa()+ "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        System.out.println("Linhas" + linhas.toString());
        out.println("</body>");
        out.println("</html>");

        
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
