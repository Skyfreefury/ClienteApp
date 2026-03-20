/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteApp.Repository;

import ClienteApp.Model.Producto;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoDAOjdbc implements ProductoDAO {
    private Connection getConection() { return Conection.getInstancia().getConection(); }

    @Override
    public void guardar(Producto p) {
        String sql = "INSERT INTO producto (nombre, foto) VALUES (?, ?)";
        try (PreparedStatement ps = getConection().prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getFoto());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void actualizar(Producto p) {
        String sql = "UPDATE producto SET nombre = ?, foto = ? WHERE id = ?";
        try (PreparedStatement ps = getConection().prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getFoto());
            ps.setInt(3, p.getId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void eliminar(int id) {
        try (PreparedStatement ps = getConection().prepareStatement("DELETE FROM producto WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public Producto getById(int id) {
        try (PreparedStatement ps = getConection().prepareStatement("SELECT * FROM producto WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Producto> getAll() {
        List<Producto> lista = new ArrayList<>();
        try (Statement st = getConection().createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM producto")) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    private Producto mapear(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setFoto(rs.getString("foto"));
        return p;
    }
}
