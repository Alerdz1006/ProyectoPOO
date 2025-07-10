// Importamos las librerías necesarias para construir la interfaz gráfica
import javax.swing.*;  // Para componentes como botones, campos de texto, etc.
import java.awt.*;     // Para el manejo de ventanas y diseños
import java.util.ArrayList;  // Para usar listas dinámicas
import java.util.List;       // Para trabajar con colecciones de datos

// Definimos la clase principal que representa nuestra ventana
public class Main extends JFrame {

    // Campos donde el usuario ingresará los datos del producto
    private JTextField nombreField;   // Para el nombre del producto
    private JTextField precioField;   // Para el precio unitario
    private JTextField cantidadField; // Para la cantidad deseada

    // El carrito donde guardaremos todos los productos
    private Carrito carrito;

    // Constructor: aquí configuramos la ventana principal
    public Main() {
        // Creamos un nuevo carrito vacío
        carrito = new Carrito();

        // Configuración básica de la ventana
        setTitle("Caja Registradora");  // El título que aparece arriba
        setSize(750, 500);              // Ancho x Alto en píxeles
        // Cerramos el programa cuando el usuario cierra la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);    // Centra la ventana en pantalla
        setLayout(new BorderLayout());  // Organiza los componentes
        // Color de fondo gris claro
        getContentPane().setBackground(new Color(245, 245, 245));

        // Creamos el título principal de la aplicación
        JLabel titulo = new JLabel("Caja Registradora", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));  // Fuente del título
        titulo.setForeground(new Color(33, 33, 33));  // Color de texto oscuro
        // Añadimos una línea azul debajo del título
        titulo.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 122, 204)));
        add(titulo, BorderLayout.NORTH);  // Lo colocamos en la parte superior

        // Creamos el panel principal donde irán los campos de entrada
        JPanel tarjeta = new JPanel(new GridBagLayout());
        tarjeta.setBackground(Color.WHITE);  // Fondo blanco
        // Borde gris con espacio interno
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Configuramos cómo se van a colocar los elementos dentro del panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);  // Espacio entre elementos
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Los elementos se expanden horizontalmente

        // Definimos estilos de fuente para las etiquetas y campos
        Font labelFont = new Font("Segoe UI", Font.BOLD, 15);  // Fuente para etiquetas
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14); // Fuente para campos

        // Configuramos el campo para el nombre del producto
        gbc.gridx = 0; gbc.gridy = 0;  // Posición en la cuadrícula (columna 0, fila 0)
        JLabel lblNombre = new JLabel("Nombre del producto:");
        lblNombre.setFont(labelFont);
        tarjeta.add(lblNombre, gbc);  // Añadimos la etiqueta al panel

        gbc.gridx = 1;  // Movemos a la columna 1 (misma fila)
        nombreField = new JTextField(20);  // Campo de texto para 20 caracteres
        nombreField.setFont(fieldFont);
        tarjeta.add(nombreField, gbc);  // Añadimos el campo de texto

        // Configuramos el campo para el precio del producto
        gbc.gridx = 0; gbc.gridy = 1;  // Columna 0, fila 1
        JLabel lblPrecio = new JLabel("Precio del producto:");
        lblPrecio.setFont(labelFont);
        tarjeta.add(lblPrecio, gbc);

        gbc.gridx = 1;
        precioField = new JTextField(20);
        precioField.setFont(fieldFont);
        tarjeta.add(precioField, gbc);

        // Configuramos el campo para la cantidad del producto
        gbc.gridx = 0; gbc.gridy = 2;  // Columna 0, fila 2
        JLabel lblCantidad = new JLabel("Cantidad del producto:");
        lblCantidad.setFont(labelFont);
        tarjeta.add(lblCantidad, gbc);

        gbc.gridx = 1;
        cantidadField = new JTextField(20);
        cantidadField.setFont(fieldFont);
        tarjeta.add(cantidadField, gbc);

        // Creamos un panel para los botones
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        botonesPanel.setBackground(Color.WHITE);

        // Creamos los tres botones principales
        JButton verCarritoBtn = new JButton(" Ver Carrito");
        JButton agregarBtn = new JButton(" Agregar");
        JButton generarTicketBtn = new JButton(" Ticket");

        // Configuramos el estilo de todos los botones
        JButton[] botones = {verCarritoBtn, agregarBtn, generarTicketBtn};
        for (JButton btn : botones) {
            btn.setBackground(new Color(0, 122, 204));  // Color azul
            btn.setForeground(Color.WHITE);  // Texto blanco
            btn.setFocusPainted(false);      // Sin borde de enfoque
            btn.setBorderPainted(false);    // Sin borde
            btn.setFont(new Font("Segoe UI", Font.BOLD, 13));  // Fuente
            btn.setPreferredSize(new Dimension(140, 40));  // Tamaño
        }

        // Añadimos los botones al panel
        botonesPanel.add(verCarritoBtn);
        botonesPanel.add(agregarBtn);
        botonesPanel.add(generarTicketBtn);

        // Añadimos el panel de botones al panel principal
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;  // Ocupa dos columnas
        tarjeta.add(botonesPanel, gbc);

        // Añadimos el panel principal al centro de la ventana
        add(tarjeta, BorderLayout.CENTER);

        // Asignamos las acciones a los botones
        verCarritoBtn.addActionListener(e -> mostrarCarrito());
        agregarBtn.addActionListener(e -> agregarAlCarrito());
        generarTicketBtn.addActionListener(e -> generarTicket());
    }

    // metodo encargar de agregar los productos al carrito
    private void agregarAlCarrito() {
        try {
            // Obtenemos los valores de los campos de texto
            String nombre = nombreField.getText().trim();
            double precio = Double.parseDouble(precioField.getText().trim());
            int cantidad = Integer.parseInt(cantidadField.getText().trim());

            // Validamos que el nombre no contenga números
            if (nombre.matches(".*\\d.*")) {
                JOptionPane.showMessageDialog(this, "El nombre del producto no puede contener números", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validamos que precio y cantidad sean positivos
            if (precio <= 0 || cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "Precio y cantidad deben ser mayores a cero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Creamos el producto y lo añadimos al carrito
            Producto producto = new Producto(nombre, precio, cantidad);
            carrito.agregarProducto(producto);

            // Limpiamos los campos después de agregar
            nombreField.setText("");
            precioField.setText("");
            cantidadField.setText("");

            // Mostramos mensaje de éxito al agrear un producto
            JOptionPane.showMessageDialog(this, "Producto agregado al carrito", "Exito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            // Si hay error en los números, mostramos mensaje
            JOptionPane.showMessageDialog(this, "Ingrese valores numericos validos para precio y cantidad", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // metodo para mostrar carrito, con validaciones
    private void mostrarCarrito() {
        if (carrito.getProductos().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El carrito está vacío", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Creamos un panel para mostrar el carrito
            JPanel panelPrincipal = new JPanel(new BorderLayout());

            // Generamos la vista del carrito
            String carritoHTML = Ticket.generarVistaCarrito(carrito);
            JLabel label = new JLabel(carritoHTML);
            JScrollPane scrollPane = new JScrollPane(label);
            scrollPane.setPreferredSize(new Dimension(550, 400));

            // Panel para los checkboxes de eliminación
            JPanel checkboxesPanel = new JPanel(new GridLayout(0, 1));
            checkboxesPanel.setBorder(BorderFactory.createTitledBorder("Seleccione productos a eliminar"));

            // Lista para guardar los checkboxes
            List<JCheckBox> checkboxes = new ArrayList<>();

            // Creamos un checkbox por cada producto
            for (Producto producto : carrito.getProductos()) {
                JCheckBox checkBox = new JCheckBox(producto.getNombre() + " - $" + String.format("%.2f", producto.getSubtotal()));
                checkboxes.add(checkBox);
                checkboxesPanel.add(checkBox);
            }

            // Botón para eliminar productos seleccionados
            JButton eliminarBtn = new JButton("Eliminar productos seleccionados");
            eliminarBtn.addActionListener(e -> {
                // Eliminamos los productos seleccionados (de atrás hacia adelante)
                for (int i = checkboxes.size() - 1; i >= 0; i--) {
                    if (checkboxes.get(i).isSelected()) {
                        carrito.getProductos().remove(i);
                    }
                }
                // Cerramos y volvemos a abrir la vista actualizada, ya con los productos actualziados
                Window window = SwingUtilities.getWindowAncestor(panelPrincipal);
                if (window != null) {
                    window.dispose();
                }
                mostrarCarrito();
            });

            // se añade al panel principal, lo anterior
            panelPrincipal.add(scrollPane, BorderLayout.CENTER);
            panelPrincipal.add(checkboxesPanel, BorderLayout.NORTH);
            panelPrincipal.add(eliminarBtn, BorderLayout.SOUTH);

            // mostrar el carrito, dialogo mas bien
            JOptionPane.showOptionDialog(
                    this,
                    panelPrincipal,
                    "Carrito Actual - Seleccione productos para eliminar",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new Object[]{},
                    null
            );
        }
    }

    // llamar metodo para generar el ticket, con validaciones
    private void generarTicket() {
        if (carrito.getProductos().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El carrito esta vacio", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Generamos el HTML del ticket
            String ticketHTML = Ticket.generarTicket(carrito);

            // Lo mostramos en un área con scroll
            JLabel label = new JLabel(ticketHTML);
            JScrollPane scrollPane = new JScrollPane(label);
            scrollPane.setPreferredSize(new Dimension(550, 500));

            JOptionPane.showMessageDialog(this, scrollPane, "Ticket de Compra", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // metodo que inicia la aplicación
    public static void main(String[] args) {
        // Ejecutamos la interfaz gráfica en el hilo adecuado
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
