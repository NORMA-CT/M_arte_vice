/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.publico;

import bean.CarritoItem;
import bean.Pedido;
import bean.Producto;
import dao.CrudPedido;
import dao.CrudProducto;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import util.Session;

@WebServlet("/CarritoController")
public class CarritoController extends HttpServlet {

    private final CrudProducto productoDao = new CrudProducto();
    private final CrudPedido pedidoDao = new CrudPedido();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");

        if ("agregar".equals(op)) {
            agregarProducto(request, response);
        } else if ("eliminar".equals(op)) {
            eliminarProducto(request, response);
        } else if ("actualizar".equals(op)) {
            actualizarCantidad(request, response);
        } else if ("ver".equals(op)) {
            verCarrito(request, response);
        } else if ("confirmar".equals(op)) {
            confirmarPedido(request, response);
        }
    }

    private void agregarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productoId = Integer.parseInt(request.getParameter("productoId"));

        Producto producto = productoDao.buscar(productoId);

        String nombre = producto.getNombre();

        double precioUnitario = producto.getPrecio();

        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        HttpSession session = request.getSession();

        List<CarritoItem> carrito = (List<CarritoItem>) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new ArrayList<>();
        }

        boolean encontrado = false;
        for (CarritoItem item : carrito) {
            if (item.getProductoId() == productoId) {
                item.setCantidad(item.getCantidad() + cantidad);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            carrito.add(new CarritoItem(productoId, nombre, precioUnitario, cantidad));
        }

        session.setAttribute("carrito", carrito);
        verCarrito(request, response);
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productoId = Integer.parseInt(request.getParameter("productoId"));
        HttpSession session = request.getSession();
        List<CarritoItem> carrito = (List<CarritoItem>) session.getAttribute("carrito");

        if (carrito != null) {
            carrito.removeIf(item -> item.getProductoId() == productoId);
        }

        session.setAttribute("carrito", carrito);
        verCarrito(request, response);
    }

    private void actualizarCantidad(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productoId = Integer.parseInt(request.getParameter("productoId"));
        int nuevaCantidad = Integer.parseInt(request.getParameter("cantidad"));
        HttpSession session = request.getSession();
        List<CarritoItem> carrito = (List<CarritoItem>) session.getAttribute("carrito");

        if (carrito != null) {
            for (CarritoItem item : carrito) {
                if (item.getProductoId() == productoId) {
                    item.setCantidad(nuevaCantidad);
                    break;
                }
            }
        }

        session.setAttribute("carrito", carrito);
        verCarrito(request, response);
    }

    private void verCarrito(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("carrito.jsp").forward(request, response);
    }

    private void confirmarPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!new Session(request).isLogged()) {
            response.sendRedirect("LoginController?op=login");  // Redirigimos al listado de pedidos del empleado
        } else {

            HttpSession session = request.getSession();

            // Obtener los datos del cliente
            int clienteId = (int) session.getAttribute("clienteId");  // Supongamos que tenemos el ID del cliente en sesión
            List<CarritoItem> carrito = (List<CarritoItem>) session.getAttribute("carrito");  // El carrito está en sesión

            if (carrito != null && !carrito.isEmpty()) {
                // Crear objeto Pedido
                Pedido pedido = new Pedido();
                pedido.setClienteId(clienteId);
                pedido.setFechaProgramada(LocalDateTime.now());  // Fecha de creación del pedido

                // Llamar al DAO para registrar el pedido y su detalle
                pedidoDao.registrarPedido(pedido, carrito);
                int pedidoId = pedido.getId();

                if (pedidoId > 0) {
                    // Limpiar el carrito después de confirmar el pedido
                    session.setAttribute("carrito", null);

                    // Redirigir al usuario a la página de confirmación de pedido
//                    response.sendRedirect("confirmacionPedido.jsp?pedidoId=" + pedidoId);
                    response.sendRedirect("TiendaPedidoController?op=detalles&pedidoId=" + pedidoId);
                } else {
                    request.setAttribute("error", "Error al confirmar el pedido.");
                    request.getRequestDispatcher("carrito.jsp").forward(request, response);
                }
            } else {
                // Si el carrito está vacío, mostrar un mensaje de error
                request.setAttribute("error", "No hay productos en el carrito.");
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
            }
        }

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
