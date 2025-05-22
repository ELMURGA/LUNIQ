package Proyecto;

public class Producto {

	private String nombre;
	private int precio;
	private String categoria;
	
	public Producto(String nombre, int precio, String categoria)
	{
		this.nombre=nombre;
		this.precio=precio;
		this.categoria=categoria;
	}
	
	// Getters
    public String getNombre() 
    { 
    	return nombre; 
    }
    public int getPrecio() 
    {
    	return precio; 
    }
    public String getCategoria() 
   { 
    	return categoria; 
    }
}
	

