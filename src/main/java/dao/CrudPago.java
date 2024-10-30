/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import bean.Pago;
import util.MySQLConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudPago {

    public List<Pago> listarPagosPorPedido(int pedidoId) {
        List<Pago> lista = new ArrayList<>();
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "SELECT id, pedido_id, tipo_pago, monto, fecha_pago FROM pedido_pago WHERE pedido_id = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, pedidoId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Pago p = new Pago();
                p.setId(rs.getInt(1));
                p.setPedidoId(rs.getInt(2));
                p.setTipoPago(rs.getInt(3));
                p.setMonto(rs.getDouble(4));
                p.setFechaPago(rs.getTimestamp(5).toLocalDateTime());
                lista.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public List<Pago> listarPagosPorUsuario(int usuarioId) {
        List<Pago> lista = new ArrayList<>();
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "SELECT pp.id, pp.pedido_id, pp.tipo_pago, pp.monto, pp.fecha_pago "
                    + "FROM pedido_pago pp JOIN pedido p ON pp.pedido_id = p.id "
                    + "WHERE p.cliente_id = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, usuarioId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Pago p = new Pago();
                p.setId(rs.getInt(1));
                p.setPedidoId(rs.getInt(2));
                p.setTipoPago(rs.getInt(3));
                p.setMonto(rs.getDouble(4));
                p.setFechaPago(rs.getTimestamp(5).toLocalDateTime());
                lista.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public void registrarPago(int pedidoId, int tipoPago, double monto) {
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "{call spRegistrarPagoPedido(?, ?, ?, NOW())}";
            CallableStatement st = cn.prepareCall(sql);
            st.setInt(1, pedidoId);
            st.setInt(2, tipoPago);
            st.setDouble(3, monto);
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
