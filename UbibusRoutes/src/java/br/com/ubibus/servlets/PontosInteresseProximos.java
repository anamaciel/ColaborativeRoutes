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
import java.text.DecimalFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.postgis.Geometry;
import org.postgis.PGgeometry;
import org.postgis.Point;

/**
 *
 * @author AnaaMaciel
 */
@WebServlet(name = "PontosInteresseProximos", urlPatterns = {"/PontosInteresseProximos"})
public class PontosInteresseProximos extends HttpServlet {

    @EJB
    private PontosInteressesFacade pontosInteressesFacade;
    @EJB
    private ParadaFacade paradaFacade;
    private List<PontosInteresse> listaPontosInteresse;
    private List<Parada> paradasProximas;

    public double distancia(double latitudeInicial, double longitudeInicial, double latitudeFinal, double longitudeFinal) {
        double d = 0;
        int raioTerra = 6371;
        double PI = Math.PI;
        int valorMetade = 90;
        int valorInteiro = 180;

        double v1 = Math.cos(PI * (valorMetade - latitudeFinal) / 180);
        double v2 = Math.cos((valorMetade - latitudeInicial) * PI / 180);
        double v3 = Math.sin((valorMetade - latitudeFinal) * PI / 180);
        double v4 = Math.sin((valorMetade - latitudeInicial) * PI / 180);
        double v5 = Math.cos((longitudeInicial - longitudeFinal) * PI / 180);

        double result = raioTerra * Math.acos((v1 * v2) + (v3 * v4 * v5));

        return d = result;
    }

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
//            out.println("<title>Servlet PontosInteresseProximos</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet PontosInteresseProximos at " + request.getContextPath() + "</h1>");
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

        PrintWriter out = response.getWriter();
        String latlng = request.getParameter("latlng");
//        Double lat = Double.parseDouble(latlng.substring(4, 18));
        String lat = latlng.substring(1, latlng.indexOf(","));
        String lng = latlng.substring(latlng.indexOf(",") + 1, latlng.indexOf(")") - 1);
//        Double lng = Double.parseDouble(latlng.substring(28, 18));
//        LatLng latl = new LatLng(lat, lng);
        double distancia = 0.001; //aproximadamente 111m
        double distanciaPontos;
        Geometry teste = new Point(Double.parseDouble(lng), Double.parseDouble(lat));
        paradasProximas = paradaFacade.findParadasProximas(new PGgeometry(teste), distancia);
        while (paradasProximas.size() <= 0) {
            distancia += 0.001;
            paradasProximas = paradaFacade.findParadasProximas(new PGgeometry(teste), distancia);
        }
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet PontosInteresseProximos</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class = \"MarkerPopUp\" style=\"width: 300px; height: 200px; overflow-x:hidden; margin-left: 80px;\">");
        out.println("<table cellpadding='2' id=\"hiddenEl\" border='1' style=\"overflow-x:hidden;\">");
        out.println("<tr><th colspan='3'>Paradas Proximas</th></tr>");
        out.println("<tr>");
        out.println("<th>Nome</th>");
        out.println("<th>Descrição</th>");
        out.println("<th>Distância ao PI</th>");
        out.println("</tr>");
        DecimalFormat df = new DecimalFormat("#,###.00");  
        for (Parada parada : paradasProximas) {
            out.println("<tr onMouseOver=teste(" + parada.getId() + ")>");
            out.println("<input type=\"hidden\" id=\"localizacao" + parada.getId() + "\" value=\"" + parada.getLocalizacao() + "\" />");
            out.println("<input type=\"hidden\" id=\"titulo" + parada.getId() + "\" value=\"" + parada.getDescricao() + "\" />");
            out.println("<td><a href=\"#\" onclick=\"hideElement(" + parada.getId() + ")\" class=\"big-link\" data-reveal-id=\"myModal\">" + parada.getNome() + "</a></td>");
            out.println("<td>" + parada.getDescricao() + "</td>"); 
            out.println("<td>" +  df.format(paradaFacade.findDistanciaParadaPI(new PGgeometry(teste), new PGgeometry(parada.getLocalizacao()))) + "m </td>");
            out.println("</tr>");
            //System.out.println("distancia: " + paradaFacade.findDistanciaParadaPI(new PGgeometry(teste), new PGgeometry(parada.getLocalizacao())));
        }


        out.println("</table>");
        out.println("</div>");
        //div para popup
        out.println("<div id=\"myModal\" class=\"reveal-modal\">");
        //out.println("<h1>Reveal Modal Goodness</h1>");
        out.println("<a class=\"close-reveal-modal\">&#215;</a>");
        out.println("</div>");


        out.println("</body>");
        out.println("</html>");
        //out.println(request.getParameter("latlng"));
        //response.getWriter().write(request.getParameter("latlng"));
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
        //response.setContentType("text/html");
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
