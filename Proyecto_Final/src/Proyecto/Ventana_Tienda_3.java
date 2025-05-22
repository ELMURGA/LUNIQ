package Proyecto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;

public class Ventana_Tienda_3 extends JFrame {
	private final ClaseConexion conexion;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ClaseConexion conexion =  new ClaseConexion("localhost", "3306", "root", "", "proyecto_bbdd");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_Tienda_3 frame = new Ventana_Tienda_3(conexion);
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
	public Ventana_Tienda_3(ClaseConexion conexion) {
		this.conexion=conexion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
