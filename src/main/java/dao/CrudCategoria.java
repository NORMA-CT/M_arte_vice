/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import interfaces.CategoriaDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import bean.Categoria;
import util.MySQLConexion;

public class CrudCategoria implements CategoriaDao {

    @Override
    public void agregar(Categoria c) {
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "{call spCrearCategoria(?)}";
            CallableStatement st = cn.prepareCall(sql);
            st.setString(1, c.getNombre());
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modificar(Categoria c) {
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "{call spModificarCategoria(?, ?)}";
            CallableStatement st = cn.prepareCall(sql);
            st.setInt(1, c.getId());
            st.setString(2, c.getNombre());
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "{call spEliminarCategoria(?)}";
            CallableStatement st = cn.prepareCall(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Categoria buscar(int id) {
        Categoria c = null;
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "SELECT id, nombre FROM categoria WHERE id = ? ORDER BY ID DESC";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                c = new Categoria();
                c.setId(rs.getInt(1));
                c.setNombre(rs.getString(2));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return c;
    }

    @Override
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "SELECT id, nombre FROM categoria";
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt(1));
                c.setNombre(rs.getString(2));
                lista.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
