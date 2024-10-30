/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.CrudPago;
import bean.Pago;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/PagoController")
public class PagoController extends HttpServlet {

    private final CrudPago pagoDao = new CrudPago();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");

        if ("listarPorPedido".equals(op)) {
            listarPagosPorPedido(request, response);
        } else if ("listarPorUsuario".equals(op)) {
            listarPagosPorUsuario(request, response);
        } else if ("registrar".equals(op)) {
            registrarPago(request, response);
        }
    }

    private void listarPagosPorPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));
        List<Pago> listaPagos = pagoDao.listarPagosPorPedido(pedidoId);
        request.setAttribute("listaPagos", listaPagos);
        request.getRequestDispatcher("pagosPorPedido.jsp").forward(request, response);
    }

    private void listarPagosPorUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int clienteId = (int) session.getAttribute("clienteId");
        List<Pago> listaPagos = pagoDao.listarPagosPorUsuario(clienteId);
        request.setAttribute("listaPagos", listaPagos);
        request.getRequestDispatcher("pagosPorUsuario.jsp").forward(request, response);
    }

    private void registrarPago(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));
        double monto = Double.parseDouble(request.getParameter("monto"));
        int tipoPago = Integer.parseInt(request.getParameter("tipoPago"));

        pagoDao.registrarPago(pedidoId, tipoPago, monto);
        response.sendRedirect("PagoController?op=listarPorPedido&pedidoId=" + pedidoId);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
