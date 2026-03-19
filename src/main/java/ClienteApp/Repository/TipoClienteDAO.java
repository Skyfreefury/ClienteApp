/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteApp.Repository;

import ClienteApp.Model.TipoCliente;
import java.util.List;

/**
 * @author christianmogena3
 */
public interface TipoClienteDAO {
    void guardar(TipoCliente tipoCliente);
    void actualizar(TipoCliente tipoCliente);
    void eliminar(int id);
    TipoCliente getforID(int id);
    List<TipoCliente> getAll();
}
