package Proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

public class ClaseConexion {
	
	private Connection conexion;
	
	public boolean success() {//Para ver si la conexion sucede
		return this.conexion != null;
	}
	
    public boolean insertUser(String usuario, String correo, String contrasena) {//Insertar usuarios
        try {
        	String SQL= "INSERT INTO usuarios (nombre_usuario, email, contraseña) VALUES (?, ?, ?)";
			var stmt = this.conexion.prepareStatement(SQL);
			stmt.setString(1, usuario);
			stmt.setString(2, correo);
			stmt.setString(3, contrasena);
			
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
    }
	
    public boolean login(String usuario, String contraseña) {//Inicio de sesion
    	String SQL = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contraseña = ?";
    	
    	try {
    		var stmt = this.conexion.prepareStatement(SQL);
    		stmt.setString(1, usuario);
    		stmt.setString(2, contraseña);
    		ResultSet rs = stmt.executeQuery();
    		
    		// Porque empieza por la cabecera
    		if (rs.next()) {
    			return true;
    		}
    	} catch (SQLException e) {}
    	
    	return false;
    }
    
    public void introducirCategorias(JComboBox<String> cb)
    {
    	String sql= "SELECT categoria FROM productos";
    	try{
    		var stmt=conexion.prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		cb.removeAllItems();
    		while(rs.next())
    		{
    			cb.addItem(rs.getString("Categoria"));
    		}
    	}catch (SQLException e){
    		System.out.println("Error en la carga"+ e.getMessage());
    	}
    }
    
    public ClaseConexion(String host, String port, String user, String pass, String bd) {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + bd + "?useSSL=false&serverTimezone=UTC";
        try {
            // Cargar el driver JDBC (opcional con JDBC 4.0+ pero recomendado)
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexion = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
            this.conexion = null;
        }
    }

    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        String sql = "SELECT nombre_producto, precio, categoria FROM productos";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                String nombre = rs.getString("nombre_producto");
                int precio = rs.getInt("precio");
                String categoria = rs.getString("categoria");
                
                Producto p =new Producto(nombre, precio, categoria);
                
                productos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
    
}
