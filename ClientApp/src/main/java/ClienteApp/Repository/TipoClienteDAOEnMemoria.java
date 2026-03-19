/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteApp.Repository;

import ClienteApp.Model.TipoCliente;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * @author christianmogena3
 */
@Repository
@Qualifier("TipoClienteDAOEnMemoria")
public class TipoClienteDAOEnMemoria implements TipoClienteDAO {

    // Nuestra "base de datos" simulada
    private List<TipoCliente> tiposCliente = new ArrayList<>();
    
    // Un contador para simular el AUTO_INCREMENT de MySQL
    private int contadorId = 1;

    public TipoClienteDAOEnMemoria() {
        // Añadimos un par de datos de prueba por defecto al arrancar
        tiposCliente.add(new TipoCliente(contadorId++, "Premium"));
        tiposCliente.add(new TipoCliente(contadorId++, "Normal"));
    }

    @Override
    public void guardar(TipoCliente tipoCliente) {
        // Simulamos el autoincremental
        tipoCliente.setId(contadorId++);
        tiposCliente.add(tipoCliente);
        System.out.println("💾 Tipo de Cliente guardado EN MEMORIA.");
    }

    @Override
    public void actualizar(TipoCliente tipoCliente) {
        for (int i = 0; i < tiposCliente.size(); i++) {
            if (tiposCliente.get(i).getId() == tipoCliente.getId()) {
                tiposCliente.set(i, tipoCliente);
                System.out.println("💾 Tipo de Cliente actualizado EN MEMORIA.");
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        // Buscamos y eliminamos el tipo que coincida con el ID
        tiposCliente.removeIf(tipo -> tipo.getId() == id);
        System.out.println("💾 Tipo de Cliente eliminado DE MEMORIA.");
    }

    @Override
    public TipoCliente getforID(int id) {
        for (TipoCliente tipo : tiposCliente) {
            if (tipo.getId() == id) {
                return tipo;
            }
        }
        return null; // Si no lo encuentra
    }

    @Override
    public List<TipoCliente> getAll() {
        System.out.println("💾 Listado de tipos de clientes recuperado DE MEMORIA.");
        // Devolvemos una copia de la lista por seguridad
        return new ArrayList<>(tiposCliente);
    }
}
