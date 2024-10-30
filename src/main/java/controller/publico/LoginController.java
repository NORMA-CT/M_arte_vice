/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.publico;

import dao.CrudPersona;
import dao.CrudUsuario;
import bean.Persona;
import bean.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import util.Session;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

    private final CrudUsuario usuarioDao = new CrudUsuario();
    private final CrudPersona personaDao = new CrudPersona();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");

        if ("login".equals(op)) {
            login(request, response);
        } else if ("iniciarSesion".equals(op)) {
            iniciarSesion(request, response);
        } else if ("registrar".equals(op)) {
            registrarNuevaCuenta(request, response);
        } else if ("verMiCuenta".equals(op)) {
            verMiCuenta(request, response);
        } else if ("modificarCuenta".equals(op)) {
            modificarCuenta(request, response);
        } else if ("logout".equals(op)) {
            logout(request, response);
        } else if ("register".equals(op)) {
            register(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        Usuario user = usuarioDao.iniciarSesion(usuario, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", user.getUsuario());
            session.setAttribute("perfilId", user.getPerfilId());

            if (user.getPerfilId() == Session.PERFIL_CLIENTE) {  // Cliente

                session.setAttribute("clienteId", user.getPersonaId());

                response.sendRedirect("TiendaPedidoController?op=listar");
            } else if (user.getPerfilId() == Session.PERFIL_EMPLEADO) {  // Cliente

                session.setAttribute("empleadoId", user.getPersonaId());

                response.sendRedirect("AdminPedidoController?op=listar");
            } else if (user.getPerfilId() == Session.PERFIL_ADMIN) {  // Empleado o Administrador

                session.setAttribute("empleadoId", user.getPersonaId());

                response.sendRedirect("AdminPedidoController?op=listarPendientes");
            }
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void registrarNuevaCuenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Persona persona = new Persona();
        persona.setTipoDoc(request.getParameter("tipoDoc"));
        persona.setNumDoc(request.getParameter("numDoc"));
        persona.setNombre(request.getParameter("nombre"));
        persona.setApellido(request.getParameter("apellido"));
        personaDao.agregar(persona);

        Usuario usuario = new Usuario();
        usuario.setPersonaId(persona.getId());  // Usamos el ID recién generado de la persona
        usuario.setPassword(request.getParameter("password"));
        usuario.setUsuario(request.getParameter("usuario"));
        usuario.setPerfilId(3);  // Perfil de cliente
        usuarioDao.agregar(usuario);

        response.sendRedirect("login.jsp");  // Redirigimos al login para iniciar sesión
    }

    private void verMiCuenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int personaId = (int) session.getAttribute("clienteId");
        Persona persona = personaDao.buscarPorId(personaId);

        request.setAttribute("persona", persona);
        request.getRequestDispatcher("cuenta/micuenta.jsp").forward(request, response);
    }

    private void modificarCuenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Persona persona = new Persona();

        int personaId = (int) session.getAttribute("clienteId");

        persona.setId(personaId);
        persona.setTipoDoc(request.getParameter("tipoDoc"));
        persona.setNumDoc(request.getParameter("numDoc"));
        persona.setNombre(request.getParameter("nombre"));
        persona.setApellido(request.getParameter("apellido"));

        personaDao.modificar(persona);

        response.sendRedirect("LoginController?op=verMiCuenta");
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Invalidate session
        HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect(request.getServletContext().getContextPath() + "/");
//        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("registro.jsp").forward(request, response);
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
