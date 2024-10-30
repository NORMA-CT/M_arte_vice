/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;


import bean.Categoria;
import java.util.List;

public interface CategoriaDao {

    void agregar(Categoria c);

    void modificar(Categoria c);

    void eliminar(int id);

    Categoria buscar(int id);

    List<Categoria> listar();
}
