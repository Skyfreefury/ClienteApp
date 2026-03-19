/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteApp.Repository;

import ClienteApp.Model.TipoCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * @author christianmogena3
 */
@Repository
@Qualifier("TipoClienteDAOjdbc")
public class TipoClienteDAOjdbc implements TipoClienteDAO {

    private Connection getConection() {
        return Conection.getInstancia().getConection();
    }

    @Override
    public void guardar(TipoCliente tipoCliente) {
        String sql = "INSERT INTO tipo_cliente (nombre) VALUES (?)";
        try (PreparedStatement pstmt = getConection().prepareStatement(sql)) {
            pstmt.setString(1, tipoCliente.getNombre());
            pstmt.executeUpdate();
            System.out.println("✅ Tipo de Cliente guardado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al guardar el tipo de cliente.");
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(TipoCliente tipoCliente) {
        String sql = "UPDATE tipo_cliente SET nombre = ? WHERE id = ?";
        try (PreparedStatement pstmt = getConection().prepareStatement(sql)) {
            pstmt.setString(1, tipoCliente.getNombre());
            pstmt.setInt(2, tipoCliente.getId());
            pstmt.executeUpdate();
            System.out.println("✅ Tipo de Cliente actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar el tipo de cliente.");
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM tipo_cliente WHERE id = ?";
        try (PreparedStatement pstmt = getConection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("✅ Tipo de Cliente eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar el tipo de cliente.");
            e.printStackTrace();
        }
    }

    @Override
    public TipoCliente getforID(int id) {
        String sql = "SELECT * FROM tipo_cliente WHERE id = ?";
        TipoCliente tipoCliente = null;
        try (PreparedStatement pstmt = getConection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    tipoCliente = mapearTipoCliente(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener el tipo de cliente por ID.");
            e.printStackTrace();
        }
        return tipoCliente;
    }

    @Override
    public List<TipoCliente> getAll() {
        List<TipoCliente> tipos = new ArrayList<>();
        String sql = "SELECT * FROM tipo_cliente";
        try (PreparedStatement pstmt = getConection().prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                tipos.add(mapearTipoCliente(rs));
            }
            System.out.println("✅ Listado de tipos de clientes recuperado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al listar los tipos de clientes.");
            e.printStackTrace();
        }
        return tipos;
    }

    private TipoCliente mapearTipoCliente(ResultSet rs) throws SQLException {
        TipoCliente tipoCliente = new TipoCliente();
        tipoCliente.setId(rs.getInt("id"));
        tipoCliente.setNombre(rs.getString("nombre"));
        return tipoCliente;
    }
}
