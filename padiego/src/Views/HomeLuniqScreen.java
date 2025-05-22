package Views;

import javax.swing.*;
import java.awt.*;

public class HomeLuniqScreen extends JFrame {

    public HomeLuniqScreen() {
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

        rightPanel.add(perfilBtn);
        rightPanel.add(carritoBtn);

        header.add(rightPanel, BorderLayout.EAST);

        getContentPane().add(header, BorderLayout.NORTH);
        
                // Buscador en el centro
                JTextField searchField = new JTextField("Buscar en LUNIQ");
                header.add(searchField, BorderLayout.CENTER);
                searchField.setBackground(new Color(0, 0, 0));
                searchField.setForeground(new Color(255, 255, 255));
                searchField.setPreferredSize(new Dimension(300, 35));

        // --- Menú de navegación ---
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        navPanel.setBackground(Color.WHITE);

        String[] categorias = {"Novedades", "Ropa", "Zapatos", "Accesorios", "Deporte", "Marcas"};
        for (String cat : categorias) {
            JButton btn = new JButton(cat);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
            navPanel.add(btn);
        }

        getContentPane().add(navPanel, BorderLayout.CENTER);

        // --- Sección principal ---
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 3, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));

        for (int i = 1; i <= 6; i++) {
            JPanel productCard = new JPanel();
            productCard.setBackground(Color.WHITE);
            productCard.setLayout(new BorderLayout());
            productCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JLabel img = new JLabel("[Imagen Producto " + i + "]", SwingConstants.CENTER);
            img.setPreferredSize(new Dimension(100, 100));
            JLabel nombre = new JLabel("Producto " + i, SwingConstants.CENTER);

            productCard.add(img, BorderLayout.CENTER);
            productCard.add(nombre, BorderLayout.SOUTH);

            mainPanel.add(productCard);
        }

        getContentPane().add(mainPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new HomeLuniqScreen();
    }
}
