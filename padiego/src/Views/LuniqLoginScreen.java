package Views;

import javax.swing.*;
import Utils.ConexionDB;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LuniqLoginScreen extends JFrame {

    private static final long serialVersionUID = 1L;
	private ConexionDB conexionDB = new ConexionDB(); // Instancia para acceder a la base de datos

    public LuniqLoginScreen() {
        setTitle("LUNIQ - Tienda Online");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Logo
        ImageIcon logoIcon = new ImageIcon(LuniqLoginScreen.class.getResource("/Views/L-REDI.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
        logoLabel.setBounds(289, 32, 60, 60);
        getContentPane().add(logoLabel);

        // Título
        JLabel title = new JLabel("Iniciar sesión o registrarse");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setBounds(289, 94, 300, 30);
        getContentPane().add(title);

        JLabel subtitle = new JLabel("En un periquete");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setBounds(289, 125, 200, 20);
        getContentPane().add(subtitle);

        // Campo email
        JLabel emailLabel = new JLabel("Correo electrónico *");
        emailLabel.setBounds(289, 177, 200, 20);
        getContentPane().add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(289, 209, 300, 35);
        getContentPane().add(emailField);

        // Campo contraseña
        JLabel passwordLabel = new JLabel("Contraseña *");
        passwordLabel.setBounds(289, 256, 200, 20);
        getContentPane().add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(289, 288, 300, 35);
        getContentPane().add(passwordField);

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
                        JOptionPane.showMessageDialog(null, "Usuario no encontrado. Redirigiendo a registro...");
                        dispose(); // Cierra esta ventana
                        new LuniqRegistroScreen().setVisible(true); // Abre la ventana de registro
                    }
                }
            }
        });

        // Términos y política
        JLabel terms = new JLabel("Al crear una cuenta, aceptas nuestros Términos de uso.<br>Consulta nuestra Política de privacidad.");
        terms.setBounds(273, 350, 335, 60);
        terms.setFont(new Font("SansSerif", Font.PLAIN, 12));
        getContentPane().add(terms);

        // Aviso legal y política en el footer
        JLabel legal = new JLabel("Aviso legal");
        legal.setFont(new Font("SansSerif", Font.PLAIN, 11));
        legal.setBounds(333, 503, 70, 20);
        getContentPane().add(legal);

        JLabel privacy = new JLabel("Política de privacidad");
        privacy.setFont(new Font("SansSerif", Font.PLAIN, 11));
        privacy.setBounds(413, 503, 140, 20);
        getContentPane().add(privacy);

        setVisible(true);
    }

    public static void main(String[] args) {
        new LuniqLoginScreen();
    }
}
