/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin;


import dao.CrudUsuario;
import bean.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import util.Session;

@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {

    private final CrudUsuario usuarioDao = new CrudUsuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ((Session) request.getAttribute("session")).validarSesion(request, response);

        String op = request.getParameter("op");

        if ("listar".equals(op)) {
            listarUsuarios(request, response);
        } else if ("agregar".equals(op)) {
            agregarUsuario(request, response);
        } else if ("modificar".equals(op)) {
            modificarUsuario(request, response);
        } else if ("eliminar".equals(op)) {
            eliminarUsuario(request, response);
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("listaUsuarios", usuarioDao.listar());
        request.getRequestDispatcher("admin/usuarios.jsp").forward(request, response);
    }

    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = new Usuario();
        usuario.setPersonaId(Integer.parseInt(request.getParameter("personaId")));
        usuario.setPassword(request.getParameter("password"));
        usuario.setUsuario(request.getParameter("usuario"));
        usuario.setPerfilId(Integer.parseInt(request.getParameter("perfilId")));
        usuarioDao.agregar(usuario);
        listarUsuarios(request, response);
    }

    private void modificarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = new Usuario();
        usuario.setId(Integer.parseInt(request.getParameter("id")));
        usuario.setPassword(request.getParameter("password"));
        usuario.setUsuario(request.getParameter("usuario"));
        usuario.setPerfilId(Integer.parseInt(request.getParameter("perfilId")));
        usuarioDao.modificar(usuario);
        listarUsuarios(request, response);
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        usuarioDao.eliminar(id);
        listarUsuarios(request, response);
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
