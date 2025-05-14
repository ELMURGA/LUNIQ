package Views;

import javax.swing.*;
import java.awt.*;
import java.security.Principal;

public class MainFrame extends JFrame {

    private JTabbedPane tabbedPane;
    private JPanel sidebar;
	private Component logo;

    public MainFrame() {
        setTitle("LUNIQ - Tienda Online");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        getContentPane().setLayout(new BorderLayout());

        initSidebar();
        initTabbedPane();

        setVisible(true);
    }

    private void initSidebar() {
        sidebar = new JPanel();
        sidebar.setBackground(new Color(255, 128, 48));
        sidebar.setPreferredSize(new Dimension(180, 0));

     JButton btnProductos = new JButton("Productos");
     btnProductos.setBackground(new Color(238, 238, 237));
     btnProductos.setBounds(62, 139, 95, 29);
        JButton btnClientes = new JButton("Clientes");
        btnClientes.setBounds(62, 173, 95, 29);
        JButton btnPedidos = new JButton("Pedidos");
        btnPedidos.setBounds(64, 214, 93, 29);

        // Opcional: estilizar botones
        for (JButton btn : new JButton[]{btnProductos, btnClientes, btnPedidos}) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(160, 40));
        }
        sidebar.setLayout(null);

        sidebar.add(logo);
        sidebar.add(btnProductos);
        sidebar.add(btnClientes);
        sidebar.add(btnPedidos);

        getContentPane().add(sidebar, BorderLayout.WEST);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon(MainFrame.class.getResource("/Views/L.png")));
        lblNewLabel_1.setBounds(0, 0, 180, 120);ImageIcon originalIcon = new ImageIcon(MainFrame.class.getResource("/Views/L.png"));
Image scaledImage = originalIcon.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
lblNewLabel_1.setIcon(new ImageIcon(scaledImage));


        sidebar.add(lblNewLabel_1);

        // Acciones para cambiar pestañas
        btnProductos.addActionListener(e -> tabbedPane.setSelectedIndex(0));
        btnClientes.addActionListener(e -> tabbedPane.setSelectedIndex(1));
        btnPedidos.addActionListener(e -> tabbedPane.setSelectedIndex(2));
    }

    private void initTabbedPane() {
        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Productos", new JPanel());
        tabbedPane.addTab("Clientes", new JPanel());
        tabbedPane.addTab("Pedidos", new JPanel());

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
} 

