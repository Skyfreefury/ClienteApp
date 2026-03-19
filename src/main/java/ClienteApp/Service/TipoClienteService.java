/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteApp.Service;

import ClienteApp.Model.TipoCliente;
import ClienteApp.Repository.TipoClienteDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author christianmogena3
 */
@Service
public class TipoClienteService {
    
    private final TipoClienteDAO tipoClienteDAO;
    
    // Inyectamos el DAO usando tu estilo de constructor
    @Autowired
    public TipoClienteService(@Qualifier("TipoClienteDAOjdbc") TipoClienteDAO tipoClienteDAO){
        this.tipoClienteDAO = tipoClienteDAO;
    }
    
    public void guardarTipoCliente(TipoCliente tipoCliente) {
        tipoClienteDAO.guardar(tipoCliente);
    }

    public void actualizarTipoCliente(TipoCliente tipoCliente) {
        tipoClienteDAO.actualizar(tipoCliente);
    }

    public void eliminarTipoCliente(int id) {
        tipoClienteDAO.eliminar(id);
    }
    
    public TipoCliente getforID(int id) {
        return tipoClienteDAO.getforID(id);
    }

    public List<TipoCliente> getAllTipoCliente() {
        return tipoClienteDAO.getAll();
    }
}
