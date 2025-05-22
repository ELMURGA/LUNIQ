package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Clase para la conexión con una base de datos MySQL
 */
public class ConexionDB {

    private String BD;
    private String USUARIO;
    private String PASS;
    private Connection connection;
    private String HOST;
    private TimeZone zonahoraria;

    public ConexionDB() {
        HOST = "localhost";
        USUARIO = "trabajador";
        PASS = "Medac2024";
        BD = "luniq_db"; // Reemplaza por el nombre real de tu base de datos
        connection = null;
    }

    private void registrarDriver() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al conectar con MySQL: " + e.getMessage());
        }
    }

    public void conectar() throws SQLException {
        if (connection == null || connection.isClosed()) {
            registrarDriver();
            Calendar now = Calendar.getInstance();
            zonahoraria = now.getTimeZone();
            connection = DriverManager.getConnection(
                "jdbc:mysql://" + HOST + "/" + BD + "?useLegacyDatetimeCode=false&serverTimezone=" + zonahoraria.getID(),
                USUARIO, PASS
            );
        }
    }

    public void desconectar() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public ResultSet ejecutarSelect(String consulta) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(consulta);
    }

    public int ejecutarInsertDeleteUpdate(String consulta) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeUpdate(consulta);
    }

    public boolean existeUsuario(String correo) {
        try {
            conectar();
            String query = "SELECT * FROM usuarios WHERE correo = '" + correo + "'";
            ResultSet rs = ejecutarSelect(query);
            boolean existe = rs.next();
            rs.close();
            desconectar();
            return existe;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertarUsuario(String nombre, String correo, String contraseña) {
        try {
            conectar();
            String query = "INSERT INTO usuarios (nombre, correo, contrasena) VALUES ('" + nombre + "', '" + correo + "', '" + contraseña + "')";
            int filas = ejecutarInsertDeleteUpdate(query);
            desconectar();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verificarContrasena(String correo, String contrasena) {
        try {
            conectar();
            String query = "SELECT * FROM usuarios WHERE correo = '" + correo + "' AND contrasena = '" + contrasena + "'";
            ResultSet rs = ejecutarSelect(query);
            boolean valido = rs.next();
            rs.close();
            desconectar();
            return valido;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}