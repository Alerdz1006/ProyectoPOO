// Importamos las clases necesarias para usar listas
import java.util.ArrayList;  // Para crear listas dinámicas
import java.util.List;       // Para trabajar con la interfaz List

// La clase Carrito representa el carrito de compras del cliente
public class Carrito {

    // Lista que almacena todos los productos agregados
    private List<Producto> productos;

    // Constructor: crea un carrito vacío
    public Carrito() {
        productos = new ArrayList<>();  // Inicializa la lista vacía
    }

    // metodo para eliminar el producto tanto d carrito como del ticket
    public void eliminarProducto(int indice) {
        // Verifica que el índice sea válido antes de eliminar
        if (indice >= 0 && indice < productos.size()) {
            productos.remove(indice);  // Elimina el producto en esa posición
        }
    }

    // metodo para agregar un producto
    public void agregarProducto(Producto producto) {
        productos.add(producto);  // Añade el producto a la lista
    }

    // metodo para listar lls productos
    public List<Producto> getProductos() {
        return productos;  // Devuelve toda la lista de productos
    }

    // Calcula el subtotal (suma de todos los productos sin IVA)
    public double calcularSubtotal() {
        // Usa streams para sumar los subtotales de cada producto
        return productos.stream()
                .mapToDouble(Producto::getSubtotal)
                .sum();
    }

    // Calcula el IVA (16% en este caso, del subtotal)
    public double calcularIVA() {
        return calcularSubtotal() * 0.16;  // IVA del 16%
    }

    // Calcula el total a pagar (subtotal + IVA)
    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }
}
