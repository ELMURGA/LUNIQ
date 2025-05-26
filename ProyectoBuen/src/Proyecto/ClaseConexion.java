package Proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

/**
 * Clase que gestiona la conexión con una base de datos MySQL y proporciona métodos
 * para interactuar con ella. Permite realizar operaciones como inserción de usuarios,
 * login, obtención de productos y gestión de perfiles de usuario.
 * 
 * @author [Diego Capellán y Alejandro Murga]
 * @version 1.0
 */
public class ClaseConexion {
    
    private Connection conexion;
    
    /**
     * Constructor que establece la conexión con la base de datos MySQL.
     * 
     * @param host Dirección del servidor de la base de datos
     * @param port Puerto de conexión a la base de datos
     * @param user Nombre de usuario para la autenticación
     * @param pass Contraseña para la autenticación
     * @param bd Nombre de la base de datos a la que conectarse
     */
    public ClaseConexion(String host, String port, String user, String pass, String bd) {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + bd + "?useSSL=false&serverTimezone=UTC";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexion = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
            this.conexion = null;
        }
    }
    
    /**
     * Verifica si la conexión con la base de datos se estableció correctamente.
     * 
     * @return true si la conexión es válida, false en caso contrario
     */
    public boolean success() {
        return this.conexion != null;
    }
    
    /**
     * Inserta un nuevo usuario en la base de datos.
     * 
     * @param usuario Nombre de usuario a insertar
     * @param correo Correo electrónico del usuario
     * @param contrasena Contraseña del usuario
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertUser(String usuario, String correo, String contrasena) {
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
    
    /**
     * Realiza el proceso de login verificando las credenciales del usuario.
     * 
     * @param usuario Nombre de usuario a verificar
     * @param contraseña Contraseña a verificar
     * @return true si las credenciales son correctas, false en caso contrario
     */
    public boolean login(String usuario, String contraseña) {
        String SQL = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contraseña = ?";
        
        try {
            var stmt = this.conexion.prepareStatement(SQL);
            stmt.setString(1, usuario);
            stmt.setString(2, contraseña);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {}
        
        return false;
    }
    
    /**
     * Obtiene todos los productos almacenados en la base de datos.
     * 
     * @return ArrayList de objetos Producto con la información de todos los productos
     */
    public ArrayList<Producto> sacarProductos() {
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
                
                ap.add(p);
            }
            
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        
        return ap;
    }
    
    /**
     * Obtiene los datos de un usuario específico.
     * 
     * @param nombreUsuario Nombre del usuario cuyos datos se quieren obtener
     * @return ResultSet con los datos del usuario o null si ocurre un error
     */
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

    /**
     * Actualiza los datos de un usuario en la base de datos.
     * 
     * @param nombreUsuario Nombre de usuario a actualizar
     * @param nombre Nuevo nombre del usuario
     * @param apellido Nuevo apellido del usuario
     * @param pais Nuevo país del usuario
     * @return true si la actualización fue exitosa, false en caso contrario
     */
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