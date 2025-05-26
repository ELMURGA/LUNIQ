package Proyecto;

/**
 * Clase que representa un producto en un sistema de gestión.
 * Contiene información básica como nombre, descripción, precio, stock, categoría y URL de imagen.
 */
public class Producto {

    private String nombre;
    private String descripcion;
    private int precio;
    private int stock;
    private String categoria;
    private String URL;
    
    /**
     * Constructor para crear un producto sin descripción.
     * @param nombre Nombre del producto
     * @param precio Precio del producto en unidades monetarias
     * @param stock Cantidad disponible en inventario
     * @param categoria Categoría a la que pertenece el producto
     * @param URL Ruta de la imagen del producto
     */
    public Producto(String nombre, int precio, int stock, String categoria, String URL)
    {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.URL = URL;
    }
    
    /**
     * Constructor para crear un producto con todos los atributos.
     * @param nombre Nombre del producto
     * @param descripcion Descripción detallada del producto
     * @param precio Precio del producto en unidades monetarias
     * @param stock Cantidad disponible en inventario
     * @param categoria Categoría a la que pertenece el producto
     * @param URL Ruta de la imagen del producto
     */
    public Producto(String nombre, String descripcion, int precio, int stock, String categoria, String URL)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.URL = URL;
    }
    
    // Getters
    
    /**
     * Obtiene el nombre del producto.
     * @return Nombre del producto
     */
    public String getNombre() 
    { 
        return nombre; 
    }
    
    /**
     * Obtiene el precio del producto.
     * @return Precio del producto
     */
    public int getPrecio() 
    {
        return precio; 
    }
    
    /**
     * Obtiene la categoría del producto.
     * @return Categoría del producto
     */
    public String getCategoria() 
    { 
        return categoria; 
    }

    /**
     * Obtiene la URL de la imagen del producto.
     * @return Ruta de la imagen del producto
     */
    public String getURL() {
        return URL;
    }

    /**
     * Establece la URL de la imagen del producto.
     * @param uRL Nueva ruta de la imagen del producto
     */
    public void setURL(String uRL) {
        URL = uRL;
    }

    /**
     * Obtiene el stock disponible del producto.
     * @return Cantidad en stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Establece el stock disponible del producto.
     * @param stock Nueva cantidad en stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Obtiene la descripción del producto.
     * @return Descripción del producto
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del producto.
     * @param descripción Nueva descripción del producto
     */
    public void setDescripcion(String descripción) {
        this.descripcion = descripción;
    }
    
    /**
     * Método main para pruebas básicas de la clase Producto.
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        Producto producto = new Producto("Producto", 100, 5, "Categoría", "/img.jpg");
        
        producto.setDescripcion("Nueva descripción");
        producto.setStock(10);
        producto.setURL("/nueva-img.jpg");

        System.out.println(producto.getDescripcion());
        System.out.println(producto.getStock() == 10);
        System.out.println(producto.getURL() == "/nueva-img.jpg");
    }
}