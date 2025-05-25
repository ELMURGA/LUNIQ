package Proyecto;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.Icon;

/**
 * Clase que representa la ventana de registro de usuarios para la aplicación LUNIQ
 * Permite a los usuarios crear una nueva cuenta proporcionando nombre, email y contraseña
 */
public class Ventana_Registro_2 extends JFrame {
    // Conexión a la base de datos
    private final ClaseConexion conexion;
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Método principal para ejecutar la ventana de registro de forma independiente
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        // Crear conexión a la base de datos
        ClaseConexion conexion = new ClaseConexion("localhost", "3306", "root", "", "proyecto_bbdd");
        
        // Si la conexión es exitosa, mostrar la ventana
        if (conexion.success()) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Ventana_Registro_2 frame = new Ventana_Registro_2(conexion);
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            // Podríamos mostrar un mensaje de error si la conexión falla
        }
    }

    /**
     * Constructor que crea la ventana de registro
     * @param cn Objeto de conexión a la base de datos
     */
    public Ventana_Registro_2(ClaseConexion cn) {
        this.conexion = cn;
        
        // Configuración básica de la ventana
        setTitle("Registro - LUNIQ");
        setBounds(100, 100, 800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Título principal
        JLabel title = new JLabel("Registrarse");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBounds(207, 112, 300, 30);
        getContentPane().add(title);

        // Subtítulo
        JLabel subtitle = new JLabel("En un periquete");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subtitle.setBounds(207, 142, 200, 20);
        getContentPane().add(subtitle);
        
        // Campo para el nombre de usuario
        JLabel nameLabel = new JLabel("Nombre de usuario *");
        nameLabel.setBounds(207, 342, 200, 20);
        getContentPane().add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(207, 362, 400, 35);
        nameField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        getContentPane().add(nameField);
        
        // Campo para el correo electrónico
        JLabel emailLabel = new JLabel("Correo electrónico *");
        emailLabel.setBounds(207, 182, 200, 20);
        getContentPane().add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(207, 202, 400, 35);
        emailField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        getContentPane().add(emailField);

        // Campo para la contraseña
        JLabel passwordLabel = new JLabel("Contraseña *");
        passwordLabel.setBounds(207, 252, 200, 20);
        getContentPane().add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(207, 272, 400, 35);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        getContentPane().add(passwordField);

        // Información sobre requisitos de contraseña
        JLabel passwordInfo = new JLabel("ⓘ Tu contraseña debe tener al menos 8 caracteres");
        passwordInfo.setFont(new Font("SansSerif", Font.PLAIN, 11));
        passwordInfo.setBounds(207, 312, 300, 15);
        getContentPane().add(passwordInfo);
        
        // Botón de aplicar/registrarse 
        JButton btnAplicar = new JButton("REGISTRARSE");
        btnAplicar.setFont(new Font("SansSerif", Font.BOLD, 14)); // Fuente más grande y en negrita
        btnAplicar.setBackground(new Color(0, 120, 215)); // Color azul moderno
        btnAplicar.setForeground(Color.WHITE); // Texto en blanco
        btnAplicar.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25)); // Padding interno
        btnAplicar.setBounds(300, 430, 200, 40); // Tamaño aumentado
        
        btnAplicar.addActionListener(e -> {
            // Obtener datos del formulario
            String usuario = nameField.getText();
            String correo = emailField.getText();
            String contrasena = passwordField.getText();
            
            // Insertar usuario en la base de datos
            this.conexion.insertUser(usuario, correo, contrasena);
                
            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(null, "Cuenta creada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            // Abrir ventana de inicio de sesión
            Ventana_InicioSesion_2 inicioSes = new Ventana_InicioSesion_2(this.conexion);
            inicioSes.setVisible(true);
            
            // Cerrar esta ventana
            dispose();
        });
        getContentPane().add(btnAplicar);
        
        //términos de uso
        JLabel terms = new JLabel("<html><center>Al crear una cuenta, aceptas nuestros <a>Términos de uso</a>.<br>Consulta nuestra <a>Política de privacidad</a>.</center></html>");
        terms.setFont(new Font("SansSerif", Font.PLAIN, 11));
        terms.setBounds(253, 487, 313, 40);
        getContentPane().add(terms);
        
        // Logo de la aplicación
        JLabel logoLabel = new JLabel(new ImageIcon(getClass().getResource("logo.png")));
        logoLabel.setBounds(207, 39, 75, 61);
        getContentPane().add(logoLabel);
    }
}