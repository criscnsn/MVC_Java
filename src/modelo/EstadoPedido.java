package modelo;

/**
 * Estados posibles de un pedido dentro del ciclo de vida del negocio.
 * Según la especificación del ADA (Línea 173 y 219), el estado final obligatorio es PROCESADO.
 */
public enum EstadoPedido {
    PROCESADO,
    PEDIDO_PROCESADO,
    PEDIDO_VALIDO,
    PEDIDO_SIN_PRODUCTOS,
    PRODUCTO_CON_EXISTENCIA_INSUFICIENTE,
    PEDIDO_CON_DESCUENTO,
    PEDIDO_SIN_DESCUENTO;

    @Override
    public String toString() {
        if (this == PROCESADO || this == PEDIDO_PROCESADO) {
            return "PROCESADO";
        }
        return name();
    }
}
