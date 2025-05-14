package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Utils.ConexionDB;

public class LuniqRegistroScreen extends JFrame {

    private static final long serialVersionUID = 1L;
	private ConexionDB conexionDB = new ConexionDB();

    public LuniqRegistroScreen() {
        setTitle("Registro - LUNIQ");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel title = new JLabel("Registrarse");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBounds(207, 112, 300, 30);
        getContentPane().add(title);

        JLabel subtitle = new JLabel("En un periquete");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subtitle.setBounds(207, 142, 200, 20);
        getContentPane().add(subtitle);

        // Correo electrónico
        JLabel emailLabel = new JLabel("Correo electrónico *");
        emailLabel.setBounds(207, 182, 200, 20);
        getContentPane().add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(207, 202, 400, 35);
        emailField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        getContentPane().add(emailField);

        // Contraseña
        JLabel passwordLabel = new JLabel("Contraseña *");
        passwordLabel.setBounds(207, 252, 200, 20);
        getContentPane().add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(207, 272, 400, 35);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        getContentPane().add(passwordField);

        JLabel passwordInfo = new JLabel("ⓘ Tu contraseña debe tener al menos 8 caracteres");
        passwordInfo.setFont(new Font("SansSerif", Font.PLAIN, 11));
        passwordInfo.setBounds(207, 312, 300, 15);
        getContentPane().add(passwordInfo);

        // Nombre
        JLabel nameLabel = new JLabel("Nombre *");
        nameLabel.setBounds(207, 342, 200, 20);
        getContentPane().add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(207, 362, 400, 35);
        nameField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        getContentPane().add(nameField);

        // Apellidos
        JLabel surnameLabel = new JLabel("Apellidos *");
        surnameLabel.setBounds(207, 402, 200, 20);
        getContentPane().add(surnameLabel);

        JTextField surnameField = new JTextField();
        surnameField.setBounds(207, 422, 400, 35);
        surnameField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        getContentPane().add(surnameField);

        // Aviso legal
        JLabel terms = new JLabel("<html><center>Al crear una cuenta, aceptas nuestros <a href='#'>Términos de uso</a>.<br>Consulta nuestra <a href='#'>Política de privacidad</a>.</center></html>");
        terms.setFont(new Font("SansSerif", Font.PLAIN, 11));
        terms.setBounds(253, 487, 313, 40);
        getContentPane().add(terms);
        
        JLabel logoLabel = new JLabel(new ImageIcon(LuniqRegistroScreen.class.getResource("/Views/L-REDI.png")));
        logoLabel.setBounds(207, 54, 75, 61);
        getContentPane().add(logoLabel);

        surnameField.addKeyListener(new KeyAdapter() {
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String nombre = nameField.getText().trim();
            String apellidos = surnameField.getText().trim();
            String correo = emailField.getText().trim();
            String contraseña = new String(passwordField.getPassword());

            if (nombre.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || contraseña.length() < 8) {
                JOptionPane.showMessageDialog(null, "Rellena todos los campos correctamente.");
                return;
            }

            if (conexionDB.existeUsuario(correo)) {
                JOptionPane.showMessageDialog(null, "Este correo ya está registrado.");
                return;
            }

            if (conexionDB.insertarUsuario(nombre, apellidos, correo)) {
                JOptionPane.showMessageDialog(null, "¡Registro completado!");
                dispose(); // Cierra la pantalla
                // Redirige a la pantalla de inicio de sesión
                new LuniqLoginScreen(); // Asegúrate de que esta clase esté implementada
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar. Inténtalo más tarde.");
            }
        }
    }
});
        setVisible(true);
    }
}
