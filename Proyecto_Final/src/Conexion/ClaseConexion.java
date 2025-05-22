package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;

public class ClaseConexion {
    private Connection conexion;

    public ClaseConexion(String host, String port, String user, String pass, String bd) {
        String url = "jdbc:mariadb://" + host + ":" + port + "/" + bd + "?useSSL=false&serverTimezone=UTC";
        try {
            // Cargar el driver JDBC de MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
            this.conexion = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC de MariaDB no encontrado: " + e.getMessage());
            this.conexion = null;
        } catch (SQLException e) {
            System.err.println("Fallo al conectar con MariaDB: " + e.getMessage());
            this.conexion = null;
        }
    }

    public boolean success() {
        try {
            return this.conexion != null && !this.conexion.isClosed();
        } catch (SQLException e) {
            System.err.println("Error al verificar la conexión: " + e.getMessage());
            return false;
        }
    }

    public boolean insertUser(String usuario, String correo, String contrasena) {
        if (!success()) return false;
        String sql = "INSERT INTO usuarios (nombre_usuario, email, contrasena) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = this.conexion.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, correo);
            stmt.setString(3, contrasena);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean login(String usuario, String contrasena) {
        if (!success()) return false;
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";
        try (PreparedStatement stmt = this.conexion.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Error durante el login: " + e.getMessage());
            return false;
        }
    }

    public void introducirCategorias(JComboBox<String> cb) {
        if (!success()) return;
        String sql = "SELECT DISTINCT categoria FROM productos";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            cb.removeAllItems();
            while (rs.next()) {
                cb.addItem(rs.getString("categoria"));
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar categorías: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return this.conexion;
    }

    public void close() {
        if (this.conexion != null) {
            try {
                this.conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}