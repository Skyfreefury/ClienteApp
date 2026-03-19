/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteApp.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author christianmogena3
 */
public class Conection {
    private static final String URL = "jdbc:mysql://localhost:3306/ClientApp";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Conection instancia;
    private Connection connection;

    // Constructor privado para evitar la creación externa de instancias
    private Conection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión a la base de datos establecida.");
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar a la base de datos.");
            e.printStackTrace();
        }
    }

    // Método para obtener la instancia del Singleton
    public static synchronized Conection getInstancia() {
        if (instancia == null || instancia.connection == null) {
            instancia = new Conection();
        }
        return instancia;
    }

    // Método para obtener la conexión activa
    public Connection getConection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Método para cerrar la conexión manualmente
    public void cerrarConection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✅ Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al cerrar la conexión.");
            e.printStackTrace();
        }
    }
}
