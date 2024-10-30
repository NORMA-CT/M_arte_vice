/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;


import bean.Usuario;
import java.util.List;

public interface UsuarioDao {

    void agregar(Usuario u);

    void modificar(Usuario u);

    void eliminar(int id);

    Usuario buscar(int id);

    List<Usuario> listar();

    Usuario iniciarSesion(String usuario, String password);  // Nuevo método para iniciar sesión

}
