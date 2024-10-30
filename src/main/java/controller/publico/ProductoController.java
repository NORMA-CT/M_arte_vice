/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.publico;


import controller.admin.*;
import dao.CrudProducto;
import bean.Producto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/ProductoController", urlPatterns = {"/ProductoController/*"})
public class ProductoController extends HttpServlet {

    private final CrudProducto productoDao = new CrudProducto();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("op");

        if ("listar".equals(action)) {
            listar(request, response);
        } else if ("detalle".equals(action)) {
            detalle(request, response);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("categoria") != null && !request.getParameter("categoria").equals("")) {
            int catId = Integer.parseInt(request.getParameter("categoria"));

            request.setAttribute("listaProductos", productoDao.listarPorCategoria(catId));
        } else {
            request.setAttribute("listaProductos", productoDao.listar());
        }

        request.getRequestDispatcher("productos.jsp").forward(request, response);
    }

    private void detalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Producto producto = productoDao.buscar(id);

        request.setAttribute("producto", producto);

        request.getRequestDispatcher("producto.jsp").forward(request, response);
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
