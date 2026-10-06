package modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * - el cliente no puede estar vacío;
 * - debe existir al menos un producto;
 * - la cantidad debe ser mayor que cero;
 * - no puede superar la existencia;
 * - subtotal = Σ precio × cantidad;
 * - descuento del 10 % si subtotal ≥ $1,000;
 * - impuesto del 16 % sobre subtotal − descuento;
 * - estado final = PROCESADO.
 * 
 * NOTA: El Modelo no imprime información ni solicita datos al usuario.
 */
public class PedidoModelo {

    private static final double UMBRAL_DESCUENTO = 1000.0;
    private static final double PORCENTAJE_DESCUENTO = 0.10;
    private static final double TASA_IVA = 0.16;

    private final Map<Integer, Pedido> pedidos = new HashMap<>();
    private int siguienteId = 1;

    public Pedido registrarPedido(Pedido pedido) {
        validarDatos(pedido);
        comprobarDisponibilidad(pedido);
        calcularSubtotal(pedido);
        aplicarDescuento(pedido);
        calcularImpuestos(pedido);
        calcularTotal(pedido);
        confirmarPedido(pedido);
        guardarPedido(pedido);

        return pedido;
    }

    public void validarDatos(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("El pedido no puede ser nulo.");
        }
        if (pedido.getCliente() == null || pedido.getCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El cliente no puede estar vacío.");
        }
        List<Producto> productos = pedido.getProductos();
        if (productos == null || productos.isEmpty()) {
            pedido.setEstado(EstadoPedido.PEDIDO_SIN_PRODUCTOS);
            throw new IllegalArgumentException("Debe existir al menos un producto.");
        }
        for (Producto p : productos) {
            if (p == null || p.getCantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor que cero para cada producto.");
            }
        }
        pedido.setEstado(EstadoPedido.PEDIDO_VALIDO);
    }

    public void comprobarDisponibilidad(Pedido pedido) {
        Map<String, Integer> cantidadesTotales = new HashMap<>();
        for (Producto p : pedido.getProductos()) {
            cantidadesTotales.put(p.getNombre(),
                    cantidadesTotales.getOrDefault(p.getNombre(), 0) + p.getCantidad());
        }

        for (Producto p : pedido.getProductos()) {
            int solicitada = cantidadesTotales.get(p.getNombre());
            if (solicitada > p.getExistencia()) {
                pedido.setEstado(EstadoPedido.PRODUCTO_CON_EXISTENCIA_INSUFICIENTE);
                throw new IllegalArgumentException("La cantidad supera la existencia para el producto: " + p.getNombre());
            }
        }
    }

    public void calcularSubtotal(Pedido pedido) {
        double subtotal = 0.0;
        for (Producto p : pedido.getProductos()) {
            subtotal += p.getPrecio() * p.getCantidad();
        }
        pedido.setSubtotal(subtotal);
    }

    public void aplicarDescuento(Pedido pedido) {
        if (pedido.getSubtotal() >= UMBRAL_DESCUENTO) {
            double descuento = pedido.getSubtotal() * PORCENTAJE_DESCUENTO;
            pedido.setDescuento(descuento);
        } else {
            pedido.setDescuento(0.0);
        }
    }

    public void calcularImpuestos(Pedido pedido) {
        double baseImponible = Math.max(0, pedido.getSubtotal() - pedido.getDescuento());
        double impuestos = baseImponible * TASA_IVA;
        pedido.setImpuestos(impuestos);
    }

    public void calcularTotal(Pedido pedido) {
        double total = (pedido.getSubtotal() - pedido.getDescuento()) + pedido.getImpuestos();
        pedido.setTotal(total);
    }

    public void confirmarPedido(Pedido pedido) {
        pedido.setId(siguienteId++);
        pedido.setEstado(EstadoPedido.PROCESADO);
    }

    public void guardarPedido(Pedido pedido) {
        pedidos.put(pedido.getId(), pedido);
    }

    public Pedido consultarPedido(int id) {
        return pedidos.get(id);
    }

    public Map<Integer, Pedido> listarPedidos() {
        return new HashMap<>(pedidos);
    }
}
