package modelo;

import java.util.HashMap;
import java.util.Map;

/**
 * Modelo encargado de toda la lógica de negocio, validaciones,
 * cálculos matemáticos y persistencia en memoria de los pedidos.
 * NOTA: No contiene dependencias de UI ni solicita entrada al usuario.
 */
public class PedidoModelo {

    private static final double UMBRAL_DESCUENTO = 1000.0;
    private static final double PORCENTAJE_DESCUENTO = 0.10;
    private static final double TASA_IVA = 0.16;
    private static final double LIMITE_FRAUDE = 5000.0;

    private final Map<Integer, Pedido> pedidos = new HashMap<>();
    private int siguienteId = 1;

    public Pedido registrarPedido(Pedido pedido) {
        // 1. Validaciones de negocio y disponibilidad de stock
        validarPedido(pedido);

        // 2. Cálculos financieros
        double subtotal = calcularSubtotal(pedido);
        double descuento = calcularDescuento(subtotal);
        double baseImponible = Math.max(0, subtotal - descuento);
        double impuestos = baseImponible * TASA_IVA;
        double total = baseImponible + impuestos;

        // 3. Asignación de valores calculados
        pedido.setId(siguienteId++);
        pedido.setSubtotal(subtotal);
        pedido.setDescuento(descuento);
        pedido.setImpuestos(impuestos);
        pedido.setTotal(total);

        // 4. Determinación y corrección de estado de negocio
        if (subtotal > LIMITE_FRAUDE) {
            pedido.setRevisionFraude(true);
            pedido.setEstado(EstadoPedido.PEDIDO_MARCADO_COMO_FRAUDE);
        } else if (descuento > 0.0) {
            pedido.setRevisionFraude(false);
            pedido.setEstado(EstadoPedido.PEDIDO_CON_DESCUENTO);
        } else {
            pedido.setRevisionFraude(false);
            pedido.setEstado(EstadoPedido.PEDIDO_SIN_DESCUENTO);
        }

        // 5. Persistencia en memoria
        pedidos.put(pedido.getId(), pedido);

        return pedido;
    }

    public Pedido consultarPedido(int id) {
        return pedidos.get(id);
    }

    public Map<Integer, Pedido> listarPedidos() {
        return new HashMap<>(pedidos);
    }

    private double calcularSubtotal(Pedido pedido) {
        double subtotal = 0.0;
        for (Producto p : pedido.getProductos()) {
            subtotal += p.getPrecio() * p.getCantidad();
        }
        return subtotal;
    }

    private double calcularDescuento(double subtotal) {
        return (subtotal >= UMBRAL_DESCUENTO) ? subtotal * PORCENTAJE_DESCUENTO : 0.0;
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
