/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.publico;


import controller.admin.*;
import dao.CrudCategoria;
import bean.Categoria;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CategoriaController", urlPatterns = {"/CategoriaController/*"})
public class CategoriaController extends HttpServlet {

    private final CrudCategoria categoriaDao = new CrudCategoria();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");

        if ("listar".equals(op)) {
            listarCategorias(request, response);
        } else if ("agregar".equals(op)) {
            agregarCategoria(request, response);
        } else if ("modificar".equals(op)) {
            modificarCategoria(request, response);
        } else if ("eliminar".equals(op)) {
            eliminarCategoria(request, response);
        }
    }

    private void listarCategorias(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("listaCategorias", categoriaDao.listar());
        request.getRequestDispatcher("admin/categorias.jsp").forward(request, response);
    }

    private void agregarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Categoria categoria = new Categoria();
        categoria.setNombre(request.getParameter("nombre"));
        categoriaDao.agregar(categoria);
        listarCategorias(request, response);
    }

    private void modificarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Categoria categoria = new Categoria();
        categoria.setId(Integer.parseInt(request.getParameter("id")));
        categoria.setNombre(request.getParameter("nombre"));
        categoriaDao.modificar(categoria);
        listarCategorias(request, response);
    }

    private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        categoriaDao.eliminar(id);
        listarCategorias(request, response);
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
