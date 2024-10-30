/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.CrudReporte;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import util.Session;


@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {

    private final CrudReporte reporteDao = new CrudReporte();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ((Session) request.getAttribute("session")).validarSesion(request, response);
        
        String op = request.getParameter("op");

        if (op == null || "".equals(op)) {
            inicio(request, response);
        }
    }

    private void inicio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        request.setAttribute("listaCategorias", categoriaDao.listar());
        HttpSession session = request.getSession();
        int usuarioId = (int) session.getAttribute("empleadoId");
        int perfilId = (int) session.getAttribute("perfilId");

        List<Map<String, Object>> pedidosPorEstado = reporteDao.obtenerPedidosPorEstado(usuarioId, perfilId, null);
        request.setAttribute("pedidosPorEstado", pedidosPorEstado);

        List<Map<String, Object>> pagosUltimos6Meses = reporteDao.obtenerPagosUltimos6Meses(usuarioId, perfilId);
        request.setAttribute("pagosUltimos6Meses", pagosUltimos6Meses);

        List<Map<String, Object>> top10ProductosVendidos = reporteDao.obtenerTop10ProductosVendidos(usuarioId, perfilId);
        request.setAttribute("top10ProductosVendidos", top10ProductosVendidos);

        request.getRequestDispatcher("admin/dashboard.jsp").forward(request, response);

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
