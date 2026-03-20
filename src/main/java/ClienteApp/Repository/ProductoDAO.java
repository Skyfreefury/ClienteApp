/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteApp.Repository;
import ClienteApp.Model.Producto;
import java.util.List;

public interface ProductoDAO {
    void guardar(Producto p);
    void actualizar(Producto p);
    void eliminar(int id);
    Producto getById(int id);
    List<Producto> getAll();
}
