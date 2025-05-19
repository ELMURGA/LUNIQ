package Test;

import conexion.ConexionDB;
import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {
    public static void main(String[] args) {
        try (Connection conn = ConexionDB.getConnection()) {
            System.out.println("✅ Conexión establecida correctamente a Oracle");
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
        }
    }
}