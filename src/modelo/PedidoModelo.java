package modelo;

import java.util.HashMap;
import java.util.Map;

/**
 * Modelo encargado de la lógica de negocio, validaciones,
 * cálculos matemáticos y persistencia en memoria de los pedidos.
 * NOTA: No debe imprimir datos ni solicitar entrada del usuario.
 */
public class PedidoModelo {

    private final Map<Integer, Pedido> pedidos = new HashMap<>();
    private int siguienteId = 1;

    public Pedido registrarPedido(Pedido pedido) {
        // 1. Validaciones de negocio
        validarPedido(pedido);

        // 2. Cálculos financieros
        double subtotal = 0.0;
        for (Producto p : pedido.getProductos()) {
            subtotal += p.getPrecio() * p.getCantidad();
        }

        double descuento = 0.0;
        if (subtotal >= 1000.0) {
            descuento = subtotal * 0.10;
        }

        double baseImponible = subtotal - descuento;
        double impuestos = baseImponible * 0.16;
        double total = baseImponible + impuestos;

        // 3. Asignación de valores calculados y estado
        pedido.setId(siguienteId++);
        pedido.setSubtotal(subtotal);
        pedido.setDescuento(descuento);
        pedido.setImpuestos(impuestos);
        pedido.setTotal(total);
        pedido.setEstado("PROCESADO");

        // 4. Almacenar pedido
        pedidos.put(pedido.getId(), pedido);

        // 5. Devolver resultado procesado
        return pedido;
    }

    public Pedido consultarPedido(int id) {
        return pedidos.get(id);
    }

    public Map<Integer, Pedido> listarPedidos() {
        return new HashMap<>(pedidos);
    }

    private void validarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("El pedido no puede ser nulo.");
        }
        if (pedido.getCliente() == null || pedido.getCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El cliente no puede estar vacío.");
        }
        if (pedido.getProductos() == null || pedido.getProductos().isEmpty()) {
            throw new IllegalArgumentException("Debe existir al menos un producto.");
        }
        for (Producto p : pedido.getProductos()) {
            if (p.getCantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor que cero para: " + p.getNombre());
            }
            if (p.getCantidad() > p.getExistencia()) {
                throw new IllegalArgumentException("La cantidad supera la existencia para el producto: " + p.getNombre());
            }
        }
    }
}
