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

    
    
    ///Para productos
    public ArrayList<Producto> sacarProductos()
    {
    	String sql="SELECT nombre, descripcion, precio, stock, categoria, ImagenURL FROM productos";
    	ArrayList<Producto> ap = new ArrayList<>();
    	
    	 try {
    	        var stmt = this.conexion.prepareStatement(sql);
    	        ResultSet rs = stmt.executeQuery();
    	        
    	        while (rs.next()) {
    	            
    	            String nombre = rs.getString("nombre");
    	            String descripcion=rs.getString("descripcion");
    	            int precio = rs.getInt("precio");
    	            int stock = rs.getInt("stock");
    	            String categoria = rs.getString("categoria");
    	            String ImagenURL = rs.getString("ImagenURL");
    	            Producto p = new Producto(nombre,descripcion, precio, stock, categoria, ImagenURL);
    	           
    	            
    	            // Agregar el producto al ArrayList
    	            ap.add(p);
    	        }
    	        

    	        
    	    } catch (Exception e) {
    	        e.printStackTrace(); 
    	    }
    	    
    	    return ap;
    }
    

    //Perfil
 // Método para obtener los datos de un usuario
    public ResultSet obtenerDatosUsuario(String nombreUsuario) {
        String SQL = "SELECT * FROM usuarios WHERE nombre_usuario = ?";
        try {
            var stmt = this.conexion.prepareStatement(SQL);
            stmt.setString(1, nombreUsuario);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para actualizar los datos del usuario
    public boolean actualizarUsuario(String nombreUsuario, String nombre, String apellido, String pais) {
        String SQL = "UPDATE usuarios SET nombre = ?, apellido = ?, pais = ? WHERE nombre_usuario = ?";
        try {
            var stmt = this.conexion.prepareStatement(SQL);
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, pais);
            stmt.setString(4, nombreUsuario);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    	
    
}
