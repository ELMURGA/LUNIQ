package Proyecto;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que representa la ventana de inicio de sesión para la aplicación LUNIQ.
 * Permite a los usuarios introducir sus credenciales para acceder al sistema.
 * 
 * @author Diego Capellán y Alejandro Murga
 * @version 1.0
 */
public class Ventana_InicioSesion_2 extends JFrame {
    /** Objeto para la conexión con la base de datos */
    private final ClaseConexion conexion;
    
    /** Identificador único para la serialización */
    private static final long serialVersionUID = 1L;

    /**
     * Método principal que inicia la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        // Establece conexión con la base de datos
        ClaseConexion conexion = new ClaseConexion("localhost", "3306", "root", "", "proyecto_bbdd");
        
        if (conexion.success()) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Ventana_InicioSesion_2 frame = new Ventana_InicioSesion_2(conexion);
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    
    /**
     * Constructor que crea la ventana de inicio de sesión.
     * 
     * @param conexion Objeto de conexión a la base de datos que se utilizará
     */
    public Ventana_InicioSesion_2(ClaseConexion conexion) {
        this.conexion = conexion;
        
        // Configuración básica de la ventana
        setTitle("LUNIQ - Tienda Online");
        setBounds(100, 100, 800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);
        
        // Título principal
        JLabel title = new JLabel("Iniciar sesión");
        title.setBounds(338, 92, 163, 30);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        getContentPane().add(title);

        // Subtítulo
        JLabel subtitle = new JLabel("Bienvenido a Luniq, a continuación inicie sesión:");
        subtitle.setBounds(250, 122, 400, 20);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        getContentPane().add(subtitle);
        
        // Campo para el nombre de usuario
        JLabel emailLabel = new JLabel("Nombre de usuario"); 
        emailLabel.setBounds(217, 180, 200, 20);
        getContentPane().add(emailLabel);
        
        JTextField userField = new JTextField();
        userField.setBounds(217, 212, 400, 35);
        userField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        getContentPane().add(userField);
        
        // Campo para la contraseña
        JLabel passwordLabel = new JLabel("Contraseña");
        passwordLabel.setBounds(217, 255, 200, 20);
        getContentPane().add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(217, 275, 400, 35);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        getContentPane().add(passwordField);
        
        // Términos y condiciones
        JLabel terms = new JLabel("<html><center>Al crear una cuenta, aceptas nuestros <a>Términos de uso</a>.<br>Consulta nuestra <a>Política de privacidad</a>.</center></html>");
        terms.setFont(new Font("SansSerif", Font.PLAIN, 11));
        terms.setBounds(262, 448, 320, 60);
        getContentPane().add(terms);
        
        // Botón de confirmar
        JButton btnConfirmar = new JButton("INICIAR SESIÓN");
        btnConfirmar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnConfirmar.setBackground(new Color(46, 125, 50));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btnConfirmar.setBounds(300, 350, 200, 40);
        
        // Acción del botón de confirmar
        btnConfirmar.addActionListener(e -> {
            String nombreUsuario = userField.getText();
            String contrasena = new String(passwordField.getPassword());
            
            boolean isLogging = this.conexion.login(nombreUsuario, contrasena);
            
            if (isLogging) {
                // Si el login es correcto, abre la ventana de tienda
                Ventana_Tienda_3 vt = new Ventana_Tienda_3(conexion, nombreUsuario);
                vt.setVisible(true);
                dispose(); // Cierra esta ventana
            } else {
                // Muestra mensaje de error si el login falla
                JOptionPane.showMessageDialog(this, 
                    "Usuario o contraseña incorrectos", 
                    "Error de login", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        getContentPane().add(btnConfirmar);
    }
}