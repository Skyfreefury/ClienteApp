/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteApp.Service;

import ClienteApp.Model.Producto;
import ClienteApp.Repository.ProductoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoDAO productoDAO;

    public List<Producto> listar() { return productoDAO.getAll(); }
    public void guardar(Producto p) { productoDAO.guardar(p); }
    public void actualizar(Producto p) { productoDAO.actualizar(p); }
    public void eliminar(int id) { productoDAO.eliminar(id); }
    public Producto buscarPorId(int id) { return productoDAO.getById(id); }
}
