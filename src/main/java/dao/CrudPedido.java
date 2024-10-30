/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import bean.CarritoItem;
import interfaces.PedidoDao;
import bean.Pedido;
import util.MySQLConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudPedido implements PedidoDao {

    @Override
    public int registrarPedido(Pedido pedido, List<CarritoItem> carrito) {
        Connection cn = MySQLConexion.getConexion();
        int pedidoId = 0;
        try {
            // 1. Insertar el pedido
            String sqlPedido = "{call spCrearPedidoConDetalle(?, ?, ?)}";
            CallableStatement stPedido = cn.prepareCall(sqlPedido);
            stPedido.setInt(1, pedido.getClienteId());
            stPedido.setTimestamp(2, Timestamp.valueOf(pedido.getFechaProgramada()));

            // Convertimos los items del carrito en JSON para pasarlo al procedimiento
            String detallesJson = generarJsonCarrito(carrito);
            stPedido.setString(3, detallesJson);
            stPedido.executeUpdate();

            // Obtener el ID del pedido recién creado
//            ResultSet rs = stPedido.getGeneratedKeys();
            ResultSet rs = stPedido.executeQuery();

            if (rs.next()) {
                pedido.setId(rs.getInt(1));
            }
//            if (rs.next()) {
//                pedidoId = rs.getInt(1);
//            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pedidoId;
    }

    // Método auxiliar para convertir el carrito a formato JSON
    private String generarJsonCarrito(List<CarritoItem> carrito) {
        StringBuilder jsonBuilder = new StringBuilder("[");
        for (int i = 0; i < carrito.size(); i++) {
            CarritoItem item = carrito.get(i);
            jsonBuilder.append("{")
                    .append("\"producto_id\":").append(item.getProductoId()).append(",")
                    .append("\"cantidad\":").append(item.getCantidad())
                    .append("}");
            if (i < carrito.size() - 1) {
                jsonBuilder.append(",");  // Separar los items con comas
            }
        }
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }

    @Override
    public List<Pedido> listarPorCliente(int clienteId) {
        List<Pedido> lista = new ArrayList<>();
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "SELECT id, cliente_id, precio_total, horas_totales, complejidad, proceso, fecha_programada FROM pedido WHERE cliente_id = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, clienteId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId(rs.getInt(1));
                p.setClienteId(rs.getInt(2));
                p.setPrecioTotal(rs.getDouble(3));
                p.setHorasTotales(rs.getInt(4));
                p.setComplejidad(rs.getInt(5));
                p.setProceso(rs.getShort(6));
                p.setFechaProgramada(rs.getTimestamp(7).toLocalDateTime());
                lista.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Pedido> listarPorEmpleado(int empleadoId, int perfilId) {
        List<Pedido> lista = new ArrayList<>();
        Connection cn = MySQLConexion.getConexion();
        try {

            String sql = "{call spListarPedidosPorPerfil(?, ?)}";
            CallableStatement st = cn.prepareCall(sql);

            /*String sql = "SELECT p.id, p.cliente_id, p.precio_total, p.horas_totales, p.complejidad, p.proceso, p.fecha_programada "
                    + "FROM pedido p JOIN asignacion a ON p.id = a.pedido_id WHERE a.trabajador_id = ?";
            PreparedStatement st = cn.prepareStatement(sql);*/
            st.setInt(1, empleadoId);
            st.setInt(2, perfilId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId(rs.getInt(1));
                p.setClienteId(rs.getInt(2));
                p.setPrecioTotal(rs.getDouble(3));
                p.setHorasTotales(rs.getInt(4));
                p.setComplejidad(rs.getInt(5));
                p.setProceso(rs.getShort(6));
                p.setFechaProgramada(rs.getTimestamp(7).toLocalDateTime());
                lista.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Pedido> listarPedidosPendientes() {
        List<Pedido> lista = new ArrayList<>();
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "SELECT id, cliente_id, precio_total, horas_totales, complejidad, proceso, fecha_programada "
                    + "FROM pedido WHERE proceso = 10";  // 10 es el estado PENDIENTE
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId(rs.getInt(1));
                p.setClienteId(rs.getInt(2));
                p.setPrecioTotal(rs.getDouble(3));
                p.setHorasTotales(rs.getInt(4));
                p.setComplejidad(rs.getInt(5));
                p.setProceso(rs.getShort(6));
                p.setFechaProgramada(rs.getTimestamp(7).toLocalDateTime());
                lista.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public Pedido buscarPorId(int pedidoId) {
        Pedido p = null;
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "SELECT id, cliente_id, precio_total, horas_totales, complejidad, proceso, fecha_programada FROM pedido WHERE id = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, pedidoId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                p = new Pedido();
                p.setId(rs.getInt(1));
                p.setClienteId(rs.getInt(2));
                p.setPrecioTotal(rs.getDouble(3));
                p.setHorasTotales(rs.getInt(4));
                p.setComplejidad(rs.getInt(5));
                p.setProceso(rs.getShort(6));
                p.setFechaProgramada(rs.getTimestamp(7).toLocalDateTime());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return p;
    }

    @Override
    public void actualizarEstadoPedido(int pedidoId, int nuevoEstado) {
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "{call spActualizarEstadoPedido(?, ?)}";
            CallableStatement st = cn.prepareCall(sql);
            st.setInt(1, pedidoId);
            st.setInt(2, nuevoEstado);  // 30 es el estado ATENDIDO
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void asignarEmpleado(int pedidoId, int empleadoId) {

        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "{call spAsignarPedidoArtesano(?, ?, NOW())}";
            CallableStatement st = cn.prepareCall(sql);
            st.setInt(1, pedidoId);
            st.setInt(2, empleadoId);
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
