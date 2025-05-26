package Proyecto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
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
 * Clase que representa la ventana principal de la tienda online.
 * Muestra una lista de productos disponibles y permite acceder al perfil del usuario.
 * Esta clase extiende JFrame para crear la interfaz gráfica.
 * 
 * @author [Diego Capellán y Alejandro Hernández]
 * @version 1.0
 */
public class Ventana_Tienda_3 extends JFrame {
    private final ClaseConexion conexion;
    private final String nombreUsuarioActual;
    
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    /**
     * Método principal para iniciar la aplicación.
     * Crea una conexión a la base de datos y muestra la ventana de la tienda.
     * 
     * @param args Argumentos de la línea de comandos (no se utilizan)
     */
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

    /**
     * Constructor de la ventana de la tienda.
     * Configura la interfaz gráfica, muestra los productos y añade funcionalidad.
     * 
     * @param conexion Objeto de conexión a la base de datos
     * @param nombreUsuario Nombre del usuario que ha iniciado sesión
     */
    public Ventana_Tienda_3(ClaseConexion conexion, String nombreUsuario) {
        this.conexion = conexion;
        this.nombreUsuarioActual = nombreUsuario;
        
        // Configuración básica de la ventana
        setTitle("Tienda Online - " + nombreUsuario);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 700);
        
        // Panel principal
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Panel de título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBounds(70, 10, 575, 30);
        panelTitulo.setBackground(new Color(70, 130, 180));
        panelTitulo.setBorder(BorderFactory.createLineBorder(new Color(50, 110, 160), 2));
        contentPane.add(panelTitulo);
        
        JLabel lblTitulo = new JLabel("Nuestros Productos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        panelTitulo.add(lblTitulo);
        
        // Botón de perfil
        JButton btnPerfil = new JButton("Mi Perfil");
        btnPerfil.setFont(new Font("Arial", Font.BOLD, 12));
        btnPerfil.setBackground(new Color(70, 130, 180));
        btnPerfil.setForeground(Color.WHITE);
        btnPerfil.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(50, 110, 160), 2),
            new EmptyBorder(5, 10, 5, 10)
        ));
        btnPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ventana_Perfil_4 perfil = new Ventana_Perfil_4(conexion, nombreUsuarioActual);
                perfil.setVisible(true);
                dispose();
            }
        });
        btnPerfil.setBounds(650, 110, 100, 30);
        btnPerfil.setFocusPainted(false);
        contentPane.add(btnPerfil);
        
        // ScrollPane para la lista de productos
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(70, 50, 575, 600);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(5, 5, 5, 5)
        ));
        scrollPane.getViewport().setBackground(Color.WHITE);
        contentPane.add(scrollPane);
        
        // Panel contenedor principal con GridLayout de 2 columnas
        JPanel panelContenedor = new JPanel(new GridLayout(0, 2, 20, 20));
        panelContenedor.setBackground(Color.WHITE);
        panelContenedor.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Obtener productos de la base de datos
        ArrayList<Producto> productos = conexion.sacarProductos();
        
        // Crear paneles para cada producto
        for (Producto producto : productos) {
            JPanel panelProducto = new JPanel();
            panelProducto.setLayout(new BorderLayout(0, 10));
            panelProducto.setPreferredSize(new Dimension(250, 300));
            panelProducto.setBackground(Color.WHITE);
            panelProducto.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1),
                new EmptyBorder(10, 10, 10, 10)
            ));
            
            // Imagen del producto
            ImageIcon imagenIcon = new ImageIcon(getClass().getResource(producto.getURL()));
            Image imagen = imagenIcon.getImage().getScaledInstance(230, 230, Image.SCALE_SMOOTH);
            JLabel lblImagen = new JLabel(new ImageIcon(imagen));
            lblImagen.setHorizontalAlignment(JLabel.CENTER);
            lblImagen.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 5));
            
            // Botón del producto
            JButton btnNombreProducto = new JButton(producto.getNombre());
            btnNombreProducto.setFont(new Font("Arial", Font.BOLD, 12));
            btnNombreProducto.setBackground(new Color(70, 130, 180));
            btnNombreProducto.setForeground(Color.WHITE);
            btnNombreProducto.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            btnNombreProducto.setFocusPainted(false);
            
            btnNombreProducto.addActionListener(e -> {
                Ventana_DetalleProducto detalle = new Ventana_DetalleProducto(
                    producto, this.conexion, this.nombreUsuarioActual);
                detalle.setVisible(true);
            });
            
            panelProducto.add(lblImagen, BorderLayout.CENTER);
            panelProducto.add(btnNombreProducto, BorderLayout.SOUTH);
            
            panelContenedor.add(panelProducto);
        }
        
        // Añadir panel contenedor al scroll
        scrollPane.setViewportView(panelContenedor);
        
        // Logo de la aplicación
        JLabel lblLogo = new JLabel();
        ImageIcon logoIcon = new ImageIcon(Ventana_Tienda_3.class.getResource("/Proyecto/logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(logoImg));
        lblLogo.setBounds(650, 30, 100, 100);
        lblLogo.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(70, 130, 180), 3),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        contentPane.add(lblLogo);
    }
}