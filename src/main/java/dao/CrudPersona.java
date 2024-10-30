/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import interfaces.PersonaDao;
import bean.Persona;
import util.MySQLConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrudPersona implements PersonaDao {

    @Override
    public void agregar(Persona p) {
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "{call spCrearPersona(?, ?, ?, ?)}";  // El stored procedure debe insertarlo
            CallableStatement st = cn.prepareCall(sql);
            st.setString(1, p.getTipoDoc());
            st.setString(2, p.getNumDoc());
            st.setString(3, p.getNombre());
            st.setString(4, p.getApellido());
//            st.executeUpdate();
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                p.setId(rs.getInt(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Persona buscarPorId(int id) {
        Persona p = null;
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "SELECT id, tipo_doc, num_doc, nombre, apellido FROM persona WHERE id = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                p = new Persona();
                p.setId(rs.getInt(1));
                p.setTipoDoc(rs.getString(2));
                p.setNumDoc(rs.getString(3));
                p.setNombre(rs.getString(4));
                p.setApellido(rs.getString(5));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return p;
    }

    @Override
    public void modificar(Persona p) {
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "{call spModificarPersona(?, ?, ?, ?, ?)}";  // El stored procedure debe actualizarlo
            CallableStatement st = cn.prepareCall(sql);
            st.setInt(1, p.getId());
            st.setString(2, p.getTipoDoc());
            st.setString(3, p.getNumDoc());
            st.setString(4, p.getNombre());
            st.setString(5, p.getApellido());
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Map<String, Object>> listarEmpleadosConHorasPendientes() {
        List<Map<String, Object>> lista = new ArrayList<>();
        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "{call spListarArtesanosConHorasPendientes()}";
            CallableStatement st = cn.prepareCall(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Map<String, Object> artesano = new HashMap<>();
                artesano.put("PersonaID", rs.getInt("PersonaID"));
                artesano.put("Nombre", rs.getString("Nombre"));
                artesano.put("Apellido", rs.getString("Apellido"));
                artesano.put("HorasAsignadas", rs.getDouble("HorasAsignadas"));
                artesano.put("HorasFaltantes", rs.getDouble("HorasFaltantes"));
                lista.add(artesano);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
