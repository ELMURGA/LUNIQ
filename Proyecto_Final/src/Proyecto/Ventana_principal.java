package Proyecto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana_principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_principal frame = new Ventana_principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana_principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTexto1 = new JLabel("Bienvenido a nuestra tienda, que desea hacer?");
		lblTexto1.setBounds(114, 45, 228, 28);
		contentPane.add(lblTexto1);
		
		JButton btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.setBounds(149, 153, 115, 21);
		contentPane.add(btnIniciarSesion);
		
		JButton btnRegistrarme = new JButton("Registrarme");
		btnRegistrarme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegistrarme.setBounds(149, 79, 113, 21);
		contentPane.add(btnRegistrarme);
		
		JLabel lblNewLabel = new JLabel("¿Ya tienes cuenta?");
		lblNewLabel.setBounds(162, 130, 115, 13);
		contentPane.add(lblNewLabel);
	}
}
