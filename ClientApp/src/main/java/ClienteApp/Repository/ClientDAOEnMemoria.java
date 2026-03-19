/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteApp.Repository;

import ClienteApp.Model.Client;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author christianmogena3
 */
@Repository
@Qualifier("ClientDAOEnMemoria")
public class ClientDAOEnMemoria implements ClientDAO {
    private final ArrayList<Client> clients = new ArrayList();
    private final AtomicInteger contadorId = new AtomicInteger(1);
//    @Override
//    public void guardar(Client client) {
//        client.setId(contadorId.getAndIncrement());
//        client.CalcularPromedioCompras();
//        clients.add(client);
//    }
    @Override
    public void guardar(Client client){

        if(client.getId()==0){
           client.setId(contadorId.getAndIncrement());
           client.CalcularPromedioCompras();
           clients.add(client);
            
        }
        else{
            
            actualizar(client);
        }
    }
    @Override
    public void eliminar(int id) {
        clients.removeIf(c -> c.getId() == id);
    }
    @Override
    public void actualizar(Client client) {
        eliminar(client.getId());
        client.CalcularPromedioCompras();
        clients.add(client);
    }
    @Override
    public Client getforID(int id) {
        return clients.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }
    @Override
    public ArrayList<Client> getAll(){
        System.out.println("Estoy aquí" + clients.size());
        return clients;
    }   
}
