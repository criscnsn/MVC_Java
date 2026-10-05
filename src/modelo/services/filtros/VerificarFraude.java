package modelo.services.filtros;


import modelo.EstadoPedido;
import modelo.Pedido;

public class VerificarFraude implements Filtro {
    private static final double LIMITE_FRAUDE=5000.0;

    @Override
    public Pedido procesar(Pedido pedido) {
        if (pedido == null){
            return null;
        }

        if (pedido.getSubtotal() > LIMITE_FRAUDE) {
            pedido.setRevisionFraude(true);
            pedido.setEstado(EstadoPedido.PEDIDO_MARCADO_COMO_FRAUDE);
        }else {
            pedido.setRevisionFraude(false);
        }

        return pedido;
    }
}
