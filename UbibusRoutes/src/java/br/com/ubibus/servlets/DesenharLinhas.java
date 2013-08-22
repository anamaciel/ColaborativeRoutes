/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ubibus.servlets;

import br.com.ubibus.model.facade.ParadaFacade;
import br.com.ubibus.model.facade.PontosInteressesFacade;
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
@WebServlet(name = "DesenharLinhas", urlPatterns = {"/DesenharLinhas"})
public class DesenharLinhas extends HttpServlet {

    @EJB
    private PontosInteressesFacade pontosInteressesFacade;
    @EJB
    private ParadaFacade paradaFacade;
    private List<PontosInteresse> listaPontosInteresse;
    private List<Parada> paradasProximas;

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet DesenharLinhas</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet DesenharLinhas at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        } finally {
            out.close();
        }
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
        processRequest(request, response);

        PrintWriter out = response.getWriter();
        Integer id_linha = Integer.parseInt(request.getParameter("id_linha"));
        //System.out.println("ID LINHA: " + id_linha);
        List<Parada> paradasLinha = paradaFacade.findParadasLinhas(id_linha);
        //System.out.println("TESTE PARADAS LINHAS: " + paradasLinha.toString());
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet DesenharLinhas</title>");
        //out.println("<script type=\"text/javascript\" src=\"resources/js/teste.js\"></script> ");
        out.println("</head>");
        out.println("<body>");
        out.println("<form id=\"dados\">");
        out.println("<input type=\"hidden\" id=\"startLinha\" value=\"" + paradasLinha.get(0).getLocalizacao() + "\" />");
        out.println("<select multiple=\"multiple\" id=\"waypointsLinha\" >");
        for (int i = 1; i < 10; i++) {
            out.println("<option value=\"" + paradasLinha.get(i).getLocalizacao() + "\" selected>" + paradasLinha.get(i).getNome() + "</option>");
        }
        out.println("</select>");
        out.println("<input type=\"hidden\" id=\"endLinha\" value=\"" + paradasLinha.get(paradasLinha.size() - 1).getLocalizacao() + "\" />");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
//        out.println("<script type=\"text/javascript\">");
//        out.println("desenhaRotaLinha();");
//        out.println("alert('oi')");
//        out.println("</script>");
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
