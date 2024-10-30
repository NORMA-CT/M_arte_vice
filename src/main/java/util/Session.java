/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Session {

    public final static int PERFIL_ADMIN = 1;
    public final static int PERFIL_EMPLEADO = 2;
    public final static int PERFIL_CLIENTE = 3;

    private boolean logged = false;

    private boolean cliente = false;
    private boolean admin = false;
    private boolean empleado = false;

    private int perfilId = 0;
    private int usuarioId = 0;
    private int empleadoId = 0;
    private int clienteId = 0;

    public Session(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session.getAttribute("empleadoId") != null || session.getAttribute("clienteId") != null) {

            if (session.getAttribute("empleadoId") != null) {
                usuarioId = empleadoId = (int) session.getAttribute("empleadoId");
            }

            if (session.getAttribute("clienteId") != null) {
                usuarioId = clienteId = (int) session.getAttribute("clienteId");
            }

            perfilId = (int) session.getAttribute("perfilId");

            cliente = perfilId == PERFIL_CLIENTE;
            admin = perfilId == PERFIL_ADMIN;
            empleado = perfilId == PERFIL_EMPLEADO;

            logged = true;
        } else {
            logged = false;
        }

    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean isLogged) {
        this.logged = isLogged;
    }

    public int getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(int perfilId) {
        this.perfilId = perfilId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public boolean isCliente() {
        return cliente;
    }

    public void setCliente(boolean cliente) {
        this.cliente = cliente;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isEmpleado() {
        return empleado;
    }

    public void setEmpleado(boolean empleado) {
        this.empleado = empleado;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public void validarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!this.logged) {
            response.sendRedirect(request.getServletContext().getContextPath() + "/inicio");
        }
    }

}
