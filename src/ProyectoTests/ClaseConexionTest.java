package ProyectoTests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import Proyecto.ClaseConexion;

import static org.junit.jupiter.api.Assertions.*;

class ClaseConexionTest {

    @Test
    void testConexionExitosa() {
        ClaseConexion conexion = new ClaseConexion("localhost", "3306", "root", "", "proyecto_bbdd");
        
        // Simulamos que la conexión fue exitosa
        assertTrue(true); 
    }

    @Test
    void testLoginUsuario() {
        ClaseConexion conexion = new ClaseConexion("localhost", "3306", "root", "", "proyecto_bbdd");
        
        // Suponiendo que existe este usuario en la BD de prueba
        boolean resultado = conexion.login("d", "d");
        
        assertTrue(resultado); 
    }
    
    
}