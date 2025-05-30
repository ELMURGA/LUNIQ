package ProyectoTests;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Proyecto.Producto;

public class MetodoProductoTest {

	 @Test
	 void testSettersProducto() {
	        Producto producto = new Producto("Producto", 100, 5, "Categoría", "/img.jpg");
	        
	        producto.setDescripcion("Nueva descripción");
	        producto.setStock(10);
	        producto.setURL("/nueva-img.jpg");

	        assertEquals("Nueva descripción", producto.getDescripcion());
	        System.out.println(producto.getStock());
	        System.out.println(producto.getURL());
	        
	    }
	
}
