package Proyecto;

import java.sql.Date;

/**
 * Clase que representa a un usuario del sistema.
 * Contiene información básica como identificador, credenciales, datos personales
 * y fecha de registro.
 * 
 * @author Diego Capellán y Alejandro Hernández
 * @version 1.0
 */
public class Usuario {
    
    /** Identificador único del usuario */
    private int idUsuario;
    
    /** Nombre de usuario para el login */
    private String nombreUsuario;
    
    /** Nombre real del usuario */
    private String nombre;
    
    /** Apellido del usuario */
    private String apellido;
    
    /** Correo electrónico del usuario */
    private String email;
    
    /** Contraseña del usuario (debería estar encriptada) */
    private String contraseña;
    
    /** País de residencia del usuario */
    private String pais;
    
    /** Fecha en que el usuario se registró en el sistema */
    private Date fechaRegistro;
    
    /**
     * Constructor para crear un objeto Usuario con todos sus datos.
     * 
     * @param idUsuario Identificador único del usuario
     * @param nombreUsuario Nombre de usuario para el login
     * @param nombre Nombre real del usuario
     * @param apellido Apellido del usuario
     * @param email Correo electrónico del usuario
     * @param contraseña Contraseña del usuario
     * @param pais País de residencia del usuario
     * @param fechaRegistro Fecha de registro en el sistema
     */
    public Usuario(int idUsuario, String nombreUsuario, String nombre, String apellido, String email, String contraseña,
        String pais, Date fechaRegistro) {
        super();
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contraseña = contraseña;
        this.pais = pais;
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Obtiene el identificador del usuario
     * @return El id del usuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador del usuario
     * @param idUsuario El nuevo id del usuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el nombre de usuario para login
     * @return El nombre de usuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre de usuario para login
     * @param nombreUsuario El nuevo nombre de usuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene el nombre real del usuario
     * @return El nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre real del usuario
     * @param nombre El nuevo nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del usuario
     * @return El apellido del usuario
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del usuario
     * @param apellido El nuevo apellido del usuario
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el email del usuario
     * @return El email del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del usuario
     * @param email El nuevo email del usuario
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario
     * @return La contraseña del usuario
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Establece la contraseña del usuario
     * @param contraseña La nueva contraseña del usuario
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * Obtiene el país del usuario
     * @return El país del usuario
     */
    public String getPais() {
        return pais;
    }

    /**
     * Establece el país del usuario
     * @param pais El nuevo país del usuario
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Obtiene la fecha de registro del usuario
     * @return La fecha de registro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Establece la fecha de registro del usuario
     * @param fechaRegistro La nueva fecha de registro
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}