/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.tienda;

import controller.admin.*;
import dao.CrudPedido;
import bean.Pedido;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/TiendaPedidoController")
public class PedidoController extends HttpServlet {

    private final CrudPedido pedidoDao = new CrudPedido();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");

        if ("listar".equals(op)) {
            listarPedidosCliente(request, response);
        } else if ("detalles".equals(op)) {
            verDetallesPedido(request, response);
        }
    }

    private void listarPedidosCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int clienteId = (int) session.getAttribute("clienteId");

        List<Pedido> listaPedidos = pedidoDao.listarPorCliente(clienteId);
        request.setAttribute("listaPedidos", listaPedidos);
        request.getRequestDispatcher("cuenta/pedidos.jsp").forward(request, response);
    }

    private void verDetallesPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));
        Pedido pedido = pedidoDao.buscarPorId(pedidoId);

        request.setAttribute("pedido", pedido);
        request.getRequestDispatcher("cuenta/pedido.jsp").forward(request, response);
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
