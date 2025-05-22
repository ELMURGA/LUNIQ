package Proyecto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class PerfilPanel extends JFrame {

    private static final long serialVersionUID = 1L;

    public PerfilPanel(String correoUsuario) { // Recibe el correo del usuario actual
        setTitle("Perfil - LUNIQ");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Editar perfil", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        getContentPane().add(titulo, BorderLayout.NORTH);

        // Panel de formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        JTextField txtNombre = new JTextField();
        JTextField txtApellido = new JTextField();
		JTextField txtCorreo = new JTextField(correoUsuario);
        txtCorreo.setEditable(false); // El correo no debe ser editable
        JPasswordField txtContrasena = new JPasswordField();

        JComboBox<String> comboSexo = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"});
        comboSexo.setBackground(new Color(255, 255, 255));

        JComboBox<String> comboDia = new JComboBox<>();
        for (int i = 1; i <= 31; i++) comboDia.addItem(String.valueOf(i));

        JComboBox<String> comboMes = new JComboBox<>(new String[]{
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        });

        JComboBox<String> comboAnio = new JComboBox<>();
        for (int i = 1980; i <= 2025; i++) comboAnio.addItem(String.valueOf(i));

        JTextField txtPais = new JTextField("España");

        formPanel.add(new JLabel("Nombre:")); formPanel.add(txtNombre);
        formPanel.add(new JLabel("Apellido:")); formPanel.add(txtApellido);
        formPanel.add(new JLabel("Correo electrónico:")); formPanel.add(txtCorreo);
        formPanel.add(new JLabel("Contraseña:")); formPanel.add(txtContrasena);
        formPanel.add(new JLabel("Sexo:")); formPanel.add(comboSexo);
        formPanel.add(new JLabel("Fecha de nacimiento:"));

        JPanel fechaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        fechaPanel.add(comboDia);
        fechaPanel.add(comboMes);
        fechaPanel.add(comboAnio);
        fechaPanel.setBackground(Color.WHITE);
        formPanel.add(fechaPanel);

        formPanel.add(new JLabel("País o región:")); formPanel.add(txtPais);

        getContentPane().add(formPanel, BorderLayout.CENTER);

        // Cargar datos del usuario desde la base de datos
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1", "Miproyecto", "Medac24")) {
            String sql = "SELECT nombre, apellido, contrasena, sexo, fecha_nacimiento, pais FROM perfil WHERE correo = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, correoUsuario);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        txtNombre.setText(rs.getString("nombre"));
                        txtApellido.setText(rs.getString("apellido"));
                        txtContrasena.setText(rs.getString("contrasena"));
                        comboSexo.setSelectedItem(rs.getString("sexo"));

                        // Dividir la fecha de nacimiento
                        String[] fecha = rs.getString("fecha_nacimiento").split("-");
                        comboAnio.setSelectedItem(fecha[0]);
                        comboMes.setSelectedIndex(Integer.parseInt(fecha[1]) - 1);
                        comboDia.setSelectedItem(fecha[2]);

                        txtPais.setText(rs.getString("pais"));
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los datos del perfil: " + ex.getMessage());
        }

        // Botón de guardar
        JButton btnGuardar = new JButton("Guardar perfil");
        btnGuardar.setBackground(new Color(0, 0, 0));
        btnGuardar.setForeground(new Color(0, 0, 0));
        btnGuardar.setPreferredSize(new Dimension(150, 40));

        btnGuardar.addActionListener((ActionEvent e) -> {
            // Recuperar los datos del formulario
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String contrasena = new String(txtContrasena.getPassword());
            String sexo = (String) comboSexo.getSelectedItem();
            String dia = (String) comboDia.getSelectedItem();
            String mes = String.valueOf(comboMes.getSelectedIndex() + 1); // Convertir mes a número
            String anio = (String) comboAnio.getSelectedItem();
            String fechaNacimiento = anio + "-" + mes + "-" + dia; // Formato de fecha
            String pais = txtPais.getText();

            // Guardar o actualizar en la base de datos
            try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1", "Miproyecto", "Medac24")) {
                String sql = "MERGE INTO perfil p USING (SELECT ? AS correo FROM dual) src " +
                             "ON (p.correo = src.correo) " +
                             "WHEN MATCHED THEN " +
                             "UPDATE SET nombre = ?, apellido = ?, contrasena = ?, sexo = ?, fecha_nacimiento = ?, pais = ? " +
                             "WHEN NOT MATCHED THEN " +
                             "INSERT (correo, nombre, apellido, contrasena, sexo, fecha_nacimiento, pais) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, correoUsuario);
                    stmt.setString(2, nombre);
                    stmt.setString(3, apellido);
                    stmt.setString(4, contrasena);
                    stmt.setString(5, sexo);
                    stmt.setString(6, fechaNacimiento);
                    stmt.setString(7, pais);
                    stmt.setString(8, correoUsuario);
                    stmt.setString(9, nombre);
                    stmt.setString(10, apellido);
                    stmt.setString(11, contrasena);
                    stmt.setString(12, sexo);
                    stmt.setString(13, fechaNacimiento);
                    stmt.setString(14, pais);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Perfil guardado exitosamente.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al guardar el perfil: " + ex.getMessage());
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setForeground(new Color(252, 255, 255));
        bottomPanel.setBackground(new Color(252, 255, 255));
        bottomPanel.add(btnGuardar);

        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PerfilPanel frame = new PerfilPanel("usuario@ejemplo.com");  // Reemplaza con el correo del usuario actual
            frame.setVisible(true);
        });
    }
}
