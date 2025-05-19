package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class HomeLuniqScreen extends JFrame {
    private static final long serialVersionUID = 1L;
    private List<String> carrito; // Lista para almacenar los productos seleccionados

    public HomeLuniqScreen() {
        carrito = new ArrayList<>(); // Inicializar la lista del carrito

        setTitle("LUNIQ - Home");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // --- Header Panel ---
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Logo a la izquierda
        JLabel logoLabel = new JLabel(new ImageIcon(HomeLuniqScreen.class.getResource("/Views/L-REDI.png")));
        header.add(logoLabel, BorderLayout.WEST);

        // Perfil y carrito a la derecha
        JPanel rightPanel = new JPanel();
        rightPanel.setForeground(new Color(0, 0, 0));
        rightPanel.setBackground(new Color(255, 255, 255));

        JButton perfilBtn = new JButton("Perfil");
        perfilBtn.setForeground(new Color(0, 0, 0));
        perfilBtn.setBackground(new Color(0, 0, 0));
        JButton carritoBtn = new JButton("Carrito");
        carritoBtn.setForeground(new Color(0, 0, 0));
        carritoBtn.setBackground(new Color(0, 0, 0));

        // ActionListener para el botón de perfil
        perfilBtn.addActionListener(e -> {
            String correoUsuario = "usuario@ejemplo.com"; // Reemplaza con el correo del usuario actual
            PerfilPanel perfilPanel = new PerfilPanel(correoUsuario);
            perfilPanel.setVisible(true);
            this.dispose(); // Cierra la ventana actual
        });

        // ActionListener para el botón de carrito
        carritoBtn.addActionListener(e -> {
            LuniqCarritoScreen carritoScreen = new LuniqCarritoScreen(carrito); // Pasar la lista del carrito
            carritoScreen.setVisible(true);
            this.dispose(); // Cierra la ventana actual
        });

        rightPanel.add(perfilBtn);
        rightPanel.add(carritoBtn);

        header.add(rightPanel, BorderLayout.EAST);

        // Buscador en el centro
        JTextField searchField = new JTextField("Buscar en LUNIQ");
        header.add(searchField, BorderLayout.CENTER);
        searchField.setBackground(new Color(0, 0, 0));
        searchField.setForeground(new Color(255, 255, 255));
        searchField.setPreferredSize(new Dimension(300, 35));

        getContentPane().add(header, BorderLayout.NORTH);

        // --- Menú de navegación ---
        JPanel navPanel = new JPanel();
        navPanel.setBackground(Color.WHITE);

        String[] categorias = {"Novedades", "Ropa", "Zapatos", "Accesorios", "Deporte", "Marcas"};
        for (String cat : categorias) {
            JButton btn = new JButton(cat);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
            navPanel.add(btn);
        }

        getContentPane().add(navPanel, BorderLayout.CENTER);
        navPanel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("NIKE AIR MAX Dn8");
        lblNewLabel_1.setFont(new Font("Al Nile", Font.BOLD, 20));
        lblNewLabel_1.setBounds(609, 60, 174, 28);
        navPanel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("Powered by 8");
        lblNewLabel_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        lblNewLabel_1_1_1.setBounds(609, 114, 109, 28);
        navPanel.add(lblNewLabel_1_1_1);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(234, 43, 330, 171);
        lblNewLabel.setIcon(new ImageIcon(HomeLuniqScreen.class.getResource("/Views/ZAPATOS-2.png")));
        navPanel.add(lblNewLabel);

        JLabel lblNewLabel_1_1 = new JLabel("MOVIMIENTO alucinante");
        lblNewLabel_1_1.setFont(new Font("Georgia", Font.PLAIN, 15));
        lblNewLabel_1_1.setBounds(609, 89, 196, 28);
        navPanel.add(lblNewLabel_1_1);

        JFormattedTextField formattedTextField = new JFormattedTextField();
        formattedTextField.setBounds(-27, 9, 1057, 241);
        formattedTextField.setFont(new Font("Georgia", Font.PLAIN, 13));
        formattedTextField.setBackground(new Color(134, 195, 88));
        navPanel.add(formattedTextField);

        // --- Sección principal ---
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 3, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));

        for (int i = 1; i <= 6; i++) {
            String productName = "Producto " + i; // Nombre del producto
            JPanel productCard = new JPanel();
            productCard.setBackground(Color.WHITE);
            productCard.setLayout(new BorderLayout());
            productCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JLabel img = new JLabel("[Imagen Producto " + i + "]", SwingConstants.CENTER);
            img.setPreferredSize(new Dimension(100, 100));
            JLabel nombre = new JLabel(productName, SwingConstants.CENTER);

            productCard.add(img, BorderLayout.CENTER);
            productCard.add(nombre, BorderLayout.SOUTH);

            // Agregar MouseListener para detectar clics
            productCard.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    carrito.add(productName); // Agregar producto al carrito
                    JOptionPane.showMessageDialog(HomeLuniqScreen.this, productName + " agregado al carrito.");
                }
            });

            mainPanel.add(productCard);
        }

        getContentPane().add(mainPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new HomeLuniqScreen();
    }
}
