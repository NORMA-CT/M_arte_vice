/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;


import java.util.List;
import bean.Producto;

public interface ProductoDao {

    void agregar(Producto p); // Agregar producto

    void modificar(Producto p); // Modificar producto

    void eliminar(int id); // Eliminar producto

    Producto buscar(int id); // Buscar producto por ID

    List<Producto> listar(); // Listar todos los productos

    List<Producto> listarPorCategoria(int categoriaId); // Listar productos por categoría
}
