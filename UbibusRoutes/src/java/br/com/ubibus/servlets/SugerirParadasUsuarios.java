/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ubibus.servlets;

import br.com.ubibus.model.facade.LinhaFacade;
import br.com.ubibus.model.facade.ParadaFacade;
import br.com.ubibus.model.facade.PontosInteressesFacade;
import br.com.ubibus.model.pojo.Carro;
import br.com.ubibus.model.pojo.Linha;
import br.com.ubibus.model.pojo.LinhasParadas;
import br.com.ubibus.model.pojo.Parada;
import br.com.ubibus.model.pojo.PontosInteresse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
    private List<Carro> carros;

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

        Integer id_parada = Integer.parseInt(request.getParameter("parada"));
        String piSelecionado = request.getParameter("latlngPI");
        
        Parada parada = paradaFacade.find(id_parada);
        
        String pontos = request.getParameter("pontos");
        //System.out.println(pontos + "=DDDD");
        String latLongs[] = pontos.split("a");
        Double menor = 0.0;
        Double distancia = 0.0;
        List<String> latitudes = new ArrayList<String>();
        int index = 0;
        int indexPiSelecionado = 0;
        String proximo = "";
        for (String string : latLongs) {
            String lats[] = string.split("/");
            //System.out.println("string: " + string);
            System.out.println(lats[0] + "----" + lats[1]);
            latitudes.add(lats[0]);
            if(latitudes.get(index).equals(piSelecionado)){
                indexPiSelecionado = index;
            }
        }
        if(index < latitudes.size()-2){
            proximo = latitudes.get(index+1);
        }
        Double latProximo = Double.parseDouble(proximo.substring(1, proximo.indexOf(",")));
        Double lngProximo = Double.parseDouble(proximo.substring(proximo.indexOf(",") + 1, proximo.indexOf(")") - 1));
        Geometry pontoProximo = new Point(lngProximo, latProximo);

        //System.out.println(id_parada);
        linhas = linhaFacade.findByParadas(id_parada);
        //System.out.println("tamanho linhas:" + linhas.size());

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>SugerirParadasUsuarios</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<table border='1'>");
        out.println("<tr><th colspan='4'>Linhas que passam nessa parada</th></tr>");
        out.println("<tr>");
        out.println("<th>Nome</th>");
        out.println("<th>Preço</th>");
        out.println("<th>Acessibilidade</th>");
        out.println("<th>Troca?</th>");
        out.println("</tr>");
        DecimalFormat df = new DecimalFormat("#,###.00");  
        for (Linha linha : linhas) {
            List<Parada> paradasLinha = paradaFacade.findParadasLinhas(linha.getId());
            int cont = 0;
            Double menorDistancia = 0.0;
            Double distanciaPiParada = 0.0;
            for (Parada parada1 : paradasLinha) {
                //System.out.println(parada1.getId());
                distanciaPiParada = paradaFacade.findDistanciaParadaPI(new PGgeometry(parada1.getLocalizacao()), new PGgeometry(pontoProximo));
                System.out.println("distanciaPiParada: "+distanciaPiParada);
                if(cont == 0){
                    System.out.println("CONT 0");
                    menorDistancia = distanciaPiParada;
                }else if(distanciaPiParada <= menorDistancia){
                    menorDistancia = distanciaPiParada;
                }
                cont++;
            }
            System.out.println("MENOR distanciaPiParada: "+menorDistancia);

            out.println("<tr>");
            //out.println("<td onMouseOver=desenhaLinha(" + linha.getId() + ")>" + linha.getNome() + "</td>");
            out.println("<td>" + linha.getNome() + "</td>");
            out.println("<td>" + "R$ " + linha.getPreco() + "</td>");
            carros = linhaFacade.findCarrosByLinha(linha.getId());
            out.println("<td>");
            for (Carro carro : carros) {
                if(carro.isAcessibilidade()){
                    out.println(carro.getPlaca());
                }
            }
            out.println("</td>");
            if(menorDistancia <=1000){
                out.println("<td>Não</td>");
            }else{
                out.println("<td>Sim</td>");
            }
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
