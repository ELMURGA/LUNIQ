import Model.Producto;
import Utils.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


package Paquete_DAO;


public class ProductoDAO {
    private static final String ConexionDB = null;
	private Connection conn;

    public ProductoDAO() {
        conn = ConexionDB.obtenerConexion();
    }

    public void insertar(Model.Producto producto) throws SQLException {
        String query = "INSERT INTO productos (nombre, descripcion, precio, categoria_id, codigo_identificador) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setInt(4, producto.getCategoriaId());
            stmt.setString(5, producto.getCodigoIdentificador());
            stmt.executeUpdate();
        }
    }

    public List<Model.Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM productos";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Model.Producto producto = new Model.Producto();
                producto.setProductoId(rs.getInt("producto_id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setCategoriaId(rs.getInt("categoria_id"));
                producto.setCodigoIdentificador(rs.getString("codigo_identificador"));
                productos.add(producto);
            }
        }
        return productos;
    }
}
