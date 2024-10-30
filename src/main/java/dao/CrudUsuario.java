/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import interfaces.UsuarioDao;
import bean.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.MySQLConexion;

public class CrudUsuario implements UsuarioDao {

    @Override
    public void agregar(Usuario u) {
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "{call spCrearUsuario(?, ?, ?, ?)}";
            CallableStatement st = cn.prepareCall(sql);
            st.setInt(1, u.getPersonaId());
            st.setString(2, u.getPassword());
            st.setString(3, u.getUsuario());
            st.setInt(4, u.getPerfilId());
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modificar(Usuario u) {
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "{call spModificarUsuario(?, ?, ?, ?)}";
            CallableStatement st = cn.prepareCall(sql);
            st.setInt(1, u.getId());
            st.setString(2, u.getPassword());
            st.setString(3, u.getUsuario());
            st.setInt(4, u.getPerfilId());
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "{call spEliminarUsuario(?)}";
            CallableStatement st = cn.prepareCall(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Usuario buscar(int id) {
        Usuario u = null;
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "SELECT id, persona_id, password, usuario, perfil_id FROM usuario WHERE id = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                u = new Usuario();
                u.setId(rs.getInt(1));
                u.setPersonaId(rs.getInt(2));
                u.setPassword(rs.getString(3));
                u.setUsuario(rs.getString(4));
                u.setPerfilId(rs.getInt(5));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return u;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "SELECT id, persona_id, password, usuario, perfil_id FROM usuario WHERE perfil_id != 3";
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt(1));
                u.setPersonaId(rs.getInt(2));
                u.setPassword(rs.getString(3));
                u.setUsuario(rs.getString(4));
                u.setPerfilId(rs.getInt(5));
                lista.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public Usuario iniciarSesion(String usuario, String password) {
        Usuario user = null;
        Connection cn = MySQLConexion.getConexion();
        try {

            //String sql = "{call spIniciarSesion(?, ?)}";
            //CallableStatement st = cn.prepareCall(sql);
//            st.executeUpdate();

            String sql = "SELECT id, persona_id, usuario, password, perfil_id FROM usuario WHERE usuario = ? AND password = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, usuario);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                user = new Usuario();
                user.setId(rs.getInt(1));
                user.setPersonaId(rs.getInt(2));
                user.setUsuario(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setPerfilId(rs.getInt(5));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }
}
