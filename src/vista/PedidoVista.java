package vista;

import modelo.Pedido;
import modelo.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoVista {

    protected final Scanner scanner;

    public PedidoVista() {
        this.scanner = new Scanner(System.in);
    }

    public Pedido capturarPedido() {
        System.out.println("\n=== REGISTRO DE NUEVO PEDIDO ===");
        System.out.print("Ingrese el nombre del cliente: ");
        String cliente = scanner.nextLine().trim();

        List<Producto> productos = new ArrayList<>();
        boolean agregarMas = true;

        while (agregarMas) {
            System.out.println("\n--- Datos del Producto ---");
            System.out.print("Nombre del producto: ");
            String nombre = scanner.nextLine().trim();

            System.out.print("Precio unitario: ");
            double precio = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Cantidad solicitada: ");
            int cantidad = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Existencia disponible: ");
            int existencia = Integer.parseInt(scanner.nextLine().trim());

            productos.add(new Producto(nombre, precio, cantidad, existencia));

            System.out.print("¿Desea agregar otro producto? (s/n): ");
            String respuesta = scanner.nextLine().trim();
            if (!respuesta.equalsIgnoreCase("s")) {
                agregarMas = false;
            }
        }

        return new Pedido(cliente, productos);
    }

    public int capturarIdPedido() {
        System.out.print("\nIngrese el ID del pedido a consultar: ");
        String input = scanner.nextLine().trim();
        return Integer.parseInt(input);
    }

    public void mostrarResultado(Pedido pedido) {
        System.out.println("\n========================================");
        System.out.println("        DETALLE DE PEDIDO REGISTRADO    ");
        System.out.println("========================================");
        imprimirDetalleCompleto(pedido);
    }

    public void mostrarPedido(Pedido pedido) {
        System.out.println("\n========================================");
        System.out.println("         CONSULTA DE PEDIDO             ");
        System.out.println("========================================");
        imprimirDetalleCompleto(pedido);
    }

    public void mostrarError(String mensaje) {
        System.err.println("\n[ERROR DE VALIDACIÓN]: " + mensaje);
    }

    protected void imprimirDetalleCompleto(Pedido pedido) {
        System.out.println("Pedido ID : " + pedido.getId());
        System.out.println("Cliente   : " + pedido.getCliente());
        System.out.println("Estado    : " + pedido.getEstado());
        System.out.println("----------------------------------------");
        System.out.println("Productos:");
        if (pedido.getProductos() != null) {
            for (Producto p : pedido.getProductos()) {
                System.out.printf(" - %s: %d unidades x $%.2f (Existencia: %d)%n",
                        p.getNombre(), p.getCantidad(), p.getPrecio(), p.getExistencia());
            }
        }
        System.out.println("----------------------------------------");
        System.out.printf("Subtotal  : $%.2f%n", pedido.getSubtotal());
        System.out.printf("Descuento : $%.2f%n", pedido.getDescuento());
        System.out.printf("Impuestos : $%.2f%n", pedido.getImpuestos());
        System.out.printf("Total     : $%.2f%n", pedido.getTotal());
        System.out.println("========================================\n");
    }
}
