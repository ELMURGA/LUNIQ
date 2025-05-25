package Proyecto;

public class Producto {

	private String nombre;
	private String descripcion;
	private int precio;
	private int stock;
	private String categoria;
	private String URL;
	
	public Producto(String nombre, int precio, int stock, String categoria, String URL)
	{
		this.nombre=nombre;
		this.precio=precio;
		this.stock=stock;
		this.categoria=categoria;
		this.URL=URL;
	}
	
	public Producto(String nombre, String descripcion, int precio, int stock, String categoria, String URL)
	{
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.precio=precio;
		this.stock=stock;
		this.categoria=categoria;
		this.URL=URL;
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

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripción) {
		this.descripcion = descripción;
	}
}
	

