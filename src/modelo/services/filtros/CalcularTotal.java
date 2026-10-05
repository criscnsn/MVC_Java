package modelo.services.filtros;


import modelo.Pedido;

public class CalcularTotal implements Filtro{

    @Override
    public Pedido procesar(Pedido pedido) {
        if (pedido == null){
            return null;
        }
        double total = pedido.getSubtotal() - pedido.getDescuento() + pedido.getImpuestos();
        pedido.setTotal(total);
        return pedido;
    }
}
