/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ClienteApp.Repository;

import ClienteApp.Model.Client;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author christianmogena3
 */
public interface ClientDAO {
    void guardar(Client client);
    void actualizar(Client client);
    void eliminar(int id);
    Client getforID(int id);
    List<Client> getAll();
}
