/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteApp.Service;

import ClienteApp.Model.Client;
import ClienteApp.Repository.ClientDAO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author christianmogena3
 */
@Service
public class ClientService {
    private final ClientDAO clientDAO;
    @Autowired
    public ClientService(@Qualifier("ClientDAOjdbc")ClientDAO clientDAO){
        this.clientDAO = clientDAO;
    }
    public void guardarClient(Client client) {
        clientDAO.guardar(client);
    }

    public void actualizarClient(Client client) {
        clientDAO.actualizar(client);
    }

    public void eliminarClient(int id) {
        clientDAO.eliminar(id);
    }
    
    public Client getforID(int id) {
        return clientDAO.getforID(id);
    }

    public List<Client> getAllClient() {
        return clientDAO.getAll();
    }
}
