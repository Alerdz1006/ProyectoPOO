public class Ticket {

    // Genera la vista HTML del carrito de compras actual
    public static String generarVistaCarrito(Carrito carrito) {
        // Usamos StringBuilder para construir el HTML de forma eficiente
        StringBuilder html = new StringBuilder();

        // Comenzamos la estructura HTML básica con estilos CSS
        html.append("<html><body style='font-family:Segoe UI; padding:20px;'>");

        // Añadimos el título principal
        html.append("<h2 style='color:#212121; border-bottom:2px solid #007acc;'>Carrito Actual</h2>");

        // Creamos una tabla para mostrar los productos
        html.append("<table style='width:100%; border-collapse:collapse;'>");

        // Encabezados de la tabla (columnas)
        html.append("<tr style='background:#007acc; color:white;'>")
                .append("<th style='padding:8px; text-align:left;'>Producto</th>")
                .append("<th style='padding:8px; text-align:right;'>Precio</th>")
                .append("<th style='padding:8px; text-align:right;'>Cantidad</th>")
                .append("<th style='padding:8px; text-align:right;'>Subtotal</th>")
                .append("</tr>");

        double total = 0;

        // Recorremos todos los productos del carrito
        for (Producto p : carrito.getProductos()) {
            // Calculamos el subtotal para este producto
            double subtotal = p.getPrecio() * p.getCantidad();
            total += subtotal;  // Acumulamos al total general

            // Creamos una fila en la tabla por cada producto
            html.append("<tr>")
                    .append("<td style='padding:8px;'>").append(p.getNombre()).append("</td>")
                    .append("<td style='padding:8px; text-align:right;'>$").append(String.format("%.2f", p.getPrecio())).append("</td>")
                    .append("<td style='padding:8px; text-align:right;'>").append(p.getCantidad()).append("</td>")
                    .append("<td style='padding:8px; text-align:right;'>$").append(String.format("%.2f", subtotal)).append("</td>")
                    .append("</tr>");
        }

        // Cerramos la tabla y mostramos el total
        html.append("</table>");
        html.append("<p style='margin-top:20px; font-weight:bold;'>Total: $").append(String.format("%.2f", total)).append("</p>");
        html.append("</body></html>");

        return html.toString();
    }

    // Genera el ticket de compra final con IVA incluido
    public static String generarTicket(Carrito carrito) {
        StringBuilder html = new StringBuilder();
        html.append("<html><body style='font-family:Segoe UI; padding:20px;'>");
        html.append("<h2 style='color:#212121; border-bottom:2px solid #007acc;'>Ticket de Compra</h2>");
        html.append("<table style='width:100%; border-collapse:collapse;'>");

        // Encabezados de la tabla
        html.append("<tr style='background:#007acc; color:white;'>")
                .append("<th style='padding:8px; text-align:left;'>Producto</th>")
                .append("<th style='padding:8px; text-align:right;'>Precio</th>")
                .append("<th style='padding:8px; text-align:right;'>Cantidad</th>")
                .append("<th style='padding:8px; text-align:right;'>Subtotal</th>")
                .append("</tr>");

        double subtotal = 0;

        // Recorremos los productos para mostrar cada uno
        for (Producto p : carrito.getProductos()) {
            double productSubtotal = p.getPrecio() * p.getCantidad();
            subtotal += productSubtotal;

            html.append("<tr>")
                    .append("<td style='padding:8px;'>").append(p.getNombre()).append("</td>")
                    .append("<td style='padding:8px; text-align:right;'>$").append(String.format("%.2f", p.getPrecio())).append("</td>")
                    .append("<td style='padding:8px; text-align:right;'>").append(p.getCantidad()).append("</td>")
                    .append("<td style='padding:8px; text-align:right;'>$").append(String.format("%.2f", productSubtotal)).append("</td>")
                    .append("</tr>");
        }

        // Calculamos impuestos y total final
        double iva = subtotal * 0.16;  // IVA del 16%
        double total = subtotal + iva;

        // Mostramos los totales
        html.append("</table>");
        html.append("<div style='margin-top:20px;'>");
        html.append("<p style='font-weight:bold;'>Subtotal: $").append(String.format("%.2f", subtotal)).append("</p>");
        html.append("<p style='font-weight:bold;'>IVA (16%): $").append(String.format("%.2f", iva)).append("</p>");
        html.append("<p style='font-weight:bold; font-size:1.2em;'>Total a pagar: $").append(String.format("%.2f", total)).append("</p>");
        html.append("</div>");
        html.append("<p style='margin-top:10px;'>¡Gracias por su compra!</p>");
        html.append("</body></html>");

        return html.toString();
    }
}
