package ProyectoTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Proyecto.Producto;

public class ProductoTest {

	@Test
    void testCreacionProductoBasico() {
        Producto producto = new Producto("Pantalon Nike", 35, 25, "Pantalones", "PantalonNike.jpg");
        
        assertEquals("Pantalon Nike", producto.getNombre());
        assertEquals(35, producto.getPrecio());
        assertEquals("Pantalones", producto.getCategoria());
        assertEquals("PantalonNike.jpg", producto.getURL());
    }

    @Test
    void testCreacionProductoCompleto() {
        Producto producto = new Producto("Pantalon Nike", "Pantalón nike básico de color rosa", 
                                        35, 25, "Pantalones", "PantalonNike.jpg");
        
        assertEquals("Pantalon Nike", producto.getNombre());
        assertEquals("Pantalón nike básico de color rosa", producto.getDescripcion());
        assertEquals(35, producto.getPrecio());
    }
	
}
