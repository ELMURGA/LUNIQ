package Proyecto;

import java.sql.Date;

public class Usuario {
	
		private int idUsuario;
	    private String nombreUsuario;
	    private String nombre;
	    private String apellido;
	    private String email;
	    private String contraseña;
	    private String pais;
	    private Date fechaRegistro;
	    
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

		public int getIdUsuario() {
			return idUsuario;
		}

		public void setIdUsuario(int idUsuario) {
			this.idUsuario = idUsuario;
		}

		public String getNombreUsuario() {
			return nombreUsuario;
		}

		public void setNombreUsuario(String nombreUsuario) {
			this.nombreUsuario = nombreUsuario;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getContraseña() {
			return contraseña;
		}

		public void setContraseña(String contraseña) {
			this.contraseña = contraseña;
		}

		public String getPais() {
			return pais;
		}

		public void setPais(String pais) {
			this.pais = pais;
		}

		public Date getFechaRegistro() {
			return fechaRegistro;
		}

		public void setFechaRegistro(Date fechaRegistro) {
			this.fechaRegistro = fechaRegistro;
		}
		
	    
	
}
