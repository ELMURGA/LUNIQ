package Proyecto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Ventana_Perfil_4 extends JFrame {
	public final ClaseConexion conexion;//Esto es para el this.conexion del metodo de la ventana 
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textID_Usuario;
	private JTextField textNombre;
	private JTextField textEmail;
	private JTextField textContrasena;
	private JTextField textDireccion;
	private JTextField textFechaRegistro;
	private JLabel lblID;
	private JLabel lblNewLabel;
	private JLabel lblCorreo;
	private JLabel lblContrasena;
	private JLabel lblPais;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ClaseConexion cn = new ClaseConexion("localhost", "3306", "root", "", "proyecto_bbdd");
		if(cn.success()) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Ventana_Perfil_4 frame = new Ventana_Perfil_4(cn);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}else {}
	}

	/**
	 * Create the frame.
	 */
	public Ventana_Perfil_4(ClaseConexion cn) {//Por que aqui es ClaseConezion y no conexion
		this.conexion=cn;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textID_Usuario = new JTextField();
		textID_Usuario.setEditable(false);
		textID_Usuario.setBounds(158, 42, 96, 19);
		contentPane.add(textID_Usuario);
		textID_Usuario.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(158, 71, 96, 19);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(158, 100, 96, 19);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		textContrasena = new JTextField();
		textContrasena.setBounds(158, 129, 96, 19);
		contentPane.add(textContrasena);
		textContrasena.setColumns(10);
		
		textDireccion = new JTextField();
		textDireccion.setBounds(158, 158, 96, 19);
		contentPane.add(textDireccion);
		textDireccion.setColumns(10);
		
		textFechaRegistro = new JTextField();
		textFechaRegistro.setEditable(false);
		textFechaRegistro.setBounds(158, 187, 96, 19);
		contentPane.add(textFechaRegistro);
		textFechaRegistro.setColumns(10);
		
		JLabel lblBienvenida = new JLabel("Esta es la ventana de perfil, introduce tus datos:");
		lblBienvenida.setToolTipText("Esta es la ventana del perfil, inserte los datos que faltan");
		lblBienvenida.setBounds(121, 10, 222, 13);
		contentPane.add(lblBienvenida);
		
		JButton btnAplicarCambios = new JButton("Aplicar cambios");
		btnAplicarCambios.setToolTipText("Aplicar");
		btnAplicarCambios.setBounds(223, 220, 105, 21);
		contentPane.add(btnAplicarCambios);
		
		lblID = new JLabel("ID de usuario");
		lblID.setBounds(73, 45, 75, 13);
		contentPane.add(lblID);
		
		lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(73, 74, 45, 13);
		contentPane.add(lblNewLabel);
		
		lblCorreo = new JLabel("E-mail");
		lblCorreo.setBounds(73, 103, 45, 13);
		contentPane.add(lblCorreo);
		
		lblContrasena = new JLabel("Contraseña");
		lblContrasena.setBounds(73, 132, 59, 13);
		contentPane.add(lblContrasena);
		
		lblPais = new JLabel("Pais");
		lblPais.setBounds(73, 161, 45, 13);
		contentPane.add(lblPais);
		
		lblNewLabel_1 = new JLabel("Fecha de registro");
		lblNewLabel_1.setBounds(56, 190, 92, 13);
		contentPane.add(lblNewLabel_1);
	}
}
