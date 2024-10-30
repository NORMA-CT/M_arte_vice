/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin;

import dao.CrudPersona;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import util.Session;


@WebServlet("/PersonaController")
public class PersonaController extends HttpServlet {

    private final CrudPersona empleadoDao = new CrudPersona();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ((Session) request.getAttribute("session")).validarSesion(request, response);
        
        String op = request.getParameter("op");

        if ("listarEmpleados".equals(op)) {
            listarEmpleados(request, response);
        }
    }

    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Map<String, Object>> artesanos = empleadoDao.listarEmpleadosConHorasPendientes();
        request.setAttribute("listaArtesanos", artesanos);
        request.getRequestDispatcher("admin/empleados.jsp").forward(request, response);
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
