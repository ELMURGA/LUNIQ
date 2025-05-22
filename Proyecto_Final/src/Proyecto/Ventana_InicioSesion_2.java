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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana_InicioSesion_2 extends JFrame {
	private final ClaseConexion conexion;
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		ClaseConexion conexion =  new ClaseConexion("localhost", "3306", "root", "", "proyecto_bbdd");
		
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
		} else {}
	}
	
	/**
	 * Create the frame.
	 */
	public Ventana_InicioSesion_2(ClaseConexion conexion) {
		this.conexion = conexion;
		
		
		
		setTitle("LUNIQ - Tienda Online");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);  // ¡Esta línea es crucial!
        
        // Título
        JLabel title = new JLabel("Iniciar sesión \n");
        title.setBounds(338, 92, 163, 30);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        getContentPane().add(title);

        JLabel subtitle = new JLabel("Bienvenido a Luniq, a continuación inicie sesión:");
        subtitle.setBounds(250, 122, 6000, 20);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        getContentPane().add(subtitle);
		
        JLabel emailLabel = new JLabel("Nombre de usuario"); 
        emailLabel.setBounds(217, 180, 200, 20);
        getContentPane().add(emailLabel);
		
		JTextField userField = new JTextField();
		userField.setBounds(217, 212, 400, 35);
		userField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        getContentPane().add(userField);
		
     // Contraseña
        JLabel passwordLabel = new JLabel("Contraseña");
        passwordLabel.setBounds(217, 255, 200, 20);
        getContentPane().add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(217, 275, 400, 35);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        getContentPane().add(passwordField);
        
        JLabel terms = new JLabel("<html><center>Al crear una cuenta, aceptas nuestros <a>Términos de uso</a>.<br>Consulta nuestra <a>Política de privacidad</a>.</center></html>");
        terms.setFont(new Font("SansSerif", Font.PLAIN, 11));
        terms.setBounds(262, 448, 320, 60);
        getContentPane().add(terms);
		
		
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(e -> {
				String nombreUsuario = userField.getText();
				String contrasena = passwordField.getText();
				
				boolean isLogging = this.conexion.login(nombreUsuario, contrasena);
				
				if (isLogging) {
					System.out.println("logeado");
					Ventana_Tienda_3 vt =new Ventana_Tienda_3(conexion);
					vt.setVisible(true);
					dispose();
				} else {
					
				}
				
		});
		btnConfirmar.setBounds(350, 400, 85, 21);
		getContentPane().add(btnConfirmar);
	}
}
