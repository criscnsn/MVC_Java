package modelo.services.filtros;


import modelo.EstadoPedido;
import modelo.Pedido;

public class ConfirmarPedido implements Filtro{
    @Override
    public Pedido procesar(Pedido pedido) {
        if (pedido == null) {
            return null;
        }
        if (pedido.isRevisionFraude()) {
            pedido.setEstado(EstadoPedido.PEDIDO_MARCADO_COMO_FRAUDE);
        } else if (pedido.getDescuento() > 0) {
            pedido.setEstado(EstadoPedido.PEDIDO_CON_DESCUENTO);
        } else {
            pedido.setEstado(EstadoPedido.PEDIDO_SIN_DESCUENTO);
        }
        return pedido;

    }
}
