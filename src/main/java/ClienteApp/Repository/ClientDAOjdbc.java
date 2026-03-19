/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteApp.Repository;

import ClienteApp.Model.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author christianmogena3
 */
@Repository
@Qualifier("ClientDAOjdbc")
public class ClientDAOjdbc implements ClientDAO {
    private Connection getConection() {
        return Conection.getInstancia().getConection();
    }
    @Override
    public void guardar(Client client) {
        client.CalcularPromedioCompras();
        String sql = "INSERT INTO cliente (foto, nombre, apellido, edad, compra1, compra2, compra3, promedioCompras) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = getConection().prepareStatement(sql)) {
            pstmt.setString(2, client.getNombre());
            pstmt.setString(3, client.getApellido());
            pstmt.setInt(4, client.getEdad());
            pstmt.setDouble(5, client.getCompra1());
            pstmt.setDouble(6, client.getCompra2());
            pstmt.setDouble(7, client.getCompra3());
            pstmt.setDouble(8, client.getPromedioCompras());
            pstmt.setString(1, client.getFoto());
            pstmt.executeUpdate();
            System.out.println("✅ Cliente guardado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al guardar el cliente.");
            e.printStackTrace();
        }
    }
    @Override
    public void actualizar(Client client) {
        String sql = "UPDATE cliente SET nombre = ?, apellido = ?, edad = ?, compra1 = ?, compra2 = ?, compra3 = ?, foto = ?, promedioCompras = ? WHERE id = ?";
        client.CalcularPromedioCompras();
        try (PreparedStatement pstmt = getConection().prepareStatement(sql)) {
            pstmt.setString(1, client.getNombre());
            pstmt.setString(2, client.getApellido());
            pstmt.setInt(3, client.getEdad()); // Mejor setInt para la edad
            pstmt.setDouble(4, client.getCompra1());
            pstmt.setDouble(5, client.getCompra2());
            pstmt.setDouble(6, client.getCompra3());
            pstmt.setString(7, client.getFoto());
            pstmt.setDouble(8, client.getPromedioCompras()); 
            pstmt.setInt(9, client.getId());
            pstmt.executeUpdate();
            System.out.println("✅ Cliente actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar el cliente.");
            e.printStackTrace();
        }
    }
    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (PreparedStatement pstmt = getConection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("✅ Cliente eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar el cliente.");
            e.printStackTrace();
        }
    }
    @Override
    public Client getforID(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        Client client = null;
        try (PreparedStatement pstmt = getConection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    client = mapearClient(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener el alumno por ID.");
            e.printStackTrace();
        }
        return client;
    }
    @Override
    public List<Client> getAll() {
        List<Client> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (PreparedStatement pstmt = getConection().prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                alumnos.add(mapearClient(rs));
            }
            System.out.println("✅ Listado de clientes recuperado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al listar los clientes.");
            e.printStackTrace();
        }
        return alumnos;
    }

    private Client mapearClient(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setId(rs.getInt("id"));
        client.setNombre(rs.getString("nombre"));
        client.setApellido(rs.getString("apellido"));
        client.setEdad(rs.getInt("edad"));
        client.setCompra1(rs.getDouble("compra1"));
        client.setCompra2(rs.getDouble("compra2"));
        client.setCompra3(rs.getDouble("compra3"));
        client.setPromedioCompras(rs.getDouble("PromedioCompras"));
        client.setFoto(rs.getString("foto"));
        return client;
    }
}
