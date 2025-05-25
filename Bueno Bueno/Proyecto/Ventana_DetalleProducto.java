package Proyecto;

import java.awt.Font;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.EmptyBorder;

//No hace falta main ya que lo tenemos en la del principio

/**
 * Clase que representa una ventana para mostrar los detalles de un producto seleccionado.
 * Extiende JFrame para crear una interfaz gráfica.
 */
public class Ventana_DetalleProducto extends JFrame {
    
    // Identificador único para la serialización
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor que crea la ventana de detalles del producto.
     * 
     * @param producto El producto cuyos detalles se mostrarán
     * @param conexion La conexión a la base de datos
     * @param nombreUsuario El nombre del usuario actual para mantener la sesión
     */
    public Ventana_DetalleProducto(Producto producto, ClaseConexion conexion, String nombreUsuario) {
        // Configuración básica de la ventana
        setTitle("Detalle del Producto");
        setBounds(100, 100, 800, 700); // Tamaño y posición de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        
        // Panel principal con márgenes
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15)); // Márgenes de 15px
        contentPane.setLayout(new BorderLayout(10, 10)); // Layout con espaciado
        setContentPane(contentPane);
        
        // Sección de imagen del producto
        ImageIcon imagenIcon = new ImageIcon(getClass().getResource(producto.getURL()));

        // Redimensionar la imagen para que se ajuste a la ventana
        Image imagenOriginal = imagenIcon.getImage();
        Image imagenRedimensionada = imagenOriginal.getScaledInstance(500, 500, Image.SCALE_SMOOTH);

        // Crear y configurar el JLabel para mostrar la imagen
        JLabel lblImagen = new JLabel(new ImageIcon(imagenRedimensionada));
        lblImagen.setHorizontalAlignment(JLabel.CENTER); // Centrar la imagen
        contentPane.add(lblImagen, BorderLayout.CENTER); // Añadir al centro del panel
        
        // Panel inferior que contiene la información del producto
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BorderLayout(10, 10)); // Layout con espaciado
        
        // Nombre del producto
        JLabel lblNombre = new JLabel(producto.getNombre());
        lblNombre.setFont(new Font("Arial", Font.BOLD, 18)); // Fuente en negrita
        panelInfo.add(lblNombre, BorderLayout.NORTH); // Añadir en la parte superior
        
        // Descripción del producto
        JLabel lblDescripcion = new JLabel("Descripción: " + producto.getDescripcion());
        lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 14)); // Fuente normal
        panelInfo.add(lblDescripcion, BorderLayout.CENTER); // Añadir en el centro
        
        // Precio del producto
        JLabel lblPrecio = new JLabel("Precio: " + producto.getPrecio() + "€");
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente en negrita
        panelInfo.add(lblPrecio, BorderLayout.SOUTH); // Añadir en la parte inferior
        
        // Añadir el panel de información al panel principal
        contentPane.add(panelInfo, BorderLayout.SOUTH);
        
        // Botón para volver a la tienda
        JButton btnVolver = new JButton("Volver a la tienda");
        btnVolver.addActionListener(e -> {
            // Al hacer clic, abrir la ventana de tienda y cerrar esta
            Ventana_Tienda_3 tienda = new Ventana_Tienda_3(conexion, nombreUsuario);
            tienda.setVisible(true);
            dispose(); // Cierra esta ventana
        });
        contentPane.add(btnVolver, BorderLayout.NORTH); // Añadir en la parte superior
    }
}