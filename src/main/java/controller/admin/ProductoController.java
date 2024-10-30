/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;


import dao.CrudProducto;
import bean.Producto;
import dao.CrudCategoria;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.json.JsonObject;
import org.json.JSONObject;
import util.Session;

@WebServlet("/AdminProductoController")
public class ProductoController extends HttpServlet {

    private final CrudProducto productoDao = new CrudProducto();
    private final CrudCategoria categoriaDao = new CrudCategoria();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ((Session) request.getAttribute("session")).validarSesion(request, response);
        
        String action = request.getParameter("op");

        if ("listar".equals(action)) {
            listarProductos(request, response);
        } else if ("agregar".equals(action)) {
            agregarProducto(request, response);
        } else if ("modificar".equals(action)) {
            modificarProducto(request, response);
        } else if ("eliminar".equals(action)) {
            eliminarProducto(request, response);
        } else if ("buscar".equals(action)) {
            buscar(request, response);
        }
    }

    private void listarProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("listaProductos", productoDao.listar());
        request.setAttribute("listaCategorias", categoriaDao.listar());
        request.getRequestDispatcher("admin/productos.jsp").forward(request, response);
    }

    private void agregarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Producto producto = new Producto();
        producto.setNombre(request.getParameter("nombre"));
        producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
        producto.setCatId(Integer.parseInt(request.getParameter("catId")));
        producto.setHoras(Integer.parseInt(request.getParameter("horas")));
        producto.setComplejidad(Integer.parseInt(request.getParameter("complejidad")));

        productoDao.agregar(producto);
//        listarProductos(request, response);
        response.sendRedirect("AdminProductoController?op=listar");  // Redirigimos al listado de pedidos del empleado
    }

    private void modificarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Producto producto = new Producto();
        producto.setId(Integer.parseInt(request.getParameter("id")));
        producto.setNombre(request.getParameter("nombre"));
        producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
        producto.setCatId(Integer.parseInt(request.getParameter("catId")));
        producto.setHoras(Integer.parseInt(request.getParameter("horas")));
        producto.setComplejidad(Integer.parseInt(request.getParameter("complejidad")));

        productoDao.modificar(producto);
//        listarProductos(request, response);
        response.sendRedirect("AdminProductoController?op=listar");  // Redirigimos al listado de pedidos del empleado
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productoDao.eliminar(id);
//        listarProductos(request, response);
        response.sendRedirect("AdminProductoController?op=listar");  // Redirigimos al listado de pedidos del empleado
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Producto producto = productoDao.buscar(Integer.parseInt(request.getParameter("id")));

        JSONObject jsonObject = new JSONObject(producto);

        response.addHeader("content-type", "application/json");

        PrintWriter pw = response.getWriter();

        pw.print(jsonObject);

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
