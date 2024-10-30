/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;



import java.util.List;
import java.util.Map;

public interface ReporteDao {
    List<Map<String, Object>> obtenerPedidosPorEstado(int usuarioId, int perfilId, Integer mes);
    List<Map<String, Object>> obtenerPagosUltimos6Meses(int usuarioId, int perfilId);
    List<Map<String, Object>> obtenerTop10ProductosVendidos(int usuarioId, int perfilId);
}
