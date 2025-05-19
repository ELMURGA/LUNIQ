package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";
    private static final String USER = "Miproyecto";
    private static final String PASSWORD = "Medac24";
	private Connection connection;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("❌ No se encontró el driver JDBC de Oracle", e);
        }
    }
    public boolean validarUsuario(String correo, String contraseña) {
        try {
            String query = "SELECT * FROM usuarios WHERE correo = ? AND contraseña = ?";
            connection = null;
			PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, correo);
            stmt.setString(2, contraseña);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Devuelve true si encuentra un usuario
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	public boolean verificarContrasena(String correo, String contrasena) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean existeUsuario(String correo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean insertarUsuario(String nombre, String apellidos, String correo) {
		// TODO Auto-generated method stub
		return false;
	}
}