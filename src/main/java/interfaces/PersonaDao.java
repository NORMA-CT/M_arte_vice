package interfaces;

import bean.Persona;
import java.util.List;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

public interface PersonaDao {

    void agregar(Persona p);  // Crear nueva persona

    Persona buscarPorId(int id);  // Ver mi cuenta

    void modificar(Persona p);  // Modificar información de la cuenta

    List<Map<String, Object>> listarEmpleadosConHorasPendientes();
}
