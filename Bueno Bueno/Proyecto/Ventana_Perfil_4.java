package Proyecto;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Ventana_Perfil_4 extends JFrame {
    public final ClaseConexion conexion;
    private String nombreUsuarioActual;
    
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textID_Usuario;
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textEmail;
    private JTextField textNombreUsuario;
    private JTextField textContrasena;
    private JTextField textPais;
    private JTextField textFechaRegistro;

    public static void main(String[] args) {
        ClaseConexion cn = new ClaseConexion("localhost", "3306", "root", "", "proyecto_bbdd");
        if(cn.success()) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Ventana_Perfil_4 frame = new Ventana_Perfil_4(cn, "testuser");
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public Ventana_Perfil_4(ClaseConexion cn, String nombreUsuario) {
        this.conexion = cn;
        this.nombreUsuarioActual = nombreUsuario;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        initializeComponents();
        cargarDatosUsuario();
    }

    private void initializeComponents() {
        // Fuente más grande para todos los componentes
        Font font = new Font("Tahoma", Font.PLAIN, 16);
        
        // Label de bienvenida
        JLabel lblBienvenida = new JLabel("Esta es la ventana de perfil, introduce tus datos:");
        lblBienvenida.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblBienvenida.setBounds(150, 30, 500, 30);
        contentPane.add(lblBienvenida);
        
        // Configuración común para labels
        int labelX = 150;
        int fieldX = 350;
        int initialY = 80;
        int spacing = 50;
        
        // Campos del formulario
        addFormField("ID de usuario:", textID_Usuario = new JTextField(), labelX, initialY, fieldX, true, font);
        addFormField("Nombre:", textNombre = new JTextField(), labelX, initialY + spacing, fieldX, false, font);
        addFormField("Apellido:", textApellido = new JTextField(), labelX, initialY + spacing*2, fieldX, false, font);
        addFormField("E-mail:", textEmail = new JTextField(), labelX, initialY + spacing*3, fieldX, true, font);
        addFormField("Nombre usuario:", textNombreUsuario = new JTextField(), labelX, initialY + spacing*4, fieldX, true, font);
        addFormField("Contraseña:", textContrasena = new JTextField(), labelX, initialY + spacing*5, fieldX, true, font);
        addFormField("País:", textPais = new JTextField(), labelX, initialY + spacing*6, fieldX, false, font);
        addFormField("Fecha registro:", textFechaRegistro = new JTextField(), labelX, initialY + spacing*7, fieldX, true, font);
        
        // Botones más grandes
        JButton btnAplicarCambios = new JButton("Aplicar cambios");
        btnAplicarCambios.setFont(font);
        btnAplicarCambios.setBounds(250, initialY + spacing*8 + 20, 150, 40);
        btnAplicarCambios.addActionListener(e -> aplicarCambios());
        contentPane.add(btnAplicarCambios);
        
        JButton btnVolver = new JButton("Volver atrás");
        btnVolver.setFont(font);
        btnVolver.setBounds(450, initialY + spacing*8 + 20, 150, 40);
        btnVolver.addActionListener(e -> {
            Ventana_Tienda_3 vt3 = new Ventana_Tienda_3(this.conexion, nombreUsuarioActual);
            vt3.setVisible(true);
            dispose();
        });
        contentPane.add(btnVolver);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(Ventana_Perfil_4.class.getResource("/Proyecto/logo.png")));
        lblNewLabel.setBounds(29, 30, 69, 81);
        contentPane.add(lblNewLabel);
    }

    private void addFormField(String labelText, JTextField textField, int labelX, int y, int fieldX, boolean editable, Font font) {
        JLabel label = new JLabel(labelText);
        label.setFont(font);
        label.setBounds(labelX, y, 180, 30);
        contentPane.add(label);
        
        textField.setFont(font);
        textField.setBounds(fieldX, y, 300, 30);
        textField.setEditable(!editable);
        contentPane.add(textField);
        textField.setColumns(10);
    }

    private void cargarDatosUsuario() {
        ResultSet rs = this.conexion.obtenerDatosUsuario(nombreUsuarioActual);
        
        try {
            if (rs != null && rs.next()) {
                textID_Usuario.setText(rs.getString("id_usuario"));
                textNombre.setText(rs.getString("nombre"));
                textApellido.setText(rs.getString("apellido"));
                textEmail.setText(rs.getString("email"));
                textNombreUsuario.setText(rs.getString("nombre_usuario"));
                textContrasena.setText(rs.getString("contraseña"));
                textPais.setText(rs.getString("pais"));
                textFechaRegistro.setText(rs.getString("fecha_registro"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void aplicarCambios() {
        String nombre = textNombre.getText();
        String apellido = textApellido.getText();
        String pais = textPais.getText();
        
        boolean actualizado = this.conexion.actualizarUsuario(
            nombreUsuarioActual, nombre, apellido, pais);
        
        if (actualizado) {
            System.out.println("Datos actualizados correctamente");
        } else {
            System.out.println("No se pudo actualizar los datos");
        }
    }
}