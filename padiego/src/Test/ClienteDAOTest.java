package Test;

import Model.Cliente;
import Paquete_DAO.ClienteDAO;

import java.sql.SQLException;
import java.util.List;


public class ClienteDAOTest {

	    public static void main(String[] args) {
	        Paquete_DAO.ClienteDAO clienteDAO = new Paquete_DAO.ClienteDAO();
	        
	        // Insertar un cliente
	        Model.Cliente cliente = new Model.Cliente();
	        cliente.setNombre("Juan");
	        cliente.setApellido("Pérez");
	        cliente.setEmail("juanperez@example.com");
	        cliente.setTelefono("123456789");
	        cliente.setCiudad("Madrid");
	        cliente.setPais("España");
	        cliente.setCodigoPostal("28001");

	        try {
	            clienteDAO.insertar(cliente);
	            System.out.println("Cliente insertado correctamente");

	            // Listar clientes
	            List<ClienteDAO> clientes = clienteDAO.listar();
	            clientes.forEach(c -> System.out.println(c.getNombre()));
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}