/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin;


import dao.CrudPedido;
import bean.Pedido;
import dao.CrudPersona;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import util.Session;

@WebServlet("/AdminPedidoController")
public class PedidoController extends HttpServlet {

    private final CrudPedido pedidoDao = new CrudPedido();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ((Session) request.getAttribute("session")).validarSesion(request, response);

        String op = request.getParameter("op");

        if ("listar".equals(op)) {
            listar(request, response);
        } else if ("detalles".equals(op)) {
            verDetallesPedido(request, response);
        } else if ("marcarAtendido".equals(op)) {
            marcarComoAtendido(request, response);
        } else if ("listarPendientes".equals(op)) {
            listarPendientes(request, response);
        } else if ("asignarEmpleado".equals(op)) {
            asignarEmpleado(request, response);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int empleadoId = (int) session.getAttribute("empleadoId");
        int perfilId = (int) session.getAttribute("perfilId");

        List<Pedido> listaPedidos = pedidoDao.listarPorEmpleado(empleadoId, perfilId);
        request.setAttribute("listaPedidos", listaPedidos);
        request.getRequestDispatcher("admin/pedidos.jsp").forward(request, response);
    }

    private void verDetallesPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));
        Pedido pedido = pedidoDao.buscarPorId(pedidoId);

        request.setAttribute("pedido", pedido);
        request.getRequestDispatcher("detallesPedido.jsp").forward(request, response);
    }

    private void marcarComoAtendido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));

        pedidoDao.actualizarEstadoPedido(pedidoId, 30);  // Cambiamos el estado a ATENDIDO (30)

        response.sendRedirect("AdminPedidoController?op=listar");  // Redirigimos al listado de pedidos del empleado
    }

    private void listarPendientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CrudPersona crudPersona = new CrudPersona();

        request.setAttribute("listaPersonal", crudPersona.listarEmpleadosConHorasPendientes());

        request.setAttribute("listaPedidos", pedidoDao.listarPedidosPendientes());

        request.getRequestDispatcher("admin/pedidosPendientes.jsp").forward(request, response);
    }

    private void asignarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));
        int empleadoId = Integer.parseInt(request.getParameter("empleadoId"));

        pedidoDao.asignarEmpleado(pedidoId, empleadoId);

        //pedidoDao.actualizarEstadoPedido(pedidoId, 20);  // Cambiamos el estado a ASIGNADO (20)
        response.sendRedirect("AdminPedidoController?op=listar");
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
