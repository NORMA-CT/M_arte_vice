/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interfaces.ReporteDao;
import util.MySQLConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CrudReporte implements ReporteDao {

    @Override
    public List<Map<String, Object>> obtenerPedidosPorEstado(int usuarioId, int perfilId, Integer mes) {
        List<Map<String, Object>> lista = new ArrayList<>();
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "{call spReportePedidosPorEstado(?, ?, ?)}";
            CallableStatement st = cn.prepareCall(sql);
            st.setInt(1, usuarioId);
            st.setInt(2, perfilId);
            if (mes != null) {
                st.setInt(3, mes);
            } else {
                st.setNull(3, Types.INTEGER);  // Sin mes filtra todos los meses
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("Estado", rs.getString("Estado"));
                map.put("Cantidad_Pedidos", rs.getInt("Cantidad_Pedidos"));
                lista.add(map);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Map<String, Object>> obtenerPagosUltimos6Meses(int usuarioId, int perfilId) {
        List<Map<String, Object>> lista = new ArrayList<>();
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "{call spReportePagosUltimos6Meses(?, ?)}";
            CallableStatement st = cn.prepareCall(sql);
            st.setInt(1, usuarioId);
            st.setInt(2, perfilId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("Mes", rs.getInt("Mes"));
                map.put("Total_Ventas", rs.getDouble("Total_Ventas"));
                lista.add(map);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Map<String, Object>> obtenerTop10ProductosVendidos(int usuarioId, int perfilId) {
        List<Map<String, Object>> lista = new ArrayList<>();
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "{call spTop10ProductosVendidos(?, ?)}";
            CallableStatement st = cn.prepareCall(sql);
            st.setInt(1, usuarioId);
            st.setInt(2, perfilId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("Producto", rs.getString("Producto"));
                map.put("Cantidad_Vendida", rs.getInt("Cantidad_Vendida"));
                lista.add(map);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
