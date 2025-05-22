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

public class Ventana_Registro_2 extends JFrame {
	private final ClaseConexion conexion;
	
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ClaseConexion conexion =  new ClaseConexion("localhost", "3306", "root", "", "proyecto_bbdd");
		
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
		} else {}
	}

	/**
	 * Create the frame.
	 */
	public Ventana_Registro_2(ClaseConexion cn) {
		this.conexion=cn;
		
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
		
		// Nombre
        JLabel nameLabel = new JLabel("Nombre de usuario *");
        nameLabel.setBounds(207, 342, 200, 20);
        getContentPane().add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(207, 362, 400, 35);
        nameField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        getContentPane().add(nameField);
		
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
        
        JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.addActionListener(e -> {
				String usuario=nameField.getText();
				String correo=emailField.getText();
				String contrasena=passwordField.getText();
				
				this.conexion.insertUser(usuario, correo, contrasena);
					
				JOptionPane.showMessageDialog(null, "Cuenta creada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);//Mensaje de operacion exitosa
				System.out.println("dkmdm");
				Ventana_InicioSesion_2 inicioSes =new  Ventana_InicioSesion_2(this.conexion);//Llama a la ventana de inicio de sesion
				System.out.println("nsd");
				
				inicioSes.setVisible(true);//Hace visible la ventana que hemos llamado
				System.out.println("j");
				dispose();//Cierra la ventana actual
		});
		btnAplicar.setBounds(375, 434, 85, 21);
        getContentPane().add(btnAplicar);
        
     // Aviso legal
        JLabel terms = new JLabel("<html><center>Al crear una cuenta, aceptas nuestros <a>Términos de uso</a>.<br>Consulta nuestra <a>Política de privacidad</a>.</center></html>");
        terms.setFont(new Font("SansSerif", Font.PLAIN, 11));
        terms.setBounds(253, 487, 313, 40);
        getContentPane().add(terms);
        
        JLabel logoLabel = new JLabel(new ImageIcon(getClass().getResource("logo.png")));
        logoLabel.setBounds(207, 39, 75, 61);
        getContentPane().add(logoLabel);

	}
}