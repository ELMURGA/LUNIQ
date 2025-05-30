package ProyectoTests;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Test;

import Proyecto.Usuario;

class UsuarioTest {

    @Test
    void testCreacionUsuario() {
        Date fecha = Date.valueOf("2025-05-23");
        Usuario usuario = new Usuario(19, "u", "u", "u", "u", 
                                      "u", "u", fecha);
        
        assertEquals(19, usuario.getIdUsuario());
        assertEquals("u", usuario.getNombreUsuario());
        assertEquals("u", usuario.getNombre());
        assertEquals("u", usuario.getEmail());
    }

    @Test
    void testSettersUsuario() {
    	Date d= Date.valueOf("2025-05-23");
        Usuario usuario = new Usuario(19, "u", "u", "u", "u", "u", "u", d);
        
        usuario.setNombre("o");
        usuario.setApellido("o");
        usuario.setEmail("o");
        
        assertEquals("o", usuario.getNombre());
        assertEquals("o", usuario.getApellido());
        assertEquals("o", usuario.getEmail());
    }
}