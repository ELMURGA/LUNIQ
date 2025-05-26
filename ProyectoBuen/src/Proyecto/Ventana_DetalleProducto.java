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
import javax.swing.border.LineBorder;
import javax.swing.BorderFactory;

/**
 * Clase que representa una ventana gráfica para mostrar los detalles de un producto.
 * Esta ventana muestra la imagen, nombre, descripción y precio del producto,
 * junto con botones para volver a la tienda o añadir el producto al carrito.
 * 
 * @author [Diego Capellán Fernández]
 * @version 1.0
 */
public class Ventana_DetalleProducto extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor que crea la ventana de detalles del producto.
     * 
     * @param producto El producto cuyos detalles se van a mostrar
     * @param conexion La conexión a la base de datos
     * @param nombreUsuario El nombre del usuario que está viendo el producto
     */
    public Ventana_DetalleProducto(Producto producto, ClaseConexion conexion, String nombreUsuario) {
        // Configuración de la ventana
        setTitle("Detalles del Producto - " + producto.getNombre());
        setBounds(100, 100, 800, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal con fondo claro
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout(15, 15));
        setContentPane(contentPane);
        
        // Botón Volver 
        JButton btnVolver = new JButton("Volver a la tienda");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
        btnVolver.setBackground(new Color(70, 130, 180));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(50, 110, 160), 2),
            new EmptyBorder(5, 15, 5, 15)
        ));
        btnVolver.setFocusPainted(false);
        btnVolver.addActionListener(e -> {
            Ventana_Tienda_3 tienda = new Ventana_Tienda_3(conexion, nombreUsuario);
            tienda.setVisible(true);
            dispose();
        });
        contentPane.add(btnVolver, BorderLayout.NORTH);
        
        // Imagen del producto con marco
        ImageIcon imagenIcon = new ImageIcon(getClass().getResource(producto.getURL()));
        Image imagenRedimensionada = imagenIcon.getImage().getScaledInstance(550, 550, Image.SCALE_SMOOTH);
        JLabel lblImagen = new JLabel(new ImageIcon(imagenRedimensionada));
        lblImagen.setHorizontalAlignment(JLabel.CENTER);
        lblImagen.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(10, 10, 10, 10)
        ));
        contentPane.add(lblImagen, BorderLayout.CENTER);
        
        // Panel de información con fondo blanco y sombra sutil
        JPanel panelInfo = new JPanel();
        panelInfo.setBackground(Color.WHITE);
        panelInfo.setLayout(new BorderLayout(10, 10));
        panelInfo.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 220, 220), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        // Nombre del producto
        JLabel lblNombre = new JLabel(producto.getNombre());
        lblNombre.setFont(new Font("Arial", Font.BOLD, 20));
        lblNombre.setForeground(new Color(70, 130, 180));
        panelInfo.add(lblNombre, BorderLayout.NORTH);
        
        // Descripción del producto
        JLabel lblDescripcion = new JLabel("<html><div style='text-align: justify;'>" + producto.getDescripcion() + "</div></html>");
        lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 14));
        panelInfo.add(lblDescripcion, BorderLayout.CENTER);
        
        // Precio del producto 
        JLabel lblPrecio = new JLabel("Precio: " + producto.getPrecio() + "€");
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 18));
        lblPrecio.setForeground(new Color(40, 100, 150));
        lblPrecio.setHorizontalAlignment(JLabel.RIGHT);
        panelInfo.add(lblPrecio, BorderLayout.SOUTH);
        
        contentPane.add(panelInfo, BorderLayout.SOUTH);
        
        // Botón de compra a falta de darle funcionalidad
        JButton btnComprar = new JButton("Añadir al carrito");
        btnComprar.setFont(new Font("Arial", Font.BOLD, 14));
        btnComprar.setBackground(new Color(76, 175, 80));
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btnComprar.setFocusPainted(false);
        panelInfo.add(btnComprar, BorderLayout.EAST);
    }
}