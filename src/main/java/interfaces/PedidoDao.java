/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import bean.CarritoItem;
import bean.Pedido;
import java.util.List;


public interface PedidoDao {

    int registrarPedido(Pedido pedido, List<CarritoItem> carrito);
    
    List<Pedido> listarPorCliente(int clienteId);

    List<Pedido> listarPorEmpleado(int empleadoId, int perfilId);

    List<Pedido> listarPedidosPendientes();  // Método para listar pedidos pendientes

    Pedido buscarPorId(int pedidoId);

    void actualizarEstadoPedido(int pedidoId, int nuevoEstado);

    void asignarEmpleado(int pedidoId, int empleadoId);
}
