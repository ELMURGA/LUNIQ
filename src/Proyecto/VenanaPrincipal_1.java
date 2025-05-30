package Proyecto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

/**
 * Clase que representa la ventana principal de la aplicación Luniq.
 * Permite al usuario elegir entre registrarse, iniciar sesión o salir de la aplicación.
 * 
 * @author [Diego Capellán y Alejandro Hernandez]
 * @version 1.0
 */
public class VenanaPrincipal_1 extends JFrame {
    // Conexión a la base de datos
    private final ClaseConexion conexion;
    
    // Identificador de versión para serialización
    private static final long serialVersionUID = 1L;
    
    // Panel principal de la ventana
    private JPanel contentPane;

    /**
     * Método principal que inicia la aplicación.
     * 
     * @param args Argumentos de línea de comandos 
     */
    public static void main(String[] args) {
        // Crear conexión con la base de datos
        ClaseConexion conexion = new ClaseConexion("localhost", "3306", "root", "", "proyecto_bbdd");
        
        // Verificar si la conexión fue exitosa
        if(conexion.success()) {
            // Ejecutar en el hilo de eventos de Swing
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        // Crear y mostrar la ventana principal
                        VenanaPrincipal_1 frame = new VenanaPrincipal_1(conexion);
                        frame.setVisible(true);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, 
                            "Error al iniciar la aplicación: " + e.getMessage(), 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        } else {
            // Mostrar mensaje de error si la conexión falla
            JOptionPane.showMessageDialog(null, 
                "No se pudo establecer conexión con la base de datos.\nLa aplicación se cerrará.", 
                "Error de conexión", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Salir de la aplicación con código de error
        }
    }

    /**
     * Constructor que crea la ventana principal.
     * Configura todos los componentes gráficos y sus listeners.
     * 
     * @param cn Objeto de conexión a la base de datos que se utilizará en la aplicación
     */
    public VenanaPrincipal_1(ClaseConexion cn) {
        this.conexion = cn;
        
        // Configuración básica de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 700);
        setTitle("Luniq - Página Principal");

        // Configuración del panel principal
        contentPane = new JPanel();
        contentPane.setForeground(new Color(0, 0, 0));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(new Color(255, 255, 255));
        setContentPane(contentPane);

        // Título de la ventana
        JLabel lblTitulo = new JLabel("Bienvenido a Luniq, ¿qué desea hacer?");
        lblTitulo.setIcon(new ImageIcon(VenanaPrincipal_1.class.getResource("/Proyecto/logo.png")));
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBorder(new EmptyBorder(10, 0, 20, 0));
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // Panel que contiene los botones principales
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 10, 20)); // 3 filas, 1 columna
        panelBotones.setOpaque(false); // Panel transparente
        contentPane.add(panelBotones, BorderLayout.CENTER);
        
        /**
         * Botón para registrar un nuevo usuario.
         * Abre la ventana de registro y cierra la actual.
         */
        JButton btnRegistro = new JButton("Registrarme");
        btnRegistro.setFont(new Font("Arial", Font.PLAIN, 16));
        btnRegistro.setBackground((new Color(0, 120, 215)));
        btnRegistro.setForeground(Color.WHITE); // Texto blanco
        btnRegistro.setFocusPainted(false); // Eliminar borde de enfoque
        btnRegistro.addActionListener(e -> {
            // Abrir ventana de registro y cerrar esta ventana
            Ventana_Registro_2 vtnR = new Ventana_Registro_2(this.conexion);
            vtnR.setVisible(true);
            dispose(); // Cierra la ventana actual
        });
        panelBotones.add(btnRegistro);

        /**
         * Botón para iniciar sesión.
         * Abre la ventana de inicio de sesión y cierra la actual.
         */
        JButton btnInicioSesion = new JButton("Iniciar sesión");
        btnInicioSesion.setFont(new Font("Arial", Font.PLAIN, 16));
        btnInicioSesion.setBackground(new Color(46, 125, 50)); // Color verde moderno
        btnInicioSesion.setForeground(Color.WHITE); // Texto blanco
        btnInicioSesion.setFocusPainted(false); // Eliminar borde de enfoque
        btnInicioSesion.addActionListener(e -> {
            // Abrir ventana de inicio de sesión y cerrar esta ventana
            Ventana_InicioSesion_2 vtnIS = new Ventana_InicioSesion_2(this.conexion);
            vtnIS.setVisible(true);
            dispose(); // Cierra la ventana actual
        });
        panelBotones.add(btnInicioSesion);

        /**
         * Botón para salir de la aplicación.
         * Cierra la ventana y termina la ejecución del programa.
         */
        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 16));
        btnSalir.setBackground(new Color(220, 53, 69)); // Color rojo
        btnSalir.setForeground(Color.WHITE); // Texto blanco
        btnSalir.setFocusPainted(false); // Eliminar borde de enfoque
        btnSalir.addActionListener(e -> {
                dispose(); // Cierra la ventana
                System.exit(0); // Sale de la aplicación
        });
        panelBotones.add(btnSalir);

        // Pie de página con información de copyright
        JLabel lblFooter = new JLabel("© 2023 Luniq. Todos los derechos reservados.");
        lblFooter.setFont(new Font("Arial", Font.ITALIC, 12));
        lblFooter.setHorizontalAlignment(SwingConstants.CENTER);
        lblFooter.setBorder(new EmptyBorder(20, 0, 0, 0));
        contentPane.add(lblFooter, BorderLayout.SOUTH);
    }
}