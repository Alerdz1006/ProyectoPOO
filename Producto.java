// Definimos la clase Producto que representa un artículo en nuestra tienda
public class Producto {

    // Estos son los atributos que guardan la información del producto
    private String nombre;   // Nombre del producto ("Manzanas")
    private double precio;   // Precio por pieza (150)
    private int cantidad;   // Cantidad (5)

    // Constructor: crea un nuevo producto con los datos proporcionados
    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;    // Asigna el nombre recibido
        this.precio = precio;    // Asigna el precio recibido
        this.cantidad = cantidad; // Asigna la cantidad recibida
    }

    // Métodos para obtener los valores (getters):

    // Devuelve el nombre del producto
    public String getNombre() {
        return nombre;
    }

    // Devuelve el precio unitario del producto
    public double getPrecio() {
        return precio;
    }

    // Devuelve la cantidad de este producto
    public int getCantidad() {
        return cantidad;
    }

    // Calcula el subtotal (precio x cantidad)
    public double getSubtotal() {
        return precio * cantidad; // Multiplica precio por cantidad
    }
}
