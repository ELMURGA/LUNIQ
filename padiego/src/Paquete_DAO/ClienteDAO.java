
import Model.Cliente;
import Utils.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


package Paquete_DAO;


public class ClienteDAO {
    private static final String ConexionDB = null;
	private Connection conn;
	private Cliente cliente;

    public ClienteDAO() {
        conn = ConexionDB.obtenerConexion();
    }

    public void insertar(Model.Cliente cliente2) throws SQLException {
        this.cliente = cliente2;
		String query = "INSERT INTO clientes (nombre, apellido, email, telefono, ciudad, pais, codigo_postal) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cliente2.getNombre());
            stmt.setString(2, cliente2.getApellido());
            stmt.setString(3, cliente2.getEmail());
            stmt.setString(4, cliente2.getTelefono());
            stmt.setString(5, cliente2.getCiudad());
            stmt.setNString(6, cliente2.getClass());
            stmt.setString(7, cliente2.getCodigoPostal());
            stmt.executeUpdate();
        }
    }

    private String getCodigoPostal() {
		// TODO Auto-generated method stub
		return null;
	}

	private String getCiudad() {
		// TODO Auto-generated method stub
		return null;
	}

	private String getTelefono() {
		// TODO Auto-generated method stub
		return null;
	}

	private String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	private String getApellido() {
		// TODO Auto-generated method stub
		return null;
	}

	private String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Paquete_DAO.ClienteDAO> listar() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM clientes";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Model.Cliente cliente = new Model.Cliente();
                cliente.setClienteId(rs.getInt("cliente_id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setCiudad(rs.getString("ciudad"));
                cliente.setPais(rs.getString("pais"));
                cliente.setCodigoPostal(rs.getString("codigo_postal"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }
}
