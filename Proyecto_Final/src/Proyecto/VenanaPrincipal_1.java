package Proyecto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VenanaPrincipal_1 extends JFrame {
	private final ClaseConexion conexion;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ClaseConexion conexion =  new ClaseConexion("localhost", "3306", "root", "", "proyecto_bbdd");
		if(conexion.success()) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VenanaPrincipal_1 frame = new VenanaPrincipal_1(conexion);
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
	public VenanaPrincipal_1(ClaseConexion cn) {
		this.conexion = cn;
		
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 800, 700);

	        // Configuración del panel principal
	        contentPane = new JPanel();
	        contentPane.setForeground(new Color(0, 0, 0));
	        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
	        contentPane.setLayout(new BorderLayout());
	        contentPane.setBackground(new Color(255, 255, 255));
	        setContentPane(contentPane);

	        // Título
	        JLabel lblTitulo = new JLabel("Bienvenido a Luniq, ¿qué desea hacer?");
	        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
	        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	        lblTitulo.setBorder(new EmptyBorder(10, 0, 20, 0));
	        contentPane.add(lblTitulo, BorderLayout.NORTH);

	        // Panel de botones
	        JPanel panelBotones = new JPanel();
	        panelBotones.setLayout(new GridLayout(3, 1, 10, 20));
	        panelBotones.setOpaque(false);
	        contentPane.add(panelBotones, BorderLayout.CENTER);
	        
	        // Botón "Registrarme"
	        JButton btnRegistro = new JButton("Registrarme");
	        btnRegistro.setFont(new Font("Arial", Font.PLAIN, 16));
	        btnRegistro.setBackground(new Color(70, 130, 180));
	        btnRegistro.setForeground(new Color(0, 0, 0));
	        btnRegistro.setFocusPainted(false);
	        btnRegistro.addActionListener(e -> {
	            Ventana_Registro_2 vtnR = new Ventana_Registro_2(this.conexion);
	            vtnR.setVisible(true);
	            dispose();
	        });
	        panelBotones.add(btnRegistro);

	        // Botón "Iniciar sesión"
	        JButton btnInicioSesion = new JButton("Iniciar sesión");
	        btnInicioSesion.setFont(new Font("Arial", Font.PLAIN, 16));
	        btnInicioSesion.setBackground(new Color(34, 139, 34));
	        btnInicioSesion.setForeground(new Color(0, 0, 0));
	        btnInicioSesion.setFocusPainted(false);
	        btnInicioSesion.addActionListener(e -> {
	            Ventana_InicioSesion_2 vtnIS = new Ventana_InicioSesion_2(this.conexion);
	            vtnIS.setVisible(true);
	            dispose();
	        });
	        
	        panelBotones.add(btnInicioSesion);

	        
	     // Pie de página
	        JLabel lblFooter = new JLabel("© 2023 Luniq. Todos los derechos reservados.");
	        lblFooter.setFont(new Font("Arial", Font.ITALIC, 12));
	        lblFooter.setHorizontalAlignment(SwingConstants.CENTER);
	        lblFooter.setBorder(new EmptyBorder(20, 0, 0, 0));
	        contentPane.add(lblFooter, BorderLayout.SOUTH);
	    }
	}
