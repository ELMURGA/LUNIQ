package Proyecto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que representa la ventana de tienda de la aplicación.
 * Versión con mejoras estéticas manteniendo la misma funcionalidad.
 */
public class Ventana_Tienda_3 extends JFrame {
    private final ClaseConexion conexion;
    private final String nombreUsuarioActual;
    
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    public static void main(String[] args) {
        ClaseConexion conexion = new ClaseConexion("localhost", "3306", "root", "", "proyecto_bbdd");
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ventana_Tienda_3 frame = new Ventana_Tienda_3(conexion, "prueba");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Ventana_Tienda_3(ClaseConexion conexion, String nombreUsuario) {
        this.conexion = conexion;
        this.nombreUsuarioActual = nombreUsuario;
        
        // Configuración básica de la ventana
        setTitle("Tienda Online");  // Título descriptivo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 700);
        
        // Panel principal con fondo blanco
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));  // Más margen
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Botón de perfil con mejor estilo
        JButton btnPerfil = new JButton("Mi Perfil");
        btnPerfil.setFont(new Font("Arial", Font.PLAIN, 12));
        btnPerfil.setBackground(new Color(240, 240, 240));  // Gris muy claro
        btnPerfil.setBorder(new LineBorder(new Color(200, 200, 200), 1));  // Borde sutil
        btnPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ventana_Perfil_4 perfil = new Ventana_Perfil_4(conexion, nombreUsuarioActual);
                perfil.setVisible(true);
                dispose();
            }
        });
        btnPerfil.setBounds(671, 110, 85, 25);  
        contentPane.add(btnPerfil);
        

        JPanel panelPadre = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        panelPadre.setBackground(Color.WHITE);
        
        // ScrollPane con estilo más limpio
        JScrollPane scrollPane = new JScrollPane(panelPadre);
        scrollPane.setBounds(73, 36, 572, 600);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());  // Sin borde
        scrollPane.getViewport().setBackground(Color.WHITE);
        contentPane.add(scrollPane);
        
        // Obtener productos
        ArrayList<Producto> productos = conexion.sacarProductos();
        
     // Panel para cada producto
        for (Producto producto : productos) {
            JPanel panelProducto = new JPanel();
            panelProducto.setLayout(new BorderLayout());
            panelProducto.setPreferredSize(new Dimension(500, 550));
            panelProducto.setBackground(Color.WHITE);
            panelProducto.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(230, 230, 230), 1),
                new EmptyBorder(10, 10, 10, 10)
            ));
            
            // Imagen del producto
            ImageIcon imagenIcon = new ImageIcon(getClass().getResource(producto.getURL()));
            Image imagen = imagenIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
            JLabel lblImagen = new JLabel(new ImageIcon(imagen));
            lblImagen.setHorizontalAlignment(JLabel.CENTER);
            
            // Botón con nombre del producto
            JButton btn = new JButton(producto.getNombre());
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            btn.setBackground(Color.WHITE);
            btn.setBorder(new LineBorder(new Color(200, 200, 200), 1));
            btn.setFocusPainted(false);
            
            // Añadir ActionListener al botón
            btn.addActionListener(e -> {
                Ventana_DetalleProducto detalle = new Ventana_DetalleProducto(
                    producto, this.conexion, this.nombreUsuarioActual);
                detalle.setVisible(true);
            });
            
            panelProducto.add(lblImagen, BorderLayout.CENTER);
            panelProducto.add(btn, BorderLayout.SOUTH);
            
            panelPadre.add(panelProducto);
        }
        
        // Logo 
        JLabel lblLogo = new JLabel();
        lblLogo.setIcon(new ImageIcon(Ventana_Tienda_3.class.getResource("/Proyecto/logo.png")));
        lblLogo.setBounds(681, 36, 63, 64);
        lblLogo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));  // Margen alrededor
        contentPane.add(lblLogo);
        
        // Etiqueta de título
        JLabel lblTitulo = new JLabel("Nuestros Productos:");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBounds(73, 10, 200, 20);
        contentPane.add(lblTitulo);
    }
}