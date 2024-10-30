/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import interfaces.ProductoDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import bean.Producto;
import util.MySQLConexion;

public class CrudProducto implements ProductoDao {

    @Override
    public void agregar(Producto p) {
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "{call spCrearProducto(?, ?, ?, ?, ?)}";
            CallableStatement st = cn.prepareCall(sql);
            st.setString(1, p.getNombre());
            st.setDouble(2, p.getPrecio());
            st.setInt(3, p.getCatId());
            st.setInt(4, p.getHoras());
            st.setInt(5, p.getComplejidad());
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modificar(Producto p) {
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "{call spModificarProducto(?, ?, ?, ?, ?, ?)}";
            CallableStatement st = cn.prepareCall(sql);
            st.setInt(1, p.getId());
            st.setString(2, p.getNombre());
            st.setDouble(3, p.getPrecio());
            st.setInt(4, p.getCatId());
            st.setInt(5, p.getHoras());
            st.setInt(6, p.getComplejidad());
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "{call spEliminarProducto(?)}";
            CallableStatement st = cn.prepareCall(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Producto buscar(int id) {
        Producto p = null;
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "SELECT id, nombre, precio, cat_id, horas, complejidad FROM producto WHERE id = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setCatId(rs.getInt(4));
                p.setHoras(rs.getInt(5));
                p.setComplejidad(rs.getInt(6));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "SELECT id, nombre, precio, cat_id, horas, complejidad FROM producto";
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setCatId(rs.getInt(4));
                p.setHoras(rs.getInt(5));
                p.setComplejidad(rs.getInt(6));
                lista.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Producto> listarPorCategoria(int categoriaId) {
        List<Producto> lista = new ArrayList<>();
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "SELECT id, nombre, precio, cat_id, horas, complejidad FROM producto WHERE cat_id = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, categoriaId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setCatId(rs.getInt(4));
                p.setHoras(rs.getInt(5));
                p.setComplejidad(rs.getInt(6));
                lista.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
