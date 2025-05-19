package Views;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.List;

public class LuniqCarritoScreen extends JFrame {

    private static final long serialVersionUID = 1L;

    public LuniqCarritoScreen(List<String> carrito) {
        setTitle("Carrito - LUNIQ");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // ======== HEADER ========
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("");
        logo.setIcon(new ImageIcon(LuniqCarritoScreen.class.getResource("/Views/L-REDI.png")));
        logo.setFont(new Font("SansSerif", Font.BOLD, 24));
        header.add(logo, BorderLayout.WEST);

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        navPanel.setBackground(Color.WHITE);

        JButton btnNewButton = new JButton("HOMBRES");
        navPanel.add(btnNewButton);
        header.add(navPanel, BorderLayout.CENTER);

        JButton btnNewButton_1 = new JButton("MUJERES");
        navPanel.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("NIÑOS");
        navPanel.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("OFERTAS");
        navPanel.add(btnNewButton_3);

        JTextField search = new JTextField("Buscar...");
        search.setPreferredSize(new Dimension(200, 30));
        header.add(search, BorderLayout.EAST);

        getContentPane().add(header, BorderLayout.NORTH);

        // ======== CONTENIDO PRINCIPAL ========
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(Color.WHITE);
        main.setBorder(new EmptyBorder(20, 40, 20, 40));

        // ---- Lista de productos ----
        JPanel productosPanel = new JPanel();
        productosPanel.setLayout(new BoxLayout(productosPanel, BoxLayout.Y_AXIS));
        productosPanel.setBackground(Color.WHITE);

        // Mostrar los productos del carrito
        for (String producto : carrito) {
            productosPanel.add(crearItemCarrito(producto, "Descripción del producto", "Precio"));
            productosPanel.add(Box.createVerticalStrut(20)); // Espaciado entre productos
        }

        main.add(productosPanel, BorderLayout.CENTER);

        // ---- Resumen del pedido ----
        JPanel resumen = new JPanel();
        resumen.setLayout(new BoxLayout(resumen, BoxLayout.Y_AXIS));
        resumen.setBorder(BorderFactory.createTitledBorder("Resumen del pedido"));
        resumen.setPreferredSize(new Dimension(300, 200));

        resumen.add(new JLabel("Subtotal: Calculado dinámicamente"));
        resumen.add(new JLabel("Envío: 0,00 €"));
        resumen.add(Box.createVerticalStrut(10));
        resumen.add(new JLabel("Total: Calculado dinámicamente"));
        resumen.add(Box.createVerticalStrut(20));

        JButton btnPedir = new JButton("Comenzar pedido");
        btnPedir.setBackground(Color.BLACK);
        btnPedir.setForeground(new Color(0, 0, 0));
        resumen.add(btnPedir);

        main.add(resumen, BorderLayout.EAST);

        getContentPane().add(main, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel crearItemCarrito(String marca, String descripcion, String precio) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panel.setBackground(Color.WHITE);

        JLabel imagen = new JLabel();
        imagen.setPreferredSize(new Dimension(100, 100));
        imagen.setBorder(new LineBorder(Color.GRAY));
        imagen.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(imagen, BorderLayout.WEST);

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(Color.WHITE);
        info.setBorder(new EmptyBorder(10, 10, 10, 10));

        info.add(new JLabel(marca));
        info.add(new JLabel(descripcion));
        JLabel precioLabel = new JLabel(precio);
        precioLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        info.add(precioLabel);

        panel.add(info, BorderLayout.CENTER);

        JPanel opciones = new JPanel(new FlowLayout());
        opciones.setBackground(Color.WHITE);
        JComboBox<String> cantidad = new JComboBox<>(new String[]{"1", "2", "3"});
        JButton eliminar = new JButton("X");
        opciones.add(cantidad);
        opciones.add(eliminar);

        panel.add(opciones, BorderLayout.EAST);

        return panel;
    }
}
