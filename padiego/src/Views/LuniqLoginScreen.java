package Views;

import javax.swing.*;

import conexion.ConexionDB;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LuniqLoginScreen extends JFrame {

    private static final long serialVersionUID = 1L;
	private ConexionDB conexionDB = new ConexionDB(); // Instancia para acceder a la base de datos
	
	public LuniqLoginScreen() {
        setTitle("LUNIQ - Tienda Online");
        setSize(770, 590);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        // Logo
        ImageIcon logoIcon = new ImageIcon(LuniqLoginScreen.class.getResource("/Views/L-REDI.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        getContentPane().setLayout(null);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
        logoLabel.setBounds(392, 31, 60, 60);
        getContentPane().add(logoLabel);

        // Título
        JLabel title = new JLabel("Iniciar sesión \n");
        title.setBounds(349, 93, 163, 30);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        getContentPane().add(title);

        JLabel subtitle = new JLabel("En un periquete");
        subtitle.setBounds(369, 123, 120, 20);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        getContentPane().add(subtitle);

        // Campo email
        JLabel emailLabel = new JLabel("Correo electrónico ");
        emailLabel.setBounds(248, 177, 200, 20);
        getContentPane().add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(248, 209, 400, 35);
        emailField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        getContentPane().add(emailField);

        
        // Contraseña
        JLabel passwordLabel = new JLabel("Contraseña");
        passwordLabel.setBounds(248, 252, 200, 20);
        getContentPane().add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(248, 272, 400, 35);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        getContentPane().add(passwordField);

        // Listener para el campo de contraseña
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String correo = emailField.getText().trim();
                    String contraseña = new String(passwordField.getPassword());

                    if (correo.isEmpty() || contraseña.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.");
                        return;
                    }

                    if (conexionDB.validarUsuario(correo, contraseña)) {
                        JOptionPane.showMessageDialog(null, "¡Inicio de sesión exitoso!");
                        dispose(); // Cierra la pantalla de inicio de sesión
                        new HomeLuniqScreen(); // Redirige a la pantalla de inicio
                    } else {
                        JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos. Por favor, regístrate.");
                        dispose(); // Cierra la pantalla de inicio de sesión
                        new LuniqRegistroScreen(); // Redirige a la pantalla de registro
                    }
                }
            }
        });

        setVisible(true);

        // Lógica de autenticación al presionar "Enter"
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String correo = emailField.getText().trim();
                    String contrasena = new String(passwordField.getPassword()).trim();

                    if (correo.isEmpty() || contrasena.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, introduce el correo y la contraseña.");
                        return;
                    }

                    if (conexionDB.existeUsuario(correo)) {
                        if (conexionDB.verificarContrasena(correo, contrasena)) {
                            JOptionPane.showMessageDialog(null, "Bienvenido de nuevo.");
                            dispose(); // Cierra esta ventana
                            new HomeLuniqScreen().setVisible(true); // Abre la pantalla principal
                        } else {
                            JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario no encontrado. Redirigiendo a registro");
                        dispose(); // Cierra esta ventana
                        new LuniqRegistroScreen().setVisible(true); // Abre la ventana de registro
                    }
                }
            }
        });

        // Términos y política
        JLabel terms = new JLabel("<html><center>Al crear una cuenta, aceptas nuestros <a>Términos de uso</a>.<br>Consulta nuestra <a>Política de privacidad</a>.</center></html>\"");
        terms.setBounds(283, 350, 335, 60);
        terms.setFont(new Font("SansSerif", Font.PLAIN, 12));
        getContentPane().add(terms);

        // Aviso legal y política en el footer
        JLabel legal = new JLabel("Aviso legal");
        legal.setBounds(349, 503, 70, 20);
        legal.setFont(new Font("SansSerif", Font.PLAIN, 11));
        getContentPane().add(legal);
        
                JLabel privacy = new JLabel("Política de privacidad");
                privacy.setBounds(424, 503, 140, 20);
                privacy.setFont(new Font("SansSerif", Font.PLAIN, 11));
                getContentPane().add(privacy);

        setVisible(true);
    }

    public static void main(String[] args) {
        new LuniqLoginScreen();
    }
}
